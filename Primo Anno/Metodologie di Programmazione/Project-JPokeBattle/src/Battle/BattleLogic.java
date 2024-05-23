package Battle;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import Game.Coach;

public class BattleLogic {
/*TODO: implementare correttamente il danno inflitto in base al tipo e tenendo conto della resistenza */
    public static float typeEffects(int damage, Coach npc, String type) { //in ingresso vuole: il danno fatto dall'abilità, il coach del giocatore e il tipo dell'abilità
    List<String> npcPokemonTypes = Arrays.asList(npc.getPokemonInUse().getTypes());    
    //int pokeDefense = npc.getPokemonInUse().getStats().getDefense();
    
    if( type == "fire") {
            if (npcPokemonTypes.contains("water")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("grass")) {
                return damage * 2;
            } else {
                return damage;
            }
        } else if (type == "water") {
            if (npcPokemonTypes.contains("grass")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("fire")) {
                return damage * 2;
            } else {
                return damage;
            }
        } else if (type == "grass") {
            if (npcPokemonTypes.contains("fire")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("water")) {
                return damage * 2;
            } else {
                return damage;
            }
        } else if(type == "electric" ) {
            if (npcPokemonTypes.contains("water")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("grass")) {
                return damage * 0.5f;
            } else {
                return damage;
            }
        } else if(type == "normal") {
            if (npcPokemonTypes.contains("ghost")) {
                return damage * 0;
            } else if(npcPokemonTypes.contains("rock")) {
                return damage / 2;
            } else {
                return damage;
            }
        } else if(type == "fighting") {
            if (npcPokemonTypes.contains("normal")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("flying")) {
                return damage * 0.5f;
            } else {
                return damage;
            }
        } else if(type == "flying") {
            if (npcPokemonTypes.contains("fighting")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("electric")) {
                return damage * 0.5f;
            } else {
                return damage;
            }
        } else if(type == "poison") {
            if (npcPokemonTypes.contains("grass")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("poison")) {
                return damage * 0.5f;
            } else {
                return damage;
            }
        } else if(type == "ground") {
            if (npcPokemonTypes.contains("poison")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("electric")) {
                return damage * 0.5f;
            } else {
                return damage;
            }
        } else if(type == "rock") {
            if (npcPokemonTypes.contains("flying")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("fire")) {
                return damage * 0.5f;
            } else {
                return damage;
            }
        } else if(type == "bug") {
            if (npcPokemonTypes.contains("grass")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("poison")) {
                return damage * 0.5f;
            } else {
                return damage;
            }
        } else if(type == "ghost") {
            if (npcPokemonTypes.contains("ghost")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("psychic")) {
                return damage * 0.5f;
            } else {
                return damage;
            }
        } else if(type == "dragon"){
            if (npcPokemonTypes.contains("dragon")) {
                return damage * 2;
            } else {
                return damage;
            }
        } else if(type == "psychic") {
            if (npcPokemonTypes.contains("fighting")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("poison")) {
                return damage * 0.5f;
            } else {
                return damage;
            }
        } else if(type == "ice") {
            if (npcPokemonTypes.contains("grass")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("fire")) {
                return damage * 0.5f;
            } else {
                return damage;
            }
        } 
        
        else {
            return damage;
        }
        
    }


    public static void decreaseHpNpc(Coach npc, int damage, String type) {
        if (npc.getPokemonInUse().getStats().getHp() - damage <= 0) {

        } else {
            float damageCalculated = typeEffects(damage, npc, type); 
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
            decreaseHpNpc(npc, damage, player.getTeam().getPokemon(0).getAbilities().get(0).getTypo());
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
