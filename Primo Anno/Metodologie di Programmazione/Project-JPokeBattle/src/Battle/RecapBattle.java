package Battle;

import java.awt.Color;
import java.awt.Component;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.*;

import Game.Coach;
import Game.Gender;
import Game.Npc;
import Game.Team;
import Pokemon.CreateObjectsPokemon;
import Pokemon.Pokemon;
import Shared.ImageUtility;
import Shared.PixelFont;
import Shared.RelativePath;
import Shared.Style;

@SuppressWarnings("deprecation")
public class RecapBattle extends JFrame implements Observer {
    private static JFrame frame;
    private static List<PokemonModel> pokemonModels;
    private static JButton newGameButton;

    public RecapBattle() throws IOException, URISyntaxException {
        Battle.addAbilityToPlayerPokemon();
        List<Pokemon> pokemonList = Battle.getPlayer().getTeam().getListPokemon();
        pokemonModels = new ArrayList<>();

        frame = new JFrame();
        frame.setTitle("Recap Battle");
        frame.setSize(600, 600);

        ImageIcon backgroundImage = new ImageIcon(RelativePath.getAbsolutePath("Image/backPlayer.png"));
        JLabel backgroundLabel = new JLabel(ImageUtility.resizeIcon(backgroundImage, 600, 600));
        backgroundLabel.setBounds(0, 0, 600, 600);

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
            yOffset += 75;
            i++;
        }

        newGameButton = Style.createButton(Color.WHITE, "New Game", 14, 200, yOffset + 20, 200, 30);
        newGameButton.setEnabled(false);
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
        checkAllButtonsDisabled();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Integer) {
            int index = (Integer) arg;
            PokemonModel updatedModel = (PokemonModel) o;
            refreshPanel(index, updatedModel);
            checkAllButtonsDisabled(); // Check if all buttons are disabled after update
        }
    }

    public static void refreshPanel(int index, PokemonModel updatedModel) {
        frame.getContentPane().removeAll();

        List<Pokemon> pokemonList = Battle.getPlayer().getTeam().getListPokemon();
        ImageIcon backgroundImage = new ImageIcon(RelativePath.getAbsolutePath("Image/backPlayer.png"));
        JLabel backgroundLabel = new JLabel(ImageUtility.resizeIcon(backgroundImage, 600, 600));
        backgroundLabel.setBounds(0, 0, 600, 600);
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
                    PokemonModel model = pokemonModels.get(i);
                    addPokemonInfo(model, yOffset, i);
                }
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
            yOffset += 60;
        }
        JButton newGameButton = Style.createButton(Color.WHITE, "New Game", 14, 175, yOffset + 100, 260, 30);
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
        checkAllButtonsDisabled(); // Check if all buttons are disabled after refresh
    }

    private static void addPokemonInfo(PokemonModel pokemonModel, int yOffset, int i)
            throws IOException, URISyntaxException {
        Pokemon pokemon = pokemonModel.getPokemon();

        ImageIcon imagePoke = ImageUtility.loadImage(new URI(pokemon.getSprite().getFront()));
        JLabel imageLabel = new JLabel(ImageUtility.resizeIcon(imagePoke, 60, 60));
        imageLabel.setBounds(30, yOffset, 60, 60);
        frame.add(imageLabel);

        JLabel nameLabel = new JLabel(pokemon.getName());
        nameLabel.setFont(PixelFont.myCustomFont.deriveFont(14f));
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setBounds(100, yOffset, 200, 30);
        frame.add(nameLabel);

        JProgressBar healthBar = new JProgressBar(0, pokemon.getStats().getMaxHp());
        healthBar.setValue(pokemon.getStats().getHp());
        healthBar.setForeground(Color.GREEN);
        healthBar.setBounds(100, yOffset + 30, 200, 15);
        frame.add(healthBar);
        JButton evolveButton = Style.createButton(Color.PINK, "Evolve", 12, 310, yOffset + 25, 100, 30);
        if (pokemon.getLvlEvoluzione() == pokemon.getLvl()) {
            evolveButton.addActionListener(e -> {
                try {
                    EvolutionFrame evolutionFrame = new EvolutionFrame(i, pokemonModel);
                    evolutionFrame.setVisible(true);
                    evolutionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    evolveButton.setEnabled(false);
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            });
        } else {
            evolveButton.setEnabled(false);
        }
        frame.add(evolveButton);

        JButton movesButton = Style.createButton(Color.WHITE, "Change", 12, 420, yOffset + 25, 150, 30);
        if (pokemon.getAbilities().size() > 4) {
            movesButton.addActionListener(e -> {
                AbilitySelection abilitySelection;
                try {
                    abilitySelection = new AbilitySelection(i);
                    abilitySelection.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    movesButton.setEnabled(false);
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            });
        } else {
            movesButton.setEnabled(false);
        }
        frame.add(movesButton);
    }

    private static void checkAllButtonsDisabled() {
        boolean allDisabled = true;
        for (Component comp : frame.getContentPane().getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                if (!button.getText().equals("New Game") && button.isEnabled()) {
                    allDisabled = false;
                    break;
                }
            }
        }
        newGameButton.setEnabled(allDisabled);
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

    public static void main(String[] args) {
        PixelFont.loadCustomFont();
        ArrayList<Pokemon> npcTeam = new ArrayList<Pokemon>(Arrays.asList(CreateObjectsPokemon.getPokemon(1, 13), CreateObjectsPokemon.getPokemon(5, 17), CreateObjectsPokemon.getPokemon(6, 17), CreateObjectsPokemon.getPokemon(12, 0), CreateObjectsPokemon.getPokemon(3, 4), CreateObjectsPokemon.getPokemon(3, 4)));
        Team team = new Team(npcTeam);
        Coach player = new Coach("Player", 1, team, Gender.MALE );
        Battle.setPlayer(player);
        Battle.setNpc(player);

        try {
            new RecapBattle();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}