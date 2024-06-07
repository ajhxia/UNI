package Battle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import Game.Coach;
import Pokemon.Pokemon;
import Shared.ImageUtility;
import Shared.PixelFont;
import Shared.Style;

public class EvolutionFrame extends JFrame {
    private JLabel currentPokemonLabel;
    private JLabel evolvedPokemonLabel;
    private JLabel currentPokemonNameLabel;
    private JLabel evolvedPokemonNameLabel;
    private JLabel evolutionMessageLabel;
    private JButton confirmButton;
    private PokemonModel pokemonModel;

    public EvolutionFrame(int indexPoke, PokemonModel pokemonModel) throws IOException, URISyntaxException {
        this.pokemonModel = pokemonModel;
        setTitle("Pokemon Evolution");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Coach player = Battle.getPlayer();
        Pokemon pokemon = player.getTeam().getPokemon(indexPoke);
        Pokemon evolvedPokemon = pokemon.getEvolutions()[0];

        ImageIcon currentPokemonIcon = ImageUtility.loadImage(new URI(pokemon.getSprite().getFront()));
        currentPokemonLabel = new JLabel(ImageUtility.resizeIcon(currentPokemonIcon, 200, 200));
        currentPokemonNameLabel = new JLabel(pokemon.getName(), SwingConstants.CENTER);
        currentPokemonNameLabel.setFont(PixelFont.myCustomFont.deriveFont(16f));

        ImageIcon evolvedPokemonIcon = ImageUtility.loadImage(new URI(evolvedPokemon.getSprite().getFront()));
        evolvedPokemonLabel = new JLabel(ImageUtility.resizeIcon(evolvedPokemonIcon, 200, 200));
        evolvedPokemonNameLabel = new JLabel(evolvedPokemon.getName(), SwingConstants.CENTER);
        evolvedPokemonNameLabel.setFont(PixelFont.myCustomFont.deriveFont(16f));

        evolutionMessageLabel = new JLabel("Vuoi evolvere " + pokemon.getName() + " in " + evolvedPokemon.getName() + "?", SwingConstants.CENTER);
        evolutionMessageLabel.setFont(PixelFont.myCustomFont.deriveFont(16f));
        
        confirmButton = Style.createButton(Color.BLACK, "Confirm", 12, 150, 10, 100, 30);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pokemonModel.evolve(evolvedPokemon);
                dispose();
            }
        });

        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new GridLayout(2, 2));
        imagePanel.add(currentPokemonLabel);
        imagePanel.add(evolvedPokemonLabel);
        imagePanel.add(currentPokemonNameLabel);
        imagePanel.add(evolvedPokemonNameLabel);

        add(evolutionMessageLabel, BorderLayout.NORTH);
        add(imagePanel, BorderLayout.CENTER);
        add(confirmButton, BorderLayout.SOUTH);
    }
}

