package games;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

public abstract class Game 
{
	public String gameTitle;
	public int BOARD_WIDTH;
	public int BOARD_HEIGHT;
	public int MAX_PLAYERS;
	public int MIN_PLAYERS;
	public GameLogic gameLogic;
	
	public ArrayList<Player> gPlayers;
	
	
	public Game(String gameTitle, int boardWidth, int boardHeight, int maxPlayers, int minPlayers, GameLogic gameLogic) 
	{
		this.gameTitle = gameTitle;
		BOARD_WIDTH = boardWidth;
		BOARD_HEIGHT = boardHeight;
		MAX_PLAYERS = maxPlayers;
		MIN_PLAYERS = minPlayers;
		this.gameLogic = gameLogic;
		
		gPlayers = new ArrayList<Player>();
	}
	
	
	// Starts the game.
	public void startGame(HashSet<String> clientNames, HashSet<PrintWriter> writers)
	{
		for(String name: clientNames) { gPlayers.add(new Player(name)); }
		for(PrintWriter writer: writers) { writer.println("MESSAGE" + "SYSTEM: " + "Players matched! Game is starting now..."); }
		gameLogic.runGame(BOARD_WIDTH, BOARD_HEIGHT, gPlayers, writers);
	}
	
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
