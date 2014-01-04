/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fivehundred;

import mechanics.Deck;
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
public class DeckTest {
    
    public DeckTest() {
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

    @Test
    public void fourPlayerDeck() {
        Deck d = new Deck(Deck.Style.FOUR_PLAYER);
        assertEquals(d.size(),43);
    }
    
    @Test
    public void threePlayerDeck() {
        Deck d = new Deck(Deck.Style.THREE_PLAYER);
        assertEquals(d.size(),33);
    }
}