package heuristics;

import mechanics.Card;
import mechanics.Hand;
import mechanics.Round;
import mechanics.Trick;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Phillip
 * Date: 11/21/13
 * Time: 10:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayRandomCard extends AbstractCardHeuristic {

    Random r = new Random();

    @Override
    public Card apply(Trick t, Hand h, Round r){
        return getRandomCard(h);
    }

    @Override
    public Card applyNello(Trick t, Hand h, Round r){
        return getRandomCard(h);
    }

    @Override
    public int getWeight(){
        return Integer.MIN_VALUE;
    }

    private Card getRandomCard(Hand h){
        boolean foundCard = false;
        ArrayList<Card> potentialCards = new ArrayList<>();
        while(!foundCard){
            int val = r.nextInt(14)+4;
            for(Card c : h){
                if(c.getPointValue()==val){
                    potentialCards.add(c);
                    foundCard = true;
                }
            }
        }
        return potentialCards.get(r.nextInt(potentialCards.size()));
    }
}
