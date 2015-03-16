package games;

import java.util.ArrayList;

public abstract class Game 
{
	public String gameTitle;
	public int BOARD_WIDTH;
	public int BOARD_HEIGHT;
	//public ArrayList<Player> gPlayers;
	public GameLogic gameLogic;

	// Creates a new game.
	// Takes in the game's title, game board width & height, and chosen game logic.
	public Game(){}

	public Game(String gameTitle, int boardWidth, int boardHeight, GameLogic gameLogic) 
	{
		this.gameTitle = gameTitle;
		BOARD_WIDTH = boardWidth;
		BOARD_HEIGHT = boardHeight;
		this.gameLogic = gameLogic;
	}
	
	/*
	// Starts the game.
	public void startGame(ArrayList<Player> gPlayers)
	{
		// List of involved players.
		//this.gPlayers = gPlayers;
		
		// Passes the game board width & height and a list of involved players to the game logic.
		gameLogic.runGame(BOARD_WIDTH, BOARD_HEIGHT, gPlayers);
	}
	*/
	/*
	// Gives the game logic the index of the current player and a copy of the current board state
	// after the player has moved a game piece so that the logic can check if the moves are legal.
	public GamePiece[][] playTurn(int playerIndex, GamePiece[][] currentBoard)
	{
		// Get the Player at the current player index.
		//Player currentPlayer = gPlayers.get(playerIndex);
		
		// Make the move in the game logic with the current player and the current board.
		//GamePiece[][] newBoard = gameLogic.makeMove(currentPlayer, currentBoard);
		
		// Returns a new version of the board after the move has/hasn't been made on the board.
		return newBoard;
	} */
	
	
	// Determines if the game is over by checking if a winner exists in the game logic.
	public boolean gameOver()
	{
		if(gameLogic.winnerExists())
			return true;
		else
			return false;
	}
	
	
	// After the game is over, the winning board state and a list of the players,
	// in order from highest to lowest winning score, is packaged into JSON code
	// so that it can be passed through the server, to the client, and then
	// reinterpreted by the View.
	public void packageBoard()
	{

	}
}
