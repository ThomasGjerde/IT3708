package ann;

import java.util.Arrays;

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
		//board.getAgent().evaluateNextMove(board);
		//inputs[0] = board.agent.foodLeft() ? 1.0 : 0.0;
		//inputs[1] = board.agent.foodAhead() ? 1.0 : 0.0;
		//inputs[2] = board.agent.foodRight() ? 1.0 : 0.0;
		//inputs[3] = board.agent.poisonLeft() ? 1.0 : 0.0;
		//inputs[4] = board.agent.poisonAhead() ? 1.0 : 0.0;
		//inputs[5] = board.agent.poisonRight() ? 1.0 : 0.0;
		Direction bestDirection;
		
		int temp = Parameters.FL_INPUT_NEURONS;
		switch(Parameters.FL_ACTIVE_FUNC){
		case LINEAR:
			LinearNeuron ln = new LinearNeuron();
			for (int i = 0; i < this.outputs.length; i++) {
				outputs[i] = ln.fire(inputs,Arrays.copyOfRange(weights, i * temp, i * temp+temp));
			}
			break;
		case RAMP:
			RampNeuron rn = new RampNeuron();
			for (int i = 0; i < this.outputs.length; i++) {
				outputs[i] = rn.fire(inputs,Arrays.copyOfRange(weights, i * temp, i * temp+temp));
			}
			break;
		case STEP:
			StepNeuron sn = new StepNeuron();
			for (int i = 0; i < this.outputs.length; i++) {
				outputs[i] = sn.fire(inputs,Arrays.copyOfRange(weights, i * temp, i * temp+temp));
			}
			break;
		case SIGMOID:
			SigmoidNeuron sign = new SigmoidNeuron();
			for (int i = 0; i < this.outputs.length; i++) {
				outputs[i] = sign.fire(inputs,Arrays.copyOfRange(weights, i * temp, i * temp+temp));
			}
			break;
		case HYPERBOLIC_TANGENT:
			HyperbolicTangentNeuron hbtn = new HyperbolicTangentNeuron();
			for (int i = 0; i < this.outputs.length; i++) {
				outputs[i] = hbtn.fire(inputs,Arrays.copyOfRange(weights, i * temp, i * temp+temp));
			}
			break;
		default:
			System.out.println("activation type er ikke satt");
			break;
		}
		
		if(outputs[0] > outputs[1] && outputs[0] > outputs[2]){ bestDirection = Direction.LEFT;}
		else if(outputs[1] > outputs[0] && outputs[1] > outputs[2]){ bestDirection = Direction.UP;}
		else if(outputs[2] > outputs[0] && outputs[2] > outputs[1]){ bestDirection = Direction.RIGHT;}
		else{bestDirection = Direction.STILL;}
		
		return bestDirection;
		
	}
}
