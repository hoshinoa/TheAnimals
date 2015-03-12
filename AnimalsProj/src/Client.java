

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	private static int port = 8901;
	
	public static void disconnect(){
		//disconnect
	}

	public static void main(String[] args) throws Exception{
		Socket clientSocket = new Socket("localhost", port);

		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
		BufferedReader sysIn = new BufferedReader( new InputStreamReader(System.in));
		System.out.println("Starting the client.");
		String userInput;
        while (true) {
        	userInput = sysIn.readLine();
        	if("quit".equalsIgnoreCase(userInput))
        		break;
        	out.println(userInput);
        	System.out.println("Client received response:" + in.readLine());
        }
	}

}