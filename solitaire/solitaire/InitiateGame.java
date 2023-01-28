package solitaire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import solitaireInterface.Card_Graphics;
import solitaireInterface.DoAction;

public class InitiateGame {

	final static int TABLE_HEIGHT = 600;
	final static int TABLE_WIDTH = 800;
	final static int DEFAULT_GAP = 20;
	final static int NUM_FOUNDATION_PILE = 4;
	final static int NUM_TABLEAU_PILE = 7;
	final static int BIG_GAP = TABLE_HEIGHT - 6 * (Card.CARD_WIDTH + DEFAULT_GAP);

	// Create a new full deck object which will contain an array of Card objects
	private Full_Deck Deck;
	// Create the Tableau's
	private ArrayList<TableauPile> tableauPiles;
	// Create stock pile object
	private StockPile stockPile;
	// Create waste pile object
	private WastePile wastePile;
	// Create foundation pile object
	private ArrayList<FoundationPile> foundationPiles;
	JPanel panel = new JPanel();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	JPanel panel5 = new JPanel();
	JPanel panel6 = new JPanel();
	JPanel panel7 = new JPanel();
	JPanel panel8 = new JPanel();

	// constructor
	public InitiateGame() {

	}

	private void initAllCardsForGame() {
		// shuffle the full deck created above
		Deck = new Full_Deck();
		Deck.Shuffle_Deck();

		// put first 24 cards in the stock pile
		stockPile = new StockPile();
		for (int i = 0; i < 24; i++) {
			stockPile.push(Deck.getCard(i));
		}
		// following commented code to test the stock Pile
		// System.out.println(Arrays.deepToString(stockPile.getStockPile()));

		// put remaining cards in 7 tableau's
		// put 1 card in Tableau 1
		tableauPiles = new ArrayList<TableauPile>();

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

		wastePile = new WastePile();
		stockPile = new StockPile();
	}
	
	public ArrayList<TableauPile> getPiles() {
		return tableauPiles;
	}
	

	public void startNewGame() {
		initAllCardsForGame();
		initAllPilesOnScreen();
	}

	// show all the piles on screen
	private void initAllPilesOnScreen() {
		JFrame mainFrame = new JFrame("Solitaire");
		Deck = new Full_Deck();
		Deck.Shuffle_Deck();
		// put 1 card in Tableau 1
		mainFrame.setResizable(false);
		// put 3 cards in Tableau 3
		TableauPile tableauPile3 = new TableauPile();
		for (int i = 1; i < 4; i++) {
			tableauPile3.push(Deck.getCard(i + 26));
		}
		tableauPiles.add(tableauPile3);

		panel1.setLayout(null);
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		JButton start = new JButton("Begin");
		panel.add(start);
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Begin")) {

					drawStockPile();
					drawWastePile();
					drawFoundationPile();
					drawTableauPile();
					}
			}
		});
		
		mainFrame.add(panel, BorderLayout.SOUTH);
		panel1.setBackground(Color.ORANGE); // background color can be set outside of the loop
		panel1.setVisible(true);
		mainFrame.add(panel1);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(TABLE_WIDTH, TABLE_HEIGHT);
		JMenuBar menuBar = new JMenuBar();
		JMenu newGameMenu = new JMenu("Game");
		JMenuItem newGameItem = new JMenuItem("New Game");

		newGameItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				startNewGame();
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
		mainFrame.setVisible(true);

	}
	
	public JPanel putTableauPile(ArrayList<TableauPile> pile, int pileNumber, Point myPoint) {
		this.tableauPiles = pile;

		System.out.println(getPiles().size());
		ArrayList<Card_Graphics> myList = new ArrayList<>();
		panel1.setLayout(null);
		Card_Graphics graphics = null; // Lazy initialization
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
	

	// TO-DO:
	private void drawStockPile() {
		// draw stock pile at
		new Point(DEFAULT_GAP, DEFAULT_GAP);
	}

	// TO-DO:
	private void drawWastePile() {
		// draw at
		new Point(DEFAULT_GAP + Card.CARD_WIDTH, DEFAULT_GAP);
	}

	// TO-DO:
	private void drawFoundationPile() {
		// Foundation1 draw at
		new Point(2 * DEFAULT_GAP + 2 * Card.CARD_WIDTH + BIG_GAP, DEFAULT_GAP);
		// Foundation2 draw at
		new Point(3 * DEFAULT_GAP + 3 * Card.CARD_WIDTH + BIG_GAP, DEFAULT_GAP);
		// Foundation3 draw at
		new Point(4 * DEFAULT_GAP + 4 * Card.CARD_WIDTH + BIG_GAP, DEFAULT_GAP);
		// Foundation4 draw at
		new Point(5 * DEFAULT_GAP + 5 * Card.CARD_WIDTH + BIG_GAP, DEFAULT_GAP);
	}

	// TO-DO:
	private void drawTableauPile() {
		// TABLE1 draw at
		panel1 = putTableauPile(getPiles(), 0,new Point(DEFAULT_GAP,DEFAULT_GAP+Card.CARD_HEIGHT+2*DEFAULT_GAP));
		// TABLE2 draw at
		panel1 = putTableauPile(getPiles(), 1,
		new Point(2 * DEFAULT_GAP + Card.CARD_WIDTH, DEFAULT_GAP + Card.CARD_HEIGHT + 2 * DEFAULT_GAP));
		// TABLE3 draw at
		panel1 = putTableauPile(getPiles(), 2,
		new Point(3 * DEFAULT_GAP + 2 * Card.CARD_WIDTH, DEFAULT_GAP + Card.CARD_HEIGHT + 2 * DEFAULT_GAP));
		// TABLE4 draw at
		panel1 = putTableauPile(getPiles(), 3,
		new Point(4 * DEFAULT_GAP + 3 * Card.CARD_WIDTH, DEFAULT_GAP + Card.CARD_HEIGHT + 2 * DEFAULT_GAP));
		// TABLE5 draw at
		panel1 = putTableauPile(getPiles(), 4, 
		new Point(5 * DEFAULT_GAP + 4 * Card.CARD_WIDTH, DEFAULT_GAP + Card.CARD_HEIGHT + 2 * DEFAULT_GAP));
		// TABLE6 draw at
		panel1 = putTableauPile(getPiles(), 5,
		new Point(6 * DEFAULT_GAP + 5 * Card.CARD_WIDTH, DEFAULT_GAP + Card.CARD_HEIGHT + 2 * DEFAULT_GAP));
		// TABLE7 draw at
		panel1 = putTableauPile(getPiles(), 6,
		new Point(7 * DEFAULT_GAP + 6 * Card.CARD_WIDTH, DEFAULT_GAP + Card.CARD_HEIGHT + 2 * DEFAULT_GAP));
	}

	// get the Tableau pile and so on
	public TableauPile getTableauByIndex(int index) {
		if (index < NUM_TABLEAU_PILE && index >= 0)
			return this.tableauPiles.get(index);
		else
			return null;

	}

	// get the foundation pile by index
	public FoundationPile getFoundationPileByIndex(int index) {
		if (index < NUM_FOUNDATION_PILE && index >= 0)
			return this.foundationPiles.get(index);
		return null;
	}

	// get the stock pile object
	public StockPile getStockPile() {
		return this.stockPile;
	}

	// get the wastePile
	public WastePile getWastePile() {
		return this.wastePile;
	}
}