package ann;

import model.Parameters;

public class StepNeuron extends Neuron{

	@Override
	public double fire(double[] input, double[] weights) {
		double weighted = 0;
		for(int i=0; i<input.length; i++){
			weighted += input[i] * weights[i];
		}
		weighted += Parameters.FL_BIAS;
		weighted /= input.length;
		if(weighted > Parameters.FL_STEP_THRESHOLD){
			return 1.0;
		}else{
			return 0.0;
		}
	}

}
