package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client {

	private BufferedReader in;
	private PrintWriter out;
	
	private HomeScreen homeScreen = new HomeScreen();
	
	public Client(){
		
		homeScreen.textField.setEditable(false);
		homeScreen.messageArea.setEditable(false);
		homeScreen.textField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				out.println(homeScreen.textField.getText());
				homeScreen.textField.setText("");
			}
		});
	}
	
	private String getName() {
		return JOptionPane.showInputDialog(
				homeScreen,
				"Choose a user name",
				"Screen name selection",
				JOptionPane.PLAIN_MESSAGE);
	}
	
	private String getPortNumber() {
		return JOptionPane.showInputDialog(
				"Enter Port Number the Server is running on");
        //TODO add some error handling for non valid port numbers
	}
	
	private String getServerAddress(){
		return JOptionPane.showInputDialog(
				"Enter Server IP the Server is running on");
		//TODO add some error handling for non valid IP Address
	}
	
	private void run() throws IOException {
		//String serverAddress = getServerAddress();
		String serverAddress = "localhost";
		//String portNumber = getPortNumber();
		String portNumber = "8901";
		
        Socket socket = new Socket(serverAddress, Integer.parseInt(portNumber));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        
        while(true) {
        	String line = in.readLine();
        	if(line.startsWith("SUBMITNAME")){
        		out.println(getName());
        	} else if(line.startsWith("NAMEACCEPTED")) {
        		homeScreen.textField.setEditable(true);
        	} else if (line.startsWith("MESSAGE")){
        		homeScreen.messageArea.append(line.substring(7) + "\n");
        	} else if(line.startsWith("UPDATEPLAYERLIST")) {
        		System.out.println("I'm updating player list");
        		//Need to update player list
        		String playerList [] = line.split("\\s+");
        		for(String player : playerList){
        			//Change Homescreen
        			System.out.println(player);
        		}
        	}
        }
        
        
	}
	
	public static void main(String[] args) throws IOException{
		Client client = new Client();
		client.homeScreen.setVisible(true);
		client.run();
	}

}

//TODO add a graceful disconnect for users
