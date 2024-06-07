package Battle;
import java.util.Observable;

import Pokemon.Pokemon;

@SuppressWarnings("deprecation")
public class PokemonModel extends Observable {
    private Pokemon pokemon;
    private int index;

    public PokemonModel(Pokemon pokemon, int index) {
        this.pokemon = pokemon;
        this.index = index;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public int getIndex() {
        return index;
    }
    
    public void evolve(Pokemon evolvedPokemon) {
        this.pokemon = evolvedPokemon;
        setChanged();
        notifyObservers(index);
    }
}