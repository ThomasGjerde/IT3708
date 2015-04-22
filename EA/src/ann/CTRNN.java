package ann;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import beertracker.BeerBoard;
import model.BeerArray;
import model.BeerVector;
import model.CellContent;
import model.Direction;
import model.Parameters;

public class CTRNN {
	double[] inputs;
	double[] outputs;
	double[] weights;
	
	public CTRNN(){
		inputs = new double[Parameters.BT_INPUT_NEURONS];
		outputs = new double[Parameters.BT_OUTPUT_NEURONS];
		//SJEKK antall weights
		weights = new double[Parameters.BT_INPUT_NEURONS*Parameters.BT_OUTPUT_NEURONS];
	}
	
	public CTRNN(double[] newWeights){
		inputs = new double[Parameters.BT_INPUT_NEURONS];
		outputs = new double[Parameters.BT_OUTPUT_NEURONS];
		
		//Her kan det kanskje spares litt kraft
		//Er ikke helt sikker pï¿½ hvor man ikke bare setter = her
		weights = new double[Parameters.BT_INPUT_NEURONS*Parameters.BT_OUTPUT_NEURONS];
		for(int i=0; i<weights.length; i++){
			weights[i] = newWeights[i];
		}
		//util.Utilities.printDoubleArray(weights);
	}
	
	public BeerVector chooseAction(BeerBoard board){
		//CellContent[] sensors = board.getSensorInfo();
		//if(sensors[0] == CellContent.FOOD){inputs[0] = 1.0;}else{inputs[0] = 0.0;}
		inputs = board.getSensorInfo();
		
		BeerVector bestBeer;
		int temp = Parameters.BT_INPUT_NEURONS;
		//int inputWeights = 39;
		
		SigmoidNeuron sign = new SigmoidNeuron();
		for (int i = 0; i < this.outputs.length; i++) {
			outputs[i] = sign.fire(inputs,Arrays.copyOfRange(weights, i * temp, i * temp+temp));
		}
		
		ArrayList<BeerArray> midArray = new ArrayList<BeerArray>();
		midArray.add(new BeerArray(outputs[0],new BeerVector(Direction.RIGHT,4), 0));
		midArray.add(new BeerArray(outputs[1],new BeerVector(Direction.LEFT,2), 1));
		midArray.add(new BeerArray(outputs[2],new BeerVector(Direction.LEFT,3), 2));
		midArray.add(new BeerArray(outputs[3],new BeerVector(Direction.LEFT,4), 3));
		midArray.add(new BeerArray(outputs[4],new BeerVector(Direction.RIGHT,1), 4));
		midArray.add(new BeerArray(outputs[5],new BeerVector(Direction.RIGHT,2), 5));
		midArray.add(new BeerArray(outputs[6],new BeerVector(Direction.RIGHT,3), 6));
		midArray.add(new BeerArray(outputs[7],new BeerVector(Direction.LEFT,1), 7));
		midArray.add(new BeerArray(outputs[8],new BeerVector(Direction.STILL,0), 8));
		
		Collections.sort(midArray, new Comparator<BeerArray>(){
			@Override
			public int compare(BeerArray o1, BeerArray o2) {
				// TODO Auto-generated method stub
				//DET HER KAN VÆRE FEIL VEI!
				double mid = o2.getWeight()-o1.getWeight();
				if(o1.getWeight() > o2.getWeight()){return -1;}
				else{return 1;}
				//return mid;
			}
		});
		return midArray.get(0).getVector();
	}

}
