package BlackJack;



public class Deck {
	
	private Card head;
	
	public Deck() {
		head = null;
	}
	
	public Card getHead() {
		return this.head;
	}
	
	public void push(Suit suit, int value) {
		Card oldHead = head;
		head = new Card(suit, value);
		head.setNext(oldHead);
	}
	
	public void push(Card card) {
		Card oldHead = head;
		head = card;
		head.setNext(oldHead);
	}
	
	public Card pop() {
		if (head == null) {
			return null;
		} else {
			Card result = head;
			head = head.getNext();
			return result;
		}
	}
	
	public int peek() {
		if (isEmpty()) {
			System.out.println("Deck is empty! No more cards to be drawn.");
			return 1;
		} else {
			return -1;
		}
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public int size() {
		Card current = head;
		int count = 0;
		if (head == null) {
			return count;
		} else {
			count++;
			current = current.getNext();
		}
		while (current != null) {
			count++;
			current = current.getNext();
		}
		return count;
	}
	
	public void printDeck() {
		Card current = head;
		while (current != null) {
			String message = cardInfo(current);
			System.out.println(message);
			current = current.getNext();		}
	}
	
	public static String cardInfo(Card card) {
		String message = "";
		if (card.getValue() == 1) {
			message = String.format("Ace of %s%n", card.getSuit());
		}
		if (card.getValue() > 1 && card.getValue() < 10) {
			message = String.format("%d of %s%n", card.getValue(), card.getSuit());
		} else {
			switch (card.getValue()) {
			case 10:
				message = String.format("King of %s%n", card.getSuit());
				break;
			case 11:
				message = String.format("Queen of %s%n", card.getSuit());
				break;
			case 12:
				message = String.format("Jack of %s%n", card.getSuit());
				break;
			default:
				break;
			}
		}
		return message;
	}
}
