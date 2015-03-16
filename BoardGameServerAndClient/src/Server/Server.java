package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;

public class Server {
	
    private static HashSet<String> clientNames = new HashSet<String>();
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
    
    //private static HashSet<Room> gameRooms = new HashSet<Room>();
    private static ArrayList<Room> gameRoomsList = new ArrayList<Room>();
    
	//Main
	public static void main(String[] args) throws IOException{
		
		//ServerSocket servSock = new ServerSocket(0);
		ServerSocket servSock = new ServerSocket(8901);
		System.out.println("Server is running on Port: " + servSock.getLocalPort());
		new ServerStatus().start();
		try{
			while(true){
				new Handler(servSock.accept()).start();
			}
		} finally { servSock.close(); }
		
	}
	//End of Main
	
	//ServerStatus is a Thread for updating clients on server status
	private static class ServerStatus extends Thread{
		//TODO check for full rooms
		public void run(){
			while(true) {
				long millis = System.currentTimeMillis();
				updateClientsPlayerList();
				updateClientsRoomList();
			    try {
					sleep(1600 - millis % 1600);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
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
				
				for(PrintWriter writer: writers) {
					writer.println("MESSAGE" + "SYSTEM: " + name + " has joined the waiting room. ");
				}
				
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
						
						synchronized (gameRoomsList) {
							newRoom = new Room(roomOptions[2] + "|" + roomOptions[1] + "|");
							newRoom.gameSetup(roomOptions[1]);
							gameRoomsList.add(newRoom);
							//gameRooms.add(newRoom);
						}
						
						for(PrintWriter writer: writers) {
							writer.println("MESSAGE" + "SYSTEM: " + name + " has created a new room called: " + roomOptions[2]);
						}
						connectToNewRoom();
						
						break;
						
					} else if (input.startsWith("CONNECTPLAYERTOROOM")) {
						
						String roomOptions [] = input.split("\\s+");
						
						//roomOptions[0] == CONNECTTOPLAYERROOM
						//roomOptions[1] == ROOM NUMBER
						
						for(PrintWriter writer: writers) {
							writer.println("MESSAGE" + "SYSTEM: " + name + " has joined a room. ");
						}
						
						System.out.println(input);
						connectToRoom(Integer.parseInt(roomOptions[1]));
						
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
				try {
					socket.close();
				} catch (IOException e){ System.err.println("There was an error closing connections, shutting down now"); }
			}
		}
	
		public void connectToNewRoom() throws IOException{
			
			// send instructions to client to connect to new game
			ServerSocket gameServSock = new ServerSocket(0);
			System.out.println("Server is running on Port: " + gameServSock.getLocalPort());
			newRoom.setPortNumber(gameServSock.getLocalPort());
			String sendThis = "CONNECTTONEWGAMEROOM" + " " + gameServSock.getLocalPort();
			out.println(sendThis);
			
			if(name != null) { clientNames.remove(name); }
			if(out != null) { writers.remove(out); }
			
			//TODO check for full room gameRooms.remove(newRoom);
			//TODO Send User Info and Server Info
			System.out.println(name);
			newRoom.connectToRoom(name, gameServSock);
		}
		
		public void connectToRoom(int roomNumber) throws IOException{
			String sendThis = "CONNECTTONEWGAMEROOM" + " " + gameRoomsList.get(roomNumber).servSock.getLocalPort();
			out.println(sendThis);
			System.out.println(name);
			gameRoomsList.get(roomNumber).connectToRoom(name, gameRoomsList.get(roomNumber).servSock);
			if(name != null) { clientNames.remove(name); }
			if(out != null) { writers.remove(out); }
			
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
		sendThis += gameRoomsList.size() + " "; // Game Roome Size
		for(Room room : gameRoomsList) {
			sendThis += room.getNameOfRoom() + "Players:" + room.getCurrentPlayerCount() 
															 + "/" + room.getMaxPlayers() + " ";
		}
		
		for(PrintWriter writer: writers) { writer.println(sendThis); }
		
	}
	
}
