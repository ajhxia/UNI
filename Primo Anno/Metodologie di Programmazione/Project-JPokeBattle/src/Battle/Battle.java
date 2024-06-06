package Battle;

import Generic.*;
import java.awt.Component;
import java.awt.Cursor;
import java.io.IOException;
import java.util.*;

import javax.swing.JButton;
import javax.swing.Timer;

import Game.Coach;

public class Battle {
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

        if (playerSpeedPercent >= npcSpeedPercent) {
            setTurn(true);
        } else {
            setTurn(false);
        }
    }

    // metodo utilizzato per calcolare i danni in base al tipo dell'abilità usata
    public static void decreaseHpNpc(int damage, String type) {
        float damageCalculated = typeEffects(damage, type, npc);
        int dmg = (int) (npc.getPokemonInUse().getStats().getHp() - damageCalculated);
        npc.getPokemonInUse().getStats().setHp(dmg);
        BattleFrame.updateNPCHealthBar(npc.getPokemonInUse().getStats().getHp());
    }

    public static void decreaseHpPlayer(int damage, String type) {
        float damageCalculated = typeEffects(damage, type, player);
        int dmg = (int) (player.getPokemonInUse().getStats().getHp() - damageCalculated);
        player.getPokemonInUse().getStats().setHp(dmg);
        BattleFrame.updatePlayerHealthBar(player.getPokemonInUse().getStats().getHp());
        if (player.getPokemonInUse().getStats().getHp() <= 0) {
            new ChangePokemonFrame(InfoRecap.battleFrame);
        }
    }

    // metodo utilizzato per eseguire la logica del npc in base al turno
    static int count = 0;

    public static void npcLogic() {
        Timer timer = new Timer(800, c -> {
            // Verifica se il Pokémon in uso ha HP maggiori di 0
            if (npc.getPokemonInUse().getStats().getHp() > 0) {
                if (count < 5) {
                    // Esegue una mossa e incrementa il contatore
                    BattleFrame.showMessageAbilityNpc(executeMove());
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
                        BattleFrame.showMessageAbilityNpc(executeMove());
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
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        allPokemonHpZero = false;
                        expChangePlayer();
                        break;
                    }
                }

                BattleFrame.updatePokeballStatusNpc(npc);

                if (allPokemonHpZero) {
                    InfoRecap.battleFrame.setVisible(false);
                    InfoRecap.battleFrame.dispose();
                    new RecapBattle();
                }
            }
            setTurn(true);
        });
        timer.setRepeats(false);
        timer.start();
    }

    private static void expChangePlayer() {
        int pokeNpcExp = npc.getPokemonInUse().getBaseExperience();
        int pokeNpcLv = npc.getPokemonInUse().getLvl();
        int totExpGain = (int) (pokeNpcExp * pokeNpcLv * 1.5 / (6 * 0.5)) * 9;
        player.getPokemonInUse().setBaseExperience(player.getPokemonInUse().getBaseExperience() + totExpGain);

        if (player.getPokemonInUse().getBaseExperience() == player.getPokemonInUse().getMaxExperience()) {
            player.getPokemonInUse().setLvl(player.getPokemonInUse().getLvl() + 1);
            player.getPokemonInUse().setBaseExperience(0);
            BattleFrame.updatePlayerExpBar(player.getPokemonInUse().getBaseExperience());
            
            BattleFrame.showIncrementStats();

            BattleFrame.lvlPlayer.setText(String.valueOf(player.getPokemonInUse().getLvl()));
        }
        BattleFrame.updatePlayerExpBar(player.getPokemonInUse().getBaseExperience());
    }

    private static int executeMove() {
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
    public static void setTurn(boolean turn) {
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
                } else if (npcPokemonTypes.contains("psychic")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                } else if (npcPokemonTypes.contains("fairy")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                }

            } else if (playerPokemonTypes.contains("flying")) {
                if (npcPokemonTypes.contains("electric")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                } else if (npcPokemonTypes.contains("fighting")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                } else if (npcPokemonTypes.contains("rock")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                }

            } else if (playerPokemonTypes.contains("poison")) {
                if (npcPokemonTypes.contains("ground")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                } else if (npcPokemonTypes.contains("psychic")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                }

            } else if (playerPokemonTypes.contains("ground")) {
                if (npcPokemonTypes.contains("water")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                } else if (npcPokemonTypes.contains("grass")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                } else if (npcPokemonTypes.contains("ice")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                }

            } else if (playerPokemonTypes.contains("rock")) {
                if (npcPokemonTypes.contains("fighting")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                } else if (npcPokemonTypes.contains("ground")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                } else if (npcPokemonTypes.contains("steel")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                } else if (npcPokemonTypes.contains("water")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                } else if (npcPokemonTypes.contains("grass")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                }

            } else if (playerPokemonTypes.contains("bug")) {
                if (npcPokemonTypes.contains("fire")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                } else if (npcPokemonTypes.contains("flying")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                } else if (npcPokemonTypes.contains("rock")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                }

            } else if (playerPokemonTypes.contains("ghost")) {
                if (npcPokemonTypes.contains("ghost")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                } else if (npcPokemonTypes.contains("psychic")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                }

            } else if (playerPokemonTypes.contains("steel")) {
                if (npcPokemonTypes.contains("fire")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                } else if (npcPokemonTypes.contains("fighting")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                } else if (npcPokemonTypes.contains("ground")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                }

            } else if (playerPokemonTypes.contains("fire")) {
                if (npcPokemonTypes.contains("water")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                } else if (npcPokemonTypes.contains("rock")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                } else if (npcPokemonTypes.contains("ground")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                }

            } else if (playerPokemonTypes.contains("water")) {
                if (npcPokemonTypes.contains("electric")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                } else if (npcPokemonTypes.contains("grass")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                }

            } else if (playerPokemonTypes.contains("grass")) {
                if (npcPokemonTypes.contains("fire")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                } else if (npcPokemonTypes.contains("flying")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                } else if (npcPokemonTypes.contains("bug")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                } else if (npcPokemonTypes.contains("poison")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                } else if (npcPokemonTypes.contains("ice")) {
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
                } else if (npcPokemonTypes.contains("ghost")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                }

            } else if (playerPokemonTypes.contains("ice")) {
                if (npcPokemonTypes.contains("fire")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                } else if (npcPokemonTypes.contains("fighting")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                } else if (npcPokemonTypes.contains("rock")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                } else if (npcPokemonTypes.contains("steel")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                }

            } else if (playerPokemonTypes.contains("dragon")) {
                if (npcPokemonTypes.contains("ice")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                } else if (npcPokemonTypes.contains("dragon")) {
                    npc.setPokemonInUse(npc.getTeam().getPokemon(i));
                    return true;
                } else if (npcPokemonTypes.contains("fairy")) {
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
        float typeEffectiveness = 1;

        if (typeAbility == "fire") {
            if (npcPokemonTypes.contains("water")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("grass")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("rock")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("ice")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("bug")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("dragon")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("fire")) {
                typeEffectiveness = 0.5f;
            }
        } else if (typeAbility == "water") {
            if (npcPokemonTypes.contains("grass")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("fire")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("rock")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("ground")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("water")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("dragon")) {
                typeEffectiveness = 0.5f;
            }
        } else if (typeAbility == "grass") {
            if (npcPokemonTypes.contains("water")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("fire")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("rock")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("ground")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("grass")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("bug")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("dragon")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("poison")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("flying")) {
                typeEffectiveness = 0.5f;
            }
        } else if (typeAbility == "electric") {
            if (npcPokemonTypes.contains("water")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("electric")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("flying")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("dragon")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("grass")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("ground")) {
                typeEffectiveness = 0;
            }
        } else if (typeAbility == "normal") {
            if (npcPokemonTypes.contains("rock")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("ghost")) {
                typeEffectiveness = 0;
            }
        } else if (typeAbility == "fighting") {
            if (npcPokemonTypes.contains("normal")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("rock")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("ice")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("poison")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("flying")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("psychic")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("bug")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("ghost")) {
                typeEffectiveness = 0;
            }
        } else if (typeAbility == "flying") {
            if (npcPokemonTypes.contains("fighting")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("rock")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("electric")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("bug")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("grass")) {
                typeEffectiveness = 2;
            }
        } else if (typeAbility == "poison") {
            if (npcPokemonTypes.contains("grass")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("poison")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("ground")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("rock")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("ghost")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("bug")) {
                typeEffectiveness = 2;
            }
        } else if (typeAbility == "ground") {
            if (npcPokemonTypes.contains("fire")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("electric")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("poison")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("rock")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("grass")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("bug")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("flying")) {
                typeEffectiveness = 0;
            }
        } else if (typeAbility == "rock") {
            if (npcPokemonTypes.contains("fire")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("ice")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("fighting")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("ground")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("flying")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("bug")) {
                typeEffectiveness = 2;
            }
        } else if (typeAbility == "bug") {
            if (npcPokemonTypes.contains("grass")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("fire")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("fighting")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("poison")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("flying")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("psychic")) {
                typeEffectiveness = 2;
            }
        } else if (typeAbility == "ghost") {
            if (npcPokemonTypes.contains("ghost")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("psychic")) {
                typeEffectiveness = 0;
            } else if (npcPokemonTypes.contains("normal")) {
                typeEffectiveness = 0;
            }
        } else if (typeAbility == "dragon") {
            if (npcPokemonTypes.contains("dragon")) {
                typeEffectiveness = 2;
            }
        } else if (typeAbility == "psychic") {
            if (npcPokemonTypes.contains("fighting")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("poison")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("psychic")) {
                typeEffectiveness = 0.5f;
            }
        } else if (typeAbility == "ice") {
            if (npcPokemonTypes.contains("flying")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("fire")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("water")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("ice")) {
                typeEffectiveness = 0.5f;
            } else if (npcPokemonTypes.contains("dragon")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("grass")) {
                typeEffectiveness = 2;
            } else if (npcPokemonTypes.contains("ground")) {
                typeEffectiveness = 2;
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