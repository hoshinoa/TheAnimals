import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

    /*
     * Each cell pane has it's own mouse listener - turns background blue, turns red when clicked
     */
    public class BoardTile extends JPanel {

		private static final long serialVersionUID = 1L;
		private Color defaultBackground;
		public int x;
		public int y;
	
        public BoardTile(int col, int row) {
        	this.x = col;
        	this.y = row;
        	mouseListen();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(75, 75);
        }
        
        private void mouseListen() {
        	// Mouse functions
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    defaultBackground = getBackground();
                    setBackground(Color.BLUE);
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    setBackground(defaultBackground);
                }
                /*
                 * Mouse click here
                 */
                @Override
                public void mousePressed(MouseEvent e) {
                    setBackground(Color.RED);
                    System.out.println("Clicked at: x:" + x + " y: " + y);
                }
            });
        }
        
        /*
        // Get coordinates
        public int getX() {
        	return x;
        }
        public int getY() {
        	return y;
        }
        */
    }