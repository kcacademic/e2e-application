package com.sapient.learning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class Utility {

	public static void main(String[] args) {
		
		String str = "RT @Femi_Sorry: I am sick to death of privileged politicians saying that they personally would be happy with No Deal Brexit.";
		
        String urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern p = Pattern.compile(urlPattern,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        int i = 0;
        while (m.find()) {
            str = str.replaceAll(m.group(i),"").trim();
            i++;
        }
		
		str = str
				.replaceAll("@\\p{L}+", "")
				.replaceAll("[^a-zA-Z ]", "")
				.replaceAll("\\b\\w{1,4}\\b","")
				.toLowerCase();
		
		System.out.println(str);
		
		String[] words = str.split("\\s+");
		
		List<String> list = new ArrayList<String>(Arrays.asList(words));
		list.removeIf(StringUtils::isBlank);
		words = list.toArray(new String[0]);
		
		System.out.println(words.length);
		System.out.println(Arrays.toString(words));
		
	}

}
