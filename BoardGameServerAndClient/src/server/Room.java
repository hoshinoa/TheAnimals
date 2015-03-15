package server;

import games.Game;
import games.SimpleGameFactory;

public class Room {
	
	//TODO figure out  what should be part of room and what should be part of game
	private String nameOfRoom;
	private int maxPlayers;
	private int currentPlayerCount;
	private SimpleGameFactory gameFactory;
	private Game game;
	//private int portNumber;
	
	public Room(String roomName) {
		this.nameOfRoom = roomName;
		gameFactory = new SimpleGameFactory();
	}
	
	public void gameSetup(String gameType){
		game = gameFactory.createGame(gameType);
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
