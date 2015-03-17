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
    
    private static ArrayList<Room> gameRoomsList = new ArrayList<Room>();
    
	//Main
	public static void main(String[] args) throws IOException{
		
		Server myBoardGameServer = new Server();
		
		//ServerSocket servSock = new ServerSocket(0);
		ServerSocket servSock = new ServerSocket(8901);
		System.out.println("Server is running on Port: " + servSock.getLocalPort());
		myBoardGameServer.new ServerStatus().start();
		try{
			while(true){
				myBoardGameServer.new Handler(servSock.accept()).start();
			}
		} finally { servSock.close(); }
		
	}
	//End of Main
	
	//ServerStatus is a Thread for updating clients on server status
	private class ServerStatus extends Thread{
		public void run(){
			while(true) {
				long millis = System.currentTimeMillis();
				updateClientsPlayerList();
				updateClientsRoomList();
				ArrayList<Integer> removeThese = new ArrayList<Integer>();
				for(Room room: gameRoomsList) {
					if(room.getCurrentPlayerCount() == room.getMaxPlayers()) {
						System.out.println(room.getNameOfRoom() + " is full, removing from list.");
						removeThese.add(gameRoomsList.indexOf(room));
					}
				}
				for(int index : removeThese) {
					gameRoomsList.remove(index);
				}
			    try {
					sleep(1600 - millis % 1600);
				} catch (InterruptedException e) { e.printStackTrace(); }
			}
		}
		
	}
	
	//Handler is a Thread for a new client
	private class Handler extends Thread{
		private String name;
		private Socket socket;
		private BufferedReader in;
		private PrintWriter out;
		
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
							System.out.println("Added to waiting room: " + name);
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
					} else if (input.startsWith("MAKENEWROOM")){
						
						String roomOptions [] = input.split("\\s+");
						
						//System.out.println(roomOptions[0]); //MAKENEWROOM
						//System.out.println(roomOptions[1]); //GAMETYPE
						//System.out.println(roomOptions[2]); //GAMEROOM NAME
						
						Room newRoom;
						
						synchronized (gameRoomsList) {
							newRoom = new Room(roomOptions[2] + "|" + roomOptions[1] + "|");
							newRoom.gameSetup(roomOptions[1]);
							gameRoomsList.add(newRoom);
						}

						createNewRoom(gameRoomsList.indexOf(newRoom));
						
						for(PrintWriter writer: writers) {
							writer.println("MESSAGE" + "SYSTEM: " + name + " has created a new room called: " + roomOptions[2]);
						}
						
						break;
						
					} else if (input.startsWith("CONNECTPLAYERTOROOM")) {
						
						String roomOptions [] = input.split("\\s+");
						
						//roomOptions[0] == CONNECTTOPLAYERROOM
						//roomOptions[1] == ROOM NUMBER
						
						for(PrintWriter writer: writers) {
							writer.println("MESSAGE" + "SYSTEM: " + name + " has joined a room. ");
						}
						
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
	
		public void createNewRoom(int index) throws IOException{
			Room newRoom = gameRoomsList.get(index);
			
			ServerSocket gameServSock = new ServerSocket(0);
			System.out.println("New Game Room Server is running on Port: " + gameServSock.getLocalPort());
			newRoom.setPortNumber(gameServSock.getLocalPort());
			
			newRoom.createGameRoomServer(gameServSock);
			connectToRoom(gameRoomsList.indexOf(newRoom));
		}
		
		public void connectToRoom(int roomNumber) throws IOException{
			String sendThis = "CONNECTTONEWGAMEROOM" + " " + gameRoomsList.get(roomNumber).getPortNumber() + " ";
			
			sendThis += gameRoomsList.get(roomNumber).getGameCols() + " ";
			sendThis += gameRoomsList.get(roomNumber).getGameRows();
			out.println(sendThis);
			
			gameRoomsList.get(roomNumber).recentlyAddedPlayer = name;
			
			if(name != null) { clientNames.remove(name); }
			if(out != null) { writers.remove(out); }
			
		}
		
	}
	
	public static void updateClientsPlayerList(){ 
		String sendThis = "UPDATEPLAYERLIST ";
		sendThis += clientNames.size() + " "; 

		for(String name: clientNames) { sendThis += name + " "; }
		
		for(PrintWriter writer: writers) { writer.println(sendThis); }
	}
	
	public static void updateClientsRoomList(){
		String sendThis = "UPDATEROOMLIST ";
		sendThis += gameRoomsList.size() + " "; 
		for(Room room : gameRoomsList) {
			sendThis += room.getNameOfRoom() + "Players:" + room.getCurrentPlayerCount() 
															 + "/" + room.getMaxPlayers() + " ";
		}
		
		for(PrintWriter writer: writers) { writer.println(sendThis); }
		
	}
	
}
