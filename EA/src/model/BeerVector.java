package model;

public class BeerVector {
	Direction direction;
	int magnitude;
	
	public BeerVector(){
		
	}
	
	public BeerVector(Direction dir){
		direction = dir;
	}
	
	public BeerVector(int mag){
		magnitude = mag;
	}
	
	public BeerVector(Direction dir, int mag){
		direction = dir;
		magnitude = mag;
	}
	
	public int getMagnitude(){
		return magnitude;
	}
	
	public Direction getDirection(){
		return direction;
	}
	
	public void setDirection(Direction dir){
		direction = dir;
	}
	
	public void setMagnitude(int mag){
		magnitude = mag;
	}

}
