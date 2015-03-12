package homescreen;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;

public class HomeScreen extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeScreen frame = new HomeScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomeScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 588);
		getContentPane().setLayout(null);
		
		JList playerList = new JList();
		playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		playerList.setFont(new Font("Tahoma", Font.BOLD, 16));
		playerList.setModel(new AbstractListModel() {
			String[] values = new String[] {"Carrey", "ninetails", "fireFox", "Rocketmouse", "FireFighter298", "joseph", "Hailey"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		playerList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		playerList.setBounds(10, 47, 223, 491);
		getContentPane().add(playerList);
		
		JLabel gameLabel = new JLabel("Games");
		gameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameLabel.setBounds(243, 11, 306, 25);
		getContentPane().add(gameLabel);
		
		JList gameList = new JList();
		gameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gameList.setModel(new AbstractListModel() {
			String[] values = new String[] {"Checkers", "Battleship", "Tic Tac Toe", "Backgammon", "Sorry!", "Monopoly", "Chutes & Ladders"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		gameList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		gameList.setFont(new Font("Tahoma", Font.BOLD, 16));
		gameList.setBounds(243, 47, 306, 433);
		getContentPane().add(gameList);
		
		JButton btnNewButton = new JButton("Play Game");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(243, 491, 306, 47);
		getContentPane().add(btnNewButton);
		
		JLabel playerLabel = new JLabel("Players");
		playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		playerLabel.setBounds(10, 10, 223, 26);
		getContentPane().add(playerLabel);
	}
}
