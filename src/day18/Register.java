package day18;


public class Register {
	private int value;
	private static int frequencyLastSound;
	private char name;
	
	public Register(char name) {
		this.name = name;
	}
	
	public int getValue() {
		return value;
	}
	public static int getFrequencyLastSound() {
		return frequencyLastSound;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public static void setFrequencyLastSound(int frequencyLastSound) {
		Register.frequencyLastSound = frequencyLastSound;
	}
	public char getName() {
		return name;
	}
	public void addValue(int value) {
		this.value += value;
	}
	public void multiplyValue(int value) {
		this.value *= value;
	}
	public void modValue(int value) {
		this.value %= value;
	}
	
}
