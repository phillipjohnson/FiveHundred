/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mechanics;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shivasprogeny
 */
public final class Team 
{
    List<Player> players;
    static int teamCounter = 0;
    private int teamNumber = 0;
    private int score = 0;
    public int gamesWon = 0;
    
    public Team(Player... players){
        this.players = new ArrayList<>(java.util.Arrays.asList(players));
        teamNumber = ++teamCounter;
        notifyPlayers();
    }
    
    public void addPlayer(Player player){
        players.add(player);
        notifyPlayers();
    }
    
    private void notifyPlayers(){
        for(Player p : players){
            p.team = this;
        }
    }
    
    public boolean isContractMaker(){
        for(Player p : players){
            if(p.isContractMaker){
                return true;
            }
        }
        return false;
    }
    
    public int getScore(){
        return score;
    }
    
    public void increaseScore(int points){
        score += points;
    }
    
    public void decreaseScore(int points){
        score -= points;
    }
    
    public void resetScore(){
        score = 0;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.teamNumber;
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
        final Team other = (Team) obj;
        if (this.teamNumber != other.teamNumber) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        String out = "";
        for(Player p : players){
            out += p.toString() + " ";
        }
        
        return out;
    }
    
}
