package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

    public class BoardTile extends JPanel {

		private static final long serialVersionUID = 1L;
		private Color defaultBackground;
		public int x;
		public int y;
		public boolean enabled;
		
		public JLabel label; //switch to image if you want to render an image
		
        public BoardTile(int col, int row) {
        	//x should equal row, and y should equal col, but whatever;
        	this.x = col;
        	this.y = row;
        	defaultBackground = this.getBackground();
        	label = new JLabel("-");
        	this.add(label);
        	enabled = false;
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(50,50);
        } 
        
        public void addMouseListen(MouseAdapter mew) { addMouseListener(mew); }
    }
 