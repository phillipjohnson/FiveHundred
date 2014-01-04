package mechanics;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Deck implements Iterable<Card> {

    public enum Style{
        THREE_PLAYER,FOUR_PLAYER;
    }
    
    Deck.Style style;
    private final LinkedList<Card> deck = new LinkedList<>();
    
    public Deck(Style style)
    {
        this.style = style;
        buildDeck();
        shuffle();
    }
    
    public Deck(Deck toCopy){
        this.style = toCopy.style;
        this.deck.clear();
        this.deck.addAll(toCopy.deck);
    }
    
    @Override
    public Iterator<Card> iterator() {
        return deck.iterator();
    }

    private void buildDeck(){
        for(Card.Rank r : Card.Rank.values()){
            if(r==Card.Rank.LEFTBOWER 
                    || r==Card.Rank.RIGHTBOWER
                    || r==Card.Rank.JOKER){
                continue;
            }
            else{
                for(Card.Suit s : Card.Suit.values()){
                    if(s==Card.Suit.NONE){
                        continue;
                    }
                    else{
                        deck.add(new Card(r,s));
                    }
                }
            }
        }
        
        if(style==Style.FOUR_PLAYER){
            Iterator<Card> iter = deck.iterator();
            while(iter.hasNext()){
                Card c = iter.next();
                if(c.getRank()==Card.Rank.FOUR && 
                        (c.getSuit()==Card.Suit.CLUB 
                        || c.getSuit()==Card.Suit.SPADE)){
                    iter.remove();
                }
            }
        }
        
        if(style==Style.THREE_PLAYER){
            Iterator<Card> iter = deck.iterator();
            while(iter.hasNext()){
                Card c = iter.next();
                if(c.getRank()==Card.Rank.FOUR
                        ||c.getRank()==Card.Rank.FIVE
                        ||c.getRank()==Card.Rank.SIX){
                    iter.remove();
                }
            }
        }
        
        deck.add(new Card(Card.Rank.JOKER,Card.Suit.NONE));
    }
    
    public Card poll(){
        return deck.poll();
    }
    
    public int size(){
        return deck.size();
    }
    
    public void removeAll(Collection c){
        deck.removeAll(c);
    }
    
    public boolean contains(Object o){
        return deck.contains(o);
    }
    
    public boolean remove(Object o){
        return deck.remove(o);
    }
    
    private void shuffle(){
        Collections.shuffle(deck);
    }

}
