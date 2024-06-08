package Battle;

public interface BattleEventListener {
    void onPlayerHealthUpdated();
    void onPokemonFainted();
    void onNpcHealthUpdated();
    void onBattleEnd();
    void incrementStats();
    void updateExpBar(int exp);
    void AbilityNpc(int indexPoke);
    void AbilityPlayer(int indexPoke);
    
}