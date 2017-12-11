package day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new File("src/day5/maze.txt"));
		int numbersCount=0;
		
		while (fileReader.hasNext()){
		    numbersCount++;
		    fileReader.nextLine();
		}
		
		int[] numbers = new int[numbersCount];
		
		Scanner fileReader2 = new Scanner(new File("src/day5/maze.txt"));
		
		int count =0;
		
		while (fileReader2.hasNext()){
		    numbers[count]=Integer.parseInt(fileReader2.nextLine());
		    count++;
		}
		
		fileReader.close();
		fileReader2.close();
		
		//int numbers[] = {0,3,0,1,-3};
		int jump;
		int numberPos=0;
		int stepCount = 0;
		
		while(numberPos >= 0 && numberPos < numbers.length) {
			jump = numbers[numberPos];
			if(jump > 0) {
				numberPos += numbers[numberPos]++;
			} else {
				if(jump==0) {
					numbers[numberPos]++;
				} else {
					numberPos += numbers[numberPos]++;	
				}
			}
			stepCount++;
		}
		
		System.out.println(stepCount);
	}
	
	
	
}
