package server;

import java.io.IOException;
import java.net.ServerSocket;

import games.Game;
import games.SimpleGameFactory;

public class Room {
	//TODO Handle disconnects from players
	//TODO figure out  what should be part of room and what should be part of game
	private String nameOfRoom;
	
	//This goes to game
	private int maxPlayers;
	//
	
	private int currentPlayerCount;
	private SimpleGameFactory gameFactory;
	private Game game;
	
	//New Server Socket for just the game
	//TODO non-local host ip?
	private int portNumber; 
	private ServerSocket servSock;
	
	public Room(String roomName) {
		this.nameOfRoom = roomName;
		gameFactory = new SimpleGameFactory();
		portNumber = 0;
	}
	
	public void gameSetup(String gameType) throws IOException{
		game = gameFactory.createGame(gameType);
		servSock = new ServerSocket(portNumber);
		portNumber = servSock.getLocalPort();
	}
	
	//Setters and getters
	public String getNameOfRoom() {
		return nameOfRoom;
	}

	public void setNameOfRoom(String nameOfRoom) {
		this.nameOfRoom = nameOfRoom;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public int getCurrentPlayerCount() {
		return currentPlayerCount;
	}

	public void setCurrentPlayerCount(int currentPlayerCount) {
		this.currentPlayerCount = currentPlayerCount;
	}
	//End of Setters and getters
	
}
