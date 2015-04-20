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
		int posX = agent.getPosX();
		int posY = agent.getPosY();
		int oldX = posX;
		int oldY = posY;
		
		if(dir == Direction.LEFT){
			orientation = Direction.rotateDirection(orientation, -1);
		}else if(dir == Direction.RIGHT){
			orientation = Direction.rotateDirection(orientation, 1);
		}else if(dir == Direction.DOWN){
			orientation = Direction.rotateDirection(orientation, 2);
		}
		
		if(orientation == Direction.LEFT){
			posX += -1;
			if(posX < 0){
				posX = Parameters.FL_MAPSIZE;
			}
		}else if(orientation == Direction.RIGHT){
			posX += 1;
			if(posX > Parameters.FL_MAPSIZE){
				posX = 0;
			}
		}else if(orientation == Direction.UP){
			posY += -1;
			if(posY < 0){
				posY = Parameters.FL_MAPSIZE;
			}
		}else if(orientation == Direction.DOWN){
			posY += 1;
			if(posY > Parameters.FL_MAPSIZE){
				posY = 0;
			}
		}
		
		agent.setPosition(posX, posY, cells[posX][posY]);
		cells[posX][posY] = CellContent.AGENT;
		if(oldX != posX || oldY != posY){
			cells[oldX][oldY] = CellContent.EMPTY;
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
	
	public CellContent[][] getCells(){
		return cells;
	}
	
	public CellContent[] getSensorInfo(){
		int x = agent.getPosX();
		int y = agent.getPosY();
		Direction ori = agent.getOrientation();
		int midX = 0;
		int midY = 0;
		CellContent sensor[] = new CellContent[3];
		if(ori == Direction.UP){
			//Front
			if(y == 0){midY = Parameters.FL_MAPSIZE-1;}else{midY = y-1;}
			midX = x;
			sensor[1] = cells[midX][midY];
			
			//Right
			if(x == Parameters.FL_MAPSIZE-1){midX = 0;}else{midX = x+1;}
			midY = y;
			sensor[2] = cells[midX][midY];
			
			//Left
			if(x == 0){midX = Parameters.FL_MAPSIZE-1;}else{midX = x-1;}
			midY = y;
			sensor[0] = cells[midX][midY];
			
		}else if(ori == Direction.RIGHT){
			//Front
			if(x == Parameters.FL_MAPSIZE-1){midX = 0;}else{midX = x+1;}
			midY = y;
			sensor[1] = cells[midX][midY];
			//Right
			if(y == Parameters.FL_MAPSIZE-1){midY = 0;}else{midY = y+1;}
			midX = x;
			sensor[2] = cells[midX][midY];
			//Left
			if(y == 0){midY = Parameters.FL_MAPSIZE-1;}else{midY = y-1;}
			midX = x;
			sensor[0] = cells[midX][midY];
		}else if(ori == Direction.DOWN){
			//Front
			if(y == Parameters.FL_MAPSIZE-1){midY = 0;}else{midY = y+1;}
			midX = x;
			sensor[1] = cells[midX][midY];
			//Right
			if(x == 0){midX = Parameters.FL_MAPSIZE-1;}else{midX = x-1;}
			midY = y;
			sensor[2] = cells[midX][midY];
			//Left
			if(x == Parameters.FL_MAPSIZE-1){midX = 0;}else{midX = x+1;}
			midY = y;
			sensor[0] = cells[midX][midY];
		}else{//ori == Direction.LEFT
			//Front
			if(x == 0){midX = Parameters.FL_MAPSIZE-1;}else{midX = x-1;}
			midY = y;
			sensor[1] = cells[midX][midY];
			//Right
			if(y == 0){midY = Parameters.FL_MAPSIZE-1;}else{midY = y-1;}
			midX = x;
			sensor[2] = cells[midX][midY];
			//Left
			if(y == Parameters.FL_MAPSIZE){midY = 0;}else{midY = y+1;}
			midX = x;
			sensor[0] = cells[midX][midY];
		}
		return sensor;
	}
}
