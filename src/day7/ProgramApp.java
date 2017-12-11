package day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProgramApp {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner fileReader = new Scanner(new File("src/day7/test.txt"));

		ArrayList<Program> programs = new ArrayList();
		
		while (fileReader.hasNext()){
		    String line = fileReader.nextLine();
		    
		    line = line.replace(" ", "");
		    String programName = line.substring(0, line.indexOf("("));
		    int weight = Integer.parseInt(line.substring(line.indexOf("(")+1, line.indexOf(")")));
		    
		    
		    ArrayList<String> childNames = new ArrayList();
		    
		    int beginName = line.indexOf(">") + 1;
		    int commaPos = line.indexOf(",");
		    
		    if(beginName != -1) {
		    	while(commaPos != -1) {
			    	commaPos=line.indexOf(",", beginName);
			    	if(commaPos == -1) {
			    		childNames.add(line.substring(beginName, line.length()));
			    	} else {
			    		childNames.add(line.substring(beginName, commaPos));
				    	beginName = commaPos + 1;
			    	}
			    }
		    }
		    
		    programs.add(new Program(programName, weight, childNames));
		}
		
		
		String currentProgram;
		for(int i=0; i < programs.size(); i++) {
			boolean nameFound = false;
			currentProgram = programs.get(i).getName();
			for(int j=0; j < programs.size(); j++) {
				if(i != j) {
					for(int y=0; y < programs.get(j).getChildNames().size(); y++) {
						if(programs.get(j).getChildNames().contains(programs.get(i).getName())) {
							nameFound = true;
						}
					}
					
				}
				
			}
			if(nameFound == false) {
				System.out.println(currentProgram);
			}
		}
		
	}

}
