
package solitaireInterface;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Card_Graphics extends JComponent {
	int x;
	int y;
	private Image cardImage;
	
	public Card_Graphics(int x, int y, Image image) {
		super.setLocation(x,y);
		this.cardImage = image;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (cardImage != null) {
			g.drawImage(cardImage, x, y, this);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		if (cardImage == null || isPreferredSizeSet()) {
			return super.getPreferredSize();
		} else {
			int w = 85;
			int h = 119;
			return new Dimension(w, h);
		}
	}
	
	public Point getPoint() {
		return new Point(x, y);
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	 @Override
	    public boolean isOptimizedDrawingEnabled()
	    {
	        return false;
	    }


}
