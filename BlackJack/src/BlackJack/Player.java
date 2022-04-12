package BlackJack;

import java.util.ArrayList;

public class Player {

	private String name;
	private int money;
	private int currentBet;
	private int total;
	private ArrayList<Card> hand;
	private int wins;
	private int losses;
	private boolean standing;
	
	public Player (String name) {
		this.name = name;
		this.money = 500;
		this.currentBet = 0;
		this.total = 0;
		this.hand = new ArrayList<Card>();
		this.wins = 0;
		this.losses = 0;
		this.standing = false;
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
	
	public ArrayList<Card> getHand() {
		return this.hand;
	}
	
	public int getWins() {
		return this.wins;
	}
	
	public int getLosses() {
		return this.losses;
	}
	
	public boolean getStanding() {
		return this.standing;
	}
	
	public void setCurrentBet(int bet) {
		this.currentBet = bet;
	}
	
	public void stand() {
		this.standing = true;
		System.out.println(this.getName() + " chooses to STAND!");
	}
	
	public void win() {
		this.money += Math.round(getCurrentBet() * 1.5f);
		this.wins++;
	}
	
	public void lose() {
		this.money -= this.getCurrentBet();
		this.losses++;
	}
	
	public void reset() {
		this.total = 0;
		this.hand = new ArrayList<Card>();
		this.currentBet = 0;
		this.standing = false;
	}
	
	public void printHand() {
		for (Card card : hand) {
			System.out.println(Deck.cardInfo(card));
		}
	}
	
	//I should be error checking here, but the user doesn't handle this method so its fine
	//will need to do error checking for Pazaak tho
	public void addCard(Card card) {
		hand.add(card);
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
}
