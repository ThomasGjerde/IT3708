package flatland;

import model.CellContent;
import model.Direction;

public class Agent {
	private int posX;
	private int posY;
	private Direction orientation;
	private int foodEaten = 0;
	private int poisonEaten = 0;
	private double fitness = 0.0;
	public Agent(int posX, int posY, Direction dir){
		this.posX = posX;
		this.posY = posY;
		this.orientation = dir;
	}
	public void setPosition(int posX,int posY, CellContent content, Direction orientation){
		this.posX = posX;
		this.posY = posY;
		if(content == CellContent.FOOD){
			foodEaten++;
		}else if(content == CellContent.POISON){
			poisonEaten++;
		}
		this.orientation = orientation;
	}
	public Direction getOrientation(){
		return this.orientation;
	}
	public void calcFitness(){
		fitness += foodEaten;
		fitness -= poisonEaten*2;
	}
	public int getPosX(){
		return posX;
	}
	public int getPosY(){
		return posY;
	}
	public double getFitness(){
		return fitness;
	}
}
