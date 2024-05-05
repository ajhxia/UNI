import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Pokemon.Pokemon;
import Shared.PixelFont;

/*
 * Questa classe rappresenta un pannello contenente le informazioni di un pokemon
 * Il pannello permette di aggiungere il pokemon che si sta visualizzando alla propria squadra
 */

public class InfoPokemon extends JPanel {
    public InfoPokemon(Pokemon pokemon) throws IOException, URISyntaxException{
        ImageIcon imagePoke = loadImage(new URI(pokemon.getSprite().getFront()));
        JLabel imageLabel = new JLabel(imagePoke);

        JLabel namePoke = new JLabel("Name: " + pokemon.getName());
        namePoke.setFont(PixelFont.myCustomFont);
        JLabel hpPoke = new JLabel("HP: " + pokemon.getStats().getHp());
        hpPoke.setFont(PixelFont.myCustomFont);
        JLabel typePoke = new JLabel("Defense: " + pokemon.getStats().getDefense());
        typePoke.setFont(PixelFont.myCustomFont);
        JLabel levelPoke = new JLabel("Level: " + pokemon.getLvl());
        levelPoke.setFont(PixelFont.myCustomFont);
        JLabel abilityPoke = new JLabel("Ability: " + pokemon.getAbilities()[0].getName());
        abilityPoke.setFont(PixelFont.myCustomFont);

        // Set layout manager to display labels in a column
        setLayout(new GridLayout(0, 1));
        add(imageLabel);
        add(namePoke);
        add(hpPoke);
        add(typePoke);
        add(levelPoke);
        add(abilityPoke);
        add(namePoke);
    }
    
    private static ImageIcon loadImage(URI uri) throws IOException {
        BufferedImage img = ImageIO.read(uri.toURL());
        return new ImageIcon(img);
    }
}