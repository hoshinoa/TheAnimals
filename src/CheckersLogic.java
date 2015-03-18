import java.util.*;

// You can call setCurrent_turn(int) and getCurrent_turn() after every move.
// makeMove can be called and there are 4 arguments in this order:
				//from Row, from Column, to Row, to Column
// isLegalMove can be called and it has the same arguments
// makeMove will do the jumps automatically if there are more after the first one.

public class CheckersLogic {
	public int EMPTY = 0;
	public int RED = 1;
	public int BLACK = 2;
	public int RED_KING = 3;
	public int BLACK_KING = 4;
	public int redCount;
	public int blackCount;
	public int current_turn;
	ArrayList<List<Integer>> board;
	
	public CheckersLogic(){
		board = new ArrayList<List<Integer>>();
		board.add(Arrays.asList(0,1,0,1,0,1,0,1));
		board.add(Arrays.asList(1,0,1,0,1,0,1,0));
		board.add(Arrays.asList(0,1,0,1,0,1,0,1));
		board.add(Arrays.asList(0,0,0,0,0,0,0,0));
		board.add(Arrays.asList(0,0,0,0,0,0,0,0));
		board.add(Arrays.asList(2,0,2,0,2,0,2,0));
		board.add(Arrays.asList(0,2,0,2,0,2,0,2));
		board.add(Arrays.asList(2,0,2,0,2,0,2,0));
		current_turn = 1;
		blackCount = 12;
		redCount = 12;
	}
	
	public boolean isGameOver(){
		if (blackCount == 0 || redCount == 0){
			return true;
		}
		return false;
	}
	
	
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
  
	public void setCurrent_turn(int turn){
		current_turn = turn;
	}
	
	public int getCurrent_turn(){
		return current_turn;
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
  
  	public void printBoard() {
  		System.out.println("  0 1 2 3 4 5 6 7 x");
		for (int i=0;i<8;i++) {
			System.out.print((i) + " ");
			for (int j=0;j<8;j++) {
				System.out.print(board.get(i).get(j) + " ");
			}
			    System.out.println();
		}
		System.out.println("y");
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
