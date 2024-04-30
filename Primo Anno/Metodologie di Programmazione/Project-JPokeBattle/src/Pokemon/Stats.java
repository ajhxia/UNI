package Pokemon;
public class Stats {
    private String hp;
    private String attack;
    private String defense;
    private String speed;

    public Stats(String hp, String attack, String defense, String speed) {
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
    }

    public String getHp() {
        return hp;
    }

    public String getAttack() {
        return attack;
    }

    public String getDefense() {
        return defense;
    }

    public String getSpeed() {
        return speed;
    }
}
