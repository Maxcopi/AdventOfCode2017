package day7;

import java.util.ArrayList;

public class Program {
	private String name;
	private int weight;
	private ArrayList<String> childNames;
	private ArrayList<Program> children = new ArrayList();
	private int sumWeight;
	
	public Program(String name, int weight, ArrayList<String> children) {
		this.name = name;
		this.weight = weight;
		childNames = children;
	}
	
	public String getName() {
		return name;
	}
	public int getWeight() {
		return weight;
	}
	public ArrayList<String> getChildNames(){
		return childNames;
	}
	
	public ArrayList<Program> getChildren() {
		return children;
	}
	
	public void setChildren(ArrayList<Program> programs) {
		for(String childname : childNames) {
			if(childname != null) {
				for(Program program : programs) {
					if(program.getName().equals(childname)){
						children.add(program);
					}
				}
			}
		}
		
	}
	
	public int getSumWeight() {
		int sumWeight = 0;
		
		sumWeight += weight;
		
		for(Program child : children) {
			sumWeight += child.getWeight();
		}
		return sumWeight;
	}
	
}
