package day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemoryReallocationAppPart2 {

	public static void main(String[] args) {
		
		int[] banks = {4,10,4,1,8,4,9,14,5,1,14,15,0,15,3,5};
		
		//int[] banks = {0,2,7,0};
		boolean found = false;
		
		ArrayList<int[]> bankCombinations = new ArrayList<int[]>();
		
		int largestBank;
		int largestBankPos;
		int loopCount = 0;
		int rightBankPos = 0;
		
		while(found == false) {
			int[] banksCopy = new int[banks.length];
			largestBank = 0;
			
			largestBankPos = determineLargestBankPos(banks);
			
			largestBank = banks[largestBankPos];
			
			distributeBlocks(banks,largestBank,largestBankPos);
			
			for(int i=0; i < bankCombinations.size(); i++) {
				if(Arrays.equals(bankCombinations.get(i), banks)) {
					found = true;
					rightBankPos = i + 1;
				}
			}
			
			for(int i=0; i < banks.length; i++) {
				banksCopy[i] = banks[i];
			}
			
			bankCombinations.add(banksCopy);
			
			loopCount++;
			
			for(int number : banks) {
				System.out.print(number + " ");
			}
			System.out.println();
			
		}
		
		System.out.println(loopCount - rightBankPos);
	}
	public static void distributeBlocks(int[] banks, int largest, int largestBankPos) {
		
		
		int bankSpread = largest;
		int spreadAmount = 1;
		/*
		int spreadAmount;
		if(largest / banks.length == 0) {
			spreadAmount = 1;
		} else {
			spreadAmount = largest / (banks.length-1);
		}*/
		
		banks[largestBankPos] = 0;
		
		while(bankSpread > 0) {
			for(int i=largestBankPos + 1; i < banks.length; i++) {
				if(spreadAmount < bankSpread) {
					banks[i] += spreadAmount;
					bankSpread -= spreadAmount;
				} else {
					spreadAmount = bankSpread;
					banks[i] += spreadAmount;
					bankSpread = 0;
				}
			}
			
			for(int j=0; j <= largestBankPos; j++) {
				if(spreadAmount < bankSpread) {
					banks[j] += spreadAmount;
					bankSpread -= spreadAmount;
				} else {
					spreadAmount = bankSpread;
					banks[j] += spreadAmount;
					bankSpread = 0;
				}
				
			}
		}
		
		
	}
	
	public static int determineLargestBankPos(int[] banks) {
		int largestBank = 0;
		int largestBankPos = 0;
		
		for(int i=0; i < banks.length; i++) {
			if(largestBank < banks[i]) {
				largestBank = banks[i];
				largestBankPos = i;
			}
		}
		
		return largestBankPos;
	}

}
