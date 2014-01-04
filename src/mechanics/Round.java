package mechanics;

import java.util.*;

public class Round {
    
    private Table table;
    private Deck deck;
    private List<Card> widow;
    private Bid contractBid;
    private Set<Card> cardsPlayed = new HashSet<>();
    
    public Round(Table table){
        this.table = table;
        if(table.players.size()==3){
            deck = new Deck(Deck.Style.THREE_PLAYER);
        } else {
            deck = new Deck(Deck.Style.FOUR_PLAYER);
        }
        widow = new ArrayList<>();
        
    }
    
    private void resetPlayers(){
        for(Player p : table.players){
            p.tricksTaken = 0;
            p.isContractMaker = false;
            p.isDealer = false;
            p.hand.clear();
            p.bidOrder ++;
        }
    } 
    
    private void Deal(){
        for(Player p : table.players){
            for(int i = 0; i < 10; i++){
                p.hand.add((Card)deck.poll());
            }
        }
        
        for(int i = 0; i < deck.size(); i++){
            widow.add((Card)deck.poll());
        }
    }
    
    public Bid Bid(){
        Bid winningBid = new Bid(Bid.Type.PASS);
        ArrayList<Player> bidders = new ArrayList<>(table.players);
        Collections.sort(bidders,Player.BidOrderComparator);
        //System.out.println(bidders);
        while(bidders.size() > 1){
            Iterator it = bidders.iterator();
            while(it.hasNext()){
            Player p = (Player)it.next();
                Bid bid = p.makeBid();
                if(bid.type == Bid.Type.PASS || bid.getScore() <= winningBid.getScore()){
                    it.remove();
                } else {
                    winningBid = bid;
                }
            }
        }
        
        if(bidders.isEmpty()){
            return null;
        }
        
        //System.out.println(bidders);
        
        for(Player p : table.players){
            if(p.equals(bidders.get(0))){
                p.isContractMaker = true;
            }
        }
        
        Card.Suit trumpSuit = winningBid.getSuit();
        int tricks = winningBid.getRequiredTricks();
        contractBid = new Bid(trumpSuit,tricks);
        setTrump(trumpSuit);
        
        return winningBid;
    }
    
    private void setTrump(Card.Suit trumpSuit){
        for(Player p : table.players){
            for(Card c : p.hand){
                if(c.getSuit()==trumpSuit || 
                        (c.getSuit().getColor()==trumpSuit.getColor() 
                            && c.getRank()==Card.Rank.JACK)){
                    c.setTrump(trumpSuit);
                }
            }
        }
    }
    
    private void playTricks(){
        table.sortPlayers();
        for(int i = 0; i < 10; i++){
            Trick t = new Trick();
            for(Player p : table.players){
                t.addCard(p, p.takeTurn(t,this));
            }
            t.getWinner().tricksTaken++;

            cardsPlayed.addAll(t.getCardsPlayed());
        }
    }
    
    private void Score(){
        for(Team team : table.teams){
            int tricksTaken = 0;
            for(Player p : team.players){
                tricksTaken += p.tricksTaken;
            }
            if(team.isContractMaker()){
                if(tricksTaken >= contractBid.getRequiredTricks()){
                    team.increaseScore(contractBid.getScore());
                } else {
                    team.decreaseScore(contractBid.getScore());
                }
            } else {
                team.increaseScore(tricksTaken * 10);
            }
        }
    }
    
    public void playRound(){
        resetPlayers();
        Deal();
        Bid winningBid = Bid();
        if(winningBid != null){
            playTricks();
            Score();
        }
    }

    public Bid getContractBid(){
        return contractBid;
    }

    public Set<Card> getCardsPlayed(){
        return cardsPlayed;
    }
    
}
