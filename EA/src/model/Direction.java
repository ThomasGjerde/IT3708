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
	public static Direction rotateDirection(Direction direction, int value){
		int tempInt = changeDirInt(direction.getValue(),value);
		return getDirectionFromInt(tempInt);
	}
	public static Direction getDirectionFromInt(int value){
		if(value == 0){
			return UP;
		}else if(value == 1){
			return RIGHT;
		}else if(value == 2){
			return DOWN;
		}else if(value == 3){
			return LEFT;
		}else{
			return STILL;
		}
	}
	public static int changeDirInt(int a, int b){
		int tempInt = a + b;
		if(tempInt < 0){
			tempInt = tempInt + 4;
		}else if(tempInt > 3){
			tempInt = tempInt - 4;
		}
		return tempInt;
	}
}
