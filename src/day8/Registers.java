package day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Registers {
	
	/*
	 * Get file with just the buffernames by using the following command in bash:
	 * cat instructions.txt | cut -d" " -f1 | sort | uniq | grep [a-zA-Z] > buffers.txt
	 * Read the names into a list, keep values of the buffers in an array
	 */
	
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<String> registerNames = new ArrayList();
		Scanner fileReader = new Scanner(new File("src/day8/buffers.txt"));
		int greatestValue = 0;
		
		while(fileReader.hasNext()){
		    registerNames.add(fileReader.nextLine());
		}
		int[] registerValues = new int[registerNames.size()];
		
		fileReader = new Scanner(new File("src/day8/instructions.txt"));
		
		int greatest = 0;
		
		while(fileReader.hasNext()) {
			String line = fileReader.nextLine();
			
			String[] statement = line.split(" ");
			
			String register = statement[0];
			String operator = statement[1];
			int valueOperation = Integer.parseInt(statement[2]);
			String registerToCompare = statement[4];
			String comparer = statement[5];
			int numberCompare = Integer.parseInt(statement[6]);
			
			int registerValue = getRegisterValue(registerValues, registerNames, register);
			
			int registerPosition = getRegisterPosition(registerValues, registerNames, register);
			
			int registerValueToCompare = getRegisterValue(registerValues, registerNames, registerToCompare);
			
			/*
			 * Format would be:
			 * if(registerValue comparer numberCompare) {
			 	*	registerValue operator valueOperation;	
			 * }
			 */
			System.out.print(registerValue + " " + valueOperation + " ");
			
			registerValue = processStatement(registerValue, registerValueToCompare, numberCompare, comparer, operator, valueOperation);
			
			registerValues[registerPosition] = registerValue;
			
			for(int i=0; i < registerValues.length; i++) {
				if(greatest< registerValues[i]) {
					greatest = registerValues[i];
				}
			}
			
			System.out.println(operator + " " + registerValue + register);
			
		}
		System.out.println(greatest);
	}
	public static int getRegisterValue(int[] values, ArrayList<String> names, String register) {
		
		for(int i=0; i < names.size(); i++) {
			if(names.get(i).equals(register)) {
				return values[i];	
			}
		}
		
		return 0;
	}
	
	public static int getRegisterPosition(int[] values, ArrayList<String> names, String register) {
		int position = 0;
		
		for(int i=0; i < names.size(); i++) {
			if(names.get(i).equals(register)) {
				position = i;
			}
		}
		
		return position;
	}
	
	public static int calculateRegisterValue(String operator, int value, int registerValue) {

		if(operator.equals("dec")) {
			value *= -1;
		}
			
		registerValue += value;
		
		
		
		return registerValue;
	}
	
	public static int processStatement(int registerValue, int registerValueToCompare, int numberCompare, 
			String comparer, String operator, int valueOperation) {
		switch(comparer) {
		case "==": 
			if(registerValueToCompare == numberCompare) {
				return calculateRegisterValue(operator, valueOperation, registerValue);
			} else {
				return registerValue;
			}
		case ">=": 
			if(registerValueToCompare >= numberCompare) {
				return calculateRegisterValue(operator, valueOperation, registerValue);
			} else {
				return registerValue;
			}
		case "<=": 
			if(registerValueToCompare <= numberCompare) {
				return calculateRegisterValue(operator, valueOperation, registerValue);
			} else {
				return registerValue;
			}
		case "<": 
			if(registerValueToCompare < numberCompare) {
				return calculateRegisterValue(operator, valueOperation, registerValue);
			} else {
				return registerValue;
			}
		case ">": 
			if(registerValueToCompare > numberCompare) {
				return calculateRegisterValue(operator, valueOperation, registerValue);
			} else {
				return registerValue;
			}
		case "!=": 
			if(registerValueToCompare != numberCompare) {
				return calculateRegisterValue(operator, valueOperation, registerValue);
			} else {
				return registerValue;
			}
		}
		
		
		return registerValueToCompare;
	}

}
