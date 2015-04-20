package flatland;

import model.Direction;
import model.Parameters;
import ann.ANN;

public class Flatland {
	double[] weights;
	//ArrayList<Board> graphicBoard;
	public Flatland(double[] weights){
		this.weights = weights;
	}
	public double run(){
		Board board = new Board();
		ANN ann = new ANN();
		for(int i = 0; i < Parameters.FL_TIMESTEPS; i++){
			board.moveAgent(ann.chooseDirection(board));
		}
		return board.getAgent().getFitness();
	}
	public void graphicRun(double[] weights){
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
