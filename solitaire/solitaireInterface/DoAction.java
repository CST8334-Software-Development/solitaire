package solitaireInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import solitaire.CardPile;
import solitaire.Full_Deck;

public class DoAction {

	private int clicks = 0;
	public void doEverything() {
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
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				
				Full_Deck fullDeck = new Full_Deck();
				CardPile pile = new CardPile();
				Card_Graphics graphics = null; // Lazy initialization
				if (e.getActionCommand().equals("Begin")) {
					int y = 0;
					fullDeck.Shuffle_Deck();
					for (int card1 = 0, i = 30; card1 < 52 && i < 132; card1 ++, i +=2 ) {
					fullDeck.getCard(card1).generateCard(fullDeck.getCard(card1).getImagePath()); // Generates the card graphic
					pile.push(fullDeck.getCard(card1));
					pile.setPosition(30, i);
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
							Card_Graphics graphics = null;
							int y = 130;
							@Override
							public void mousePressed(MouseEvent e) {
								clicks = clicks + 1;
								y = y+(clicks*20);
								panel1.remove(0);
								System.out.println(e.getClickCount());
								graphics = new Card_Graphics(100, y, pile.getCard(clicks).returnCardImage());
								graphics.setBorder(b2); 
								graphics.setSize(85, 119); 
								myList.set(clicks, graphics); 
								panel1.add(graphics); 
								panel1.repaint();
								}
						});
						myList.add(graphics); // adds each card to panel
						for (int i1 = 0; i1 < myList.size(); i1++) {
							panel1.add(myList.get(i1));
							panel1.repaint();
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
