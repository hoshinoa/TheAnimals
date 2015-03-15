package games;

public class SimpleGameFactory {

	public Game createGame(String gameType) {
		Game game = null;

		if (gameType == "Tic-Tac-Toe") {
			System.out.println("Starting tic tac toe");
			game = new TicTacToeGame();
		} 
		
		return game;
	}
	
}
