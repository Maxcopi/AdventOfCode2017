package day3;

import java.util.ArrayList;

public class Part2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[][] matrix = new int[558][558];
		int[] adjacentValues = new int[8];
		int sumOfAdjacent = 0;
		String direction = "Right";
		int stepSize = 0;
		int y = (int)Math.round(matrix.length / 2.0);
		int x = (int)Math.round(matrix[y].length / 2.0);
		matrix[y][x]=1;
		int input = 312051;
		boolean directionShifted = false;
		int stepCount;
		
		while(sumOfAdjacent < input) {
			stepSize++;
			for(int i=0; i < 2; i++) {
				stepCount = stepSize;
				while(stepCount > 0) {
					switch(direction) {
					case "Right":
							x += 1;
							break;
					case "Up":
							y -= 1;
							break;
					case "Left":
							x -= 1;
							break;
					case "Down":
							y +=1;
							break;
					}
					
					System.out.println(y + " " + x);
					
					fillAdjacentValues(matrix, x, y, adjacentValues);
				
					sumOfAdjacent = calculateSumOfAdjacentValues(adjacentValues);
					
					matrix[y][x] = sumOfAdjacent;
					
					System.out.println(sumOfAdjacent);
					
					stepCount--;
				}
				
				direction = changeDirection(direction);
			}
			
		}
	}
	
	public static void fillAdjacentValues(int[][] matrix, int x, int y, int[]adjacentValues) {
		int index = 0;
		
		for(int i=y-1; i <= y+1; i++) {
			for(int j=x-1; j <= x+1; j++) {
				if(!(i == y & j == x)) {
					adjacentValues[index] = matrix[i][j];
					index++;
				}
			}
		}
	}
	
	public static int calculateSumOfAdjacentValues(int[] adjacentValues) {
		int sum = 0;
		
		for(int i=0; i < adjacentValues.length; i++) {
			sum += adjacentValues[i];
		}
		
		return sum;
	}
	
	public static String changeDirection(String currentDirection) {
		switch(currentDirection) {
		case "Right":
				return "Up";
		case "Up":
				return "Left";
		case "Left":
				return "Down";
		case "Down":
				return "Right";
		default: return "";
		}
	}
}
