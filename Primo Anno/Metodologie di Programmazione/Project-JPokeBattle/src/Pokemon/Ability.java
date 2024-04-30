package Pokemon;

public class Ability {
    private String name;
    private String typo;
    private int strength;

    public Ability(String name, String typo, int strength) {
        this.name = name;
        this.typo = typo;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public String getTypo() {
        return typo;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTypo(String typo) {
        this.typo = typo;
    }

}
