package day17;

import java.util.ArrayList;
import java.util.List;

public class SpinLock {
	public static void main(String[] args) {
		int numSteps = 371;
		
		final List<Integer> spinLock = new ArrayList<>();
	    spinLock.add(0);

	    int pos = 0;
	    for(int i = 1; i <= 2017; i++) {
	        pos = ((pos + numSteps) % i) + 1;
	        spinLock.add(pos, i);
	    }

	    System.out.println(spinLock.get(pos + 1));
	}
}
