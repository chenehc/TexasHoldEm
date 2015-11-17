package texasholdem;

/**
 * The class that contains objects that represent playing cards
 * @author Naresh
 */
public class Card {
    private int rank;
    private char suit;
    
/**
 * Constructor
 *    
 * @param rank The rank of the card(can range from 1 tp 13)
 * @param suit The suit of the card(can be one of: C,S,D,H)
 */
    public Card (int rank, char suit){
        this.rank = rank;
        this.suit = suit;        
    }
    
/**
 * Getter for rank
 * @return Return rank of card passed to the constructor(can range from 1 tp 13)
 */
    public int getRank() {
        return rank;
    }

/**
 * Getter for suit
 * @return Return suit of card passed to the constructor(can be one of: C,S,D,H)
 */
    public char getSuit() {
        return suit;
    }
  
/**
 * The textual representation of the card rank and suit
 */
    public String toString(){
        return rank+""+suit;
    }
    
    public String rankToString(){
    	switch (this.rank){
    	case 2: return "Two";
    	case 3: return "Three";
    	case 4: return "Four";
    	case 5: return "Five";
    	case 6: return "Six";
    	case 7: return "Seven";
    	case 8: return "Eight";
    	case 9: return "Nine";
    	case 10: return "Ten";
    	case 11: return "Jack";
    	case 12: return "Queen";
    	case 13: return "King";
    	case 14: return "Ace";
    	}
		return null;
    }

/*<<<<<<< HEAD
=======*/
    public String suitToString(){
    	switch (this.suit){
    	case 'C': return "Clubs";
    	case 'D': return "Diamonds";
    	case 'S': return "Spades";
    	case 'H': return "Hearts";
    	}
		return null;
    }
    
	public String getFileName(){
		return "Cards/" + rankToString() + " of " + suitToString() + ".gif";
		
	}

//	@Override
	public int compareTo(Card x) {
		if(this.rank>x.rank){ return 1;}
		if(this.rank<x.rank){return -1;}
		return 0;
		
	}

//>>>>>>> b62563a38df1d1f39815e052f714a24a2041e0fa
}
