package solitaire;

import java.util.Arrays;

public class InitiateGame {
	
	//Create a new full deck object which will contain an array of Card objects
	private Full_Deck Deck = new Full_Deck();
	//Create the Tableau's
	private Tableau Tableau1 = new Tableau(13);
	private Tableau Tableau2 = new Tableau(13);
	private Tableau Tableau3 = new Tableau(13);
	private Tableau Tableau4 = new Tableau(13);
	private Tableau Tableau5 = new Tableau(13);
	private Tableau Tableau6 = new Tableau(13);
	private Tableau Tableau7 = new Tableau(13);
	
	//Create stock pile object
	private Stock_Pile stockPile = new Stock_Pile();
	
	
	//constructor (initiate the game)
	public InitiateGame() {
	
		//shuffle the full deck created above
		Deck.Shuffle_Deck();
					
					//following commented code was to test the Deck which was successful
					/*System.out.println(Deck.getCard(8).getCardValue());
					System.out.println(Deck.getCard(8).getCardSuit());
					System.out.println(Deck.getCard(8).getStatus());
					
					System.out.println(Deck.getCard(0).getCardValue());
					System.out.println(Deck.getCard(0).getCardSuit());
					System.out.println(Deck.getCard(0).getStatus());*/
		
		//put first 24 cards in the stock pile
		for(int i=0; i<24; i++) {
			stockPile.putInStockPile(i, Deck.getCard(i));
		}
				//following commented code to test the stock Pile	
				//System.out.println(Arrays.deepToString(stockPile.getStockPile()));
		
		//put remaining cards in 7 tableau's
		//put 1 card in Tableau 1
		for(int i=1;i<=1;i++) {
			Tableau1.putInTableauPile(0, Deck.getCard(i+23));
		}
		
		//put 2 cards in Tableau 2
		for(int i=1;i<3;i++) {
			Tableau2.putInTableauPile(i-1, Deck.getCard(i+24));
		}
		
		//put 3 cards in Tableau 3
		for(int i=1;i<4;i++) {
			Tableau3.putInTableauPile(i-1, Deck.getCard(i+26));
		}
		
		//put 4 cards in Tableau 4
		for(int i=1;i<5;i++) {
			Tableau4.putInTableauPile(i-1, Deck.getCard(i+29));
		}
		
		//put 5 cards in Tableau 5
		for(int i=1;i<6;i++) {
			Tableau5.putInTableauPile(i-1, Deck.getCard(i+33));
		}
		
		//put 6 cards in Tableau 6
		for(int i=1;i<7;i++) {
			Tableau6.putInTableauPile(i-1, Deck.getCard(i+38));
		}
		
		//put 7 cards in Tableau 7
		for(int i=1;i<8;i++) {
			Tableau7.putInTableauPile(i-1, Deck.getCard(i+44));
		}
		
		//set the last card of the tableau (last index) to a status "revealed" (boolean to true for the variable "revealed"
		//The boolean revealed set to true indicate to swing that the card should be shown
		Tableau1.getCard(0).setRevealed();
		Tableau2.getCard(1).setRevealed();
		Tableau3.getCard(2).setRevealed();
		Tableau4.getCard(3).setRevealed();
		Tableau5.getCard(4).setRevealed();
		Tableau6.getCard(5).setRevealed();
		Tableau7.getCard(6).setRevealed();
	}
	
	//get the Tableau1 object and so on
	public Tableau getTableau1() {
		return this.Tableau1;
	}
	
	public Tableau getTableau2() {
		return this.Tableau2;
	}
	
	public Tableau getTableau3() {
		return this.Tableau3;
	}
	
	public Tableau getTableau4() {
		return this.Tableau4;
	}
	
	public Tableau getTableau5() {
		return this.Tableau5;
	}
	
	public Tableau getTableau6() {
		return this.Tableau6;
	}
	
	public Tableau getTableau7() {
		return this.Tableau7;
	}
	
	//get the stock pile object
	public Stock_Pile getStockPile() {
		return this.stockPile;
	}
}
