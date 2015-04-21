package beertracker;

import java.awt.Point;
import java.util.ArrayList;

public class BeerAgent {
	private int[] positions;
	private double fitness;
	private int avoided = 0;
	private int caught = 0;
	public BeerAgent(int[] pos){
		this.positions = pos;
	}
	public int[] getPositions(){
		return positions;
	}
	public void setPositions(int[] pos){
		this.positions = pos;
	}
	public void calcFitness(){
		this.fitness = caught + avoided;
	}
	public double getFitness(){
		return this.fitness;
	}
	public void checkCollision(ArrayList<Point> block){
		if(block.size() > 4){
			for(Point pos : block){
				for(int agentPos : positions){
					if(pos.x == agentPos){
						return;
					}
				}
			}
			avoided++;
		}else{
			int hits = 0;
			for(Point pos : block){
				for(int agentPos : positions){
					if(pos.x == agentPos){
						hits++;
					}
				}
			}
			if(hits == block.size()){
				caught++;
			}
		}
	}
}
