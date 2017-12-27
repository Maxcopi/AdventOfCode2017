package day15;

import java.math.BigInteger;

public class DuelingGenerators {

	public static void main(String[] args) throws InterruptedException {
		//Generator A starts with 618
		//Generator B starts with 814
		
		long valueGeneratorA = 618;
		long valueGeneratorB = 814;

		int divider = 2147483647;
		
		System.out.println(part2(valueGeneratorA, valueGeneratorB, divider));
	}

	public static int part1(long valueGeneratorA, long valueGeneratorB, int divider) {
		int matchingPairs = 0;
		
		for(int i=0; i < 40000000; i++) {
			valueGeneratorA = (valueGeneratorA * 16807) % divider;
			valueGeneratorB = (valueGeneratorB * 48271) % divider;
			if(((short)valueGeneratorA) == ((short)valueGeneratorB)) { // shorts are 16 bits long (will cut front off)
				matchingPairs++;
			}
		}
		
		return matchingPairs;
	}
	
	
	
	public static int part2(long valueGeneratorA, long valueGeneratorB, int div) {
		int matchingPairs = 0;
		
		BigInteger genA = new BigInteger("" + valueGeneratorA);
		BigInteger genB = new BigInteger("" + valueGeneratorB);
		
		BigInteger multiplierA = new BigInteger("" + 16807);
		BigInteger multiplierB = new BigInteger("" + 48271);
		
		BigInteger divider = new BigInteger("" + div);
		
		for(int i=0; i < 5000000; i++) {
			genA = new BigInteger("" + (genA.multiply(multiplierA).mod(divider)));
			while(Integer.parseInt(genA.toString().substring(genA.toString().length()-3)) % 4 != 0) {
				genA = new BigInteger("" + (genA.multiply(multiplierA).mod(divider)));
			}
			genB = new BigInteger("" + (genB.multiply(multiplierB).mod(divider)));
			while(Integer.parseInt(genB.toString().substring(genB.toString().length()-3)) % 8 != 0) {
				genB = new BigInteger("" + (genB.multiply(multiplierB).mod(divider)));
			}
			
			if(genA.shortValue() == genB.shortValue()){
				matchingPairs++;
			}	
		}
		return matchingPairs;
	}
}
