package heuristics;

import mechanics.*;

/**
 * User: Phillip
 * Date: 11/13/13
 * Time: 9:44 PM
 */
public class PlayLowestCostWinningCard extends AbstractCardHeuristic {
    @Override
    public Card apply(Trick t, Hand h, Round round){
        
        if(t.getCardsPlayed().isEmpty()){
            //Does not apply if leading
            return null;
        }

        Deck mockDeck = getAvailableCards(round, t, h, false);

        for(Card c : h){ //Already sorted by definition
            if(mockDeck == null){
                int i = 1;
            }
            if(mockDeck.contains(c)){
                return c;
            }
        }

        return null;

    }

    @Override
    public Card applyNello(Trick t, Hand h, Round round){
        if(t.getCardsPlayed().isEmpty()){
            //Does not apply if leading
            return null;
        }
        
        Deck mockDeck = getAvailableCards(round, t, h, true);

        for(Card c : h.descendingSet()){ //Already sorted by definition
            if(mockDeck.contains(c)){
                return c;
            }
        }

        return null;

    }

    @Override
    public int getWeight(){
        return 100;
    }

    private Deck getAvailableCards(Round round, Trick t, Hand h, boolean nello){
        Card.Suit trump = round.getContractBid().getSuit();

        Deck mockDeck = new Deck(Deck.Style.THREE_PLAYER);

        mockDeck.removeAll(round.getCardsPlayed());
        
        Deck iterDeck = new Deck(mockDeck);

        for(Card c : iterDeck){
            if(c.getSuit()==trump){
                c.setTrump(trump);
            }
            for(Card trickCard : t.getCardsPlayed()){
                if((!nello && c.compareTo(trickCard) < 0) || //Remove any card that can't win the trick
                    (nello && c.compareTo(trickCard) > 0)) //Remove any card that will win the trick
                {
                    mockDeck.remove(c);
                }
            }
        }

        Card.Suit leadSuit = t.getCardsPlayed().get(0).getSuit();

        boolean followSuit = false;

        for(Card c : h){
            if(c.getSuit() == leadSuit){
                followSuit = true;
                break;
            }
        }

        if(followSuit){
            iterDeck = new Deck(mockDeck);
            for(Card c : iterDeck){
                if(c.getSuit() != leadSuit){
                    mockDeck.remove(c);
                }
            }
        }

        return mockDeck;
    }
}
