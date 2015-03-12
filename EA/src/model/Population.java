package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;


public class Population
{
	ArrayList<Individual> individuals = new ArrayList<Individual>();
	ArrayList<Individual> nextGeneration = new ArrayList<Individual>();
	double populationFitness = 0;
	double highestFitness = 0;
	double sd = 0;
	int currentGen = 0;
	Individual mostFitIndividual;
	public Population(ArrayList<Individual> individuals){
		nextGeneration.addAll(individuals);
	}
	public GenerationInfo evolve(){
		currentGen++;
		developAll();
		calcAllFitness();
		selectAdults();
		calcGenerationInfo();
		reproduce();
		mutateAll();
		outputLog();
		return new GenerationInfo(currentGen, highestFitness, populationFitness, sd);
	}
	private void mutateAll(){
		for(Individual individ : nextGeneration){
			individ.mutate();
		}
	}
	private void developAll(){
		for(Individual individ : nextGeneration){
			individ.develop();
		}
	}
	private void calcAllFitness(){
		for(Individual individ : nextGeneration){
			individ.calcFitness();
		}
	}
	private void calcGenerationInfo(){
		double popFit = 0;
		for(Individual individ : individuals){
			individ.calcFitness();
			popFit += individ.getFitness();
			if(individ.getFitness() > highestFitness){
				highestFitness = individ.getFitness();
				mostFitIndividual = individ;
				//System.out.println("Most fit: " + highestFitness + " Gen: " + currentGen);
				//Utilities.printBoolArray(((OneMaxIndividual)mostFitIndividual).getGenotype());
			}
		}
		popFit = popFit / (double)individuals.size();
		populationFitness = popFit;
		calcSD();
	}
	private void reproduce(){
		ArrayList<IndividualPair> parents = selectParents();
		nextGeneration.clear();
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
		nextGeneration.clear();
	}
	private void selectOverProduction(){
		individuals.clear();
		nextGeneration = sortIndividualList(nextGeneration);
		for(int i = 0; i < Parameters.POPULATION_SIZE; i++){
			individuals.add(nextGeneration.get(i));
		}
		nextGeneration.clear();
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
		if(Parameters.PARENT_SELECTION_MODE == ParentSelectionMode.TOURNAMENT_SELECTION){
			return selectTournament();
		}else if(Parameters.PARENT_SELECTION_MODE == ParentSelectionMode.FITNESS_PROPORTIONATE){
			return selectFitnessProportionate();
		}else if(Parameters.PARENT_SELECTION_MODE == ParentSelectionMode.SIGMA_SCALING){
			return selectSigmaScaling();
		}else if(Parameters.PARENT_SELECTION_MODE == ParentSelectionMode.STOCHASTIC_UNIFORM){
			return selectStochasticUniform();
		}
		
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
			currentTournament.add(possibleContestants.remove(rand.nextInt(possibleContestants.size())));
		}
		if(Math.random() < Parameters.TOURNAMENT_SELECTION_PROBABILITY){ //puts best on top if under probability
			currentTournament = sortIndividualList(currentTournament);
		}
		return currentTournament.get(0);
	}
	
	private ArrayList<IndividualPair> selectFitnessProportionate(){
		ArrayList<IndividualPair> pairList = new ArrayList<IndividualPair>();
		while(pairList.size() < Parameters.PARENT_PAIRS){
			Individual parent1 = runRoulette(individuals,false);
			Individual parent2 = runRoulette(individuals,false);
			while(parent1 == parent2){
				parent2 = runRoulette(individuals,false);
			}
			pairList.add(new IndividualPair(parent1, parent2));
		}
		return pairList;
	}
	private Individual runRoulette(ArrayList<Individual> list, boolean sigmaMode){
		double totalFitness = 0;
		double localAvg = 0;
		double localSd = 0;
		int currentPos = 0;
		HashMap<Integer,Individual> contestants = new HashMap<Integer, Individual>();
		for(Individual individ : list){
			totalFitness += individ.getFitness();
		}
		localAvg = totalFitness / list.size();
		double sdTotal = 0;
		for(Individual individ : list){
			sdTotal += (individ.getFitness() - localAvg) * (individ.getFitness() - localAvg);
		}
		sdTotal = sdTotal / (double)list.size();
		sd = Math.sqrt(sdTotal);
		for(Individual individ : list){
			int places;
			if(sigmaMode){
				places = (int)((sigmaScaleValue(individ.getFitness(),localSd,localAvg) / totalFitness)*1000);
			}else{
				places = (int)((individ.getFitness() / totalFitness)*1000);
			}
			
			for(int i = 0; i < places; i++){
				contestants.put(currentPos,individ);
				currentPos++;
			}
		}
		Random rand = new Random();
		int pos = rand.nextInt(1001);
		while(!contestants.containsKey(pos)){
			pos = rand.nextInt(1001);
		}
		return contestants.get(pos);
	}
	private double sigmaScaleValue(double value, double sd, double avg){
		if(sd != 0){
			return 1 + ((value - avg)/(2*sd));
		}else{
			return 1;
		}
	}
	private ArrayList<IndividualPair> selectSigmaScaling(){
		ArrayList<IndividualPair> pairList = new ArrayList<IndividualPair>();
		while(pairList.size() < Parameters.PARENT_PAIRS){
			ArrayList<Individual> list = new ArrayList<Individual>(individuals);
			Individual parent1 = runRoulette(list,true);
			list.remove(parent1);
			Individual parent2 = runRoulette(list,true);
			while(parent1 == parent2){
				parent2 = runRoulette(list,true);
			}
			pairList.add(new IndividualPair(parent1, parent2));
		}
		return pairList;
	}
	private ArrayList<IndividualPair> selectStochasticUniform(){
		ArrayList<IndividualPair> pairList = new ArrayList<IndividualPair>();
		while(pairList.size() < Parameters.PARENT_PAIRS){
			Individual parent1 = randomSelect();
			Individual parent2 = randomSelect();
			while(parent1 == parent2){
				parent2 = randomSelect();
			}
			pairList.add(new IndividualPair(parent1, parent2));
		}
		return pairList;
	}
	private Individual randomSelect(){
		Random rand = new Random();
		return individuals.get(rand.nextInt(individuals.size()));
	}
	/*
	 * End parent selections
	 */
	
	/*
	 * Start util methods
	 */
	
	private ArrayList<Individual> sortIndividualList(ArrayList<Individual> list){
		Collections.sort(list, new Comparator<Individual>(){
		     public int compare(Individual o1, Individual o2){
		    	 if(o1.getFitness() > o2.getFitness()){
		    		 return -1;
		    	 }else if(o1.getFitness() < o2.getFitness()){
		    		 return 1;
		    	 }else{
		    		 return 0;
		    	 }
		     }
		});
		return list;
	}
	private void calcSD(){
		double total = 0;
		for(Individual individ : individuals){
			total += (individ.getFitness() - populationFitness) * (individ.getFitness() - populationFitness);
		}
		total = total / (double)individuals.size();
		sd = Math.sqrt(total);
	}
	private void outputLog(){
		System.out.println("----------------------------------------------------");
		System.out.println("Current gen: " + currentGen);
		System.out.println("Highest fitness: " + highestFitness);
		System.out.println("SD: " + sd);
		System.out.println("Best individual: " + mostFitIndividual.toString());
		System.out.println("----------------------------------------------------");
	}
	/*
	 * End util methods
	 */
}
