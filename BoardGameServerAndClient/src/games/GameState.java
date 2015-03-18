package games;

import java.util.ArrayList;


public class GameState {

	private int mCurrentTurn;

	private GamePiece board[][];
	
	public GameState(int boardWidth, int boardHeight){
		this.board = new GamePiece[boardWidth][boardHeight];
	}
	
}