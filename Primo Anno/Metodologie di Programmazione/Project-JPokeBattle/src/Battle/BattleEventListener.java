package Battle;

/**
 * Interfaccia per la gestione degli eventi della battaglia
 */
public interface BattleEventListener {
    /**
     * Metodo per aggiornare la vita del giocatore e del nemico
     * @param dmg danno del pokemon
     * @param health vita del pokemon
     */
    void onPlayerHealthUpdated(int dmg, int health);

    /**
     * Metodo per aggiornare la vita del giocatore e del nemico
     */
    void onPokemonFainted();

    /**
     * Metodo per aggiornare la vita del giocatore e del nemico
     * @param dmg danno del pokemon
     */
    void onNpcHealthUpdated(int dmg);

    /**
     * Metodo per la fine della battaglia
     */
    void onBattleEnd();

    /**
     * Metodo per incrementare le statistiche del giocatore
     */
    void incrementStats();

    /**
     * Metodo per modificare la barra dell'esperienza
     * @param exp exp del giocatore
     */
    void updateExpBar(int exp);

    /**
     * Metodo per cambiare l'abilità del giocatore
     * @param indexPoke indice del pokemon
     */
    void AbilityNpc(int indexPoke);

    /**
     * Metodo per cambiare l'abilità del giocatore
     * @param indexPoke indice del pokemon
     */
    void AbilityPlayer(int indexPoke);
}