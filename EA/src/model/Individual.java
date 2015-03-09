package model;

import java.util.ArrayList;

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
	public abstract void crossOver(Individual individual);
	public abstract void mutate();
	public abstract void calcFitness();
	public abstract ArrayList<Individual> generateChildren(Individual individual);
}
