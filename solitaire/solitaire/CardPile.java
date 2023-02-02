package solitaire;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class CardPile extends JComponent {

	private static final long serialVersionUID = 1L;
	protected ArrayList<Card> cards;
	private int x=0;		//x position
	private int y=0;		//y position
	private int faceUpCardCount=0;

	public int getFaceUpCardCount() {
		faceUpCardCount = 0; 
		for (Card aCard:cards) {
			if (aCard.isFaceUp()) {
				faceUpCardCount++;
			}
		}
		return faceUpCardCount;
	}
	
	public CardPile() {
		cards = new ArrayList<Card>();
	}
	//put the new card into the end of ArrayList
	public void push(Card aCard) {
		cards.add(aCard);
	}
	//get the card from the end of ArrayList and remove it
	public Card pop() {
		if (cards.isEmpty()) return null;
		return cards.remove(cards.size()-1);
	}
	//get the card at the end of the ArrayList
	public Card top() {
		if (cards.isEmpty()) return null;
		return cards.get(cards.size()-1);
	}
	//get the card at the beginning of ArrayList
	public Card tail() {
		if (cards.isEmpty()) return null;
		return cards.get(0);
	}
	//get how many cards in the pile
	public int getActualSize() {
		return cards==null?0:cards.size();
	}
	//set position of card pile
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	//get position of card pile
	public Point getPoint(int x,int y) {
		return new Point(x,y);
	}
	
	public Card getCard( int x) {
		return cards.get(x);
	}
	
	public int getXCoord() {
		return x;
	}

	public int getYCoord() {
		return x;
	}
	private String getBlankImage() {
		return "/images/blank.png";
	}
	
	//draw the card pile
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (getActualSize()>0 && top().getCardImage() != null)
			top().paintComponent(g);
		else {
			BufferedImage cardImage;
			try {
				cardImage = ImageIO.read(getClass().getResource(getBlankImage()));
				g.drawImage(cardImage, x, y,Card.CARD_WIDTH,Card.CARD_HEIGHT, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(Card.CARD_WIDTH,Card.HEIGHT);
	}

	public boolean canPutOnTop(Card aCard) {
		return false;
	}
}
