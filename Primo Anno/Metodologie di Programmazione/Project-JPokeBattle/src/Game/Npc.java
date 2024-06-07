package Game;

import java.util.ArrayList;

/*
 * Npc class 
 * Create npc con un random team di 6 pokemon
 */

import Pokemon.CreateObjectsPokemon;
import Pokemon.Pokemon;

public class Npc {
    static boolean start = false;

    public static Coach createNpc(int index, int teamSize) {
        ArrayList<Pokemon> npcTeam = new ArrayList<>();
        if (start == false) {
            int startCount = 0; // Contatore per i Pokémon con etichetta "start"

            while (startCount < teamSize) {
                int pokeIndex = (int) (Math.random() * 55) + 1;
                Pokemon pokemon = CreateObjectsPokemon.getPokemon(pokeIndex);

                // Controlla se il Pokémon ha l'etichetta "start"
                if (pokemon.getStart() != null) {
                    npcTeam.add(pokemon);
                    startCount++;
                }
            }
        }else{
            for (int i = 0; i < teamSize; i++) {
                int pokeIndex = (int) (Math.random() * 55) + 1;
                Pokemon pokemon = CreateObjectsPokemon.getPokemon(pokeIndex);
                npcTeam.add(pokemon);
            }
        }

        Team team = new Team(npcTeam);
        return switch (index) {
            case 1 -> new Coach("Brock", 15, team, Gender.MALE);
            case 2 -> new Coach("Misty", 15, team, Gender.FEMALE);
            case 3 -> new Coach("Lt. Surge", 15, team, Gender.MALE);
            case 4 -> new Coach("Erika", 15, team, Gender.FEMALE);
            case 5 -> new Coach("Koga", 15, team, Gender.MALE);
            default -> null;
        };
    }
}
