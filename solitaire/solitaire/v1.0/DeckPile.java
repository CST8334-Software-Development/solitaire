package solitaire;

import java.util.Random;

class DeckPile extends CardPile {

	DeckPile (int x, int y) {
		// first initialize parent
		super(x,y);		
						//provide code here
		
		// then create the new deck
		// first put them into a local pile
		for (int i = 0; i < 4; i++){
			for (int j = 0; j <= 12; j++){ 
				thePile.push(new Card(i,j));
			}
		}

		// then shuffle the cards
		Random generator = new Random();
		for (int i = 0; i < 52; i++) {
			int j = Math.abs(generator.nextInt() % 52);
			
			// swap the two card values
			Card temp = thePile.elementAt(i);
			thePile.setElementAt(thePile.elementAt(j), i);
			thePile.setElementAt(temp, j);
		}
	}

	public void select(int tx, int ty) {
		if (!isEmpty()) {
			Card aCard = thePile.pop();
			Solitaire.discardPile.addCard(aCard);
		}
	}
}
