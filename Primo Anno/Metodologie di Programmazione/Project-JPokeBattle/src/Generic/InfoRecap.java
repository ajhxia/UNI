package Generic;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.*;

import Battle.BattleFrame;
import Battle.Battle;
import Game.*;
import Pokemon.Pokemon;
import Shared.ImageUtility;
import Shared.PixelFont;
import Shared.Style;

import java.util.List; // Import the List class from java.util

public class InfoRecap extends JFrame {

    public static BattleFrame battleFrame;

    public InfoRecap() throws IOException, URISyntaxException {
        Coach player = Battle.getPlayer();
        setTitle("Recap Team");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        List<Pokemon> pokemons = Battle.getPlayer().getTeam().getListPokemon(); // Change the declaration to use the generic type List<Pokemon>
        int yOffset = 60; // Offset per posizionare i componenti lungo l'asse Y
        int rowHeight = 100; // Altezza di ogni riga

        // Aggiunge i Pokémon dal primo all'ultimo
        for (int i = 0; i < pokemons.size(); i++) {
            Pokemon pokemon = pokemons.get(i);
            int yPosition = yOffset + i * rowHeight; // Posizione lungo l'asse Y per ogni Pokémon
            JButton changeButton = Style.createButton(Color.black, "Change", 12, 25, yPosition, 120, 30);
            changeButton.setActionCommand(String.valueOf(i));
            changeButton.addActionListener(e -> {
                int indexToRemove = Integer.parseInt(e.getActionCommand()); // Recupera il valore di i
                        // dall'ActionCommand
                        player.getTeam().getListPokemon().remove(indexToRemove);
                        Pokedex pokedex = new Pokedex();
                        try {
                            Pokedex.updateTeamPanel();
                        } catch (IOException | URISyntaxException e1) {
                            e1.printStackTrace();
                        }
                        pokedex.setVisible(true);
                        dispose();
            });
            panel.add(changeButton);

            JLabel nameLabel = new JLabel(pokemon.getName());
            nameLabel.setFont(PixelFont.myCustomFont);
            nameLabel.setBounds(170, yPosition - 5, 170, 30); // Imposta la posizione e le dimensioni
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(nameLabel);

            JLabel imageLabel = new JLabel(ImageUtility.loadImage(new URI(pokemon.getSprite().getFront())));
            // Imposta l'altezza dell'etichetta dell'immagine per allinearla con l'altezza
            // della riga
            imageLabel.setBounds(350, yPosition - 35, rowHeight, rowHeight); // Imposta la posizione e le dimensioni
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(imageLabel);

        }

        JButton confirmButton = Style.createButton(Color.black, "Start!!", 16, 115, 650, 200, 50);
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Battle.setNpc(Npc.createNpc(1, 3));
                    battleFrame = new BattleFrame();
                    dispose();
                } catch (IOException | URISyntaxException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        // Aggiungi il pulsante "Conferma" al centro in basso
        panel.add(confirmButton);

        add(panel);
        pack();
    }

}
