package texasholdem;

public class Game{
	private int currentPlayer;
	private Player player1, player2;
	private Deck deck;
	private Hand p1hand;
    private Hand p2hand;
    private int cardCount;
    private boolean gameEnd;
    private Pot pot;
    
	//constructor
	public Game(Player player1, Player player2){
		this.player1 = player1;
		this.player2 = player2;
		this.currentPlayer = 0;
		deck = new Deck();
		deck.Set();
		deck.Shuffle();
		this.gameEnd = false;
		pot = new Pot(player1, player2, this);
	}
	
	/**
	 * Method that switches the current player, used for turn switch
	 */
	public void playerSwitch(){ //0 is player 1, 1 is player 2
		this.currentPlayer = (this.currentPlayer + 1) % 2 ;
	}
	
	/**
	 * Method that returns the value of parameter currentPlayer
	 */
	public int getCurrentPlayer(){
		return currentPlayer;
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
	}

	/**
	 * Method that deals the same card to each player, represents the community card
	 */
	public void next_card(){
		p1hand.add(deck.get(cardCount));
		p2hand.add(deck.get(cardCount));
		cardCount++;
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
	public void roundEnd(){
		deal();
		gameEnd = false;
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
	}

}
