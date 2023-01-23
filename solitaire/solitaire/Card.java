package solitaire;

public class Card {

	private boolean revealed;
	private int value;
	private String suit;
	private String imagePath;
	
	
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
}
