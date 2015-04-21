package beertracker;

import model.BeerVector;
import model.Direction;
import model.Parameters;
import model.StaticGraphic;
import ann.CTRNN;

public class BeerTracker {
	double[] weights;
	//ArrayList<Board> graphicBoard;
	public BeerTracker(double[] weights){
		this.weights = new double[weights.length];
		for(int i = 0; i < weights.length; i++){
			this.weights[i] = weights[i];
		}
	}
	public double run(){
		double sum = 0;
		int runs = 0;
		for(int i = 0; i < Parameters.ANN_RUNS; i++){
			BeerBoard board = new BeerBoard();
			CTRNN ctrnn = new CTRNN(weights);
			for(int j = 0; j < Parameters.FL_TIMESTEPS; j++){
				//board.moveAgent(CTRNN.chooseAction(board));
				BeerVector midVector = ctrnn.chooseAction(board);
				board.moveAgent(midVector.getDirection(), midVector.getMagnitude());
			}
			board.getAgent().calcFitness();
			sum += board.getAgent().getFitness();
			runs++;
		}
		return sum / runs;
		//return board.getAgent().getFitness();
	}
	public void graphicRun(){
		BeerBoard board = new BeerBoard();
		CTRNN ctrnn = new CTRNN(weights);
		//Direction step = Direction.STILL;
		//FlGraphics graphics = new FlGraphics(board);
		BeerVector midVector = new BeerVector();
		StaticGraphic.beerGraphics.setBoard(board);
		
		for(int i=0; i<Parameters.FL_TIMESTEPS; i++){
			midVector = ctrnn.chooseAction(board);
			board.moveAgent(midVector.getDirection(), midVector.getMagnitude());
			StaticGraphic.beerGraphics.setBoard(board);
			
		}
	}
}
