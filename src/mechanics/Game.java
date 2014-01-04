package mechanics;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Game {

    Table table;
    private Random r = new Random();
    
    public Game(Table table){
        this.table = table;
    }
    
    private void reset(){
        for(Team t : table.teams){
            t.resetScore();
        }
    }
    
    private Team checkWinner(){
        Set<Team> possibleWinners = new HashSet<>();
        
        boolean goOutNegative = false;
        
        for(Team t : table.teams){
            if(t.getScore() <= -500){
                goOutNegative = true;
            }
        }
        
        for(Team t : table.teams){
            if(goOutNegative){
                //We check everyone and see who has the highest score
                possibleWinners.add(t);
            } else if (t.getScore()>=500){
                //We only care about people with at least 500 points
                possibleWinners.add(t);
            }
        }
        
        if(possibleWinners.isEmpty()){
            return null;
        } else if(possibleWinners.size()==1){
            return (Team)possibleWinners.toArray()[0];
        } else {
            int maxScore = -500;
            Team winner = null;
            //What's the highest score?
            for(Team t : possibleWinners){
                if(t.getScore() >= maxScore){
                    maxScore = t.getScore();
                }
            }
            //Get rid of anyone who doesn't have the higest score
            for (Iterator<Team> it = possibleWinners.iterator(); it.hasNext(); ){
                if (it.next().getScore() < maxScore){
                    it.remove();
                }
            }
            
            if(possibleWinners.size() == 1){
                winner = (Team)possibleWinners.toArray()[0];
            } else {
                //Random winner in the event of a tie
                winner = (Team)possibleWinners.toArray()[r.nextInt(possibleWinners.size())];
            }
            
            return winner;
        }
    }
    
    public void playGame(){
        reset();
        Team winner = null;
        while(winner==null){
            Round round = new Round(table);
            round.playRound();
            winner = checkWinner();
        }
        
        winner.gamesWon ++;
        
        //System.out.println(winner + " " + winner.getScore());
        for(Team t : table.teams){
            //System.out.println(t.getScore());
        }
    }

}
