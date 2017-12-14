package day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class ProgramAppPart2 {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner fileReader = new Scanner(new File("src/day7/programs.txt"));

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
			
			if(program.getName().equals("rqwgj")){
				for(Program child : program.getChildren()) {
					int sum =0;
					System.out.println(child.getName() + " " + child.getSumWeight());
					for(Program grandchild : child.getChildren()) {
						System.out.println("\t " + grandchild.getName() + " " + grandchild.getSumWeight());
						sum+=grandchild.getSumWeight();
						for(Program greatgrandchild : grandchild.getChildren()) {
							System.out.println("\t\t" + greatgrandchild.getName() + " " + greatgrandchild.getSumWeight());
							for(Program greatgreatgrandchild : greatgrandchild.getChildren()) {
								System.out.println("\t\t\t" + greatgreatgrandchild.getName() + " " + greatgreatgrandchild.getSumWeight());
								for(Program greatgreatgreatgrandchild : greatgreatgrandchild.getChildren()) {
									System.out.println("\t\t\t\t" + greatgreatgreatgrandchild.getName() + " " + greatgreatgreatgrandchild.getSumWeight());
									
								}

							}
							
						}
						
					}System.out.println(sum);
				}
				
			}
		}
		
		
		
		
		
	}

}
