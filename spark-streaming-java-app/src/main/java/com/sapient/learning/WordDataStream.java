package com.sapient.learning;

import static com.datastax.spark.connector.japi.CassandraJavaUtil.javaFunctions;
import static com.datastax.spark.connector.japi.CassandraJavaUtil.mapToRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.Optional;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.Function3;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.State;
import org.apache.spark.streaming.StateSpec;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaMapWithStateDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;

import com.mongodb.spark.MongoSpark;

import scala.Tuple2;

public class WordDataStream {
	
	public static JavaSparkContext javaSparkContext;
	
	public static MyProperties props = new MyProperties();

	public static void main(String[] args) throws InterruptedException {
		
		Logger.getLogger("org").setLevel(Level.OFF);
		Logger.getLogger("akka").setLevel(Level.OFF);

		Map<String, Object> kafkaParams = new HashMap<>();
		kafkaParams.put("bootstrap.servers", props.getValue("kafka.server"));
		kafkaParams.put("key.deserializer", StringDeserializer.class);
		kafkaParams.put("value.deserializer", StringDeserializer.class);
		kafkaParams.put("group.id", "use_a_separate_group_id_for_each_stream");
		kafkaParams.put("auto.offset.reset", "latest");
		kafkaParams.put("enable.auto.commit", false);

		Collection<String> topics = Arrays.asList(props.getValue("kafka.topic"));
		
		SparkConf sparkConf = new SparkConf();
		sparkConf.setMaster("local[2]");
		sparkConf.setAppName("WordCountingAppWithCheckpoint");
		sparkConf.setIfMissing("spark.cassandra.connection.host", props.getValue("cassandra.server"));
		sparkConf.setIfMissing("spark.mongodb.output.uri", 
				"mongodb://"
						+ props.getValue("mongo.server")
						+ "/" + props.getValue("mongo.database")
						+ "." + props.getValue("mongo.collection") );

		SparkSession sparkSession = SparkSession.builder().config(sparkConf).getOrCreate();
		
		SparkContext sparkContext = sparkSession.sparkContext();
		
		sparkContext = sparkSession.sparkContext();
		
		javaSparkContext = JavaSparkContext.fromSparkContext(sparkContext);
		
		JavaStreamingContext streamingContext = new JavaStreamingContext(javaSparkContext, Durations.seconds(1));
		
		streamingContext.checkpoint("./.checkpoint");

		JavaInputDStream<ConsumerRecord<String, String>> messages = KafkaUtils.createDirectStream(
				streamingContext,
				LocationStrategies.PreferConsistent(),
				ConsumerStrategies.<String, String>Subscribe(topics, kafkaParams));
		
		JavaPairDStream<String, String> results = messages.mapToPair(
				(PairFunction<ConsumerRecord<String, String>, String, String>) 
					record -> new Tuple2<>(record.key(), record.value())
				);
		
		JavaDStream<String> lines = results.map(
				(Function<Tuple2<String, String>, String>)
					tuple -> tuple._2
				);
		
		JavaDStream<String> words = lines.flatMap(
				(FlatMapFunction<String, String>)
					str -> Arrays.asList(cleanse(str)).iterator()
				);
		
		JavaPairDStream<String, Integer> wordCounts = words.mapToPair(
				(PairFunction<String, String, Integer>)
					str -> new Tuple2<>(str, 1)
				).reduceByKey(
						(Function2<Integer, Integer, Integer>)
							(i1, i2) -> i1 + i2
						);
				
		JavaMapWithStateDStream<String, Integer, Integer, Tuple2<String, Integer>> cumulativeWordCounts = wordCounts
				.mapWithState(StateSpec.function(
						(Function3<String, Optional<Integer>, State<Integer>, Tuple2<String, Integer>>)
							(word, one, state) -> {
								int sum = one.orElse(0) + (state.exists() ? state.get() : 0);
								Tuple2<String, Integer> output = new Tuple2<>(word, sum);
								state.update(sum);
								return output;
							}	
						));
		
		cumulativeWordCounts.foreachRDD(
				(VoidFunction<JavaRDD<Tuple2<String,Integer>>>)
					javaRdd -> {
						List<Tuple2<String,Integer>> wordCountList = javaRdd.collect();
				        for (Tuple2<String,Integer> tuple : wordCountList) {
				             List<Word> listWord = Arrays.asList(new Word(tuple._1, tuple._1, tuple._2));
				             JavaRDD<Word> rddWord = javaSparkContext.parallelize(listWord);
				             
				             javaFunctions(rddWord).writerBuilder(
				            		 props.getValue("cassandra.keyspace"), 
				            		 props.getValue("cassandra.table"), 
				            		 mapToRow(Word.class)).saveToCassandra();
				             
				             MongoSpark.save(sparkSession.createDataFrame(rddWord, Word.class).write().mode("append"));
				        }
					}
				);
		
		cumulativeWordCounts.print();
		
		streamingContext.start();
		streamingContext.awaitTermination();
		
	}
	
	public static String[] cleanse(String str) {
		
        String urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern p = Pattern.compile(urlPattern,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        int i = 0;
        while (m.find()) {
            str = str.replaceAll(m.group(i),"").trim();
            i++;
        }
		
		String[] words = str
				.replaceAll("@\\p{L}+", "")
				.replaceAll("[^a-zA-Z ]", "")
				.replaceAll("\\b\\w{1,4}\\b","")
				.toLowerCase()
				.split("\\s+");
		
		List<String> list = new ArrayList<String>(Arrays.asList(words));
		list.removeIf(StringUtils::isBlank);
		words = list.toArray(new String[0]);
		
		return words;
	}
	
}
