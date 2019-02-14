package com.sapient.learning.unit

import com.sapient.learning.controller.WordController
import com.sapient.learning.model.Word
import com.sapient.learning.repo.WordRepository
import org.hamcrest.Matchers.equalTo
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@WebMvcTest(WordController::class)
class WordControllerUnitTest {

	@Autowired
	lateinit var mvc: MockMvc

	@MockBean
	lateinit var wordRepository: WordRepository

	@Test
	fun helloWorldReturnsPersonalizedMessage() {

		val word: Word = Word("", 25);
		val allWords: List<Word> = listOf(word);

		given(wordRepository.findAll()).willReturn(allWords);

		mvc.perform(
				get("/api/words")
						.contentType(APPLICATION_JSON))
				.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize<Any>(1)))
		.andExpect(jsonPath("$[0].word", equalTo(word.word)))

	}
}