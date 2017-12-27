package day18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;
public class Duet {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner filehand = new Scanner(new File("src/day18/input"));
		ArrayList<String> instructions = new ArrayList<>();
		String command;
		char registerName;
		char target;
		String instruction[];
		ArrayList<Register> registers = new ArrayList<>();
		int frequency;
		Register currentRegister;
		Register targetRegister;
		int value;
		int value2;
		boolean first = true;
		
		while(filehand.hasNext()) {
			instructions.add(filehand.nextLine());
		}

		createRegisters(instructions, registers);
		
		for(int i=0; i < instructions.size(); i++) {
			instruction = instructions.get(i).split(" ");
			command = instruction[0];
			System.out.println(i);
			
			switch(command) {
			case "snd":
				if(isARegister(instruction[1].charAt(0))) {
					currentRegister = getRegister(registers, instruction[1].charAt(0));
					frequency = currentRegister.getValue();
				} else {
					frequency = Integer.parseInt(instruction[1]);
				}
				Register.setFrequencyLastSound(frequency);
				break;
			case "set":
				registerName = instruction[1].charAt(0);
				if(isARegister(instruction[2].charAt(0))) {
					currentRegister = getRegister(registers, instruction[2].charAt(0));
					value = currentRegister.getValue();
				} else {
					value = Integer.parseInt(instruction[2]);
				}
				targetRegister = getRegister(registers, registerName);
				targetRegister.setValue(value);
				break;
			case "add":
				registerName = instruction[1].charAt(0);
				if(isARegister(instruction[2].charAt(0))) {
					currentRegister = getRegister(registers, instruction[2].charAt(0));
					value = currentRegister.getValue();
				} else {
					value = Integer.parseInt(instruction[2]);
				}
				targetRegister = getRegister(registers, registerName);
				targetRegister.addValue(value);
				break;
			case "mul":
				registerName = instruction[1].charAt(0);
				if(isARegister(instruction[2].charAt(0))) {
					currentRegister = getRegister(registers, instruction[2].charAt(0));
					value = currentRegister.getValue();
				} else {
					value = Integer.parseInt(instruction[2]);
				}
				targetRegister = getRegister(registers, registerName);
				targetRegister.multiplyValue(value);
				break;
			case "mod":
				registerName = instruction[1].charAt(0);
				if(isARegister(instruction[2].charAt(0))) {
					currentRegister = getRegister(registers, instruction[2].charAt(0));
					value = currentRegister.getValue();
				} else {
					value = Integer.parseInt(instruction[2]);
				}
				targetRegister = getRegister(registers, registerName);
				targetRegister.modValue(value);
				break;
			case "rcv":
				currentRegister = getRegister(registers, instruction[1].charAt(0));
				if(currentRegister.getValue() != 0) {
					if(first) {
						System.out.println(Register.getFrequencyLastSound());
						first = false;
					}
				}
				break;
			case "jgz":
				if(isARegister(instruction[1].charAt(0))) {
					targetRegister = getRegister(registers, instruction[1].charAt(0));
					value2 = targetRegister.getValue();
				} else {
					value2 = Integer.parseInt(instruction[1]);
				}
				if(isARegister(instruction[2].charAt(0))) {
					currentRegister = getRegister(registers, instruction[2].charAt(0));
					value = currentRegister.getValue();
				} else {
					value = Integer.parseInt(instruction[2]);
				}
				
				if(value2 > 0) {
					i += value;
					i--;
				}
				break;
			}
		}
		
		for(Register register : registers) {
			System.out.println(register.getName());
		}
		
	}
	
	public static boolean isRegisterInArrayList(ArrayList<Register> registers, char name) {
		for(Register register : registers) {
			if(register.getName() == name) {
				return true;
			}
		}
		return false;
	}
	
	public static Register getRegister(ArrayList<Register> registers, char registerName) {
		for(Register register : registers) {
			if(register.getName() == registerName) {
				return register;
			}
		}
		return registers.get(0);
	}
	
	public static boolean isARegister(char input) {
		if(input >= 'a' && input <= 'z') {
			return true;
		}
		return false;
	}
	
	public static void createRegisters(ArrayList<String> instructions, ArrayList<Register> registers) {
		for(int i=0; i < instructions.size(); i++) {
			char potentialRegister = instructions.get(i).charAt(instructions.get(i).indexOf(" ")+1);
			if(isARegister(potentialRegister)) {
				if(isRegisterInArrayList(registers, potentialRegister) == false) {
					registers.add(new Register(potentialRegister));
				}
			}
		}
	}

}
