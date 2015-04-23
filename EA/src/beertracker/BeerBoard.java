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
	private boolean pulledDown = false;
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
		int size = rand.nextInt(6) + 1;
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
		if(Parameters.BT_NO_WRAP){
			sensors = new double[positions.length+8];
			if(positions[0] - 1 < 0){
				sensors[positions.length] = 1;
			}
			if(positions[0] - 2 < 0){
				sensors[positions.length +1] = 1;
			}
			if(positions[0] - 3 < 0){
				sensors[positions.length +2] = 1;
			}
			if(positions[0] - 4 < 0){
				sensors[positions.length +3] = 1;
			}
			if(positions[positions.length-1] + 1 > Parameters.BT_SIZE_X -1){
				sensors[positions.length +4] = 1;
			}
			if(positions[positions.length-1] + 2 > Parameters.BT_SIZE_X -1){
				sensors[positions.length +5] = 1;
			}
			if(positions[positions.length-1] + 3 > Parameters.BT_SIZE_X -1){
				sensors[positions.length +6] = 1;
			}
			if(positions[positions.length-1] + 4 > Parameters.BT_SIZE_X -1){
				sensors[positions.length +7] = 1;
			}
		}
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
		//System.out.println(dir.name());
		//Pulldown
		this.pulledDown = false;
		if(dir == Direction.BEERPOOL){
			this.pulledDown = true;
			agent.checkCollision(currentBlock);
			for(Point pos : currentBlock){
				cells[pos.x][pos.y] = BeerCellContent.EMPTY;
			}
			generateBlock();
			return;
		}
		int[] sensorPos = agent.getPositions();
		int[] oldPos = new int[sensorPos.length];
		boolean setRightMost = false;
		boolean setLeftMost = false;
		int[] leftMost = new int[]{0,1,2,3,4};
		int[] rightMost = new int[]{25,26,27,28,29};
		for(int i = 0; i < sensorPos.length; i++){
			int pos = sensorPos[i];
			oldPos[i] = pos;
			if(dir == Direction.LEFT){
				pos += -magnitude;
			}else if(dir == Direction.RIGHT){
				pos += magnitude;
			}
			if(!Parameters.BT_NO_WRAP){
				if(pos < 0){
					pos += (Parameters.BT_SIZE_X) ;
				}else if(pos > (Parameters.BT_SIZE_X -1)){
					pos += -(Parameters.BT_SIZE_X);
				}
			}else{
				if(pos < 0){
					setLeftMost = true;
				}else if(pos > (Parameters.BT_SIZE_X -1)){
					setRightMost = true;
				}
			}
			
			//cells[oldPos][Parameters.BT_SIZE_Y -1] = BeerCellContent.EMPTY;
			//cells[pos][Parameters.BT_SIZE_Y -1] = BeerCellContent.SENSOR;
			sensorPos[i] = pos;
		}
		if(setLeftMost){
			sensorPos = leftMost;
		}else if(setRightMost){
			sensorPos = rightMost;
		}
		agent.setPositions(sensorPos);
		for(int pos : oldPos){
			cells[pos][Parameters.BT_SIZE_Y -1] = BeerCellContent.EMPTY;
		}
		for(int pos : sensorPos){
			cells[pos][Parameters.BT_SIZE_Y -1] = BeerCellContent.SENSOR;
		}
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
	public boolean getPulledDown(){
		return this.pulledDown;
	}
}
