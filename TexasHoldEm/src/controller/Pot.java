package controller;

import model.Player;
import userInterface.TexasHoldEm;

public class Pot {
	private int pot, bet;
	private boolean hasBet = false;
	private Player player1;
	private AIOpponent player2;
	Game game;
	private int checkCount = 0;
	
	//constructor
	public Pot (Player player1, AIOpponent player2, Game game){
		this.player1 = player1;
		this.player2 = player2;
		this.pot = 0;
		this.bet = 0;
		this.game = game;
	}
	
	/**
	 * Method that is used and called at the start of a round to collect the forced bet
	 */
	public void ante(){
		if (player1.getChips() < 100){
			pot += player1.getChips();
			player1.loseChips(player1.getChips());
			pot += 100; //for player 2
		}else if (player2.getChips() < 100){
			pot += player2.getChips();
			player2.loseChips(player2.getChips());
			pot += 100; // for player 1
		}else{
			player1.loseChips(100);
			player2.loseChips(100);
			pot += 200;
		}
	}
	
	/**
	 * Method collects chips from the player if the other player made a raise previously
	 */
	public void call(){
		if (hasBet) { //call when the other play has made a bet
			if (game.getCurrentPlayer() == 0){
				player1.loseChips(getBet());
				this.pot += getBet();
				game.getView().log("Player " +  " calls");
				game.switchPlayer();
//				game.turnEnd();
			}
			else {
				player2.loseChips(getBet());
				this.pot += getBet();
				game.getView().log("Dealer " +  " calls");
				game.switchPlayer();
//				game.turnEnd();
			}
		}
		else if (!hasBet && checkCount == 1){ //check if previous player checks and end turn
			if (game.getCurrentPlayer() == 0 )
				game.getView().log("Player " +  " checks");
			else 
				game.getView().log("Dealer " +  " checks");
			game.turnEnd();
			resetCheckCount();
			game.switchPlayer();
//			game.getView().log("turn end");
		}else { //check if there isn't a bet
			checkCount++;
			if (game.getCurrentPlayer() == 0 ){
				game.getView().log("Player " +  " checks");
				player2.first = true;
			}else 
				game.getView().log("Dealer " +  " checks");
			
			game.switchPlayer();
		}
		game.getView().updateChipLabels();
		game.getView().changeBetBtnLabel("Bet");
	}
	
	/**
	 * Method that collects chips from the current player 
	 * @param amt int - the amount of chips the player is raising
	 */
	public void raise(int amt){
		resetCheckCount(); 
		if (hasBet) {
			amt += bet;
			game.getView().changeBetBtnLabel("Raise");
		}
		if (game.getCurrentPlayer() == 0){
			if (amt > player1.getChips()){ //not enough chips
				TexasHoldEm.displayMessage(0);
			}else if (amt <= 0 ){ //negative chips
				TexasHoldEm.displayMessage(2);
			}
			else { 
				this.pot += amt;
				player1.loseChips(amt);
				hasBet = true;
				game.getView().log("Player " + " raises: " + (amt - bet) + " chips.");
				bet = amt;
				game.switchPlayer();
			}
		}
		else {
			if (amt > player2.getChips()){ //not enough chips
				this.pot += player2.getChips();
				hasBet = true;
			}
			else if (amt <= 0 ) //negative chips
				TexasHoldEm.displayMessage(2);
			else {
				this.pot += amt;
				hasBet = true;
				game.getView().log("Dealer " + " raises: " + (amt - bet) + " chips.");
				bet = amt;
				player2.loseChips(amt);
				game.switchPlayer();
			}
		}
		game.getView().updateChipLabels();
	}
	
	/**
	 * Method gets the value of parameter bet
	 * @return bet int
	 */
	public int getBet(){
		return this.bet;
	}
	
	/**
	 * Method that distributes pot to the player that did not fold
	 */
	public void fold(){
		resetCheckCount();
		if (game.getCurrentPlayer() == 0) {
			game.getView().log("Player " + " folds.");
			distributePot(2);
		}else { 
			game.getView().log("Dealer " + " folds.");
			distributePot(1);
		}
		
		game.getView().updateChipLabels();
		game.newRound();
	}
	
	/**
	 * Method used to set checkCount to 0
	 */
	public void resetCheckCount(){
		checkCount = 0;
	}
	
	/**
	 * Method that returns the value of parameter pot
	 * @return pot int
	 */
	public int getPot(){
		return pot;
	}
	
	/**
	 * 
	 */
	public void resetBets(){
		hasBet = false;
		bet = 0;
	}
	
	/**
	 * Method that distributes pot to player depending on the strength of their respective hand
	 * @param option int option for distribution 
	 */
	public void distributePot(int option){
		switch(option){
		//option 1: player 1 has better hand than player 2
		case 1:	game.getView().log("Player 1 gains: " + pot);
				player1.gainChips(pot);
				pot = 0;
				return;
		//option 2: player 2 has better hand than player 2
		case 2: game.getView().log("Player 2 gains: " + pot);
				player2.gainChips(pot);
				pot = 0;
				return;
		//option 3: both players have equal hand strength, divide the pot by 2
		//if pot is even, distribute evenly, if uneven, add one to the next pot 
		//and distribute evenly for (pot-1)/2
		case 3: game.getView().log("Player 1 gains: " + pot / 2);
				player1.gainChips(pot / 2);
				game.getView().log("Player 2 gains: " + pot / 2);
				player2.gainChips(pot / 2);
				if(pot % 2 == 0)
					pot = 0;
				else
					pot = 1;
				return;
		}
		game.getView().updateChipLabels();
	}
}
