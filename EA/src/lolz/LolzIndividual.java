package lolz;

import java.util.Random;

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

	public static LolzIndividual generateRandomIndividual(){
		boolean[] newGenotype = new boolean[Parameters.VECTOR_LENGTH];
		Random r = new Random();
		for(int i = 0; i < newGenotype.length; i++){
			newGenotype[i] = r.nextBoolean();
		}
		return new LolzIndividual(newGenotype);
	}
	
	@Override
	public void calcFitness() {
		int numLeading = 0;
		if(phenotype[0] == 0){
			int pos = 0;
			while(phenotype[pos] == 0){
				if(numLeading == Parameters.LOLZ_CUTOFF){
					this.setFitness((double)numLeading/(double)phenotype.length);
					return;
				}
				numLeading++;
				pos++;
			}
		}else{
			int pos = 0;
			while(pos < phenotype.length && phenotype[pos] == 1){
				numLeading++;
				pos++;
			}
			this.setFitness((double)numLeading/(double)phenotype.length);
		}
	}

}
