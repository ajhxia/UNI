package Battle;

import java.awt.Color;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.*;

import Game.Coach;
import Pokemon.Pokemon;
import Shared.PixelFont;
import Shared.Style;

public class ChangePokemonFrame extends JFrame {
    public ChangePokemonFrame(BattleFrame battleFrame) {
        Coach player = Battle.getPlayer();
        Coach npc = Battle.getNpc();

        setTitle("Change Pokemon");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(battleFrame);
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 400, 350);
        add(panel);

        JLabel label = new JLabel("Select a Pokémon to change");
        label.setFont(PixelFont.myCustomFont.deriveFont(11f));
        label.setBounds(50, 10, 300, 30);
        panel.add(label);

        for (int i = 0; i < player.getTeam().getListPokemon().size(); i++) {
            final int index = i;
            Pokemon pokemon = player.getTeam().getPokemon(index);
            JButton button = Style.createButton(Color.BLACK, pokemon.getName(), 14, 100, 60 + (index * 40), 200, 30);

            // Cambia il colore del bottone se la vita del Pokémon è inferiore
            if (pokemon.getStats().getHp() <= 0) {
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
                    Battle.setTurn(false);
                    System.out.println(player.getPokemonInUse().getName());

                } else {
                    JOptionPane.showMessageDialog(null, "You can't change a fainted Pokémon", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            });
            panel.add(button);
        }

        setVisible(true);
    }
}
