package Battle;
import java.awt.Color;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.*;

import Game.Coach;
import Shared.PixelFont;

public class ChangePokemonFrame extends JFrame {
    public ChangePokemonFrame(Coach player, BattleFrame battleFrame, Coach npc) {
        setTitle("Change Pokemon");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(battleFrame);
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 400, 400);
        add(panel);

        JLabel label = new JLabel("Select a Pokémon to change");
        label.setFont(PixelFont.myCustomFont.deriveFont(11f));
        label.setBounds(50, 10, 300, 30);
        panel.add(label);

        for (int i = 0; i < player.getTeam().getPlayerTeam().size(); i++) {
            final int index = i;
            JButton button = new JButton(player.getTeam().getPokemon(index).getName());
            button.setBounds(100, 50 + (index * 40), 200, 30);
            button.setFont(PixelFont.myCustomFont);
            button.setForeground(Color.BLACK);
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            button.setMargin(new Insets(10, 20, 10, 20));
            button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.BLACK, 2),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20)
            ));
            button.setFocusPainted(false);
            button.addActionListener(e -> {
                BattleLogic.currentPokemonPlayer = player.getTeam().getPokemon(index);
                dispose();
                try {
                    battleFrame.updatePokemonDisplayPlayer(player, npc);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            });
            panel.add(button);
        }

        setVisible(true);
    }
}

