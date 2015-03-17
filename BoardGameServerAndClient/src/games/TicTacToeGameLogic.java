package games;

public class TicTacToeGameLogic implements GameLogic{

	@Override
	public boolean invalidBoard(GameState gs) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validMove(GameState gs) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean winnerExists() {
		// TODO Auto-generated method stub
		return false;
	}

}

/*

import java.util.*;


public class TicTacToeLogic {

	ArrayList<List<Integer>> board;
	int PLAYER1 = 1;
	int PLAYER2 = 2;
	int EMPTY = 0;
	
	public TicTacToeLogic(){
		board = new ArrayList<List<Integer>>();
		board.add(Arrays.asList(0,0,0));
		board.add(Arrays.asList(0,0,0));
		board.add(Arrays.asList(0,0,0));
	}
	
	public void makeMove(int x, int y, int player){
		board.get(x).set(y,player);
	}
	
	public boolean isGameOver(){
		if (checkColumnsForWin() || checkRowsForWin() || checkDiagonalsForWin() || isBoardFull()){
			 return true;
		}	
		return false;
	}
	
	public boolean isLegalMove(int x, int y){
		if(board.get(x).get(y) == EMPTY){
			return true;
		}
		return false;
	}
	
	private boolean checkColumnsForWin() {
		for (int i = 0; i < 3; i++) {
			if (board.get(0).get(i) == board.get(1).get(i) && board.get(2).get(i) == board.get(1).get(i) && board.get(1).get(i) != EMPTY) {
				return true;
		    }
		}
		return false;
	}
	
	private boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            if (board.get(i).get(0) == board.get(i).get(1) && board.get(i).get(2) == board.get(i).get(1) && board.get(i).get(1) != EMPTY) {
                return true;
            }
        }
        return false;
    }

	private boolean checkDiagonalsForWin() {
		if (board.get(0).get(0) == board.get(1).get(1) && board.get(1).get(1) == board.get(2).get(2) && board.get(1).get(1) != EMPTY){
			return true;
		}
		else if(board.get(0).get(2) == board.get(1).get(1) && board.get(1).get(1) == board.get(2).get(0) && board.get(1).get(1) != EMPTY){
			return true;
		}
		return false;
	}
	
	private boolean isBoardFull(){
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				if (board.get(i).get(j) == EMPTY){
					return false;
				}
			}
		}
		return true;
	}
	
	public void printBoard(){
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				if (j != 2){
					System.out.print(board.get(i).get(j) + " | ");
				}
				else{
					System.out.print(board.get(i).get(j));
				}
			}
			if(i !=2)
				System.out.println("\n----------");
		}
	}


}
*/