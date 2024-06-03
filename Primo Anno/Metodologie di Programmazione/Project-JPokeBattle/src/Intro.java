import javax.swing.*;

import Shared.*;

/*
 * Questa classe rappresenta la finestra di benvenuto del gioco
 * La finestra contiene un'immagine di benvenuto e un bottone per iniziare la battaglia
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Intro extends JFrame implements ActionListener {
    private JFrame frame;
    private JPanel loadingPanel;
    private JPanel mainPanel;
    private JProgressBar progressBar;

    public static void main(String[] args) {
        PixelFont.loadCustomFont();
        Intro intro = new Intro();
        intro.showLoadingScreen();
        intro.loadResources();
        // Create a separate thread to load resources and update the progress bar
        new Thread(() -> {
            SwingUtilities.invokeLater(() -> {
                intro.showMainScreen();
            });
        }).start();
    }

    public Intro() {
        frame = new JFrame("JPokeBattle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(722, 725);
        frame.setLocationRelativeTo(null); // Centra la finestra
        frame.setVisible(true);
    }

    private void showLoadingScreen() {
        loadingPanel = new JPanel(null);
        loadingPanel.setBackground(Color.BLACK);

        JLabel loadingLabel = new JLabel("Loading...");
        loadingLabel.setBounds(250, 250, 200, 50);
        loadingLabel.setForeground(Color.WHITE);
        loadingLabel.setFont(PixelFont.myCustomFont.deriveFont(20f));

        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.BLACK);
        progressBar.setFont(PixelFont.myCustomFont.deriveFont(8f));
        progressBar.setBounds(27, 650, 650, 25);

        loadingPanel.add(loadingLabel);
        loadingPanel.add(progressBar);

        frame.add(loadingPanel);
        frame.revalidate();
        frame.repaint();
    }

    private void loadResources() {
        try {
            // Simulate loading process with sleep
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(50); // Simulate time taken to load each resource
                progressBar.setValue(i);
            }
            Pokedex.loadPokemon();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void showMainScreen() {
        frame.remove(loadingPanel);

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
