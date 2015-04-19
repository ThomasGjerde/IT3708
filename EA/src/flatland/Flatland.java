package flatland;

import model.Parameters;
import ann.ANN;

public class Flatland {
	double[] weights;
	public Flatland(double[] weights){
		this.weights = weights;
	}
	public double run(){
		Board board = new Board();
		ANN ann = new ANN();
		for(int i = 0; i < Parameters.FLATLAND_TIMESTEPS; i++){
			
		}
		return 0.0;
	}
}
