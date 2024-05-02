import javax.imageio.ImageIO;
import javax.swing.*;

import Pokemon.CreateObjectsPokemon;
import Pokemon.Pokemon;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Pokedex extends JPanel implements ActionListener {
    private static Pokemon[] pokemonArray = new Pokemon[42];
    private static ImageIcon[] pokemonImages = new ImageIcon[42]; // Array per 42 Pokémon
    
    public Pokedex() {
        // Inizializzo il pannello
        this.setLayout(new GridLayout(0, 6)); // 6 colonne per la griglia

        // Inizializzo le immagini dei Pokémon
        loadPokemon();
        loadImagesPokemon(); // Carica le immagini dei Pokémon

        // Aggiungo le immagini dei Pokémon al pannello
        for (int i = 0; i < pokemonArray.length; i++) {
            JButton button = new JButton();
            button.setIcon(pokemonImages[i]);
            
            button.setActionCommand(String.valueOf(i + 1)); 
            button.addActionListener(this);
            button.setBorder(null);
            button.setContentAreaFilled(false);
            button.setToolTipText("Pokemon " + (i + 1)); 
            this.add(button);
        }
    }

    public static void loadImagesPokemon() {
        try {
            for (int i = 0; i < 42; i++) {
                String sprites = pokemonArray[i].getSprite().getFront();
                if (sprites != null) {
                    pokemonImages[i] = loadImage(new URI(sprites));
                }
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
    

    public static void loadPokemon() {
    for (int i = 0; i < 42; i++){
        pokemonArray[i] = CreateObjectsPokemon.getPokemon(i + 1);
    }
}

    private static ImageIcon loadImage(URI uri) throws IOException {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        int pokeIndex = Integer.parseInt(e.getActionCommand());
        showPokemonInfo(pokeIndex);
    }

    private void showPokemonInfo(int pokeIndexIn) {
        Pokemon pokemon = CreateObjectsPokemon.getPokemon(pokeIndexIn);
        InfoPokemon infoPokemon = new InfoPokemon(pokemon); 
        JFrame infoFrame = new JFrame("Info Pokemon");
        infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        infoFrame.setSize(400, 300);
        infoFrame.add(infoPokemon);
        infoFrame.setVisible(true);
    }

    public Pokemon[] getPokemonArray() {
        return pokemonArray;
    }
}
