package twentyfortyeigth;

import java.util.Random;
import java.util.concurrent.ExecutionException;

import onemax.OneMaxIndividual;

import model.Parameters;
import ea.BinaryVectorIndividual;
import ea.FlatlandIndividual;
import ea.Individual;

public class TFEIndividual extends BinaryVectorIndividual{

	public TFEIndividual(boolean[] genotype){
		this.genotype = genotype;
	}
	@Override
	public Individual crossOver(Individual individual) {
		boolean[] newGenotype = runCrossOver(((TFEIndividual)individual).getGenotype());
		return new TFEIndividual(newGenotype);
	}

	@Override
	public Individual clone() {
		boolean[] newGenotype = new boolean[genotype.length];
		//Not sure if this is necessary, doing it anyway
		for(int i = 0; i < genotype.length; i++){
			newGenotype[i] = this.genotype[i];
		}
		return new TFEIndividual(newGenotype);
	}

	@Override
	public void calcFitness() {
		TFE tfe = new TFE(4096, this.phenotype[0], this.phenotype[1], this.phenotype[2], this.phenotype[3], this.phenotype[4]);
		try {
			setFitness(tfe.runMultiThreadedTest(15));
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			setFitness(0);
		}
		tfe = null; //Attempt to dispose
	}

	@Override
	public void develop() {
		this.phenotype = new double[Parameters.VECTOR_LENGTH];
		for(int i = 0; i < this.genotype.length; i++){
			if(this.genotype[i] == true){
				this.phenotype[i/8] += 1.0/Parameters.FL_BITSIZE;
			}
		}
	}
	public static TFEIndividual generateRandomIndividual(){
		boolean[] newGenotype = new boolean[Parameters.VECTOR_LENGTH*Parameters.FL_BITSIZE];
		Random r = new Random();
		for(int i = 0; i < newGenotype.length; i++){
			newGenotype[i] = r.nextBoolean();
		}
		//util.Utilities.printBoolArray(newGenotype);
		return new TFEIndividual(newGenotype);
	}

}
