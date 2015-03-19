package games;

public class SimpleGameFactory {

	public Game createGame(String gameType) {
		Game game = null;
		GameLogic logic = null;
		
		if (gameType.contains("Tic-Tac-Toe")) {
			
			logic = new TicTacToeGameLogic();
			game = new TicTacToeGame(gameType, 3, 3, 2, 2, logic);
			
			
		} else if(gameType.contains("Checkers")) {
			
			logic = new CheckersGameLogic();
			game = new Checkers(gameType, 8, 8, 2, 2, logic);
			
		} else if(gameType.contains("Othello")) {
			
			logic = new OthelloGameLogic();
			game = new Othello(gameType, 8, 8, 2, 2, logic);
			
		}
		
		return game;
	}
	
}
