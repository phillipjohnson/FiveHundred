/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fivehundred;

import mechanics.*;

/**
 *
 * @author shivasprogeny
 */
public class FiveHundred {


    public static void main(String[] args) {
        Table table = new Table(3);
        for (int i = 0; i < 100; i++) {
            table.playGame();
        }
        
        for(Team t : table.teams){
            System.out.println(t + " " + t.gamesWon);
        }
    }
}
