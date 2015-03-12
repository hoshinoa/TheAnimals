package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private static int port;
	private static ServerSocket servSock;
	//private static ObjectOutputStream out;
	private static PrintWriter out;
	private static BufferedReader in;
	//static ClientConnections clients;
	//Room[] rooms;

	public Server(){
		port = 8901; //Current port choice is arbitrary.
	}

	/**Precondition: The back end of the system must be brought up
	 * 				 to support the connections.
	 * Postcondition: The Server session has ended; sockets are disconnected.
	 * 
	 * The main method of the Server. It opens the Server socket, and while 
	 * there are no fatal exceptions and the System has not told it to quit,
	 * the Server will wait for connections and set up streams whenever
	 * connections from clients are made.
	 * 
	 * @param args (currently not in use)
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("The Server is running.");

		servSock = new ServerSocket(port);
		//clients = new ClientConnections();
		Socket connected;

		BufferedReader sysIn = new BufferedReader( new InputStreamReader(System.in));

		String sysInput = "";
		while(true)
		{
			sysInput = sysIn.readLine();
			connected = waitForConnection();
			if(connected != null)
				setupStreams(connected);
			if("quit".equalsIgnoreCase(sysInput))
				break;
			// & handle other server responsibilities like createRoom()
		}

		connected.close();
		//clients.closeConnections();
		servSock.close();//Closes socket after end of session

	}

	/**Precondition:
	 * Postcondition:
	 * @return
	 */
	private static Socket waitForConnection() throws Exception
	{
		System.out.println("Waiting for someone to connect...\n");
		return servSock.accept();
	}
	
	/**Precondition:
	 * Postcondition:
	 * Should probably set up the Client's info and add it to ClientConnections?
	 * @param s
	 */
	private static void setupStreams(Socket s) throws Exception
	{
		//out = new ObjectOutputStream(s.getOutputStream());
		out = new PrintWriter(s.getOutputStream(), true);
		out.flush();
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		//What do we do with these??
		//call populateClients?
	}
	
	/**Precondition:
	 * Postcondition:
	 * @param 
	 */
	public static void createRoom(){
		//Make a room and put two Clients in where they can choose the game
	}
		
}
