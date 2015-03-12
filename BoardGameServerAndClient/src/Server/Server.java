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
    
    private static HashSet<Room> rooms = new HashSet<Room>();
    
	//Main
	public static void main(String[] args) throws IOException{
		
		ServerSocket servSock = new ServerSocket(0);
		System.out.println("Server is running on Port: " + servSock.getLocalPort());
		try{
			while(true){
				new Handler(servSock.accept()).start();
			}
		} finally { servSock.close(); }
		
	}
	//End of Main
	
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
							System.out.println("Added to room: " + name);
							break;
						}
					}
				}
				
				out.println("NAMEACCEPTED");
				System.out.println("NAMEACCEPTED");
				writers.add(out);
				
				while(true) {
					String input = in.readLine();
					//if(input == null) { return; }
					if(input.startsWith("SEND")){
						
						//TODO JSON stuff //need to choose a json parsing library -> JSONObject looks fine
						String sendThis = "";
						sendThis = clientNames.size() + "";
						for(String name: clientNames) {
							sendThis += name + " ";
						}
						out.println(sendThis);
					} else {
						System.out.println(input);
					}
					//check for actions
					//make room
					//enter a room
					
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
	
}

/*
 * 				/*
				//Chat Messaging system
				while(true) {
					String input = in.readLine();
					if(input == null) { return; }
					for(PrintWriter writer: writers) {
						writer.println("MESSAGE" + name + ": " + input);
					}
				} */
 
