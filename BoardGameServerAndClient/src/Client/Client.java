package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client {

	private BufferedReader in;
	private PrintWriter out;
	JFrame frame = new JFrame("Chatter");
	JTextField textField = new JTextField(40);
	JTextArea messageArea = new JTextArea(8,40);
	
	public Client(){
		textField.setEditable(false);
		messageArea.setEditable(false);
		frame.getContentPane().add(textField, "North");
		frame.getContentPane().add(new JScrollPane(messageArea), "Center");
		
		textField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				out.println(textField.getText());
				textField.setText("");
			}
		});
	}
	
	private String getName() {
		return JOptionPane.showInputDialog(
				frame,
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
		String portNumber = getPortNumber();
		
        Socket socket = new Socket(serverAddress, Integer.parseInt(portNumber));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        
        while(true) {
        	String line = in.readLine();
        	System.out.println(line);
        	if(line.startsWith("SUBMITNAME")){
        		out.println(getName());
        	} else if(line.startsWith("NAMEACCEPTED")) {
        		textField.setEditable(true);
        	} else if (line.startsWith("MESSAGE")){
        		messageArea.append(line.substring(8) + "\n");
        	}
        }
	}
	
	public static void main(String[] args) throws IOException{
		Client client = new Client();
		client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.frame.setVisible(true);
		client.run();
	}

}
