package solitaire;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeSupport;

public class FoundationPile extends CardPile{
	
	private int suit;// only the same suit card can be put on the foundation
	private PropertyChangeSupport propertyChangeSupport;

	public FoundationPile() {
		super();
		propertyChangeSupport = new PropertyChangeSupport(this);
		this.addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent e) {
				firePropertyChange(Solitaire.FOUNDATION_PILE_MOUSE_CLICK_EVENT,null,this);
			}
		});
		suit = -1;
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
			this.suit = aCard.getCardSuit();
			return true;
		}
		if (getActualSize()>0 && aCard.getCardSuit()==suit) {
				if (top().getCardValue()== aCard.getCardValue()-1) return true;
		}
		return false;
	}
}
