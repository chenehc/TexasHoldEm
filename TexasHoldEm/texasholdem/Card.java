package proofOfConcept;

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

}
