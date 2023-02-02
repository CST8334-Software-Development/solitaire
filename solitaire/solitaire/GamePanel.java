package solitaire;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

public class GamePanel {
	final static int TABLE_HEIGHT = 600;
	final static int TABLE_WIDTH = 800;
	static Solitaire game = null;
	private static JFrame mainFrame;
		
		public static void main(String[] args) {
			EventQueue.invokeLater(() ->
			 {
				 if (mainFrame!=null) mainFrame.dispose();
				mainFrame = new JFrame("Solitaire");
				createShow();
			 });
		}
		
		private static void createShow() {
				
				JMenuBar menuBar = new JMenuBar();
				JMenu newGameMenu = new JMenu("Game");
				JMenuItem newGameItem = new JMenuItem("New Game");
					mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					mainFrame.setSize(TABLE_WIDTH,TABLE_HEIGHT);
					mainFrame.setResizable(false);

					newGameItem.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							game = new Solitaire();
							mainFrame.getContentPane().add(game);
							mainFrame.setVisible(true);
						}
					});
					JMenuItem exitItem = new JMenuItem("Exit");
					exitItem.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							mainFrame.dispose();
							System.exit(0);
						}
					});
					newGameMenu.add(newGameItem);
					newGameMenu.add(exitItem);
					menuBar.add(newGameMenu);
					mainFrame.setJMenuBar(menuBar);
					mainFrame.getContentPane().setBackground(Color.ORANGE);
					mainFrame.setVisible(true);
			 }
			
		}
	
	
		
	

