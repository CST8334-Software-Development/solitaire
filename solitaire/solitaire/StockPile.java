package solitaire;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StockPile extends CardPile{
	//constructor (create an array of 24 cards)
	public StockPile() {
		super();
		this.addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent e) {
				if (getActualSize()>0) {
					Card aCard = top();
					if (!aCard.isFaceUp()) {
						aCard.setRevealed();
						//
						repaint();
					}
					else {
						// move the card to waste pile
						// show next card
					}
				}
			}
		});
	}
}
