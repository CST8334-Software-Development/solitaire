package solitaire;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Card extends JPanel{

	private static final long serialVersionUID = 1L;
	final public static int SUIT_HEART = 0;
	final public static int SUIT_SPADE = 1;
	final public static int SUIT_DIAMOND = 2;
	final public static int SUIT_CLUB = 3;
	
	final public static int CARD_HEIGHT=120;
	final public static int CARD_WIDTH=85;
	
	private boolean isFaceUp;
	private int value;
	private int suit;
	private int x=0;		//x position
	private int y=0;		//y position

	private BufferedImage cardImage;

	//card constructor
	//We will initially make all cards face down. We will reveal once we know their position. 
	public Card(int value, int suit) {
		this.isFaceUp = false;
		this.value = value;
		this.suit = suit;
	}
	public String getImagePath() {
		if (this.isFaceUp) return getFaceImagePath();
		return getBackImagePath();
	}
	// get face image path
	private String getFaceImagePath() {
		switch(this.suit) {
			case Card.SUIT_CLUB:
				return "/images/club_"+Integer.toString(this.value)+"_dog.png";
			case Card.SUIT_DIAMOND:
				return "/images/DIAMOND_"+Integer.toString(this.value)+"_dog.png";
			case Card.SUIT_HEART:
				return  "/images/HEART_"+Integer.toString(this.value)+"_dog.png";
			case Card.SUIT_SPADE:
				return "/images/SPADE_"+Integer.toString(this.value)+"_dog.png";
		}	
		return "/images/blank.png";
	}
	
	// get back image path
	public String getBackImagePath() {
		return "/images/back.png";
	}
	
	//reveal the face of the card
	public void setRevealed(){
		this.isFaceUp = true;
	}

	//for now, we will assume that once a card is revealed, it stays revealed
	public void setFaceDown() {
		this.isFaceUp = false;
	}
	//get the card value
	public int getCardValue() {
		return this.value;
	}
	//get the card suit
	public int getCardSuit() {
		return this.suit;
	}

	//get the statut (revealed or not)
	public Boolean isFaceUp() {
		return this.isFaceUp;
	}
		
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point getPosition() {
		return new Point(x,y);
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    if (cardImage == null) cardImage = getCardImage();
        Dimension dim = getPreferredSize();
        g.drawImage(cardImage, x, y, dim.width, dim.height, this);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(CARD_WIDTH, CARD_HEIGHT);
	}

	public BufferedImage getCardImage() {
		try {
			this.cardImage = ImageIO.read(getClass().getResource(getImagePath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.cardImage;
	}
	
	// get card color is red?
	public boolean isCardRed() {
		if (suit==0 || suit==2) return true;
		return false;
	}
}


