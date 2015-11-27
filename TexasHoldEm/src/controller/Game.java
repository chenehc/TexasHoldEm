package controller;

import java.io.File;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import model.Deck;
import model.Hand;
import model.Player;
import userInterface.TexasHoldEm;

public class Game{
	private int currentPlayer;
	private Player player1, player2;
	private AIOpponent AIplayer;
	private Deck deck;
	private Hand p1hand;
    private Hand p2hand;
    private int cardCount;
    private boolean gameEnd;
    private TexasHoldEm view;
    private Pot pot;
	
    //constructor
	public Game(Player player1, Player player2, TexasHoldEm view){
		this.player1 = player1;
		this.player2 = player2;
		this.currentPlayer = 0;
		deck = new Deck();
		deck.Set();
		deck.Shuffle();
		this.gameEnd = false;
		pot = new Pot(player1, player2, this);
		this.view = view;
	}
	
	//constructor
	public Game(Player player1, TexasHoldEm view){
		this.player1 = player1;
		this.AIplayer = new AIOpponent(this);
		this.player2 = this.AIplayer;
		this.currentPlayer = 0;
		deck = new Deck();
		deck.Set();
		deck.Shuffle();
		this.gameEnd = false;
		pot = new Pot(this.player1, player2, this);
		this.view = view;
		trackAI();
	}
	
	/**
	 * Method that switches the current player, used for turn switch
	 */
	public void switchPlayer(){ //0 is player 1, 1 is player 2
		this.currentPlayer = (this.currentPlayer + 1) % 2 ;
	}
	
	public void trackAI(){
		Thread th = new Thread(){
			@Override
			public void run(){
				while(true){
					if (currentPlayer == 1){
						AIplayer.getAction(pot);
						switchPlayer();
					}
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		th.start();
	}
	
	public void turnEnd(){
		next_card();
		view.createCommunityCard();
		view.showCommunity();
	}
	
	public TexasHoldEm getView(){
		return view;
	}
	/**
	 * Method that returns the value of parameter pot
	 * @return Pot
	 */
	public Pot getPot(){
		return pot;
	}
	
	/**
	 * Method that returns the value of parameter currentPlayer
	 * @return int
	 */
	public int getCurrentPlayer(){
		return currentPlayer;
	}
	
	/**
	 * Method that returns the value of parameter chips for player 1
	 * @return chips int
	 */
	public int getChipsP1(){
		return player1.getChips();
	}
	
	/**
	 * Method that returns the value of parameter chips for player 2
	 * @return chips int
	 */
	public int getChipsP2(){
		return player2.getChips();
	}
	
	/**
	 * Method that deals 3 cards to each player
	 */
	public void deal(){
		deck.Shuffle();
		p1hand = new Hand();
		p2hand = new Hand();
		player1.setHand(p1hand);
		player2.setHand(p2hand);
		cardCount = 0;
		p1hand.add(deck.get(cardCount));
		cardCount++;
		p2hand.add(deck.get(cardCount));
		cardCount++;
		p1hand.add(deck.get(cardCount));
		cardCount++;
		p2hand.add(deck.get(cardCount));
		cardCount++;
		for(int i =0; i<3; i++){
			next_card();
		}
		view.log("cards are dealt");
		view.updateChipLabels();
	}

	/**
	 * Method that deals the same card to each player, represents the community card
	 */
	public void next_card(){
		p1hand.add(deck.get(cardCount));
		p2hand.add(deck.get(cardCount));
		cardCount++;
		view.log("Next community card shown");
	}

	/**
	 * Method that returns the value of parameter gameEnd
	 * @return gameEnd boolean
	 */
	public boolean isGameEnd(){
		return gameEnd;
	}
	
	/**
	 * Method that ends the round and starts a new round
	 */
	public void newRound(){
		deck.Shuffle();
		view.reset();
		gameEnd = false;
		pot.resetCheckCount();
		view.log("new round");
		view.updateChipLabels();
	}
	
	/**
	 * Method that evaluates the round if no player has folded  
	 */
	public void evaluateRound(){
		if(player1.getHand().getStrength() > player2.getHand().getStrength())
			pot.distributePot(1);
		else if(player1.getHand().getStrength() < player2.getHand().getStrength())
			pot.distributePot(2);
		else
			pot.distributePot(3);
		view.log("round evaluated");
		newRound();
	}
	
//	public static void main(String args[]){
//		Player p1 = new Player();
//		Player p2 = new Player();
//		TexasHoldEm t= new TexasHoldEm();
//		Game game = new Game(p1, p2, t);
//		game.deal();
//		System.out.println(p1.getHand().toString());
//		game.deal();
//		System.out.println(p1.getHand().toString());
//	}
	

}
