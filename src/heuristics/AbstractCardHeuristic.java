/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristics;

/**
 *
 * @author shivasprogeny
 */
public abstract class AbstractCardHeuristic implements ICardHeuristic, Comparable<ICardHeuristic> {
    
    @Override
    public final int compareTo(ICardHeuristic heuristic){
        return Integer.valueOf(heuristic.getWeight()).compareTo(Integer.valueOf(this.getWeight()));
    }
       
}
