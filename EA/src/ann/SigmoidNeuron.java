package ann;

import model.Parameters;

public class SigmoidNeuron extends Neuron{

	@Override
	public double fire(double[] input, double[] weights) {
		double weighted = 0;
		for(int i=0; i<input.length; i++){
			weighted += input[i] * weights[i];
		}
		weighted /= input.length;
		return 1.0 / (1.0 + Math.exp((-Parameters.FL_SIGMOID_SCALING * weighted)));
	}

}
