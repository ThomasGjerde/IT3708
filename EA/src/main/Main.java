package main;

import java.util.ArrayList;
import java.util.Random;

import org.jfree.ui.RefineryUtilities;

import onemax.OneMaxIndividual;
import plot.BarChart_AWT;
import util.Utilities;

import lolz.LolzIndividual;
import model.GenerationInfo;
import model.Individual;
import model.Parameters;
import model.ProblemType;

public class Main
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		//Set parameters
		for(String s : args){
			Parameters.setParameter(s);
		}
		
		
		//Generate initial population
		ArrayList<Individual> individuals = new ArrayList<Individual>();
		for(int i = 0; i < Parameters.POPULATION_SIZE; i++){
			if(Parameters.PROBLEM_TYPE == ProblemType.ONE_MAX){
				individuals.add(OneMaxIndividual.generateRandomIndividual());
			}else if(Parameters.PROBLEM_TYPE == ProblemType.LOLZ){
				individuals.add(LolzIndividual.generateRandomIndividual());
			}
			
		}
		EA ea = new EA();
		Individual result = ea.run(individuals);
		if(result != null){
			//Utilities.printBoolArray(result.getGenotype());
		}else{
			System.out.println("No solution");
		}
		
	      BarChart_AWT chart = new BarChart_AWT(ea.getGiList());
	      chart.pack( );        
	      RefineryUtilities.centerFrameOnScreen( chart );        
	      chart.setVisible( true ); 
	}

}
