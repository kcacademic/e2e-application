package com.sapient.learning.producer

import org.springframework.boot.CommandLineRunner

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.common.collect.Lists
import com.twitter.hbc.ClientBuilder
import com.twitter.hbc.core.Client
import com.twitter.hbc.core.Constants
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint
import com.twitter.hbc.core.processor.StringDelimitedProcessor
import com.twitter.hbc.httpclient.auth.Authentication
import com.twitter.hbc.httpclient.auth.OAuth1
import java.util.concurrent.BlockingQueue
import java.util.Properties
import java.util.concurrent.LinkedBlockingQueue
import javax.annotation.PostConstruct

class TwitterKafkaProducer : CommandLineRunner {

	@Value("\${kafka.topic}")
	lateinit var topic: String

	@Value("\${kafka.brokerList}")
	lateinit var brokerList: String

	lateinit var producer: Producer<String, String>

	val queue: BlockingQueue<String> = LinkedBlockingQueue<String>(10000);

	lateinit var client: Client

	@PostConstruct
	fun init() {
		initKafka();
		initTwitter();
	}

	fun initKafka() {
		val kafkaProps: Properties = Properties();
		kafkaProps.put("bootstrap.servers", brokerList);
		kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		kafkaProps.put("acks", "1");
		kafkaProps.put("retries", "1");
		kafkaProps.put("linger.ms", 5);
		producer = KafkaProducer<String, String>(kafkaProps);
	}

	fun initTwitter() {

		val endpoint: StatusesFilterEndpoint = StatusesFilterEndpoint();

		// add some track terms
		endpoint.trackTerms(Lists.newArrayList("Brexit"));

		val auth: Authentication = OAuth1("zY022kxzlsjbGAPYcUHNJWIyz",
				"h7jA5bx6BGCPOlgn1tP8XRucR6EuBrREMj3iKqNhQgrkmTe1oG",
				"56278192-NSzus4wTJslMmV5QcAVTQeLdcS3fxrwH1BjBjFNtg",
				"5cWOPFHl9wHbQQj58eJ1GdYWxMvwvvwi8guXIbD78dmKE");
		// OAuth1(consumerKey, consumerSecret, token, secret);
		// BasicAuth("username", "password");


		// Create a new BasicClient. By default gzip is enabled.
		client = ClientBuilder()
				.hosts(Constants.STREAM_HOST)
				.endpoint(endpoint).authentication(auth)
				.processor(StringDelimitedProcessor(queue))
				.build();

		// Establish a connection
		client.connect();
	}

	override fun run(vararg args: String) {

		Logger.getLogger("org").setLevel(Level.OFF);

		// Do whatever needs to be done with messages
		for (msgRead in 0..1000) {
			val tweet: String = queue.take()
			val objectMapper: ObjectMapper = ObjectMapper()
			val rootNode: JsonNode = objectMapper.readTree (tweet)
			val idNode: JsonNode = rootNode.path ("text")
			System.out.println(idNode.asText());
			var message = ProducerRecord < String, String>(topic, idNode.asText());
			producer.send(message).get();
		}
		producer.close();
		client.stop();

	}
}