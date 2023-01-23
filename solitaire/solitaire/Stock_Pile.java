package solitaire;

import java.util.Arrays;

public class Stock_Pile {

	private Card[] stockPileCards;
	
	//constructor (create an array of 24 cards)
	public Stock_Pile() {
		stockPileCards = new Card[24];
	}
	
	//put cards in the stock pile one by one
	public void putInStockPile(int index, Card card) {
		stockPileCards[index] = card;
	}
	
	//get the stock pile array
	public Card[] getStockPile() {
		return this.stockPileCards;
	}
	
}
