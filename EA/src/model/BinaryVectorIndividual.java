package model;

import java.util.Random;

public abstract class BinaryVectorIndividual extends Individual
{
	protected boolean[] genotype = new boolean[Parameters.VECTOR_LENGTH]; //Possibly unnecessary
	protected double[] phenotype = new double[Parameters.VECTOR_LENGTH];
	
	public boolean[] getGenotype()
	{
		return genotype;
	}
	public void setGenotype(boolean[] genotype)
	{
		this.genotype = genotype;
	}
	@Override
	public String toString()
	{
		String s = "[";
		for(double i : phenotype){
			s += i;
			s += ",";
		}
		if(s.length() > 0){
			s = s.substring(0,s.length() -1);
		}
		s += "]";
		return s;
	}
	@Override
	public void develop()
	{
		for(int i = 0; i < genotype.length; i++){
			phenotype[i] = genotype[i] ? 1 : 0;
		}
	}

	protected boolean[] runCrossOver(boolean[] partnersGenotype){
		Random rand = new Random();
		boolean[] newGenotype = new boolean[this.genotype.length];
		if(Parameters.TWO_POINT_CROSSOVER){
			int crossAt1 = 1 + rand.nextInt(this.genotype.length - 2);
			int crossAt2 = crossAt1 + rand.nextInt((this.genotype.length - 1) - crossAt1);
			for(int i = 0; i < crossAt1; i++){
				newGenotype[i] = this.genotype[i];
			}
			for(int i = crossAt1; i < crossAt2; i++){
				newGenotype[i] = partnersGenotype[i];
			}
			for(int i = crossAt2; i < this.genotype.length; i++){
				newGenotype[i] = this.genotype[i];
			}
		}else{
			int crossAt = 1 + rand.nextInt(this.genotype.length - 1);
			for(int i = 0; i < crossAt; i++){
				newGenotype[i] = this.genotype[i];
			}
			for(int i = crossAt; i < this.genotype.length; i++){
				newGenotype[i] = partnersGenotype[i];
			}
		}
		return newGenotype;
	}

	@Override
	public void mutate()
	{
		for(int i = 0; i < this.genotype.length; i++){
			if(Math.random() < Parameters.MUTATION_RATE){
				this.genotype[i] = !this.genotype[i];
			}
		}
		
	}
}
