package model;

public class Parameters
{
	public static int GENERATIONS = 200;
	
	public static int ONE_MAX_VECTOR_LENGTH = 40;
	public static double MUTATION_RATE = 0.01;
	public static double CROSSOVER_RATE = 0.7;
	public static boolean TWO_POINT_CROSSOVER = false;
	public static int POPULATION_SIZE = 50;
	public static int PARENT_PAIRS = 75;
	public static AdultSelectionMode ADULT_SELECTION_MODE = AdultSelectionMode.OVER_PRODUCTION;
	public static double TARGET_FITNESS = 1;
	
	/*
	 * Parent selections
	 */
	public static ParentSelectionMode PARENT_SELECTION_MODE = ParentSelectionMode.FitnessProportionate;
	
	public static int TOURNAMENT_SIZE = 4;
	public static double TOURNAMENT_SELECTION_PROBABILITY = 0.8;
	
}
