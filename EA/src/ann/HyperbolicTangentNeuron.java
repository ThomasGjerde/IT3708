package ann;

import model.Parameters;

public class HyperbolicTangentNeuron extends Neuron{

	@Override
	public double fire(double[] input, double[] weights) {
		double output = 0;
		double weighted = 0;
		double posExpo = 0;
		double negExpo = 0;
		for (int i = 0; i < input.length; i++) {
			weighted += input[i] * weights[i];
		}
		weighted /= input.length;
		posExpo = Math.exp(weighted);
		negExpo = Math.exp(-weighted);
		output = (posExpo - negExpo) / (posExpo + negExpo);
		return output;
	}

}
