package Pokemon;

/*
    * Classe che rappresenta le statistiche dei pokemon
    * Ogni pokemon ha delle statistiche come hp, attacco, difesa e velocità
*/

public class Stats {
    private int hp;
    private int maxHp;
    private int attack;
    private int defense;
    private int speed;

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

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
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

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void increaseHp(int hp) {
        this.hp += hp;
    }

    public void increaseAttack(int attack) {
        this.attack += attack;
    }

    public void increaseDefense(int defense) {
        this.defense += defense;
    }

    public void increaseSpeed(int speed) {
        this.speed += speed;
    }

    public void decreaseHp(int hp) {
        this.hp -= hp;
    }

    public void decreaseAttack(int attack) {
        this.attack -= attack;
    }

    public void decreaseDefense(int defense) {
        this.defense -= defense;
    }

    public void decreaseSpeed(int speed) {
        this.speed -= speed;
    }

}
