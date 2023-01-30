
package solitaireInterface;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Card_Graphics extends JComponent implements MouseListener, MouseMotionListener {
	int x;
	int y;
	private Image cardImage;
	private Point startDrag;
	private Point startLoc;


	public Card_Graphics(int x, int y, Image image) {
		super.setLocation(x, y);
		this.cardImage = image;
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		startDrag = new Point(e.getX(), e.getY());
		startLoc = getLocation();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = startLoc.x + e.getX() - startDrag.x;
		int y = startLoc.y + e.getY() - startDrag.y;
		setLocation(x, y);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		repaint();
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
	public boolean isOptimizedDrawingEnabled() {
		return false;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
