package Game;

import Pokemon.Pokemon;

/*
 * Coach class
 */
public class Coach {
    private String name;
    private Team team;
    private int age;
    private Gender gender;
    private int gameWinned;

    /**
     * Costruttore della classe Coach
     * @param name
     * @param age
     * @param teamIn
     * @param gender
     * @param gameWinned
     */
    public Coach(String name, int age, Team teamIn, Gender gender, int gameWinned) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.team = teamIn;
        this.gameWinned = gameWinned;
    }

    /**
     * Metodo per ottenere il nome del coach
      @return nome del coach
     */
    public String getName() {
        return name;
    }

    /**
     * Metodo per ottenere la squadra del coach
      @return squadra del coach
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Metodo per ottenere l'età del coach
      @return età del coach
     */
    public int getAge() {
        return age;
    }

    /**
     * Metodo per ottenere il numero di partite vinte dal coach
      @return numero di partite vinte
     */
    public int getGameWinned() {
        return gameWinned;
    }

    /**
     * Metodo per impostare il numero di partite vinte dal coach
     * @param gameWinned
     */
    public void setGameWinned(int gameWinned) {
        this.gameWinned = gameWinned;
    }

    /**
     * Metodo per ottenere il genere del coach
      @return genere del coach
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Metodo per impostare il genere del coach
     * @param teamIn
     */
    public void setTeam(Team teamIn) {
        team = teamIn;
    }

    /**
     * Metodo per impostare il nome del coach
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Metodo per impostare l'età del coach
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Metodo per impostare il genere del coach
      @return pokemon in uso
     */
    public Pokemon getPokemonInUse() {
        for (Pokemon pokemon : this.team.getListPokemon()) {
            if (pokemon.isInUse()) {
                return pokemon;
            }
        }
        return null;
    }

    /**
     * Metodo per impostare il pokemon in uso
     * @param pokemon
     */
    public void setPokemonInUse(Pokemon pokemon) {
        for (Pokemon poke : this.team.getListPokemon()) {
            poke.setInUse(false);
        }
        pokemon.setInUse(true);
    }
}
