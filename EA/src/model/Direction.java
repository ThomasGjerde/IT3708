package model;

public enum Direction {
	UP(0),
	DOWN(2),
	LEFT(3),
	RIGHT(1),
	STILL(-1);
	
	private final int value;
	Direction(int value){
		this.value = value;
	}
	public int getValue(){
		return this.value;
	}
}
