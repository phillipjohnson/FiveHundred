package mechanics;

import ai.*;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

public class Table {
    public List<Player> players;
    public List<Team> teams;
    
    /**
     * Creates a new table of players
     * @param numberOfPlayers The number of people playing
     */
    public Table(int numberOfPlayers){
        players = new ArrayList<>();
        for(int i = 0; i < numberOfPlayers - 1; i++){
            players.add(new Player(new RandomLogic()));
        }
        players.add(new Player(new OptimalLogic()));

        assignTeams();
    }
    
    private void assignTeams(){
        
        teams = new ArrayList<>();
        
        if(players.size() % 2 == 1){
            for(int i = 0; i < players.size(); i++){
                Team t = new Team(players.get(i));
                teams.add(t);
            }
        } else {
            Team teamA = new Team();
            Team teamB = new Team();
            teams.add(teamA);
            teams.add(teamB);
            for(int i = 0; i < players.size(); i++){
                if(i % 2 == 0){
                    teamA.addPlayer(players.get(i));
                } else {
                    teamB.addPlayer(players.get(i));
                }
            }
        }
    }
    
    /*
     * Reorganize the players so that the contract winner is first
     * and the remaining players are added in order
     */
    public void sortPlayers(){
        List<Player> orderedPlayers = new ArrayList<>();
        List<Player> iter = new ArrayList<>(players);
        //Keep going as long as there are still players
        boolean foundWinner = false;
        while(!players.isEmpty()){
            for(Player p : iter){
                if(p.isContractMaker && !orderedPlayers.contains(p)){
                    orderedPlayers.add(p);
                    players.remove(p);
                    foundWinner = true;
                } else {
                    if(foundWinner && !orderedPlayers.contains(p)){ //We can't add players until the contract winner is first
                        orderedPlayers.add(p);
                        players.remove(p);
                    }
                }
            }
        }
        
        players = orderedPlayers;
    }
    
    public void playGame(){
        Game game = new Game(this);
        game.playGame();
    }

}
