package games;

public class SimpleGameFactory {

	public Game createGame(String gameType) {
		Game game = null;

		if (gameType.contains("Tic-Tac-Toe")) {
			System.out.println("Starting tic tac toe");
			//TODO use Game's better constructor
			game = new TicTacToeGame();
		} else if(gameType.contains("Checkers")) {
			System.out.println("Checkers");
			//TODO use Game's better constructor
			game = new Checkers();
		} else if(gameType.contains("Othello")) {
			System.out.println("Othello");
			//TODO use Game's better constructor
			game = new Othello();
		}
		
		return game;
	}
	
}
