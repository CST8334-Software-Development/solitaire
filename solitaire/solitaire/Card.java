package solitaire;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Card extends JPanel{

	public static int CARD_HIGHT=120;
	public static int CARD_WIDTH=85;
	
	private boolean revealed;
	private int value;
	private String suit;
	private String imagePath;
	private int x=0;		//x position
	private int y=0;		//y position
	private BufferedImage cardImage;
	
	//card constructor
	//We will initially make all cards face down. We will reveal once we know their position. 
	public Card(int value, String suit, String imagePath) {
		this.revealed = false;
		this.value = value;
		this.suit = suit;
		this.imagePath = imagePath;
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
	public String getCardSuit() {
		return this.suit;
	}

	//get the statut (revealed or not)
	public Boolean getStatus() {
		return this.revealed;
	}
	
	//get the appropriate image path wether it is revealed or not
	public String getImagePath() {
		if(revealed = true) {
		return this.imagePath;
		} else {
		return "src/card_graphics/Face_dog.png";	
		}
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point getPoint() {
		return new Point(x,y);
	}
	
	public void draw(Graphics g) {
	     super.paintComponent(g);
	        if (cardImage != null) {
	            g.drawImage(cardImage, x, y, this);
	        }
	}
	public void generateCard(String imagePath) {
		try {
			this.cardImage = ImageIO.read(getClass().getResource(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage returnCardImage() {
		return cardImage;
	}
}
