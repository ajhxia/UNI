package Battle;
import java.util.Observable;

import Pokemon.Pokemon;

@SuppressWarnings("deprecation")
public class PokemonModel extends Observable {
    private Pokemon pokemon;
    private int index;
    private boolean evolveButtonEnabled;

    /**
     * Costruttore della classe PokemonModel
     * @param pokemon
     * @param index
     */
    public PokemonModel(Pokemon pokemon, int index) {
        this.pokemon = pokemon;
        this.index = index;
        this.evolveButtonEnabled = pokemon.getLvlEvoluzione() == pokemon.getLvl();
    }

    /**
     * Metodo per ottenere il pokemon
      
     */
    public Pokemon getPokemon() {
        return pokemon;
    }

    /**
     * Metodo per ottenere l'indice del pokemon
      
     */
    public int getIndex() {
        return index;
    }
    
    /**
     * Metodo per evolvere un pokemon
     * @param evolvedPokemon
     */
    public void evolve(Pokemon evolvedPokemon) {
        this.pokemon = evolvedPokemon;
        setChanged();
        notifyObservers(index);
    }

    /**
     * Metodo per abilitare il pulsante di evoluzione
     */
    public void disableEvolveButton() {
        this.evolveButtonEnabled = false;
        setChanged();
        notifyObservers(index);
    }
    
    /**
     * Metodo per disabilitare il pulsante di evoluzione
     */
    public boolean isEvolveButtonEnabled() {
        return evolveButtonEnabled;
    }
}