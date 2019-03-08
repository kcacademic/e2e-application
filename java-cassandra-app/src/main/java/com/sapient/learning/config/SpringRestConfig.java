package com.sapient.learning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringRestConfig {
	
	@Bean
	RestTemplate restTemplate() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		return restTemplate;
		
	}

}
