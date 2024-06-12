package Shared;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

/*
 * Classe per caricare un font personalizzato
 */

public class PixelFont {
    public static Font myCustomFont;

    /**
     * Carica un font personalizzato dal file
     *  
     */
    public static void loadCustomFont() {
        try {
            // Percorso del file del font personalizzato
            String fontFilePath = RelativePath.getAbsolutePath("/src/Shared/dogica.ttf");

            // Carica il font dal file
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontFilePath));

            // Ottieni il sistema di grafica
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            // Registra il font personalizzato nel sistema di grafica
            ge.registerFont(customFont);

            // Ora puoi utilizzare il font personalizzato
            myCustomFont = customFont.deriveFont(Font.PLAIN, 14);

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }
}
