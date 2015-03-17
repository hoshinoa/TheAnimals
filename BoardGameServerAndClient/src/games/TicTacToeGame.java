package games;

public class TicTacToeGame extends Game {

	public TicTacToeGame() {
		this.gameTitle = "Tic-Tac-Toe";
		this.BOARD_HEIGHT = 3;
		this.BOARD_WIDTH = 3;
	}

	public TicTacToeGame(String gameTitle, int boardWidth, int boardHeight,
			GameLogic gameLogic) {
		super(gameTitle, boardWidth, boardHeight, gameLogic);
	}
	
}
