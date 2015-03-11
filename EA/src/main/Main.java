package main;

import java.util.ArrayList;
import java.util.Random;

import org.jfree.ui.RefineryUtilities;

import onemax.OneMaxIndividual;
import plot.BarChart_AWT;
import util.Utilities;

import model.GenerationInfo;
import model.Individual;
import model.Parameters;

public class Main
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		/*
		ArrayList<GenerationInfo> giList = new ArrayList<GenerationInfo>();
		for(int i = 0; i < 200; i++){
			giList.add(new GenerationInfo(i, Math.random(), Math.random(), Math.random()));
		}
		*/

		// TODO Auto-generated method stub
		ArrayList<Individual> individuals = new ArrayList<Individual>();
		for(int i = 0; i < Parameters.POPULATION_SIZE; i++){
			individuals.add(OneMaxIndividual.generateRandomIndividual());
		}
		EA ea = new EA();
		OneMaxIndividual result = (OneMaxIndividual)ea.run(individuals);
		if(result != null){
			Utilities.printBoolArray(result.getGenotype());
		}else{
			System.out.println("No solution");
		}
		
	      BarChart_AWT chart = new BarChart_AWT(ea.getGiList());
	      chart.pack( );        
	      RefineryUtilities.centerFrameOnScreen( chart );        
	      chart.setVisible( true ); 
	}

}
