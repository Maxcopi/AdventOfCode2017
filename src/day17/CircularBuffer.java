package day17;

import java.util.ArrayList;
import java.util.List;

public class CircularBuffer {
	private List<Integer> navigator = new ArrayList<>();
	private int[] values = new int[2018];
	private int position;
	private int stepSize;
	
	{
		navigator.add(0);
		position = 0;
	}
	
	public int determineNewPosition() {
		for(int step=0; step < stepSize; step++) {
			if(position < navigator.size() - 1) {
				position++;
			} else {
				position = 0;
			}
		}
		
		return position;
	}
	
	public void setStepSize(int stepSize) {
		this.stepSize = stepSize;
	}
	
	public void addValue(int stepCount) {
		values[position + 1] = stepCount;
		navigator.add(0);
	}
}
