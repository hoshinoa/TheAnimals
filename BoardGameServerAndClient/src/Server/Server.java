package server;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Server {

	
	private static ServerSocket servSock;
	//private static ObjectOutputStream out;
	private static PrintWriter out;
	private static BufferedReader in;
	//static ClientConnections clients;
	//Room[] rooms;
	
	
	public static void main(String[] args) {
		
		try {
			servSock = new ServerSocket(0);
			System.out.println("The Server is running on port: " + servSock.getLocalPort());
		} catch (IOException e) {
			System.err.println("No available ports, closing now");
			System.exit(0);
		}
		
		//clients = new ClientConnections();
		Socket connected;

		BufferedReader sysIn = new BufferedReader( new InputStreamReader(System.in));
		
		/*
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
