package client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Client {

	private BufferedReader in;
	private PrintWriter out;
	
	private HomeScreen homeScreen;
	private String serverAddress;
	private String portNumber;
	
	public Client(){
		homeScreen = new HomeScreen();
		homeScreen.textField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				out.println(homeScreen.textField.getText());
				homeScreen.textField.setText("");
			}
		});
		
		//Make new room button
		homeScreen.makeNewRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sendThis = "MAKENEWROOM" + " ";
				String choice = homeScreen.showGamesList();
				if(choice != null) {
					sendThis += choice;
					out.println(sendThis);
				}
			}
		});
		
		homeScreen.gameList.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent le) {
		    	  if(homeScreen.gameList.getSelectedIndex() != -1 && !le.getValueIsAdjusting()) {
		    		  String sendThis = "CONNECTPLAYERTOROOM" + " " + homeScreen.gameList.getSelectedIndex();
		    		  out.println(sendThis);
		    	  }
		      }
		    });
	}
	
	private String getName() {
		return JOptionPane.showInputDialog(
				homeScreen,
				"Choose a user name",
				"Screen name selection",
				JOptionPane.PLAIN_MESSAGE).replace(' ','_');
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
		//serverAddress = getServerAddress();
		serverAddress = "localhost";
		//portNumber = getPortNumber();
		portNumber = "8901";
		
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
        		String playerList [] = line.split("\\s+");
        		homeScreen.updatePlayerList(playerList);
        	} else if(line.startsWith("UPDATEROOMLIST")) {
        		String roomList [] = line.split("\\s+");
        		homeScreen.updateRoomList(roomList);
        	} else if(line.startsWith("CONNECTTONEWGAMEROOM")) {
        		connectToNewGameRoom(line);
        		break;
        	}
        }
        
        
	}
	
	public void connectToNewGameRoom(String instructions) throws IOException{
		homeScreen.dispose();
		
		String info [] = instructions.split("\\s+");
		//info[0] = CONNECTTONEWGAMEROOM
		//info[1] = PORTNUMBER
		//info[2] = COLS
		//info[3] = ROWS
		portNumber = info[1];
		final GameRoomScreen newRoom = new GameRoomScreen(Integer.parseInt(info[2]), Integer.parseInt(info[3]));
		newRoom.textField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				out.println(newRoom.textField.getText());
				newRoom.textField.setText("");
			}
		});
		
		newRoom.setVisible(true);
		
        Socket socket = new Socket(serverAddress, Integer.parseInt(portNumber));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        
        for (BoardTile[] row: newRoom.boardGame.gridPane.boardArray) {
        	for (final BoardTile tile: row) { 
    	    	tile.addMouseListen(new MouseAdapter(){

    			    @Override
    			    public void mousePressed(MouseEvent e) {
    			    	//tile.setBackground(Color.RED);
    			    	if(tile.enabled) {
    			    		out.println( "MOVEMADE " + tile.x + " " + tile.y);
    			    	}
    			    }
    	    }); 
    	    } 
		}
        
        while(true) {
        	String line = in.readLine();
        	if (line.startsWith("MESSAGE")){
        		newRoom.messageArea.append(line.substring(7) + "\n");
        	} else if(line.startsWith("MAKEMOVE")) {
        		for (BoardTile[] row: newRoom.boardGame.gridPane.boardArray) {
				    for (final BoardTile tile: row) { 
				    	tile.enabled = true;
				    } }
        		
			} else if(line.startsWith("WAIT")) {
				for (BoardTile[] row: newRoom.boardGame.gridPane.boardArray) {
				    for (final BoardTile tile: row) { 
				    	tile.enabled = false;
				    } }
				
			} else if(line.startsWith("PLACEPIECE")){
				String placePieceInstructions [] = line.split("\\s+");
				//placePieceInstructions[0] == "PLACEPIECE"
				//placePieceInstructions[1] == Piece to place 'x' or 'o'
				//placePieceInstructions[2] == col
				//placePieceInstructions[3] == row
				
				newRoom.boardGame.gridPane.boardArray[Integer.parseInt(placePieceInstructions[2])][Integer.parseInt(placePieceInstructions[3])].label.setText(placePieceInstructions[1]);
				
			} else if(line.startsWith("INVALIDMOVE")) {
				//out.println("The move was invalid please try again");
			}
        	else if(line.startsWith("FINISH")) {
        		break;
        	}
        }
       
	}
	
	public static void main(String[] args) throws IOException{
		Client client = new Client();
		client.homeScreen.setVisible(true);
		client.run();
	}

}
