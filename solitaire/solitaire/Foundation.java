package solitaire;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Foundation {
	
	private Card[] foundationCards;
	
	//constructor (array of 13 object of type "Card")
	public Foundation() {
		foundationCards = new Card[13];
	}
	
	//put a card in the foundation
	public void putInFoundation(int index, Card card) {
		foundationCards[index] = card;
	}
	
	//get the last index so you know where to put the next card (which index)
	public int getLastIndex() {
		return Arrays.asList(foundationCards).size();
	}
	
}
