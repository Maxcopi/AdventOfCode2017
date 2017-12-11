package day1;

import java.util.Scanner;

public class InverseCaptcha {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		String input = keyboard.next();
		
		int currentNumber;
		int lastNumber = Character.getNumericValue(input.charAt(input.length()-1));
		int sum = 0;
		
		for(int i=0; i < input.length(); i++) {
			currentNumber = Character.getNumericValue(input.charAt(i));
			if(currentNumber == lastNumber) {
				sum += lastNumber;
			}
			
			lastNumber = currentNumber;
		}
		
		System.out.println(sum);
		
	}

}
