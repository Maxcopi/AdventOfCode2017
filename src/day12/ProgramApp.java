package day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProgramApp {

	public static void main(String[] args) throws FileNotFoundException {
		
		ArrayList<Program> programs = new ArrayList();
		
		Scanner fileHandler = new Scanner(new File("src/day12/input"));
		String[] linkedPrograms;
		int programCount = 0;
		Program currentProgram;
		int countZero = 0;
		
		while(fileHandler.hasNextLine()) {
			String line = fileHandler.nextLine();
			
			line = line.replaceAll(" ", "");
			
			linkedPrograms = line.substring(line.indexOf('>')+1, line.length()).split(",");
			
			currentProgram = new Program(linkedPrograms);
			
			if(programCount == 0) {
				currentProgram.setZero(true);
			}
			
			programs.add(currentProgram);
			
			programCount++;
		}
		
		String links[];
		

		for(int i=0; i < programs.size(); i++) {
			if(programs.get(i).getZero()) {
				links = programs.get(i).getLinks();
				for(String link : links) {
					if(!link.isEmpty()) {
							programs.get(Integer.parseInt(link)).setZero(true);
					}
				}
			}
		}
		
		
		for(int i=0; i < programs.size(); i++) {
			System.out.println(i + " " + programs.get(i).getZero());
		}
		
		for(int i=0; i < programs.size(); i++) {
			if(programs.get(i).getZero()) {
				countZero++;
			}
		}
		
		System.out.println(countZero);
		
	}

}
