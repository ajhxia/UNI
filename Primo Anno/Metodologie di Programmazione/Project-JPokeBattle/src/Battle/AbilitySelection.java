package Battle;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Game.Coach;
import Shared.PixelFont;

/* TODO: da finire, manca la logica del cambio di abilità */

public class AbilitySelection extends JFrame {

    JFrame frame;

    public AbilitySelection(Coach player) {

        JPanel abilityPanel = new JPanel(new GridLayout(2, 2, 10, 10)); // 2 righe, 2 colonne, gap di 10 pixel
        abilityPanel.setBounds(100, 400, 400, 100); // Posizione e dimensione del pannello
        abilityPanel.setOpaque(false); // Imposta lo sfondo trasparente

        frame = new JFrame("Ability Selection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(500, 500);
        
        for (int i = 0; i < player.getTeam().getPokemon(0).getAbilities().size(); i++) {
            JButton abilityButton = new JButton(player.getTeam().getPokemon(0).getAbilities().get(i).getName());
            abilityButton.setFont(PixelFont.myCustomFont);
            abilityButton.setForeground(Color.WHITE); // Imposta il colore del testo
            abilityButton.setOpaque(false); // Rende il bottone trasparente
            abilityButton.setContentAreaFilled(false); // Rende trasparente l'area di contenuto del bottone
            abilityButton.setBorder(BorderFactory.createLineBorder(Color.white, 2)); // Imposta il bordo
            abilityButton.setMargin(new Insets(10, 20, 10, 20)); // Imposta il padding (top, left, bottom, right)
            abilityButton.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.white, 2),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20) // Imposta il padding all'interno del bordo
            ));
            abilityButton.setFocusPainted(false); // Rimuove l'effetto focus per migliorare l'aspetto
            abilityButton.setBounds(70, 65, 200, 40); // Posiziona il bottone

            abilityButton.addActionListener(e -> {
                System.out.println("Ability selected: " + abilityButton.getText());
            }); 

            // Aggiungo un ascoltatore per l'effetto pointer
            abilityButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            abilityPanel.add(abilityButton);
        }

        frame.add(abilityPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}
