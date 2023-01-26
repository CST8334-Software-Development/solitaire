

class SuitPile extends CardPile {

	SuitPile (int x, int y) {
		super(x,y);
	}

	public boolean canTake (Card aCard) {
		if (thePile.isEmpty()) {
			if (aCard.rank()==1) return true;
		}
		else {
			Card bCard = top();
			if (aCard.suit()==bCard.suit() && aCard.rank()==bCard.rank()+1) return true;
		}
		return false;
	}
}
