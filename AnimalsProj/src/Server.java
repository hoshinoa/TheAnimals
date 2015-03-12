import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private static int port = 8901;
	private static ServerSocket servSock;
	private static PrintWriter out;
	private static BufferedReader in;
	//ClientConnections clients;
	//Room[] rooms;
	
	//Server()
	//createRoom()
	
	public static void main(String[] args) throws Exception{
		
		try{
			servSock = new ServerSocket(port);
			Socket s = servSock.accept(); //So that we can get an input stream
			System.out.println("The Server is running.");
		} catch(BindException e) { //Server is already running
			System.out.println("Server is already running...");
			System.exit(0);
			
			//Instructions to close the Server if you can't close it
			// (Windows) Close terminal or restart pc lol
			// (Mac or linux based) open a terminal
			// run lsof -i:PORT <- WHERE PORT IS THE PORT NUMBER OF THE SERVER
			// find the PID(PROCESS ID) and run kill -9 PID command <- WHERE PID IS THE PROCESS ID 
			// if all else fails just restart
		}
		
		/*
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		out = new PrintWriter(s.getOutputStream(), true);
		
		//To take input from the Server Console itself?
		BufferedReader sysIn = new BufferedReader( new InputStreamReader(System.in));
		
		boolean inSession = true;
		String nextLine = "", sysInput = "";
		while(inSession){
			sysInput = sysIn.readLine();
			nextLine = in.readLine();
			if("quit".equals(sysInput)){

				System.out.println("Quiting");
				inSession = false;
			}
			else{
				//Reads a line and writes it to the connected socket, sending it to the server
				out.println("[Server: Message received!]");
				out.println(nextLine);
				//The server echoes the info back, so we can print that here:
				System.out.println("Server: echo of client: " + nextLine);
			}
			
		}
		s.close();
		servSock.close();//Closes socket after end of session
		*/
	}

}
