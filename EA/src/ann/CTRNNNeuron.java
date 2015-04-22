package ann;

public abstract class CTRNNNeuron {
	public abstract double fire(double[] input, double[] weights, double gain, double bias);
}
