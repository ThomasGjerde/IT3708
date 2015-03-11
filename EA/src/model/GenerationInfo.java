package model;

public class GenerationInfo
{
	private int generationNumber;
	private double best;
	private double average;
	private double sd;
	public GenerationInfo(int generationNumber, double best,double average,double sd){
		this.best = best;
		this.average = average;
		this.sd = sd;
		this.generationNumber = generationNumber;
	}
	public double getBest()
	{
		return best;
	}
	public void setBest(double best)
	{
		this.best = best;
	}
	public double getAverage()
	{
		return average;
	}
	public void setAverage(double average)
	{
		this.average = average;
	}
	public double getSd()
	{
		return sd;
	}
	public void setSd(double sd)
	{
		this.sd = sd;
	}
	public int getGenerationNumber()
	{
		return generationNumber;
	}
	public void setGenerationNumber(int generationNumber)
	{
		this.generationNumber = generationNumber;
	}
}
