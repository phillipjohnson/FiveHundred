package mechanics;

public class Card implements Comparable<Card> {
    
    public enum Rank{
        FOUR(4),FIVE(5),
        SIX(6),SEVEN(7),EIGHT(8),NINE(9),TEN(10),
        JACK(11),QUEEN(12),KING(13),ACE(14),
        LEFTBOWER(15),RIGHTBOWER(16),JOKER(17);

        int value;

        private Rank(int value)
        {
            this.value = value;
        }
        
        @Override
        public String toString(){
            switch(this){
            case KING:
                return "K";
            case QUEEN:
                return "Q";
            case JACK:
                return "J";
            case ACE:
                return "A";
            case LEFTBOWER:
                return "J";
            case RIGHTBOWER:
                return "J";
            case JOKER:
                return "R";
            default:
                return String.valueOf(value);
            }
        }
    }
    
    public enum Suit{
        HEART(false,Color.RED,80),
        CLUB(false,Color.BLACK,60),
        SPADE(false,Color.BLACK,40),
        DIAMOND(false,Color.RED,100),
        NONE(true,Color.NONE,120);
        
        boolean trump;
        private Color color;
        private int baseBidValue;
        
        private Suit(boolean trump, Color color, int baseBidValue)
        {
            this.trump = trump;
            this.color = color;
            this.baseBidValue = baseBidValue;
        }
        
        public void setTrump(boolean trump) {
            this.trump = trump;
        }
        
        public Color getColor(){
            return color;
        }
        
        public int getBaseBidValue(){
            return baseBidValue;
        }
        
        @Override
        public String toString(){
            switch(this){
            case HEART:
                return "(H)";
            case DIAMOND:
                return "(D)";
            case CLUB:
                return "(C)";
            case SPADE:
                return "(S)";
            case NONE:
                return "(-)";
            default:
                assert false;
                return null;
            }
        }
    }
    
    public enum Color{
        RED,BLACK,NONE;
    }
    
    private Rank rank;
    private Suit suit;

    public Card(Rank rank, Suit suit)
    {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank()
    {
        return rank;
    }

    public Suit getSuit()
    {
        return suit;
    }
    
    public int getPointValue(){
        return rank.value;
    }
    
    public void setTrump(Suit s){
        suit.trump = true;
        if (rank==Rank.JACK){
            if(s.equals(this.suit)){
                rank=Rank.RIGHTBOWER;
            } else {
                rank=Rank.LEFTBOWER;
            }
        }
    }
    
    @Override
    public int compareTo(Card otherCard){
        if(this==otherCard){
            return 0;
        }
        if(this.equals(otherCard)){
            return 0;
        } else if(this.suit==otherCard.suit){
            //Simple, which card is higher?
            if (this.rank.value > otherCard.rank.value){
                return 1;
            } else {
                return -1;
            }
        } else {
            //Trump always beats non-trump
            if(this.suit.trump && !otherCard.suit.trump){
                return 1;
            } else if (!this.suit.trump && otherCard.suit.trump){
                return -1;
            } else {
                //An off-suit can never beat another card
                return -1;
            }
        }
    }

    @Override
    public String toString()
    {
        return rank.toString() + suit.toString();
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 43 * hash + (this.rank != null ? this.rank.hashCode() : 0);
        hash = 43 * hash + (this.suit != null ? this.suit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Card other = (Card) obj;
        if (this.rank != other.rank)
        {
            return false;
        }
        if (this.suit != other.suit)
        {
            return false;
        }
        return true;
    }
    
}
