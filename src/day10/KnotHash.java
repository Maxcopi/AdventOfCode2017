package day10;

public class KnotHash {

	public static void main(String[] args) {
		
		int[] knotList = new int[256];
		int[] input = {106,16,254,226,55,2,1,166,177,247,93,0,255,228,60,36};
		int currentPosition = 0;
		int[] length;
		for(int i=0; i < knotList.length; i++) {
			knotList[i] = i;
		}
		int skipSize = 0;
		
		
		for(int amountOfNumbers : input) {
			
			length = new int[amountOfNumbers];
			
			makeSelection(amountOfNumbers, currentPosition, length, knotList);
			
			reverseSelection(length);
			
			insertReverseIntoList(currentPosition, length, knotList);
			
			currentPosition += skipSize + amountOfNumbers;
			
			while(currentPosition > knotList.length) {
				currentPosition -= knotList.length;
			}
			
			skipSize++;
			
		}
		
		System.out.println(knotList[0] * knotList[1]);
		
	}
	
	public static void makeSelection(int amountOfNumbers, int currentPosition, int[] length, int[] knotList) {
		
		for(int i=0; i < length.length;i++) {
			if(currentPosition >= knotList.length) {
				currentPosition = 0;
			}
			length[i] = knotList[currentPosition];
			currentPosition++;
			
		}
	}
	
	public static void reverseSelection(int[] length) {
		int[] helpArray = new int[length.length];
		
		for(int i=0; i < helpArray.length; i++) {
			helpArray[i] = length[i];
		}
		int counter = 0;
		
		for(int i = helpArray.length-1; i >= 0; i--) {
			length[counter] = helpArray[i];
			counter++;
		}
	}
	
	private static void insertReverseIntoList(int pos, int[] length, int[] knotList) {
		for(int i=0; i < length.length;i++) {
			if(pos == knotList.length) {
				pos = 0;
			}
			knotList[pos] = length[i];
			pos++;
		}
	}

}
