package Battle;

import java.awt.Color;

import javax.swing.*;

import Shared.ImageUtility;
import Shared.PixelFont;
import Shared.RelativePath;

public class RecapBattle extends JFrame{
    
    public JFrame frame;

    public RecapBattle() {
        frame = new JFrame();
        frame.setTitle("Recap Battle");
        frame.setSize(500, 500);

        // Carica l'immagine di sfondo
        ImageIcon backgroundImage = new ImageIcon(RelativePath.getAbsolutePath("Image/backPlayer.png"));

        // Crea un JLabel con l'immagine di sfondo
        JLabel backgroundLabel = new JLabel(ImageUtility.resizeIcon(backgroundImage, 850, 500));
        backgroundLabel.setBounds(0, 0, 500, 500);
        
        frame.setLayout(null);

        JLabel labelWon = new JLabel("You won!");
        labelWon.setFont(PixelFont.myCustomFont.deriveFont(18f));
        labelWon.setForeground(Color.BLACK);
        labelWon.setBounds(175, 0, 200, 50);
        frame.add(labelWon);
      
        frame.add(backgroundLabel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
    }

    public static void main(String[] args) {
        PixelFont.loadCustomFont();
        new RecapBattle();
    }
}
