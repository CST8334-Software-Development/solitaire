package solitaire;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Toolkit;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import score.Score;
import score.ScoreBoard;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Stack;

public class Solitaire implements PropertyChangeListener {
	
	public final static String STOCK_PILE_MOUSE_CLICK_EVENT="STOCK PILE CLICK";
	public final static String TABLEAU_PILE_MOUSE_CLICK_EVENT="TABLEAU PILE CLICK";
	public final static String FOUNDATION_PILE_MOUSE_CLICK_EVENT="FOUNDATION PILE CLICK";
	public final static String WASTE_PILE_MOUSE_CLICK_EVENT="WASTE PILE CLICK";
	public final static String CARD_MOUSE_CLICK_EVENT="CARD CLICK";
	public final static String CURRENT_USER_CHANGE_EVENT="CHANGE_USER";
	public final static String SCORE_CHANGE_EVENT="CHANGE SCORE";
	
	public final static int KLONDIKE_GAME_MODE=0;
	public final static int VEGAS_GAME_MODE=1;
	public final static int KLONDIKE_GAME_INITIAL_SCORE=0;	
	public final static int VEGAS_GAME_INITIAL_SCORE=-52;

	
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
	private GameFrame mainFrame;
	
	private CardPile currentClickedPile;
	private int mouseClickCount=0;
	
	private ScoreBoard scoreBoard;
	private Score currentScore;
	private JLabel scoreLabel;
	private JLabel userNameLabel;
	private JLabel gameModeLabel;
	
	private int currentGameMode = KLONDIKE_GAME_MODE;
	
	private class GameFrame extends JFrame{
		public GameFrame(String title) {
			super(title);
		}
		public void paint(Graphics g) {
			Image offScreenImage=null;
	        if (offScreenImage == null) {
	        	offScreenImage = createImage(TABLE_WIDTH, TABLE_HEIGHT);

	        }
	        Graphics gImage = offScreenImage.getGraphics();

	        gImage.setColor(gImage.getColor());

	        gImage.fillRect(0, 0, TABLE_WIDTH, TABLE_HEIGHT);
	        super.paint(gImage);

	        g.drawImage(offScreenImage, 0, 0, null);
		}
		private class PaintThread implements Runnable {
	
		        public void run() {
		            while (true) {
		                repaint();
		                try {
		                    Thread.sleep(20);
		                } catch (InterruptedException e) {
		                    e.printStackTrace();
		                }
		            }
		        }
	
		    }
		}
	
	public void startNewGame() {
		scoreBoard = new ScoreBoard(currentGameMode);
		
		currentScore= new Score(currentGameMode);
		
		initAllCardsForGame();
		initAllPilesOnScreen();
		
		currentClickedPile = null;
		mouseClickCount=0;
		showScoreBoard();
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
	
	private void initStatusBar() {
		JPanel statusPanel = new JPanel();
		statusPanel.setLayout(new BorderLayout());
		userNameLabel = new JLabel();
		gameModeLabel = new JLabel();
		scoreLabel = new JLabel();
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusPanel.setLayout(new BorderLayout());
		
		statusPanel.add(gameModeLabel,BorderLayout.WEST);
		statusPanel.add(userNameLabel,BorderLayout.CENTER);
		statusPanel.add(scoreLabel,BorderLayout.EAST);

		mainFrame.getContentPane().add(statusPanel);
		
		//statusPanel.setBounds(0,TABLE_HEIGHT-5,TABLE_WIDTH,5);
		statusPanel.setBounds(0,520,TABLE_WIDTH-15,20);
		updateStatusBar();
	}
	private void initAllPilesOnScreen() {
		if (mainFrame!=null) mainFrame.dispose();
		mainFrame = new GameFrame("Solitaire");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(TABLE_WIDTH,TABLE_HEIGHT);
		mainFrame.setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu newGameMenu = new JMenu("Game");
		JMenuItem scoreBoardMenu = new JMenuItem("ScoreBoard");
		JMenuItem newGameItem = new JMenuItem("New Game");

		newGameItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				startNewGame();
			}
		});
		
		scoreBoardMenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				showScoreBoard();
			}
		});
		
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				scoreBoard.saveCurrentScore(currentScore);
				mainFrame.dispose();
				System.exit(0);
			}
		});
		
		newGameMenu.add(newGameItem);
		newGameMenu.add(scoreBoardMenu);
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
		
		initStatusBar();
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		mainFrame.setLocation((tk.getScreenSize().width - TABLE_WIDTH)/2,
				(tk.getScreenSize().height - TABLE_HEIGHT)/2);
		mainFrame.setVisible(true);	
	}
	
	private void showScoreBoard() {
		scoreBoard.saveCurrentScore(currentScore);
		scoreBoard.showScoreBoardDialog(mainFrame);
		String userName= scoreBoard.getCurrentUser();
		int gameMode = scoreBoard.getCurrentGameMode();
		
		if (this.currentGameMode != gameMode) {
			this.currentGameMode = gameMode;
			currentScore.setGameMode(this.currentGameMode);
			currentScore.resetScore();				
		}
		
		if (userName!=null && !userName.equals("")) {
			if (!userName.equals(currentScore.getUser())){
				//scoreBoard.setCurrentUser(userName);
				currentScore.setUser(userName);
				currentScore.resetScore();				
			}
		}
		updateStatusBar();
		//scoreBoard.closeDialog();
	}
	
	private void updateStatusBar() {
		if (userNameLabel!=null)
			userNameLabel.setText("Current User:" + currentScore.getUser());
		if (scoreLabel!=null)
			scoreLabel.setText("Current Score:" + this.currentScore.getScore());
		if (this.currentGameMode == KLONDIKE_GAME_MODE) {
			gameModeLabel.setText("Current Game Mode:KLONDIKE");
		}
		if (this.currentGameMode == VEGAS_GAME_MODE) {
			gameModeLabel.setText("Current Game Mode:VEGAS");
		}
		mainFrame.validate();
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
		EventQueue.invokeLater(() ->
		 {
			newGame = new Solitaire();
			newGame.startNewGame();
		 });
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
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
			handleWastePileMouseClickEvent(wastePile);		
		}
	}
	
	private void handleStockPileMouseClickEvent() {
		System.out.println(STOCK_PILE_MOUSE_CLICK_EVENT);
		if (this.stockPile.getActualSize()>0) {
			Card aCard = stockPile.top();
			wastePile.push(aCard);
			wastePile.top().setRevealed();
			wastePile.repaint();
			stockPile.pop();
				if (stockPile.getActualSize()==0){
					stockPile.repaint();
				}		
		} else {
			for(int i=wastePile.getActualSize()-1;i>=0;i--) {
				wastePile.getCard(i).setFaceDown();
				stockPile.push(wastePile.getCard(i));
				wastePile.pop();
			}
			stockPile.repaint();
			wastePile.repaint();
		}
	}
	
	private void handleWastePileMouseClickEvent(WastePile pile) {
		//System.out.println(WASTE_PILE_MOUSE_CLICK_EVENT);
		// To do
		mouseClickCount+=1;
		
		if (mouseClickCount==1) {
			currentClickedPile = pile;
		}
		else if (this.currentClickedPile!=pile){
			moveCard(this.currentClickedPile,pile);
			mouseClickCount = 0;
		}
		
	}
	
	private void handleTableauPileMouseClickEvent(TableauPile pile) {
		// To do
		mouseClickCount+=1;
		
		if (mouseClickCount==1) {
			currentClickedPile = pile;
		}
		else if (this.currentClickedPile!=pile){
			//moveCard(this.currentClickedPile,pile);
			if (this.currentClickedPile instanceof TableauPile)
				moveCardByInterMedia(this.currentClickedPile,pile);
			else
				moveCard(this.currentClickedPile,pile);
			Rectangle rt = pile.getBounds();
			int newHeight = Card.CARD_HEIGHT + TableauPile.CASCADE_GAP * (pile.getActualSize()-1);
			pile.setBounds(rt.x,rt.y,Card.CARD_WIDTH,newHeight);
			mouseClickCount = 0;
		}
	}
	private void handleFoundationPileMouseClickEvent(FoundationPile pile) {		
		mouseClickCount+=1;
		
		if (mouseClickCount==1) {
			currentClickedPile = pile;
		}
		else if (this.currentClickedPile!=pile){
			moveCard(this.currentClickedPile,pile);
			this.currentScore.addScore(5);
			this.scoreLabel.setText("Current Score:" + this.currentScore.getScore());
			mouseClickCount = 0;
		}
	}
	private void moveCard(CardPile fromPile,CardPile toPile) {
		if (fromPile.getActualSize()>0) {
			System.out.println("Moving Card:" + fromPile.top().toString());
			Card fromCard = fromPile.top();
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
	
	private void moveCardByInterMedia(CardPile fromPile,CardPile toPile) {
		int cardsToMoveNum = fromPile.getFaceUpCardCount();
		
		Card firstMoveCard = fromPile.getCard(fromPile.getActualSize()-cardsToMoveNum);
		if (toPile.canPutOnTop(firstMoveCard)) {
			Stack<Card> tempCards = new Stack<Card>();
			for (int i =0;i<cardsToMoveNum;i++) {
				tempCards.push(fromPile.pop());
			}
			if (fromPile.getActualSize()>0) {
				fromPile.top().setRevealed();
			}
			fromPile.repaint();
			
			for (int i =0;i<cardsToMoveNum;i++) {
//				Card fromCard = tempCards.peek();
//				if (toPile.canPutOnTop(fromCard)) {
					toPile.push(tempCards.pop());
//				}
			}
			toPile.repaint();
		}
	}

}
