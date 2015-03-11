package surprisingsequenses;

import java.util.Random;

import model.BinaryVectorIndividual;
import model.Individual;
import model.Parameters;

public class SurprisingSequensesIndividual extends BinaryVectorIndividual
{

	private SurprisingSequensesIndividual(boolean[] genotype){
		this.genotype = genotype;
	}
	@Override
	public Individual crossOver(Individual individual)
	{
		boolean[] newGenotype = runCrossOver(((SurprisingSequensesIndividual)individual).getGenotype());
		return new SurprisingSequensesIndividual(newGenotype);
	}
	public static SurprisingSequensesIndividual generateRandomIndividual(){
		boolean[] newGenotype = new boolean[Parameters.VECTOR_LENGTH*Parameters.SYMBOL_SIZE];
		Random r = new Random();
		for(int i = 0; i < newGenotype.length; i++){
			newGenotype[i] = r.nextBoolean();
		}
		return new SurprisingSequensesIndividual(newGenotype);
	}
	@Override
	public Individual clone()
	{
		boolean[] newGenotype = new boolean[this.genotype.length];
		//Not sure if this is necessary, doing it anyway
		for(int i = 0; i < this.genotype.length; i++){
			newGenotype[i] = this.genotype[i];
		}
		return new SurprisingSequensesIndividual(newGenotype);
	}

	@Override
	public void calcFitness()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void develop()
	{
		for(int i = 0; i < Parameters.VECTOR_LENGTH; i++){
			int ones = 0;
			for(int j = 0; j < Parameters.SYMBOL_SIZE; j++){
				if(genotype[(i*Parameters.SYMBOL_SIZE) + j] == true){
					ones++;
				}
			}
			phenotype[i] = ones;
		}
	}

}
