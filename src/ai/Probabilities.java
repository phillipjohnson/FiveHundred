package ai;

import mechanics.*;
import java.util.List;
import java.util.LinkedList;

public class Probabilities {
    private List<Card> availableCards;
    private List<Player> players;
    
    public Probabilities(List<Card> availableCards, List<Player> players){
        this.availableCards = availableCards;
        this.players = players;
    }
    
    public double playerHasSuit(Card.Suit suit){
        double suitCount = 0;
        for(Card c : availableCards){
            if(c.getSuit()==suit){
                suitCount++;
            }
        }

        double cardsInHand = (availableCards.size()-3)/players.size();
        
        double probCardIsNotSuit = 1 - (suitCount / (double)availableCards.size());
        //At least one is suit
        return 1 - Math.pow(probCardIsNotSuit,cardsInHand);
    }
}
