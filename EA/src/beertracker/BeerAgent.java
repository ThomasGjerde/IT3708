package beertracker;

import java.awt.Point;
import java.util.ArrayList;

public class BeerAgent {
	private int[] positions;
	private double fitness;
	public BeerAgent(int[] pos){
		this.positions = pos;
	}
	public int[] getPositions(){
		return positions;
	}
	public void setPositions(int[] pos){
		this.positions = pos;
	}
	public void checkCollision(ArrayList<Point> block){
		
	}
}
