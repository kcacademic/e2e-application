package com.sapient.learning;

import static org.apache.storm.cassandra.DynamicStatementBuilder.async;
import static org.apache.storm.cassandra.DynamicStatementBuilder.fields;
import static org.apache.storm.cassandra.DynamicStatementBuilder.simpleQuery;

import java.io.Serializable;
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
import org.apache.storm.tuple.Tuple;

@SuppressWarnings("serial")
public class WordDataStream implements Serializable{

	public static MyProperties props = new MyProperties();

	@SuppressWarnings("unused")
	public static void main(String[] args) throws InterruptedException {
		
		WordDataStream wordDataStream = new WordDataStream();
		
		SplitSentenceBolt splitSentenceBolt = wordDataStream.new SplitSentenceBolt();
		
		WordCountBolt wordCountBolt = wordDataStream.new WordCountBolt();
		
		ReportBolt reportBolt = wordDataStream.new ReportBolt();

		CassandraWriterBolt cassandraBolt = new CassandraWriterBolt(
				async(simpleQuery("INSERT INTO album (title,year,performer,genre,tracks) VALUES (?, ?, ?, ?, ?);")
						.with(fields("title", "year", "performer", "genre", "tracks"))));
		
		String url = "mongodb://127.0.0.1:27017/test";
		String collectionName = "wordcount";
		MongoMapper mapper = new SimpleMongoMapper()
		        .withFields("word", "count");
		MongoInsertBolt mongoBolt = new MongoInsertBolt(url, collectionName, mapper);
		
		KafkaSpoutConfig<String, String> spoutConf =  KafkaSpoutConfig
				.builder("127.0.0.1:9092", "twitter").build();	
		
		final TopologyBuilder tp = new TopologyBuilder();
		Config config = new Config();
		config.setNumWorkers(1);
		
		tp.setSpout("kafka_spout", new KafkaSpout<>(spoutConf), 1);
		
		//tp.setBolt("split_sentence_bolt", splitSentenceBolt);
		//tp.setBolt("word_count_bolt", wordCountBolt);
		//tp.setBolt("cassandra_bolt", cassandraBolt).shuffleGrouping("kafka_spout");
		//tp.setBolt("mongo_bolt", mongoBolt).shuffleGrouping("kafka_spout");
		
		tp.setBolt("report_bolt", reportBolt).shuffleGrouping("kafka_spout");
		
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("word-count-topology", config, tp.createTopology());
		
		//cluster.killTopology("word-count-topology");
		//cluster.shutdown();
		
	}
	
	public class SplitSentenceBolt extends BaseRichBolt{

		@SuppressWarnings("rawtypes")
		@Override
		public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void execute(Tuple input) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void declareOutputFields(OutputFieldsDeclarer declarer) {
			// TODO Auto-generated method stub
			
		}

	}
	
	public class WordCountBolt extends BaseRichBolt{

		@SuppressWarnings("rawtypes")
		@Override
		public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void execute(Tuple input) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void declareOutputFields(OutputFieldsDeclarer declarer) {
			// TODO Auto-generated method stub
			
		}

	}
	
	public class ReportBolt extends BaseRichBolt {

		@SuppressWarnings("rawtypes")
		@Override
		public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void execute(Tuple input) {
			// TODO Auto-generated method stub
			System.out.println(input.getValues());
		}

		@Override
		public void declareOutputFields(OutputFieldsDeclarer declarer) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
