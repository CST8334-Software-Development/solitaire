package solitaire;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Foundation {
	
	private Card[] foundationCards;
	
	//constructor
	public Foundation() {
		foundationCards = new Card[13];
	}
	
	public void putInFoundation(int index, Card card) {
		foundationCards[index] = card;
	}
	
	public int getLastIndex() {
		return Arrays.asList(foundationCards).size();
	}
	
}
