package games;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class OthelloGameLogic implements GameLogic{

	private GameState gameState;
	private Player player1;
	private Player player2;
	
	private int blackCount = 2;
	private int whiteCount = 2;
	
	@Override
	public void runGame(int boardWidth, int boardHeight,
			ArrayList<Player> players, HashSet<PrintWriter> writers) {
		
			gameState = new GameState(boardWidth, boardHeight);
			
			PrintWriter playerOuts[] = writers.toArray(new PrintWriter[writers.size()]);
			
			player1 = players.get(0);
			player1.setOut(playerOuts[0]);
			player1.sendMessageToPlayer("MESSAGE" + "Othello: " + "You are player 1 (B), you will go first");
			
			player2 = players.get(1);
			player2.setOut(playerOuts[1]);
			player2.sendMessageToPlayer("MESSAGE" + "Othello: " + "You are player 2 (W), you will go second");
			
			player1.sendMessageToPlayer("MESSAGE" + "Othello: " + "Currently Player 1's turn, please make a move");
			player2.sendMessageToPlayer("MESSAGE" + "Othello: " + "Currently Player 1's turn, please wait...");
			
			//Initial Board Setup
			
			gameState.board[3][3].setPiece("W");
			gameState.board[3][3].setValue(2);
			gameState.board[4][4].setPiece("W");
			gameState.board[4][4].setValue(2);
			
			player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[3][3].getPiece() + " " + 3 + " " + 3); 
			player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[3][3].getPiece() + " " + 3 + " " + 3);
			player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[4][4].getPiece() + " " + 4 + " " + 4); 
			player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[4][4].getPiece() + " " + 4 + " " + 4);
			
			gameState.board[3][4].setPiece("B");
			gameState.board[3][4].setValue(1);
			gameState.board[4][3].setPiece("B");
			gameState.board[4][3].setValue(1);
			
			player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[3][4].getPiece() + " " + 3 + " " + 4); 
			player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[3][4].getPiece() + " " + 3 + " " + 4);
			player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[4][3].getPiece() + " " + 4 + " " + 3); 
			player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[4][3].getPiece() + " " + 4 + " " + 3);
			
			//Start Game
			player1.sendMessageToPlayer("MAKEMOVE");
			player2.sendMessageToPlayer("WAIT");
		
	}

	@Override
	public boolean invalidBoard(GameState gs) { return false; }

	
	@Override
	public String makeMove(int col, int row) {
		
		String sendThis = "";
		
		if (validMove(col, row)){
			if(gameState.mCurrentTurn == 1) { //player 1
				gameState.board[row][col].setPiece("B");
				gameState.board[row][col].setValue(1); 
				flipPieces(col, row);
				gameState.mCurrentTurn = 2; 
				sendThis = "PLACEPIECE " + gameState.board[row][col].getPiece() + " " + col + " " + row; 
				player1.sendMessageToPlayer("WAIT");
				player2.sendMessageToPlayer("MAKEMOVE");
				player2.sendMessageToPlayer("MESSAGE" + "Othello: " + "Currently Player 2's turn, please make a move");
				player1.sendMessageToPlayer("MESSAGE" + "Othello: " + "Currently Player 2's turn, please wait...");
			} else { //player 2
				gameState.board[row][col].setPiece("W");
				gameState.board[row][col].setValue(2); 
				flipPieces(col, row);
				gameState.mCurrentTurn = 1; 
				sendThis = "PLACEPIECE " + gameState.board[row][col].getPiece() + " " + col + " " + row;
				player1.sendMessageToPlayer("MAKEMOVE");
				player2.sendMessageToPlayer("WAIT");
				player1.sendMessageToPlayer("MESSAGE" + "Othello: " + "Currently Player 1's turn, please make a move");
				player2.sendMessageToPlayer("MESSAGE" + "Othello: " + "Currently Player 1's turn, please wait...");
			}
			
			winnerExists();
			
		}  else { sendThis = "INVALIDMOVE "; }
		return sendThis;
	}

	@Override
	public boolean validMove(int col, int row) {
		if (gameState.board[row][col].getValue() != 0){ return false; } //If a piece exists there
		return true;
	}

	@Override
	public boolean winnerExists() {
		
		if (isBoardFull()){
			player1.sendMessageToPlayer("WAIT");
			player2.sendMessageToPlayer("WAIT");
			pieceCount();
			if (blackCount > whiteCount){
				player1.sendMessageToPlayer("MESSAGE" + "Othello: " + "Congratulations Player 1 is the WINNER!");
				player2.sendMessageToPlayer("MESSAGE" + "Othello: " + "Congratulations Player 1 is the WINNER!");
			} else if (blackCount < whiteCount){
				player1.sendMessageToPlayer("MESSAGE" + "Othello: " + "Congratulations Player 2 is the WINNER!");
				player2.sendMessageToPlayer("MESSAGE" + "Othello: " + "Congratulations Player 2 is the WINNER!");
			} else if (blackCount == whiteCount){
				player1.sendMessageToPlayer("MESSAGE" + "Othello: " + "Tie Game");
				player2.sendMessageToPlayer("MESSAGE" + "Othello: " + "Tie Game");
			}
			return true;
		}	
		return false;
	}
	
	public void flipPieces(int col, int row){
		
		if(row != 0) { checkAbove(col, row - 1); }
		if(row != 8) { checkBelow(col, row + 1); }
		if(col != 0) { checkLeft(col - 1, row); }
		if(col != 8) { checkRight(col + 1, row); }
		
		if(row != 0 && col != 0){ checkUpperLeftDiagonal(col-1,row-1); }
		if(row != 0 && col != 8){ checkUpperRightDiagonal(col+1,row-1); }
		if(row != 8 && col != 0){ checkLowerLeftDiagonal(col-1,row+1); }
		if(row != 8 && col != 8){ checkLowerRightDiagonal(col+1,row+1); }
		
	}
	
	public boolean checkAbove(int col, int row) {
		if(row == 0 || gameState.board[row][col].getValue() == 0) { //Stop don't do anything
			return false;
		} else if(gameState.board[row][col].getValue() == gameState.mCurrentTurn){ //need to do flipping
			return true;
		} else { //different color, continue
			if (checkAbove(col, row - 1) ) { //if true flip current piece 
				if(gameState.mCurrentTurn == 2) {
					gameState.board[row][col].setPiece("W");
					gameState.board[row][col].setValue(2);
				} else {
					gameState.board[row][col].setPiece("B");
					gameState.board[row][col].setValue(1);
				}
				player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[row][col].getPiece() + " " + col + " " + row); 
				player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[row][col].getPiece() + " " + col + " " + row);
				return true;
			}
		}
		return false;
	}
	public boolean checkBelow(int col, int row) {
		if(row == 8 || gameState.board[row][col].getValue() == 0) { //Stop don't do anything
			return false;
		} else if(gameState.board[row][col].getValue() == gameState.mCurrentTurn){ //need to do flipping
			return true;
		} else { //different color, continue
			if (checkBelow(col, row + 1) ) { //if true flip current piece 
				if(gameState.mCurrentTurn == 2) {
					gameState.board[row][col].setPiece("W");
					gameState.board[row][col].setValue(2);
				} else {
					gameState.board[row][col].setPiece("B");
					gameState.board[row][col].setValue(1);
				}
				player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[row][col].getPiece() + " " + col + " " + row); 
				player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[row][col].getPiece() + " " + col + " " + row);
				return true;
			}
		}
		return false;
	}
	public boolean checkLeft(int col, int row) {
		if(col == 0 || gameState.board[row][col].getValue() == 0) { //Stop don't do anything
			return false;
		} else if(gameState.board[row][col].getValue() == gameState.mCurrentTurn){ //need to do flipping
			return true;
		} else { //different color, continue
			if (checkLeft(col - 1, row ) ) { //if true flip current piece 
				if(gameState.mCurrentTurn == 2) {
					gameState.board[row][col].setPiece("W");
					gameState.board[row][col].setValue(2);
				} else {
					gameState.board[row][col].setPiece("B");
					gameState.board[row][col].setValue(1);
				}
				player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[row][col].getPiece() + " " + col + " " + row); 
				player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[row][col].getPiece() + " " + col + " " + row);
				return true;
			}
		}
		return false;
	}
	public boolean checkRight(int col, int row) {
		if(col == 8 || gameState.board[row][col].getValue() == 0) { //Stop don't do anything
			return false;
		} else if(gameState.board[row][col].getValue() == gameState.mCurrentTurn){ //need to do flipping
			return true;
		} else { //different color, continue
			if (checkRight(col + 1, row ) ) { //if true flip current piece 
				if(gameState.mCurrentTurn == 2) {
					gameState.board[row][col].setPiece("W");
					gameState.board[row][col].setValue(2);
				} else {
					gameState.board[row][col].setPiece("B");
					gameState.board[row][col].setValue(1);
				}
				player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[row][col].getPiece() + " " + col + " " + row); 
				player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[row][col].getPiece() + " " + col + " " + row);
				return true;
			}
		}
		return false;
	}
	
	public boolean checkUpperLeftDiagonal(int col, int row){
		if(col == 0 || row == 0 || gameState.board[row][col].getValue() == 0) { //Stop don't do anything
			return false;
		} else if(gameState.board[row][col].getValue() == gameState.mCurrentTurn){ //need to do flipping
			return true;
		} else { //different color, continue
			if (checkUpperLeftDiagonal(col - 1, row - 1 ) ) { //if true flip current piece 
				if(gameState.mCurrentTurn == 2) {
					gameState.board[row][col].setPiece("W");
					gameState.board[row][col].setValue(2);
				} else {
					gameState.board[row][col].setPiece("B");
					gameState.board[row][col].setValue(1);
				}
				player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[row][col].getPiece() + " " + col + " " + row); 
				player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[row][col].getPiece() + " " + col + " " + row);
				return true;
			}
		}
		return false;
	}
	
	public boolean checkUpperRightDiagonal(int col, int row){
		if(col == 8 || row == 0 || gameState.board[row][col].getValue() == 0) { //Stop don't do anything
			return false;
		} else if(gameState.board[row][col].getValue() == gameState.mCurrentTurn){ //need to do flipping
			return true;
		} else { //different color, continue
			if (checkUpperRightDiagonal(col + 1, row - 1 ) ) { //if true flip current piece 
				if(gameState.mCurrentTurn == 2) {
					gameState.board[row][col].setPiece("W");
					gameState.board[row][col].setValue(2);
				} else {
					gameState.board[row][col].setPiece("B");
					gameState.board[row][col].setValue(1);
				}
				player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[row][col].getPiece() + " " + col + " " + row); 
				player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[row][col].getPiece() + " " + col + " " + row);
				return true;
			}
		}
		return false;
	}
	
	public boolean checkLowerRightDiagonal(int col, int row){
		if(col == 8 || row == 8 || gameState.board[row][col].getValue() == 0) { //Stop don't do anything
			return false;
		} else if(gameState.board[row][col].getValue() == gameState.mCurrentTurn){ //need to do flipping
			return true;
		} else { //different color, continue
			if (checkLowerRightDiagonal(col + 1, row + 1 ) ) { //if true flip current piece 
				if(gameState.mCurrentTurn == 2) {
					gameState.board[row][col].setPiece("W");
					gameState.board[row][col].setValue(2);
				} else {
					gameState.board[row][col].setPiece("B");
					gameState.board[row][col].setValue(1);
				}
				player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[row][col].getPiece() + " " + col + " " + row); 
				player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[row][col].getPiece() + " " + col + " " + row);
				return true;
			}
		}
		return false;
	}
	
	public boolean checkLowerLeftDiagonal(int col, int row){
		if(col == 0 || row == 8 || gameState.board[row][col].getValue() == 0) { //Stop don't do anything
			return false;
		} else if(gameState.board[row][col].getValue() == gameState.mCurrentTurn){ //need to do flipping
			return true;
		} else { //different color, continue
			if (checkLowerLeftDiagonal(col - 1, row + 1 ) ) { //if true flip current piece 
				if(gameState.mCurrentTurn == 2) {
					gameState.board[row][col].setPiece("W");
					gameState.board[row][col].setValue(2);
				} else {
					gameState.board[row][col].setPiece("B");
					gameState.board[row][col].setValue(1);
				}
				player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[row][col].getPiece() + " " + col + " " + row); 
				player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[row][col].getPiece() + " " + col + " " + row);
				return true;
			}
		}
		return false;
	}
	
	private boolean isBoardFull(){
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (gameState.board[i][j].getValue() == 0){
					return false;
				}
			}
		}
		return true;
	}
	
	private void pieceCount(){
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (gameState.board[i][j].getValue() == 2){
					blackCount++;
				} else if (gameState.board[i][j].getValue() == 1){
					whiteCount++;
				}
			}
		}
	}
	
}





 
