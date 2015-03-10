package model;

public class IndividualPair
{
	private Individual a;
	private Individual b;
	
	public IndividualPair(Individual a, Individual b){
		this.a = a;
		this.b = b;
	}

	public Individual getA()
	{
		return a;
	}

	public void setA(Individual a)
	{
		this.a = a;
	}

	public Individual getB()
	{
		return b;
	}

	public void setB(Individual b)
	{
		this.b = b;
	}
}
