package BlackJack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GameController {
	
	private static Suit[] suits = {Suit.HEARTS, Suit.CLUBS, Suit.SPADES, Suit.DIAMONDS};
	
	public static boolean endGame = false;
	
	public static Scanner sc = new Scanner(System.in);
	public static Player player;
	public static Deck deck;

	
	public static void main(String[] args) throws Exception {
		welcome();
		start();
	}
	
	private static void start() throws InterruptedException {
		player = createPlayer();
		do {
			mainMenu();
		} while(!endGame);
		
		System.out.println("\n\n\nGoodbye!");
	}
	
	public static void mainMenu() throws InterruptedException {
		System.out.println("Please select an option: "
				+ "\n1. Play BlackJack"
				+ "\n2. Display Player Stats"
				+ "\n3. Display Deck (test)"
				+ "\n4. Exit");
		int input = sc.nextInt();
		switch (input) {
		case 1:
			blackJack();
			break;
		case 2:
			diagnosticMessage();
			mainMenu();
			break;
		case 3:
			displayDeck();
			mainMenu();
			break;
		case 4:
			endGame = true;
			break;
		default:
			System.out.println("\n\nOnly integers from 1 to 3 are valid inputs. Returning to main menu...\n\n");
			mainMenu();
		}
	}
	
	public static void blackJack() throws InterruptedException {
		GameLoop.placeBet();
		GameLoop.playGame();
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
	
	// remove current bet once all testing is done
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
	
	
	//move these to GameLoop once test is no longer needed
	public static void createDeck(Deck deck) {
		
		for (int i = 0; i < 4; i++) {	//suits
			for (int j = 1; j < 13; j++) { //card values
				deck.push(new Card(suits[i], j));
			}
		}
	}
	
	//move these to GameLoop once test is no longer needed
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
	
	//move these to GameLoop once test is no longer needed
		private static void displayDeck() {
			deck = new Deck();
			GameController.createDeck(deck);
			
			System.out.println("\n*********UNSHUFFLED DECK*********\n");
			deck.printDeck();
			
			System.out.println("\n*********SHUFFLED DECK*********\n");
			deck = GameController.shuffleDeck(deck);
			deck.printDeck();
		}
	
}
