package main;

import java.util.ArrayList;

import onemax.OneMaxIndividual;

import model.Individual;
import model.Parameters;
import model.Population;

public class EA
{
	public EA(){
		
	}
	public Individual run(ArrayList<Individual> individuals){

		Population pop = new Population(individuals);
		for(int i = 0; i < Parameters.GENERATIONS; i++){
			pop.evolve();
			if(pop.solutionFound()){
				System.out.println("Win");
				return pop.getMostFit();
			}
		}
		return null;
	}
}
