package BlackJack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GameLoop {
	
	private static Random rand = new Random();
	
	private static Suit[] suits = {Suit.HEARTS, Suit.CLUBS, Suit.SPADES, Suit.DIAMONDS};
	
	private static Player player = GameController.player;
	private static Dealer dealer = new Dealer();
	private static Scanner sc = GameController.sc;
	private static Deck deck = GameController.deck;

	public static void playGame() throws InterruptedException {
		startOfGame();
	}

	public static void placeBet() {
		String message = String.format("\n%-10s: %-10d", "Money", player.getMoney());
		System.out.println("\n-----------------------------------\n"
			+ message);
		boolean hasBet = false;
		while(!hasBet) {
			System.out.println("How much would you like to bet this round?");
			try {
				int bet = sc.nextInt();
				if (bet > player.getMoney()) {
					System.out.println(String.format("How're you gonna try and bet $%d when you only have $%d", bet, player.getMoney()));
				} else if (bet <= 0) {
					System.out.println(String.format("Now a bet of %d sure is a fake bet if I've ever seen one! Try again, %s.", bet, player.getName()));
				} else {
					GameController.player.setCurrentBet(bet);
					String[] phrases = {
							"Let's hope you win!\n",
							"Good luck to you and have fun!\n",
							"Wow, we've got a high-roller here!\n",
							"Now that's a lot of money!\n",
							"I'm rooting for you!\n",
							"Never before have I seen so much cash!\n",
							"Whoa, I wouldn't be so cavalier with all that money!\n",
							"I'm sure we're looking at a winner here!\n",
							"Make sure to cash out before you spend it all!\n"
					};
					System.out.println(String.format("%s has bet %d! %s", GameController.player.getName(), player.getCurrentBet(), phrases[rand.nextInt(phrases.length)]));
					hasBet = true;
				}
			} catch (InputMismatchException e) {
				System.out.println("We're betting money here! Not letters! Try again, and give us a real betting value this time.");
				sc.next();
			}
		}	
	}
	
	public static void createDeck(Deck deck) {
		
		for (int i = 0; i < 4; i++) {	//suits
			for (int j = 1; j < 13; j++) { //card values
				deck.push(new Card(suits[i], j));
			}
		}
	}
	
	public static Deck shuffleDeck(Deck deck) {
		Deck newDeck = new Deck();
		ArrayList<Card> shuffleList = new ArrayList<Card>();
		while (!deck.isEmpty()) {
			shuffleList.add(deck.pop());
		}
		Collections.shuffle(shuffleList);
		for (Card card : shuffleList) {
			newDeck.push(card);
		}
		return newDeck;
	}
	
	//Remove duplicate code in GameController once that test is no longer needed
	private static void displayDeck() {
		deck = new Deck();
		createDeck(deck);
		
		System.out.println("\n*********UNSHUFFLED DECK*********\n");
		deck.printDeck();
		
		System.out.println("\n*********SHUFFLED DECK*********\n");
		deck = shuffleDeck(deck);
		deck.printDeck();
	}
	
	private static void displayTotals() {
		String message = String.format("\nYour total: %d"
				+ "\nDealer total: %d", player.getTotal(), dealer.getTotal());
		System.out.println(message);
	}
	
	private static void startOfGame() throws InterruptedException {
		deck = new Deck();
		createDeck(deck);
		deck = shuffleDeck(deck);
		
		for (int i = 0; i < 2; i++) {
			draw(true);
			draw(false);
		}
		
		displayTotals();
		
		restOfGame();
	}
	
	private static void restOfGame() throws InterruptedException {
		boolean endOfGame = false;
		boolean winnerFound = false;
		
		while (!endOfGame) {
			if (player.getTotal() < 21 && dealer.getTotal() < 21) {
				if (player.getStanding() != true) {
					playerTurn();
				}
				if (dealer.getStanding() != true) {
					dealerTurn();
				}
			}
			
			if (player.getTotal() >= 21 || dealer.getTotal() >= 21 || (player.getStanding() == true && dealer.getStanding() == true)) {
				endOfGame = true;
			}
		}
		
		if (player.getTotal() == 21 || (player.getTotal() < 21 && dealer.getTotal() > 21)) {	//don't have to make cause for tie since we're making player win ties and if statements short-circuit 
			endGameMessage(true);
		} else {
			endGameMessage(false);
		}
		playAgainPrompt();
	}
	
	private static void resetEverything() {
		player.reset();
		dealer.reset();
		//don't have to reset the deck because that already happens @ startOfGame()
	}
	
	private static void playAgainPrompt() throws InterruptedException {
		if (player.getMoney() <= 0) {
			printEllipses(850);
			System.out.println("\nSo sorry to say this, but you must have a non-zero amount of money to play at this table."
					+ "\nMy guards will escort you out. Thank you, though,"
					+ "\nand do come 'round once you've refilled your pockets."
					+ "\n\nEND OF GAME");
			GameController.endGame = true;
		} else {
			System.out.println("Please select an option:"
					+ "\n1. Play Again"
					+ "\n2. Return to Main Menu");
			boolean validInput = false;
			while (!validInput) {
				try {
					int input = sc.nextInt();
					switch (input) {
					case 1:
						resetEverything();
						GameController.blackJack();
						validInput = true;
						break;
					case 2:
						resetEverything();
						GameController.mainMenu();
						validInput = true;
						break;
					default:
						System.out.println("Only input 1 or 2 please.");
						break;
					}
				} catch (InputMismatchException e) {
					System.out.println("Only input 1 or 2 please.");
					sc.next();
				}
			}
		}
	}
	
	private static void endGameMessage(boolean playerWin) {
		String message = "";
		if (playerWin) {
			player.win();
			int winnings = Math.round(player.getCurrentBet() * 1.5f);
			message = String.format("Congratulations, you win!"
					+ "\nBet Amount: %d"
					+ "\nWinnings: %d"
					+ "\nMoney: %d"
					+ "\nWin Count: %d"
					+ "\nLoss Count:%d\n",
					player.getCurrentBet(), winnings, player.getMoney(), player.getWins(), player.getLosses());
		} else {
			player.lose();
			message = String.format("Ooooo so sorry for your loss!"
					+ "\nMoney: %d"
					+ "\nWin Count: %d"
					+ "\nLoss Count: %d\n",
					player.getMoney(), player.getWins(), player.getLosses());
		}
		System.out.println(message);
	}
	
	private static void playerTurn() throws InterruptedException {
		System.out.println("Please select an option:"
				+ "\n1. Hit"
				+ "\n2. Stand\n");
		boolean validInput = false;
		while (!validInput) {
			try {
				int input = sc.nextInt();
				switch (input) {
				case 1:
					//System.out.println("1 was hit!");
					draw(true);
					validInput = true;
					break;
				case 2:
					
					validInput = true;
					break;
				default:
					System.out.println("Only input 1 or 2 please.");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Only input 1 or 2 please.");
				sc.next();
			}
		}
		
		System.out.println("WE GOT TO THE END of playerTurn");
		
	}
	
	private static void dealerTurn() throws InterruptedException {
		draw(false);
	}
	
	private static void draw(boolean isPlayer) throws InterruptedException {
		printEllipses(350);
		Card card = deck.pop();
		String message = "";
		
		if (isPlayer) {
			player.addCard(card);
			player.updateTotal();
			message = String.format("You drew a %s"
					+ "Your current total is: %d\n", Deck.cardInfo(card), player.getTotal());
			
		} else {
			dealer.addCard(card);
			dealer.updateTotal();
			message = String.format("Dealer drew a %s"
					+ "The Dealer's current total is: %d\n", Deck.cardInfo(card), dealer.getTotal());
		}
		System.out.println(message);
	}
	
	private static void printEllipses(int time) throws InterruptedException {
		for (int i = 0; i < 3; i++) {
			System.out.print(". ");
			TimeUnit.MILLISECONDS.sleep(time);
		}
		System.out.println("\n");
	}

}
