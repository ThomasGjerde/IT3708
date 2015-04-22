package model;

import ann.NeuronType;

public class Parameters
{
	public static int GENERATIONS = 100;
	
	public static int VECTOR_LENGTH = 47;
	public static double MUTATION_RATE = 0.1;
	public static double CROSSOVER_RATE = 0.7;
	public static boolean TWO_POINT_CROSSOVER = false;
	public static int POPULATION_SIZE = 50;
	public static int PARENT_PAIRS = 75;
	public static AdultSelectionMode ADULT_SELECTION_MODE = AdultSelectionMode.OVER_PRODUCTION;
	public static double TARGET_FITNESS = 1.1;
	public static ParentSelectionMode PARENT_SELECTION_MODE = ParentSelectionMode.TOURNAMENT_SELECTION;
	public static int TOURNAMENT_SIZE = 4;
	public static double TOURNAMENT_SELECTION_PROBABILITY = 0.8;
	public static int LOLZ_CUTOFF = 4;	
	public static ProblemType PROBLEM_TYPE = ProblemType.BEERTRACKER;
	public static int SYMBOL_SIZE = 20;
	public static SurprisingSequenceType SURPRISING_SEQUENCE_TYPE = SurprisingSequenceType.GLOBALLY;
	public static int FL_INPUT_NEURONS = 6;
	public static int FL_OUTPUT_NEURONS = 3;
	public static double FL_BIAS = 0;
	public static double FL_RAMP_UPPER = 0.8;
	public static double FL_RAMP_LOWER = 0.2;
	public static int FL_TIMESTEPS = 600;
	public static double FL_SIGMOID_SCALING = 1.0;
	public static double FL_STEP_THRESHOLD = 0.5;
	public static boolean FL_STATIC_BOARD = true;
	public static NeuronType FL_ACTIVE_FUNC = NeuronType.LINEAR;
	public static int FL_MAPSIZE = 10;
	public static int FL_BITSIZE = 8;
	public static int ANN_RUNS = 10;
	public static int BT_INPUT_NEURONS = 5;
	public static int BT_OUTPUT_NEURONS = 9;
	public static int BT_SIZE_X = 30;
	public static int BT_SIZE_Y = 15;
	public static int DELAY = 50;
	
	public static void setParameter(String input){
		String[] data = input.split("=");
		if(data.length != 2){
			System.out.println("Malformed input");
			System.exit(1);
		}
		if(data[0].toUpperCase().equals("VECTOR_LENGTH")){
			VECTOR_LENGTH = Integer.parseInt(data[1]);
			System.out.println("VECTOR_LENGTH Set");
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
		}else if(data[0].toUpperCase().equals("LOLZ_CUTOFF")){
			LOLZ_CUTOFF = Integer.parseInt(data[1]);
			System.out.println("LOLZ_CUTOFF Set");
		}else if(data[0].toUpperCase().equals("PROBLEM_TYPE")){
			if(data[1].toUpperCase().equals("ONE_MAX")){
				PROBLEM_TYPE = ProblemType.ONE_MAX;
				System.out.println("ONE_MAX Set");
			}else if(data[1].toUpperCase().equals("LOLZ")){
				PROBLEM_TYPE = ProblemType.LOLZ;
				System.out.println("LOLZ");
			}else if(data[1].toUpperCase().equals("SURPRISING_SEQUENCES")){
				PROBLEM_TYPE = ProblemType.SURPRISING_SEQUENCES;
				System.out.println("SURPRISING_SEQUENCES Set");
			}else if(data[1].toUpperCase().equals("FLATLAND")){
				PROBLEM_TYPE = ProblemType.FLATLAND;
				System.out.println("FLATLAND Set");
			}
		}else if(data[0].toUpperCase().equals("SYMBOL_SIZE")){
			SYMBOL_SIZE = Integer.parseInt(data[1]);
			System.out.println("SYMBOL_SIZE Set");
		}else if(data[0].toUpperCase().equals("SURPRISING_SEQUENCE_TYPE")){
			if(data[1].toUpperCase().equals("GLOBALLY")){
				SURPRISING_SEQUENCE_TYPE = SurprisingSequenceType.GLOBALLY;
				System.out.println("GLOBALLY Set");
			}else if(data[1].toUpperCase().equals("LOCALLY")){
				SURPRISING_SEQUENCE_TYPE = SurprisingSequenceType.LOCALLY;
				System.out.println("LOCALLY Set");
			}
		}else if(data[0].toUpperCase().equals("FL_INPUT_NEURONS")){
			FL_INPUT_NEURONS = Integer.parseInt(data[1]);
			System.out.println("FL_INPUT_NEURONS Set");
		}else if(data[0].toUpperCase().equals("FL_OUTPUT_NEURONS")){
			FL_OUTPUT_NEURONS = Integer.parseInt(data[1]);
			System.out.println("FL_OUTPUT_NEURONS Set");
		}else if(data[0].toUpperCase().equals("FL_BIAS")){
			FL_BIAS = Double.parseDouble(data[1]);
			System.out.println("FL_BIAS Set");
		}else if(data[0].toUpperCase().equals("FL_RAMP_UPPER")){
			FL_RAMP_UPPER = Double.parseDouble(data[1]);
			System.out.println("FL_RAMP_UPPER Set");
		}else if(data[0].toUpperCase().equals("FL_RAMP_LOWER")){
			FL_RAMP_LOWER = Double.parseDouble(data[1]);
			System.out.println("FL_RAMP_LOWER Set");
		}else if(data[0].toUpperCase().equals("FL_TIMESTEPS")){
			FL_TIMESTEPS = Integer.parseInt(data[1]);
			System.out.println("FL_TIMESTEPS Set");
		}else if(data[0].toUpperCase().equals("FL_SIGMOID_SCALING")){
			FL_SIGMOID_SCALING = Double.parseDouble(data[1]);
			System.out.println("FL_SIGMOID_SCALING Set");
		}else if(data[0].toUpperCase().equals("FL_STEP_THRESHOLD")){
			FL_STEP_THRESHOLD = Double.parseDouble(data[1]);
			System.out.println("FL_STEP_THRESHOLD Set");
		}else if(data[0].toUpperCase().equals("FL_STATIC_BOARD")){
			FL_STATIC_BOARD = Boolean.parseBoolean(data[1]);
			System.out.println("FL_STATIC_BOARD Set");
		}else if(data[0].toUpperCase().equals("FL_ACTIVE_FUNC")){
			if(data[1].toUpperCase().equals("LINEAR")){
				FL_ACTIVE_FUNC = NeuronType.LINEAR;
				System.out.println("LINEAR Set");
			}else if(data[1].toUpperCase().equals("STEP")){
				FL_ACTIVE_FUNC = NeuronType.STEP;
				System.out.println("STEP Set");
			}else if(data[1].toUpperCase().equals("RAMP")){
				FL_ACTIVE_FUNC = NeuronType.RAMP;
				System.out.println("RAMP Set");
			}else if(data[1].toUpperCase().equals("SIGMOID")){
				FL_ACTIVE_FUNC = NeuronType.SIGMOID;
				System.out.println("SIGMOID Set");
			}else if(data[1].toUpperCase().equals("HYPERBOLIC_TANGENT")){
				FL_ACTIVE_FUNC = NeuronType.HYPERBOLIC_TANGENT;
				System.out.println("HYPERBOLIC_TANGENT Set");
			}
		}else if(data[0].toUpperCase().equals("FL_MAPSIZE")){
			FL_MAPSIZE = Integer.parseInt(data[1]);
			System.out.println("FL_MAPSIZE Set");
		}else if(data[0].toUpperCase().equals("FL_BITSIZE")){
			FL_BITSIZE = Integer.parseInt(data[1]);
			System.out.println("FL_BITSIZE Set");
		}
		
	}
	
}
