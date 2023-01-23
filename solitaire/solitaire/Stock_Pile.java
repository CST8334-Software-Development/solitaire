package solitaire;

import java.util.Arrays;

public class Stock_Pile {

	private Card[] stockPileCards;
	
	//constructor
	public Stock_Pile() {
		stockPileCards = new Card[24];
	}
	
	public void putInStockPile(int index, Card card) {
		stockPileCards[index] = card;
	}
	
	public Card[] getStockPile() {
		return this.stockPileCards;
	}
	
}
