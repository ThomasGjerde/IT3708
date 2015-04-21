package beertracker;

import model.BeerCellContent;

public class BeerBoard {
	BeerCellContent cells[][];
	BeerAgent agent;
	public BeerBoard(){
		cells = new BeerCellContent[30][15];
		int[] sensorPos = new int[] {7,8,9,10,11};
		agent = new BeerAgent(sensorPos);
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
}
