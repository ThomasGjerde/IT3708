package flatland;

import java.util.Random;

import model.Direction;
import model.Parameters;
import model.StaticGraphic;
import ann.ANN;

public class Flatland {
	double[] weights;
	//ArrayList<Board> graphicBoard;
	public Flatland(double[] weights){
		this.weights = new double[weights.length];
		for(int i = 0; i < weights.length; i++){
			this.weights[i] = weights[i];
		}
	}
	public double run(){
		double sum = 0;
		int runs = 0;
		if(!Parameters.FL_STATIC_BOARD){
			for(int i = 0; i < Parameters.ANN_RUNS; i++){
				Board board = new Board();
				ANN ann = new ANN(weights);
				for(int j = 0; j < Parameters.FL_TIMESTEPS; j++){
					board.moveAgent(ann.chooseDirection(board));
				}
				board.getAgent().calcFitness();
				sum += board.getAgent().getFitness();
				runs++;
			}
		}else{
			for(int i = 0; i < Parameters.NUM_STATIC_MAPS; i++){
				Board board = new Board(i);
				ANN ann = new ANN(weights);
				for(int j = 0; j < Parameters.FL_TIMESTEPS; j++){
					board.moveAgent(ann.chooseDirection(board));
				}
				board.getAgent().calcFitness();
				sum += board.getAgent().getFitness();
				runs++;
			}
		}

		return sum / runs;
		//return board.getAgent().getFitness();
	}
	public void graphicRun(){
		Board board;
		if(Parameters.FL_STATIC_BOARD){
			board = new Board(0);
		}else{
			board = new Board();
		}
		ANN ann = new ANN(weights);
		Direction step = Direction.STILL;
		//FlGraphics graphics = new FlGraphics(board);
		StaticGraphic.flGraphics.setBoard(board);
		
		for(int i=0; i<Parameters.FL_TIMESTEPS; i++){
			step = ann.chooseDirection(board);
			board.moveAgent(step);
			StaticGraphic.flGraphics.setBoard(board);
			
		}
	}
}
