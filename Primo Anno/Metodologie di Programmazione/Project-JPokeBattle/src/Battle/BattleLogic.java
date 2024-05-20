package Battle;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.JPanel;

import Game.Coach;
import Pokemon.Pokemon;

public class BattleLogic {

    public static Pokemon currentPokemonPlayer;
    public static Pokemon currentPokemonNpc;

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

    public static void whoStart(Coach player, Coach npc) {
        int playerSpeed = player.getTeam().getPokemon(0).getStats().getSpeed();
        int npcSpeed = npc.getTeam().getPokemon(0).getStats().getSpeed();

        float playerSpeedPercent = (playerSpeed / 7) * 100;
        float npcSpeedPercent = (npcSpeed / 7) * 100;

        if (playerSpeedPercent >= npcSpeedPercent) {
            setTurn(true, BattleFrame.abilityPanel);
            int damage = player.getTeam().getPokemon(0).getStats().getAttack();
            decreaseHpNpc(npc, damage);
        } else {
            setTurn(false, BattleFrame.abilityPanel);
            int damage = npc.getTeam().getPokemon(0).getStats().getAttack();
            decreaseHpPlayer(player, damage);
        }
    }

    public static void setTurn(boolean turn, JPanel abilityPanel) {
        if (turn) {
            // Attiva i pulsanti delle abilità del giocatore
            for (Component component : abilityPanel.getComponents()) {
                if (component instanceof JButton) {
                    JButton abilityButton = (JButton) component;
                    abilityButton.setEnabled(true);
                    abilityButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }
        } else {
            // Disattiva i pulsanti delle abilità del giocatore
            for (Component component : abilityPanel.getComponents()) {
                if (component instanceof JButton) {
                    JButton abilityButton = (JButton) component;
                    abilityButton.setEnabled(false);
                    // Imposta l'opacità del pulsante
                    abilityButton.setOpaque(true);
                    abilityButton.setContentAreaFilled(false);
                    abilityButton.setBorderPainted(false);
                    abilityButton.setBackground(new Color(0, 0, 0, 50)); // Imposta un colore con trasparenza
                }
            }
        }
    }

}
