import javax.swing.*;

import Shared.ImageUtility;
import Shared.PixelFont;
import Shared.RelativePath;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.*;

/*
 * Questa classe rappresenta la finestra di benvenuto del gioco
 * La finestra contiene un'immagine di benvenuto e un bottone per iniziare la battaglia
 */

public class Intro extends JFrame implements ActionListener {
    JFrame frame;

    public static void main(String[] args) {
        Intro intro = new Intro();
        PixelFont.loadCustomFont();
        Pokedex.loadPokemon();
        intro.showWindow();
    }

    public Intro() {
        frame = new JFrame("JPokeBattle");
    }

    private void showWindow() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(722, 725);

        // Creazione di un layout null
        frame.setLayout(null);

        // Carica l'immagine di sfondo
        ImageIcon backgroundImage = new ImageIcon(RelativePath.getAbsolutePath("/Image/penup_20240516_163422.jpg"));

        // Crea un JLabel con l'immagine di sfondo e posiziona
        JLabel backgroundLabel = new JLabel(ImageUtility.resizeIcon(backgroundImage, 725, 730));
        backgroundLabel.setBounds(0, 0, 725, 730);

        ImageIcon arrow = new ImageIcon(RelativePath.getAbsolutePath("/Image/arrow.gif"));
        // Resize the original image to the desired dimensions
        Image originalImage = arrow.getImage();
        Image resizedImage = originalImage.getScaledInstance(40, 40, Image.SCALE_DEFAULT);

        // Create a new ImageIcon from the resized image
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel arrowLabel = new JLabel(resizedIcon);
        arrowLabel.setBounds(20, 70, 40, 40);

        // creazione del bottone e posizionamento
        JButton button = new JButton("Start Game");
        button.setFont(PixelFont.myCustomFont);
        button.setForeground(Color.WHITE); // Imposta il colore del testo
        button.setOpaque(false); // Rende il bottone trasparente
        button.setContentAreaFilled(false); // Rende trasparente l'area di contenuto del bottone
        button.setBorder(BorderFactory.createLineBorder(Color.white, 2)); // Imposta il bordo
        button.setMargin(new Insets(10, 20, 10, 20)); // Imposta il padding (top, left, bottom, right)
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20) // Imposta il padding all'interno del bordo
        ));
        button.setFocusPainted(false); // Rimuove l'effetto focus per migliorare l'aspetto
        button.setBounds(70, 65, 200, 40); // Posiziona il bottone

        // Aggiungo un ascoltatore per l'effetto pointer
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Aggiungo l'azione di ascolto al bottone
        button.setActionCommand("start");
        button.addActionListener(this);

        // Aggiunge il bottone e il JLabel al JFrame
        frame.add(arrowLabel);
        frame.add(button);
        frame.add(backgroundLabel);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Centra la finestra
    }

    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        if (command.equals("start")) {
            Player showCreateCharacter = new Player();
            showCreateCharacter.setVisible(true);
            frame.setVisible(false); // Chiudi la finestra Intro
            System.out.println("Start button clicked.");
        }
    }
}
