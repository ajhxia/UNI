package Battle;

import javax.swing.*;

import Shared.PixelFont;
import Shared.RelativePath;
import Shared.Style;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GameOverScreen extends JFrame {

    /**
     * Costruttore della classe GameOverScreen
     */
    public GameOverScreen() {
        // Impostazioni della finestra
        setTitle("Game Over");
        setSize(305, 230);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Etichetta con il messaggio "Game Over"
        JLabel gameOverLabel = new JLabel("Game Over", SwingConstants.CENTER);
        gameOverLabel.setFont(PixelFont.myCustomFont.deriveFont(24f));
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setBounds(0, 50, 300, 50);
        
        add(gameOverLabel);

        // Pulsante per chiudere il gioco
        JButton closeButton = Style.createButton(Color.BLACK ,"Close", 15, 70, 130, 150, 35);
        closeButton.setFont(PixelFont.myCustomFont.deriveFont(18f));
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGameData();
                System.exit(0); // Esce dall'applicazione
            }
        });
        add(closeButton);
    }

    
    private void saveGameData() {
        String playerName = Battle.getPlayer().getName();
        int gamesWon = Battle.getPlayer().getGameWinned();
        String path = RelativePath.getAbsolutePath("src/Shared/Record.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.write("\n" + playerName + "," + gamesWon);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
