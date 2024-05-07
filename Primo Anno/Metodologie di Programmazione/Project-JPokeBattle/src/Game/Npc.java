package Game;

import java.util.ArrayList;

/*
 * Npc class 
 * Create npc con un random team di 6 pokemon
 */

import Pokemon.CreateObjectsPokemon;
import Pokemon.Pokemon;

public class Npc {
    private static ArrayList<Pokemon> playerTeam;
    private static int teamMaxSize;

    public static Coach createNpc(int index) {
        playerTeam = new ArrayList<>();
        teamMaxSize = 6; // Imposta una dimensione massima predefinita, ma può essere modificata
        for (int i = 0; i < teamMaxSize; i++) {
            int pokeIndex = (int) (Math.random() * 42) + 1;
            playerTeam.add(CreateObjectsPokemon.getPokemon(pokeIndex));
        }
        Coach npc;
        switch (index) {
            case 1:
                npc = new Coach("Brock", 15, new Team(playerTeam), "M");
            case 2:
                return new Coach("Misty", 15, new Team(playerTeam), "F");
            case 3:
                return new Coach("Lt. Surge", 15, new Team(playerTeam), "M");
            case 4:
                return new Coach("Erika", 15, new Team(playerTeam), "F");
            case 5:
                return new Coach("Koga", 15, new Team(playerTeam), "M");
            default:
                return null;
        }
    }
}
