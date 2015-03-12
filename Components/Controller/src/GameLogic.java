import java.util.ArrayList;

public interface GameLogic 
{
	// The Game abstract class calls on runGame to initialize the first game state and set up the game board.
	public void runGame(int boardWidth, int boardHeight, ArrayList<Player> players);
	
	// Inputs the current player and game state so that the game logic can determine if
	// the move was legal for the current game state.
	public GamePiece[][] makeMove(Player p, GamePiece[][] gameBoard);
	
	// Returns true if the game state has reached its final state, therefore no more available moves exist,
	// or if the player's move was not valid in the current game state. 
	public boolean invalidBoard(GameState gs);
	
	// Returns true if the player's move was valid in the current game state.
	public boolean validMove(GameState gs);
	
	// Returns true if a the player made a winning move or if the last game state has been reached.
	public boolean winnerExists();
}
