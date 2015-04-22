package ann;

import model.Parameters;

public class SigmoidCTRNNNeuron extends CTRNNNeuron{

	@Override
	public double fire(double[] input, double[] weights, double gain, double bias) {
		double weighted = 0;
		for(int i=0; i<input.length; i++){
			weighted += input[i] * weights[i];
		}
		weighted += bias;
		return (1.0 / (1.0 + Math.exp((-Parameters.FL_SIGMOID_SCALING * weighted))*gain));
	}

}
