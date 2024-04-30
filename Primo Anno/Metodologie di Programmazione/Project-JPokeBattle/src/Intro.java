import javax.swing.*;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.*;

public class Intro extends JFrame implements ActionListener {
    JFrame frame;

    public static void main(String[] args) {
        Intro intro = new Intro();
        intro.showWindow();
    }

    public Intro() {
        frame = new JFrame("JPokeBattle");
    }

    private void showWindow() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel panel = new JPanel();
        //aggiungo l'immagine Pokemon
        ImageIcon image = new ImageIcon("Primo Anno\\Metodologie di Programmazione\\Project-JPokeBattle\\Image\\International_Pokémon_logo.svg.png");
        
       
        // Dimensioni desiderate per l'immagine ridimensionata
        int newWidth = 700;
        int newHeight = 300;

        // Ridimensiona l'immagine
        ImageIcon resizedIcon = resizeIcon(image, newWidth, newHeight);
        JLabel label = new JLabel(resizedIcon);
        panel.add(label);
        //creazione del bottone
        ImageIcon icon = new ImageIcon("Primo Anno\\Metodologie di Programmazione\\Project-JPokeBattle\\Image\\button_start-battle.png");
        JButton button = new JButton(icon);
        button.setBorder(null);
        panel.add(button);
        button.setActionCommand("start");
        button.addActionListener(this);
        // aggiungo un ascoltatore per l'effetto pointer
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setCursor(Cursor.getDefaultCursor());
            }
        });

        frame.getContentPane().add(panel);
        frame.setVisible(true);
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

            frame.setVisible(false); // Chiudi la finestra Intro
            System.out.println("Start button clicked.");
        }
    }

    // Metodo per ridimensionare un ImageIcon
    private static ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
}