package client;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

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
	
	private JList playerList;
	private JList gameList;
	
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
		chatLabel.setBounds(550, 11, 306, 25);
		getContentPane().add(chatLabel);
		
		textField = new JTextField(40);
		messageArea = new JTextArea(8,40);
		
		//left side of the screen user list
		JScrollPane messagesHolder = new JScrollPane(messageArea);
		messagesHolder.setBounds(550, 50, 250, 400);
		getContentPane().add(messagesHolder);
		textField.setBounds(550, 491, 250, 30);
		getContentPane().add(textField);
		//right side of the screen rooms list and make new room
		
		
		//Player List
		
		JLabel playerLabel = new JLabel("Waiting Room - Players: ");
		playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		playerLabel.setBounds(10, 10, 223, 26);
		getContentPane().add(playerLabel);
		
		playerList = new JList();
		playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		playerList.setFont(new Font("Tahoma", Font.BOLD, 16));
		/*
		playerList.setModel(new AbstractListModel() {
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {"Carrey", "ninetails", "fireFox", "Rocketmouse", "FireFighter298", "joseph", "Hailey"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		}); */
		
		playerList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		playerList.setBounds(10, 47, 223, 491);
		getContentPane().add(playerList);
		
		//Game List
		JLabel gameLabel = new JLabel("Rooms");
		gameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameLabel.setBounds(243, 11, 306, 25);
		getContentPane().add(gameLabel);
		
		//gameList = new JList();
		//gameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
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
		
		//gameList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		//gameList.setFont(new Font("Tahoma", Font.BOLD, 16));
		//gameList.setBounds(243, 47, 306, 433);
		//getContentPane().add(gameList);
		
		//Button for some action
		JButton makeNewRoom = new JButton("Make New Room");
		makeNewRoom.setFont(new Font("Tahoma", Font.PLAIN, 24));
		makeNewRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Making new room");
			}
		});
		makeNewRoom.setBounds(243, 491, 306, 47);
		getContentPane().add(makeNewRoom);
		
	}
	
	public void updatePlayerList(){
		
	}
	
	public void updateRoomList(){
		
	}
	
	public void updateView(){
		
	}
	
	//Uncomment main to test the screen
	/*
	public static void main(String args[]){
		HomeScreen test = new HomeScreen();
		test.setVisible(true);
	}
	*/
	
	
}
