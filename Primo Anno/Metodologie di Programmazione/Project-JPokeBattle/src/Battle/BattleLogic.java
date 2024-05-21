package Battle;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JPanel;

import Game.Coach;

public class BattleLogic {
/*TODO: implementare correttamente il danno inflitto in base al tipo e tenendo conto della resistenza */
    public static float typeEffects(int damage, Coach npc, String[] types) {
        if( types[0] == "fire") {
            if (Arrays.asList(npc.getPokemonInUse().getTypes()).contains("water")) {
                return damage * 0.5f;
            } else if (Arrays.asList(npc.getPokemonInUse().getTypes()).contains("grass")) {
                return damage * 2;
            } else {
                return damage;
            }
        } else if (types[0] == "water") {
            if (Arrays.asList(npc.getPokemonInUse().getTypes()).contains("grass")) {
                return damage * 0.5f;
            } else if (Arrays.asList(npc.getPokemonInUse().getTypes()).contains("fire")) {
                return damage * 2;
            } else {
                return damage;
            }
        } else if (types[0] == "grass") {
            if (Arrays.asList(npc.getPokemonInUse().getTypes()).contains("fire")) {
                return damage * 0.5f;
            } else if (Arrays.asList(npc.getPokemonInUse().getTypes()).contains("water")) {
                return damage * 2;
            } else {
                return damage;
            }
        } else if(types[0] == "electric" ) {
            if (Arrays.asList(npc.getPokemonInUse().getTypes()).contains("water")) {
                return damage * 2;
            } else if (Arrays.asList(npc.getPokemonInUse().getTypes()).contains("grass")) {
                return damage * 0.5f;
            } else {
                return damage;
            }
        } else if(types[0] == "normal") {
            if (Arrays.asList(npc.getPokemonInUse().getTypes()).contains("ghost")) {
                return damage * 0;
            } else if(Arrays.asList(npc.getPokemonInUse().getTypes()).contains("rock")) {
                return damage / 2;
            } else {
                return damage;
            }
        } else if(types[0] == "fighting") {
            if (Arrays.asList(npc.getPokemonInUse().getTypes()).contains("normal")) {
                return damage * 2;
            } else if (Arrays.asList(npc.getPokemonInUse().getTypes()).contains("flying")) {
                return damage * 0.5f;
            } else {
                return damage;
            }
        } else if(types[0] == "flying") {
            if (Arrays.asList(npc.getPokemonInUse().getTypes()).contains("fighting")) {
                return damage * 2;
            } else if (Arrays.asList(npc.getPokemonInUse().getTypes()).contains("electric")) {
                return damage * 0.5f;
            } else {
                return damage;
            }
        } else if(types[0] == "poison") {
            if (Arrays.asList(npc.getPokemonInUse().getTypes()).contains("grass")) {
                return damage * 2;
            } else if (Arrays.asList(npc.getPokemonInUse().getTypes()).contains("poison")) {
                return damage * 0.5f;
            } else {
                return damage;
            }
        } else if(types[0] == "ground") {
            if (Arrays.asList(npc.getPokemonInUse().getTypes()).contains("poison")) {
                return damage * 2;
            } else if (Arrays.asList(npc.getPokemonInUse().getTypes()).contains("electric")) {
                return damage * 0.5f;
            } else {
                return damage;
            }
        } else if(types[0] == "rock") {
            if (Arrays.asList(npc.getPokemonInUse().getTypes()).contains("flying")) {
                return damage * 2;
            } else if (Arrays.asList(npc.getPokemonInUse().getTypes()).contains("fire")) {
                return damage * 0.5f;
            } else {
                return damage;
            }
        } else if(types[0] == "bug") {
            if (Arrays.asList(npc.getPokemonInUse().getTypes()).contains("grass")) {
                return damage * 2;
            } else if (Arrays.asList(npc.getPokemonInUse().getTypes()).contains("poison")) {
                return damage * 0.5f;
            } else {
                return damage;
            }
        } else if(types[0] == "ghost") {
            if (Arrays.asList(npc.getPokemonInUse().getTypes()).contains("ghost")) {
                return damage * 2;
            } else if (Arrays.asList(npc.getPokemonInUse().getTypes()).contains("psychic")) {
                return damage * 0.5f;
            } else {
                return damage;
            }
        } else if(types[0] == "dragon"){
            if (Arrays.asList(npc.getPokemonInUse().getTypes()).contains("dragon")) {
                return damage * 2;
            } else {
                return damage;
            }
        } else if(types[0] == "psychic") {
            if (Arrays.asList(npc.getPokemonInUse().getTypes()).contains("fighting")) {
                return damage * 2;
            } else if (Arrays.asList(npc.getPokemonInUse().getTypes()).contains("poison")) {
                return damage * 0.5f;
            } else {
                return damage;
            }
        } else if(types[0] == "ice") {
            if (Arrays.asList(npc.getPokemonInUse().getTypes()).contains("grass")) {
                return damage * 2;
            } else if (Arrays.asList(npc.getPokemonInUse().getTypes()).contains("fire")) {
                return damage * 0.5f;
            } else {
                return damage;
            }
        } 
        
        else {
            return damage;
        }
        
    }


    public static void decreaseHpNpc(Coach npc, int damage, String[] types) {
        if (npc.getPokemonInUse().getStats().getHp() - damage <= 0) {

        } else {
            float damageCalculated = typeEffects(damage, npc, types);
            int dmg = (int) (npc.getPokemonInUse().getStats().getHp() - damageCalculated);
            npc.getPokemonInUse().getStats().setHp(dmg); 
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
            decreaseHpNpc(npc, damage, player.getTeam().getPokemon(0).getTypes());
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
