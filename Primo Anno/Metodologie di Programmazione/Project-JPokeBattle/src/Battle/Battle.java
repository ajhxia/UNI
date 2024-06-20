package Battle;

import Pokemon.Pokemon;
import Pokemon.Types;

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
    private BattleEventListener eventListener;

    /**
     * Metodo per ottenere il turno
      
     */
    public static boolean isTurn() {
        return turn;
    }
    
    /**
     * Metodo per ottenere il giocatore
      
     */
    public static Coach getPlayer() {
        return player;
    }

    /**
     * Metodo per impostare il giocatore
     * @param playerIn
     */
    public static void setPlayer(Coach playerIn) {
        player = playerIn;
    }

    /**
     * Metodo per ottenere il npc
      
     */
    public static Coach getNpc() {
        return npc;
    }

    /**
     * Metodo per impostare il npc
     * @param npcIn
     */
    public static void setNpc(Coach npcIn) {
        npc = npcIn;
    }

    /**
     * Metodo per ottenere il pokemon in uso del giocatore
      
     */
    public Battle(BattleEventListener eventListener) {
        this.eventListener = eventListener;
    }

    /**
     * Metodo per ottenere il pokemon in uso del giocatore
     * @param index
     */
    public static void setPokeInUseAtStart(int index) {
        int indexPoke = index;
        while (player.getTeam().getPokemon(index).getStats().getHp() <= 0) {
            indexPoke++;
        }
        indexPoke = index;
        player.getTeam().getPokemon(index).setInUse(true);
        while (npc.getTeam().getPokemon(index).getStats().getHp() <= 0) {
            indexPoke++;
        }
        npc.getTeam().getPokemon(index).setInUse(true);
    }

    /**
     * Metodo per ottenere il pokemon in uso del giocatore
     */
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

    /**
     * Metodo per ottenere il pokemon in uso del giocatore
     */
    public static void addAbilityToPlayerPokemon() {
        for (int i = 0; i < player.getTeam(). getTeam().size(); i++) {
            player.getTeam(). getTeam().get(i).setAbility(
                    player.getTeam(). getTeam().get(i).getIndexInPokedex(),
                    player.getTeam(). getTeam().get(i).getLvl());
        }
    }

    /**
     * Metodo per ottenere il pokemon in uso del giocatore
     */
    public void decreaseHpNpc(int damage, Types type) {
        int damageCalculated = calculateDamage(damage, type, npc);
        int dmg = npc.getPokemonInUse().getStats().getHp() - damageCalculated;
        eventListener.onNpcHealthUpdated(damageCalculated);
        npc.getPokemonInUse().getStats().setHp(dmg);
    }

    /**
     * Metodo per ottenere il pokemon in uso del giocatore
     */
    public void decreaseHpPlayer(int damage, Types type) {
        int damageCalculated = calculateDamage(damage, type, player);
        int dmg = player.getPokemonInUse().getStats().getHp() - damageCalculated;
        player.getPokemonInUse().getStats().setHp(dmg);

        eventListener.onPlayerHealthUpdated(damageCalculated, dmg);

        if (player.getPokemonInUse().getStats().getHp() <= 0) {
            eventListener.onPokemonFainted();
        }

        boolean allPokemonHpZero = true;
        
        for (int i = 0; i < player.getTeam(). getTeam().size(); i++) {
            if (player.getTeam().getPokemon(i).getStats().getHp() > 0) {
                allPokemonHpZero = false;
            }
        }

        if (allPokemonHpZero) {
            GameOverScreen gameOverScreen = new GameOverScreen();
            gameOverScreen.setVisible(true);
        }
    }

    // metodo utilizzato per eseguire la logica del npc in base al turno
    static int count = 0;

    /**
     * Metodo per ottenere il pokemon in uso del giocatore
     */
    public void npcLogic() {
        Timer timer = new Timer(800, c -> {
            // Verifica se il Pokémon in uso ha HP maggiori di 0
            if (npc.getPokemonInUse().getStats().getHp() > 0) {
                if (count < 5) {
                    // Esegue una mossa e incrementa il contatore
                    eventListener.AbilityNpc(executeMove());
                    count++;
                } else {
                    // Prova a cambiare Pokémon
                    boolean changedPokemon = changePokeNpc();
                    if (!changedPokemon) {
                        // Se non riesce a cambiare Pokémon, esegue una mossa
                        eventListener.AbilityNpc(executeMove());
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
                for (int i = 0; i < npc.getTeam(). getTeam().size(); i++) {
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
                    for (int i = 0; i < player.getTeam(). getTeam().size(); i++) {
                        Random random = new Random();
                        int randomNum = random.nextInt(5) + 1;
                        player.getTeam().getPokemon(i).setLvl(player.getTeam().getPokemon(i).getLvl() + randomNum);
                    }
                    player.getPokemonInUse().setLvl(player.getPokemonInUse().getLvlEvoluzione());
                    try {
                    	new RecapBattle();
                        eventListener.onBattleEnd();
                        player.setGameWinned(player.getGameWinned() + 1);
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

    /**
     * Metodo per ottenere il pokemon in uso del giocatore
     */
    private void expChangePlayer() {
        int pokeNpcExp = npc.getPokemonInUse().getBaseExperience();
        int pokeNpcLv = npc.getPokemonInUse().getLvl();
        int totExpGain = (int) (pokeNpcExp * pokeNpcLv * 1.5 / (6 * 0.5)) * 7;
        player.getPokemonInUse().setBaseExperience(player.getPokemonInUse().getBaseExperience() + totExpGain);

        if (player.getPokemonInUse().getBaseExperience() >= player.getPokemonInUse().getMaxExperience()) {
            player.getPokemonInUse().setLvl(player.getPokemonInUse().getLvl() + 5);
            player.getPokemonInUse().setBaseExperience(0);
            eventListener.updateExpBar(player.getPokemonInUse().getBaseExperience());
            incrementStats();
        }
        eventListener.updateExpBar(player.getPokemonInUse().getBaseExperience());
    }

    /**
     * Metodo per ottenere il pokemon in uso del giocatore
     */
    public void incrementStats() {
        Pokemon pokemon = player.getPokemonInUse();
        pokemon.getStats().setAttack(pokemon.getStats().getAttack() + 30);
        pokemon.getStats().setDefense(pokemon.getStats().getDefense() + 25);
        pokemon.getStats().setSpeed(pokemon.getStats().getSpeed() + 50);
        pokemon.getStats().setMaxHp(pokemon.getStats().getMaxHp() + 23);
        pokemon.getStats().setHp(pokemon.getStats().getHp() + 14);
        eventListener.incrementStats();
    }

    /**
     * Metodo per ottenere il pokemon in uso del giocatore
     */
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

    /**
     * Metodo per ottenere il pokemon in uso del giocatore
     * @param turn
     */
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

    /**
     * Metodo per ottenere il pokemon in uso del giocatore
      
     */
    private static Boolean changePokeNpc() {
        System.out.println("Changing Pokemon");

        // crea una map ti tipi e debolezze per ogni tipo
        Map<Types, List<Types>> typeWeaknesses = new HashMap<>();
        typeWeaknesses.put(Types.NORMAL, Arrays.asList(Types.FIGHTING));
        typeWeaknesses.put(Types.FIGHTING, Arrays.asList(Types.FLYING, Types.PSYCHIC, Types.FAIRY));
        typeWeaknesses.put(Types.FLYING, Arrays.asList(Types.ELECTRIC, Types.ROCK, Types.ICE));
        typeWeaknesses.put(Types.POISON, Arrays.asList(Types.GROUND, Types.PSYCHIC));
        typeWeaknesses.put(Types.GROUND, Arrays.asList(Types.WATER, Types.GRASS, Types.ICE));
        typeWeaknesses.put(Types.ROCK, Arrays.asList(Types.FIGHTING, Types.GROUND, Types.STEEL, Types.WATER, Types.GRASS));
        typeWeaknesses.put(Types.BUG, Arrays.asList(Types.FIRE, Types.FLYING, Types.ROCK));
        typeWeaknesses.put(Types.GHOST, Arrays.asList(Types.GHOST, Types.DARK));
        typeWeaknesses.put(Types.STEEL, Arrays.asList(Types.FIRE, Types.FIGHTING, Types.GROUND));
        typeWeaknesses.put(Types.FIRE, Arrays.asList(Types.WATER, Types.ROCK, Types.GROUND));
        typeWeaknesses.put(Types.WATER, Arrays.asList(Types.ELECTRIC, Types.GRASS));
        typeWeaknesses.put(Types.GRASS, Arrays.asList(Types.FIRE, Types.FLYING, Types.BUG, Types.POISON, Types.ICE));
        typeWeaknesses.put(Types.ELECTRIC, Arrays.asList(Types.GROUND));
        typeWeaknesses.put(Types.PSYCHIC, Arrays.asList(Types.BUG, Types.GHOST, Types.DARK));
        typeWeaknesses.put(Types.ICE, Arrays.asList(Types.FIRE, Types.FIGHTING, Types.ROCK, Types.STEEL));
        typeWeaknesses.put(Types.DRAGON, Arrays.asList(Types.ICE, Types.DRAGON, Types.FAIRY));

        List<Types> playerPokemonTypes = Arrays.asList(npc.getPokemonInUse().getTypes());

        for (int i = 0; i < npc.getTeam(). getTeam().size(); i++) {
            List<Types> npcPokemonTypes = Arrays.asList(npc.getTeam().getPokemon(i).getTypes());

            for (Types playerType : playerPokemonTypes) {
                List<Types> weaknesses = typeWeaknesses.get(playerType);

                if (weaknesses != null) {
                    for (Types weakness : weaknesses) {
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

    /**
     * Metodo per ottenere il pokemon in uso del giocatore
     * @param damageIn
     * @param typeAbility
     * @param enemy
      
     */
    public static int calculateDamage(int damageIn, Types typeAbility, Coach enemy) {
        List<Types> npcPokemonTypes = Arrays.asList(enemy.getPokemonInUse().getTypes());
        float typeEffectiveness = 1;

        // Define type effectiveness map
        Map<Types, Map<Types, Float>> typeEffectivenessMap = new HashMap<>();
        typeEffectivenessMap.put(Types.FIRE, Map.of(
                Types.WATER, 0.5f, Types.GRASS, 2f, Types.ROCK, 0.5f, Types.ICE, 2f, Types.BUG, 2f, Types.DRAGON, 0.5f, Types.FIRE, 0.5f));
        typeEffectivenessMap.put(Types.WATER, Map.of(
                Types.GRASS, 0.5f, Types.FIRE, 2f, Types.ROCK, 2f, Types.GROUND, 2f, Types.WATER, 0.5f, Types.DRAGON, 0.5f));
        typeEffectivenessMap.put(Types.GRASS, Map.of(
                Types.WATER, 2f, Types.FIRE, 0.5f, Types.ROCK, 2f, Types.GROUND, 2f, Types.GRASS, 0.5f, Types.BUG, 0.5f, Types.DRAGON, 0.5f,
                Types.POISON, 0.5f, Types.FLYING, 0.5f));
        typeEffectivenessMap.put(Types.ELECTRIC, Map.of(
                Types.WATER, 2f, Types.ELECTRIC, 0.5f, Types.FLYING, 2f, Types.DRAGON, 0.5f, Types.GRASS, 0.5f, Types.GROUND, 0f));
        typeEffectivenessMap.put(Types.NORMAL, Map.of(
                Types.ROCK, 0.5f, Types.GHOST, 0f));
        typeEffectivenessMap.put(Types.FIGHTING, Map.of(
                Types.NORMAL, 2f, Types.ROCK, 2f, Types.ICE, 2f, Types.POISON, 0.5f, Types.FLYING, 0.5f, Types.PSYCHIC, 0.5f, Types.BUG, 0.5f,
                Types.GHOST, 0f));
        typeEffectivenessMap.put(Types.FLYING, Map.of(
                Types.FIGHTING, 2f, Types.ROCK, 0.5f, Types.ELECTRIC, 0.5f, Types.BUG, 2f, Types.GRASS, 2f));
        typeEffectivenessMap.put(Types.POISON, Map.of(
                Types.GRASS, 2f, Types.POISON, 0.5f, Types.GROUND, 0.5f, Types.ROCK, 0.5f, Types.GHOST, 0.5f, Types.BUG, 2f));
        typeEffectivenessMap.put(Types.GROUND, Map.of(
                Types.FIRE, 2f, Types.ELECTRIC, 2f, Types.POISON, 2f, Types.ROCK, 2f, Types.GRASS, 0.5f, Types.BUG, 0.5f, Types.FLYING, 0f));
        typeEffectivenessMap.put(Types.ROCK, Map.of(
                Types.FIRE, 2f, Types.ICE, 2f, Types.FIGHTING, 0.5f, Types.GROUND, 0.5f, Types.FLYING, 2f, Types.BUG, 2f));
        typeEffectivenessMap.put(Types.BUG, Map.of(
                Types.GRASS, 2f, Types.FIRE, 0.5f, Types.FIGHTING, 0.5f, Types.POISON, 0.5f, Types.FLYING, 0.5f, Types.PSYCHIC, 2f));
        typeEffectivenessMap.put(Types.GHOST, Map.of(
                Types.GHOST, 2f, Types.PSYCHIC, 0f, Types.NORMAL, 0f));
        typeEffectivenessMap.put(Types.DRAGON, Map.of(
                Types.DRAGON, 2f));
        typeEffectivenessMap.put(Types.PSYCHIC, Map.of(
                Types.FIGHTING, 2f, Types.POISON, 2f, Types.PSYCHIC, 0.5f));
        typeEffectivenessMap.put(Types.ICE, Map.of(
                Types.FLYING, 2f, Types.FIRE, 0.5f, Types.WATER, 0.5f, Types.ICE, 0.5f, Types.DRAGON, 2f, Types.GRASS, 2f, Types.GROUND, 2f));

        // Get type effectiveness based on typeAbility and npcPokemonTypes
        if (typeEffectivenessMap.containsKey(typeAbility)) {
            for (Types npcType : npcPokemonTypes) {
                typeEffectiveness *= typeEffectivenessMap.get(typeAbility).getOrDefault(npcType, 1f);
            }    
        }

        float pokeDefense = (float) enemy.getPokemonInUse().getStats().getDefense();
        float pokeLv = (float) enemy.getPokemonInUse().getLvl();
        float pokeAttack = (float) player.getPokemonInUse().getStats().getAttack();

        int random = new Random().nextInt(39) + 217; // Random number between 217 and 255

        int damage = (int) (((((2f * pokeLv + 10f) / 5f) * (damageIn * pokeAttack / pokeDefense)) / 50f + 2f)
                * typeEffectiveness * random / 125f);
        return damage;
    }

}