package client;

import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class BoardGameWindow {
	
	BoardGenerator gridPane;
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
		gridPane = new BoardGenerator(gridCols, gridRows);
		
		// Adding game board pane
		gamePane.add(gridPane);
	
	}
	
	public void printBoard(){
		System.out.println(Arrays.deepToString(gridPane.boardArray));
	}

}
