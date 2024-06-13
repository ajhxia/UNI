package Battle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import Game.Coach;
import Pokemon.CreateObjectsPokemon;
import Pokemon.Pokemon;
import Shared.ImageUtility;
import Shared.PixelFont;
import Shared.RelativePath;
import Shared.Style;

class EvolutionFrame extends JFrame {
    private JLabel currentPokemonLabel;
    private JLabel evolvedPokemonLabel;
    private JLabel currentPokemonNameLabel;
    private JLabel evolvedPokemonNameLabel;
    private JLabel evolutionMessageLabel;
    private JButton confirmButton;

    /**
     * Constructor of the class EvolutionFrame
     * @param indexPoke
     * @param pokemonModel
     * @throws IOException
     * @throws URISyntaxException
     */
    public EvolutionFrame(int indexPoke, PokemonModel pokemonModel) throws IOException, URISyntaxException {
        setTitle("Pokemon Evolution");
        setSize(650, 450); // Increased frame size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Set layout to null
        setIconImage(new ImageIcon(RelativePath.getAbsolutePath("/Image/active_pokeball.png")).getImage());
        Coach player = Battle.getPlayer();
        Pokemon pokemon = player.getTeam().getPokemon(indexPoke);
        int evolvedPokemon = pokemon.getEvolutions()[0];

        Pokemon poke = CreateObjectsPokemon.getPokemon(evolvedPokemon, pokemon.getLvl());
        poke.getStats().setHp(pokemon.getStats().getHp());

        ImageIcon currentPokemonIcon = ImageUtility.loadImage(new URI(pokemon.getSprite().getFront()));
        currentPokemonLabel = new JLabel(ImageUtility.resizeIcon(currentPokemonIcon, 250, 250)); // Increased image size
        currentPokemonLabel.setBounds(50, 50, 250, 250); // Set bounds manually

        currentPokemonNameLabel = new JLabel(pokemon.getName(), SwingConstants.CENTER);
        currentPokemonNameLabel.setFont(PixelFont.myCustomFont.deriveFont(20f)); // Increased font size
        currentPokemonNameLabel.setBounds(50, 300, 250, 30); // Set bounds manually

        ImageIcon evolvedPokemonIcon = ImageUtility.loadImage(new URI(poke.getSprite().getFront()));
        evolvedPokemonLabel = new JLabel(ImageUtility.resizeIcon(evolvedPokemonIcon, 250, 250)); // Increased image size
        evolvedPokemonLabel.setBounds(350, 50, 250, 250); // Set bounds manually

        evolvedPokemonNameLabel = new JLabel(poke.getName(), SwingConstants.CENTER);
        evolvedPokemonNameLabel.setFont(PixelFont.myCustomFont.deriveFont(20f)); // Increased font size
        evolvedPokemonNameLabel.setBounds(350, 300, 250, 30); // Set bounds manually

        evolutionMessageLabel = new JLabel("Would you like to evolve... ", SwingConstants.CENTER);
        evolutionMessageLabel.setFont(PixelFont.myCustomFont.deriveFont(20f)); // Increased font size
        evolutionMessageLabel.setBounds(50, 10, 550, 30); // Set bounds manually

        confirmButton = Style.createButton(Color.BLACK, "Confirm", 16, 265, 350, 120, 40); // Increased button size
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Battle.getPlayer().getTeam().removePokemon(indexPoke);
                System.out.println(poke.getStats().getHp());
                Battle.getPlayer().getTeam().addPokemonAtIndex(indexPoke, poke);
                pokemonModel.evolve(poke);
                dispose();
            }
        });

        add(currentPokemonLabel);
        add(currentPokemonNameLabel);
        add(evolvedPokemonLabel);
        add(evolvedPokemonNameLabel);
        add(evolutionMessageLabel);
        add(confirmButton);
    }
}