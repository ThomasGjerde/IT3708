package flatland;

import ann.ANN;

public class Flatland {
	double[] weights;
	public Flatland(double[] weights){
		this.weights = weights;
	}
	public double run(){
		Board board = new Board();
		ANN ann = new ANN();
		return 0.0;
	}
}
