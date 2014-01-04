package mechanics;

import java.util.*;

/**
 * A series of cards played
 *
 * @author Phillip Johnson
 */

/**
 * Creates a new instance of Trick
 */
public class Trick {
    
    private final LinkedHashMap<Player,Card> cardsMap;
    
    public Trick(){
        cardsMap = new LinkedHashMap<>();
    }
    
    public void addCard(Player p, Card c){
        cardsMap.put(p, c);
    }
    
    public Player getWinner(){
        Player winner = null;
        Card highestCard = null;
        for (Map.Entry pair : cardsMap.entrySet()) {
            Card currentCard = (Card)pair.getValue();
            Player currentPlayer = (Player)pair.getKey();
            
            if(highestCard == null){
                highestCard = currentCard;
                winner = currentPlayer;
            } else {
                if(currentCard.compareTo(highestCard) > 0){
                    highestCard = currentCard;
                    winner = currentPlayer;
                }
            }
        }
        
        return winner;
    }
    
    @Override
    public String toString(){
        String toString = "[";
        for (Map.Entry pairs : cardsMap.entrySet()){
            toString += pairs.getKey() + "|" + pairs.getValue() + " ";
        }
        toString += "]";
        
        return toString;
    }

    public int numberCardsPlayed(){
        return cardsMap.size();
    }

    public List<Card> getCardsPlayed(){
        return new ArrayList<Card>(cardsMap.values());
    }
}
