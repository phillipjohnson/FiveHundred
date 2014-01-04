/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import java.util.Iterator;
import mechanics.Card;
import mechanics.Deck;
import mechanics.Player;
import java.util.List;
import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author shivasprogeny
 */
public class ProbabilitiesTest {
    
    public ProbabilitiesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of playerHasSuit method, of class Probabilities.
     */
    @Test
    public void testPlayerHasSuitSomeGone() {
        Card.Suit suit = Card.Suit.CLUB;
        Deck d = new Deck(Deck.Style.THREE_PLAYER);
        List<Player> players = new LinkedList<>();
        players.add(new Player(new RandomLogic()));
        players.add(new Player(new RandomLogic()));
        players.add(new Player(new RandomLogic()));
        Iterator<Card> iter = d.iterator();
        while(iter.hasNext()){
                Card c = iter.next();
                if(c.getSuit()==Card.Suit.CLUB &&
                        (c.getRank()==Card.Rank.SEVEN
                        ||c.getRank()==Card.Rank.EIGHT
                        ||c.getRank()==Card.Rank.NINE)){
                    iter.remove();
                }
        }
        Probabilities instance = new Probabilities((List)d,players);
        double expResult = 0.806;
        double result = instance.playerHasSuit(suit);
        assertEquals(expResult, result, 0.001);
    }
    
    @Test
    public void testPlayerHasSuitAllGone() {
        System.out.println("playerHasSuit");
        Card.Suit suit = Card.Suit.CLUB;
        Deck d = new Deck(Deck.Style.THREE_PLAYER);
        List<Player> players = new LinkedList<>();
        players.add(new Player(new RandomLogic()));
        players.add(new Player(new RandomLogic()));
        players.add(new Player(new RandomLogic()));
        Iterator<Card> iter = d.iterator();
        while(iter.hasNext()){
                Card c = iter.next();
                if(c.getSuit()==Card.Suit.CLUB){
                    iter.remove();
                }
        }
        Probabilities instance = new Probabilities((List)d,players);
        double expResult = 0.0;
        double result = instance.playerHasSuit(suit);
        assertEquals(expResult, result, 0.001);
    }
}