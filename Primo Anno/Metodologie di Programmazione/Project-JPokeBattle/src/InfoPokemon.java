import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Pokemon.Pokemon;

public class InfoPokemon extends JPanel {
    private JLabel namePoke;
    private JLabel hpPoke;
    private ImageIcon imagePoke;

    public InfoPokemon(Pokemon pokemon){
        namePoke = new JLabel(pokemon.getName());
        hpPoke = new JLabel("HP: " + pokemon.getStats().getHp());
        try {
            imagePoke = loadImage(new URI(pokemon.getSprite().getFront()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        JLabel imageLabel = new JLabel(imagePoke);
        this.add(imageLabel);
        this.add(hpPoke);

        add(namePoke);
    }
    
    private static ImageIcon loadImage(URI uri) throws IOException {
        BufferedImage img = ImageIO.read(uri.toURL());
        return new ImageIcon(img);
    }
}