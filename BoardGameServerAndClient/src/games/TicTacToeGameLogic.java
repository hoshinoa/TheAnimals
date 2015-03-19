package games;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
	}
	
	@Override
	public boolean invalidBoard(GameState gs) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validMove(int col, int row) {
		
		if(gameState.board[row][col].getValue() == 0){ 
			return true;
		}
		return false;
	}

	@Override
	public boolean winnerExists() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String makeMove(int col, int row) {
		String sendThis = "";
		if(validMove(col,row)){
			if(gameState.mCurrentTurn == 1) { //player 1
				gameState.board[row][col].setPiece("X");
				gameState.board[row][col].setValue(1); 
				gameState.mCurrentTurn = 2; 
				sendThis = "PLACEPIECE " + gameState.board[row][col].getPiece() + " " + col + " " + row; 
				
				if(isGameOver()) { //player 1 victory
					player1.sendMessageToPlayer("WAIT");
					player2.sendMessageToPlayer("WAIT");
					player1.sendMessageToPlayer("MESSAGE" + "Tic-Tac-Toe: " + "Congratulations Player 1 is the WINNER!");
					player2.sendMessageToPlayer("MESSAGE" + "Tic-Tac-Toe: " + "Congratulations Player 1 is the WINNER!");
				} else {
					sendThis = "PLACEPIECE " + gameState.board[row][col].getPiece() + " " + col + " " + row; 
					player2.sendMessageToPlayer("MESSAGE" + "Tic-Tac-Toe: " + "Currently Player 2's turn, please make a move");
					player1.sendMessageToPlayer("MESSAGE" + "Tic-Tac-Toe: " + "Currently Player 2's turn, please wait...");
				
					player2.sendMessageToPlayer("MAKEMOVE");
					player1.sendMessageToPlayer("WAIT");
				}
				
			} else { //player 2
				gameState.board[row][col].setPiece("O");
				gameState.board[row][col].setValue(2); 
				gameState.mCurrentTurn = 1; 
				sendThis = "PLACEPIECE " + gameState.board[row][col].getPiece() + " " + col + " " + row; 
				
				if(isGameOver()) { //player 2 victory
					player1.sendMessageToPlayer("WAIT");
					player2.sendMessageToPlayer("WAIT");
					player1.sendMessageToPlayer("MESSAGE" + "Tic-Tac-Toe: " + "Congratulations Player 2 is the WINNER!");
					player2.sendMessageToPlayer("MESSAGE" + "Tic-Tac-Toe: " + "Congratulations Player 2 is the WINNER!");
				} else {
					
					player1.sendMessageToPlayer("MESSAGE" + "Tic-Tac-Toe: " + "Currently Player 1's turn, please make a move");
					player2.sendMessageToPlayer("MESSAGE" + "Tic-Tac-Toe: " + "Currently Player 1's turn, please wait...");
				
					player1.sendMessageToPlayer("MAKEMOVE");
					player2.sendMessageToPlayer("WAIT");
				}
			}
			
			return sendThis;
			} else {
			sendThis = "INVALIDMOVE ";
			return sendThis; }
	}

	public boolean isGameOver(){
		if (checkColumnsForWin() || checkRowsForWin() || checkDiagonalsForWin() || isBoardFull()){
			 return true;
		}	
		return false;
	}
	
	private boolean checkColumnsForWin() {
		for (int i = 0; i < 3; i++) {
			if (gameState.board[0][i].getValue() == gameState.board[1][i].getValue() && gameState.board[2][i].getValue() == gameState.board[1][i].getValue() && gameState.board[0][i].getValue() != 0) {
				return true;
		    }
		}
		return false;
	}
	
	private boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            if (gameState.board[i][0].getValue() == gameState.board[i][1].getValue() && gameState.board[i][2].getValue() == gameState.board[i][1].getValue() && gameState.board[i][1].getValue() != 0) {
                return true;
            }
        }
        return false;
    }
	
	private boolean checkDiagonalsForWin() {
		if (gameState.board[0][0].getValue() == gameState.board[1][1].getValue() && gameState.board[1][1].getValue() == gameState.board[2][2].getValue() && gameState.board[1][1].getValue() != 0){
			return true;
		}
		else if(gameState.board[0][2].getValue() == gameState.board[1][1].getValue() && gameState.board[1][1].getValue() == gameState.board[2][0].getValue() && gameState.board[1][1].getValue() != 0){
			return true;
		}
		return false;
	}
	
	private boolean isBoardFull(){
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				if (gameState.board[i][j].getValue() == 0){
					return false;
				}
			}
		}
		return true;
	}
	
}


