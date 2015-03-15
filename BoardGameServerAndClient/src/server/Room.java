package server;

public class Room {

	private String nameOfRoom;
	private int maxPlayers;
	private int currentPlayerCount;
	//private int portNumber;
	
	public Room(String roomName) {
		this.nameOfRoom = roomName;
		this.maxPlayers = 2;
		this.currentPlayerCount = 1;
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
