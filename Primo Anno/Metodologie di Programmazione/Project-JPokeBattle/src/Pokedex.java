import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import Pokemon.CreateObjectsPokemon;

public class Pokedex extends JPanel {
    private HashMap<String, ImageIcon> pokemonImages;

    public Pokedex() {
        // Inizializzo il pannello
        this.setLayout(new GridLayout(0, 6)); // 6 colonne per la griglia

        // Inizializzo le immagini dei Pokémon
        loadPokemonImages();

        // Aggiungo le immagini dei Pokémon al pannello
        for (String pokemonName : pokemonImages.keySet()) {
            JLabel label = new JLabel(pokemonImages.get(pokemonName));
            this.add(label);
        }
    }

    private void loadPokemonImages() {
        pokemonImages = new HashMap<>();

        // Aggiungo le immagini dei Pokémon da URL online
        try {
            for (int i = 1; i <= 42; i++) {
                // ottengo il nome e l'URL dell'immagine del Pokémon
                String pokemonName = CreateObjectsPokemon.getPokemon(i).getName();
                String sprites = CreateObjectsPokemon.getPokemon(i).getSprite().getFront();
                pokemonImages.put(pokemonName, loadImage(new URI(sprites)));
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private ImageIcon loadImage(URI uri) throws IOException {
        BufferedImage img = ImageIO.read(uri.toURL());
        return new ImageIcon(img);
    }

    public static void main(String[] args) {
        // Creo e configuro la finestra
        JFrame frame = new JFrame("Pokedex");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);

        // Creo il pannello dei Pokémon
        Pokedex panel = new Pokedex();

        // Aggiungo il pannello alla finestra
        frame.add(panel);

        // Mostro la finestra
        frame.setVisible(true);
    }
}
