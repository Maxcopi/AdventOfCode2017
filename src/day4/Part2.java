package day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Part2 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new File("src/day4/phrases.txt"));
		ArrayList<String> phrases = new ArrayList<String>();
		while (fileReader.hasNext()){
		    phrases.add(fileReader.nextLine());
		}
		fileReader.close();
		
		int validPhraseCount = 0;
		String words[];
		
		char sortedCurrent[];
		char sortedCompare[];
		boolean valid;
		
		for(String phrase : phrases) {
			words = splitToArray(phrase);
			valid = true;
			for(int i=0; i < words.length; i++) {
				sortedCurrent = getSortedCharArray(words[i]);
				for(int j=0; j < words.length; j++) {
					if(i != j) {
						sortedCompare = getSortedCharArray(words[j]);
						if(sortedCompare.length == sortedCurrent.length) {
							if(Arrays.equals(sortedCurrent, sortedCompare)) {
								valid = false;
							}
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
	
	public static char[] getSortedCharArray(String word) {
		char[] sorted = new char[word.length()];
		
		for(int i=0; i < word.length(); i++) {
			sorted[i] = word.charAt(i);
		}
		Arrays.sort(sorted);
		
		return sorted;
	}

}
