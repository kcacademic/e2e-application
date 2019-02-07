package com.sapient.learning.twitter;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

@Component
public class TwitterKafkaProducer implements CommandLineRunner{

	private final String topic = "twitter";

	private Producer<String, String> producer;

	private BlockingQueue<String> queue;

	private Client client;
	
	@PostConstruct
	public void init() throws ExecutionException, IOException, InterruptedException {	
		initKafka();
		initTwitter();
	}

	private void initKafka() {
		Properties kafkaProps = new Properties();
		kafkaProps.put("bootstrap.servers", "host.docker.internal:9092");
		kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		kafkaProps.put("acks", "1");
		kafkaProps.put("retries", "1");
		kafkaProps.put("linger.ms", 5);
		producer = new KafkaProducer<>(kafkaProps);
	}

	private void initTwitter() {

		queue = new LinkedBlockingQueue<String>(10000);
		StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
		// add some track terms
		endpoint.trackTerms(Lists.newArrayList("Brexit"));

		Authentication auth = null;
		// auth = new OAuth1(consumerKey, consumerSecret, token, secret);
		auth = new OAuth1("zY022kxzlsjbGAPYcUHNJWIyz", 
				"h7jA5bx6BGCPOlgn1tP8XRucR6EuBrREMj3iKqNhQgrkmTe1oG",
				"56278192-NSzus4wTJslMmV5QcAVTQeLdcS3fxrwH1BjBjFNtg", 
				"5cWOPFHl9wHbQQj58eJ1GdYWxMvwvvwi8guXIbD78dmKE");
		// auth = new BasicAuth("username", "password");

		// Create a new BasicClient. By default gzip is enabled.
		client = new ClientBuilder()
				.hosts(Constants.STREAM_HOST)
				.endpoint(endpoint).authentication(auth)
				.processor(new StringDelimitedProcessor(queue))
				.build();

		// Establish a connection
		client.connect();
	}

	@Override
	public void run(String... args) throws Exception {
		
		Logger.getLogger("org").setLevel(Level.OFF);
		
		// Do whatever needs to be done with messages
		for (int msgRead = 0; msgRead < 1000; msgRead++) {
			ProducerRecord<String, String> message = null;
			String tweet = queue.take();
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(tweet);
			JsonNode idNode = rootNode.path("text");
			System.out.println(idNode.asText());
			message = new ProducerRecord<String, String>(topic, idNode.asText());
			producer.send(message).get();
		}
		producer.close();
		client.stop();
	}
}