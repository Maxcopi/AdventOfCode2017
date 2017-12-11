package day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class ProgramAppPart2 {

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
		
		for(Program program : programs) {
			program.setChildren(programs);
		}
		
		for(Program program : programs) {
			if(program.getChildNames().isEmpty() == false) {
				System.out.println("size " + program.getSumWeight() + " children:" + program.getChildren().size());
			}
		}
		
		
		
	}

}
