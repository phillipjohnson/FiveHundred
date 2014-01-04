package ai;

import java.util.List;
import java.util.Set;

import heuristics.ICardHeuristic;
import java.util.ArrayList;
import java.util.Collections;
import mechanics.*;

/**
 * {Insert class description here}
 *
 * @author Phillip Johnson
 */

public abstract class Logic {

    public abstract Set<ICardHeuristic> getHeuristics();

    public abstract Bid makeBid(Hand h);

    public Card playCard(Trick t, Hand h, Round round){
        Card c = null;
        for(ICardHeuristic heuristic : getHeuristics()){
            if(round.getContractBid().getRequiredTricks()==0){
                c = heuristic.applyNello(t,h,round);
            } else {
                c = heuristic.apply(t,h,round);
            }
            
            if(c != null){
                return c;
            }
        }

        //Emergency catch-all, just play the next card you have
        return h.pollLast();

    }

}
