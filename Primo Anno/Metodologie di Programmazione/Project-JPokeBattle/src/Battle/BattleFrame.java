package Battle;

import javax.swing.*;

import Game.Coach;

public class BattleFrame extends JFrame{
    JFrame frame; 
    public BattleFrame(Coach player, Coach npc) {
        frame = new JFrame("Battle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 470);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    
}
