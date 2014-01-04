/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import java.util.HashSet;
import mechanics.Bid;
import mechanics.Card;
import mechanics.Hand;
import org.junit.*;

import static org.junit.Assert.assertEquals;


/**
 *
 * @author shivasprogeny
 */
public class OptimalLogicTest {
    
    Hand hand1;
    Hand hand2;
    
    public OptimalLogicTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        hand1 = new Hand();
        hand1.add(new Card(Card.Rank.EIGHT,Card.Suit.CLUB));
        hand1.add(new Card(Card.Rank.JACK,Card.Suit.CLUB));
        hand1.add(new Card(Card.Rank.ACE,Card.Suit.CLUB));
        hand1.add(new Card(Card.Rank.JOKER,Card.Suit.NONE));
        hand1.add(new Card(Card.Rank.EIGHT,Card.Suit.HEART));
        hand1.add(new Card(Card.Rank.JACK,Card.Suit.HEART));
        hand1.add(new Card(Card.Rank.KING,Card.Suit.HEART));
        hand1.add(new Card(Card.Rank.EIGHT,Card.Suit.SPADE));
        hand1.add(new Card(Card.Rank.QUEEN,Card.Suit.SPADE));
        hand1.add(new Card(Card.Rank.KING,Card.Suit.DIAMOND));
        System.out.println(hand1);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of makeBid method, of class OptimalLogic.
     */
    @Test
    public void testMakeBid() {
        System.out.println("makeBid");
        OptimalLogic instance = new OptimalLogic();
        Bid expResult = new Bid(Card.Suit.CLUB,6);
        Bid result = instance.makeBid(hand1);
        assertEquals(expResult, result);
    }
}