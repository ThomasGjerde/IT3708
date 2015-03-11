package main;

import java.util.ArrayList;

import onemax.OneMaxIndividual;

import model.GenerationInfo;
import model.Individual;
import model.Parameters;
import model.Population;

public class EA
{
	private ArrayList<GenerationInfo> giList;
	public ArrayList<GenerationInfo> getGiList()
	{
		return giList;
	}
	public EA(){
		
	}
	public Individual run(ArrayList<Individual> individuals){
		giList = new ArrayList<GenerationInfo>();
		Population pop = new Population(individuals);
		for(int i = 0; i < Parameters.GENERATIONS; i++){
			giList.add(pop.evolve());
			if(pop.solutionFound()){
				System.out.println("Win");
				return pop.getMostFit();
			}
		}
		return null;
	}
}
