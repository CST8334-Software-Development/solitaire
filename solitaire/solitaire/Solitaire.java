package solitaire;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class Solitaire implements PropertyChangeListener {
	
	public final static String STOCK_PILE_MOUSE_CLICK_EVENT="STOCK PILE CLICK";
	public final static String TABLEAU_PILE_MOUSE_CLICK_EVENT="TABLEAU PILE CLICK";
	public final static String FOUNDATION_PILE_MOUSE_CLICK_EVENT="FOUNDATION PILE CLICK";
	public final static String WASTE_PILE_MOUSE_CLICK_EVENT="WASTE PILE CLICK";
	public final static String CARD_MOUSE_CLICK_EVENT="CARD CLICK";
	
	final static int TABLE_HEIGHT = 600;
	final static int TABLE_WIDTH = 800;
	final static int DEFAULT_GAP = 20;
	final static int NUM_FOUNDATION_PILE = 4;
	final static int NUM_TABLEAU_PILE = 7;
	final static int BIG_GAP = TABLE_WIDTH - 6 * Card.CARD_WIDTH -7* DEFAULT_GAP;	
	
	// Create a new full deck object which will contain an array of Card objects
	private Full_Deck Deck;
	private ArrayList<TableauPile> tableauPiles;
	private StockPile stockPile;
	private WastePile wastePile;
	private ArrayList<FoundationPile> foundationPiles;
	private static Solitaire newGame;
	private JFrame mainFrame;
	
	private CardPile currentClickedPile;
	private int mouseClickCount=0;
	
	public void startNewGame() {
		
		initAllCardsForGame();
		initAllPilesOnScreen();
		
		currentClickedPile = null;
		mouseClickCount=0;
	}
	
	public void initAllCardsForGame() {
		Deck = new Full_Deck();
		Deck.Shuffle_Deck();
		
		// shuffle the full deck created above
		Deck = new Full_Deck();
		Deck.Shuffle_Deck();

		// put first 24 cards in the stock pile
		stockPile = new StockPile();
		for (int i = 0; i < 24; i++) {
			stockPile.push(Deck.getCard(i));
		}
		stockPile.addPropertyChangeListener(this);
		
		wastePile = new WastePile();
		wastePile.addPropertyChangeListener(this);

		foundationPiles = new ArrayList<FoundationPile>();
		FoundationPile pile1 = new FoundationPile();
		pile1.addPropertyChangeListener(this);
		FoundationPile pile2 = new FoundationPile();
		pile2.addPropertyChangeListener(this);
		FoundationPile pile3 = new FoundationPile();
		pile3.addPropertyChangeListener(this);
		FoundationPile pile4 = new FoundationPile();
		pile4.addPropertyChangeListener(this);
		foundationPiles.add(pile1);
		foundationPiles.add(pile2);
		foundationPiles.add(pile3);
		foundationPiles.add(pile4);
		
		// put remaining cards in 7 tableau's
		// put 1 card in Tableau 1
		tableauPiles = new ArrayList<TableauPile>();

		TableauPile tableauPile1 = new TableauPile();
		for (int i = 1; i <= 1; i++) {
			tableauPile1.push(Deck.getCard(i + 23));
		}
		tableauPile1.addPropertyChangeListener(this);
		tableauPiles.add(tableauPile1);

		// put 2 cards in Tableau 2
		TableauPile tableauPile2 = new TableauPile();
		for (int i = 1; i < 3; i++) {
			tableauPile2.push(Deck.getCard(i + 24));
		}
		tableauPile2.addPropertyChangeListener(this);
		tableauPiles.add(tableauPile2);

		// put 3 cards in Tableau 3
		TableauPile tableauPile3 = new TableauPile();
		for (int i = 1; i < 4; i++) {
			tableauPile3.push(Deck.getCard(i + 26));
		}
		tableauPile3.addPropertyChangeListener(this);
		tableauPiles.add(tableauPile3);

		// put 4 cards in Tableau 4
		TableauPile tableauPile4 = new TableauPile();
		for (int i = 1; i < 5; i++) {
			tableauPile4.push(Deck.getCard(i + 29));
		}
		tableauPile4.addPropertyChangeListener(this);
		tableauPiles.add(tableauPile4);

		// put 5 cards in Tableau 5
		TableauPile tableauPile5 = new TableauPile();
		for (int i = 1; i < 6; i++) {
			tableauPile5.push(Deck.getCard(i + 33));
		}
		tableauPile5.addPropertyChangeListener(this);
		tableauPiles.add(tableauPile5);

		// put 6 cards in Tableau 6
		TableauPile tableauPile6 = new TableauPile();
		for (int i = 1; i < 7; i++) {
			tableauPile6.push(Deck.getCard(i + 38));
		}
		tableauPile6.addPropertyChangeListener(this);
		tableauPiles.add(tableauPile6);

		// put 7 cards in Tableau 7
		TableauPile tableauPile7 = new TableauPile();
		for (int i = 1; i < 8; i++) {
			tableauPile7.push(Deck.getCard(i + 44));
		}
		tableauPile7.addPropertyChangeListener(this);
		tableauPiles.add(tableauPile7);

		// set the last card of the tableau (last index) to a status "revealed" (boolean
		// to true for the variable "revealed"
		// The boolean revealed set to true indicate to swing that the card should be
		// shown
		for (TableauPile tableauPile : tableauPiles) {
			tableauPile.top().setRevealed();
		}
	}
	
	private void initAllPilesOnScreen() {
		if (mainFrame!=null) mainFrame.dispose();
		mainFrame = new JFrame("Solitaire");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(TABLE_WIDTH,TABLE_HEIGHT);
		mainFrame.setResizable(false);
		
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
		
		mainFrame.getContentPane().setLayout(null);
		mainFrame.getContentPane().setBackground(Color.ORANGE);

		//1 draw stock pile 
		//2 draw waste pile
		//3 draw foundation pile
		//4 draw tableau pile
		drawStockPile(DEFAULT_GAP,DEFAULT_GAP);
		drawWastePile(2*DEFAULT_GAP + Card.CARD_WIDTH, DEFAULT_GAP);
		drawFoundationPiles(2 * DEFAULT_GAP + 2 * Card.CARD_WIDTH + BIG_GAP,DEFAULT_GAP);
		drawTableauPiles(DEFAULT_GAP,DEFAULT_GAP + Card.CARD_HEIGHT + 2 * DEFAULT_GAP);
			
		mainFrame.setVisible(true);	
	}
	private void drawStockPile(int x,int y) {
		mainFrame.getContentPane().add(newGame.stockPile);
		newGame.stockPile.setBounds(x,y,Card.CARD_WIDTH,Card.CARD_HEIGHT);
	}
	private void drawWastePile(int x,int y) {
		mainFrame.getContentPane().add(newGame.wastePile);
		newGame.wastePile.setBounds(x,y,Card.CARD_WIDTH,Card.CARD_HEIGHT);
	}
	
	private void drawFoundationPiles(int x,int y) {
		for (int i=0;i<newGame.foundationPiles.size();i++) {
			int newX =x +i*(DEFAULT_GAP + Card.CARD_WIDTH);
			mainFrame.getContentPane().add(newGame.foundationPiles.get(i));
			newGame.foundationPiles.get(i).setBounds(newX,y,Card.CARD_WIDTH,Card.CARD_HEIGHT);
		}
	}
	
	private void drawTableauPiles(int x,int y) {
		for (int i=0;i<newGame.tableauPiles.size();i++) {
			int newX =x +i*(DEFAULT_GAP + Card.CARD_WIDTH);
			mainFrame.getContentPane().add(newGame.tableauPiles.get(i));
			int newHeight = Card.CARD_HEIGHT + TableauPile.CASCADE_GAP * (newGame.tableauPiles.get(i).getActualSize()-1);
			newGame.tableauPiles.get(i).setBounds(newX,y,Card.CARD_WIDTH,newHeight);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(() ->
		 {
			newGame = new Solitaire();
			newGame.startNewGame();
		 });
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		String propertyName = evt.getPropertyName();
		System.out.println(propertyName);
		
		if (propertyName.equals(STOCK_PILE_MOUSE_CLICK_EVENT)){
			handleStockPileMouseClickEvent();
		}
		
		if (propertyName.equals(TABLEAU_PILE_MOUSE_CLICK_EVENT)){
			TableauPile pile = (TableauPile)evt.getNewValue();
			handleTableauPileMouseClickEvent(pile);
		};
		
		if (propertyName.equals(FOUNDATION_PILE_MOUSE_CLICK_EVENT)){
			FoundationPile pile = (FoundationPile)evt.getNewValue();
			handleFoundationPileMouseClickEvent(pile);
		}
		
		if (propertyName.equals(CARD_MOUSE_CLICK_EVENT)){
			
		}
		if (propertyName.equals(WASTE_PILE_MOUSE_CLICK_EVENT)){
					
		}
	}
	
	private void handleStockPileMouseClickEvent() {
		System.out.println(STOCK_PILE_MOUSE_CLICK_EVENT);
		if (this.stockPile.getActualSize()>0) {
			Card aCard = stockPile.top();
			/*if (!aCard.isFaceUp()) {
				aCard.setRevealed();
				stockPile.repaint();
			}
			else {*/
				// To do
				// move the top card to waste pile
				if (stockPile.getActualSize()>0) {
					wastePile.push(stockPile.pop());				
					stockPile.top().setRevealed();
				}				
				wastePile.repaint();
				stockPile.repaint();
				// show the next card
				wastePile.push(aCard);
				wastePile.top().setRevealed();
				wastePile.repaint();
				stockPile.pop();
				if (stockPile.getActualSize()==0){
					stockPile.repaint();
				}
				//stockPile.top().setRevealed();
				//stockPile.repaint();
				
				//System.out.println(Integer.toString(stockPile.getActualSize()));
				
			//}
		}
		else {
			// To do
			// if empty, 
			//	if game is not over 
			//		get all cards from waste pile
			//	else
			//		game over
			for(int i=wastePile.getActualSize()-1;i>=0;i--) {
				wastePile.getCard(i).setFaceDown();
				stockPile.push(wastePile.getCard(i));
				wastePile.pop();
			}
			stockPile.repaint();
			wastePile.repaint();
		}

	}
	private void handleWastePileMouseClickEvent() {
		System.out.println(WASTE_PILE_MOUSE_CLICK_EVENT);
		// To do
	}
	private void handleTableauPileMouseClickEvent(TableauPile pile) {
		// To do
		mouseClickCount+=1;
		
		if (mouseClickCount==1) {
			currentClickedPile = pile;
		}
		else if (this.currentClickedPile!=pile){
			moveCard(this.currentClickedPile,pile);
			
			Rectangle rt = pile.getBounds();
			int newHeight = Card.CARD_HEIGHT + TableauPile.CASCADE_GAP * (pile.getActualSize()-1);
			pile.setBounds(rt.x,rt.y,Card.CARD_WIDTH,newHeight);
			mouseClickCount = 0;
		}
	}
	private void handleFoundationPileMouseClickEvent(FoundationPile pile) {		
		// To do
			mouseClickCount+=1;
			
			if (mouseClickCount==1) {
				currentClickedPile = pile;
			}
			else if (this.currentClickedPile!=pile){
				// 
				moveCard(this.currentClickedPile,pile);
				mouseClickCount = 0;
			}
		
	}
	private void moveCard(CardPile fromPile,CardPile toPile) {
		if (fromPile.getActualSize()>0) {
			System.out.println("Moving Card:" + fromPile.top().toString());
			Card fromCard = this.currentClickedPile.top();
			if (fromCard.isFaceUp()) {
				if (toPile.canPutOnTop(fromCard)) {
					toPile.push(fromCard);
					fromPile.pop();
					if (fromPile.getActualSize()>0) {
						fromPile.top().setRevealed();
					}
				}
				fromPile.repaint();
				toPile.repaint();
			}
		}
	} 
}
