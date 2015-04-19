package ann;

import flatland.Board;
import model.Direction;
import model.Parameters;

public class ANN {
	double[] inputs;
	double[] outputs;
	double[] weights;
	
	public ANN(){
		inputs = new double[Parameters.FL_INPUT_NEURONS];
		outputs = new double[Parameters.FL_OUTPUT_NEURONS];
		weights = new double[Parameters.FL_INPUT_NEURONS*Parameters.FL_OUTPUT_NEURONS];
	}
	
	public ANN(double[] newWeights){
		inputs = new double[Parameters.FL_INPUT_NEURONS];
		outputs = new double[Parameters.FL_OUTPUT_NEURONS];
		
		//Her kan det kanskje spares litt kraft
		//Er ikke helt sikker på hvor man ikke bare setter = her
		weights = new double[Parameters.FL_INPUT_NEURONS*Parameters.FL_OUTPUT_NEURONS];
		for(int i=0; i<weights.length; i++){
			weights[i] = newWeights[i];
		}
	}
	
	public Direction chooseDirection(Board board){
		
		Direction bestDirection;
		
		return null;
		
	}
}
