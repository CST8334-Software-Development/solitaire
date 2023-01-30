package solitaire;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeSupport;

public class StockPile extends CardPile {
	private PropertyChangeSupport propertyChangeSupport;

	//constructor (create an array of 24 cards)
	public StockPile() {
		super();
		propertyChangeSupport = new PropertyChangeSupport(this);
		this.addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent e) {
				firePropertyChange(Solitaire.STOCK_PILE_MOUSE_CLICK_EVENT,null,null);
			}
		});
	}
}
