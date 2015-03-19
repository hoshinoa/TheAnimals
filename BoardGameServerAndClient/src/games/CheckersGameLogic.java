package games;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

public class CheckersGameLogic implements GameLogic{

	public int EMPTY = 0;
	public int WHITE = 1;
	public int BLACK = 2;
	public int WHITE_KING = 3;
	public int BLACK_KING = 4;
	public int whiteCount;
	public int blackCount;
	
	private GameState gameState;
	private Player player1;
	private Player player2;
	
	public int currentSelectionX;
	public int currentSelectionY;
	
	@Override
	public void runGame(int boardWidth, int boardHeight,
			ArrayList<Player> players, HashSet<PrintWriter> writers) {
		
		gameState = new GameState(boardWidth, boardHeight);
		
		PrintWriter playerOuts[] = writers.toArray(new PrintWriter[writers.size()]);
		
		player1 = players.get(0);
		player1.setOut(playerOuts[0]);
		player1.sendMessageToPlayer("MESSAGE" + "Checkers: " + "You are player 1 (W), you will go first");
		
		player2 = players.get(1);
		player2.setOut(playerOuts[1]);
		player2.sendMessageToPlayer("MESSAGE" + "Checkers: " + "You are player 2 (B), you will go second");
		
		player1.sendMessageToPlayer("MESSAGE" + "Checkers: " + "Currently Player 1's turn, please make a move");
		player2.sendMessageToPlayer("MESSAGE" + "Checkers: " + "Currently Player 1's turn, please wait...");
		
		player1.sendMessageToPlayer("MAKEMOVE");
		player2.sendMessageToPlayer("WAIT");
		
		setupCheckers();
		
	}
	
	public void setupCheckers(){
		
		gameState.board[0][1].setPiece("W");
		gameState.board[0][3].setPiece("W");
		gameState.board[0][5].setPiece("W");
		gameState.board[0][7].setPiece("W");
		gameState.board[0][1].setValue(1);
		gameState.board[0][3].setValue(1);
		gameState.board[0][5].setValue(1);
		gameState.board[0][7].setValue(1);
		player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[0][1].getPiece() + " " + 1 + " " + 0); 
		player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[0][3].getPiece() + " " + 3 + " " + 0);
		player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[0][5].getPiece() + " " + 5 + " " + 0); 
		player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[0][7].getPiece() + " " + 7 + " " + 0);
		player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[0][1].getPiece() + " " + 1 + " " + 0); 
		player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[0][3].getPiece() + " " + 3 + " " + 0);
		player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[0][5].getPiece() + " " + 5 + " " + 0); 
		player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[0][7].getPiece() + " " + 7 + " " + 0);
		
		gameState.board[1][0].setPiece("W");
		gameState.board[1][2].setPiece("W");
		gameState.board[1][4].setPiece("W");
		gameState.board[1][6].setPiece("W");
		gameState.board[1][0].setValue(1);
		gameState.board[1][2].setValue(1);
		gameState.board[1][4].setValue(1);
		gameState.board[1][6].setValue(1);
		player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[1][0].getPiece() + " " + 0 + " " + 1); 
		player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[1][2].getPiece() + " " + 2 + " " + 1);
		player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[1][4].getPiece() + " " + 4 + " " + 1); 
		player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[1][6].getPiece() + " " + 6 + " " + 1);
		player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[1][0].getPiece() + " " + 0 + " " + 1); 
		player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[1][2].getPiece() + " " + 2 + " " + 1);
		player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[1][4].getPiece() + " " + 4 + " " + 1); 
		player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[1][6].getPiece() + " " + 6 + " " + 1);
		
		gameState.board[2][1].setPiece("W");
		gameState.board[2][3].setPiece("W");
		gameState.board[2][5].setPiece("W");
		gameState.board[2][7].setPiece("W");
		gameState.board[2][1].setValue(1);
		gameState.board[2][3].setValue(1);
		gameState.board[2][5].setValue(1);
		gameState.board[2][7].setValue(1);
		player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[2][1].getPiece() + " " + 1 + " " + 2); 
		player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[2][3].getPiece() + " " + 3 + " " + 2);
		player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[2][5].getPiece() + " " + 5 + " " + 2); 
		player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[2][7].getPiece() + " " + 7 + " " + 2);
		player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[2][1].getPiece() + " " + 1 + " " + 2); 
		player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[2][3].getPiece() + " " + 3 + " " + 2);
		player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[2][5].getPiece() + " " + 5 + " " + 2); 
		player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[2][7].getPiece() + " " + 7 + " " + 2);
		
		//
		gameState.board[7][0].setPiece("B");
		gameState.board[7][2].setPiece("B");
		gameState.board[7][4].setPiece("B");
		gameState.board[7][6].setPiece("B");
		gameState.board[7][0].setValue(2);
		gameState.board[7][2].setValue(2);
		gameState.board[7][4].setValue(2);
		gameState.board[7][6].setValue(2);
		player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[7][0].getPiece() + " " + 0 + " " + 7); 
		player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[7][2].getPiece() + " " + 2 + " " + 7);
		player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[7][4].getPiece() + " " + 4 + " " + 7); 
		player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[7][6].getPiece() + " " + 6 + " " + 7);
		player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[7][0].getPiece() + " " + 0 + " " + 7); 
		player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[7][2].getPiece() + " " + 2 + " " + 7);
		player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[7][4].getPiece() + " " + 4 + " " + 7); 
		player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[7][6].getPiece() + " " + 6 + " " + 7);
		
		gameState.board[6][1].setPiece("B");
		gameState.board[6][3].setPiece("B");
		gameState.board[6][5].setPiece("B");
		gameState.board[6][7].setPiece("B");
		gameState.board[6][1].setValue(2);
		gameState.board[6][3].setValue(2);
		gameState.board[6][5].setValue(2);
		gameState.board[6][7].setValue(2);
		player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[6][1].getPiece() + " " + 1 + " " + 6); 
		player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[6][3].getPiece() + " " + 3 + " " + 6);
		player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[6][5].getPiece() + " " + 5 + " " + 6); 
		player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[6][7].getPiece() + " " + 7 + " " + 6);
		player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[6][1].getPiece() + " " + 1 + " " + 6); 
		player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[6][3].getPiece() + " " + 3 + " " + 6);
		player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[6][5].getPiece() + " " + 5 + " " + 6); 
		player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[6][7].getPiece() + " " + 7 + " " + 6);
		
		gameState.board[5][0].setPiece("B");
		gameState.board[5][2].setPiece("B");
		gameState.board[5][4].setPiece("B");
		gameState.board[5][6].setPiece("B");
		gameState.board[5][0].setValue(2);
		gameState.board[5][2].setValue(2);
		gameState.board[5][4].setValue(2);
		gameState.board[5][6].setValue(2);
		player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[5][0].getPiece() + " " + 0 + " " + 5); 
		player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[5][2].getPiece() + " " + 2 + " " + 5);
		player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[5][4].getPiece() + " " + 4 + " " + 5); 
		player1.sendMessageToPlayer("PLACEPIECE " + gameState.board[5][6].getPiece() + " " + 6 + " " + 5);
		player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[5][0].getPiece() + " " + 0 + " " + 5); 
		player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[5][2].getPiece() + " " + 2 + " " + 5);
		player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[5][4].getPiece() + " " + 4 + " " + 5); 
		player2.sendMessageToPlayer("PLACEPIECE " + gameState.board[5][6].getPiece() + " " + 6 + " " + 5);
		
		gameState.mCurrentTurn = 1;
		
		blackCount = 12;
		whiteCount = 12;
	}

	public boolean markLegalMoves(boolean fromPrevious, int col, int row){
		
		boolean hasLegalMove = false;
		
		if(gameState.mCurrentTurn == WHITE) { //check bottom diagonals
			if(row != 7 && col != 0){ hasLegalMove |= checkLowerLeftDiagonal(col-1,row+1); }
			if(row != 7 && col != 7){ hasLegalMove |= checkLowerRightDiagonal(col+1,row+1); }
		} else if (gameState.mCurrentTurn == BLACK) { //check upper diagonals
			if(row != 0 && col != 0){ hasLegalMove |= checkUpperLeftDiagonal(col-1,row-1); }
			if(row != 0 && col != 7){ hasLegalMove |= checkUpperRightDiagonal(col+1,row-1); }
		}
		
		return hasLegalMove;
	}
	
	public boolean checkUpperLeftDiagonal(int col, int row){
		if(col < 0 || row < 0 || gameState.board[row][col].getValue() == gameState.mCurrentTurn) { //Stop don't do anything
			return false;
		} else if( gameState.board[row][col].getValue() == 0 ){ //have a legal space
			gameState.board[row][col].legal = true;
			return true;
		} else { //different color, continue
			if(row - 1 >= 0 && col - 1 >= 0 && gameState.board[row - 1][col - 1].getValue() == 0) { //have a legal space that removes something
				gameState.board[row - 1][col - 1].legal = true; 
				return true;
			} 
		}
		return false;
	}
	
	public boolean checkUpperRightDiagonal(int col, int row){
		if(col > 7 || row < 0 || gameState.board[row][col].getValue() == gameState.mCurrentTurn) { //Stop don't do anything
			return false;
		} else if( gameState.board[row][col].getValue() == 0 ){ //have a legal space
			gameState.board[row][col].legal = true;
			return true;
		} else { //different color, continue
			if(row - 1 >= 0 && col + 1 <= 7 && gameState.board[row - 1][col + 1].getValue() == 0) { //have a legal space that removes something
				gameState.board[row - 1][col + 1].legal = true;
				return true;
			} 
		}
		return false;
	}
	
	public boolean checkLowerRightDiagonal(int col, int row){
		if(col > 7 || row > 7 || gameState.board[row][col].getValue() == gameState.mCurrentTurn) { //Stop don't do anything
			return false;
		} else if( gameState.board[row][col].getValue() == 0 ){ //have a legal space
			gameState.board[row][col].legal = true;
			return true;
		} else { //different color, continue
			if(row + 1 <= 7 && col + 1 <= 7 && gameState.board[row + 1][col + 1].getValue() == 0) { //have a legal space that removes something
				gameState.board[row + 1][col + 1].legal = true;
				return true;
				
			} 
		}
		return false;
	}
	
	public boolean checkLowerLeftDiagonal(int col, int row){
		if(col < 0 || row > 7 || gameState.board[row][col].getValue() == gameState.mCurrentTurn) { //Stop don't do anything
			return false;
		} else if( gameState.board[row][col].getValue() == 0 ){ //have a legal space
			gameState.board[row][col].legal = true;
			return true;
		} else { //different color, continue
			if(row + 1 <= 7 && col - 1 >= 0 && gameState.board[row + 1][col - 1].getValue() == 0) { //have a legal space that removes something
				gameState.board[row + 1][col - 1].legal = true;
				return true;
			} 
		}
		return false;
		
	}
	
	public void resetBoardMoves(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++) { gameState.board[i][j].legal = false; } }
	}
	
	@Override
	public String makeMove(int col, int row) {
		String sendThis = "";
		
		//Jump, or Select Piece move
		if(gameState.board[row][col].getValue() == gameState.mCurrentTurn) { //is your own piece, select it
			currentSelectionX = row;
			currentSelectionY = col;
			resetBoardMoves();
			markLegalMoves(false, col, row);
			return sendThis;
			
		} else if (gameState.board[row][col].legal) {
			
			//Empty out current space
			gameState.board[currentSelectionX][currentSelectionY].setPiece(" ");
			gameState.board[currentSelectionX][currentSelectionY].setValue(EMPTY);
			sendThis = "PLACEPIECE " + "-" + " " + currentSelectionY + " " + currentSelectionX; 
			player1.sendMessageToPlayer(sendThis);
			player2.sendMessageToPlayer(sendThis);
			
			//If you jumped over a piece empty that space too
			if(row - currentSelectionX == 2) { //jumped down 
				int x = (row + currentSelectionX) / 2;
				int y = (col + currentSelectionY) / 2;
				gameState.board[x][y].setPiece(" ");
				gameState.board[x][y].setValue(EMPTY);
				sendThis = "PLACEPIECE " + "-" + " " + y + " " + x; 
				System.out.println("Jumping down!:  " + sendThis);
				player1.sendMessageToPlayer(sendThis);
				player2.sendMessageToPlayer(sendThis);
				blackCount--;
				}
			if(row - currentSelectionX == -2) { //jumped up
				int x = (row + currentSelectionX) / 2;
				int y = (col + currentSelectionY) / 2;
				gameState.board[x][y].setPiece(" ");
				gameState.board[x][y].setValue(EMPTY);
				sendThis = "PLACEPIECE " + "-" + " " + y + " " + x; 
				System.out.println("Jumping up!:  " + sendThis);
				player1.sendMessageToPlayer(sendThis);
				player2.sendMessageToPlayer(sendThis);
				whiteCount--;
			}
			
			//While still legal moves //Note can't jump to two empty spaces
				//TODO check if you have to King the piece
			
			gameState.board[row][col].setValue(gameState.mCurrentTurn);
			if(gameState.mCurrentTurn == WHITE) {
				gameState.board[row][col].setPiece("W");
				player1.sendMessageToPlayer("WAIT");
				player2.sendMessageToPlayer("MAKEMOVE");
				player2.sendMessageToPlayer("MESSAGE" + "Checkers: " + "Currently Player 2's turn, please make a move");
				player1.sendMessageToPlayer("MESSAGE" + "Checkers: " + "Currently Player 2's turn, please wait...");
				gameState.mCurrentTurn = 2;
			} else {
				gameState.board[row][col].setPiece("B");
				player2.sendMessageToPlayer("WAIT");
				player1.sendMessageToPlayer("MAKEMOVE");
				player2.sendMessageToPlayer("MESSAGE" + "Checkers: " + "Currently Player 1's turn, please make a move");
				player1.sendMessageToPlayer("MESSAGE" + "Checkers: " + "Currently Player 1's turn, please wait...");
				gameState.mCurrentTurn = 1;
			}
				
			sendThis = "PLACEPIECE " + gameState.board[row][col].getPiece() + " " + col + " " + row; 

			resetBoardMoves();
			if(winnerExists()) {
				player1.sendMessageToPlayer("WAIT");
				player2.sendMessageToPlayer("WAIT");
				if(whiteCount == 0) {
					player1.sendMessageToPlayer("MESSAGE" + "Checkers: " + "Congratulations Player 2 is the WINNER!");
					player2.sendMessageToPlayer("MESSAGE" + "Checkers: " + "Congratulations Player 2 is the WINNER!");
				} else {
					player1.sendMessageToPlayer("MESSAGE" + "Checkers: " + "Congratulations Player 1 is the WINNER!");
					player2.sendMessageToPlayer("MESSAGE" + "Checkers: " + "Congratulations Player 1 is the WINNER!");
				}
			}
		} else { //is probably opponents space, don't do anything
			sendThis = "INVALIDMOVE ";
		}
		
		return sendThis;
	}

	@Override
	public boolean invalidBoard(GameState gs) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validMove(int col, int row) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean winnerExists() {
		if (blackCount == 0 || whiteCount == 0){
			return true;
		}
		return false;
	}

}
