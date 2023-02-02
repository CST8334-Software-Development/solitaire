package solitaire;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class OurMenu extends JMenuBar {

	private JMenuItem newGame;
	private JMenuItem exitGame;
	private JMenu menu;
	
	public OurMenu() {
		super();

		menu=new JMenu("Menu");
		newGame=new JMenuItem("New Game");
		exitGame=new JMenuItem("Exit Game");
		newGame.addActionListener(e->GamePanel.initGame());
		exitGame.addActionListener(e-> System.exit(0));
		menu.add(newGame);
		menu.add(exitGame);
		super.add(menu);
	}
	

}