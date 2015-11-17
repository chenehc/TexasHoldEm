package texasholdem;

public class AIOpponent{
	
	public static int bet(Player ai){
		Hand hand = new Hand();
		
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
}
