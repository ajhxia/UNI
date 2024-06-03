import javax.swing.*;

import Battle.Battle;
import Game.Coach;
import Pokemon.*;
import Shared.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Pokedex extends JPanel implements ActionListener {
    private static List<Pokemon> initialPokemonList = new ArrayList<>();
    private static List<ImageIcon> pokemonImages = new ArrayList<>();
    private static JFrame infoFrame;
    private static JFrame pokedexFrame;
    public static JLabel titleLabel;
    public static JPanel teamPanel; // Pannello per i Pokémon in squadra

    public Pokedex() {
        Coach player = Battle.getPlayer();
        pokedexFrame = new JFrame("Pokédex of " + player.getName());
        pokedexFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pokedexFrame.setSize(1120, 680);
        this.setLayout(null); // Utilizza un layout nullo per la personalizzazione

        // Carica l'immagine di sfondo
        ImageIcon backgroundImage = new ImageIcon(RelativePath.getAbsolutePath("/Image/pokedex-back.png"));

        // Crea un JLabel con l'immagine di sfondo e posiziona
        JLabel backgroundLabel = new JLabel(ImageUtility.resizeIcon(backgroundImage, 1100, 600));
        backgroundLabel.setBounds(0, 0, 1100, 600);

        // Creo un pannello per i bottoni dei Pokémon
        JPanel pokemonPanel = new JPanel(new GridLayout(5, 5));
        pokemonPanel.setBounds(420, 125, 600, 400); // Posiziona il pannello pokemonPanel
        pokemonPanel.setOpaque(false); // Rendi il pannello trasparente

        JLabel pokedexLabel = new JLabel("Your Pokédex");
        pokedexLabel.setFont(PixelFont.myCustomFont.deriveFont(16f));
        pokedexLabel.setBounds(620, 45, 350, 50);
        this.add(pokedexLabel);
    
        // Aggiungo i bottoni con le immagini dei Pokémon al pannello pokemonPanel
        for (int i = 0; i < initialPokemonList.size(); i++) {
            JButton button = new JButton();
            button.setIcon(ImageUtility.resizeIcon(pokemonImages.get(i), 100, 100));
            button.setActionCommand(String.valueOf(i + 1));
            button.addActionListener(this);
            button.setBorder(null);
            button.setContentAreaFilled(false);
            button.setToolTipText(initialPokemonList.get(i).getName());
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            button.setFocusPainted(false);
            pokemonPanel.add(button);
        }
    
        // Aggiungi il pannello pokemonPanel al layout nullo
        this.add(pokemonPanel);
    
        // Creo il pannello per i Pokémon in squadra
        teamPanel = new JPanel();
        teamPanel.setLayout(new BoxLayout(teamPanel, BoxLayout.Y_AXIS));
        teamPanel.setBounds(5, 10, 400, 560); // Posiziona il pannello della squadra
        teamPanel.setOpaque(false); // Rendi il pannello trasparente
        // Inizializza titleLabel
        titleLabel = new JLabel();
        titleLabel.setFont(PixelFont.myCustomFont);
        titleLabel.setBounds(370,585, 400, 50);

        this.add(teamPanel);
        this.add(titleLabel);

        updateTitle();
        // Aggiungo il bottone "Conferma" al layout nullo
        JButton confirm = Style.createButton(Color.BLACK, "Confirm", 14, 70, 600, 200, 30);
        confirm.setActionCommand("confirm");
        confirm.addActionListener(e -> {
            if (player.getTeam().getListPokemon().size() == 6) {
                InfoRecap infoRecap = new InfoRecap();
                infoRecap.setVisible(true);
                pokedexFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(pokedexFrame, "You must have 6 Pokémon in the team to continue.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        this.add(confirm);
        this.add(teamPanel);
        this.add(backgroundLabel); // Aggiungi lo sfondo al pannello principale
        pokedexFrame.add(this);
        pokedexFrame.setVisible(true);
        pokedexFrame.setLocationRelativeTo(null);
    }

    // Carica le immagini dei Pokémon in un array
    public static void loadImagesPokemon() {
        try {
            for (Pokemon pokemon : initialPokemonList) {
                String sprites = pokemon.getSprite().getFront();
                if (sprites != null) {
                    pokemonImages.add(ImageUtility.loadImage(new URI(sprites)));
                }
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    // Carica i Pokémon in un array e filtra solo i Pokémon iniziali
    public static void loadPokemon() {
        for (int i = 0; i < 55; i++) {
            Pokemon pokemon = CreateObjectsPokemon.getPokemon(i + 1);
            if (pokemon.getStart() != null) {
                initialPokemonList.add(pokemon);
            }
        }
        loadImagesPokemon();
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

    // Mostra le informazioni di un Pokémon in una finestra separata di tipo
    // InfoPokemon
    private void showPokemonInfo(int pokeIndexIn) throws IOException, URISyntaxException {
        if (infoFrame != null) {
            infoFrame.dispose(); // Chiudi il JFrame precedente se esiste
        }
        Pokemon pokemon = initialPokemonList.get(pokeIndexIn - 1);
        InfoPokemon infoPokemon = new InfoPokemon(pokemon, Battle.getPlayer());
        infoFrame = new JFrame(pokemon.getName() + " Info");
        infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        infoFrame.setSize(510, 400);
        infoFrame.setLocationRelativeTo(null); // Centra la finestra nello schermo
        infoFrame.add(infoPokemon);
        infoFrame.setVisible(true);
    }

    // Restituisce il Pokémon all'indice specificato
    public static Pokemon getPokemon(int index) {
        return initialPokemonList.get(index - 1);
    }

    public static void updateTitle() {
        // Ottieni il numero corrente di Pokémon nella squadra e aggiorna il testo del titolo
        int numPokemon = Battle.getPlayer().getTeam().getListPokemon().size();
        titleLabel.setText("Pokémon in Team: " + numPokemon);
    }
    
    public static void updateTeamPanel() throws IOException, URISyntaxException {
    teamPanel.removeAll(); // Rimuove tutti i componenti dal pannello dei Pokémon
    teamPanel.setLayout(null); // Imposta il layout a null
    List<Pokemon> pokemons = Battle.getPlayer().getTeam().getListPokemon();

    int rowHeight = 93; // Altezza della riga
    int yOffset = 60; // Offset per posizionare i componenti lungo l'asse Y

    // Aggiunge i Pokémon dal primo all'ultimo
    for (int i = 0; i < pokemons.size(); i++) {
        Pokemon pokemon = pokemons.get(i);
        int yPosition = yOffset + i * rowHeight; // Posizione lungo l'asse Y per ogni Pokémon
        JButton removeButton = new JButton("-");
        removeButton.setBounds(45, yPosition, 20, 20); // Imposta la posizione e le dimensioni
        removeButton.setActionCommand(String.valueOf(i));
        removeButton.addActionListener(e -> {
            int indexToRemove = Integer.parseInt(e.getActionCommand());
            Battle.getPlayer().getTeam().removePokemon(indexToRemove);
            try {
                updateTeamPanel();
                updateTitle();
            } catch (IOException | URISyntaxException e1) {
                e1.printStackTrace();
            }
        });
        removeButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(2, 2, 4, 2)));
        removeButton.setContentAreaFilled(false);
        removeButton.setFont(PixelFont.myCustomFont);
        removeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        teamPanel.add(removeButton);

        JLabel nameLabel = new JLabel(pokemon.getName());
        nameLabel.setFont(PixelFont.myCustomFont);
        nameLabel.setBounds(70, yPosition-5, 170, 30); // Imposta la posizione e le dimensioni
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        teamPanel.add(nameLabel);

        JLabel imageLabel = new JLabel(ImageUtility.loadImage(new URI(pokemon.getSprite().getFront())));
        // Imposta l'altezza dell'etichetta dell'immagine per allinearla con l'altezza della riga
        imageLabel.setBounds(220, yPosition-35, rowHeight, rowHeight); // Imposta la posizione e le dimensioni
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        teamPanel.add(imageLabel);

    }
    teamPanel.revalidate(); // Riesegue il layout
    teamPanel.repaint(); // Ridisegna il pannello
}

    
}
