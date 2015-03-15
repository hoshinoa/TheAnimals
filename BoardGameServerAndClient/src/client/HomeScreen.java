package client;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.util.Arrays;

import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;


//Notes: Absolute positioning through (int x, int y, int width, int height) function.

public class HomeScreen extends JFrame {
	
	private JList<String> playerList;
	private JList<String> gameList;
	
	JButton makeNewRoom;
	
	//Chat Interface
	JTextField textField = new JTextField(40);
	JTextArea messageArea = new JTextArea(8,40);
	
	public HomeScreen() {
		setTitle("Waiting Room");
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
		
		//left side of the screen user list
		JScrollPane messagesHolder = new JScrollPane(messageArea);
		messagesHolder.setBounds(560, 50, 250, 400);
		getContentPane().add(messagesHolder);
		textField.setBounds(560, 491, 250, 30);
		getContentPane().add(textField);
		//right side of the screen rooms list and make new room
		
		
		//Player List
		//TODO make JScrollPanel for names
		JLabel playerLabel = new JLabel("Waiting Room - Players: ");
		playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		playerLabel.setBounds(10, 10, 223, 26);
		getContentPane().add(playerLabel);
		
		playerList = new JList<String>();
		playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		playerList.setFont(new Font("Tahoma", Font.BOLD, 16));
		playerList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		playerList.setBounds(10, 47, 223, 491);
		getContentPane().add(playerList);
		
		//Game List
		JLabel gameLabel = new JLabel("Open Rooms");
		gameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameLabel.setBounds(243, 11, 306, 25);
		getContentPane().add(gameLabel);
		
		//TODO Add JScrollPane for Rooms/Game list
		gameList = new JList<String>();
		gameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		/*
		gameList.setModel(new AbstractListModel() {
			String[] values = new String[] {"Checkers", "Battleship", "Tic Tac Toe", "Backgammon", "Sorry!", "Monopoly", "Chutes & Ladders"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		*/
		/*
		gameList.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent le) {
		        int idx = gameList.getSelectedIndex();
		        if (idx != -1)
		          System.out.println("Current selection: " + gameList.getModel().getElementAt(idx));
		        else
		          System.out.println("Please choose a language.");
		      }
		    });
		*/
		
		gameList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		gameList.setFont(new Font("Tahoma", Font.BOLD, 16));
		gameList.setBounds(243, 47, 306, 433);
		getContentPane().add(gameList);
		
		//Button for some action
		makeNewRoom = new JButton("Make New Room");
		makeNewRoom.setFont(new Font("Tahoma", Font.PLAIN, 24));
		makeNewRoom.setBounds(243, 491, 306, 47);
		getContentPane().add(makeNewRoom);
		
	}
	
	public void updatePlayerList(final String[] players){
		playerList.setListData(Arrays.copyOfRange(players, 2, players.length));
	}
	
	public void updateRoomList(final String[] rooms){
		gameList.setListData(Arrays.copyOfRange(rooms, 2, rooms.length));
	}
	
	
	//Uncomment main to test the screen
	/*
	public static void main(String args[]){
		HomeScreen test = new HomeScreen();
		test.setVisible(true);
	}
	*/
	
	
}