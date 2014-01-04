/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import heuristics.ICardHeuristic;
import heuristics.PlayRandomCard;
import java.util.Set;
import java.util.TreeSet;
import mechanics.*;

/**
 *
 * @author shivasprogeny
 */
public class RandomLogic extends Logic{

    Random r = new Random();

    private Set<ICardHeuristic> heuristics = new TreeSet<>();

    public RandomLogic(){
        heuristics.add(new PlayRandomCard());
    }

    @Override
    public Set<ICardHeuristic> getHeuristics(){
        return heuristics;
    }
    
    @Override
    public Bid makeBid(Hand h) {
        int tricks = -1;
        tricks = r.nextInt(11);
        if(tricks !=0 && tricks < 6){
            return new Bid(Bid.Type.PASS);
        }
        
        Card.Suit s;
        
        if(tricks == 0){
            s = Card.Suit.NONE;
        } else {
            s = Card.Suit.values()[r.nextInt(5)];
        }
        
        boolean exposed = false;
        
        if(tricks==0){
            exposed = r.nextBoolean();
        }
        return new Bid(s,tricks,exposed);
        
    }
    
}
