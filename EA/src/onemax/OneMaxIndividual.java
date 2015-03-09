package onemax;

import java.util.ArrayList;
import java.util.Random;

import com.sun.xml.internal.ws.policy.spi.PolicyAssertionValidator.Fitness;

import model.Individual;
import model.Parameters;

public class OneMaxIndividual extends Individual
{
	private boolean[] genotype = new boolean[Parameters.ONE_MAX_VECTOR_LENGTH]; //Possibly unnecessary
	private int[] phenotype = new int[Parameters.ONE_MAX_VECTOR_LENGTH];
	
	private OneMaxIndividual(boolean[] genotype){
		this.genotype = genotype;
	}
	public static OneMaxIndividual generateRandomIndividual(){
		boolean[] newGenotype = new boolean[Parameters.ONE_MAX_VECTOR_LENGTH];
		Random r = new Random();
		for(int i = 0; i < newGenotype.length; i++){
			newGenotype[i] = r.nextBoolean();
		}
		return new OneMaxIndividual(newGenotype);
	}
	
	@Override
	public void develop()
	{
		for(int i = 0; i < genotype.length; i++){
			phenotype[i] = genotype[i] ? 1 : 0;
		}
	}

	@Override
	public void crossOver(Individual individual)
	{
		// TODO Auto-generated method stub
		
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

	@Override
	public void calcFitness()
	{
		int numOnes = 0;
		for(int i = 0; i < phenotype.length; i++){
			if(phenotype[i] == 1){
				numOnes++;
			}
		}
		this.setFitness(numOnes/phenotype.length);
	}

	@Override
	public ArrayList<Individual> generateChildren(Individual individual)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
