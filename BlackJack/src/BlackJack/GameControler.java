package BlackJack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class GameControler {
	
	private static Random rand = new Random();
	private static Suit[] suits = {Suit.HEARTS, Suit.CLUBS, Suit.SPADES, Suit.DIAMONDS};
	private static Player player;
	private static boolean endGame = false;
	private static Scanner sc = new Scanner(System.in);

	
	public static void main(String[] args) {
		welcome();
		start();
	}
	
	private static void start() {
		player = createPlayer();
		do {
			mainMenu();
		} while(!endGame);
		
		System.out.println("\n\n\nGoodbye!");
	}
	
	private static void mainMenu() {
		System.out.println("Please select an option: "
				+ "\n1. Play BlackJack"
				+ "\n2. Display Player Stats"
				+ "\n3. Exit");
		int input = sc.nextInt();
		switch (input) {
		case 1:
			System.out.println("valid 1");
			blackJack();
			break;
		case 2:
			diagnosticMessage();
			mainMenu();
			break;
		case 3:
			endGame = true;
			break;
		default:
			System.out.println("\n\nOnly integers from 1 to 3 are valid inputs. Returning to main menu...\n\n");
			mainMenu();
		}
	}
	
	private static void blackJack() {
		placeBet();
	}
	
	private static void placeBet() {
		String message = String.format("\n%-10s: %-10d", "Money", player.getMoney());
		System.out.println("\n-----------------------------------\n"
				+ "\nHow much would you like to bet this round?"
				+ message);
		int bet = sc.nextInt();
		player.setCurrentBet(bet);
		String[] phrases = {
				"Lets hope they win!",
				"Good luck to you and have fun!",
				"Wow, we've got a high-roller here!",
				"Now that's a lot of money!",
				"I'm rooting for you!",
				"Never before have I seen so much cash!",
				"Whoa, I wouldn't be so cavalier with all that money!",
				"I'm sure we're looking at a winner here!",
				"Make sure to cash out before you spend it all!"
		};
		System.out.println(String.format("%s has bet %d! %s", player.getName(), player.getCurrentBet(), phrases[rand.nextInt(phrases.length)]));
	}
	
	private static void welcome() {
		String message = "Goodday and welcome to Console BlackJack!. The game is played vs the dealer(computer) and with a deck of 48 cards."
				+ "\nYou can bet as much as you like (have) each round. Winning a game earns you 1.5x your bet amount. Losing"
				+ "\nmakes you lose the amount you bet. The game ends when you run out of money or exit the program.";
		System.out.println(message);
	}
	
	private static Player createPlayer() {
		System.out.println("Please enter your name: ");
		String name = sc.nextLine();
		System.out.println(String.format("Hello, %s! We hope you enjoy playing.", name));
		return new Player(name);
	}
	
	public static void diagnosticMessage() {
		String message = String.format("-----------------------------------"
				+ "\n%-12s: %-10s"
				+ "\n%-12s: %-10d"
				+ "\n%-12s: %-10d"
				+ "\n%-12s: %-10d"
				+ "\n%-12s: %-10d"
				+ "\n-----------------------------------",
				"Player", player.getName(), "Money", player.getMoney(), "Current Bet", player.getCurrentBet(), "Wins", player.getWins(), "Losses", player.getLosses());
		System.out.println(message);
	}
	
	private static void createDeck(Deck deck) {
		
		for (int i = 0; i < 4; i++) {	//suits
			for (int j = 1; j < 13; j++) { //card values
				deck.push(new Card(suits[i], j));
			}
		}
	}
	
	private static Deck shuffleDeck(Deck deck) {
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
	
}
