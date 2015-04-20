package flatland;

import java.util.ArrayList;
import java.util.Random;

import model.CellContent;
import model.Direction;
import model.Parameters;

public class Board {
	static CellContent[][] staticBoard;
	Agent agent;
	CellContent cells[][];
	public Board(){	
		cells = new CellContent[Parameters.FL_MAPSIZE][Parameters.FL_MAPSIZE];
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
		int agentX = 0;
		int agentY = 0;
		for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells[0].length; j++){
				if(cells[i][j] == CellContent.AGENT){
					agentX = i;
					agentY = j;
				}
			}
		}
		agent = new Agent(agentX,agentY,Direction.UP);
	}
	public void moveAgent(Direction dir){
		Direction orientation = agent.getOrientation();
		Direction boardDirection;
		if(dir == Direction.LEFT){
			if(orientation == Direction.UP){
				boardDirection = Direction.LEFT;
			}else if(orientation == Direction.LEFT){
				boardDirection = Direction.DOWN;
			}else if()
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
		for(int i = 0; i < numEmpty - 1; i++){
			cellList.add(CellContent.EMPTY);
		}
		cellList.add(CellContent.AGENT);
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
