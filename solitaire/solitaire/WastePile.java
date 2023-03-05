package solitaire;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeSupport;

public class WastePile extends CardPile{
	private PropertyChangeSupport propertyChangeSupport;
	public static final int CASCADE_GAP=20;

	public WastePile() {
		super();
		propertyChangeSupport = new PropertyChangeSupport(this);
		this.addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent e) {
				firePropertyChange(Solitaire.WASTE_PILE_MOUSE_CLICK_EVENT,null,null);
			}
		});
	}
	
	//***********************************Put 3 cards instead of 1 mod Feb 25********************************************
	
	public boolean canPutOnTop(Card aCard) {
		return true;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int cardPosition=0;
		if (this.getActualSize() > 0) {
			if (this.getActualSize()<4) {
				for (int i = 0;i<this.getActualSize();i++) {
					Card aCard = cards.get(i);
					aCard.setPosition(i*CASCADE_GAP, 0);
					aCard.paintComponent(g);
				}
			} else {
				for (int i = this.getActualSize()-3;i<this.getActualSize();i++) {
					Card aCard = cards.get(i);
					aCard.setPosition(cardPosition, 0);
					aCard.paintComponent(g);
					cardPosition = cardPosition + 20;
				}
			}
		}
		cardPosition = 0;	
	}
	
}
