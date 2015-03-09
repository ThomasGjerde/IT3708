package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Population
{
	ArrayList<Individual> individuals = new ArrayList<Individual>();
	ArrayList<Individual> nextGeneration = new ArrayList<Individual>();
	double populationFitness = 0;
	double highestFitness = 0;
	Individual mostFitIndividual;
	public Population(ArrayList<Individual> individuals){
		this.individuals = individuals;
	}
	public void evolve(){
		nextGeneration.clear();
		//Reproduce
		selectAdults();
	}
	private void reproduce(){
		
	}
	public void developAll(){
		for(Individual individ : individuals){
			individ.develop();
		}
	}
	public void calcPopulationFitness(){
		int popFit = 0;
		for(Individual individ : individuals){
			individ.calcFitness();
			popFit += individ.getFitness();
			if(individ.getFitness() >= popFit){
				highestFitness = individ.getFitness();
				mostFitIndividual = individ;
			}
		}
		popFit = popFit / individuals.size();
	}
	private void selectAdults(){
		if(Parameters.ADULT_SELECTION_MODE == AdultSelectionMode.FULL){
			selectFull();
		}else if(Parameters.ADULT_SELECTION_MODE == AdultSelectionMode.OVER_PRODUCTION){
			selectOverProduction();
		}else if(Parameters.ADULT_SELECTION_MODE == AdultSelectionMode.MIXING){
			selectMixing();
		}
	}
	/*
	 * Start adult selections
	 */
	private void selectFull(){
		individuals.clear();
		individuals.addAll(nextGeneration);
	}
	private void selectOverProduction(){
		individuals.clear();
		Collections.sort(nextGeneration, new Comparator<Individual>(){
		     public int compare(Individual o1, Individual o2){
		    	 if(o1.getFitness() > o2.getFitness()){
		    		 return 1;
		    	 }else if(o1.getFitness() < o2.getFitness()){
		    		 return -1;
		    	 }else{
		    		 return 0;
		    	 }
		     }
		});
		for(int i = 0; i < Parameters.POPULATION_SIZE; i++){
			individuals.add(nextGeneration.get(i));
		}
	}
	private void selectMixing(){
		nextGeneration.addAll(individuals);
		selectOverProduction();
	}
	/*
	 * End adult selections
	 */
	
	/*
	 * Start parent selections
	 */
	private void selectTournament(){
		
	}
}
