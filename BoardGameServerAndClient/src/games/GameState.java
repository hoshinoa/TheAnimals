package games;

import java.util.ArrayList;


public class GameState {

	int mCurrentTurn;

	GamePiece board[][];
	
	public GameState(int boardWidth, int boardHeight){
		this.board = new GamePiece[boardWidth][boardHeight];
		mCurrentTurn = 1;
	}
	
}