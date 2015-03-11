package lolz;

import onemax.OneMaxIndividual;
import model.BinaryVectorIndividual;
import model.Individual;
import model.Parameters;

public class LolzIndividual extends BinaryVectorIndividual{

	@Override
	public Individual crossOver(Individual individual) {
		boolean[] newGenotype = runCrossOver(((LolzIndividual)individual).getGenotype());
		return new LolzIndividual(newGenotype);
	}
	
	private LolzIndividual(boolean[] genotype){
		this.genotype = genotype;
	}
	
	@Override
	public Individual clone() {
		boolean[] newGenotype = new boolean[Parameters.VECTOR_LENGTH];
		//Not sure if this is necessary, doing it anyway
		for(int i = 0; i < Parameters.VECTOR_LENGTH; i++){
			newGenotype[i] = this.genotype[i];
		}
		return new LolzIndividual(newGenotype);
	}

	@Override
	public void calcFitness() {
		// TODO Auto-generated method stub
		
	}

}
