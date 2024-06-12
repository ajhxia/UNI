package Game;

import java.util.ArrayList;
import Pokemon.Pokemon;

public class Team {
    private ArrayList<Pokemon> playerTeam = new ArrayList<>();
    private int teamMaxSize;

    /**
     * Costruttore della classe Team
     * @param playerTeamIn
     */
    public Team(ArrayList<Pokemon> playerTeamIn) {
        this.playerTeam = playerTeamIn;
        this.teamMaxSize = 6;
    }

    /**
     * Costruttore della classe Team
     * @param playerTeamIn
     * @param teamMaxSize
     */
    public void addPokemon(Pokemon pokemon) {
        if (playerTeam.size() < teamMaxSize) {
            playerTeam.add(pokemon);
        } else {
            System.out.println("Team is full.");
        }
    }

    /**
     * Metodo per aggiungere un pokemon in una posizione specifica del team
     * @param index
     * @param pokemon
     */
    public void addPokemonAtIndex(int index, Pokemon pokemon) {
        if (index >= 0 && index <= playerTeam.size()) {
            playerTeam.add(index, pokemon);
        }
    }

    /**
     * Metodo per rimuovere un pokemon dal team
     * @param index
     */
    public void removePokemon(int index) {
        if (index >= 0 && index < playerTeam.size()) {
            playerTeam.remove(index);
        } else {
            System.out.println("Invalid index.");
        }
    }

    /**
     * Metodo per ottenere un pokemon dal team
     * @param index
      @return pokemon
     */
    public Pokemon getPokemon(int index) {
        if (index >= 0 && index < playerTeam.size()) {
            return playerTeam.get(index);
        } else {
            System.out.println("Invalid index.");
            return null;
        }
    }

    /**
     * Metodo per ottenere la grandezza del team
      @return grandezza del team
     */
    public int getTeamSize() {
        return playerTeam.size();
    }

    /**
     * Metodo per ottenere la grandezza massima del team
      @return grandezza massima del team
     */
    public int getTeamMaxSize() {
        return teamMaxSize;
    }

    /**
     * Metodo per impostare la grandezza massima del team
     * @param maxSize
     */
    public void setTeamMaxSize(int maxSize) {
        this.teamMaxSize = maxSize;
    }

    /**
     * Metodo per ottenere la lista dei pokemon del team
      @return lista dei pokemon del team
     */
    public ArrayList<Pokemon> getListPokemon() {
        return playerTeam;
    }

    /**
     * Metodo per impostare la lista dei pokemon del team
     * @param playerTeam
     */
    public void setPlayerTeam(ArrayList<Pokemon> playerTeam) {
        this.playerTeam = playerTeam;
    }
}
