package beertracker;

public class BeerAgent {
	private int[] positions;
	private double fitness;
	public BeerAgent(int[] pos){
		this.positions = pos;
	}
	public int[] getPositions(){
		return positions;
	}
}
