package onemax;
import java.util.Random;

import util.Utilities;
import model.Individual;
import model.Parameters;

public class OneMaxIndividual extends Individual
{
	private boolean[] genotype = new boolean[Parameters.ONE_MAX_VECTOR_LENGTH]; //Possibly unnecessary
	public boolean[] getGenotype()
	{
		return genotype;
	}
	public void setGenotype(boolean[] genotype)
	{
		this.genotype = genotype;
	}

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
	public Individual crossOver(Individual individual)
	{
		Random rand = new Random();
		boolean[] newGenotype = new boolean[Parameters.ONE_MAX_VECTOR_LENGTH];
		//System.out.println("Parent1:");
		//Utilities.printBoolArray(this.genotype);
		//System.out.println("Parent2:");
		//Utilities.printBoolArray(((OneMaxIndividual)individual).getGenotype());
		if(Parameters.TWO_POINT_CROSSOVER){
			int crossAt1 = 1 + rand.nextInt(Parameters.ONE_MAX_VECTOR_LENGTH - 2); //Check this
			int crossAt2 = crossAt1 + rand.nextInt((Parameters.ONE_MAX_VECTOR_LENGTH - 1) - crossAt1); //and this
			for(int i = 0; i < crossAt1; i++){
				newGenotype[i] = this.genotype[i];
			}
			for(int i = crossAt1; i < crossAt2; i++){
				newGenotype[i] = ((OneMaxIndividual)individual).getGenotype()[i];
			}
			for(int i = crossAt2; i < Parameters.ONE_MAX_VECTOR_LENGTH; i++){
				newGenotype[i] = this.genotype[i];
			}
		}else{
			int crossAt = 1 + rand.nextInt(Parameters.ONE_MAX_VECTOR_LENGTH - 1); //Check this
			for(int i = 0; i < crossAt; i++){
				newGenotype[i] = this.genotype[i];
			}
			for(int i = crossAt; i < Parameters.ONE_MAX_VECTOR_LENGTH; i++){
				newGenotype[i] = ((OneMaxIndividual)individual).getGenotype()[i];
			}
		}
		//System.out.println("Child:");
		//Utilities.printBoolArray(newGenotype);
		return new OneMaxIndividual(newGenotype);
		
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
		this.setFitness(((double)numOnes)/phenotype.length);
	}
	@Override
	public Individual clone()
	{
		boolean[] newGenotype = new boolean[Parameters.ONE_MAX_VECTOR_LENGTH];
		//Not sure if this is necessary, doing it anyway
		for(int i = 0; i < Parameters.ONE_MAX_VECTOR_LENGTH; i++){
			newGenotype[i] = this.genotype[i];
		}
		return new OneMaxIndividual(newGenotype);
	}
	@Override
	public String toString()
	{
		String s = "[";
		for(int i : phenotype){
			s += i;
			s += ",";
		}
		if(s.length() > 0){
			s = s.substring(0,s.length() -1);
		}
		s += "]";
		return s;
	}
}
