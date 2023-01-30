package solitaireInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import solitaire.Card;
import solitaire.CardPile;
import solitaire.Full_Deck;
import solitaire.TableauPile;

public class DoAction {

	private int clicks = 0;
	private Full_Deck Deck;
	final static int TABLE_HEIGHT = 600;
	final static int TABLE_WIDTH = 800;
	final static int DEFAULT_GAP = 20;
	final static int NUM_FOUNDATION_PILE = 4;
	final static int NUM_TABLEAU_PILE = 7;
	final static int BIG_GAP = TABLE_HEIGHT - 6 * (Card.CARD_WIDTH + DEFAULT_GAP);
	JFrame myFrame = new JFrame("Solitaire");
	JPanel panel = new JPanel();
	JPanel panel1 = new JPanel();
	private ArrayList<TableauPile> tableauPiles;
	// shuffle the full deck created above

	public void startGame() {

		Deck = new Full_Deck();
		Deck.Shuffle_Deck();
		// put 1 card in Tableau 1
		tableauPiles = new ArrayList<TableauPile>();

		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setResizable(false);
		myFrame.setSize(new Dimension(800, 600));


		TableauPile tableauPile1 = new TableauPile();
		for (int i = 1; i <= 1; i++) {
			tableauPile1.push(Deck.getCard(i + 23));
		}
		tableauPiles.add(tableauPile1);

		// put 2 cards in Tableau 2
		TableauPile tableauPile2 = new TableauPile();
		for (int i = 1; i < 3; i++) {
			tableauPile2.push(Deck.getCard(i + 24));
		}
		tableauPiles.add(tableauPile2);

		// put 3 cards in Tableau 3
		TableauPile tableauPile3 = new TableauPile();
		for (int i = 1; i < 4; i++) {
			tableauPile3.push(Deck.getCard(i + 26));
		}
		tableauPiles.add(tableauPile3);

		// put 4 cards in Tableau 4
		TableauPile tableauPile4 = new TableauPile();
		for (int i = 1; i < 5; i++) {
			tableauPile4.push(Deck.getCard(i + 29));
		}
		tableauPiles.add(tableauPile4);

		// put 5 cards in Tableau 5
		TableauPile tableauPile5 = new TableauPile();
		for (int i = 1; i < 6; i++) {
			tableauPile5.push(Deck.getCard(i + 33));
		}
		tableauPiles.add(tableauPile5);

		// put 6 cards in Tableau 6
		TableauPile tableauPile6 = new TableauPile();
		for (int i = 1; i < 7; i++) {
			tableauPile6.push(Deck.getCard(i + 38));
		}
		tableauPiles.add(tableauPile6);
		
		// put 7 cards in Tableau 7
		TableauPile tableauPile7 = new TableauPile();
		for (int i = 1; i < 8; i++) {
			tableauPile7.push(Deck.getCard(i + 44));
		}
		tableauPiles.add(tableauPile7);

		// set the last card of the tableau (last index) to a status "revealed" (boolean
		// to true for the variable "revealed"
		// The boolean revealed set to true indicate to swing that the card should be
		// shown
		for (TableauPile tableauPile : tableauPiles) {
			tableauPile.top().setRevealed();
		}

		panel1.setLayout(null);
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		JButton start = new JButton("Begin");
		panel.add(start);
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Begin")) {
					// TABLE1 draw at
					panel1 = putTableauPile(tableauPiles, 0,new Point(DEFAULT_GAP,DEFAULT_GAP+Card.CARD_HEIGHT+2*DEFAULT_GAP));
					// TABLE2 draw at
					panel1 = putTableauPile(tableauPiles, 1,
					new Point(2 * DEFAULT_GAP + Card.CARD_WIDTH, DEFAULT_GAP + Card.CARD_HEIGHT + 2 * DEFAULT_GAP));
					// TABLE3 draw at
					panel1 = putTableauPile(tableauPiles, 2,
					new Point(3 * DEFAULT_GAP + 2 * Card.CARD_WIDTH, DEFAULT_GAP + Card.CARD_HEIGHT + 2 * DEFAULT_GAP));
					// TABLE4 draw at
					panel1 = putTableauPile(tableauPiles, 3,
					new Point(4 * DEFAULT_GAP + 3 * Card.CARD_WIDTH, DEFAULT_GAP + Card.CARD_HEIGHT + 2 * DEFAULT_GAP));
					// TABLE5 draw at
					panel1 = putTableauPile(tableauPiles, 4, 
					new Point(5 * DEFAULT_GAP + 4 * Card.CARD_WIDTH, DEFAULT_GAP + Card.CARD_HEIGHT + 2 * DEFAULT_GAP));
					// TABLE6 draw at
					panel1 = putTableauPile(tableauPiles, 5,
					new Point(6 * DEFAULT_GAP + 5 * Card.CARD_WIDTH, DEFAULT_GAP + Card.CARD_HEIGHT + 2 * DEFAULT_GAP));
					// TABLE7 draw at
					panel1 = putTableauPile(tableauPiles, 6,
					new Point(7 * DEFAULT_GAP + 6 * Card.CARD_WIDTH, DEFAULT_GAP + Card.CARD_HEIGHT + 2 * DEFAULT_GAP));

				}
			}
		});
		myFrame.add(panel, BorderLayout.SOUTH);
		panel1.setBackground(Color.ORANGE); // background color can be set outside of the loop
		panel1.setVisible(true);
		myFrame.add(panel1);
		myFrame.setVisible(true);
	}

	public JPanel putTableauPile(ArrayList<TableauPile> pile, int pileNumber, Point myPoint) {
		this.tableauPiles = pile;
		System.out.println(tableauPiles.size());
		ArrayList<Card_Graphics> myList = new ArrayList<>();
		panel1.setLayout(null);
		Card_Graphics graphics = null; // Lazy initialization
		System.out.println(myPoint.y);
		for (int card1 = 0; card1 < tableauPiles.get(pileNumber).getActualSize(); card1++, myPoint.y += 30) {
			Border b2 = new LineBorder(Color.BLACK, 1);
			graphics = new Card_Graphics(myPoint.x, myPoint.y,
					tableauPiles.get(pileNumber).getCard(card1).generateCard(tableauPiles.get(pileNumber).getCard(card1).getImagePath()));
			graphics.setBorder(b2); // sets border to each graphic
			graphics.setSize(85, 119); // set size method
			/*
			 * When clicking on the card, it will generate a card in a different location.
			 * It will remove the current uppermost card from the view.
			 */
			graphics.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
				}
			});
			myList.add(graphics); // adds each card to panel
			for (int i1 = 0; i1 < myList.size(); i1++) {
				panel1.add(myList.get(i1));
				panel1.repaint();
			}
		}

		return panel1;
	}

}
