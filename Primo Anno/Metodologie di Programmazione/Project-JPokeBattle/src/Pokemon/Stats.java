package Pokemon;

/*
    * Classe che rappresenta le statistiche dei pokemon
    * Ogni pokemon ha delle statistiche come hp, attacco, difesa e velocità
*/

public class Stats {
    private int hp;
    private int attack;
    private int defense;
    private int speed;

    public Stats(int hp, int attack, int defense, int speed, int levelIn) {
        if (hp < 0 || attack < 0 || defense < 0 || speed < 0) {
            throw new IllegalArgumentException("Stats cannot be negative");
        }
        if (levelIn > 1 && levelIn < 10) {
            this.hp = hp;
            this.attack = attack;
            this.defense = defense;
            this.speed = speed;
        }
        if (levelIn >= 10 && levelIn < 17) {
            this.hp = hp + 50;
            this.attack = attack + 24;
            this.defense = defense + 12;
            this.speed = speed + 19;
        }
        if (levelIn >= 17 && levelIn <= 25) {
            this.hp = hp + 100;
            this.attack = attack + 48;
            this.defense = defense + 24;
            this.speed = speed + 38;
        }
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

}
