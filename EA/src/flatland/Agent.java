package flatland;

import model.CellContent;
import model.Direction;

public class Agent {
	private int posX;
	private int posY;
	private Direction direction;
	private int foodEaten = 0;
	private int poisonEaten = 0;
	private double fitness;
	public Agent(int posX, int posY, Direction dir){
		this.posX = posX;
		this.posY = posY;
		this.direction = dir;
	}
	public void move(Direction dir, CellContent content){
		
	}
}
