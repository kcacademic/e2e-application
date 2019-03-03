package com.sapient.learning.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import com.sapient.learning.repo.WordRepository
import com.sapient.learning.model.Word

@CrossOrigin(origins = ["http://localhost"])
@RestController
@RequestMapping("/api")
class WordController {

	@Autowired
	lateinit var wordRepository: WordRepository

	@GetMapping("/words")
	fun getAllWords(): List<Word> {

		System.out.println("Get all Words...");

		var words = wordRepository.findAll();
		
		words = words.sortedWith(compareByDescending(Word::count)).take(25)

		return words;
	}
}