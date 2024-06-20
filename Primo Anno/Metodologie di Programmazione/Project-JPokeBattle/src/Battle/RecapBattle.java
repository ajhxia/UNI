package Battle;

import java.awt.Color;
import java.awt.Component;
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
    private static List<PokemonModel> pokemonModels;
    private static JButton newGameButton;

    /**
     * Costruttore della classe RecapBattle
     * @throws IOException
     * @throws URISyntaxException
     */
    public RecapBattle() throws IOException, URISyntaxException {
        Battle.addAbilityToPlayerPokemon();
        List<Pokemon> pokemonList = Battle.getPlayer().getTeam(). getTeam();
        pokemonModels = new ArrayList<>();

        frame = new JFrame();
        frame.setTitle("Recap Battle");
        frame.setSize(610, 600);
        frame.setIconImage(new ImageIcon(RelativePath.getAbsolutePath("/Image/active_pokeball.png")).getImage());
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

        newGameButton = Style.createButton(Color.WHITE, "New Game", 14, 190, yOffset + 10, 200, 30);
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

    /**
     * Metodo per aggiungere le informazioni del pokemon
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Integer) {
            int index = (Integer) arg;
            PokemonModel updatedModel = (PokemonModel) o;
            refreshPanel(index, updatedModel);
            checkAllButtonsDisabled(); // Check if all buttons are disabled after update
        }
    }

    /**
     * Metodo per aggiornare il pannello
     * @param index
     * @param updatedModel
     */
    public static void refreshPanel(int index, PokemonModel updatedModel) {
        frame.getContentPane().removeAll();
        
        List<Pokemon> pokemonList = Battle.getPlayer().getTeam(). getTeam();
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
        JButton newGameButton = Style.createButton(Color.WHITE, "New Game", 14, 170, yOffset + 100, 240, 30);
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
    
    /**
     * Metodo per aggiungere le informazioni del pokemon
     * @param pokemonModel
     * @param yOffset
     * @param i
     * @throws IOException
     * @throws URISyntaxException
     */
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
        JButton evolveButton = Style.createButton(Color.WHITE, "Evolve", 12, 310, yOffset + 25, 100, 30);
        if (pokemon.getLvlEvoluzione() == pokemon.getLvl()) {
            evolveButton.addActionListener(e -> {
                try {
                    EvolutionFrame evolutionFrame = new EvolutionFrame(i, pokemonModel);
                    evolutionFrame.setVisible(true);
                    evolutionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    evolveButton.setEnabled(false);
                    pokemonModel.disableEvolveButton(); // Notify observers that the evolve button is disabled
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            });
        } else {
            evolveButton.setEnabled(false);
        }
        frame.add(evolveButton);

        JButton movesButton = Style.createButton(Color.WHITE, "New Move!", 12, 420, yOffset + 25, 150, 30);
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

    /**
     * Metodo per controllare se tutti i pulsanti sono disabilitati
     */
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

    /**
     * Metodo per iniziare una nuova partita
     * @throws IOException
     * @throws URISyntaxException
     */
    private static void startNewGame() throws IOException, URISyntaxException {
        int teamSizeNpcDefeated = Battle.getNpc().getTeam(). getTeam().size();
        Random random = new Random();
        int randomNum = random.nextInt(5) + 1;
        Coach npc = Npc.createNpc(randomNum, teamSizeNpcDefeated + 1);
        Battle.setNpc(npc);
        new BattleFrame();
        frame.dispose();
    }

}