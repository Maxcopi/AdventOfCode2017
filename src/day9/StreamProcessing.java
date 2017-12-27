package day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StreamProcessing {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner fileHandler = new Scanner(new File("src/day9/input"));
		
		StringBuilder input = new StringBuilder(fileHandler.nextLine());
		

	    removeAllAfterExclamation(input);
	    
	    int garbageRemoved = removeAllGarbage(input);
	    
	    int points = calculatePoints(input.toString());
	    
	    System.out.println(input.toString());
	    System.out.println(points);
	    System.out.println(garbageRemoved);
	}
	
	public static void removeAllAfterExclamation(StringBuilder input) {
		for(int i=0; i < input.length(); i++) {
			if(input.charAt(i)=='!') {
				input.deleteCharAt(i+1);
				input.deleteCharAt(i);
				i--;
			}
		}
	}
	
	public static int removeAllGarbage(StringBuilder input) {
		boolean inGarbage = false;
		int garbageCount = 0;
		boolean counted = false;
		
		for(int i=0; i < input.length(); i++) {
			if(input.charAt(i)=='<') {
				inGarbage = true;
				counted = true;
			} else {
				if(input.charAt(i) == '>') {
					inGarbage = false;
					garbageCount--;
					input.deleteCharAt(i);
				} else {
					
				}
			}
			if(inGarbage == true) {
				input.deleteCharAt(i);
				garbageCount++;
				i--;
			}
			
		}
		
		return garbageCount;
	}
	
	public static int calculatePoints(String input) {
		int points = 0;
		int depth = 0;
		
		for(int i=0; i < input.length(); i++) {
			if(input.charAt(i) == '{') {
				depth++;
			} else {
				if(input.charAt(i) == '}') {
					points += depth;
					if(depth > 0) {
						depth--;
					}
				}
			}
		}
		
		return points;
		
	}

}
