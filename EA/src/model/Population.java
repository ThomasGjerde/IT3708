package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

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
		reproduce();
		selectAdults();
	}
	private void reproduce(){
		ArrayList<IndividualPair> parents = selectParents();
		for(IndividualPair pair : parents){
			if(Math.random() < Parameters.CROSSOVER_RATE){
				nextGeneration.add(pair.getA().crossOver(pair.getB()));
				nextGeneration.add(pair.getB().crossOver(pair.getA()));
			}else{
				nextGeneration.add(pair.getA().clone());
				nextGeneration.add(pair.getB().clone());
			}
		}
	}
	public Individual getMostFit(){
		return mostFitIndividual;
	}
	public boolean solutionFound(){
		if(highestFitness == Parameters.TARGET_FITNESS){
			return true;
		}
		return false;
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
	private ArrayList<Individual> sortIndividualList(ArrayList<Individual> list){
		Collections.sort(list, new Comparator<Individual>(){
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
		return list;
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
		/*
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
		*/
		nextGeneration = sortIndividualList(nextGeneration);
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
	private ArrayList<IndividualPair> selectParents(){
		if(Parameters.PARENT_SELECTION_MODE == ParentSelectionMode.TournamentSelection){
			return selectTournament();
		}
		//Add rest of selections
		
		System.out.println("No Parent selection mode chosen, defaulting to tournament selection");
		return selectTournament();
	}
	private ArrayList<IndividualPair> selectTournament(){
		ArrayList<IndividualPair> parentPairs = new ArrayList<IndividualPair>();		
		while(parentPairs.size() < Parameters.PARENT_PAIRS){
			Individual parent1 = runTournament();
			Individual parent2 = runTournament();
			while(parent1 == parent2){
				parent2 = runTournament();
			}
			parentPairs.add(new IndividualPair(parent1, parent2));
		}
		return parentPairs;
	}
	private Individual runTournament(){
		Random rand = new Random();
		ArrayList<Individual> currentTournament = new ArrayList<Individual>();
		ArrayList<Individual> possibleContestants = new ArrayList<Individual>(individuals);
		for(int i = 0; i < Parameters.TOURNAMENT_SIZE; i++){
			currentTournament.add(possibleContestants.remove(rand.nextInt(possibleContestants.size()))); //Pop
		}
		currentTournament = sortIndividualList(currentTournament);
		return currentTournament.get(0);
	}
	
	/*
	 * End parent selections
	 */
}
