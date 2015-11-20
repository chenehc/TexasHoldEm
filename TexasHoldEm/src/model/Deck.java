package model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Card> {

    public void Set(){
        for(int i =2; i<=14; i++){
            Card card = new Card(i,'C');
            this.add(card);
        }
        for(int i =2; i<=14; i++){
            Card card = new Card(i,'D');
            this.add(card);
        }
        for(int i =2; i<=14; i++){
            Card card = new Card(i,'S');
            this.add(card);
        }
        for(int i =2; i<=14; i++){
            Card card = new Card(i,'H');
            this.add(card);
        }
    }
    
    public void Shuffle(){
        Collections.shuffle(this);
    }
    
    
    
}