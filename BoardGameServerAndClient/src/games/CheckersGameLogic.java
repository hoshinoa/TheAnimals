package games;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

public class CheckersGameLogic implements GameLogic{

	public int EMPTY = 0;
	public int WHITE = 1;
	public int BLACK = 2;
	public int RED_KING = 3;
	public int BLACK_KING = 4;
	public int redCount;
	public int blackCount;
	
	private GameState gameState;
	private Player player1;
	private Player player2;
	
	public int currentSelectionX;
	public int currentSelectionY;
	public boolean selected = false;
	
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
		gameState.board[2][2].setValue(2);
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
		redCount = 12;
	}

	@Override
	public String makeMove(int col, int row) {
		String sendThis = "";
		
		//Jump, or Select Piece move
		if(gameState.board[row][col].getValue() == gameState.mCurrentTurn && !selected) { //is your own piece, select it
			currentSelectionX = row;
			currentSelectionY = col;
			selected = true;
			
			return sendThis;
			
		} else if (gameState.board[row][col].getValue() == EMPTY && selected) {
			
			//Empty out current space
			gameState.board[currentSelectionX][currentSelectionY].setPiece(" ");
			gameState.board[currentSelectionX][currentSelectionY].setValue(EMPTY);
			sendThis = "PLACEPIECE " + "-" + " " + currentSelectionY + " " + currentSelectionX; 
			player1.sendMessageToPlayer(sendThis);
			player2.sendMessageToPlayer(sendThis);
			
			//Jump to new space
			//TODO check if you have to King the piece
			if(gameState.mCurrentTurn == WHITE) {
				gameState.board[row][col].setPiece("W");
			} else {
				gameState.board[row][col].setPiece("B");
			}
				
			gameState.board[row][col].setValue(gameState.mCurrentTurn);
			sendThis = "PLACEPIECE " + gameState.board[row][col].getPiece() + " " + col + " " + row; 
			
			selected = false;
			//TODO check for victory
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
		if (blackCount == 0 || redCount == 0){
			return true;
		}
		return false;
	}

}

/*

	public void makeMove(int fromRow, int fromCol, int toRow, int toCol) {
     board.get(toRow).set(toCol, board.get(fromRow).get(fromCol));
     board.get(fromRow).set(fromCol,EMPTY);
     if (fromRow - toRow == 2 || fromRow - toRow == -2) {
    	
        int jumpRow = (fromRow + toRow) / 2; 
        int jumpCol = (fromCol + toCol) / 2; 
        board.get(jumpRow).set(jumpCol,EMPTY);
        if(current_turn == 1){
        	blackCount--;
        }
        else{
        	redCount--;
        }
     }
     if (toRow == 7 && board.get(toRow).get(toCol) == RED)
        board.get(toRow).set(toCol,RED_KING);
     if (toRow == 0 && board.get(toRow).get(toCol) == BLACK)
    	 board.get(toRow).set(toCol,BLACK_KING);
     CheckersMove[] x = getLegalJumpsFrom(current_turn, toRow, toCol);
     if(x!=null){
    	 makeMove(x[0].fromRow, x[0].fromCol, x[0].toRow, x[0].toCol);
     }
     


     
  }
  
	public boolean isLegalMove(int fromRow, int fromCol, int toRow, int toCol){
		CheckersMove[] moveArray = getLegalMoves(current_turn);
		if (moveArray != null){
		CheckersMove wantedTurn = new CheckersMove(fromRow, fromCol, toRow, toCol);
		for(int i = 0; i < moveArray.length; i++){
			if(wantedTurn.fromCol == moveArray[i].fromCol && wantedTurn.toCol == moveArray[i].toCol && wantedTurn.fromRow == moveArray[i].fromRow && wantedTurn.toRow == moveArray[i].toRow){
				return true;
			}
		}
		return false;
		}
		else{
			return false;
		}
	}

  public CheckersMove[] getLegalMoves(int player) {
     if (player != RED && player != BLACK)
        return null;

     int playerKing;  
     if (player == RED)
        playerKing = RED_KING;
     else
        playerKing = BLACK_KING;

     Vector<CheckersMove> moves = new Vector<CheckersMove>(); 
     
     for (int row = 0; row < 8; row++) {
        for (int col = 0; col < 8; col++) {
           if (board.get(row).get(col)== player || board.get(row).get(col) == playerKing) {
              if (canJump(player, row, col, row+1, col+1, row+2, col+2))
                 moves.addElement(new CheckersMove(row, col, row+2, col+2));
              if (canJump(player, row, col, row-1, col+1, row-2, col+2))
                 moves.addElement(new CheckersMove(row, col, row-2, col+2));
              if (canJump(player, row, col, row+1, col-1, row+2, col-2))
                 moves.addElement(new CheckersMove(row, col, row+2, col-2));
              if (canJump(player, row, col, row-1, col-1, row-2, col-2))
                 moves.addElement(new CheckersMove(row, col, row-2, col-2));
           }
        }
     }
          
     if (moves.size() == 0) {
        for (int row = 0; row < 8; row++) {
           for (int col = 0; col < 8; col++) {
              if (board.get(row).get(col) == player || board.get(row).get(col) == playerKing) {
                 if (canMove(player,row,col,row+1,col+1))
                    moves.addElement(new CheckersMove(row,col,row+1,col+1));
                 if (canMove(player,row,col,row-1,col+1))
                    moves.addElement(new CheckersMove(row,col,row-1,col+1));
                 if (canMove(player,row,col,row+1,col-1))
                    moves.addElement(new CheckersMove(row,col,row+1,col-1));
                 if (canMove(player,row,col,row-1,col-1))
                    moves.addElement(new CheckersMove(row,col,row-1,col-1));
              }
           }
        }
     }     
     if (moves.size() == 0){
        return null;
     }
     else {
        CheckersMove[] moveArray = new CheckersMove[moves.size()];
        for (int i = 0; i < moves.size(); i++)
           moveArray[i] = (CheckersMove)moves.elementAt(i);
        return moveArray;
     }

  }
  

  public CheckersMove[] getLegalJumpsFrom(int player, int row, int col) {
     if (player != RED && player != BLACK)
        return null;
     int playerKing;  
     if (player == RED)
        playerKing = RED_KING;
     else
        playerKing = BLACK_KING;
     Vector<CheckersMove> moves = new Vector<CheckersMove>();  
     if (board.get(row).get(col) == player || board.get(row).get(col) == playerKing) {
        if (canJump(player, row, col, row+1, col+1, row+2, col+2))
           moves.addElement(new CheckersMove(row, col, row+2, col+2));
        if (canJump(player, row, col, row-1, col+1, row-2, col+2))
           moves.addElement(new CheckersMove(row, col, row-2, col+2));
        if (canJump(player, row, col, row+1, col-1, row+2, col-2))
           moves.addElement(new CheckersMove(row, col, row+2, col-2));
        if (canJump(player, row, col, row-1, col-1, row-2, col-2))
           moves.addElement(new CheckersMove(row, col, row-2, col-2));
     }
     if (moves.size() == 0)
        return null;
     else {
        CheckersMove[] moveArray = new CheckersMove[moves.size()];
        for (int i = 0; i < moves.size(); i++)
           moveArray[i] = (CheckersMove)moves.elementAt(i);
        return moveArray;
     }
  } 
  

  private boolean canJump(int player, int r1, int c1, int r2, int c2, int r3, int c3) {          
     if (r3 < 0 || r3 >= 8 || c3 < 0 || c3 >= 8)
        return false;  
        
     if (board.get(r3).get(c3) != EMPTY)
        return false;  
        
     if (player == RED) {
        if (board.get(r1).get(c1) == RED && r3 < r1)
           return false;  
        if (board.get(r2).get(c2) != BLACK && board.get(r2).get(c2) != BLACK_KING)
           return false; 
        return true;
     }
     else {
        if (board.get(r1).get(c1) == BLACK && r3 > r1)
           return false; 
        if (board.get(r2).get(c2) != RED && board.get(r2).get(c2) != RED_KING)
           return false;  
        return true;  
     }

  } 
  

  private boolean canMove(int player, int r1, int c1, int r2, int c2) {      
     if (r2 < 0 || r2 >= 8 || c2 < 0 || c2 >= 8)
        return false; 
        
     if (board.get(r2).get(c2) != EMPTY){
        return false;  
     }

     if (player == RED) {
        if (board.get(r1).get(c1) == RED && r2 < r1)
            return false;  
         return true;  
     }
     else {
        if (board.get(r1).get(c1) == BLACK && r2 > r1)
            return false;  
         return true;  
     }
     
  }
  
  
}


// HELPER CLASS
class CheckersMove {
  int fromRow, fromCol;  
  int toRow, toCol;      
  CheckersMove(int r1, int c1, int r2, int c2) {
     fromRow = r1;
     fromCol = c1;
     toRow = r2;
     toCol = c2;
  }
}
 */
