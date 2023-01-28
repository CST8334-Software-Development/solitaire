package solitaire;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import solitaireInterface.DoAction;

public class InitiateGame {
	
	final static int TABLE_HEIGHT=600;
	final static int TABLE_WIDTH=800;
	final static int DEFAULT_GAP=20;
	final static int NUM_FOUNDATION_PILE=4;
	final static int NUM_TABLEAU_PILE=7;
	final static int BIG_GAP = TABLE_HEIGHT-6*(Card.CARD_WIDTH+DEFAULT_GAP);
	
	//Create a new full deck object which will contain an array of Card objects
	private Full_Deck Deck;
	//Create the Tableau's
	private ArrayList<TableauPile> tableauPiles;
	//Create stock pile object
	private StockPile stockPile;
	//Create waste pile object
	private WastePile wastePile;
	//Create foundation pile object
	private ArrayList<FoundationPile> foundationPiles;
	
	//constructor
	public InitiateGame() {
	
	}
	private void initAllCardsForGame() {
		//shuffle the full deck created above
		Deck = new Full_Deck();
		Deck.Shuffle_Deck();
						
		//put first 24 cards in the stock pile
		stockPile = new StockPile();
		for(int i=0; i<24; i++) {
			stockPile.push(Deck.getCard(i));
		}
				//following commented code to test the stock Pile	
				//System.out.println(Arrays.deepToString(stockPile.getStockPile()));
		
		//put remaining cards in 7 tableau's
		//put 1 card in Tableau 1
		tableauPiles = new ArrayList<TableauPile>();
		
		TableauPile tableauPile1= new TableauPile();
		for(int i=1;i<=1;i++) {
			tableauPile1.push(Deck.getCard(i+23));
		}
		tableauPiles.add(tableauPile1);
		
		//put 2 cards in Tableau 2
		TableauPile tableauPile2= new TableauPile();
		for(int i=1;i<3;i++) {
			tableauPile2.push(Deck.getCard(i+24));
		}
		tableauPiles.add(tableauPile2);

		//put 3 cards in Tableau 3
		TableauPile tableauPile3= new TableauPile();
		for(int i=1;i<4;i++) {
			tableauPile3.push(Deck.getCard(i+26));
		}
		tableauPiles.add(tableauPile3);

		//put 4 cards in Tableau 4
		TableauPile tableauPile4= new TableauPile();
		for(int i=1;i<5;i++) {
			tableauPile4.push(Deck.getCard(i+29));
		}
		tableauPiles.add(tableauPile4);

		//put 5 cards in Tableau 5
		TableauPile tableauPile5= new TableauPile();
		for(int i=1;i<6;i++) {
			tableauPile5.push(Deck.getCard(i+33));
		}
		
		//put 6 cards in Tableau 6
		TableauPile tableauPile6= new TableauPile();
		for(int i=1;i<7;i++) {
			tableauPile6.push(Deck.getCard(i+38));
		}
		
		//put 7 cards in Tableau 7
		TableauPile tableauPile7= new TableauPile();
		for(int i=1;i<8;i++) {
			tableauPile7.push(Deck.getCard(i+44));
		}
		
		//set the last card of the tableau (last index) to a status "revealed" (boolean to true for the variable "revealed"
		//The boolean revealed set to true indicate to swing that the card should be shown
		for (TableauPile tableauPile:tableauPiles) {
			tableauPile.top().setRevealed();
		}
		
		wastePile = new WastePile();
		stockPile = new StockPile();
	}
	
	public void startNewGame() {
		initAllCardsForGame();
		initAllPilesOnScreen();
	}
	
	// show all the piles on screen
	private void initAllPilesOnScreen() {
		JFrame mainFrame = new JFrame("Solitaire");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainFrame.setSize(TABLE_WIDTH,TABLE_HEIGHT);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu newGameMenu = new JMenu("Game");
		JMenuItem newGameItem = new JMenuItem("New Game");
		newGameItem.addActionListener(new java.awt.event.ActionListener()
        {
			public void actionPerformed(java.awt.event.ActionEvent e)
            {
				startNewGame();
            }       
		});
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
            	mainFrame.dispose();
                System.exit(0);
            }
        });
		
		newGameMenu.add(newGameItem);
		newGameMenu.add(exitItem);
		menuBar.add(newGameMenu);
		
		mainFrame.setJMenuBar(menuBar);
		mainFrame.setVisible(true);
		
		drawStockPile();
		drawWastePile();
		drawFoundationPile();
		drawTableauPile();
	}
	
	//TO-DO:
	private void drawStockPile() {
		//draw stock pile at 
		new Point(DEFAULT_GAP,DEFAULT_GAP);
	}
	//TO-DO:
	private void drawWastePile() {
		//draw at 
		new Point(DEFAULT_GAP+Card.CARD_WIDTH,
				DEFAULT_GAP);
	}
	
	//TO-DO:
	private void drawFoundationPile(){
		//Foundation1 draw at 
		new Point(2*DEFAULT_GAP+2*Card.CARD_WIDTH+BIG_GAP,
				DEFAULT_GAP);
		//Foundation2 draw at 
		new Point(3*DEFAULT_GAP+3*Card.CARD_WIDTH+BIG_GAP,
				DEFAULT_GAP);
		//Foundation3 draw at 
		new Point(4*DEFAULT_GAP+4*Card.CARD_WIDTH+BIG_GAP,
				DEFAULT_GAP);
		//Foundation4 draw at 
		new Point(5*DEFAULT_GAP+5*Card.CARD_WIDTH+BIG_GAP,
				DEFAULT_GAP);
	}
	//TO-DO:
	private void drawTableauPile() {
		//TABLE1 draw at 
		new Point(DEFAULT_GAP,
				DEFAULT_GAP+Card.CARD_HEIGHT+2*DEFAULT_GAP);
		//TABLE2 draw at 
		new Point(2*DEFAULT_GAP+Card.CARD_WIDTH,
				DEFAULT_GAP+Card.CARD_HEIGHT+2*DEFAULT_GAP);
		//TABLE3 draw at 
		new Point(3*DEFAULT_GAP+2*Card.CARD_WIDTH,
				DEFAULT_GAP+Card.CARD_HEIGHT+2*DEFAULT_GAP);
		//TABLE4 draw at 
		new Point(4*DEFAULT_GAP+3*Card.CARD_WIDTH,
				DEFAULT_GAP+Card.CARD_HEIGHT+2*DEFAULT_GAP);
		//TABLE5 draw at 
		new Point(5*DEFAULT_GAP+4*Card.CARD_WIDTH,
				DEFAULT_GAP+Card.CARD_HEIGHT+2*DEFAULT_GAP);
		//TABLE6 draw at 
		new Point(6*DEFAULT_GAP+5*Card.CARD_WIDTH,
				DEFAULT_GAP+Card.CARD_HEIGHT+2*DEFAULT_GAP);
		//TABLE7 draw at 
		new Point(7*DEFAULT_GAP+6*Card.CARD_WIDTH,
				DEFAULT_GAP+Card.CARD_HEIGHT+2*DEFAULT_GAP);
	}
	
	//get the Tableau pile  and so on
	public TableauPile getTableauByIndex(int index) {
		if (index<NUM_TABLEAU_PILE && index>=0)  return this.tableauPiles.get(index);
		return null;
		
	}
	// get the foundation pile by index
	public FoundationPile getFoundationPileByIndex(int index) {
		if (index < NUM_FOUNDATION_PILE && index>=0) return this.foundationPiles.get(index);
		return null;
	}
	//get the stock pile object
	public StockPile getStockPile() {
		return this.stockPile;
	}
	// get the wastePile
	public WastePile getWastePile() {
		return this.wastePile;
	}
}
