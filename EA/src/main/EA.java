package main;

import java.util.ArrayList;

import ea.Individual;
import ea.Population;

import onemax.OneMaxIndividual;

import model.GenerationInfo;
import model.Parameters;

public class EA
{
	private ArrayList<GenerationInfo> giList;
	private Population population;
	public ArrayList<GenerationInfo> getGiList()
	{
		return giList;
	}
	public EA(){
		
	}
	public EA(ArrayList<Individual> individuals){
		this.population = new Population(individuals);
		giList = new ArrayList<GenerationInfo>();
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
	public Individual runSingleGeneration(){
		giList.add(population.evolve());
		return population.getMostFit();
	}
}
