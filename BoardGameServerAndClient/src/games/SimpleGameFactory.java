package games;

public class SimpleGameFactory {

	public Game createGame(String gameType) {
		Game game = null;

		if (gameType.contains("Tic-Tac-Toe")) {
			System.out.println("Starting tic tac toe");
			//TODO use Game's better constructor
			game = new TicTacToeGame();
		} 
		
		return game;
	}
	
}
