package flatland;

import model.CellContent;
import model.Direction;

public class Agent {
	private int posX;
	private int posY;
	private Direction orientation;
	private int foodEaten = 0;
	private int poisonEaten = 0;
	private double fitness;
	public Agent(int posX, int posY, Direction dir){
		this.posX = posX;
		this.posY = posY;
		this.orientation = dir;
	}
	public void setPosition(int posX,int posY, CellContent content){

	}
	public Direction getOrientation(){
		return this.orientation;
	}
	public int getPosX(){
		return posX;
	}
	public int getPosY(){
		return posY;
	}
}
