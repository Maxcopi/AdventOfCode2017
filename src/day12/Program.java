package day12;

import java.util.ArrayList;

public class Program {
	private boolean hasZero=false;
	private String id;
	private String links[];
	private ArrayList<Program> linkedPrograms = new ArrayList<>();
	
	public Program(String linkedPrograms[], String id) {
		links = linkedPrograms;
		this.id = id;
	}
	public boolean getZero() {
		return hasZero;
	}
	
	public void setZero(boolean zero) {
		this.hasZero = zero;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String[] getLinks() {
		return links;
	}
	
	public void setLinkedPrograms(ArrayList<Program> programs) {
		for(int i=0; i < programs.size(); i++) {
			for(String linkId : links) {
				if(linkId != null) {
					if(programs.get(i).getId().equals(linkId)) {
						linkedPrograms.add(programs.get(i));
					}
				}
			}
			
		}
	}
	public ArrayList<Program> getLinkedPrograms() {
		return linkedPrograms;
	}
	
	public void setLinkedProgramsOfLinks() {
		ArrayList<Program> currentProgramLinks;
		
		for(int i=0; i < linkedPrograms.size(); i++) {
			currentProgramLinks = linkedPrograms.get(i).getLinkedPrograms();
			for(int j=0; j < currentProgramLinks.size(); j++) {
				if(!linkedPrograms.contains(currentProgramLinks.get(j))) {
				}
			}
			
		}
	}
	
	
}
