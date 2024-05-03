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

/*
 * Questa classe rappresenta il pokedex del gioco
 * Il pokedex contiene le immagini dei Pokémon
 * Cliccando su un'immagine si aprirà una finestra con le informazioni del Pokémon
 */

public class Pokedex extends JPanel implements ActionListener {
    private static Pokemon[] pokemonArray = new Pokemon[42];
    private static ImageIcon[] pokemonImages = new ImageIcon[42]; // Array per 42 Pokémon
    
    public Pokedex() {
        // inizializzo il pannello
        this.setLayout(new GridLayout(0, 6)); // 6 colonne per la griglia

        // carico i Pokèmon in un array e le loro immagini
        loadPokemon();
        loadImagesPokemon(); // Carica le immagini dei Pokémon

        // aggiungo i bottoni con le immagini dei Pokémon 
        for (int i = 0; i < pokemonArray.length; i++) {
            JButton button = new JButton();
            button.setIcon(pokemonImages[i]);
            button.setActionCommand(String.valueOf(i + 1));  // setto l'indice del Pokémon come action command
            button.addActionListener(this); // aggiungo un ascoltatore per il click
            button.setBorder(null); // rimuovo il bordo
            button.setContentAreaFilled(false); // rimuovo il colore di sfondo
            button.setToolTipText("Pokemon " + (i + 1)); // aggiungo un tooltip con il nome del Pokémon
            this.add(button); 
        }
    }

    // Carica le immagini dei Pokémon in un array
    public static void loadImagesPokemon() {
        // il try-catch serve per gestire le eccezioni che possono essere lanciate durante il caricamento delle immagini
        // in particolare, il metodo loadImage lancia un'eccezione se non riesce a caricare l'immagine
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
    
    // carico i Pokémon in un array
    public static void loadPokemon() {
    for (int i = 0; i < 42; i++){
        pokemonArray[i] = CreateObjectsPokemon.getPokemon(i + 1);
    }
}
    // metodo privato che carica un'immagine da un URI e la restituisce come ImageIcon
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

    // Metodo chiamato quando si clicca su un bottone
    @Override
    public void actionPerformed(ActionEvent e) {
        int pokeIndex = Integer.parseInt(e.getActionCommand());
        try {
            showPokemonInfo(pokeIndex);
        } catch (IOException | URISyntaxException e1) {
            e1.printStackTrace();
        }
    }

    // Mostra le informazioni di un Pokémon in una finestra separata di tipo InfoPokemon
    private void showPokemonInfo(int pokeIndexIn) throws IOException, URISyntaxException {
        InfoPokemon infoPokemon = new InfoPokemon(pokemonArray[pokeIndexIn - 1]); 
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
