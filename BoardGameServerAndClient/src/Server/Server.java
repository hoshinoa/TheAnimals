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
				//TODO added a new person so update client's player list
				for(PrintWriter writer: writers) {
					String sendThis = "UPDATEPLAYERLIST " + updateClientsPlayerList(); 
					System.out.println(sendThis);
					writer.println(sendThis);
				}
				//TODO going along with graceful loss of user, needs to update on lost connection, some ping function
				
				while(true) {
					String input = in.readLine();
					//if(input == null) { return; }
					if(input.startsWith("USERS")){
						
						System.out.println("USERS");
						
					} else if (input.startsWith("MAKENEWROOM")){
						//Spawn a new room
						
						synchronized (gameRooms) {
							Room newRoom = new Room("Game Room :" + gameRooms.size());
							gameRooms.add(newRoom);
						}
						
						//TODO added a new room so update client's game room list
						
					} else if(input.startsWith("LISTOFROOMS")){
						//TODO JSONIFY this
						String sendThis = "Num rooms: " + gameRooms.size() + '\n';
						for(Room room : gameRooms) {
							sendThis += room.getNameOfRoom() + " | Players: " + room.getCurrentPlayerCount() 
																			 + "/" + room.getMaxPlayers() + '\n';
						}
						out.println(sendThis);
						
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
		
	}
	
	public static String updateClientsPlayerList(){ //get player list
		//TODO JSON stuff //need to choose a json parsing library -> JSONObject looks fine
		String sendThis = "";
		sendThis = clientNames.size() + " "; //Number of clients

		for(String name: clientNames) {
			sendThis += name + " ";
		}
		
		return sendThis; //returns a single string of all the clients separated by \n's
	}
	
	public void updateClientsRoomList(){
		
	}
	
}
