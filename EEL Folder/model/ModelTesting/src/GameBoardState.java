
public class GameBoardState {
	
	private int mRowSize;
	private int mColumnSize;
	
	private GamePiece[][] mBoard;
	
	//
	
	public GameBoardState(){} //Default Constructor
	
	public GameBoardState(int rowSize, int columnSize){ //Constructor to create a new board
		mBoard = new GamePiece[rowSize][columnSize];
		mRowSize = rowSize;
		mColumnSize = columnSize;
	}
	
	// This function makes a move changing the state or the mBoard
	// TODO: Implement this function and think about what params it needs.
	public void makeMove(){
		
	}
	
	// This function should check if the move is valid or not, 
	// but actually I feel this should be in the game logic
	public boolean validMove(){
		return true;
	}
	
	
}
