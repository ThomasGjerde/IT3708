package model;

public class Parameters
{
	public static int GENERATIONS = 200;
	
	public static int ONE_MAX_VECTOR_LENGTH = 20;
	public static double MUTATION_RATE = 0.2;
	public static double CROSSOVER_RATE = 0.2;
	public static boolean TWO_POINT_CROSSOVER = true;
	public static int POPULATION_SIZE = 20;
	public static int PARENT_PAIRS = 10;
	public static AdultSelectionMode ADULT_SELECTION_MODE = AdultSelectionMode.MIXING;
	public static double TARGET_FITNESS = 1;
	
	/*
	 * Parent selections
	 */
	public static ParentSelectionMode PARENT_SELECTION_MODE = ParentSelectionMode.TournamentSelection;
	
	public static int TOURNAMENT_SIZE = 4;
	public static double TOURNAMENT_SELECTION_PROBABILITY = 0.8;
	
}
