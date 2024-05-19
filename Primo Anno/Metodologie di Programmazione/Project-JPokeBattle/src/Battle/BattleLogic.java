package Battle;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JPanel;

import Game.Coach;

public class BattleLogic {

    public static void decreaseHpNpc(Coach npc, int damage) {
        if (npc.getTeam().getPokemon(0).getStats().getHp() - damage <= 0) {

        } else {

        }

        BattleFrame.updateNPCHealthBar(npc.getTeam().getPokemon(0).getStats().getHp());
    }

    public static void decreaseHpPlayer(Coach player, int damage) {
        if (player.getTeam().getPokemon(0).getStats().getHp() - damage <= 0) {

        } else {

        }

        BattleFrame.updatePlayerHealthBar(player.getTeam().getPokemon(0).getStats().getHp());
    }

    public static void whoStart(int playerSpeed, int npcSpeed) {
        
        float playerSpeedPercent = (playerSpeed/7)*100;
        float npcSpeedPercent = (npcSpeed/7)*100;

        if (playerSpeedPercent >= npcSpeedPercent) {
            setTurn(true, BattleFrame.abilityPanel);
        } else {
            setTurn(false, BattleFrame.abilityPanel);
        }

        BattleFrame.startBattle = true;
    }

    public static void setTurn(boolean turn, JPanel abilityPanel) {
        if (turn) {
            // Attiva i pulsanti delle abilità del giocatore
            for (Component component : abilityPanel.getComponents()) {
                if (component instanceof JButton) {
                    JButton abilityButton = (JButton) component;
                    abilityButton.setEnabled(true);
                }
            }
        } else {
            // Disattiva i pulsanti delle abilità del giocatore
            for (Component component : abilityPanel.getComponents()) {
                if (component instanceof JButton) {
                    JButton abilityButton = (JButton) component;
                    abilityButton.setEnabled(false);
                }
            }
        }
    }

}
