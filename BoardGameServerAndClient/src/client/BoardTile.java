package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

    public class BoardTile extends JPanel {

		private static final long serialVersionUID = 1L;
		private Color defaultBackground;
		public int x;
		public int y;
	
        public BoardTile(int col, int row) {
        	this.x = col;
        	this.y = row;
        	//mouseListen();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(50,50);
        } 
        
        public void addMouseListen(MouseAdapter mew) { addMouseListener(mew); }
    }
 