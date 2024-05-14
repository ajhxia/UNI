import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.*;
import Game.Coach;
import Shared.ImageUtility;
import Shared.PixelFont;

public class InfoRecap extends JFrame {

    public InfoRecap(Coach player) {

        setTitle("Recap Team");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout()); // Utilizza BorderLayout per il pannello principale

        JPanel teamPanel = new JPanel();
        teamPanel.setLayout(new GridLayout(player.getTeam().getPlayerTeam().size(), 1));

        for (int i = 0; i < player.getTeam().getPlayerTeam().size(); i++) {
            JPanel pokemonPanel = new JPanel();
            pokemonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

            JLabel pokeLabel = new JLabel(player.getTeam().getPokemon(i).getName());
            pokeLabel.setFont(PixelFont.myCustomFont);

            ImageIcon imagePoke = null;
            try {
                imagePoke = ImageUtility.loadImage(new URI(player.getTeam().getPokemon(i).getSprite().getFront()));
                int newWidth = 100;
                int newHeight = 100;
                JLabel imageLabel = new JLabel(new ImageIcon(
                        imagePoke.getImage().getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH)));

                JButton changeButton = new JButton("Change");
                changeButton.setFont(PixelFont.myCustomFont);
                changeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                changeButton.setActionCommand(String.valueOf(i)); // Imposta il valore di i come ActionCommand

                changeButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int indexToRemove = Integer.parseInt(e.getActionCommand()); // Recupera il valore di i
                                                                           // dall'ActionCommand
                        player.getTeam().getPlayerTeam().remove(indexToRemove);
                        Pokedex pokedex = new Pokedex();
                        try {
                            Pokedex.updateTeamPanel();
                        } catch (IOException | URISyntaxException e1) {
                            e1.printStackTrace();
                        }         
                        pokedex.setVisible(true);
                        dispose();
                    }
                });

                changeButton.setBackground(Color.WHITE);
                changeButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                changeButton.setMargin(new Insets(10, 20, 10, 20));
                changeButton.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.BLACK),
                        BorderFactory.createEmptyBorder(10, 20, 10, 20)));
                changeButton.setFocusPainted(false);
                
                pokemonPanel.add(changeButton);
                pokemonPanel.add(imageLabel);
                pokemonPanel.add(pokeLabel);
                
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }

            teamPanel.add(pokemonPanel);
        }

        panel.add(teamPanel, BorderLayout.CENTER); // Aggiungi il teamPanel al centro del BorderLayout

        JButton confirmButton = new JButton("Start Battle");
        confirmButton.setFont(PixelFont.myCustomFont);
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Azione da eseguire al click del pulsante "Conferma"
                JOptionPane.showMessageDialog(null, "Conferma effettuata");
            }
        });

        confirmButton.setBackground(Color.WHITE);
        confirmButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        confirmButton.setMargin(new Insets(10, 20, 10, 20));
        confirmButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        confirmButton.setFocusPainted(false);

        // Aggiungi il pulsante "Conferma" al centro in basso
        panel.add(confirmButton, BorderLayout.PAGE_END);

        add(panel);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

}
