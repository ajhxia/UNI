package Battle;

import java.awt.Color;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.*;

import Game.Coach;
import Pokemon.Pokemon;
import Shared.ImageUtility;
import Shared.PixelFont;
import Shared.RelativePath;
import Shared.Style;

public class ChangePokemonFrame extends JFrame {
    /**
     * Costruttore della classe ChangePokemonFrame
     * @param battleFrame
     */
    public ChangePokemonFrame(BattleFrame battleFrame) {
        Coach player = Battle.getPlayer();
        Coach npc = Battle.getNpc();

        Battle battle = new Battle(battleFrame);

        setTitle("Change Pokemon");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(380, 745);
        setLocationRelativeTo(battleFrame);
        setLayout(null);
        setIconImage(new ImageIcon(RelativePath.getAbsolutePath("/Image/active_pokeball.png")).getImage());
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 368, 710);
        add(panel);

        JLabel label = new JLabel("Select a Pokémon to change");
        label.setFont(PixelFont.myCustomFont.deriveFont(11f));
        label.setBounds(43, 15, 300, 20);
        panel.add(label);
        
        ImageIcon backgroundImage = new ImageIcon(RelativePath.getAbsolutePath("Image/image.png"));
        JLabel backgroundLabel = new JLabel(ImageUtility.resizeIcon(backgroundImage, 368, 670));
        backgroundLabel.setBounds(0, 0, 368, 760);

        for (int i = 0; i < player.getTeam(). getTeam().size(); i++) {
            final int index = i;
            Pokemon pokemon = player.getTeam().getPokemon(index);
            JButton button = Style.createButton(Color.WHITE, pokemon.getName(), 14, 120, 83 + (index * 110), 200, 30);
            ImageIcon imagePoke;
            JLabel imageLabel;
            try {
                imagePoke = ImageUtility.loadImage(new URI(pokemon.getSprite().getFront()));
                imageLabel = new JLabel(ImageUtility.resizeIcon(imagePoke, 75, 75));
                imageLabel.setBounds(45, 63 + (index * 110), 75, 75); // Adjust the position accordingly
                panel.add(imageLabel);
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
            // Create JLabel for the image

            // Change button color if Pokémon is fainted
            if (pokemon.getStats().getHp() <= 0 || pokemon.equals(player.getPokemonInUse())) {
                button.setBackground(Color.RED);
                button.setEnabled(false);
            }

            button.addActionListener(e -> {
                if (pokemon.getStats().getHp() > 0) {
                    player.setPokemonInUse(pokemon);
                    dispose();

                    try {
                        battleFrame.updatePokemonDisplayPlayer(player, npc);
                    } catch (IOException | URISyntaxException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    battle.setTurn(false);
                    System.out.println(player.getPokemonInUse().getName());

                } else {
                    JOptionPane.showMessageDialog(null, "You can't change a fainted Pokémon", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            });
            panel.add(button);
            
        }
        panel.add(backgroundLabel); // Add the background to the main panel
        setVisible(true);
    }
}
