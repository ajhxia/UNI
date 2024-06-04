package Battle;

import java.awt.Color;

import javax.swing.*;

import Shared.PixelFont;

public class RecapBattle extends JFrame{
    
    public JFrame frame;

    public RecapBattle() {
        frame = new JFrame();
        frame.setTitle("Recap Battle");
        frame.setSize(500, 500);
        
        frame.setLayout(null);

        JLabel labelWon = new JLabel("You won!");
        labelWon.setFont(PixelFont.myCustomFont.deriveFont(18f));
        labelWon.setForeground(Color.BLACK);
        labelWon.setBounds(175, 0, 200, 50);
        frame.add(labelWon);
      

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
    }

    public static void main(String[] args) {
        PixelFont.loadCustomFont();
        new RecapBattle();
    }
}
