package day1;

import java.util.Scanner;

public class Part2 {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		String input = keyboard.next();
		
		String part1 = input.substring(0, input.length()/2);
		String part2 = input.substring((input.length() / 2), input.length());
		int sum = 0;
		
		for(int i=0; i < part1.length(); i++) {
			int numberPart1 = Character.getNumericValue(part1.charAt(i));
			int numberPart2 = Character.getNumericValue(part2.charAt(i));
			if(numberPart1 == numberPart2) {
				sum += numberPart1 * 2;
			}
		}
		
		System.out.println(sum);
		
	}

}
