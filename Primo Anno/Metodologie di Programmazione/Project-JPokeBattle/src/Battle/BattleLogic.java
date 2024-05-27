package Battle;

import java.awt.Component;
import java.awt.Cursor;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import Game.Coach;

public class BattleLogic {
    /*
     * TODO: implementare correttamente il danno inflitto in base al tipo e tenendo
     * conto della resistenza
     */
    public static float typeEffects(int damageIn, String typeAbility, Coach enemy) { // in ingresso vuole: il danno
                                                                                     // fatto dall'abilità, il coach del
                                                                                     // giocatore e il tipo dell'abilità
        List<String> npcPokemonTypes = Arrays.asList(enemy.getPokemonInUse().getTypes());
        int pokeDefense = enemy.getPokemonInUse().getStats().getDefense();
        int pokeLv = enemy.getPokemonInUse().getLvl();

        // calcolo del danno
        float damage = (float) (((2 * pokeLv + 10) / 250) * (damageIn) * (pokeDefense / 50) + 2);

        if (typeAbility == "fire") {
            if (npcPokemonTypes.contains("water")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("grass")) {
                return damage * 2;
            } else if(npcPokemonTypes.contains("rock")) {
                return damage * 0.5f;
            } else if(npcPokemonTypes.contains("ice")) {
                return damage * 2;
            } else if(npcPokemonTypes.contains("bug")) {
                return damage * 2;
            } else if(npcPokemonTypes.contains("dragon")) {
                return damage * 0.5f;
            } else if(npcPokemonTypes.contains("fire")) {
                return damage * 0.5f;
            } else {
                return damage;
            }
        } else if (typeAbility == "water") {
            if (npcPokemonTypes.contains("grass")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("fire")) {
                return damage * 2;
            } else if(npcPokemonTypes.contains("rock")) {
                return damage * 2;
            } else if(npcPokemonTypes.contains("ground")) {
                return damage * 2;
            } else if(npcPokemonTypes.contains("water")) {
                return damage * 0.5f;
            } else if(npcPokemonTypes.contains("dragon")) {
                return damage * 0.5f;
            } else {
                return damage;
            }
        } else if (typeAbility == "grass") {
            if (npcPokemonTypes.contains("water")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("fire")) {
                return damage * 0.5f;
            } else if(npcPokemonTypes.contains("rock")) {
                return damage * 2;
            } else if(npcPokemonTypes.contains("ground")) {
                return damage * 2;
            } else if(npcPokemonTypes.contains("grass")) {
                return damage * 0.5f;
            } else if(npcPokemonTypes.contains("bug")) {
                return damage * 0.5f;
            } else if(npcPokemonTypes.contains("dragon")) {
                return damage * 0.5f;
            } else if(npcPokemonTypes.contains("poison")) {
                return damage * 0.5f;
            } else if(npcPokemonTypes.contains("flying")) {
                return damage * 0.5f;
            } else {
                return damage;
            }
        } else if (typeAbility == "electric") {
            if (npcPokemonTypes.contains("water")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("electric")) {
                return damage * 0.5f;
            } else if(npcPokemonTypes.contains("flying")) {
                return damage * 2;
            } else if(npcPokemonTypes.contains("dragon")) {
                return damage * 0.5f;
            } else if(npcPokemonTypes.contains("grass")) {
                return damage * 0.5f;
            } else if(npcPokemonTypes.contains("ground")) {
                return damage * 0;
            } else {
                return damage;
            }
        } else if (typeAbility == "normal") {
            if (npcPokemonTypes.contains("rock")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("ghost")) {
                return damage * 0;
            } else {
                return damage;
            }
        } else if (typeAbility == "fighting") {
            if (npcPokemonTypes.contains("normal")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("rock")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("ice")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("poison")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("flying")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("psychic")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("bug")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("ghost")) {
                return damage * 0;
            } else {
                return damage;
            }
        } else if (typeAbility == "flying") {
            if (npcPokemonTypes.contains("fighting")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("rock")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("electric")) {
                return damage * 0.5f;
            } else if(npcPokemonTypes.contains("bug")) {
                return damage * 2;
            } else if(npcPokemonTypes.contains("grass")) {
                return damage * 2;
            } else {
                return damage;
            }
        } else if (typeAbility == "poison") {
            if (npcPokemonTypes.contains("grass")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("poison")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("ground")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("rock")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("ghost")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("bug")) {
                return damage * 2;
            } else {
                return damage;
            }
        } else if (typeAbility == "ground") {
            if (npcPokemonTypes.contains("fire")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("electric")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("poison")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("rock")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("grass")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("bug")) {
                return damage * 0.5f;
            } else if(npcPokemonTypes.contains("flying")) {
                return damage * 0;
            } else {
                return damage;
            }
        } else if (typeAbility == "rock") {
            if (npcPokemonTypes.contains("fire")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("ice")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("fighting")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("ground")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("flying")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("bug")) {
                return damage * 2;
            } else {
                return damage;
            }
        } else if (typeAbility == "bug") {
            if (npcPokemonTypes.contains("grass")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("fire")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("fighting")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("poison")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("flying")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("psychic")) {
                return damage * 2;
            } else {
                return damage;
            }
        } else if (typeAbility == "ghost") {
            if (npcPokemonTypes.contains("ghost")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("psychic")) {
                return damage * 0;
            } else if(npcPokemonTypes.contains("normal")) {
                return damage * 0;
            } else {
                return damage;
            }
        } else if (typeAbility == "dragon") {
            if (npcPokemonTypes.contains("dragon")) {
                return damage * 2;
            } else {
                return damage;
            }
        } else if (typeAbility == "psychic") {
            if (npcPokemonTypes.contains("fighting")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("poison")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("psychic")) {
                return damage * 0.5f;
            } else {
                return damage;
            }
        } else if (typeAbility == "ice") {
            if (npcPokemonTypes.contains("flying")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("fire")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("water")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("ice")) {
                return damage * 0.5f;
            } else if(npcPokemonTypes.contains("dragon")) {
                return damage * 2;
            } else if(npcPokemonTypes.contains("grass")) {
                return damage * 2;
            } else if(npcPokemonTypes.contains("ground")) {
                return damage * 2;
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
            float damageCalculated = typeEffects(damage, type, npc);
            int dmg = (int) (npc.getPokemonInUse().getStats().getHp() - damageCalculated);
            npc.getPokemonInUse().getStats().setHp(dmg);
        }

        BattleFrame.updateNPCHealthBar(npc.getTeam().getPokemon(0).getStats().getHp());
    }

    public static void decreaseHpPlayer(Coach player, int damage, String type) {
        if (player.getPokemonInUse().getStats().getHp() - damage <= 0) {

        } else {
            float damageCalculated = typeEffects(damage, type, player);
            int dmg = (int) (player.getPokemonInUse().getStats().getHp() - damageCalculated);
            player.getPokemonInUse().getStats().setHp(dmg);
        }

        BattleFrame.updateNPCHealthBar(player.getTeam().getPokemon(0).getStats().getHp());
    }

    public static void whoStart(Coach player, Coach npc) {
        int playerSpeed = player.getTeam().getPokemon(0).getStats().getSpeed();
        int npcSpeed = npc.getTeam().getPokemon(0).getStats().getSpeed();

        float playerSpeedPercent = (playerSpeed / 7) * 100;
        float npcSpeedPercent = (npcSpeed / 7) * 100;

        if (playerSpeedPercent >= npcSpeedPercent) {
            setTurn(true, BattleFrame.abilityPanel, player,0 ,npc);
            int damage = player.getTeam().getPokemon(0).getStats().getAttack();
            decreaseHpNpc(npc, damage, player.getTeam().getPokemon(0).getAbilities().get(0).getTypo());
        } else {
            setTurn(false, BattleFrame.abilityPanel, player,0,npc);
            int damage = npc.getTeam().getPokemon(0).getStats().getAttack();
            decreaseHpPlayer(player, damage, npc.getTeam().getPokemon(0).getAbilities().get(0).getTypo());
        }
    }

    public static void setTurn(boolean turn, JPanel abilityPanel, Coach player, int index, Coach npc) {
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
                }
            }

            decreaseHpPlayer(player, player.getPokemonInUse().getStats().getAttack(), npc.getPokemonInUse().getAbilities().get(index).getTypo());
            
        }
    }

}
