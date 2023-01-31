package solitaire;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeSupport;

public class FoundationPile extends CardPile{
	
	private int suit;// only the same suit card can be put on the foundation
	private PropertyChangeSupport propertyChangeSupport;

	public FoundationPile() {
		super();
		propertyChangeSupport = new PropertyChangeSupport(this);
		this.addMouseListener(new FoundationPileMouseApdater(this));
		
		suit = -1;
	}
	private class FoundationPileMouseApdater extends MouseAdapter{
		private FoundationPile pile;
		public FoundationPileMouseApdater(FoundationPile pile) {
			this.pile = pile;
		}
		public void mousePressed(MouseEvent e) {
			firePropertyChange(Solitaire.FOUNDATION_PILE_MOUSE_CLICK_EVENT,null,pile);
		}
	}
	public void pop(Card aCard) {
		super.pop();
		this.suit = aCard.getCardSuit();
	}
	
	public void setSuit(int suit) {
		this.suit = suit;
	}
	public int getSuit() {
		return this.suit;
	}
	
	// if Foundation pile is empty , can put an ACE on top what ever the suit
	// if the coming card value is bigger than the top card by 1, and have the same suit,it can be put on top
	public boolean canPutOnTop(Card aCard) {
		if (getActualSize()==0 && aCard.getCardValue()==1) {
			return true;
		}
		if (getActualSize()>0 && aCard.getCardSuit()==suit) {
				if (top().getCardValue()== aCard.getCardValue()-1) return true;
		}
		return false;
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		
		for (int i = 0;i<this.getActualSize();i++) {
			Card aCard = cards.get(i);
			aCard.setPosition(0, 0);
			aCard.paintComponent(g);
		}
	}
}
