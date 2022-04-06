package BlackJack;

import java.util.ArrayList;

public class Dealer {

	private int total;
	private ArrayList<Card> hand;
	private boolean standing;
	
	public Dealer() {
		this.total = 0;
		this.hand = new ArrayList<Card>();
		this.standing = false;
	}
	
	public int getTotal() {
		return this.total;
	}
	
	public boolean getStanding() {
		return this.standing;
	}
	
	public void standing() {
		this.standing = true;
	}
	
	public void addCard(Card card) {
		this.hand.add(card);
	}
	
	public void updateTotal() {
		this.total = 0; 	//resetting total's value to 0 to make updating easy
		for (Card card : this.hand) {
			int actualCardValue;
			if (card.getValue() >= 10) {
				actualCardValue = 10;	//K/Q/J represented as 10, 11, 12. need to update here to properly value them at 10
			} else {
				actualCardValue = card.getValue();
			}
			this.total += actualCardValue;
		}
	}
	
	public void reset() {
		this.total = 0;
		this.hand = new ArrayList<Card>();
		this.standing = false;
	}
}
