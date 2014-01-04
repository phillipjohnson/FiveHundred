/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mechanics;

import java.util.Objects;
import mechanics.Card.*;

public class Bid{
    public enum Type2 {
        SIX_CLUB(40),SEVEN_CLUB(140),EIGHT_CLUB(240),NINE_CLUB(340),TEN_CLUB(440),
        SIX_SPADE(60),SEVEN_SPADE(160),EIGHT_SPADE(260),NINE_SPADE(360),TEN_SPADE(460),
        SIX_DIAMOND(80),SEVEN_DIAMOND(180),EIGHT_DIAMOND(280),NINE_DIAMOND(380),TEN_DIAMOND(480),
        SIX_HEART(100),SEVEN_HEART(200),EIGHT_HEART(300),NINE_HEART(400),TEN_HEART(500),
        SIX_NONE(120),SEVEN_NONE(220),EIGHT_NONE(320),NINE_NONE(420),TEN_NONE(520),
        NELLO(250),NELLO_EXPOSED(500);

        int points;

        Type2(int points){
            this.points = points;
        }
    }
    
    public enum Type{
        PLAY,PASS;
    }
    
    Type type = Type.PLAY;
    private Card.Suit suit;
    private int tricks;
    private boolean exposed;
    
    /**
     * Create a new Bid with a suit and number of tricks
     * @param suit
     * @param tricks 
     */
    
    public Bid(Type type){
        this.type = type;
    }
    
    public Bid(Card.Suit suit, int tricks){
        this.suit = suit;
        this.tricks = tricks;
        exposed = false;
    }
    
    public Bid(Card.Suit suit, int tricks, boolean exposed){
        this.suit = suit;
        this.tricks = tricks;
        this.exposed = exposed;
    }
    
    public int getRequiredTricks(){
        return tricks;
    }
    
    public Suit getSuit(){
        return suit;
    }
    
    public int getScore(){
        if(type==Type.PASS){
            return 0;
        } else if(tricks==0){
            if(exposed){
                return 500;
            } else {
                return 250;
            }
        } else {
            int baseScore = suit.getBaseBidValue();
            
            int trickMultiplier = (tricks - 6) * 100;
            
            return baseScore + trickMultiplier;
        }
    }
    
    @Override
    public String toString(){
        String bidString = suit.toString() + " " + Integer.valueOf(tricks).toString();
        if(exposed){
            bidString += " Exposed";
        }
        
        return bidString;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.suit);
        hash = 97 * hash + this.tricks;
        hash = 97 * hash + (this.exposed ? 1 : 0);
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
        final Bid other = (Bid) obj;
        if (this.suit != other.suit) {
            return false;
        }
        if (this.tricks != other.tricks) {
            return false;
        }
        if (this.exposed != other.exposed) {
            return false;
        }
        return true;
    }
    
    
}
