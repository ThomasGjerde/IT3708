package ann;

import model.Parameters;

public class HyperbolicTangentNeuron extends Neuron{

	@Override
	public double fire(double[] input, double[] weights) {
		// TODO Auto-generated method stub
		double output = 0;
		double weightedInput = 0;
		double posExpo;
		double negExpo;
		for (int i = 0; i < input.length; i++) {
			weightedInput += input[i] * weights[i];
		}
		weightedInput += Parameters.FL_BIAS;
		weightedInput /= input.length + Parameters.FL_BIAS != 0 ? 1.0 : 0.0;
		
		posExpo = Math.exp(weightedInput);
		negExpo = Math.exp(-weightedInput);
		
		output = (posExpo - negExpo) / (posExpo + negExpo);
		return output;
	}

}
