package day10;

import java.util.ArrayList;

public class Part2 {
public static void main(String[] args) {
		int[] knotList = new int[256];
		String input = "106,16,254,226,55,2,1,166,177,247,93,0,255,228,60,36";
		int currentPosition = 0;
		int[] length;
		
		int skipSize = 0;
		int[] extraValues = {17, 31, 73, 47, 23};
		ArrayList<Integer> codes = new ArrayList<>();
		int[] denseHash = new int[16];
		StringBuilder knotHash = new StringBuilder();
		
		for(int i=0; i < knotList.length; i++) {
			knotList[i] = i;
		}
		for(int i=0; i < input.length(); i++) {
			codes.add((int)input.charAt(i));
		}
		
		for(int extra : extraValues) {
			codes.add(extra);
		}
		
		for(int i=0; i < 64; i++) {
			for(int amountOfNumbers : codes) {
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
		}

		denseHash = createDenseHash(knotList);
		
		
		for(int denseValue : denseHash) {
			knotHash.append(convertToHexadecimal(denseValue));
		}
		
		for(int value : denseHash) {
			System.out.println(value);
		}
		
		System.out.println(knotHash.toString());
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
	
	public static int[] createDenseHash(int[] knotList) {
		int[] denseHash = new int[16];
		int knotListIndex = 0;
		int resultXOR = -1;
		int currentHashValue;
		for(int i=0; i < 16; i++) {
			resultXOR = -1;
			currentHashValue = 0;
			for(int j=0; j < 16; j++) {
				if(resultXOR == -1) {
					resultXOR = knotList[knotListIndex];
				} else {
					resultXOR = resultXOR ^ knotList[knotListIndex + j];
				}
			}
			knotListIndex += 16;
			denseHash[i] = resultXOR;
		}
		
		return denseHash;
	}
	
	public static String convertToHexadecimal(int value) {
		String hexValue;
		
		hexValue = Integer.toHexString(value);
		
		if(hexValue.length()==1) {
			hexValue = "0" + hexValue;
		}
		
		return hexValue;
	}
}
