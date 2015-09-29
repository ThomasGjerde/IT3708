package main;

import java.util.ArrayList;
import java.util.Random;

import org.jfree.ui.RefineryUtilities;

import beertracker.BeerGraphics;
import beertracker.BeerTracker;
import beertracker.BeertrackerIndividual;

import ea.EAGraphics;
import ea.FlatlandIndividual;
import ea.Individual;
import flatland.FlGraphics;
import flatland.Flatland;

import onemax.OneMaxIndividual;
import plot.BarChart_AWT;
import surprisingsequenses.SurprisingSequensesIndividual;
import util.Utilities;

import lolz.LolzIndividual;
import model.GenerationInfo;
import model.Parameters;
import model.ProblemType;
import model.StaticGraphic;

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
		
		//Add param controls
		EAGraphics.showMutateControl();
		
		
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
			StaticGraphic.flGraphics = new FlGraphics();
			if(Parameters.SINGLE_RUN){
				double[] weights = new double[] { 0.75,0.25,0.5,0.25,0.75,0.625,0.625,0.75,0.375,0.75,0.375,0.625,0.375,0.625,0.875,0.875,0.625,0.25};
				//FlatlandIndividual bestInd = (FlatlandIndividual) flEa.runSingleGeneration();
				Flatland fl = new Flatland(weights);
				fl.graphicRun();
				return;
			}
			
			EA flEa = new EA(individuals);
			for(int i = 0; i < Parameters.GENERATIONS; i++){
				FlatlandIndividual bestInd = (FlatlandIndividual) flEa.runSingleGeneration();
				Flatland fl = new Flatland(bestInd.getPhenotype());
				fl.graphicRun();
			}
			showGraph(flEa.getGiList());
		}else if(Parameters.PROBLEM_TYPE == ProblemType.BEERTRACKER){
			StaticGraphic.beerGraphics = new BeerGraphics();
			
			if(Parameters.SINGLE_RUN){
				double[] weights = new double[] { 0.75,0.5,0.5,0.25,0.75,0.625,0.25,0.625,0.375,0.75,0.375,0.75,0.5,0.5,0.75,0.5,0.875,0.25};
				Flatland fl = new Flatland(weights);
				fl.graphicRun();
				return;
			}else{
				EA beerEA = new EA(individuals);
				for(int i = 0; i < Parameters.GENERATIONS; i++){
					BeertrackerIndividual bestInd = (BeertrackerIndividual)beerEA.runSingleGeneration();
					BeerTracker bt = new BeerTracker(bestInd.getPhenotype());
					bt.graphicRun();
				}
				showGraph(beerEA.getGiList());
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
				Parameters.VECTOR_LENGTH = 18; //Auto
				individuals.add(FlatlandIndividual.generateRandomIndividual());
			}else if(Parameters.PROBLEM_TYPE == ProblemType.BEERTRACKER){
				if(Parameters.PULLDOWN_ACTIVE){
					Parameters.VECTOR_LENGTH = 70;
					Parameters.BT_OUTPUT_NEURONS = 10;
				}else{
					Parameters.VECTOR_LENGTH = 63;
					Parameters.BT_OUTPUT_NEURONS = 9;
				}
				individuals.add(BeertrackerIndividual.generateRandomIndividual());
			}
		}
		return individuals;
	}

}
