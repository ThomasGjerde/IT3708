package ann;

import model.Parameters;

public class LinearNeuron extends Neuron{

	@Override
	public double fire(double[] input, double[] weights) {
		double output = 0;
		for(int i=0; i<input.length; i++){
			output += input[i] * weights[i];
		}
		output += Parameters.FL_BIAS;
		int div = input.length;
		if(Parameters.FL_BIAS != 0){
			div += 1.0;
		}
		output /= div;
		return output;
	}

}
