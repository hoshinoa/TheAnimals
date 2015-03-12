import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private static int port;
	private static ServerSocket servSock;
	//private static ObjectOutputStream out;
	private static PrintWriter out;
	private static BufferedReader in;
	static ClientConnections clients;
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
	public static void main(String[] args) throws Exception{
<<<<<<< HEAD
		
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
=======
		System.out.println("The Server is running.");

		servSock = new ServerSocket(port);
		clients = new ClientConnections();
		Socket connected;

>>>>>>> origin/evelyn-logic
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
		clients.closeConnections();
		servSock.close();//Closes socket after end of session
		*/
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
