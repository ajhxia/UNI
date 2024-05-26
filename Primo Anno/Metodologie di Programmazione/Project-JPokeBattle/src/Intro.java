import javax.swing.*;

import Shared.ImageUtility;
import Shared.PixelFont;
import Shared.RelativePath;
import Shared.Style;

import java.awt.Color;
import java.awt.Image;
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
        arrowLabel.setBounds(20, 65, 40, 40);

        // creazione del bottone e posizionamento
        JButton button = Style.createButton(Color.WHITE, "Start a New Game", 14, 70, 65, 280, 40);
    
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
