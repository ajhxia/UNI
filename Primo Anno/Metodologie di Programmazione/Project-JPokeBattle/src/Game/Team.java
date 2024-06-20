package Game;

import java.util.ArrayList;
import Pokemon.Pokemon;

public class Team {
    private ArrayList<Pokemon> playerTeam = new ArrayList<>();
    private int teamMaxSize;

    public Team(ArrayList<Pokemon> playerTeamIn) {
        this.playerTeam = playerTeamIn;
        this.teamMaxSize = 6;
    }

    public void addPokemon(Pokemon pokemon) {
        if (playerTeam.size() < teamMaxSize) {
            playerTeam.add(pokemon);
        } else {
            System.out.println("Team is full.");
        }
    }

    public void removePokemon(int index) {
        if (index >= 0 && index < playerTeam.size()) {
            playerTeam.remove(index);
        } else {
            System.out.println("Invalid index.");
        }
    }

    public Pokemon getPokemon(int index) {
        if (index >= 0 && index < playerTeam.size()) {
            return playerTeam.get(index);
        } else {
            System.out.println("Invalid index.");
            return null;
        }
    }

    public int getTeamSize() {
        return playerTeam.size();
    }

    public int getTeamMaxSize() {
        return teamMaxSize;
    }

    public void setTeamMaxSize(int maxSize) {
        this.teamMaxSize = maxSize;
    }

    public ArrayList<Pokemon> getListPokemon() {
        return playerTeam;
    }

    public void setPlayerTeam(ArrayList<Pokemon> playerTeam) {
        this.playerTeam = playerTeam;
    }
}
