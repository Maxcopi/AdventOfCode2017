package day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Hexes {

	public static void main(String[] args) throws FileNotFoundException {
		int row = 0;
		int column = 0;
		int distance = 0;
		
		
		Scanner fileHandler = new Scanner(new File("src/day11/input"));
		Location coords = new Location();
		
		StringBuilder input = new StringBuilder();
		
		while(fileHandler.hasNextLine()) {
			input.append(fileHandler.nextLine());
		}
		
		String[] directions = input.toString().split(",");
		
		for(String direction : directions) {
			
			coords.move(direction);
			coords.getDistance();
			
		}
		
		System.out.println(coords.getDistance());
		System.out.println(coords.getMaxDistance());
	}
	

}
