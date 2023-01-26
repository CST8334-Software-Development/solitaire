
package solitaireInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import solitaire.Card;

public class MainInterface {

	public static void main(String[] args) {

		JFrame myFrame = new JFrame("Solitaire");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setResizable(false);
		myFrame.setSize(new Dimension(800, 600));
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		panel.setBackground(Color.ORANGE);
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		JButton start = new JButton("Begin Game");
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Begin Game")) {
					
				}
			}
		});
		Card card = new Card(1, "Clubs", "/images/club_" + 1 + "_dog.png");
		Card_Graphics graphics = new Card_Graphics(400, 100, card.generateCard(card.getImagePath()));
		graphics.setSize(85, 119);
		panel1.add(graphics);
		panel.add(start);
		panel1.setVisible(true);
		myFrame.add(panel1);
		myFrame.add(panel, BorderLayout.SOUTH);
		myFrame.setVisible(true);
	}

}