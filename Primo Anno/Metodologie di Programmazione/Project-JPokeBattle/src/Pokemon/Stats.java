package Pokemon;

/**
    * Classe che rappresenta le statistiche dei pokemon
    * Ogni pokemon ha delle statistiche come hp, attacco, difesa e velocità
*/
public class Stats {
    private int hp;
    private int maxHp;
    private int attack;
    private int defense;
    private int speed;

    /**
     * Costruttore della classe Stats
     * @param attack attacco
     * @param defense difesa
     * @param speed velocità
     * @param levelIn livello 
     * @param maxHp hp massimi
     */
    public Stats(int attack, int defense, int speed, int levelIn, int maxHp) {
        if (hp < 0 || attack < 0 || defense < 0 || speed < 0) {
            throw new IllegalArgumentException("Stats cannot be negative");
        }
        if (levelIn >= 1 && levelIn < 10) {
            this.maxHp = maxHp;
            this.attack = attack;
            this.defense = defense;
            this.speed = speed;
        }
        if (levelIn >= 10 && levelIn < 17) {
            this.maxHp = maxHp + 50;
            this.attack = attack + 24;
            this.defense = defense + 12;
            this.speed = speed + 19;
        }
        if (levelIn >= 17 && levelIn <= 25) {
            this.maxHp = maxHp + 100;
            this.attack = attack + 48;
            this.defense = defense + 24;
            this.speed = speed + 38;
        }
        this.hp = this.maxHp;
    }

    /**
     * Metodo per ottenere gli hp di un pokemon
     * @return numero intero
     */

    public int getHp() {
        return hp;
    }

    /**
     * Metodo per ottenere il massimo degli hp di un pokemon
     * @return numero intero
     */

    public int getMaxHp() {
        return maxHp;
    }

    /**
     * Metodo per impostare il massimo degli hp di un pokemon
     * @param maxHp massimi hp del pokemon
     */

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    /**
     * Metodo per ottenere l'attacco di un pokemon
     * @return numero intero
     */

    public int getAttack() {
        return attack;
    }

    /**
     * Metodo per ottenere la difesa di un pokemon
     * @return numero intero
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Metodo per ottenere le statistiche di un pokemon
     * @return numero intero
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Metodo per impostare gli hp di un pokemon
     * @param hp hp del pokemon
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Metodo per ottenere l'attack di un pokemon
     * @param attack attacco del pokemon
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * Metodo per ottenere le statistiche di un pokemon
     * @param defense difesa del pokemon
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * Metodo per impostare la velocità di un pokemon
     * @param speed velocità del pokemon
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
