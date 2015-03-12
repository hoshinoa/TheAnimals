

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private static int port = 8901;
	private static ServerSocket servSock;
	private static PrintWriter out;
	private static BufferedReader in;
	private static PrintWriter out2;
	private static BufferedReader in2;
	//ClientConnections clients;
	//Room[] rooms;
	
	//Server()
	//createRoom()
	
	public static void main(String[] args) throws Exception{
		System.out.println("The Server is running.");
		servSock = new ServerSocket(port);
		
		Socket s = servSock.accept(); //So that we can get an input stream
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		out = new PrintWriter(s.getOutputStream(), true);
		
		Socket s2 = servSock.accept(); //So that we can get an input stream
		in2 = new BufferedReader(new InputStreamReader(s2.getInputStream()));
		out2 = new PrintWriter(s2.getOutputStream(), true);
		
		//To take input from the Server Console itself?
		BufferedReader sysIn = new BufferedReader( new InputStreamReader(System.in));
		
		boolean inSession = true;
		String nextLine = "", nextLine2 = "", sysInput = "";
		while(inSession){
			sysInput = sysIn.readLine();
			nextLine = in.readLine();
			nextLine2 = in2.readLine();
			if("quit".equals(sysInput)){

				System.out.println("Quiting");
				inSession = false;
			}
			else{
				//Reads a line and writes it to the connected socket, sending it to the server
				out.println("[Server: Message received!]");
				out.println(nextLine);
				out2.println(nextLine2);

				//The server echoes the info back, so we can print that here:
				System.out.println("Server: echo of client: " + nextLine);
				System.out.println("Server: echo of client: " + nextLine2);

			}
			
		}
		s2.close();
		s.close();
		servSock.close();//Closes socket after end of session

	}

}