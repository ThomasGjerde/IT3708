package model;

public class BeerArray {
	BeerVector vector;
	int index;
	double weight;
	
	public BeerArray(double w, BeerVector v, int i){
		vector = v;
		index = i;
		weight = w;
		
	}
	
	public BeerVector getVector(){
		return vector;
	}
	
	public int getIndex(){
		return index;
	}
	
	public double getWeight(){
		return weight;
	}
	
}
