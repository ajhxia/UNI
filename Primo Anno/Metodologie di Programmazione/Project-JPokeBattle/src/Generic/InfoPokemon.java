package Generic;
import java.awt.*;
import java.io.IOException;
import java.net.*;
import javax.swing.*;
import Game.*;
import Pokemon.Pokemon;
import Shared.*;
import Shared.PixelFont;

import javax.swing.JOptionPane;

/*
 * Questa classe rappresenta un pannello contenente le informazioni di un pokemon
 * Il pannello permette di aggiungere il pokemon che si sta visualizzando alla propria squadra
 */

public class InfoPokemon extends JPanel {

    public InfoPokemon(Pokemon pokemon, Coach player) throws IOException, URISyntaxException {
        setLayout(null); // Imposta il layout a null per posizionare manualmente i componenti

        // Carica l'immagine del Pokémon+
        ImageIcon imagePoke = ImageUtility.loadImage(new URI(pokemon.getSprite().getFront()));
        int newWidth = 200;
        int newHeight = 200;
        JLabel imageLabel = new JLabel(ImageUtility.resizeIcon(imagePoke, newWidth, newHeight));
        imageLabel.setBounds(40, 40, 200, 200); // Imposta la posizione e le dimensioni dell'immagine

        // Carica l'immagine di sfondo
        ImageIcon backgroundImage = new ImageIcon(RelativePath.getAbsolutePath("/Image/info.png"));
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());

        // label per il titolo "Nome" del pokemon
        JLabel titleName = new JLabel("Name");
        titleName.setFont(PixelFont.myCustomFont);
        titleName.setBounds(290, 34, 200, 50);

        // label per il nome del pokemon
        JLabel name = new JLabel(pokemon.getName());
        name.setFont(PixelFont.myCustomFont);
        name.setBounds(260, 73, 200, 50);

        JLabel lvl = new JLabel("Level: " + pokemon.getLvl());
        lvl.setFont(PixelFont.myCustomFont.deriveFont(12f));
        lvl.setBounds(315, 250, 200, 50);


        String stats = "";
        for (int i = 0; i < pokemon.getTypes().length; i++) {
            if (i == 0) {
                stats += pokemon.getTypes()[i];
            } else {
                stats += ", " + pokemon.getTypes()[i];
            }
        }
        // label per il tipo del pokemon
        JLabel type = new JLabel(stats);
        type.setFont(PixelFont.myCustomFont.deriveFont(12f));
        type.setBounds(315, 160, 200, 50);

        // label per gli hp del pokemon
        JLabel hp = new JLabel("HP: " + pokemon.getStats().getHp());
        hp.setFont(PixelFont.myCustomFont.deriveFont(12f));
        hp.setBounds(315, 193, 200, 50);

        JLabel atk = new JLabel("Attack: " + pokemon.getStats().getAttack());
        atk.setFont(PixelFont.myCustomFont.deriveFont(12f));
        atk.setBounds(315, 270, 200, 50);

        JLabel def = new JLabel("Defense: " + pokemon.getStats().getDefense());
        def.setFont(PixelFont.myCustomFont.deriveFont(12f));
        def.setBounds(315, 290, 200, 50);

        JLabel spAtk = new JLabel("Sp. Atk: " + pokemon.getStats().getSpeed());
        spAtk.setFont(PixelFont.myCustomFont.deriveFont(12f));
        spAtk.setBounds(315, 310, 200, 50);

        JButton buttonAdd = Style.createButton(Color.BLACK, "Add to team", 12, 70, 277, 200, 50);
        buttonAdd.setActionCommand("add");
        buttonAdd.addActionListener(e -> {
            Team team = player.getTeam();
            if (team.getListPokemon().size() < 6) {
                if (team.getListPokemon().contains(pokemon)) {
                    JOptionPane.showMessageDialog(null, "Pokemon already in team.");
                }else{
                    team.addPokemon(pokemon);
                    player.setTeam(team);
                    Pokedex.updateTitle();
                    System.out.println("Pokemon added to team.");
                    try {
                        Pokedex.updateTeamPanel();
                    } catch (IOException | URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                    SwingUtilities.getWindowAncestor(this).dispose();
                }  
            } else{
                JOptionPane.showMessageDialog(null, "You can't have more than 6 Pokémon in your team.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Imposta le dimensioni del pannello alle dimensioni dell'immagine di sfondo
        setPreferredSize(new Dimension(backgroundImage.getIconWidth(), backgroundImage.getIconHeight()));
        
        // Aggiungi l'immagine sopra lo sfondo
        add(imageLabel);
        add(atk);
        add(def);
        add(spAtk);
        add(lvl);
        add(buttonAdd);
        add(titleName);
        add(hp);
        add(type);
        add(name);
        add(backgroundLabel);
        }

}