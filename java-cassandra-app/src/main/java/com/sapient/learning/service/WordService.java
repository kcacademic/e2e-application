package com.sapient.learning.service;

import static java.util.Collections.singletonList;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sapient.learning.controller.WordController;
import com.sapient.learning.model.Word;
import com.sapient.learning.repo.WordRepository;

@Service
public class WordService {

	private static final Logger logger = LoggerFactory.getLogger(WordController.class);

	@Value("${prediction.url:http://localhost:5000/predict}")
	String predictUrl;

	@Autowired
	WordRepository wordRepository;

	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getDefaultWords")
	public List<Word> getAllWords() {

		logger.info("Get all Words...");

		List<Word> words = wordRepository.findAll();
		words = words.stream().sorted(Comparator.comparing(Word::getCount).reversed()).limit(25)
				.collect(Collectors.toList());
		return words;

	}

	@HystrixCommand(fallbackMethod = "getDefaultSentiment")
	public String getSentiment() {

		logger.info("Get sentiment...");

		return restTemplate.postForEntity(predictUrl, "", String.class).getBody();

	}
	
	List<Word> getDefaultWords() {
		
		Word word = new Word();
		word.setWord("Kumar Chandrakant");
		word.setCount(25);
		List<Word> allWords = singletonList(word);
		
		return allWords;
		
	}

	String getDefaultSentiment() {

		return "{\"sentiment\": \"Happy\"}";

	}

}
