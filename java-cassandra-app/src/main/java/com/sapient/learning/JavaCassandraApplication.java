package com.sapient.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@EnableCircuitBreaker
@SpringBootApplication
public class JavaCassandraApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaCassandraApplication.class, args);
	}
}
