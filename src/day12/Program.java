package day12;

public class Program {
	private boolean hasZero=false;
	private String id;
	private String links[];
	
	public Program(String linkedPrograms[]) {
		links = linkedPrograms;
	}
	public boolean getZero() {
		return hasZero;
	}
	
	public void setZero(boolean zero) {
		this.hasZero = zero;
	}
	
	public String[] getLinks() {
		return links;
	}
	
}
