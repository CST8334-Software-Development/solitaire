package solitaire;

public class TableauPile extends CardPile{
	
	//constructor (create a tableau with specified amount of cards)
	public TableauPile() {
		super();
	}
	
	public boolean canPutOnTop(Card aCard) {
		if (getActualSize()==0 && aCard.getCardValue()==13) return true;	//only K can be put on a empty tableau pile
		Card topCard = top();
		if (topCard.isCardRed()==!aCard.isCardRed())//different color card
			if (aCard.getCardValue() == topCard.getCardValue()-1) return true;// the value of new card should be 1 less than the top card
		return false;
	}
}