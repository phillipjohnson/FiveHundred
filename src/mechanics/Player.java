package mechanics;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;


public class Player {
    
    private static int playerIdCounter = 0;
    private int playerId;
    public boolean isDealer;
    public boolean isContractMaker;
    public Team team;
    private ai.Logic logic;
    public int tricksTaken;
    Hand hand;
    
    int bidOrder;
    
    public Player(ai.Logic logic){
        hand = new Hand();
        isDealer = false;
        isContractMaker = false;
        tricksTaken = 0;
        this.logic = logic;
        playerId = ++playerIdCounter;
        bidOrder = playerId;
    }
    
    public Bid makeBid(){
        return logic.makeBid(hand);
    }

    //@depricated
//    public Card playCard(){
//        return hand.pop(logic.playCard(hand,round));
//    }
    
    public Card getHighestCard(){
        return null;
    }
    
    public Card getLowestCard(){
        return null;
    }
    
    public Card getBestPlay(){
        return null;
    }
    
    public Card takeTurn(Trick trick, Round round){
        Card card = logic.playCard(trick,hand, round);
        hand.remove(card);
        return card;
    }
    
    @Override
    public String toString(){
        return "Player " + playerId + ": " + logic;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.playerId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (this.playerId != other.playerId) {
            return false;
        }
        return true;
    }
    
    public static Comparator<Player> BidOrderComparator = new Comparator<Player>(){
    
        @Override
        public int compare(Player p1, Player p2){
            //Need to not hard-code the 3
            return p1.bidOrder % 3 - p2.bidOrder % 3;
        }
    
    };
}