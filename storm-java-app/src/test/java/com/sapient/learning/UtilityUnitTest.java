package com.sapient.learning;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UtilityUnitTest {
	
	@Test
	public void testUtility() {
		
		String str = "RT @Femi_Sorry: I am sick to death of privileged politicians saying that they personally would be happy with No Deal Brexit.";
		
		String[] words = Utility.cleanse(str);
		
		System.out.println(words.length);
		
		assertEquals(9, words.length);;
		
	}

}
