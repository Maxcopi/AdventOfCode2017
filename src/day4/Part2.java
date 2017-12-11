package day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Part2 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new File("src/day4/test.txt"));
		ArrayList<String> phrases = new ArrayList<String>();
		while (fileReader.hasNext()){
		    phrases.add(fileReader.nextLine());
		}
		fileReader.close();
		
		int validPhraseCount = 0;
		String words[];
		
		for(String phrase : phrases) {
			words = splitToArray(phrase);
			
			char[] karaktersCurrentWord;
			boolean valid = false;
			for(int i=0; i < words.length; i++) {
				karaktersCurrentWord = new char[words[i].length()];
				for(int charPos=0; charPos < words[i].length(); charPos++) {
					karaktersCurrentWord[charPos] = words[i].charAt(charPos);
				}
				char[] karaktersCompareWord;
				for(int j=0; j < words.length; j++) {
					if(i != j) {
						karaktersCompareWord = new char[words[j].length()];
						for(int charPosComp=0; charPosComp < karaktersCompareWord.length; charPosComp++) {
							karaktersCurrentWord[charPosComp] = words[j].charAt(charPosComp);
						}
						Arrays.sort(karaktersCurrentWord);
						Arrays.sort(karaktersCompareWord);
						if(karaktersCurrentWord.length == karaktersCompareWord.length) {
							if(Arrays.equals(karaktersCurrentWord, karaktersCompareWord)) {
								valid = true;
							}
						}
						
					}
				}
				
				
				
				
				if(valid) {
					validPhraseCount++;
				}
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
