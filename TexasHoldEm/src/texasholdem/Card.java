package texasholdem;

public class Card implements Comparable<Card>{
    private int rank;
    private char suit;
    
    
    public Card (int rank, char suit){
        this.rank = rank;
        this.suit = suit;
        
    }
    
    public int getRank() {
        return rank;
    }

    public char getSuit() {
        return suit;
    }
    
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

	@Override
	public int compareTo(Card x) {
		if(this.rank>x.rank){ return 1;}
		if(this.rank<x.rank){return -1;}
		return 0;
		
	}

}
