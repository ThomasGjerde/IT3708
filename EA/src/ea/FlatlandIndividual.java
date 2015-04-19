package ea;

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
		boolean[] newGenotype = new boolean[Parameters.VECTOR_LENGTH];
		//Not sure if this is necessary, doing it anyway
		for(int i = 0; i < Parameters.VECTOR_LENGTH; i++){
			newGenotype[i] = this.genotype[i];
		}
		return new FlatlandIndividual(newGenotype);
	}

	@Override
	public void calcFitness() {
		Flatland flatland = new Flatland(this.phenotype);
		this.setFitness(flatland.run());
	}
}
