package solitaire;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Full_Deck {
	
	private Card[] cardArray;
	
	//constructor
	public Full_Deck() {
				
		cardArray = new Card[52];
		
		for (int i=1; i<14; i++) {
			cardArray[i-1] = new Card(i,"Clubs","src/card_graphics/Club_"+Integer.toString(i)+"_dog.png");
		}
		
		for (int i=1; i<14; i++) {
			cardArray[i+12] = new Card(i,"Diamonds","src/card_graphics/Diamond_"+Integer.toString(i)+"_dog.png");
		}
		
		for (int i=1; i<14; i++) {
			cardArray[i+25] = new Card(i,"Hearts","src/card_graphics/Heart_"+Integer.toString(i)+"_dog.png");
		}
		
		for (int i=1; i<14; i++) {
			cardArray[i+38] = new Card(i,"Spades","src/card_graphics/Spade_"+Integer.toString(i)+"_dog.png");
		}
	}
	
	public void Shuffle_Deck() {
		//Using Collections utility to shuffle 
		List<Card> toList = Arrays.asList(cardArray);
		Collections.shuffle(toList);
		toList.toArray(cardArray);
		//System.out.println(Arrays.deepToString(cardArray));
	}
	
	public Card getCard(int cardIndex) {
		return cardArray[cardIndex];
	}
}
