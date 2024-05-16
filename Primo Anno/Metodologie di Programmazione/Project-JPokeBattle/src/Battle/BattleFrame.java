package Battle;

import javax.swing.*;

import Game.Coach;
import Shared.*;

public class BattleFrame extends JFrame{
    JFrame frame; 
    public BattleFrame(Coach player, Coach npc) {
        frame = new JFrame("Battle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(849, 528);

        // Carica l'immagine di sfondo
        ImageIcon backgroundImage = new ImageIcon(RelativePath.getAbsolutePath("Image/PENUP_20240516_162632.png"));

        // Crea un JLabel con l'immagine di sfondo
        JLabel backgroundLabel = new JLabel(ImageUtility.resizeIcon(backgroundImage, 850, 500));

        // Aggiunge il JLabel al JFrame
        frame.add(backgroundLabel);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        // Esempio di utilizzo
        new BattleFrame(null, null);
    }
}
