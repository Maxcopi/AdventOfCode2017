package day4;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PassPhrase {
	public static void main(String[] args) throws FileNotFoundException {
		
		
		Scanner fileReader = new Scanner(new File("src/day4/phrases.txt"));
		ArrayList<String> phrases = new ArrayList<String>();
		while (fileReader.hasNext()){
		    phrases.add(fileReader.nextLine());
		}
		fileReader.close();
		
		int validPhraseCount = 0;
		String words[];
		
		for(String phrase : phrases) {
			words = splitToArray(phrase);
			
			boolean valid = true;
			
			for(int i=0; i < words.length; i++) {
				String currentWord = words[i];
				for(int j=0; j < words.length; j++) {
					if(j == i) {
						continue;
					} else {
						if(currentWord.equals(words[j])) {
							valid = false;
						}
					}
				}
			}
			
			if(valid) {
				validPhraseCount++;
			}
			
			
		}
		
		System.out.println(validPhraseCount);
	}
	
	public static String[] splitToArray(String phrase) {
		String[] words;
		int beginWord = 0;
		int spacePos = 0;
		int wordCount = 0;
		
		//getAmountOfWords
		while(spacePos > -1) {
			spacePos = phrase.indexOf(" ", beginWord);
			beginWord = spacePos + 1;
			wordCount++;
		}
		
		words = new String[wordCount];
		
		beginWord = 0;
		spacePos = 0;
		
		for(int i=0; i < wordCount; i++) {
			spacePos = phrase.indexOf(" ", beginWord);
			if(spacePos == -1) {
				spacePos = phrase.length();
			}
			
			words[i] = phrase.substring(beginWord, spacePos);
			beginWord = spacePos + 1;
		}
		while(spacePos > -1) {
			spacePos = phrase.indexOf(" ", beginWord);
			beginWord = spacePos + 1;
			wordCount++;
		}
		
		return words;
	}
}

