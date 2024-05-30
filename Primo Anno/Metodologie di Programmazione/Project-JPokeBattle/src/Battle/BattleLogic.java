package Battle;

import java.awt.Component;
import java.awt.Cursor;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

    // metodo utilizzato per inizializzare il pokemon iniziale da usare dei due
    // allenatori
    public static void setPokeInUseAtStart(int index) {
        player.getTeam().getPokemon(index).setInUse(true);
        npc.getTeam().getPokemon(index).setInUse(true);
    }

    // metodo utilizzato per decidere chi inizia per primo in base alla velocità dei
    // due pokemon in campo
    public static void whoStart() {
        int playerSpeed = player.getTeam().getPokemon(0).getStats().getSpeed();
        int npcSpeed = npc.getTeam().getPokemon(0).getStats().getSpeed();

        float playerSpeedPercent = (playerSpeed / 7) * 100;
        float npcSpeedPercent = (npcSpeed / 7) * 100;
        int damage = 0;
        if (playerSpeedPercent >= npcSpeedPercent) {
            damage = player.getPokemonInUse().getStats().getAttack();
            decreaseHpNpc(damage, player.getPokemonInUse().getAbilities().get(0).getTypo());
            setTurn(true);
        } else {
            damage = npc.getPokemonInUse().getStats().getAttack();
            decreaseHpPlayer(damage, npc.getPokemonInUse().getAbilities().get(0).getTypo());
            setTurn(false);
        }
    }

    // metodo utilizzato per calcolare i danni in base al tipo dell'abilità usata
    public static void decreaseHpNpc(int damage, String type) {
        float damageCalculated = typeEffects(damage, type, npc);
        int dmg = (int) (npc.getPokemonInUse().getStats().getHp() - damageCalculated);
        npc.getPokemonInUse().getStats().setHp(dmg);
        BattleFrame.updateNPCHealthBar(npc.getPokemonInUse().getStats().getHp());
        setTurn(false);
    }

    public static void decreaseHpPlayer(int damage, String type) {
        float damageCalculated = typeEffects(damage, type, player);
        int dmg = (int) (player.getPokemonInUse().getStats().getHp() - damageCalculated);
        player.getPokemonInUse().getStats().setHp(dmg);
        BattleFrame.updatePlayerHealthBar(player.getPokemonInUse().getStats().getHp());
        setTurn(true);
    }

    // metodo utilizzato per eseguire la logica del npc in base al turno
    static int count = 0;

public static void npcLogic() {
    // Verifica se il Pokémon in uso ha HP maggiori di 0
    if (npc.getPokemonInUse().getStats().getHp() > 0) {
        if (count != 5) {
            // Esegue una mossa e incrementa il contatore
            executeMove();
            count++;
        } else {
            // Prova a cambiare Pokémon
            boolean changedPokemon = changePokeNpc();
            try {
                BattleFrame.updatePokemonDisplayNpc();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!changedPokemon) {
                // Se non riesce a cambiare Pokémon, esegue una mossa
                executeMove();
            } else {
                // Se cambia Pokémon, imposta il turno
                setTurn(true);
            }
            // Resetta il contatore
            count = 0;
        }
    } else {
        // Cerca un Pokémon con HP maggiori di 0
        for (int i = 0; i < npc.getTeam().getListPokemon().size(); i++) {
            if (npc.getTeam().getPokemon(i).getStats().getHp() > 0) {
                npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                setTurn(true);
                break;
            }
        }
    }
}

    private static void executeMove() {
        Random randomMove = new Random();
        int moveInd = randomMove.nextInt(3) + 1;
        if (moveInd >= npc.getPokemonInUse().getAbilities().size()) {
            moveInd = 0; // Default to the first move if the generated index is out of bounds
        }
        int damage = npc.getPokemonInUse().getAbilities().get(moveInd).getStrength();
        decreaseHpPlayer(damage, npc.getPokemonInUse().getAbilities().get(moveInd).getTypo());
    }

    // metodo utilizzato per attivare/disattivare i pulsanti delle abilità in base
    // al turno
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
                    npcLogic(); // NPC logic method

                }
            }
        }
    }

    // metodo utilizzato per cambiare il pokemon del npc in base al tipo del pokemon
    // del giocatore
    private static Boolean changePokeNpc() {
        List<String> playerPokemonTypes = Arrays.asList(npc.getPokemonInUse().getTypes());
        for (int i = 0; i < npc.getTeam().getListPokemon().size(); i++) {
            List<String> npcPokemonTypes = Arrays.asList(npc.getTeam().getPokemon(i).getTypes());
            if (playerPokemonTypes.contains("normal")) {
                if (npcPokemonTypes.contains("fighting")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                }
            } else if (playerPokemonTypes.contains("fighting")) {
                if (npcPokemonTypes.contains("flying")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                }

            } else if (playerPokemonTypes.contains("flying")) {
                if (npcPokemonTypes.contains("electric")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                }

            } else if (playerPokemonTypes.contains("poison")) {
                if (npcPokemonTypes.contains("ground")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                }

            } else if (playerPokemonTypes.contains("ground")) {
                if (npcPokemonTypes.contains("water")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                }

            } else if (playerPokemonTypes.contains("rock")) {
                if (npcPokemonTypes.contains("fighting")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                }

            } else if (playerPokemonTypes.contains("bug")) {
                if (npcPokemonTypes.contains("fire")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                }

            } else if (playerPokemonTypes.contains("ghost")) {
                if (npcPokemonTypes.contains("ghost")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                }

            } else if (playerPokemonTypes.contains("steel")) {
                if (npcPokemonTypes.contains("fire")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                }

            } else if (playerPokemonTypes.contains("fire")) {
                if (npcPokemonTypes.contains("water")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                }

            } else if (playerPokemonTypes.contains("water")) {
                if (npcPokemonTypes.contains("electric")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                }

            } else if (playerPokemonTypes.contains("grass")) {
                if (npcPokemonTypes.contains("fire")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                }

            } else if (playerPokemonTypes.contains("electric")) {
                if (npcPokemonTypes.contains("ground")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                }

            } else if (playerPokemonTypes.contains("psychic")) {
                if (npcPokemonTypes.contains("bug")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                }

            } else if (playerPokemonTypes.contains("ice")) {
                if (npcPokemonTypes.contains("fire")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                }

            } else if (playerPokemonTypes.contains("dragon")) {
                if (npcPokemonTypes.contains("ice")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                }
            } 
        }
        return false;
    }

    // metodo utilizzato per calcolare i danni in base al tipo dell'abilità usata
    public static float typeEffects(int damageIn, String typeAbility, Coach enemy) {
        // in ingresso vuole: il danno fatto dall'abilità, il tipo di Coach
        // e il tipo dell'abilità
        List<String> npcPokemonTypes = Arrays.asList(enemy.getPokemonInUse().getTypes());
        int pokeDefense = enemy.getPokemonInUse().getStats().getDefense();
        int pokeLv = enemy.getPokemonInUse().getLvl();

        // calcolo del danno
        float damage = (float) (((2 * pokeLv + 10) / 50) * (damageIn) * (pokeDefense / 50) + 2);

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