package Game;

import java.util.ArrayList;

/*
 * Npc class 
 * Create npc con un random team di 6 pokemon
 */

import Pokemon.CreateObjectsPokemon;
import Pokemon.Pokemon;

public class Npc {
    public static Coach createNpc(int index) {
        ArrayList<Pokemon> npcTeam = new ArrayList<>();
        int teamMaxSize = 6; // Dimensione massima del team
        int startCount = 0; // Contatore per i Pokémon con etichetta "start"

        while (startCount < teamMaxSize) {
            int pokeIndex = (int) (Math.random() * 42) + 1;
            Pokemon pokemon = CreateObjectsPokemon.getPokemon(pokeIndex);

            // Controlla se il Pokémon ha l'etichetta "start"
            if (pokemon.getStart() != null) {
                npcTeam.add(pokemon);
                startCount++;
            }
        }

        Team team = new Team(npcTeam);
        switch (index) {
            case 1:
                return new Coach("Brock", 15, team, "M");
            case 2:
                return new Coach("Misty", 15, team, "F");
            case 3:
                return new Coach("Lt. Surge", 15, team, "M");
            case 4:
                return new Coach("Erika", 15, team, "F");
            case 5:
                return new Coach("Koga", 15, team, "M");
            default:
                return null;
        }
    }
}
