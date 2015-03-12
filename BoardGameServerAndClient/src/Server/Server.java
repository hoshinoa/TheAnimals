package server;

import java.io.IOException;

import java.net.ServerSocket;


public class Server {

	private static ServerSocket servSock;
	
	public static void main(String[] args) {
		
		try { //Open socket on a random usable port
			servSock = new ServerSocket(0);
			System.out.println("The Server is running on port: " + servSock.getLocalPort());
		} catch (IOException e) {
			System.err.println("No available ports, closing now");
			System.exit(-1);
		}
		
		//while();
		
		
		try { //Close the socket 
			servSock.close(); 
		} catch (IOException e) {
			System.err.println("There was a problem closing the server");
			System.exit(-1);
		}
		
	}


		
}
