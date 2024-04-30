import Pokemon.CreateObjectsPokemon;
import Pokemon.Pokemon;

import java.util.Random;

public class MainGame {
    public static void main(String[] args) {
        Pokemon[] pokemon = new Pokemon[4] ;
        for (int i = 0; i < 4; i++) {
            Random random = new Random();
            int randomNumber = random.nextInt(42);
            System.out.println("Random number: " + randomNumber);
            pokemon[i] = CreateObjectsPokemon.getPokemon(randomNumber);
        }
    }
}
