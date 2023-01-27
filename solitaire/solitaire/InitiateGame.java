package solitaire;

import solitaireInterface.DoAction;

public class InitiateGame {
	
	//Create a new full deck object which will contain an array of Card objects
	private Full_Deck Deck = new Full_Deck();
	//Create the Tableau's
	private TableauPile Tableau1 = new TableauPile(13);
	private TableauPile Tableau2 = new TableauPile(13);
	private TableauPile Tableau3 = new TableauPile(13);
	private TableauPile Tableau4 = new TableauPile(13);
	private TableauPile Tableau5 = new TableauPile(13);
	private TableauPile Tableau6 = new TableauPile(13);
	private TableauPile Tableau7 = new TableauPile(13);
	
	//Create stock pile object
	private StockPile stockPile = new StockPile();
	
	//Create waste pile object
	private WastePile wastePile = new WastePile();
	
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
			stockPile.push(Deck.getCard(i));
		}
				//following commented code to test the stock Pile	
				//System.out.println(Arrays.deepToString(stockPile.getStockPile()));
		
		//put remaining cards in 7 tableau's
		//put 1 card in Tableau 1
		for(int i=1;i<=1;i++) {
			Tableau1.push(Deck.getCard(i+23));
		}
		
		//put 2 cards in Tableau 2
		for(int i=1;i<3;i++) {
			Tableau2.push(Deck.getCard(i+24));
		}
		
		//put 3 cards in Tableau 3
		for(int i=1;i<4;i++) {
			Tableau3.push(Deck.getCard(i+26));
		}
		
		//put 4 cards in Tableau 4
		for(int i=1;i<5;i++) {
			Tableau4.push(Deck.getCard(i+29));
		}
		
		//put 5 cards in Tableau 5
		for(int i=1;i<6;i++) {
			Tableau5.push(Deck.getCard(i+33));
		}
		
		//put 6 cards in Tableau 6
		for(int i=1;i<7;i++) {
			Tableau6.push(Deck.getCard(i+38));
		}
		
		//put 7 cards in Tableau 7
		for(int i=1;i<8;i++) {
			Tableau7.push(Deck.getCard(i+44));
		}
		
		//set the last card of the tableau (last index) to a status "revealed" (boolean to true for the variable "revealed"
		//The boolean revealed set to true indicate to swing that the card should be shown
		Tableau1.top().setRevealed();
		Tableau2.top().setRevealed();
		Tableau3.top().setRevealed();
		Tableau4.top().setRevealed();
		Tableau5.top().setRevealed();
		Tableau6.top().setRevealed();
		Tableau7.top().setRevealed();
	}
	
	//get the Tableau1 object and so on
	public TableauPile getTableau1() {
		return this.Tableau1;
	}
	
	public TableauPile getTableau2() {
		return this.Tableau2;
	}
	
	public TableauPile getTableau3() {
		return this.Tableau3;
	}
	
	public TableauPile getTableau4() {
		return this.Tableau4;
	}
	
	public TableauPile getTableau5() {
		return this.Tableau5;
	}
	
	public TableauPile getTableau6() {
		return this.Tableau6;
	}
	
	public TableauPile getTableau7() {
		return this.Tableau7;
	}
	
	//get the stock pile object
	public StockPile getStockPile() {
		return this.stockPile;
	}
	public WastePile getWastePile() {
		return this.wastePile;
	}
	
	//******Pascal: show the game board by passing the Tableau1 as an object to be used in the DoAction class
	public void showGameBoard() {
		DoAction show = null;
		show.doEverything(Tableau1, 50, 1);
	}
}
