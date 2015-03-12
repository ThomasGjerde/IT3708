package surprisingsequenses;

import java.util.ArrayList;
import java.util.Random;

import model.BinaryVectorIndividual;
import model.Individual;
import model.Parameters;
import model.StringIntPair;
import model.SurprisingSequenceType;

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
		if(Parameters.SURPRISING_SEQUENCE_TYPE == SurprisingSequenceType.LOCALLY){
			ArrayList<String> substrings = new ArrayList<String>();
			for(int i = 0; i < phenotype.length-1; i++){
				substrings.add(phenotype[i] + "" + phenotype[i+1]);
			}
			int errors = 0;
			for(String s : substrings){
				for(String s2 : substrings){
					if(s != s2 && s.equals(s2)){
						errors++;
					}
				}
			}
			if(errors != 0){
				errors = errors / 2; //Due to symmetric errors checking

				this.setFitness((double)1 / (double)(errors+1));
			}else{
				this.setFitness(1.0);
			}
			
		}else{
			ArrayList<StringIntPair> stringList = new ArrayList<StringIntPair>();
			for(int i = 0; i < phenotype.length-1; i++){
				for(int j = i + 1; j < phenotype.length; j++){
					stringList.add(new StringIntPair(phenotype[i] + "" + phenotype[j], j-i-1));
				}
			}
			int errors = 0;
			for(StringIntPair pair1 : stringList){
				for(StringIntPair pair2 : stringList){
					if(pair1 != pair2 && pair1.getString().equals(pair2.getString()) && pair1.getInteger() == pair2.getInteger()){
						errors++;
					}
				}
			}
			if(errors != 0){
				errors = errors / 2; //Due to symmetric errors checking
				this.setFitness((double)1 / (double)(errors+1));
			}else{
				this.setFitness(1.0);
			}
		}

		
		
		
		
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
