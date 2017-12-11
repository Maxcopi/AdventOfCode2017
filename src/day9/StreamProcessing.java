package day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StreamProcessing {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner fileHandler = new Scanner(new File("src/day9/input"));
		
		String input = fileHandler.nextLine();
		int groupDepth = 0;
		int garbageDepth = 0;
		int groupScore = 0;
		int garbageScore = 0;
		int groupsClosed = 0;
		
		boolean garbage = false;
		boolean skipNext = false;
		
		char currentChar;
		
	    for(int i=0; i < input.length(); i++) {
	    	currentChar = input.charAt(i);
	    	
	    	switch(currentChar) {
	    	case '{': groupDepth++;
	    		break;
	    	case '}': 
	    		if(groupDepth > 0) {
	    			if(skipNext == false) {
	    				groupScore += groupDepth;
	    			} else {
	    				skipNext = false;
	    			}
	    			
	    		}
	    		groupDepth--;
	    		garbageDepth=0;
	    		break;
	    	case '!' : 
	    		if(skipNext == true) {
	    			skipNext = false;
	    		} else {
	    			skipNext = true;
	    		}
	    		
	    		break;	
	    	case '<' : garbage=true;
	    		break;
	    	case '>' : 
	    		garbage = false;
	    		break;
	    	default: garbage = true;
	    	}
	    	System.out.print(currentChar);
	    }
	    System.out.println();
	    System.out.println("groupscore: " + groupScore);
    	System.out.println("garbagescore: " + garbageScore);
    	System.out.println(groupScore + garbageScore);
		
	}

}
