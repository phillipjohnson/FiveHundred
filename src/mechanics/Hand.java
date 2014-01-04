/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mechanics;

import java.util.TreeSet;

/**
 *
 * @author shivasprogeny
 */
public class Hand extends TreeSet<Card> {
    
    public Hand(){
        super();
    }
    
    public Hand(Hand h){
        super(h);
    }
    
    public void setTrump(Card.Suit s){
        for(Card c : this){
            if(c.getSuit()==s || 
                        (c.getSuit().getColor()==s.getColor() 
                            && c.getRank()==Card.Rank.JACK)){
                    c.setTrump(s);
                }
        }
    }
    
    public Card pop(Card c) {
        if(remove(c)){
            return c;
        }
        
        return null;
    }
    
}