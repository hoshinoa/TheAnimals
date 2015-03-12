package server;

public class Room {

	private String nameOfRoom;
	private int maxPlayers;
	private int currentPlayerCount;
	
	public Room(String roomName) {
		this.nameOfRoom = roomName;
	}

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
	
}
