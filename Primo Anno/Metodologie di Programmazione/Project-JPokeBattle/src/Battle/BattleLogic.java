package Battle;

import java.awt.Component;
import java.awt.Cursor;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;

import Game.Coach;

public class BattleLogic {
    private static Coach player;
    private static Coach npc;
    private static boolean turn;

    public static boolean isTurn() {
        return turn;
    }

    public static Coach getPlayer() {
        return player;
    }

    public static void setPlayer(Coach playerIn) {
        player = playerIn;
    }

    public static Coach getNpc() {
        return npc;
    }

    public static void setNpc(Coach npcIn) {
        npc = npcIn;
    }

    public static void decreaseHpNpc(int damage, String type) {
        if (npc.getPokemonInUse().getStats().getHp() - damage <= 0) {

        } else {
            float damageCalculated = typeEffects(damage, type, npc);
            int dmg = (int) (npc.getPokemonInUse().getStats().getHp() - damageCalculated);
            npc.getPokemonInUse().getStats().setHp(dmg);
        }

        BattleFrame.updateNPCHealthBar(npc.getTeam().getPokemon(0).getStats().getHp());
        setTurn(false);
    }

    public static void decreaseHpPlayer(int damage, String type) {
        if (player.getPokemonInUse().getStats().getHp() - damage <= 0) {

        } else {
            float damageCalculated = typeEffects(damage, type, player);
            int dmg = (int) (player.getPokemonInUse().getStats().getHp() - damageCalculated);
            player.getPokemonInUse().getStats().setHp(dmg);
        }

        BattleFrame.updatePlayerHealthBar(player.getTeam().getPokemon(0).getStats().getHp());
        setTurn(true);
    }

    public static void whoStart() {
        int playerSpeed = player.getTeam().getPokemon(0).getStats().getSpeed();
        int npcSpeed = npc.getTeam().getPokemon(0).getStats().getSpeed();

        float playerSpeedPercent = (playerSpeed / 7) * 100;
        float npcSpeedPercent = (npcSpeed / 7) * 100;

        if (playerSpeedPercent >= npcSpeedPercent) {
            int damage = player.getTeam().getPokemon(0).getStats().getAttack();
            decreaseHpNpc(damage, player.getTeam().getPokemon(0).getAbilities().get(0).getTypo());
            setTurn(true);
        } else {
            setTurn(false);
            int damage = npc.getTeam().getPokemon(0).getStats().getAttack();
            decreaseHpPlayer(damage, npc.getTeam().getPokemon(0).getAbilities().get(0).getTypo());
        }
    }

    public static void setTurn(boolean turn) {
        BattleLogic.turn = turn;
        if (turn) {
            // Attiva i pulsanti delle abilità del giocatore
            for (Component component : BattleFrame.abilityPanel.getComponents()) {
                if (component instanceof JButton) {
                    JButton abilityButton = (JButton) component;
                    abilityButton.setEnabled(true);
                    abilityButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }
        } else {
            // Disattiva i pulsanti delle abilità del giocatore
            for (Component component : BattleFrame.abilityPanel.getComponents()) {
                if (component instanceof JButton) {
                    JButton abilityButton = (JButton) component;
                    abilityButton.setEnabled(false);
                    // Imposta l'opacità del pulsante
                    npcLogic();
                }
            }
        }
    }

    public static void npcLogic() {
        List<String> npcPokemonTypes = Arrays.asList(player.getPokemonInUse().getTypes());
        //List<String> playerPokemonTypes = Arrays.asList(npc.getPokemonInUse().getTypes());

        if (npcPokemonTypes.contains("normal")){
            
        } else if (npcPokemonTypes.contains("fighting")){

        } else if (npcPokemonTypes.contains("flying")){

        } else if (npcPokemonTypes.contains("poison")){

        } else if (npcPokemonTypes.contains("ground")){

        } else if (npcPokemonTypes.contains("rock")){

        } else if (npcPokemonTypes.contains("bug")){

        } else if (npcPokemonTypes.contains("ghost")){

        } else if (npcPokemonTypes.contains("steel")){

        } else if (npcPokemonTypes.contains("fire")){

        } else if (npcPokemonTypes.contains("water")){

        } else if (npcPokemonTypes.contains("grass")){

        } else if (npcPokemonTypes.contains("electric")){

        } else if (npcPokemonTypes.contains("psychic")){

        } else if (npcPokemonTypes.contains("ice")){

        } else if (npcPokemonTypes.contains("dragon")){

        } 

        if (npc.getTeam().getPokemon(0).getStats().getHp() <= 0) {
            
        }
        setTurn(true);
    }

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
            } else if (npcPokemonTypes.contains("rock")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("ice")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("bug")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("dragon")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("fire")) {
                return damage * 0.5f;
            } else {
                return damage;
            }
        } else if (typeAbility == "water") {
            if (npcPokemonTypes.contains("grass")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("fire")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("rock")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("ground")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("water")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("dragon")) {
                return damage * 0.5f;
            } else {
                return damage;
            }
        } else if (typeAbility == "grass") {
            if (npcPokemonTypes.contains("water")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("fire")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("rock")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("ground")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("grass")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("bug")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("dragon")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("poison")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("flying")) {
                return damage * 0.5f;
            } else {
                return damage;
            }
        } else if (typeAbility == "electric") {
            if (npcPokemonTypes.contains("water")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("electric")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("flying")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("dragon")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("grass")) {
                return damage * 0.5f;
            } else if (npcPokemonTypes.contains("ground")) {
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
            } else if (npcPokemonTypes.contains("bug")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("grass")) {
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
            } else if (npcPokemonTypes.contains("flying")) {
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
            } else if (npcPokemonTypes.contains("normal")) {
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
            } else if (npcPokemonTypes.contains("dragon")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("grass")) {
                return damage * 2;
            } else if (npcPokemonTypes.contains("ground")) {
                return damage * 2;
            } else {
                return damage;
            }
        } else {
            return damage;
        }

    }

}
