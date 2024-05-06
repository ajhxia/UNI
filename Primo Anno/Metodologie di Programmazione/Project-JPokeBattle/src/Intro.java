import javax.swing.*;

import Shared.PixelFont;
import Shared.RelativePath;
import Shared.ResizeIcon;

import java.awt.Color;
import java.awt.Cursor;
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
        frame.setSize(800, 470);

        JPanel panel = new JPanel();
        // aggiungo l'immagine Pokemon
        ImageIcon image = new ImageIcon(RelativePath.getAbsolutePath("\\Image\\International_Pokémon_logo.svg.png"));

        // Dimensioni desiderate per l'immagine ridimensionata
        int newWidth = 700;
        int newHeight = 300;
        // Ridimensiona l'immagine
        JLabel label = new JLabel(ResizeIcon.resizeIcon(image, newWidth, newHeight));
        panel.add(label);

        // creazione del bottone
        JButton button = new JButton("Start Battle");
        button.setFont(PixelFont.myCustomFont);
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button.setMargin(new Insets(10, 20, 10, 20)); // Imposta il padding (top, left, bottom, right)
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(10, 20, 10, 20) // Imposta il padding all'interno del bordo
        ));
        button.setFocusPainted(false); // Rimuove l'effetto focus per migliorare l'aspetto

        panel.add(button);
        button.setActionCommand("start");
        button.addActionListener(this);
        // aggiungo un ascoltatore per l'effetto pointer
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Centra la finestra
    }

    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        if (command.equals("start")) {
            Pokedex pokedex = new Pokedex();
            JFrame pokedexFrame = new JFrame("Pokedex");
            pokedexFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pokedexFrame.setSize(900, 700);
            pokedexFrame.add(pokedex);
            pokedexFrame.setVisible(true);
            pokedexFrame.setLocationRelativeTo(null); // Centra la finestra
            frame.setVisible(false); // Chiudi la finestra Intro
            System.out.println("Start button clicked.");
        }
    }
    
}
