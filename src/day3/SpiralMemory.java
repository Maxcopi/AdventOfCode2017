package day3;

public class SpiralMemory {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int numberSpiral = 312051;
		
		int maxNumber=1;
		int minNumber=1;
		int ringSize=1;
		int lastMaxNumber=0;
		int ringCount = -1;
		
		int[] midPoints = new int[4];
		
		while(!(numberSpiral <= maxNumber && numberSpiral >= minNumber)){
			maxNumber = ringSize * ringSize;
			minNumber = lastMaxNumber + 1;
			
			ringCount++;
			ringSize+=2;
			lastMaxNumber = maxNumber;
		}
		ringSize -=2;
		
		findMidPoints(maxNumber, midPoints, ringSize);
		
		int closestMid = findClosestMidPoint(numberSpiral, midPoints, maxNumber, minNumber);
		
		System.out.println(closestMid);
		
		int totalDistance = closestMid - numberSpiral;
		
		if(totalDistance < 0) {
			totalDistance = -1 * totalDistance;
		}
		
		totalDistance += ringCount;
		
		System.out.println(totalDistance);
		System.out.println(ringCount);
	}
	
	public static void findMidPoints(int max, int[]points, int ringSize) {
		int midpointBottom = max - ringSize / 2;
		points[0] = midpointBottom;
		
		for(int i = 1; i < points.length; i++) {
			points[i] = points[i-1] - (ringSize-1);
		}
	}
	
	public static int findClosestMidPoint(int numberSpiral, int[] midPoints, int max, int min) {
		int closestDistance = 50000;
		int closestMidPoint = 0;
		int distance;
		
		for(int i=0; i < midPoints.length; i++) {
			distance = numberSpiral - midPoints[i];
			
			if(distance < 0) {
				distance = -1 * distance;
			}
			
			if(distance < closestDistance) {
				closestDistance = distance;
				closestMidPoint = midPoints[i];
			}
		}
		
		return closestMidPoint;
	}

}
