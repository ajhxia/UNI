import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Pokemon.Pokemon;
import Shared.PixelFont;
import Shared.RelativePath;
import Shared.ResizeIcon;

/*
 * Questa classe rappresenta un pannello contenente le informazioni di un pokemon
 * Il pannello permette di aggiungere il pokemon che si sta visualizzando alla propria squadra
 */

public class InfoPokemon extends JPanel {
    public InfoPokemon(Pokemon pokemon) throws IOException, URISyntaxException {
        setLayout(null); // Imposta il layout a null per posizionare manualmente i componenti

        // Carica l'immagine del Pokémon
        ImageIcon imagePoke = loadImage(new URI(pokemon.getSprite().getFront()));
        int newWidth = 200;
        int newHeight = 200;
        JLabel imageLabel = new JLabel(ResizeIcon.resizeIcon(imagePoke, newWidth, newHeight));
        imageLabel.setBounds(40, 40, 200, 200); // Imposta la posizione e le dimensioni dell'immagine

        // Carica l'immagine di sfondo
        ImageIcon backgroundImage = new ImageIcon(RelativePath.getAbsolutePath("\\Image\\info.png"));
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());

        JLabel titleName = new JLabel("Name");
        titleName.setFont(PixelFont.myCustomFont);
        titleName.setBounds(290, 34, 200, 50);

        JLabel name = new JLabel(pokemon.getName());
        name.setFont(PixelFont.myCustomFont);
        name.setBounds(260, 73, 200, 50);

        String stats = "";
        for (int i = 0; i < pokemon.getTypes().length; i++) {
            if (i == 0) {
                stats += pokemon.getTypes()[i];
            } else {
                stats += ", " + pokemon.getTypes()[i];
            }
        }
        JLabel type = new JLabel(stats);
        type.setFont(PixelFont.myCustomFont.deriveFont(12f));
        type.setBounds(315, 160 , 200, 50);

        JLabel hp = new JLabel("HP: " + pokemon.getStats().getHp());
        hp.setFont(PixelFont.myCustomFont.deriveFont(12f));
        hp.setBounds(315, 193, 200, 50);
        
        JButton buttonAdd = new JButton("Add to team");
        buttonAdd.setBounds(60, 275, 200, 50);
        buttonAdd.setFont(PixelFont.myCustomFont);
        buttonAdd.setFocusPainted(false); // Rimuove l'effetto focus per migliorare l'aspetto
        buttonAdd.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia il cursore al passaggio del mouse
        buttonAdd.setMargin(new Insets(10, 20, 10, 20)); // Imposta il padding (top, left, bottom, right)
        buttonAdd.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(10, 20, 10, 20) // Imposta il padding all'interno del bordo
        ));
        buttonAdd.setContentAreaFilled(false); // Rimuove il colore di sfondo

        // Imposta le dimensioni del pannello alle dimensioni dell'immagine di sfondo
        setPreferredSize(new Dimension(backgroundImage.getIconWidth(), backgroundImage.getIconHeight()));

        // Aggiungi l'immagine sopra lo sfondo
        add(imageLabel);
        add(buttonAdd);
        add(titleName);
        add(hp);
        add(type);
        add(name);
        add(backgroundLabel);
    }

    private static ImageIcon loadImage(URI uri) throws IOException {
        BufferedImage img = ImageIO.read(uri.toURL());
        return new ImageIcon(img);
    }
}