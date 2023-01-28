package solitaire;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
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
	
	private boolean revealed;
	private int value;
	private int suit;
	private String imagePath;
	private int x=0;		//x position
	private int y=0;		//y position

	private BufferedImage cardImage;
	private Image newImage;

	//card constructor
	//We will initially make all cards face down. We will reveal once we know their position. 
	public Card(int value, int suit, String imagePath) {
		this.revealed = false;
		this.value = value;
		this.suit = suit;
		this.imagePath = imagePath;
		getCardImage();
	}
	
	//reveal the face of the card
	public void setRevealed(){
		this.revealed = true;
	}

	//for now, we will assume that once a card is revealed, it stays revealed
	public void setFaceDown() {
		this.revealed = false;
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
	public Boolean getStatus() {
		return this.revealed;
	}
	
	//get the appropriate image path wether it is revealed or not
	public String getImagePath() {
		if(revealed == true) return this.imagePath;
		return "src/images/back.png";	
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
        if (cardImage != null) {
            g.drawImage(cardImage, x, y, this);
        }
	}
	
	@Override
	public Dimension getPreferredSize() {
		if (cardImage == null || isPreferredSizeSet()) {
			return super.getPreferredSize();
		} else {
			int w = 85;
			int h = 119;
			return new Dimension(w, h);
		}
	}

	public BufferedImage getCardImage() {
		if (this.cardImage == null) {
			try {
				this.cardImage = ImageIO.read(getClass().getResource(imagePath));
				this.newImage = cardImage.getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_FAST);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return cardImage;
	}
	
	// get card color is red?
	public boolean isCardRed() {
		if (suit==0 || suit==2) return true;
		return false;
	}
}


