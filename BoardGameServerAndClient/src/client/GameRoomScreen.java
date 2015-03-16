package client;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GameRoomScreen extends JFrame{

	JTextField textField = new JTextField(40);
	JTextArea messageArea = new JTextArea(8,40);
	
	public GameRoomScreen() {
		setTitle("Game Room");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		setResizable(false);
		getContentPane().setLayout(null);
		
		//Chat Interface
		JLabel chatLabel = new JLabel("Chat");
		chatLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		chatLabel.setHorizontalAlignment(SwingConstants.CENTER);
		chatLabel.setBounds(560, 11, 306, 25);
		getContentPane().add(chatLabel);
		
		textField = new JTextField(40);
		messageArea = new JTextArea(8,40);
		messageArea.setEditable(false);
		
		JScrollPane messagesHolder = new JScrollPane(messageArea);
		messagesHolder.setBounds(560, 50, 250, 400);
		getContentPane().add(messagesHolder);
		textField.setBounds(560, 491, 250, 30);
		getContentPane().add(textField);
	}
	
	//For testing purposes uncomment if you want
	/*
	public static void main(String args[]) {
		GameRoomScreen test = new GameRoomScreen();
		test.setVisible(true);
	}
	*/
	
}
