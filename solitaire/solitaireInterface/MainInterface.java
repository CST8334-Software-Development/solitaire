
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
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import solitaire.Card;

public class MainInterface {

	public static void main(String[] args) {

		int x = 0;
		
		JFrame myFrame = new JFrame("Solitaire");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setResizable(false);
		myFrame.setSize(new Dimension(800, 600));
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		JButton start = new JButton("Begin");
		panel.add(start);
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getActionCommand().equals("Begin")) {
					
					int y = 0;
					Card card = new Card(1, "Clubs", "/images/club_" + 1 + "_dog.png");
					card.generateCard(card.getImagePath()); //Generates the card graphic
					Card_Graphics graphics = null; //Lazy initialization
					Border b2 = new LineBorder(Color.BLACK, 1); //Border default value
					for(int i = 30; i < 62; i+=2) { //So far only generates a few cards default y position is 30
						y = i; //Set the y value to the loop as it iterates
						graphics = new Card_Graphics(30, y, card.returnCardImage()); //After the lazy initialization, it will generate graphics within the loop
						graphics.setBorder(b2); //sets border to each graphic 
						graphics.setSize(85, 119); // set size method 
						panel1.add(graphics); //adds each card to panel
						panel1.repaint(); //Withiout repainting the screen, nothing will show.
					}
				

			}
			}
		});
		
		myFrame.add(panel, BorderLayout.SOUTH);
		panel1.setBackground(Color.ORANGE); //background color can be set outside of the loop
		panel1.setVisible(true);
		myFrame.add(panel1);
		myFrame.setVisible(true);
	}
}