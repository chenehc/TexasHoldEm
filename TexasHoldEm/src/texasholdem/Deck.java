package texasholdem;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Create,store,shuffle and distribute the deck of playing cards
 * @author Naresh
 */

public class Deck extends ArrayList<Card> {

/**
 * The method creates every card(its rank and suit) and adds it to the playing deck
 */
    public void Set(){
        for(int i = 2; i <= 14; i++){
            Card card = new Card(i,'C');
            this.add(card);
        }
        for(int i = 2; i <= 14; i++){
            Card card = new Card(i,'D');
            this.add(card);
        }
        for(int i = 2; i <= 14; i++){
            Card card = new Card(i,'S');
            this.add(card);
        }
        for(int i = 2; i <= 14; i++){
            Card card = new Card(i,'H');
            this.add(card);
        }
    }
   
/**
 * The method shuffles the deck of cards
 */
    public void Shuffle(){
        Collections.shuffle(this);
    }
    
    
    
}