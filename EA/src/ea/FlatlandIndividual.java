package ea;

import java.util.Random;

import surprisingsequenses.SurprisingSequensesIndividual;
import flatland.Flatland;
import model.Parameters;

public class FlatlandIndividual extends BinaryVectorIndividual{

	@Override
	public Individual crossOver(Individual individual) {
		return new FlatlandIndividual(runCrossOver(((FlatlandIndividual)individual).getGenotype()));
	}

	private FlatlandIndividual(boolean[] genotype){
		this.genotype = genotype;
	}
	@Override
	public Individual clone() {
		boolean[] newGenotype = new boolean[genotype.length];
		//Not sure if this is necessary, doing it anyway
		for(int i = 0; i < genotype.length; i++){
			newGenotype[i] = this.genotype[i];
		}
		return new FlatlandIndividual(newGenotype);
	}

	@Override
	public void calcFitness() {
		Flatland flatland = new Flatland(this.phenotype);
		this.setFitness(flatland.run());
		//System.out.println("Fitness: " + this.getFitness());
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

	public static FlatlandIndividual generateRandomIndividual(){
		boolean[] newGenotype = new boolean[Parameters.VECTOR_LENGTH*Parameters.FL_BITSIZE];
		Random r = new Random();
		for(int i = 0; i < newGenotype.length; i++){
			newGenotype[i] = r.nextBoolean();
		}
		//util.Utilities.printBoolArray(newGenotype);
		return new FlatlandIndividual(newGenotype);
	}
}
