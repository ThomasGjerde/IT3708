package ann;

import model.Parameters;

public class LinearNeuron extends Neuron{

	@Override
	public double fire(double[] input, double[] weights) {
		// TODO Auto-generated method stub
		double output = 0;
		for(int i=0; i<input.length; i++){
			output += input[i] * weights[i];
		}
		
		output += Parameters.FL_BIAS;
		//Check this out
		output /= input.length + Parameters.FL_BIAS != 0 ? 1.0 : 0.0;
		return output;
	}

}
