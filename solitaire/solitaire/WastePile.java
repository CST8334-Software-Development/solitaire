package solitaire;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeSupport;

public class WastePile extends CardPile{
	private PropertyChangeSupport propertyChangeSupport;

	public WastePile() {
		super();
		propertyChangeSupport = new PropertyChangeSupport(this);
		this.addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent e) {
				firePropertyChange(Solitaire.WASTE_PILE_MOUSE_CLICK_EVENT,null,null);
			}
		});
	}
}
