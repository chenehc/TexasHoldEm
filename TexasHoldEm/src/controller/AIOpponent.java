package controller;

import model.Hand;
import model.Player;

public class AIOpponent extends Player{
//	Pot pot;
	Game game;
	
	public AIOpponent(Game game){
		super();
		this.game = game;
//		this.pot = game.getPot();
	}
	
	public int choose(){
		if(getHand().getStrength() >=100000000){
			return getChips();
			
		}
		if(getHand().getStrength() >= 10000000){
			double d = getChips()*0.5;
			if((int)d == 0){
				return getChips();
			}
			return (int)d;
		}
		if(getHand().getStrength()>=1000000){
			double d = getChips()*0.45;
			if((int)d == 0){
				return getChips();
			}
			return (int)d;
		}
		if(getHand().getStrength()>=100000){
			double d = getChips()*0.4;
			if((int)d == 0){
				return getChips();
			}
			return (int)d;
		}
		
		if(getHand().getStrength()>=10000){
			double d = getChips()*0.3;
			if((int)d == 0){
				return getChips();
			}
			return (int)d;
		}
		if(getHand().getStrength()>=1000){
			double d = getChips()*0.2;
			if((int)d == 0){
				return getChips();
			}
			return (int)d;
		}
		if(getHand().getStrength()>=100){
			double d = getChips()*0.1;
			if((int)d == 0){
				return getChips();
			}
			return (int)d;
		}
		if(getHand().getStrength()>=10){
			double d = getChips()*0.05;
			if((int)d == 0){
				return getChips();
			}
			return (int)d;
		}
		if(getHand().size()>5&&getHand().getStrength()<15){
			return 0;
		}
		return getHand().getStrength();
		
	}
	
	public void getAction(Pot pot){
		int bet = pot.getBet();
		if(this.getHand().size()>2){
			if(bet > this.choose()){
				pot.fold();
			}
			else if(bet < this.choose()){
				pot.raise(this.choose()-bet);
			}
			else{
				pot.call();
			}
		}
		else{
			pot.call();
		}

	}
}
