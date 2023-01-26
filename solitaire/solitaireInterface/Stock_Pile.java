package solitaireInterface;

import java.awt.Dimension;

import javax.swing.JPanel;

public class Stock_Pile extends JPanel{
	int x;
	int y;
	int width;
	int height;
	
	Stock_Pile(int x, int y) {
		super.setLocation(x, y);
	}
	
	  public Dimension getPreferredSize() {
		  
		  width = 85;
		  height = 119;
		  return new Dimension(width, height);
	  }
}
