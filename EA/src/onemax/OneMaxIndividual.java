package onemax;
import java.util.Random;

import ea.BinaryVectorIndividual;
import ea.Individual;

import util.Utilities;


import model.Parameters;

public class OneMaxIndividual extends BinaryVectorIndividual
{
	

	@Override
	public Individual clone()
	{
		boolean[] newGenotype = new boolean[Parameters.VECTOR_LENGTH];
		//Not sure if this is necessary, doing it anyway
		for(int i = 0; i < Parameters.VECTOR_LENGTH; i++){
			newGenotype[i] = this.genotype[i];
		}
		return new OneMaxIndividual(newGenotype);
	}
	
	
	private OneMaxIndividual(boolean[] genotype){
		this.genotype = genotype;
	}
	public static OneMaxIndividual generateRandomIndividual(){
		boolean[] newGenotype = new boolean[Parameters.VECTOR_LENGTH];
		Random r = new Random();
		for(int i = 0; i < newGenotype.length; i++){
			newGenotype[i] = r.nextBoolean();
		}
		return new OneMaxIndividual(newGenotype);
	}
	
	@Override
	public void calcFitness()
	{
		int numOnes = 0;
		//int[] target = {1,0,1,0,1,0,1,0,0,1,1,0,1,0,1,0,1,0,0,1,1,0,1,0,1,0,1,0,0,1,1,0,1,0,1,0,1,0,0,1};
		for(int i = 0; i < phenotype.length; i++){
			if(phenotype[i] == 1){ //target[i]){
				numOnes++;
			}
		}
		this.setFitness(((double)numOnes)/phenotype.length);
	}


	@Override
	public Individual crossOver(Individual individual)
	{
		boolean[] newGenotype = runCrossOver(((OneMaxIndividual)individual).getGenotype());
		return new OneMaxIndividual(newGenotype);
	}

}
