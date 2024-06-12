import javax.swing.*;

import Generic.*;
import Shared.*;

/*
 * Questa classe rappresenta la finestra di benvenuto del gioco
 * La finestra contiene un'immagine di benvenuto e un bottone per iniziare la battaglia
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JPokeBattle extends JFrame implements ActionListener {
    private JFrame frame;
    private JPanel mainPanel;

    public static void main(String[] args) {
        PixelFont.loadCustomFont();
        Pokedex.loadPokemon();
        JPokeBattle intro = new JPokeBattle();
        intro.showMainScreen();
    }

    /**
     * Costruttore della classe JPokeBattle
       JPokeBattle
     */

    public JPokeBattle() {
        frame = new JFrame("JPokeBattle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(722, 725);
        frame.setLocationRelativeTo(null); // Centra la finestra
        frame.setVisible(true);
    }

    /**
     * Mostra la schermata iniziale del gioco
     *  
     */

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
        JButton startButton = Style.createButton(Color.WHITE, "Start a New Game", 14, 70, 65, 280, 40);
        // Aggiungo l'azione di ascolto al bottone
        startButton.setActionCommand("start");
        startButton.addActionListener(this);

        // Creazione del secondo bottone e posizionamento
        
        JButton loadButton = Style.createButton(Color.WHITE, "Record", 14, 70, 20, 150, 40);
        loadButton.setActionCommand("load");
        loadButton.addActionListener(this);

        // Aggiunge i bottoni e il JLabel al mainPanel
        mainPanel.add(arrowLabel);
        mainPanel.add(startButton);
        mainPanel.add(loadButton);
        mainPanel.add(backgroundLabel);

        frame.add(mainPanel);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Metodo per gestire l'evento di click sui bottoni
     * @param event evento di click
     *  
     */
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        if (command.equals("start")) {
            Player showCreateCharacter = new Player();
            showCreateCharacter.setVisible(true);
            frame.setVisible(false); // Chiudi la finestra Intro
            System.out.println("Start button clicked.");
        } else if (command.equals("load")) {
            showDataFromFile();
        }
    }

    /**
     * Mostra i dati salvati su file
     *  
     */
    private void showDataFromFile() {
        JFrame dataFrame = new JFrame("Data from File");
        dataFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dataFrame.setSize(375, 760);
        dataFrame.setLocationRelativeTo(null); // Centra la finestra
        ImageIcon backgroundImage = new ImageIcon(RelativePath.getAbsolutePath("Image/image.png"));
        JLabel backgroundLabel = new JLabel(ImageUtility.resizeIcon(backgroundImage, 368, 670));
        backgroundLabel.setBounds(0, 0, 368, 760);

        JLabel title = new JLabel("Record", SwingConstants.CENTER);
        title.setFont(PixelFont.myCustomFont.deriveFont(24f));
        title.setForeground(Color.BLACK);
        title.setBounds(0, 0, 368, 50);
        dataFrame.add(title);

        // Usa BorderLayout per una gestione migliore dei componenti
        dataFrame.setLayout(null);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBounds(0, 0, 368, 710);

        String path = RelativePath.getAbsolutePath("src/Shared/Record.txt");
        System.out.println(path);
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int index = 0;
            while ((line = br.readLine()) != null) {
                JLabel label = new JLabel(line);
                label.setFont(PixelFont.myCustomFont.deriveFont(12f));
                label.setForeground(Color.WHITE);
                label.setBounds(45, 63 + (index * 110), 200, 75);
                contentPanel.add(label);
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPanel.setOpaque(false);
        dataFrame.add(contentPanel);
        dataFrame.add(backgroundLabel);
        dataFrame.setVisible(true);
    }
}
