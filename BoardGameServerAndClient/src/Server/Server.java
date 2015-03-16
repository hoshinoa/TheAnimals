package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class Server {
	
    private static HashSet<String> clientNames = new HashSet<String>();
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
    
    private static HashSet<Room> gameRooms = new HashSet<Room>();
    
	//Main
	public static void main(String[] args) throws IOException{
		
		//ServerSocket servSock = new ServerSocket(0);
		ServerSocket servSock = new ServerSocket(8901);
		System.out.println("Server is running on Port: " + servSock.getLocalPort());
		try{
			while(true){
				new Handler(servSock.accept()).start();
			}
		} finally { servSock.close(); }
		
	}
	//End of Main
	
	//Handler is a Thread for a new client
	private static class Handler extends Thread{
		private String name;
		private Socket socket;
		private BufferedReader in;
		private PrintWriter out;
		private Room newRoom;
		
		public Handler(Socket socket){ this.socket = socket; }
		
		public void run(){
			System.out.println("Running new thread");
			try{
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);
				
				while(true) {
					out.println("SUBMITNAME");
					name = in.readLine();
					System.out.println(name);
					if(name == null) { System.out.println("name was null"); return;}
					synchronized (clientNames) {
						if(!clientNames.contains(name)) {
							clientNames.add(name);
							System.out.println("Added waiting to room: " + name);
							break;
						}
					}
				}
				
				out.println("NAMEACCEPTED");
				writers.add(out);

				updateClientsPlayerList();
				updateClientsRoomList();
				
				while(true) {
					//TODO ping users to check if online
					String input = in.readLine();
					if(input == null){	
						return; //don't do anything on empty returns
					} else if (input.startsWith("MAKENEWROOM")){ //Spawn a new room
						
						String roomOptions [] = input.split("\\s+");
						
						//System.out.println(roomOptions[0]); //MAKENEWROOM
						//System.out.println(roomOptions[1]); //GAMETYPE
						//System.out.println(roomOptions[2]); //GAMEROOM NAME
						
						synchronized (gameRooms) {
							newRoom = new Room(roomOptions[2] + "|" + roomOptions[1] + "|");
							newRoom.gameSetup(roomOptions[1]);
							gameRooms.add(newRoom);
						}
						
						updateClientsRoomList();
						connectToNewGame();
						
						break;
						
					} else { 
						for(PrintWriter writer: writers) {
							writer.println("MESSAGE" + name + " : " + input);
						}
					}
					
				}
				
				
			} catch (IOException e) {
				System.err.println(e);
			} finally {
				if(name != null) { clientNames.remove(name); }
				if(out != null) { writers.remove(out); }
				updateClientsPlayerList();
				try {
					socket.close();
				} catch (IOException e){ System.err.println("There was an error closing connections, shutting down now"); }
			}
		}
		
		public void connectToNewGame() throws IOException{
			// send instructions to client to connect to new game
			ServerSocket gameServSock = new ServerSocket(0);
			System.out.println("Server is running on Port: " + gameServSock.getLocalPort());
			
			String sendThis = "CONNECTTONEWGAMEROOM" + " " + gameServSock.getLocalPort();
			out.println(sendThis);
			
			try{
				while(newRoom.getCurrentPlayerCount() != newRoom.getMaxPlayers() ){ //While numplayers != maxPlayers 
					//TODO allow for game options that have minAmount of players vs maxAmount of players
					new GameHandler(gameServSock.accept()).start();
					newRoom.incrementPlayerCount();
					updateClientsRoomList();
				}
			} finally { System.out.println("Closing room on port " + gameServSock.getLocalPort());
						gameServSock.close(); 
						gameRooms.remove(newRoom);
						updateClientsRoomList(); }
			
		}
		
		private static class GameHandler extends Thread{
			private String name;
			private Socket socket;
			private BufferedReader in;
			private PrintWriter out;
			private Room newRoom;
			
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
	
	public static void updateClientsPlayerList(){ //get player list
		//TODO JSON stuff //need to choose a json parsing library -> JSONObject looks fine
		//TODO going along with graceful loss of user, needs to update on lost connection, some ping function
		String sendThis = "UPDATEPLAYERLIST ";
		sendThis += clientNames.size() + " "; //Number of clients

		for(String name: clientNames) { sendThis += name + " "; }
		
		for(PrintWriter writer: writers) { writer.println(sendThis); }
	}
	
	public static void updateClientsRoomList(){
		//TODO JSONIFY this
		//TODO be able to update when the room is closed or full
		String sendThis = "UPDATEROOMLIST ";
		sendThis += gameRooms.size() + " "; // Game Roome Size
		for(Room room : gameRooms) {
			sendThis += room.getNameOfRoom() + "Players:" + room.getCurrentPlayerCount() 
															 + "/" + room.getMaxPlayers() + " ";
		}
		
		for(PrintWriter writer: writers) { writer.println(sendThis); }
		
	}
	
}
