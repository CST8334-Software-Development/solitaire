package solitaire;

public class Tableau {
	private Card[] tableauCards;
	
	//constructor (create a tableau with specified amount of cards)
	public Tableau(int amountOfCards) {
		tableauCards = new Card[amountOfCards];
	}
	
	//put cards in the tableau
	public void putInTableauPile(int index, Card card) {
		tableauCards[index] = card;
	}
	
	//get the card array of the tableau
	public Card[] getTableauPile() {
		return this.tableauCards;
	}
	
	//get a specific card in the Tableau using its index
	public Card getCard(int index) {
		return this.tableauCards[index];
	}
	
	
}
