package twentyfortyeigth;

import java.util.ArrayList;

public class MinMax {
	TFEBoard board;
	public MinMax(TFEBoard board){
		this.board = board;
	}
	public Move search(double alpha, double beta, int depth){
		Move bestMove = new Move(null, -100001);
		if(board.getPlayer()){
			bestMove.setHeuristic(alpha);
			Move currentMove = new Move(null, -100001);
			for(Direction direction : Direction.values()){
				if(board.isLegalMove(direction)){
					TFEBoard newBoard = this.board.cloneBoard();
					newBoard.move(direction);
					newBoard.setPlayer(false);
					
					if(depth == 0){
						currentMove.setDirection(direction);
						currentMove.setHeuristic(newBoard.heuristic());
					}else{
						MinMax newMinMax = new MinMax(newBoard);
						currentMove = newMinMax.search(bestMove.getHeuristic(), beta, depth - 1);
					}
					if(currentMove.getHeuristic() > bestMove.getHeuristic()){
						bestMove.setHeuristic(currentMove.getHeuristic());
						bestMove.setDirection(direction);
					}
					if(bestMove.getHeuristic() > beta){
						return new Move(bestMove.getDirection(),beta);
					}
				}
			}
		}else{ //Player == false
			bestMove.setHeuristic(beta);
			ArrayList<Point> availableTiles = this.board.getUnoccupiedTiles();
			int[] values = { 2 , 4 };
			for(Point tilePos : availableTiles){
				for(int value : values){
					TFEBoard newBoard = this.board.cloneBoard();
					newBoard.setPlayer(true);
					newBoard.getBoard()[tilePos.y][tilePos.x] = value;
					MinMax newMinMax = new MinMax(newBoard);
					Move currentMove = newMinMax.search(alpha, bestMove.getHeuristic(), depth);
					if(currentMove.getHeuristic() < bestMove.getHeuristic()){
						bestMove.setHeuristic(currentMove.getHeuristic());
					}
					if(bestMove.getHeuristic() < alpha){
						return new Move(null, alpha);
					}
				}
			}			
			
		}
		return bestMove;
	}
}
