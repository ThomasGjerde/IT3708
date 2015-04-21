package beertracker;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import model.BeerCellContent;
import model.Direction;
import model.Parameters;

public class BeerBoard {
	BeerCellContent cells[][];
	BeerAgent agent;
	ArrayList<Point> currentBlock;
	public BeerBoard(){
		cells = new BeerCellContent[Parameters.BT_SIZE_X][Parameters.BT_SIZE_Y];
		int[] sensorPos = new int[] {7,8,9,10,11};
		agent = new BeerAgent(sensorPos);
		for(int pos : sensorPos){
			cells[pos][Parameters.BT_SIZE_Y -1] = BeerCellContent.SENSOR;
		}
		generateBlock();
	}
	public BeerAgent getAgent(){
		return this.agent;
	}
	private void generateBlock(){
		currentBlock = new ArrayList<Point>();
		Random rand = new Random();
		int size = rand.nextInt(5) + 1;
		int startPosX = rand.nextInt(Parameters.BT_SIZE_X - size);
		int startPosY = rand.nextInt(Parameters.BT_SIZE_Y - 2) + 1;
		for(int i = 0; i < size; i++){
			int posX = startPosX + i;
			int posY = startPosY;
			cells[posX][posY] = BeerCellContent.BLOCK;
			currentBlock.add(new Point(posX,posY));
		}
	}
	public BeerCellContent[][] getCells(){
		return cells;
	}
	public double[] getSensorInfo(){
		int[] positions = agent.getPositions();
		double[] sensors = new double[positions.length]; 
		for(int i = 0; i < positions.length; i++){
			sensors[i] = 0;
			for(int j = 0; j < cells[0].length; j++){
				if(cells[positions[i]][j] == BeerCellContent.BLOCK){
					sensors[i] = 1;
				}
			}
		}
		return sensors;
	}
	public void moveAgent(Direction dir, int magnitude){
		int[] sensorPos = agent.getPositions();
		
		for(int i = 0; i < sensorPos.length; i++){
			int pos = sensorPos[i];
			int oldPos = pos;
			if(dir == Direction.LEFT){
				pos += -magnitude;
			}else if(dir == Direction.RIGHT){
				pos += magnitude;
			}
			if(pos < 0){
				pos += (Parameters.BT_SIZE_X) ;
			}else if(pos > (Parameters.BT_SIZE_X -1)){
				pos += -(Parameters.BT_SIZE_X);
			}
			cells[oldPos][Parameters.BT_SIZE_Y -1] = BeerCellContent.EMPTY;
			cells[pos][Parameters.BT_SIZE_Y -1] = BeerCellContent.SENSOR;
			sensorPos[i] = pos;
		}
		agent.setPositions(sensorPos);
		moveBlock();
	}
	private void moveBlock(){
		ArrayList<Point> newBlock = new ArrayList<Point>();
		for(Point pos : currentBlock){
			cells[pos.x][pos.y] = BeerCellContent.EMPTY;
			int newY = pos.y + 1;
			newBlock.add(new Point(pos.x,newY));
			if(newY < Parameters.BT_SIZE_Y - 1){
				cells[pos.x][newY] = BeerCellContent.BLOCK;
			}
		}
		if(newBlock.get(0).y == Parameters.BT_SIZE_Y -1){
			agent.checkCollision(newBlock);
			generateBlock();
		}else{
			currentBlock = newBlock;
		}
	}
}
