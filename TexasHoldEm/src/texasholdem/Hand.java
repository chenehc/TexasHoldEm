package texasholdem;

import java.util.ArrayList;

/**
 * Keeps track of players cards, and calculates hand ranking
 * @author Naresh
 */
public class Hand extends ArrayList<Card> {
    int strength;
    
/**
 * setter for Strength 
 * @param x
 */
    public void setStrength(int x){
    	this.strength = x;
    }

/**
 * getter for strength
 * @return
 */
    public int getStrength(){
        return strength;
    }
   
/**
 * The textual representation for all the cards in a player's hand
 */
    public String toString(){
    	String hand = "";
    	for(int i =0; i<this.size(); i++){
    		hand = hand+ this.get(i).toString() + " ";
    	}
    	return hand;
    }
 
/**
 * The player's hand
 * @return
 */
    public String myhand(){
    	String hand = "";
    	for(int i =0; i<3; i++){
    		hand = hand+ this.get(i).toString() + " ";
    	}
    	return hand;
    } 
}
