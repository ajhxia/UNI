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

    public Coach(String name, int age, Team teamIn, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.team = teamIn;
    }

    public String getName() {
        return name;
    }

    public Team getTeam() {
        return team;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setTeam(Team teamIn) {
        team = teamIn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Pokemon getPokemonInUse() {
        for (Pokemon pokemon : this.team.getListPokemon()) {
            if (pokemon.isInUse()) {
                return pokemon;
            }
        }
        return null;
    }

    public void setPokemonInUse(Pokemon pokemon) {
        for (Pokemon poke : this.team.getListPokemon()) {
            poke.setInUse(false);
        }
        pokemon.setInUse(true);
    }
}
