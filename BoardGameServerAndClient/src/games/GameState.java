package games;

public class GameState {

	int mCurrentTurn;

	GamePiece board[][];
	
	public GameState(int boardWidth, int boardHeight){
		this.board = new GamePiece[boardWidth][boardHeight];
		mCurrentTurn = 1;
		
		for(int i = 0; i < boardWidth; i++) {
			for(int j = 0; j < boardHeight; j++) {
				board[i][j] = new GamePiece();
				board[i][j].setValue(0);
			}
		}
		
	}
	
}