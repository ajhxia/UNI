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

    /**
     * Metodo per creare un npc
     * @param index
     * @param teamSize
      @return npc
     */
    public static Coach createNpc(int index, int teamSize) {
        ArrayList<Pokemon> npcTeam = new ArrayList<>();
        if (start == false) {
            int startCount = 0; // Contatore per i Pokémon con etichetta "start"

            while (startCount < teamSize) {
                int pokeIndex = (int) (Math.random() * 55) + 1;
                Pokemon pokemon = CreateObjectsPokemon.getPokemon(pokeIndex, 0);

                // Controlla se il Pokémon ha l'etichetta "start"
                if (pokemon.getStart() != null) {
                    npcTeam.add(pokemon);
                    startCount++;
                }
            }
            start = true;
        }else{
            for (int i = 0; i < teamSize; i++) {
                int pokeIndex = (int) (Math.random() * 55) + 1;
                Pokemon pokemon = CreateObjectsPokemon.getPokemon(pokeIndex, 0);
                npcTeam.add(pokemon);
            }
        }

        Team team = new Team(npcTeam);
        return switch (index) {
            case 1 -> new Coach("Brock", 15, team, Gender.MALE, 0);
            case 2 -> new Coach("Misty", 15, team, Gender.FEMALE, 0);
            case 3 -> new Coach("Lt. Surge", 15, team, Gender.MALE, 0);
            case 4 -> new Coach("Erika", 15, team, Gender.FEMALE, 0);
            case 5 -> new Coach("Koga", 15, team, Gender.MALE, 0);
            default -> null;
        };
    }
}
