/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mechanics;

import java.util.ArrayList;
import java.util.List;
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
public class TableTest {
    
    public TableTest() {
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
     * Test of sortPlayers method, of class Table.
     */
    @Test
    public void testSortPlayers() {
        Table t = new Table(3);
        
        List<Player> validPlayers = new ArrayList<>();
        validPlayers.add(t.players.get(1));
        validPlayers.add(t.players.get(2));
        validPlayers.add(t.players.get(0));
        
        t.players.get(1).isContractMaker = true;
        t.sortPlayers();
        
        assertEquals(validPlayers,t.players);
    }

    /**
     * Test of playGame method, of class Table.
     */
    @Test
    public void testPlayGame() {

        fail("The test case is a prototype.");
    }
}