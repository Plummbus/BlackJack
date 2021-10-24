package BlackJack;

public class TestDemo {

	
	
	public static void main(String[] args) {
		Card h2 = new Card(Suit.HEARTS, 2);
		Card sA = new Card(Suit.SPADES, 1);
		Card c8 = new Card(Suit.CLUBS, 8);
		Card d11 = new Card(Suit.DIAMONDS, 11);
		
		Deck deck = new Deck();
		System.out.println("deck size: " + deck.size());
		deck.push(d11);
		System.out.println("deck size: " + deck.size());
		deck.push(c8);
		deck.push(sA);
		deck.push(h2);
		deck.printDeck();
		System.out.println("deck size: " + deck.size());
		System.out.println(deck.pop());
		
	}
}
