package Game;

import java.util.ArrayList;

/*
 * Npc class 
 * Create npc con un random team di 6 pokemon
 */

import Pokemon.CreateObjectsPokemon;
import Pokemon.Pokemon;

public class Npc {
    private static ArrayList<Pokemon> npcTeam;
    private static int teamMaxSize;

    public static Coach createNpc(int index) {
        npcTeam = new ArrayList<>();
        teamMaxSize = 6; // Imposta una dimensione massima predefinita, ma può essere modificata
        for (int i = 0; i < teamMaxSize; i++) {
            int pokeIndex = (int) (Math.random() * 42) + 1;
            npcTeam.add(CreateObjectsPokemon.getPokemon(pokeIndex));
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
