package com.sapient.learning.controller;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.learning.model.Word;
import com.sapient.learning.repo.WordRepository;


@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/api")
public class WordController {
	
	private static final Logger logger = LoggerFactory.getLogger(WordController.class);

	@Autowired
	WordRepository wordRepository;

	@Async("threadPoolTaskExecutor")
	@GetMapping("/words")
	public CompletableFuture<List<Word>> getAllWords() {
		logger.info("Get all Words...");
		
		List<Word> words = wordRepository.findAll();
		
		words = words.stream()
			.sorted(Comparator.comparing(Word::getCount).reversed())
			.limit(25)
			.collect(Collectors.toList());

		return CompletableFuture.completedFuture(words);
	}
	
}
