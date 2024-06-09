import javax.swing.*;

import Generic.Player;
import Generic.Pokedex;
import Shared.*;

/*
 * Questa classe rappresenta la finestra di benvenuto del gioco
 * La finestra contiene un'immagine di benvenuto e un bottone per iniziare la battaglia
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JPokeBattle extends JFrame implements ActionListener {
    private JFrame frame;
    private JPanel mainPanel;

    public static void main(String[] args) {
        PixelFont.loadCustomFont();
        Pokedex.loadPokemon();
        JPokeBattle intro = new JPokeBattle();
        intro.showMainScreen();
    }

    public JPokeBattle() {
        frame = new JFrame("JPokeBattle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(722, 725);
        frame.setLocationRelativeTo(null); // Centra la finestra
        frame.setVisible(true);
    }

    private void showMainScreen() {
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

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

        // Aggiunge il bottone e il JLabel al mainPanel
        mainPanel.add(arrowLabel);
        mainPanel.add(button);
        mainPanel.add(backgroundLabel);

        frame.add(mainPanel);
        frame.revalidate();
        frame.repaint();
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
