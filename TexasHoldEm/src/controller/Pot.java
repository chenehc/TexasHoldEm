package controller;

import model.Player;
import userInterface.TexasHoldEm;

public class Pot {
	private int pot, bet;
	private boolean hasBet = false;
	private Player player1,player2;
	Game game;
	private int checkCount = 0;
	
	//constructor
	public Pot (Player player1, Player player2, Game game){
		this.player1 = player1;
		this.player2 = player2;
		this.pot = 0;
		this.bet = 0;
		this.game = game;
		anti();
		
	}
	
	/**
	 * Method that is used and called at the start of a round to collect the initial pot 
	 */
	public void anti(){
		player1.loseChips(100);
		player2.loseChips(100);
		pot += 200;
	}
	
	/**
	 * Method collects chips from the player if the other player made a raise previously
	 */
	public void call(){
		if (hasBet) {
			if (game.getCurrentPlayer() == 0){
				player1.loseChips(getBet());
				this.pot += getBet();
				game.switchPlayer();
				game.turnEnd();
			}
			else {
				player2.loseChips(getBet());
				this.pot += getBet();
				game.switchPlayer();
				game.turnEnd();
			}
		}
		else if (!hasBet && checkCount == 1){
			game.turnEnd();
			resetCheckCount();
			game.switchPlayer();
			game.getView().log("turn end");
		}else {
			checkCount++;
			game.switchPlayer();
			game.getView().log("next player");
		}
		game.getView().log("Player " + game.getCurrentPlayer() +  " call/checks");
		game.getView().updateChipLabels();
	}
	
	/**
	 * Method that collects chips from the current player 
	 * @param amt int - the amount of chips the player is raising
	 */
	public void raise(int amt){
		resetCheckCount();
		if (game.getCurrentPlayer() == 0){
			if (amt > player1.getChips())
				TexasHoldEm.raiseError(0);
			else {
				this.pot += amt;
				player1.loseChips(amt);
				hasBet = true;
				bet = amt;
				game.getView().log("Player " + game.getCurrentPlayer() + " bets: " + amt + " chips.");
				game.switchPlayer();
			}
		}
		else {
			if (amt > player2.getChips())
				TexasHoldEm.raiseError(0);
			else {
				this.pot += amt;
				hasBet = true;
				bet = amt;
				player2.loseChips(amt);
				game.getView().log("Player " + game.getCurrentPlayer() + " bets: " + amt + " chips.");
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
		if (game.getCurrentPlayer() == 0) 
			distributePot(2);
		else 
			distributePot(1);
		game.getView().log("Player " + game.getCurrentPlayer() + " folds.");
		game.getView().updateChipLabels();
		game.newRound();
	}
	
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
	 * Method that distributes pot to player depending on the strength of their respective hand
	 * @param option int option for distribution 
	 */
	public void distributePot(int option){
		switch(option){
		//option 1: player 1 has better hand than player 2
		case 1:	player1.gainChips(pot);
				pot = 0;
				break;
		//option 2: player 2 has better hand than player 2
		case 2: player2.gainChips(pot);
				pot = 0;
				break;
		//option 3: both players have equal hand strength, divide the pot by 2
		//if pot is even, distribute evenly, if uneven, add one to the next pot 
		//and distribute evenly for (pot-1)/2
		case 3: player1.gainChips(pot / 2);
				player2.gainChips(pot / 2);
				if(pot % 2 == 0)
					pot = 0;
				else
					pot = 1;
		}
		game.getView().updateChipLabels();
	}
	
	public static void main(String args[]){
		
	}
}
