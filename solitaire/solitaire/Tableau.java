package solitaire;

public class Tableau {
	private Card[] tableauCards;
	
	//constructor
	public Tableau(int amountOfCards) {
		tableauCards = new Card[amountOfCards];
		//
	}
	
	public void putInTableauPile(int index, Card card) {
		tableauCards[index] = card;
	}
	
	public Card[] getTableauPile() {
		return this.tableauCards;
	}
	
	public Card getCard(int index) {
		return this.tableauCards[index];
	}
	
	
}
