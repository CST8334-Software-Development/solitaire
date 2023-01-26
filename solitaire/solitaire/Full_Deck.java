package solitaire;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Full_Deck {
	
	private Card[] cardArray;
	
	//constructor (creates 13 cards of each suits by setting its value, suit and image path)
	//After these cards are created, they will be used throughout the game, moving from one class array to another
	public Full_Deck() {
				
		cardArray = new Card[52];
		
		//create the Clubs suit
		for (int i=1; i<14; i++) {
			cardArray[i-1] = new Card(i,"Clubs","/images/club_"+Integer.toString(i)+"_dog.png");
		}
		
		//create the Diamonds suit
		for (int i=1; i<14; i++) {
			cardArray[i+12] = new Card(i,"Diamonds","/images/diamond_"+Integer.toString(i)+"_dog.png");
		}
		
		//create the Hearts suit
		for (int i=1; i<14; i++) {
			cardArray[i+25] = new Card(i,"Hearts","/images/heart_"+Integer.toString(i)+"_dog.png");
		}
		
		//create the Spades suit
		for (int i=1; i<14; i++) {
			cardArray[i+38] = new Card(i,"Spades","/images/spade_"+Integer.toString(i)+"_dog.png");
		}
	}
	
	//Shuffle the full deck before distributing into the Stock Pile and the Tableau using the new order after shuffled.
	public void Shuffle_Deck() {
		//Using Collections utility to shuffle 
		List<Card> toList = Arrays.asList(cardArray);
		Collections.shuffle(toList);
		toList.toArray(cardArray);
		//System.out.println(Arrays.deepToString(cardArray));
	}
	
	//get a specific card in the array using its index
	public Card getCard(int cardIndex) {
		if (cardIndex>cardArray.length-1) return null;
		return cardArray[cardIndex];
	}
}
