package ann;

import model.Parameters;

public class SigmoidNeuron extends Neuron{

	@Override
	public double fire(double[] input, double[] weights) {
		// TODO Auto-generated method stub
		double output = 0;
		double weightedInput = 0;
		for(int i=0; i<input.length; i++){
			weightedInput += input[i] * weights[i];
		}
		weightedInput += Parameters.FL_BIAS;
		//weightedInput += bias;
		//Check this out
		weightedInput /= input.length + Parameters.FL_BIAS != 0 ? 1.0 : 0.0;
		//weightedInput /= input.length + bias != 0 ? 1.0 : 0.0;
		output = 1.0 / (1.0 + Math.exp((-Parameters.FL_SIGMOID_SCALING * weightedInput)));
		return output;
	}

}
