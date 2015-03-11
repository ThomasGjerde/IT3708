package plot;

import java.util.ArrayList;

import model.GenerationInfo;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 

public class BarChart_AWT extends ApplicationFrame
{
	public BarChart_AWT(ArrayList<GenerationInfo> list)
	{
		super("EA");
		String chartTitle = "Stats";
	      JFreeChart lineChart = ChartFactory.createLineChart(
	    	         chartTitle,
	    	         "Generation","Fitness",
	    	         createDataset(list),
	    	         PlotOrientation.VERTICAL,
	    	         true,true,false);
	    	         
	    	      ChartPanel chartPanel = new ChartPanel( lineChart );
	    	      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
	    	      setContentPane( chartPanel );
	}
	private CategoryDataset createDataset(ArrayList<GenerationInfo> list)
	{
		final String best = "Best";        
		final String avg = "Average";        
		final String sd = "SD";     
		final DefaultCategoryDataset dataset = 
				new DefaultCategoryDataset( );  

		for(GenerationInfo gi : list){
			String gen = Integer.toString(gi.getGenerationNumber());
			dataset.addValue(gi.getBest(), best , gen);
			dataset.addValue(gi.getAverage(), avg, gen);
			dataset.addValue(gi.getSd(), sd, gen);
		}
		return dataset; 
	}
}