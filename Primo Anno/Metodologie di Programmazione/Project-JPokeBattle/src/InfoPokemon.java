import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Pokemon.Pokemon;

/*
 * Questa classe rappresenta un pannello contenente le informazioni di un pokemon
 * Il pannello permette di aggiungere il pokemon che si sta visualizzando alla propria squadra
 */

public class InfoPokemon extends JPanel {
    private JLabel namePoke;
    private JLabel hpPoke;
    private ImageIcon imagePoke;

    public InfoPokemon(Pokemon pokemon) throws IOException, URISyntaxException{
        namePoke = new JLabel(pokemon.getName());
        hpPoke = new JLabel("HP: " + pokemon.getStats().getHp());
        imagePoke = loadImage(new URI(pokemon.getSprite().getFront()));
        JLabel imageLabel = new JLabel(imagePoke);
        JLabel typePoke = new JLabel("Defense: " + pokemon.getStats().getDefense());
        JLabel levelPoke = new JLabel("Level: " + pokemon.getLvl());
        JLabel abilityPoke = new JLabel("Ability: " + pokemon.getAbilities()[0].getName());

        this.add(typePoke);
        this.add(levelPoke);
        this.add(abilityPoke);
        this.add(imageLabel);
        this.add(hpPoke);

        add(namePoke);
    }
    
    private static ImageIcon loadImage(URI uri) throws IOException {
        BufferedImage img = ImageIO.read(uri.toURL());
        return new ImageIcon(img);
    }
}