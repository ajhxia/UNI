package Shared;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Questa classe contiene un metodo per ridimensionare un'icona
 */

public class ImageUtility {
    public static ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }

    public static ImageIcon loadImage(URI uri) throws IOException {
        BufferedImage img = ImageIO.read(uri.toURL());
        return new ImageIcon(img);
    }
}
