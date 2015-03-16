package client;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class BoardGameWindow {
	
	JLabel currentStatus;	
	JPanel gamePane;
	
	public BoardGameWindow(int cols, int rows) {
		/*
		 * Board size, gets passed into BoardGenerator to generate the size of board
		 */
		int gridCols = cols;
		int gridRows = rows;

		// Initializing overall game panel
		gamePane = new JPanel();
		
		// Initializing board panel
		BoardGenerator gridPane = new BoardGenerator(gridCols, gridRows);
		
		// Adding game board pane
		gamePane.add(gridPane);
	
	}
	

	public JPanel getGameFrame(){
		return this.gamePane;
	}
	
	public void updateStatus(String status) {
		currentStatus.setText(status);
	}

}
