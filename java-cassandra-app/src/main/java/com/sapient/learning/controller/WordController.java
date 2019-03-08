package com.sapient.learning.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.learning.model.Word;
import com.sapient.learning.service.WordService;


@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/api")
public class WordController {
	
	@Autowired
	WordService wordService;

	@Async("threadPoolTaskExecutor")
	@GetMapping("/words")
	public CompletableFuture<List<Word>> getAllWords() {
		
		List<Word> words = wordService.getAllWords();
		
		System.out.println(wordService.getSentiment());
		
		return CompletableFuture.completedFuture(words);
	}
	
}
