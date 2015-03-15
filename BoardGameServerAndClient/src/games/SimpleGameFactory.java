package games;

public class SimpleGameFactory {

	public Game createPizza(String type) {
		Game game = null;

		if (type.equals("Tic-Tac-Toe")) {
			game = new TicTacToeGame();
		} 
		
		return game;
	}
	
}
