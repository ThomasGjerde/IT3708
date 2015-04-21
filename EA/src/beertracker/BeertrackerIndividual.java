package beertracker;

import java.util.Random;

import model.Parameters;
import ea.BinaryVectorIndividual;
import ea.Individual;

public class BeertrackerIndividual extends BinaryVectorIndividual{

	@Override
	public Individual crossOver(Individual individual) {
		// TODO Auto-generated method stub
		return new BeertrackerIndividual(runCrossOver(((BeertrackerIndividual)individual).getGenotype()));
	}

	private BeertrackerIndividual(boolean[] genotype){
		this.genotype = genotype;
	}
	
	@Override
	public Individual clone() {
		boolean[] newGenotype = new boolean[genotype.length];
		//Not sure if this is necessary, doing it anyway
		for(int i = 0; i < genotype.length; i++){
			newGenotype[i] = this.genotype[i];
		}
		return new BeertrackerIndividual(newGenotype);
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

	public static BeertrackerIndividual generateRandomIndividual(){
		boolean[] newGenotype = new boolean[Parameters.VECTOR_LENGTH*Parameters.FL_BITSIZE];
		Random r = new Random();
		for(int i = 0; i < newGenotype.length; i++){
			newGenotype[i] = r.nextBoolean();
		}
		//util.Utilities.printBoolArray(newGenotype);
		return new BeertrackerIndividual(newGenotype);
	}

	@Override
	public void calcFitness() {
		BeerTracker bt = new BeerTracker(this.phenotype);
		this.setFitness(bt.run());
	}

}
