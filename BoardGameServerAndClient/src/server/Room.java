package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

import games.Game;
import games.SimpleGameFactory;

public class Room {
	
	private static HashSet<String> clientNames = new HashSet<String>();
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
	
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
		//TODO update maxplayers and current player count to be extensible
		currentPlayerCount = 0;
		maxPlayers = 2;
	}

	public void gameSetup(String gameType) throws IOException{
		game = gameFactory.createGame(gameType);
		servSock = new ServerSocket(portNumber);
		portNumber = servSock.getLocalPort();
	}
	
	//Setters and getters
	public int getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
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

	public void incrementPlayerCount() {
		this.currentPlayerCount ++;
	}
	//End of Setters and getters
	
	public void connectToRoom() throws IOException{
		/*
		try{
			while(getCurrentPlayerCount() != getMaxPlayers() ){ //While numplayers != maxPlayers 
				//TODO allow for game options that have minAmount of players vs maxAmount of players
				new GameHandler(gameServSock.accept()).start();
				newRoom.incrementPlayerCount();
				updateClientsRoomList();
				if(name != null) { clientNames.remove(name); }
				if(out != null) { writers.remove(out); }
				updateClientsPlayerList();
			}
		} finally { System.out.println("Closing room on port " + gameServSock.getLocalPort());
					gameServSock.close(); 
					gameRooms.remove(newRoom);
					updateClientsRoomList(); }
		*/
	}
	
	
	//Multi thread handler for the game
	private static class GameHandler extends Thread{
		private String name;
		private Socket socket;
		private BufferedReader in;
		private PrintWriter out;
		
		public GameHandler(Socket socket){ this.socket = socket; }
		
		public void run(){
			System.out.println("Running new thread");
			try{
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);
				
				while(true) {
					//TODO ping users to check if online
					String input = in.readLine();
					if(input == null){	
						return; //don't do anything on empty returns
					} else { 
						for(PrintWriter writer: writers) {
							writer.println("MESSAGE" + name + " : " + input);
						}
					}
				}
				
				
			} catch (IOException e) {
				System.err.println(e);
			} finally {
				try {
					socket.close();
				} catch (IOException e){ System.err.println("There was an error closing connections, shutting down now"); }
			}
		} // end of run()
		
	}
	
}
