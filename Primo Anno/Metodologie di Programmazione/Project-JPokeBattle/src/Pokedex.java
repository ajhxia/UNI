import javax.swing.*;
import javax.swing.border.Border;

import Game.Coach;
import Pokemon.CreateObjectsPokemon;
import Pokemon.Pokemon;
import Shared.ImageUtility;
import Shared.PixelFont;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Pokedex extends JPanel implements ActionListener {
    private static List<Pokemon> initialPokemonList = new ArrayList<>();
    private static List<ImageIcon> pokemonImages = new ArrayList<>();
    private static JFrame infoFrame;
    private static JFrame pokedexFrame;
    public static JLabel titleLabel;
    public static JPanel teamPanel; // Pannello per i Pokémon in squadra
    private static Coach player;

    public Pokedex(Coach playerIn) {
        player = playerIn;
        pokedexFrame = new JFrame("Pokédex of " + player.getName());
        pokedexFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pokedexFrame.setSize(1100, 800); // Ampliato il pokedexFrame per accomodare il nuovo pannello
    
        // Utilizzo BorderLayout per il pannello principale
        this.setLayout(new BorderLayout());
    
        // Creo un pannello per i bottoni dei Pokémon
        JPanel pokemonPanel = new JPanel(new GridLayout(4, 4));
    
        // Aggiungo i bottoni con le immagini dei Pokémon al pannello pokemonPanel
        for (int i = 0; i < initialPokemonList.size(); i++) {
            JButton button = new JButton();
            button.setIcon(ImageUtility.resizeIcon(pokemonImages.get(i), 150, 150));
            button.setActionCommand(String.valueOf(i + 1));
            button.addActionListener(this);
            button.setBorder(null);
            button.setContentAreaFilled(false);
            button.setToolTipText(initialPokemonList.get(i).getName());
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            button.setFocusPainted(false);
            pokemonPanel.add(button);
        }
    
        // Aggiungo il pannello pokemonPanel nella parte centrale del layout
        this.add(pokemonPanel, BorderLayout.CENTER);
    
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setPreferredSize(new Dimension(1100, 50)); // Ampliato il pannello superiore per accomodare il nuovo
    
        // Aggiungo il bottone "Conferma" al pannello
        JButton confirm = new JButton("Confirm Team");
        confirm.setActionCommand("confirm");
        confirm.addActionListener(e -> {
            if (player.getTeam().getListPokemon().size() == 6) {
                InfoRecap infoRecap = new InfoRecap(player);
                infoRecap.setVisible(true);
                pokedexFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(pokedexFrame, "You must have 6 Pokémon in the team to continue.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        confirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
        confirm.setFont(PixelFont.myCustomFont);
        confirm.setMargin(new Insets(10, 20, 10, 20));
        confirm.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        confirm.setContentAreaFilled(false);
        topPanel.add(confirm);
    
        // Aggiungo il pannello dei titoli e del bottone "Conferma" al frame
        pokedexFrame.add(topPanel, BorderLayout.NORTH);
    
        // Creo il pannello per i Pokémon in squadra
        teamPanel = new JPanel();
        int spessoreBordo = 2; // Puoi regolare questo valore secondo le tue preferenze
        Border bordoPersonalizzato = BorderFactory.createLineBorder(Color.RED, spessoreBordo);
        
        teamPanel.setLayout(new BoxLayout(teamPanel, BoxLayout.Y_AXIS));
        teamPanel.setBorder(bordoPersonalizzato);
        teamPanel.setPreferredSize(new Dimension(350, 0)); // Imposta solo la larghezza specificata
    
        // Inizializza titleLabel
        titleLabel = new JLabel();
        titleLabel.setFont(PixelFont.myCustomFont);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Add top margin
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        teamPanel.add(titleLabel); // Aggiunge il titolo al pannello principale
        teamPanel.add(Box.createVerticalStrut(5)); // Spazio tra il titolo e i Pokémon
    
        this.add(teamPanel, BorderLayout.EAST);
    
        updateTitle();
    
        // Aggiungo il pokedexFrame al pannello
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
        for (int i = 0; i < 42; i++) {
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
        InfoPokemon infoPokemon = new InfoPokemon(pokemon, player);
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
        int numPokemon = player.getTeam().getListPokemon().size();
        titleLabel.setText("Pokémon in Team: " + numPokemon);
    }
    
    public static void updateTeamPanel() throws IOException, URISyntaxException {
        teamPanel.removeAll(); // Rimuove tutti i componenti dal pannello dei Pokémon
        teamPanel.add(titleLabel); // Aggiunge il titolo al pannello principale
        teamPanel.add(Box.createVerticalStrut(5)); // Spazio tra il titolo e i Pokémon
    
        int i = 0;
        for (Pokemon pokemon : player.getTeam().getListPokemon()) {
    
            JPanel pokemonInfoPanel = new JPanel();
            pokemonInfoPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // orizzontalmente
    
            JLabel nameLabel = new JLabel(pokemon.getName());
            nameLabel.setFont(PixelFont.myCustomFont);
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
            JLabel imageLabel = new JLabel(ImageUtility.loadImage(new URI(pokemon.getSprite().getFront())));
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
            JButton removeButton = new JButton("-");
            removeButton.setActionCommand(String.valueOf(i));
            removeButton.addActionListener(e -> {
                int indexToRemove = Integer.parseInt(e.getActionCommand());
                player.getTeam().removePokemon(indexToRemove);
                try {
                    updateTeamPanel();
                    updateTitle();
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            });
            removeButton.setMargin(new Insets(5, 10, 7, 10));
            removeButton.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.BLACK),
                    BorderFactory.createEmptyBorder(5, 10, 7, 10)));
            removeButton.setContentAreaFilled(false);
    
            removeButton.setFont(PixelFont.myCustomFont);
            removeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            pokemonInfoPanel.add(removeButton); // Aggiunge il bottone "Remove" al pannello dei Pokémon
            pokemonInfoPanel.add(imageLabel); // Aggiunge l'immagine al pannello dei Pokémon
            pokemonInfoPanel.add(nameLabel); // Aggiunge il nome al pannello dei Pokémon
            i++;
    
            teamPanel.add(pokemonInfoPanel); // Aggiunge il pannello con nome e immagine al pannello principale
            teamPanel.add(Box.createVerticalStrut(5)); // Spazio tra i Pokémon
        }
    
        teamPanel.revalidate(); // Riesegue il layout
        teamPanel.repaint(); // Ridisegna il pannello
    }
}
