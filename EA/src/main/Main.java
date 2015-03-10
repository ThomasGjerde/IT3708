package main;

import java.util.ArrayList;

import onemax.OneMaxIndividual;

import model.Individual;
import model.Parameters;

public class Main
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		ArrayList<Individual> individuals = new ArrayList<Individual>();
		for(int i = 0; i < Parameters.POPULATION_SIZE; i++){
			individuals.add(OneMaxIndividual.generateRandomIndividual());
		}
		EA ea = new EA();
		OneMaxIndividual result = (OneMaxIndividual)ea.run(individuals);
		if(result != null){
			System.out.println("Most fit: " + result.getGenotype().toString());
		}
	}

}
