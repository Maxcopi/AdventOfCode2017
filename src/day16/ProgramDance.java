package day16;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProgramDance {

	public static void main(String[] args) throws FileNotFoundException {
		char[] programNames = new char[16];
		int spinValue;
		int position1;
		int position2;
		char program1;
		char program2;
		int counter = 0;
		long totalCounter = 0;
		ArrayList<char[]> cacheProgramNames = new ArrayList<>();
		ArrayList<String> cacheInstructions = new ArrayList<>();
		
		for(char name = 'a'; name <= 'p'; name++) {
			programNames[Character.getNumericValue(name)-10] = name;
		}
		
		Scanner filehandler = new Scanner(new File("src/day16/input"));
		
		StringBuilder input = new StringBuilder();
		
		while(filehandler.hasNextLine()) {
			input.append(filehandler.nextLine());
		}
		
		String[] instructions = input.toString().split(",");
		
		for(long i=0; i < 1000000000; i++) {
			for(String instruction : instructions) {
				if(cacheInstructions.contains(instruction)) {
					programNames = cacheProgramNames.get(cacheInstructions.indexOf(instruction));
					continue;
				} else {
					if(instruction.startsWith("s")) {
						spinValue = Integer.parseInt(instruction.substring(instruction.indexOf("s")+1));
						spin(programNames, spinValue);
					} else {
						if(instruction.startsWith("x")) {
							position1 = Integer.parseInt(instruction.substring(1, instruction.indexOf("/")));
							position2 = Integer.parseInt(instruction.substring(instruction.indexOf("/") + 1));
							exchange(programNames, position1, position2);
						} else {
								program1 = instruction.charAt(1);
								program2 = instruction.charAt(3);
								partner(programNames, program1, program2);
							}
						}
						if(!cacheProgramNames.contains(programNames)) {
							cacheProgramNames.add(programNames);
							cacheInstructions.add(instruction);
						}
					}
			}
		}
		
		for(char name : programNames) {
			System.out.print(name);
		}
		
		filehandler.close();
	}

	public static void spin(char[] programs, int spinValue) {
		ArrayList<Character> newPrograms = new ArrayList<>();
		
		for(int i=programs.length - spinValue; i < programs.length; i++) {
			newPrograms.add(programs[i]);
		}
		
		for(int i=0; i < programs.length - spinValue; i++) {
			newPrograms.add(programs[i]);
		}
		
		for(int i=0; i < programs.length; i++) {
			programs[i] = newPrograms.get(i);
		}
		
	}
	
	public static void exchange(char[] programs, int position1, int position2) {
		char program1 = programs[position1];
		char program2 = programs[position2];
		
		programs[position1] = program2;
		programs[position2] = program1;
	}
	
	public static void partner(char[] programs, char program1, char program2) {
		int position1 = 0;
		int position2 = 0;
		
		for(int i=0; i < programs.length; i++) {
			if(programs[i] == program1) {
				position1 = i;
			}
			if(programs[i] == program2) {
				position2 = i;
			}
		}
		
		programs[position1] = program2;
		programs[position2] = program1;
	}
}


