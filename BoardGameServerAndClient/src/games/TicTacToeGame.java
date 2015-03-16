package games;

public class TicTacToeGame extends Game {

	public TicTacToeGame() {
		super();
	}
	
	public TicTacToeGame(String gameTitle, int boardWidth, int boardHeight,
			GameLogic gameLogic) {
		super(gameTitle, boardWidth, boardHeight, gameLogic);
	}
	
}
