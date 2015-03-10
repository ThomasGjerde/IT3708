package model;


public abstract class Individual
{
	private double fitness = 0;
	public double getFitness(){
		return fitness;
	}
	public void setFitness(double fitness){
		this.fitness = fitness;
	}
	public abstract void develop();
	public abstract Individual crossOver(Individual individual);
	public abstract Individual clone();
	public abstract void mutate();
	public abstract void calcFitness();
}
