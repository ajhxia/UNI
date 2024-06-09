package Battle;

import java.awt.Color;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.*;

import Game.Coach;
import Game.Npc;
import Pokemon.Pokemon;
import Shared.ImageUtility;
import Shared.PixelFont;
import Shared.RelativePath;
import Shared.Style;

@SuppressWarnings("deprecation")
public class RecapBattle extends JFrame implements Observer {
    private static JFrame frame;
    private List<PokemonModel> pokemonModels;

    public RecapBattle() throws IOException, URISyntaxException {
        Battle.addAbilityToPlayerPokemon();
        List<Pokemon> pokemonList = Battle.getPlayer().getTeam().getListPokemon();
        pokemonModels = new ArrayList<>();

        frame = new JFrame();
        frame.setTitle("Recap Battle");
        frame.setSize(500, 600);

        ImageIcon backgroundImage = new ImageIcon(RelativePath.getAbsolutePath("Image/backPlayer.png"));
        JLabel backgroundLabel = new JLabel(ImageUtility.resizeIcon(backgroundImage, 850, 600));
        backgroundLabel.setBounds(0, 0, 500, 600);

        frame.setLayout(null);

        JLabel labelWon = new JLabel("You won!");
        labelWon.setFont(PixelFont.myCustomFont.deriveFont(18f));
        labelWon.setForeground(Color.BLACK);
        labelWon.setBounds(175, 0, 200, 50);
        frame.add(labelWon);

        int yOffset = 50;
        int i = 0;
        for (Pokemon pokemon : pokemonList) {
            PokemonModel pokemonModel = new PokemonModel(pokemon, i);
            pokemonModel.addObserver(this);
            pokemonModels.add(pokemonModel);
            addPokemonInfo(pokemonModel, yOffset, i);
            yOffset += 60;
            i++;
        }

        JButton newGameButton = Style.createButton(Color.WHITE, "New Game", 14, 100, yOffset + 20, 200, 30);
        newGameButton.addActionListener(e -> {
            try {
                startNewGame();
            } catch (IOException | URISyntaxException e1) {
                e1.printStackTrace();
            }
        });
        frame.add(newGameButton);

        frame.add(backgroundLabel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Integer) {
            int index = (Integer) arg;
            PokemonModel updatedModel = (PokemonModel) o;
            refreshPanel(index, updatedModel);
        }
    }

    public static void refreshPanel(int index, PokemonModel updatedModel) {
        frame.getContentPane().removeAll();

        List<Pokemon> pokemonList = Battle.getPlayer().getTeam().getListPokemon();
        ImageIcon backgroundImage = new ImageIcon(RelativePath.getAbsolutePath("Image/backPlayer.png"));
        JLabel backgroundLabel = new JLabel(ImageUtility.resizeIcon(backgroundImage, 850, 600));
        backgroundLabel.setBounds(0, 0, 500, 600);
        frame.setLayout(null);

        JLabel labelWon = new JLabel("You won!");
        labelWon.setFont(PixelFont.myCustomFont.deriveFont(18f));
        labelWon.setForeground(Color.BLACK);
        labelWon.setBounds(175, 0, 200, 50);
        frame.add(labelWon);

        int yOffset = 50;
        for (int i = 0; i < pokemonList.size(); i++) {
            try {
                if (i == index) {
                    addPokemonInfo(updatedModel, yOffset, i);
                } else {
                    PokemonModel model = new PokemonModel(pokemonList.get(i), i);
                    addPokemonInfo(model, yOffset, i);
                }
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
            yOffset += 60;
        }
        JButton newGameButton = Style.createButton(Color.WHITE, "New Game", 14, 100, yOffset + 20, 260, 30);
        newGameButton.addActionListener(e -> {
            try {
                startNewGame();
            } catch (IOException | URISyntaxException e1) {
                e1.printStackTrace();
            }
        });
        frame.add(newGameButton);
        frame.add(backgroundLabel);
        frame.repaint();
        frame.revalidate();
    }

    private static void addPokemonInfo(PokemonModel pokemonModel, int yOffset, int i) throws IOException, URISyntaxException {
        Pokemon pokemon = Battle.getPlayer().getTeam().getPokemon(i);

        ImageIcon imagePoke = ImageUtility.loadImage(new URI(pokemon.getSprite().getFront()));
        JLabel imageLabel = new JLabel(ImageUtility.resizeIcon(imagePoke, 80, 80));
        imageLabel.setBounds(10, yOffset, 50, 50);
        frame.add(imageLabel);

        JLabel nameLabel = new JLabel(pokemon.getName());
        nameLabel.setFont(PixelFont.myCustomFont.deriveFont(14f));
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setBounds(70, yOffset, 200, 30);
        frame.add(nameLabel);

        JProgressBar healthBar = new JProgressBar(0, pokemon.getStats().getMaxHp());
        healthBar.setValue(pokemon.getStats().getHp());
        healthBar.setForeground(Color.GREEN);
        healthBar.setBounds(70, yOffset + 30, 200, 15);
        frame.add(healthBar);

        if (pokemon.getLvlEvoluzione() == pokemon.getLvl()) {
            JButton evolveButton = Style.createButton(Color.WHITE, "Evolve", 12, 280, yOffset + 25, 100, 30);
            evolveButton.addActionListener(e -> {
                try {
                    EvolutionFrame evolutionFrame = new EvolutionFrame(i, pokemonModel);
                    evolutionFrame.setVisible(true);
                    evolutionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            });
            frame.add(evolveButton);
        }

        if (pokemon.getAbilities().size() > 4) {
            JButton movesButton = Style.createButton(Color.WHITE, "Change", 12, 390, yOffset + 25, 150, 30);
            movesButton.addActionListener(e -> {
                AbilitySelection abilitySelection;
                try {
                    abilitySelection = new AbilitySelection(i);
                    abilitySelection.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            });
            frame.add(movesButton);
        }
    }

    private static void startNewGame() throws IOException, URISyntaxException {
        int teamSizeNpcDefeated = Battle.getNpc().getTeam().getListPokemon().size();
        Random random = new Random();
        int randomNum = random.nextInt(5) + 1;
        Coach npc = Npc.createNpc(randomNum, teamSizeNpcDefeated + 1);
        Battle.setNpc(npc);
        new BattleFrame();
        frame.dispose();
    }
}
