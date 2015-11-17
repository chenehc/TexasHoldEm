
package texasholdem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Hand extends ArrayList<Card>{
    int strength;
    
    public void setStrength(int x){
    	this.strength = x;
    }
    
    public int getStrength(){
    	boolean flush = false;
    	boolean straightFlu = true;
    	boolean straight = false;
    	boolean quad = false;
    	boolean FullH = false;
    	boolean trips = false;
    	boolean twopair = false;
    	boolean pair = false;
    	int count =0;
    	Hand clone = new Hand();
    	Hand played = new Hand();
    	
    	for(int i=0; i<7; i++){
    		clone.add(this.get(i));
    	}
    	
    	Collections.sort(clone);
    	Collections.reverse(clone);
    	
    	//can be optimized if needed
    	FLUSH:
        for(int i =0; i<7; i++){
        	played = new Hand();
        	count = 0;
        	for(int j =0; j<7; j++){
        		if(clone.get(i).getSuit() == clone.get(j).getSuit()){
        			count++;
        			played.add(clone.get(j));
        		}
        		if(count ==5){
        			
        			flush = true;
        			break FLUSH;
        		}
        	}
        }
        
    	
        // can be optimized if needed
    	if(flush){
    		  for(int i=0; i<4; i++){
    	        	if(played.get(i+1).getRank() != played.get(i).getRank()-1){
    	        		straightFlu = false;
    	        		break;
    	        	}
    	        
    	        }
    	}
    	//STRAIGHT FLUSH
        if(straightFlu && flush){
        	return clone.get(0).getRank()*100000000;
        }
        
        //can be optimized
        
       //QUADS
        for(int i =0; i<7; i++){
        	played = new Hand();
        	count = 0;
        	for(int j=i; j<7; j++){
        		if(clone.get(i).getRank() == clone.get(j).getRank()){
        			played.add(clone.get(j));
        			count ++;	
        		}
        		if(count ==4){
        			quad = true;
        			return played.get(0).getRank()*10000000;
        		}
        	}
        }
    
        
        
        TRIPS:
        for(int i =0; i<7; i++){
        	played = new Hand();
        	count = 0;
        	for(int j=0; j<7; j++){
        		if(clone.get(i).getRank() == clone.get(j).getRank()){
        			played.add(clone.get(j));
        			count ++;	
        		}
        		if(count ==3){
        			trips = true;
        			break TRIPS;
        		}
        	}
        }
        
        //FULLHOUSE
        if(trips){
            for(int i =0; i<7; i++){
            	count = 0;
            	for(int j=0; j<7; j++){
            		if(clone.get(i).getRank() == clone.get(j).getRank() && clone.get(i).getRank() != played.get(0).getRank()){
            			count ++;	
            		}
            		if(count ==2){
            			FullH = true;
            			return played.get(0).getRank()*1000000;
            		}
            	}
            }
        	
        }
        
        //FLUSH
    	if(flush){
            for(int i =0; i<7; i++){
            	played = new Hand();
            	count = 0;
            	for(int j =0; j<7; j++){
            		if(clone.get(i).getSuit() == clone.get(j).getSuit()){
            			count++;
            			played.add(clone.get(j));
            		}
            		if(count ==5){
            			return played.get(0).getRank()*100000;
            		}
            	}
            }
    		
    	}

    	//STRAIGHT
        for(int i =0; i<3; i++){
        	played = new Hand();
        	played.add(clone.get(i));
        	count = 1;
        	for(int j=i; j<6; j++){
        		if(clone.get(j+1).getRank() == clone.get(j).getRank()-1){
        			played.add(clone.get(j+1));
        			count ++;	
        		}
        		else{
        			break;
        		}
        		if(count ==5){
        			straight = true;
        			return played.get(0).getRank()*10000;
        		}
        	}
        }
        
        //TRIPS
        if(trips){
                for(int i =0; i<7; i++){
                	played = new Hand();
                	count = 0;
                	for(int j=0; j<7; j++){
                		if(clone.get(i).getRank() == clone.get(j).getRank()){
                			played.add(clone.get(j));
                			count ++;	
                		}
                		if(count ==3){
                			return played.get(0).getRank()*1000;
                		}
                	}
                }
        	
        }
        
        PAIR:
        for(int i =0; i<7; i++){
        	played = new Hand();
        	count = 0;
        	for(int j=0; j<7; j++){
        		if(clone.get(i).getRank() == clone.get(j).getRank()){
        			played.add(clone.get(j));
        			count ++;	
        		}
        		if(count ==2){
        			pair = true;
        			break PAIR;
        		}
        	}
        }
        int pair1 = played.get(0).getRank();
        
        if(pair){
                for(int i =0; i<7; i++){
                	played = new Hand();
                	count = 0;
                	for(int j=0; j<7; j++){
                		if(clone.get(i).getRank() == clone.get(j).getRank() && clone.get(j).getRank() != pair1){
                			played.add(clone.get(j));
                			count ++;	
                		}
                		//TWO PAIR
                		if(count ==2){
                			if(played.get(0).getRank() > pair1){
                				return played.get(0).getRank()*100;
                			}
                			return pair1*100;
                		}
                	}
                }
                //PAIR
                return pair1*10;
                
        	
        }
        //HIGH CARD
        return clone.get(0).getRank();
    }
    
    public String toString(){
    	String hand = "";
    	for(int i =0; i<this.size(); i++){
    		hand = hand+ this.get(i).toString() + " ";
    	}
    	return hand;
    }
    
    public String myhand(){
    	String hand = "";
    	for(int i =0; i<3; i++){
    		hand = hand+ this.get(i).toString() + " ";
    	}
    	return hand;
    }    
    
}
