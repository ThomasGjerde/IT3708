package beertracker;

import java.util.Random;

import model.BeerCellContent;
import model.Direction;
import model.Parameters;

public class BeerBoard {
	BeerCellContent cells[][];
	BeerAgent agent;
	public BeerBoard(){
		cells = new BeerCellContent[Parameters.BT_SIZE_X][Parameters.BT_SIZE_Y];
		int[] sensorPos = new int[] {7,8,9,10,11};
		agent = new BeerAgent(sensorPos);
		generateBlock();
	}
	private void generateBlock(){
		Random rand = new Random();
		int size = rand.nextInt(5) + 1;
		int startPosX = rand.nextInt(Parameters.BT_SIZE_X - size);
		int startPosY = rand.nextInt(Parameters.BT_SIZE_Y - 1) + 1;
		for(int i = 0; i < size; i++){
			cells[startPosX + i][startPosY] = BeerCellContent.BLOCK;
		}
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
	public void moveAgent(Direction dir){
		int[] sensorPos = agent.getPositions();
		for(int i = 0; i < sensorPos.length; i++){
			int pos = sensorPos[i];
			if(dir == Direction.LEFT){
				pos += -1;
			}else if(dir == Direction.RIGHT){
				pos += 1;
			}
			if(pos < 0){
				pos = Parameters.BT_SIZE_X -1;
			}else if(pos > (Parameters.BT_SIZE_X -1)){
				pos = 0;
			}
			sensorPos[i] = pos;
		}
		agent.setPositions(sensorPos);
	}
	private void moveBlock(){
		
	}
}
