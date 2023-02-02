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
	private static Solitaire game = null;
	private static JFrame mainFrame;
	public static OurMenu menuBar;
		
		public static void main(String[] args) {
			EventQueue.invokeLater(() ->
			 {
				 if (mainFrame!=null) mainFrame.dispose();
				mainFrame = new JFrame("Solitaire");
				createShow();
			 });
		}
		
		static void initGame() {
				mainFrame.remove(game);
				mainFrame.revalidate();
				createShow();
				mainFrame.revalidate();
				mainFrame.repaint();
		}
		
		private static void createShow() {
				game = new Solitaire();
				menuBar = new OurMenu();
					mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					mainFrame.setSize(TABLE_WIDTH,TABLE_HEIGHT);
					mainFrame.setResizable(false);
					mainFrame.setJMenuBar(menuBar);
					mainFrame.add(game);
					mainFrame.getContentPane().setBackground(Color.ORANGE);
					mainFrame.setVisible(true);
			 }
			
		}
	
	
		
	

