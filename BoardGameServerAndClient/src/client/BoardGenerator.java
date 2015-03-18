package client;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class BoardGenerator extends JPanel {

		private static final long serialVersionUID = 1L;
		BoardTile boardArray[][];
		
		public BoardGenerator(int gridCols, int gridRows) {
			
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            
            /*
             * Generating visual grid
             */
            boardArray = new BoardTile[gridCols][gridRows];
            
            for (int row = 0; row < gridRows; row++) {
                for (int col = 0; col < gridCols; col++) {
                    gbc.gridx = col;
                    gbc.gridy = row;

                    BoardTile cellPane = new BoardTile(col, row);
                    Border border = null;
                    
                    /*
                     * Grid outline borders
                     */
                    if (row < gridRows-1) {
                        if (col < gridCols-1) {
                            border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
                        } else {
                            border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
                        }
                    } else {
                        if (col < gridCols-1) {
                            border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
                        } else {
                            border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                        }
                    }
                    cellPane.setBorder(border);
                    add(cellPane, gbc);
                    
                    boardArray[col][row] = cellPane;
                }
            }
        }
		
		public void changeMouseListeners(String type){
			
			for (BoardTile[] row: boardArray) {
			    for (final BoardTile tile: row) { tile.addMouseListen(new MouseAdapter(){
			    	 @Override
					    public void mouseEntered(MouseEvent e) {
					        
					    }
					    
					    @Override
					    public void mouseExited(MouseEvent e) {
					        
					    }

					    @Override
					    public void mousePressed(MouseEvent e) {
					        tile.setBackground(Color.RED);
					       //TODO set a string for the most recently touched position
					    }
			    }); 
			    } } }
		
    } // end of class declaration