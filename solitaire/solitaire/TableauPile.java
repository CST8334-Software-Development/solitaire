package solitaire;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public class TableauPile extends CardPile{
	
	//constructor (create a tableau with specified amount of cards)
	public static final int CASCADE_GAP=15;
	public TableauPile() {
		super();
	}
	
	public boolean canPutOnTop(Card aCard) {
		if (getActualSize()==0 && aCard.getCardValue()==13) return true;	//only K can be put on a empty tableau pile
		Card topCard = top();
		if (topCard.isCardRed()==!aCard.isCardRed())//different color card
			if (aCard.getCardValue() == topCard.getCardValue()-1) return true;// the value of new card should be 1 less than the top card
		return false;
	}
	
	@Override
	public Dimension getPreferredSize() {
		int height;
		height = Card.CARD_HEIGHT + this.getActualSize() * CASCADE_GAP;
		return new Dimension(Card.CARD_WIDTH,height);
	}
	@Override
	public void paintComponent(Graphics g) {
		//super.paintComponent(g);
		for (int i = 0;i<this.getActualSize();i++) {
			Card aCard = cards.get(i);
//			Point cardPoint = aCard.getPosition();
			aCard.setPosition(0, i*CASCADE_GAP);
			aCard.paintComponent(g);
		}
	}
}
