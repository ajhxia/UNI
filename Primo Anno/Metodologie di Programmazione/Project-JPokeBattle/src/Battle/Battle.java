package Battle;

import Pokemon.Pokemon;

import java.awt.Component;
import java.awt.Cursor;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import javax.swing.JButton;
import javax.swing.Timer;

import Game.Coach;

public class Battle {
    private static Coach player;
    private static Coach npc;
    private static boolean turn;
    private BattleEventListener battleEventListener;

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

    public Battle(BattleEventListener battleEventListener) {
        this.battleEventListener = battleEventListener;
    }

    // metodo utilizzato per inizializzare il pokemon iniziale da usare dei due
    // allenatori
    public static void setPokeInUseAtStart(int index) {
        player.getTeam().getPokemon(index).setInUse(true);
        npc.getTeam().getPokemon(index).setInUse(true);
    }

    // metodo utilizzato per decidere chi inizia per primo in base alla velocità dei
    // due pokemon in campo
    public void whoStart() {
        int playerSpeed = player.getTeam().getPokemon(0).getStats().getSpeed();
        int npcSpeed = npc.getTeam().getPokemon(0).getStats().getSpeed();

        float playerSpeedPercent = (playerSpeed / 7) * 100;
        float npcSpeedPercent = (npcSpeed / 7) * 100;

        if (playerSpeedPercent >= npcSpeedPercent) {
            setTurn(true);
        } else {
            setTurn(false);
        }
    }

    public static void addAbilityToPlayerPokemon() {
        for (int i = 0; i < player.getTeam().getListPokemon().size(); i++) {
            player.getTeam().getListPokemon().get(i).setAbility(
                    player.getTeam().getListPokemon().get(i).getIndexInPokedex(),
                    player.getTeam().getListPokemon().get(i).getLvl());
        }
    }

    // metodo utilizzato per calcolare i danni in base al tipo dell'abilità usata
    public void decreaseHpNpc(int damage, String type) {
        float damageCalculated = calculateDamage(damage, type, npc);
        int dmg = (int) (npc.getPokemonInUse().getStats().getHp() - damageCalculated);
        npc.getPokemonInUse().getStats().setHp(dmg);
        battleEventListener.onNpcHealthUpdated();
    }

    public void decreaseHpPlayer(int damage, String type) {
        float damageCalculated = calculateDamage(damage, type, player);
        int dmg = (int) (player.getPokemonInUse().getStats().getHp() - damageCalculated);
        player.getPokemonInUse().getStats().setHp(dmg);

        battleEventListener.onPlayerHealthUpdated();

        if (player.getPokemonInUse().getStats().getHp() <= 0) {
            battleEventListener.onPokemonFainted();
        }
    }

    // metodo utilizzato per eseguire la logica del npc in base al turno
    static int count = 0;

    public void npcLogic() {
        Timer timer = new Timer(800, c -> {
            // Verifica se il Pokémon in uso ha HP maggiori di 0
            if (npc.getPokemonInUse().getStats().getHp() > 0) {
                if (count < 5) {
                    // Esegue una mossa e incrementa il contatore
                    battleEventListener.AbilityNpc(executeMove());
                    count++;
                } else {
                    // Prova a cambiare Pokémon
                    boolean changedPokemon = changePokeNpc();
                    if (!changedPokemon) {
                        // Se non riesce a cambiare Pokémon, esegue una mossa
                        battleEventListener.AbilityNpc(executeMove());
                    } else {
                        try {
                            BattleFrame.updatePokemonDisplayNpc();
                        } catch (IOException | URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                    // Resetta il contatore
                    count = 0;
                }
            } else {
                boolean allPokemonHpZero = true;
                for (int i = 0; i < npc.getTeam().getListPokemon().size(); i++) {
                    if (npc.getTeam().getPokemon(i).getStats().getHp() > 0) {
                        npc.setPokemonInUse(npc.getTeam().getPokemon(i));

                        try {
                            BattleFrame.updatePokemonDisplayNpc();
                        } catch (IOException | URISyntaxException e) {
                            e.printStackTrace();
                        }
                        allPokemonHpZero = false;
                        expChangePlayer();
                        break;
                    }
                }

                BattleFrame.updatePokeballStatusNpc(npc);

                if (allPokemonHpZero) {
                    for (int i = 0; i < player.getTeam().getListPokemon().size(); i++) {
                        Random random = new Random();
                        int randomNum = random.nextInt(5) + 1;
                        player.getTeam().getPokemon(i).setLvl(player.getTeam().getPokemon(i).getLvl() + randomNum);
                    }
                    player.getPokemonInUse().setLvl(player.getPokemonInUse().getLvlEvoluzione());
                    try {
                        battleEventListener.onBattleEnd();
                        new RecapBattle();
                    } catch (IOException | URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            }
            setTurn(true);
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void expChangePlayer() {
        int pokeNpcExp = npc.getPokemonInUse().getBaseExperience();
        int pokeNpcLv = npc.getPokemonInUse().getLvl();
        int totExpGain = (int) (pokeNpcExp * pokeNpcLv * 1.5 / (6 * 0.5)) * 7;
        player.getPokemonInUse().setBaseExperience(player.getPokemonInUse().getBaseExperience() + totExpGain);

        if (player.getPokemonInUse().getBaseExperience() >= player.getPokemonInUse().getMaxExperience()) {
            player.getPokemonInUse().setLvl(player.getPokemonInUse().getLvl() + 5);
            player.getPokemonInUse().setBaseExperience(0);
            battleEventListener.updateExpBar(player.getPokemonInUse().getBaseExperience());
            incrementStats();

        }
        battleEventListener.updateExpBar(player.getPokemonInUse().getBaseExperience());
    }

    public void incrementStats() {
        Pokemon pokemon = player.getPokemonInUse();
        pokemon.getStats().setAttack(pokemon.getStats().getAttack() + 30);
        pokemon.getStats().setDefense(pokemon.getStats().getDefense() + 25);
        pokemon.getStats().setSpeed(pokemon.getStats().getSpeed() + 50);
        pokemon.getStats().setMaxHp(pokemon.getStats().getMaxHp() + 23);
        pokemon.getStats().setHp(pokemon.getStats().getHp() + 14);
        battleEventListener.incrementStats();
    }

    private int executeMove() {
        Random randomMove = new Random();
        int moveInd = randomMove.nextInt(3) + 1;
        if (moveInd >= npc.getPokemonInUse().getAbilities().size()) {
            moveInd = 0; // Default to the first move if the generated index is out of bounds
        }
        int damage = npc.getPokemonInUse().getAbilities().get(moveInd).getStrength();
        decreaseHpPlayer(damage, npc.getPokemonInUse().getAbilities().get(moveInd).getTypo());
        return moveInd;
    }

    // metodo utilizzato per attivare/disattivare i pulsanti delle abilità in base
    // al turno
    public void setTurn(boolean turn) {
        Battle.turn = turn;
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
            System.out.println("NPC Turn");
            // Disattiva i pulsanti delle abilità del giocatore
            for (Component component : BattleFrame.abilityPanel.getComponents()) {
                if (component instanceof JButton) {
                    JButton abilityButton = (JButton) component;
                    abilityButton.setEnabled(false);
                    abilityButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }
            Timer timer = new Timer(1500, e -> npcLogic());
            timer.setRepeats(false);
            timer.start();
        }
    }

    // metodo utilizzato per cambiare il pokemon del npc in base al tipo del pokemon
    // del giocatore
    private static Boolean changePokeNpc() {
        System.out.println("Changing Pokemon");

        // crea una map ti tipi e debolezze per ogni tipo
        Map<String, List<String>> typeWeaknesses = new HashMap<>();
        typeWeaknesses.put("normal", Arrays.asList("fighting"));
        typeWeaknesses.put("fighting", Arrays.asList("flying", "psychic", "fairy"));
        typeWeaknesses.put("flying", Arrays.asList("electric", "rock", "ice"));
        typeWeaknesses.put("poison", Arrays.asList("ground", "psychic"));
        typeWeaknesses.put("ground", Arrays.asList("water", "grass", "ice"));
        typeWeaknesses.put("rock", Arrays.asList("fighting", "ground", "steel", "water", "grass"));
        typeWeaknesses.put("bug", Arrays.asList("fire", "flying", "rock"));
        typeWeaknesses.put("ghost", Arrays.asList("ghost", "dark"));
        typeWeaknesses.put("steel", Arrays.asList("fire", "fighting", "ground"));
        typeWeaknesses.put("fire", Arrays.asList("water", "rock", "ground"));
        typeWeaknesses.put("water", Arrays.asList("electric", "grass"));
        typeWeaknesses.put("grass", Arrays.asList("fire", "flying", "bug", "poison", "ice"));
        typeWeaknesses.put("electric", Arrays.asList("ground"));
        typeWeaknesses.put("psychic", Arrays.asList("bug", "ghost", "dark"));
        typeWeaknesses.put("ice", Arrays.asList("fire", "fighting", "rock", "steel"));
        typeWeaknesses.put("dragon", Arrays.asList("ice", "dragon", "fairy"));

        List<String> playerPokemonTypes = Arrays.asList(npc.getPokemonInUse().getTypes());

        for (int i = 0; i < npc.getTeam().getListPokemon().size(); i++) {
            List<String> npcPokemonTypes = Arrays.asList(npc.getTeam().getPokemon(i).getTypes());

            for (String playerType : playerPokemonTypes) {
                List<String> weaknesses = typeWeaknesses.get(playerType);

                if (weaknesses != null) {
                    for (String weakness : weaknesses) {
                        if (npcPokemonTypes.contains(weakness)) {
                            if (npc.getTeam().getPokemon(i).getStats().getHp() > 0
                                    && npc.getPokemonInUse().getName() != npc.getTeam().getPokemon(i).getName()) {
                                npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    // metodo utilizzato per calcolare i danni in base al tipo dell'abilità usata
    public static float calculateDamage(int damageIn, String typeAbility, Coach enemy) {
        List<String> npcPokemonTypes = Arrays.asList(enemy.getPokemonInUse().getTypes());
        float typeEffectiveness = 1;

        // Define type effectiveness map
        Map<String, Map<String, Float>> typeEffectivenessMap = new HashMap<>();
        typeEffectivenessMap.put("fire", Map.of(
                "water", 0.5f, "grass", 2f, "rock", 0.5f, "ice", 2f, "bug", 2f, "dragon", 0.5f, "fire", 0.5f));
        typeEffectivenessMap.put("water", Map.of(
                "grass", 0.5f, "fire", 2f, "rock", 2f, "ground", 2f, "water", 0.5f, "dragon", 0.5f));
        typeEffectivenessMap.put("grass", Map.of(
                "water", 2f, "fire", 0.5f, "rock", 2f, "ground", 2f, "grass", 0.5f, "bug", 0.5f, "dragon", 0.5f,
                "poison", 0.5f, "flying", 0.5f));
        typeEffectivenessMap.put("electric", Map.of(
                "water", 2f, "electric", 0.5f, "flying", 2f, "dragon", 0.5f, "grass", 0.5f, "ground", 0f));
        typeEffectivenessMap.put("normal", Map.of(
                "rock", 0.5f, "ghost", 0f));
        typeEffectivenessMap.put("fighting", Map.of(
                "normal", 2f, "rock", 2f, "ice", 2f, "poison", 0.5f, "flying", 0.5f, "psychic", 0.5f, "bug", 0.5f,
                "ghost", 0f));
        typeEffectivenessMap.put("flying", Map.of(
                "fighting", 2f, "rock", 0.5f, "electric", 0.5f, "bug", 2f, "grass", 2f));
        typeEffectivenessMap.put("poison", Map.of(
                "grass", 2f, "poison", 0.5f, "ground", 0.5f, "rock", 0.5f, "ghost", 0.5f, "bug", 2f));
        typeEffectivenessMap.put("ground", Map.of(
                "fire", 2f, "electric", 2f, "poison", 2f, "rock", 2f, "grass", 0.5f, "bug", 0.5f, "flying", 0f));
        typeEffectivenessMap.put("rock", Map.of(
                "fire", 2f, "ice", 2f, "fighting", 0.5f, "ground", 0.5f, "flying", 2f, "bug", 2f));
        typeEffectivenessMap.put("bug", Map.of(
                "grass", 2f, "fire", 0.5f, "fighting", 0.5f, "poison", 0.5f, "flying", 0.5f, "psychic", 2f));
        typeEffectivenessMap.put("ghost", Map.of(
                "ghost", 2f, "psychic", 0f, "normal", 0f));
        typeEffectivenessMap.put("dragon", Map.of(
                "dragon", 2f));
        typeEffectivenessMap.put("psychic", Map.of(
                "fighting", 2f, "poison", 2f, "psychic", 0.5f));
        typeEffectivenessMap.put("ice", Map.of(
                "flying", 2f, "fire", 0.5f, "water", 0.5f, "ice", 0.5f, "dragon", 2f, "grass", 2f, "ground", 2f));

        // Get type effectiveness based on typeAbility and npcPokemonTypes
        if (typeEffectivenessMap.containsKey(typeAbility)) {
            for (String npcType : npcPokemonTypes) {
                typeEffectiveness *= typeEffectivenessMap.get(typeAbility).getOrDefault(npcType, 1f);
            }
        }

        float pokeDefense = (float) enemy.getPokemonInUse().getStats().getDefense();
        float pokeLv = (float) enemy.getPokemonInUse().getLvl();
        float pokeAttack = (float) player.getPokemonInUse().getStats().getAttack();

        int random = new Random().nextInt(39) + 217; // Random number between 217 and 255

        float damage = ((((2f * pokeLv + 10f) / 5f) * (damageIn * pokeAttack / pokeDefense)) / 50f + 2f)
                * typeEffectiveness * random / 125f;
        return damage;
    }

}