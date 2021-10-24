package BlackJack;

public class Card {

	private int value;
	private Suit suit;
	
	private Card next;
	
	//face cards are their face-value
	//Ace is 1
	//There is no Joker
	//King, Queen, and Jack are 10-12
	public Card(Suit suit, int value) {
		this.suit = suit;
		this.value = value;
		this.next = null;
	}

	public int getValue() {
		return value;
	}

	public Suit getSuit() {
		return suit;
	}
	
	public Card getNext() {
		return this.next;
	}
	
	public void setNext(Card card) {
		this.next = card;
	}
	
	@Override
	public String toString() {
		return String.format("%d of %s%n", value, suit);
	}
}
