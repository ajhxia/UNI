package Pokemon;

public class Pokemon {
    private String name;
    private Ability[] abilities;
    private Stats stats;
    private String[] types;
    private int baseExperience;
    private Sprites sprite;
    private int lvl;

    public Pokemon(String name, Ability[] abilities, Stats stats, String[] types, int baseExperience, Sprites sprite, int lvl) {
        this.name = name;
        this.abilities = abilities;
        this.stats = stats;
        this.types = types;
        this.baseExperience = baseExperience;
        this.sprite = sprite;
        this.lvl = lvl;
    }

    public String getName() {
        return name;
    }

    public Ability[] getAbilities() {
        return abilities;
    }

    public Stats getStats() {
        return stats;
    }

    public String[] getTypes() {
        return types;
    }
    
    public int getLvl() {
        return lvl;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public Sprites getSprite() {
        return sprite;
    }


}
