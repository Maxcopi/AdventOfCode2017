package day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Firewall {

	public static void main(String[] args) throws FileNotFoundException {
		boolean[][] firewalls = new boolean[97][];
		
		Scanner filehandler = new Scanner(new File("src/day13/input"));
		
		String line;
		int firewallPosition;
		int firewallLength;
		int conflictCount = 0;
		double severity = 0;
		
		boolean[] richtingen = new boolean[firewalls.length];
		
		while(filehandler.hasNextLine()) {
			line = filehandler.nextLine().replace(" ", "");
			firewallPosition = Integer.parseInt(line.substring(0, line.indexOf(':')));
			firewallLength = Integer.parseInt(line.substring(line.indexOf(':')+1));
			firewalls[firewallPosition] = new boolean[firewallLength];
		}
		
		
		int delay = 0;
		
		boolean firstRun = true;
		while(severity > 0 || firstRun) {
			initializeFirewalls(firewalls);
			firstRun = false;
			for(int i=0; i < delay; i++) {
				moveFirewallScanners(firewalls, richtingen, i);
			}
			severity = 0;
			for(int i=0; i < firewalls.length; i++) {
					severity += checkConflict(firewalls, i);
					moveFirewallScanners(firewalls, richtingen, i);
			}
			delay++;
		}
		
		System.out.println(delay);
		System.out.println(severity);
		
		for(int i=0; i < firewalls.length; i++) {
			if(firewalls[i] != null) {
				for(int j=0; j < firewalls[i].length; j++) {
					System.out.print(firewalls[i][j]);
				}
				System.out.println();
			}
		}
	}
		
		/*
		for(int pos = 0; pos < firewalls.length; pos++) {
			conflictCount += checkConflict(firewalls, pos);
			moveFirewallScanners(firewalls, richtingen);
		}
		*/
		

	
	public static void initializeFirewalls(boolean [][] firewalls) {
		for(int i=0; i < firewalls.length; i++) {
			if(!(firewalls[i] == null)) {
				firewalls[i][0] = true;
			}
		}
	}
	
	public static void moveFirewallScanners(boolean [][] firewalls, boolean[] richtingen, int depth) {
		for(int i=0; i < firewalls.length; i++) {
			if(isDirectionBackwards(richtingen, i) == false) {
				moveForward(firewalls, i, richtingen);
			} else {
				moveBackwards(firewalls, i, richtingen);
			}
		}
	}
	
	public static int checkConflict(boolean[][] firewalls, int position) {
		int severity = 0;
		
		for(int i=0; i < firewalls.length; i++) {
			if(!(firewalls[i] == null)) {
				for(int j=0; j < firewalls[i].length; j++) {
					if(firewalls[i][j] == true){
						if(position == i && j == 0) {
							return position * firewalls[i].length;
						}
					}
				}
			}
		}
		
		return severity;
	}
	
	public static void moveForward(boolean[][] firewalls, int depth, boolean[] richtingen) {
		int position;
		if(firewalls[depth] != null) {
			position = findPosition(firewalls, depth);
			if(position + 1 < firewalls[depth].length) {
				firewalls[depth][position] = false;
				firewalls[depth][position+1] = true;
			} else {
				richtingen[depth] = true;
				moveBackwards(firewalls, depth, richtingen);
			}
		}
	}
	
	public static int findPosition(boolean[][] firewalls, int depth) {
		for(int j=0; j < firewalls[depth].length; j++) {
			if(firewalls[depth][j] == true) {
				return j;
			}
		}
		
		System.out.println("error");
		return -1;
	}
	
	public static boolean isDirectionBackwards(boolean[] richtingen, int depth) {
		return richtingen[depth];
	}
	
	public static void moveBackwards(boolean[][] firewalls, int depth, boolean[] richtingen) {
		int position;
		if(firewalls[depth] != null) {
			position = findPosition(firewalls, depth);
			if(position - 1 >= 0) {
				firewalls[depth][position] = false;
				firewalls[depth][position-1] = true;
			} else {
				richtingen[depth] = false;
				moveForward(firewalls, depth, richtingen);
			}
		}
	}
}
