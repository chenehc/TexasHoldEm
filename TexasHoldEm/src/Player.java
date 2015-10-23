
public class Player {
	private int chips;
	private Hand hand;
	
	public Player(){
		this.chips = 2000;
		this.hand = new Hand();
	}

	public int getChips() {
		return chips;
	}

	public void gainChips(int chips) {
		this.chips += chips;
	}
	
	public void loseChips(int chips) {
		this.chips -= chips;
	}
	
	public Hand getHand() {
		return hand;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}
	
	
}
