package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	//Main
	public static void main(String[] args) throws IOException{
		
		ServerSocket servSock = new ServerSocket(0);
		System.out.println("Server is running on Port: " + servSock.getLocalPort());
		try{
			while(true){
				Socket socket = servSock.accept();
				try {
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					out.println("meow");
				} finally {
					socket.close();
				}
			}
		} finally {
			servSock.close();
		}
		
	}
	//End of Main

}
