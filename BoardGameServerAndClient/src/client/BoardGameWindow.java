package client;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class BoardGameWindow {
	
	JLabel currentStatus;	
	
	public BoardGameWindow() {
		/*
		 * Board size, gets passed into BoardGenerator to generate the size of board
		 */
		int gridCols = 8;
		int gridRows = 8;
		
		// Initializing frame
		JFrame boardGameFrame = new JFrame("Board Game");
		boardGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		boardGameFrame.setSize(1024, 768);
		boardGameFrame.setResizable(false);
		boardGameFrame.setLayout(new BorderLayout());
		
		// Initializing overall game panel
		JPanel gamePane = new JPanel();
		gamePane.setLayout(new BoxLayout(gamePane, BoxLayout.PAGE_AXIS));
		
		// Initializing board panel
		BoardGenerator testPane = new BoardGenerator(gridCols, gridRows);
		
		// Player 1's Name
		JLabel player1Name = new JLabel("<Player 1 Name>");
		player1Name.setAlignmentX(Component.CENTER_ALIGNMENT);
		player1Name.setPreferredSize(new Dimension(200, 50));
		gamePane.add(player1Name);
		
		// Adding game board pane
		gamePane.add(testPane);
		
		// Player 2's Name
		JLabel player2Name = new JLabel("<Player 2 Name>");
		player2Name.setAlignmentX(Component.CENTER_ALIGNMENT);
		player2Name.setPreferredSize(new Dimension(200, 50));
		gamePane.add(player2Name);
		
		
		boardGameFrame.add(gamePane, BorderLayout.CENTER);
		
		// Initializing status panel
		currentStatus = new JLabel("<Current Status>");
		currentStatus.setHorizontalAlignment(JLabel.CENTER);
		currentStatus.setPreferredSize(new Dimension(200, 50));
		currentStatus.setFont(new Font("Sans Serif", Font.PLAIN, 30));
		boardGameFrame.add(currentStatus, BorderLayout.SOUTH);
		
		
		
		boardGameFrame.pack();
		boardGameFrame.setVisible(true);
	}
	

	public void updateStatus(String status) {
		currentStatus.setText(status);
	}

}
