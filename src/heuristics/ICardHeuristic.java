package heuristics;

import mechanics.*;

/**
 * User: Phillip
 * Date: 11/13/13
 * Time: 9:00 PM
 */
public interface ICardHeuristic {
    
    public Card apply(Trick t, Hand h, Round round);

    public Card applyNello(Trick t, Hand h, Round round);

    public int getWeight();
}
