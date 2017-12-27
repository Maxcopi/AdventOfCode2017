package day14;

import java.util.ArrayList;

public class DiskFragmentationApp {
	public static void main(String[] args) {
		boolean[][] disk = new boolean[128][128];
		String input = "xlqgujun";
		String knotHash;
		String knotHashBinary;
		boolean[] knotHashBinaryArray;
		int oneCount;
		int regionCount = 0;
		
		for(int i=0; i < disk.length; i++) {
			knotHash = createKnotHashFromInput(input + "-" + i);
			
			knotHashBinary = convertKnotHashToBinary(knotHash);
		
			knotHashBinaryArray = convertKnotHashBinaryToArrayOfBools(knotHashBinary);
			
			insertBinaryIntoDisk(knotHashBinaryArray, disk, i);
		}
		
		oneCount = countAmountOfOnes(disk);
		
		System.out.println(oneCount);
		System.out.println(regionCount);
	}
	
	public static String createKnotHashFromInput(String input) {
		int[] knotList = new int[256];
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
		
		return knotHash.toString();
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
		for(int i=0; i < 16; i++) {
			resultXOR = -1;
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
	
	public static String convertKnotHashToBinary(String knotHash) {
		StringBuilder binary = new StringBuilder();
		char currentHexValue;
		for(int i=0; i < knotHash.length(); i++) {
			currentHexValue = knotHash.charAt(i);
			binary.append(convertHexToBinary(currentHexValue));
		}
		return binary.toString();
	}
	
	private static String convertHexToBinary(char hexValue) {
		switch(hexValue) {
		case '0':
			return "0000";
		case '1':
			return "0001";
		case '2':
			return "0010";
		case '3':
			return "0011";
		case '4':
			return "0100";
		case '5':
			return "0101";
		case '6':
			return "0110";
		case '7':
			return "0111";
		case '8':
			return "1000";
		case '9':
			return "1001";
		case 'a':
			return "1010";
		case 'b':
			return "1011";
		case 'c':
			return "1100";
		case 'd':
			return "1101";
		case 'e':
			return "1110";
		case 'f':
			return "1111";
		default: return "00000";
		}
	}
	
	public static boolean[] convertKnotHashBinaryToArrayOfBools(String binary) {
		boolean[] arrayBools = new boolean[binary.length()];
		
		for(int i=0; i < arrayBools.length; i++) {
			if(binary.charAt(i) == '1') {
				arrayBools[i] = true;
			} else {
				arrayBools[i] = false;
			}
		}
		return arrayBools;
	}
	
	public static void insertBinaryIntoDisk(boolean[] knotHashBinaryArray, boolean[][] disk, int index) {
		for(int binaryIndex=0; binaryIndex < knotHashBinaryArray.length; binaryIndex++) {
			disk[index][binaryIndex] = knotHashBinaryArray[binaryIndex];
		}
	}
	
	public static int countAmountOfOnes(boolean[][] disk) {
		int count = 0;
		for(int i=0; i < disk.length; i++) {
			for(int j=0; j < disk[i].length; j++) {
				if(disk[i][j] == true) {
					count++;
				}
			}
		}
		return count;
	}
	
	private static boolean isSquareCounted(ArrayList<int[]> coordList, int[] coords) {
		if(coordList.contains(coords)) {
			return true;
		}
		return false;
	}
	
	public static void getAdjacentTrueSquares(ArrayList<int[]> adjacentSquares, int[] coords, boolean[][] disk){
		int[][] coordAdjacentSquares = {
			{coords[0] -1, coords[1]},
			{coords[0] +1, coords[1]},
			{coords[0], coords[1] + 1},
			{coords[0], coords[1] - 1}
		};
		
		
		if(isSquareInAdjacent(adjacentSquares, coords) == false) {
			for(int i=0; i < coordAdjacentSquares.length; i++) {
				if(doesSquareExist(coordAdjacentSquares[i], disk) == true) {
					adjacentSquares.add(coordAdjacentSquares[i]);
				}
			}
		}
		
		
	}
	
	private static boolean isSquareInAdjacent(ArrayList<int[]> adjacentSquares, int[] squareCoords) {
		if(adjacentSquares.contains(squareCoords)) {
			return true;
		}
		return false;
	}
	
	private static boolean doesSquareExist(int[] coordsSquare, boolean[][] disk) {
		if(coordsSquare[0] < disk.length && coordsSquare[1] < disk.length) {
			
		}
		return false;
	}
}
