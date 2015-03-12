package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Client {

	public static void main(String[] args) throws IOException{
		String serverAddress = "localhost";
        String portNumber = JOptionPane.showInputDialog(
                "Enter Port Number the Server is running on");
        //TODO add some error handling for non valid port numbers
        Socket s = new Socket(serverAddress, Integer.parseInt(portNumber));
        BufferedReader input =
            new BufferedReader(new InputStreamReader(s.getInputStream()));
        String answer = input.readLine();
        JOptionPane.showMessageDialog(null, answer);
        System.exit(0);

	}

}
