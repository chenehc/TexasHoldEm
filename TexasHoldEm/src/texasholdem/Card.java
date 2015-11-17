package proofOfConcept;

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

	

	@Override
	public int compareTo(Card x) {
		if(this.rank>x.rank){ return 1;}
		if(this.rank<x.rank){return -1;}
		return 0;
		
	}

}
