package ai;
import java.util.*;

import heuristics.ICardHeuristic;
import heuristics.*;
import mechanics.*;

/**
 *
 * @author shivasprogeny
 */
public class OptimalLogic extends Logic {

    private Set<ICardHeuristic> heuristics = new TreeSet<>();

    public OptimalLogic(){
        heuristics.add(new PlayRandomCard());
        heuristics.add(new PlayLowestCostWinningCard());
    }


    @Override
    public Bid makeBid(Hand h){
        Card.Suit s = bestSuit(h);
        int tricks = optimalTricks(s);
        return new Bid(s,tricks);
    }

    @Override
    public Set<ICardHeuristic> getHeuristics(){
        return heuristics;
    }
    
    private Card.Suit bestSuit(Hand h){
        
        Card.Suit bestSuit = null;
        
        EnumMap<Card.Suit,Integer> suitCounts = new EnumMap<>(Card.Suit.class);
        for(Card.Suit s : Card.Suit.values()){
            suitCounts.put(s,0);
        }
        
        Hand handClone = new Hand(h);
        
        for(Card.Suit s : Card.Suit.values()){
            for(Card c : handClone){
                if(c.getSuit()==s
                        || (c.getRank()==Card.Rank.JACK //The left bower case
                            &&c.getSuit().getColor()==s.getColor())){
                    c.setTrump(s); //Assume best-case scenario
                    suitCounts.put(s,(Integer)suitCounts.get(s) + c.getPointValue());
                }
            }
        }
        
        int bestPoints = 0;
        for(Map.Entry<Card.Suit,Integer> p : suitCounts.entrySet()){
            if(p.getValue() > bestPoints){
                bestPoints = p.getValue();
            }
        }
        
        ArrayList<Card.Suit> bestSuits = new ArrayList<>();
        for(Map.Entry<Card.Suit,Integer> p : suitCounts.entrySet()){
            if(p.getValue()==bestPoints){
                bestSuits.add(p.getKey());
            }
        }
        
        int bestSuitsBidValue = 0;
        for(Card.Suit s : bestSuits){
            if(s.getBaseBidValue() > bestSuitsBidValue){
                bestSuit = s;
            }
        }
        
        return bestSuit;
    }
    
    private int optimalTricks(Card.Suit s){
        return 6;
    }
    
    private ArrayList<Card> myTrumpCards(Hand h, Card.Suit trumpSuit){
        Hand handClone = new Hand(h);
        handClone.setTrump(trumpSuit);
        ArrayList<Card> myTrumpCards = new ArrayList<>();
        for(Card c: handClone){
            if(c.getSuit()==trumpSuit){
                myTrumpCards.add(c);
            }
        }
        
        return myTrumpCards;
    }

}
