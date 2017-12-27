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
		String currentId;
		int groupCount = 0;
		ArrayList<ArrayList<Program>> groupsCounted = new ArrayList<>();
		
		while(fileHandler.hasNextLine()) {
			String line = fileHandler.nextLine();
			
			line = line.replaceAll(" ", "");
			
			linkedPrograms = line.substring(line.indexOf('>')+1, line.length()).split(",");
			currentId = line.substring(0, line.indexOf('<'));
			currentProgram = new Program(linkedPrograms, currentId);
			
			if(programCount == 0) {
				currentProgram.setZero(true);
			}
			
			programs.add(currentProgram);
			
			programCount++;
		}
		
		String links[];
		
		
		for(int j=0; j < 15; j++) {
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
		}
		
		for(int i=0; i < programs.size(); i++) {
			System.out.println(i + " " + programs.get(i).getZero());
			programs.get(i).setLinkedPrograms(programs);
		}
		
		for(Program prog : programs) {
			prog.setLinkedProgramsOfLinks();
		}
		for(int i=0; i < programs.size(); i++) {
			if(programs.get(i).getZero()) {
				countZero++;
			}
		}
		
		ArrayList<Program> linkedProgramList;
		
		linkedProgramList = programs.get(0).getLinkedPrograms();
		
		for(Program prog : programs) {
			prog.setLinkedProgramsOfLinks();
		}
		
		
		ArrayList<Program> currentList;
		for(Program prog: programs) {
			currentList = prog.getLinkedPrograms();
			if(groupsCounted.contains(currentList) == false) {
				groupCount++;
				groupsCounted.add(currentList);
			}
		}
		
		System.out.println(countZero);
		System.out.println(groupCount);
	}

}
