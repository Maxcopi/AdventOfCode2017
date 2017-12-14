package day11;

public class Location {
	private int x = 0;
	private int y = 0;
	private int z = 0;
	private int distance;
	private static int maxDistance = 0;
	
	public void move(String direction) {
		switch(direction) {
		case "n":
			y += 1;
		    z -= 1;
			break;
		case "ne": 
			 x += 1;
			 z -= 1;
			break;
		case "se":
			x += 1;
		    y -= 1;
			break;
		case "s":
			y -= 1;
		    z += 1;
			break;
		case "sw":
			x -= 1;
		    z += 1;
			break;
		case "nw":
			x -= 1;
		    y += 1;
			break;
		default:System.out.println("error");
		}
		
	}
	
	public int getDistance() {
		int distance;
		distance = (Math.abs(x) + Math.abs(y) + Math.abs(z)) / 2;
		checkIfMax(distance);
		return distance;
	}
	
	private void checkIfMax(int distance) {
		if(maxDistance < distance) {
			maxDistance = distance;
		}
	}
	
	public int getMaxDistance() {
		return maxDistance;
	}
	
}
