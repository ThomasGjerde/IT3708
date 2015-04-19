package flatland;

import java.util.ArrayList;
import java.util.Random;

import model.CellContent;
import model.Parameters;

public class Board {
	static CellContent[][] staticBoard;
	Agent agent;
	CellContent cells[][];
	public Board(){
		Agent agent = new Agent();	
		if(Parameters.FL_STATIC_BOARD){
			if(staticBoard == null){
				generateRandomCells();
				staticBoard = copyCells(cells);
			}else{
				cells = copyCells(staticBoard);
			}
		}else{
			generateRandomCells();
		}
	}
	private void generateRandomCells(){
		//Check for nullpointers
		ArrayList<CellContent> cellList = new ArrayList<CellContent>();
		int totalNumCells = cells.length * cells[0].length;
		int numFood = (int)(totalNumCells * 0.33);
		int numPoison = (int)((totalNumCells - numFood) * 0.33);
		int numEmpty = totalNumCells - (numFood + numPoison);
		Random r = new Random();
		for(int i = 0; i < numFood; i++){
			cellList.add(CellContent.FOOD);
		}
		for(int i = 0; i < numPoison; i++){
			cellList.add(CellContent.POISON);
		}
		for(int i = 0; i < numEmpty; i++){
			cellList.add(CellContent.EMPTY);
		}
		for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells[0].length; j++){
				cells[i][j] = cellList.remove(r.nextInt(cellList.size()));
			}
		}
	}
	private static CellContent[][] copyCells(CellContent[][] cells){
		CellContent[][] newCells = new CellContent[cells.length][cells[0].length];
		for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells[0].length; j++){
				newCells[i][j] = cells[i][j];
			}
		}
		return newCells;
	}
	public Agent getAgent(){
		return agent;
	}
}
