package Battle;

import java.awt.Color;
import java.util.List;

import javax.swing.*;

import Pokemon.Pokemon;
import Shared.ImageUtility;
import Shared.PixelFont;
import Shared.RelativePath;


public class RecapBattle extends JFrame {
    private JFrame frame;

    public RecapBattle() {
        List<Pokemon> pokemonList = Battle.getPlayer().getTeam().getListPokemon();

        frame = new JFrame();
        frame.setTitle("Recap Battle");
        frame.setSize(500, 500);

        // Carica l'immagine di sfondo
        ImageIcon backgroundImage = new ImageIcon(RelativePath.getAbsolutePath("Image/backPlayer.png"));

        // Crea un JLabel con l'immagine di sfondo
        JLabel backgroundLabel = new JLabel(ImageUtility.resizeIcon(backgroundImage, 850, 500));
        backgroundLabel.setBounds(0, 0, 500, 500);
        
        frame.setLayout(null);

        JLabel labelWon = new JLabel("You won!");
        labelWon.setFont(PixelFont.myCustomFont.deriveFont(18f));
        labelWon.setForeground(Color.BLACK);
        labelWon.setBounds(175, 0, 200, 50);
        frame.add(labelWon);

        int yOffset = 50;
        for (Pokemon pokemon : pokemonList) {
            addPokemonInfo(pokemon, yOffset);
            yOffset += 60; // Adjust spacing as needed
        }

        frame.add(backgroundLabel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    private void addPokemonInfo(Pokemon pokemon, int yOffset) {
        JLabel nameLabel = new JLabel(pokemon.getName());
        nameLabel.setFont(PixelFont.myCustomFont.deriveFont(14f));
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setBounds(10, yOffset, 200, 30);
        frame.add(nameLabel);

        JProgressBar healthBar = new JProgressBar(0, pokemon.getStats().getMaxHp());
        healthBar.setValue(pokemon.getStats().getHp());
        healthBar.setStringPainted(true);
        healthBar.setBounds(10, yOffset + 30, 200, 15);
        frame.add(healthBar);

        if (pokemon.getLvlEvoluzione() == pokemon.getLvl()) {
            JButton evolveButton = new JButton("Evolve " + pokemon.getName() +  " to " + pokemon.getEvolutions()[0].getName() + "!");
            evolveButton.setBounds(220, yOffset + 30, 100, 30);
            evolveButton.addActionListener(e -> {
                // TODO
            });
            frame.add(evolveButton);
        }

        if (pokemon.getAbilities().size() == 4) {
            JButton movesButton = new JButton("Show Moves");
            movesButton.setBounds(330, yOffset + 30, 150, 30);
            movesButton.addActionListener(e -> {
                // TODO
            });
            frame.add(movesButton);
        }
    }
        public static void main(String[] args) {
                PixelFont.loadCustomFont();
                new RecapBattle();
            }
}