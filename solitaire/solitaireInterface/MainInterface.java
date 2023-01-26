
package solitaireInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import solitaire.Card;
import solitaire.Full_Deck;

public class MainInterface {

	public static void main(String[] args) {

		int x = 0;

		JFrame myFrame = new JFrame("Solitaire");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setResizable(false);
		myFrame.setSize(new Dimension(800, 600));
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		panel1.setLayout(null);
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		JButton start = new JButton("Begin");
		panel.add(start);
		ArrayList<Card_Graphics> myList = new ArrayList<>();
		ArrayList<Card> myCards = new ArrayList<>();
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Full_Deck fullDeck = new Full_Deck();
				Card_Graphics graphics = null; // Lazy initialization
				if (e.getActionCommand().equals("Begin")) {
					int y = 0;
					for (int card1 = 0, i = 30; card1 < 52 && i < 132; card1 ++, i +=2 ) {
					fullDeck.getCard(card1).generateCard(fullDeck.getCard(card1).getImagePath()); // Generates the card graphic
					Border b2 = new LineBorder(Color.BLACK, 1); // Border default value
					// So far only generates a few cards default y position is 30
						y = i; // Set the y value to the loop as it iterates
						graphics = new Card_Graphics(30, y, fullDeck.getCard(card1).returnCardImage()); // After the lazy initialization,												// within the loop
						graphics.setBorder(b2); // sets border to each graphic
						graphics.setSize(85, 119); // set size method
						/*When clicking on the card, it will generate a card in a different location. It will remove the current uppermost
						 * card from the view. 
						 * */
						graphics.addMouseListener(new MouseAdapter() { 
							@Override
							public void mousePressed(MouseEvent e) {
								int y = 0;
								Card_Graphics graphics = null;
								graphics = new Card_Graphics(100, 150, fullDeck.getCard(0).returnCardImage());
								graphics.setBorder(b2); 
								graphics.setSize(85, 119); 
								myList.set(0, graphics); 
								panel1.remove(0);
								panel1.add(myList.get(0));    
								panel1.repaint();
							}
						});
						myList.add(graphics); // adds each card to panel
						for (int i1 = 0; i1 < myList.size(); i1++) {
							panel1.add(myList.get(i1));
							panel1.repaint();
							System.out.println(myList.size());
						}
					}
					}
				}
		});
		myFrame.add(panel, BorderLayout.SOUTH);
		panel1.setBackground(Color.ORANGE); // background color can be set outside of the loop
		panel1.setVisible(true);
		myFrame.add(panel1);
		myFrame.setVisible(true);
	}
}