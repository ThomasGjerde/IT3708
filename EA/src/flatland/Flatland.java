package flatland;

import model.Direction;
import model.Parameters;
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
		Board board = new Board();
		ANN ann = new ANN(weights);
		for(int i = 0; i < Parameters.FL_TIMESTEPS; i++){
			board.moveAgent(ann.chooseDirection(board));
		}
		board.getAgent().calcFitness();
		return board.getAgent().getFitness();
	}
	public void graphicRun(){
		Board board = new Board();
		ANN ann = new ANN(weights);
		Direction step = Direction.STILL;
		FlGraphics graphics = new FlGraphics(board);
		
		for(int i=0; i<Parameters.FL_TIMESTEPS; i++){
			step = ann.chooseDirection(board);
			board.moveAgent(step);
			graphics.setBoard(board);
			
		}
	}
}
