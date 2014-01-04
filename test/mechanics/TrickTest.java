/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mechanics;

import ai.RandomLogic;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Phillip
 */
public class TrickTest
{
    
    public TrickTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    @Test
    public void testGetWinnerSameSuit()
    {
        Player p1 = new Player(new RandomLogic());
        Player p2 = new Player(new RandomLogic());
        Player p3 = new Player(new RandomLogic());
        Trick t = new Trick();
        t.addCard(p1, new Card(Card.Rank.ACE,Card.Suit.HEART));
        t.addCard(p2, new Card(Card.Rank.KING,Card.Suit.HEART));
        t.addCard(p3, new Card(Card.Rank.EIGHT,Card.Suit.HEART));
        
        assertEquals(t.getWinner(),p1);
    }
    
    @Test
    public void testGetWinnerOffSuit()
    {
        Player p1 = new Player(new RandomLogic());
        Player p2 = new Player(new RandomLogic());
        Player p3 = new Player(new RandomLogic());
        Trick t = new Trick();
        t.addCard(p1, new Card(Card.Rank.EIGHT,Card.Suit.HEART));
        t.addCard(p2, new Card(Card.Rank.KING,Card.Suit.CLUB));
        t.addCard(p3, new Card(Card.Rank.EIGHT,Card.Suit.CLUB));
        
        assertEquals(t.getWinner(),p1);
    }
    
    @Test
    public void testGetWinnerTrumpSuit()
    {
        Player p1 = new Player(new RandomLogic());
        Player p2 = new Player(new RandomLogic());
        Player p3 = new Player(new RandomLogic());
        Trick t = new Trick();
        t.addCard(p1, new Card(Card.Rank.EIGHT,Card.Suit.HEART));
        t.addCard(p2, new Card(Card.Rank.KING,Card.Suit.CLUB));
        Card c3 = new Card(Card.Rank.EIGHT,Card.Suit.SPADE);
        c3.setTrump(Card.Suit.SPADE);
        t.addCard(p3, c3);
        
        assertEquals(t.getWinner(),p3);
    }
    
    @Test
    public void testGetWinnerJoker()
    {
        Player p1 = new Player(new RandomLogic());
        Player p2 = new Player(new RandomLogic());
        Player p3 = new Player(new RandomLogic());
        Trick t = new Trick();
        t.addCard(p1, new Card(Card.Rank.EIGHT,Card.Suit.HEART));
        t.addCard(p2, new Card(Card.Rank.KING,Card.Suit.CLUB));
        t.addCard(p3, new Card(Card.Rank.JOKER,Card.Suit.NONE));
        
        assertEquals(t.getWinner(),p3);
    }
}