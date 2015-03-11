package model;

public class Parameters
{
	public static int GENERATIONS = 200;
	
	public static int ONE_MAX_VECTOR_LENGTH = 40;
	public static double MUTATION_RATE = 0.01;
	public static double CROSSOVER_RATE = 0.7;
	public static boolean TWO_POINT_CROSSOVER = false;
	public static int POPULATION_SIZE = 50;
	public static int PARENT_PAIRS = 30;
	public static AdultSelectionMode ADULT_SELECTION_MODE = AdultSelectionMode.OVER_PRODUCTION;
	public static double TARGET_FITNESS = 1;
	
	/*
	 * Parent selections
	 */
	public static ParentSelectionMode PARENT_SELECTION_MODE = ParentSelectionMode.SIGMA_SCALING;
	
	public static int TOURNAMENT_SIZE = 4;
	public static double TOURNAMENT_SELECTION_PROBABILITY = 0.8;
	
	
	
	
	public static void setParameter(String input){
		String[] data = input.split("=");
		if(data.length != 2){
			System.out.println("Malformed input");
			System.exit(1);
		}
		if(data[0].toUpperCase().equals("ONE_MAX_VECTOR_LENGTH")){
			ONE_MAX_VECTOR_LENGTH = Integer.parseInt(data[1]);
			System.out.println("ONE_MAX_VECTOR_LENGTH Set");
		}else if(data[0].toUpperCase().equals("GENERATIONS")){
			GENERATIONS = Integer.parseInt(data[1]);
			System.out.println("GENERATIONS Set");
		}else if(data[0].toUpperCase().equals("MUTATION_RATE")){
			MUTATION_RATE = Double.parseDouble(data[1]);
			System.out.println("MUTATION_RATE Set");
		}else if(data[0].toUpperCase().equals("CROSSOVER_RATE")){
			CROSSOVER_RATE = Double.parseDouble(data[1]);
			System.out.println("CROSSOVER_RATE Set");
		}else if(data[0].toUpperCase().equals("TWO_POINT_CROSSOVER")){
			TWO_POINT_CROSSOVER = Boolean.parseBoolean(data[1]);
			System.out.println("TWO_POINT_CROSSOVER Set");
		}else if(data[0].toUpperCase().equals("POPULATION_SIZE")){
			POPULATION_SIZE = Integer.parseInt(data[1]);
			System.out.println("POPULATION_SIZE Set");
		}else if(data[0].toUpperCase().equals("PARENT_PAIRS")){
			PARENT_PAIRS = Integer.parseInt(data[1]);
			System.out.println("PARENT_PAIRS Set");
		}else if(data[0].toUpperCase().equals("ADULT_SELECTION_MODE")){
			if(data[1].toUpperCase().equals("FULL")){
				ADULT_SELECTION_MODE = AdultSelectionMode.FULL;
				System.out.println("ADULT_SELECTION_MODE Set");
			}else if(data[1].toUpperCase().equals("OVER_PRODUCTION")){
				ADULT_SELECTION_MODE = AdultSelectionMode.OVER_PRODUCTION;
				System.out.println("ADULT_SELECTION_MODE Set");
			}else if(data[1].toUpperCase().equals("MIXING")){
				ADULT_SELECTION_MODE = AdultSelectionMode.MIXING;
				System.out.println("ADULT_SELECTION_MODE Set");
			}
		}else if(data[0].toUpperCase().equals("TARGET_FITNESS")){
			TARGET_FITNESS = Double.parseDouble(data[1]);
			System.out.println("TARGET_FITNESS Set");
		}else if(data[0].toUpperCase().equals("PARENT_SELECTION_MODE")){
			if(data[1].toUpperCase().equals("TOURNAMENT_SELECTION")){
				PARENT_SELECTION_MODE = ParentSelectionMode.TOURNAMENT_SELECTION;
				System.out.println("PARENT_SELECTION_MODE Set");
			}else if(data[1].toUpperCase().equals("FITNESS_PROPORTIONATE")){
				PARENT_SELECTION_MODE = ParentSelectionMode.FITNESS_PROPORTIONATE;
				System.out.println("PARENT_SELECTION_MODE Set");
			}else if(data[1].toUpperCase().equals("SIGMA_SCALING")){
				PARENT_SELECTION_MODE = ParentSelectionMode.SIGMA_SCALING;
				System.out.println("PARENT_SELECTION_MODE Set");
			}else if(data[1].toUpperCase().equals("STOCHASTIC_UNIFORM")){
				PARENT_SELECTION_MODE = ParentSelectionMode.STOCHASTIC_UNIFORM;
				System.out.println("PARENT_SELECTION_MODE Set");
			}
		}else if(data[0].toUpperCase().equals("TOURNAMENT_SIZE")){
			TOURNAMENT_SIZE = Integer.parseInt(data[1]);
			System.out.println("TOURNAMENT_SIZE Set");
		}else if(data[0].toUpperCase().equals("TOURNAMENT_SELECTION_PROBABILITY")){
			TOURNAMENT_SELECTION_PROBABILITY = Double.parseDouble(data[1]);
			System.out.println("TOURNAMENT_SELECTION_PROBABILITY Set");
		}
		
	}
	
}
