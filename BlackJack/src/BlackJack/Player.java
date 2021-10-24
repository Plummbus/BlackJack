package BlackJack;

public class Player {

	private String name;
	private int money;
	private int currentBet;
	private int total;
	private boolean wonBet;
	private Card[] hand;
	private int wins;
	private int losses;
	
	public Player (String name) {
		this.name = name;
		this.money = 500;
		this.currentBet = 0;
		this.total = 0;
		this.wonBet = false;
		this.hand = new Card[2];
		this.wins = 0;
		this.losses = 0;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public int getCurrentBet() {
		return this.currentBet;
	}
	
	public int getTotal() {
		return this.total;
	}
	
	public boolean hasWonBet() {
		return this.wonBet;
	}
	
	public Card[] getHand() {
		return this.hand;
	}
	
	public int getWins() {
		return this.wins;
	}
	
	public int getLosses() {
		return this.losses;
	}
	
	public void setCurrentBet(int bet) {
		this.currentBet = bet;
	}
	
	public void setWonBet(boolean value) {
		this.wonBet = value;
	}
	
	public void win() {
		this.wins++;
	}
	
	public void lose() {
		this.losses++;
	}
	
	public void printHand() {
		for (Card card : hand) {
			System.out.println(Deck.cardInfo(card));
		}
	}
	
	//I should be error checking here, but the user doesn't handle this method so its fine
	//will need to do error checking for Pazaak tho
	public void addCard(Card card) {
		if (hand[0] == null) {
			hand[0] = card;
		} else {
			hand[1] = card;
		}
	}
	
	public void updateTotal() {
		if (hasWonBet()) {
			this.money += Math.round(getCurrentBet() * 1.5f);
		} else {
			this.money -= getCurrentBet();
		}
	}
}
