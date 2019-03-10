package com.sapient.learning;

import static org.apache.storm.cassandra.DynamicStatementBuilder.async;
import static org.apache.storm.cassandra.DynamicStatementBuilder.fields;
import static org.apache.storm.cassandra.DynamicStatementBuilder.simpleQuery;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.cassandra.bolt.CassandraWriterBolt;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.mongodb.bolt.MongoInsertBolt;
import org.apache.storm.mongodb.common.mapper.MongoMapper;
import org.apache.storm.mongodb.common.mapper.SimpleMongoMapper;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

@SuppressWarnings("serial")
public class WordDataStream implements Serializable {

	public static MyProperties props = new MyProperties();

	@SuppressWarnings("unused")
	public static void main(String[] args) throws InterruptedException {
		
		Config config = new Config();

		WordDataStream wordDataStream = new WordDataStream();

		SplitSentenceBolt splitSentenceBolt = wordDataStream.new SplitSentenceBolt();
		WordCountBolt wordCountBolt = wordDataStream.new WordCountBolt();
		ReportBolt reportBolt = wordDataStream.new ReportBolt();
		
		config.put("cassandra.nodes", props.getValue("cassandra.server"));
		//config.put("cassandra.port", "9042");
		config.put("cassandra.keyspace", props.getValue("cassandra.keyspace"));
		config.put("cassandra.username", "cassandra");
		config.put("cassandra.password", "cassandra");

		CassandraWriterBolt cassandraBolt = new CassandraWriterBolt(
				async(simpleQuery(
						"INSERT INTO " + 
						props.getValue("cassandra.table") + 
						" (word,count) VALUES (?, ?);")
					.with(fields("word", "count"))));

		String url = "mongodb://"
				+ props.getValue("mongo.server")
				+ "/" + props.getValue("mongo.database");
		String collectionName = props.getValue("mongo.collection");
		MongoMapper mapper = new SimpleMongoMapper().withFields("word", "count");
		MongoInsertBolt mongoBolt = new MongoInsertBolt(url, collectionName, mapper);

		KafkaSpoutConfig<String, String> spoutConf = KafkaSpoutConfig
				.builder(props.getValue("kafka.server"), 
						props.getValue("kafka.topic")
						).build();
		
		System.out.println("Connecting to Kafka: " + props.getValue("kafka.server") + ":" + props.getValue("kafka.topic"));

		final TopologyBuilder tp = new TopologyBuilder();
		
		config.setNumWorkers(1);

		tp.setSpout("kafka_spout", new KafkaSpout<>(spoutConf), 1);

		tp.setBolt("split_sentence_bolt", splitSentenceBolt).shuffleGrouping("kafka_spout");
		tp.setBolt("word_count_bolt", wordCountBolt).shuffleGrouping("split_sentence_bolt");
		tp.setBolt("report_bolt", reportBolt).shuffleGrouping("word_count_bolt");
		tp.setBolt("cassandra_bolt", cassandraBolt).shuffleGrouping("word_count_bolt");
		tp.setBolt("mongo_bolt", mongoBolt).shuffleGrouping("word_count_bolt");
		
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("word-count-topology", config, tp.createTopology());

		// cluster.killTopology("word-count-topology");
		// cluster.shutdown();

	}

	public class SplitSentenceBolt extends BaseRichBolt {

		private OutputCollector collector;

		@SuppressWarnings("rawtypes")
		@Override
		public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
			this.collector = collector;
		}

		@Override
		public void execute(Tuple input) {
			String sentence = input.getString(4);
			String words[] = Utility.cleanse(sentence);
			for (String w : words) {
				collector.emit(new Values(w));
			}

		}

		@Override
		public void declareOutputFields(OutputFieldsDeclarer declarer) {
			declarer.declare(new Fields("word"));
		}

	}

	public class WordCountBolt extends BaseRichBolt {

		Map<String, Integer> counts;
		private OutputCollector collector;

		@SuppressWarnings("rawtypes")
		@Override
		public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
			this.counts = new HashMap<String, Integer>();
			this.collector = collector;
		}

		@Override
		public void execute(Tuple input) {
			String word = input.getString(0);
			Integer count = counts.get(word);
			if (count == null)
				count = 0;
			count++;
			counts.put(word, count);
			collector.emit(new Values(word, count));
		}

		@Override
		public void declareOutputFields(OutputFieldsDeclarer declarer) {
			declarer.declare(new Fields("word", "count"));
		}

	}

	public class ReportBolt extends BaseRichBolt {

		@SuppressWarnings("rawtypes")
		@Override
		public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {

		}

		@Override
		public void execute(Tuple input) {
			System.out.println(input.getValues());
		}

		@Override
		public void declareOutputFields(OutputFieldsDeclarer declarer) {

		}

	}

}
