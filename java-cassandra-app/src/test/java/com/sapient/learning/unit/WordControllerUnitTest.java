package com.sapient.learning.unit;

import static java.util.Collections.singletonList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.sapient.learning.controller.WordController;
import com.sapient.learning.model.Word;
import com.sapient.learning.repo.WordRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(WordController.class)
public class WordControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	WordRepository wordRepository;

	@Test
	public void getAllWords() throws Exception {

		Word word = new Word();
		word.setWord("Kumar");
		word.setCount(25);
		List<Word> allWords = singletonList(word);

		given(wordRepository.findAll()).willReturn(allWords);

		MvcResult result = 
				mockMvc.perform(get("/api/words")
						.contentType(APPLICATION_JSON))
				.andReturn();

		mockMvc.perform(asyncDispatch(result))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$[0].word", is(word.getWord())));

	}

}
