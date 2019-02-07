package com.sapient.learning.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.learning.model.Word;
import com.sapient.learning.repo.WordRepository;

@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/api")
public class TagController {

	@Autowired
	WordRepository wordRepository;

	@GetMapping("/words")
	public List<Word> getAllWords() {
		System.out.println("Get all Words...");
		
		List<Word> words = wordRepository.findAll();
		
		words = words.stream()
			.sorted(Comparator.comparing(Word::getCount).reversed())
			.limit(25)
			.collect(Collectors.toList());

		return words;
	}
	
}
