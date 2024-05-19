package Battle;

import Game.Coach;

public class BattleLogic {

    public static void decreaseHpNpc(Coach npc, int damage){
        if (npc.getTeam().getPokemon(0).getStats().getHp() - damage <= 0){
            npc.getTeam().getPokemon(0).getStats().setHp(0);
        } else {
            npc.getTeam().getPokemon(0).getStats().setHp(npc.getTeam().getPokemon(0).getStats().getHp() - damage);
        }

        BattleFrame.updateNPCHealthBar(npc.getTeam().getPokemon(0).getStats().getHp());
    }

    public static void decreaseHpPlayer(Coach player, int damage){
        if (player.getTeam().getPokemon(0).getStats().getHp() - damage <= 0){
            player.getTeam().getPokemon(0).getStats().setHp(0);
        } else {
            player.getTeam().getPokemon(0).getStats().setHp(player.getTeam().getPokemon(0).getStats().getHp() - damage);
        }

        BattleFrame.updatePlayerHealthBar(player.getTeam().getPokemon(0).getStats().getHp());
    }


}
