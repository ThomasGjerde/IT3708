package main;

import java.util.ArrayList;
import java.util.Random;

import org.jfree.ui.RefineryUtilities;

import ea.FlatlandIndividual;
import ea.Individual;

import onemax.OneMaxIndividual;
import plot.BarChart_AWT;
import surprisingsequenses.SurprisingSequensesIndividual;
import util.Utilities;

import lolz.LolzIndividual;
import model.GenerationInfo;
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
		ArrayList<Individual> individuals = generateIndividuals();
		EA ea = new EA();
		if(Parameters.PROBLEM_TYPE == ProblemType.LOLZ || Parameters.PROBLEM_TYPE == ProblemType.ONE_MAX){
			ea.run(individuals);
			showGraph(ea.getGiList());
		}else if(Parameters.PROBLEM_TYPE == ProblemType.SURPRISING_SEQUENCES){
			Individual result = ea.run(individuals);
			Individual lastResult = null;
			ArrayList<GenerationInfo> giList = new ArrayList<GenerationInfo>();
			while(result != null){
				lastResult = result;
				giList = ea.getGiList();
				Parameters.VECTOR_LENGTH++;
				System.out.println("Trying length: " + Parameters.VECTOR_LENGTH);
				result = ea.run(generateIndividuals());
			}
			showGraph(giList);
			System.out.println("Max length: " + (Parameters.VECTOR_LENGTH - 1));
			System.out.println(((SurprisingSequensesIndividual)lastResult).toString());
		}else if(Parameters.PROBLEM_TYPE == ProblemType.FLATLAND){
			EA flEa = new EA(individuals);
			for(int i = 0; i < Parameters.GENERATIONS; i++){
				FlatlandIndividual bestInd = (FlatlandIndividual) flEa.runSingleGeneration();
			}
		}
		
		

	}
	private static void showGraph(ArrayList<GenerationInfo> list){
	      BarChart_AWT chart = new BarChart_AWT(list);
	      chart.pack( );        
	      RefineryUtilities.centerFrameOnScreen( chart );        
	      chart.setVisible( true ); 
	}
	private static ArrayList<Individual> generateIndividuals(){
		ArrayList<Individual> individuals = new ArrayList<Individual>();
		for(int i = 0; i < Parameters.POPULATION_SIZE; i++){
			if(Parameters.PROBLEM_TYPE == ProblemType.ONE_MAX){
				individuals.add(OneMaxIndividual.generateRandomIndividual());
			}else if(Parameters.PROBLEM_TYPE == ProblemType.LOLZ){
				individuals.add(LolzIndividual.generateRandomIndividual());
			}else if(Parameters.PROBLEM_TYPE == ProblemType.SURPRISING_SEQUENCES){
				individuals.add(SurprisingSequensesIndividual.generateRandomIndividual());
			}else if(Parameters.PROBLEM_TYPE == ProblemType.FLATLAND){
				individuals.add(FlatlandIndividual.generateRandomIndividual());
			}
		}
		return individuals;
	}

}
