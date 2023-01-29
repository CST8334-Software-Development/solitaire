package solitaire;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Card extends JPanel{

	public static int CARD_HEIGHT=120;
	public static int CARD_WIDTH=85;
	

	private static final long serialVersionUID = 1L;
	final public static int SUIT_HEART = 0;
	final public static int SUIT_SPADE = 1;
	final public static int SUIT_DIAMOND = 2;
	final public static int SUIT_CLUB = 3;
	
	private boolean revealed = false;
	private int value;
	private int suit;
	private String imagePath;
	private int x=0;		//x position
	private int y=0;		//y position
	private BufferedImage cardImage;
	private Image newImage;
	
	//card constructor
	//We will initially make all cards face down. We will reveal once we know their position. 
	public Card(int value, int suitClub, String imagePath) {
		this.revealed = revealed;
		this.value = value;
		this.suit = suitClub;
		this.imagePath = imagePath;
	}
	
	public Card() {
		
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
		if(this.revealed == true) {
		return this.imagePath;
		} else {
			return getFaceDown();
		}
	}
	
	public String getFaceDown() {
		return "/images/back.png";	
	}
	
	public Image generateCard(String imagePath) {
		try {
			this.cardImage = ImageIO.read(getClass().getResource(getImagePath()));
			this.newImage = cardImage.getScaledInstance(85, 119, Image.SCALE_FAST);
			repaint();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newImage;
	}
	
	public Image returnCardImage() {
		return newImage;
	}
	

	// get card color is red?
	public boolean isCardRed() {
		if (suit==0 || suit==2) return true;
		return false;
	}

	}


