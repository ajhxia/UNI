package Shared;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Questa classe contiene un metodo per ridimensionare un'icona
 */

public class ResizeIcon {
    public static ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
}
