package ann;

import model.Parameters;

public class RampNeuron extends Neuron {

	@Override
	public double fire(double[] input, double[] weights) {
		// TODO Auto-generated method stub
		double output = 0;
		double weightedInput = 0;
		for(int i=0; i<input.length; i++){
			weightedInput += input[i] * weights[i];
		}
		weightedInput += Parameters.FL_BIAS;
		//Check this out
		weightedInput /= input.length + Parameters.FL_BIAS != 0 ? 1.0 : 0.0;
		
		if(weightedInput < Parameters.FL_RAMP_LOWER){
			output = 0.0;
		}else if(weightedInput < Parameters.FL_RAMP_UPPER){
			output = weightedInput;
		}else{
			output = 1.0;
		}
		return output;
	}

}
