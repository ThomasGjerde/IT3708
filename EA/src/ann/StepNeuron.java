package ann;

import model.Parameters;

public class StepNeuron extends Neuron{

	@Override
	public double fire(double[] input, double[] weights) {
		// TODO Auto-generated method stub
		
		double weightedInput = 0;
		for(int i=0; i<input.length; i++){
			weightedInput += input[i] * weights[i];
		}
		weightedInput += Parameters.FL_BIAS;
		//Check this out
		weightedInput /= input.length + Parameters.FL_BIAS != 0 ? 1.0 : 0.0;
		return weightedInput > Parameters.FL_STEP_THRESHOLD ? 1.0 : 0.0;
	}

}
