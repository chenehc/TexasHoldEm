package texasholdem;

public class AIOpponent{
	
	Player ai;
	Pot pot;
	
	public AIOpponent(Player ai, Pot pot){
		this.ai = ai;
		this.pot = pot;
	}
	
	public int choose(){
		Hand hand = ai.getHand();
		
		if(hand.getStrength() >=100000000){
			return ai.getChips();
			
		}
		if(hand.getStrength() >= 10000000){
			double d = ai.getChips()*0.5;
			if((int)d == 0){
				return ai.getChips();
			}
			return (int)d;
		}
		if(hand.getStrength()>=1000000){
			double d = ai.getChips()*0.45;
			if((int)d == 0){
				return ai.getChips();
			}
			return (int)d;
		}
		if(hand.getStrength()>=100000){
			double d = ai.getChips()*0.4;
			if((int)d == 0){
				return ai.getChips();
			}
			return (int)d;
		}
		
		if(hand.getStrength()>=10000){
			double d = ai.getChips()*0.3;
			if((int)d == 0){
				return ai.getChips();
			}
			return (int)d;
		}
		if(hand.getStrength()>=1000){
			double d = ai.getChips()*0.2;
			if((int)d == 0){
				return ai.getChips();
			}
			return (int)d;
		}
		if(hand.getStrength()>=100){
			double d = ai.getChips()*0.1;
			if((int)d == 0){
				return ai.getChips();
			}
			return (int)d;
		}
		if(hand.getStrength()>=10){
			double d = ai.getChips()*0.05;
			if((int)d == 0){
				return ai.getChips();
			}
			return (int)d;
		}
		if(hand.size()>5&&hand.getStrength()<15){
			return 0;
		}
		return hand.getStrength();
		
	}
	
	public void bet(int bet){
			if(ai.getHand().size()>2){
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
