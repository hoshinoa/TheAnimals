package games;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

public class TicTacToeGameLogic implements GameLogic{

	private GameState gameState;
	private Player player1;
	private Player player2;
	
	@Override
	public void runGame(int boardWidth, int boardHeight,
			ArrayList<Player> players, HashSet<PrintWriter> writers) {
		gameState = new GameState(boardWidth, boardHeight);
		
		PrintWriter playerOuts[] = writers.toArray(new PrintWriter[writers.size()]);
		
		player1 = players.get(0);
		player1.setOut(playerOuts[0]);
		player1.sendMessageToPlayer("MESSAGE" + "Tic-Tac-Toe: " + "You are player 1 (X), you will go first");
		
		player2 = players.get(1);
		player2.setOut(playerOuts[1]);
		player2.sendMessageToPlayer("MESSAGE" + "Tic-Tac-Toe: " + "You are player 2 (O), you will go second");
		
		player1.sendMessageToPlayer("MESSAGE" + "Tic-Tac-Toe: " + "Currently Player 1's turn, please make a move");
		player2.sendMessageToPlayer("MESSAGE" + "Tic-Tac-Toe: " + "Currently Player 1's turn, please wait...");
		
		player1.sendMessageToPlayer("MAKEMOVE");
		player2.sendMessageToPlayer("WAIT");
		//Game Loop
		/*
		while(!winnerExists()) {
			
		}
		*/
	}
	
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

	@Override
	public GamePiece[][] makeMove(Player p, GamePiece[][] gameBoard) {
		// TODO Auto-generated method stub
		return null;
	}

}

/*


	int PLAYER1 = 1;
	int PLAYER2 = 2;
	int EMPTY = 0;
	
	
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
	
*/