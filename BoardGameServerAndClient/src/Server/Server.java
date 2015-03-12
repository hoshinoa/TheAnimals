package server;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Server {

	/*
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

	*/
	
	public static void main(String[] args) {
		
		//Step 1 Show a JFrame choosing the port number to run on
		Server.ServerDialogs helper = new Server.ServerDialogs();
		helper.getPortNumber();
		
		//System.out.println("The Server is running.");
		/*
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
		*/

	}
	
	static class ServerDialogs{
		
		private JFrame frame;
		
		/**
		 * 
		 */
		public void getPortNumber(){
			frame = new JFrame("Server Settings");
			frame.setSize(500, 500);
			frame.getContentPane().add(new JTextField());
			System.out.println("Meow");
			frame.setVisible(true);
		}
		
	}

	/*
	private static Socket waitForConnection() throws Exception
	{
		System.out.println("Waiting for someone to connect...\n");
		return servSock.accept();
	}
	

	private static void setupStreams(Socket s) throws Exception
	{
		//out = new ObjectOutputStream(s.getOutputStream());
		out = new PrintWriter(s.getOutputStream(), true);
		out.flush();
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		//What do we do with these??
		//call populateClients?
	}
	
	public static void createRoom(){
		//Make a room and put two Clients in where they can choose the game
	}
	*/
		
}
