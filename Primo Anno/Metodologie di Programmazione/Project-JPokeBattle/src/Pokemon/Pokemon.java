package Pokemon;

import java.util.ArrayList;
/*
 * Questa classe rappresenta un pokemon con le sue caratteristiche
 * Ogni pokemon ha un nome, delle abilità, delle statistiche, dei tipi, un'esperienza base, uno sprite, un livello e delle evoluzioni
 */

public class Pokemon {
    private String name;
    private String start;
    private ArrayList<Ability> abilities;
    private Stats stats;
    private String[] types;
    private int baseExperience;
    private Sprites sprite;
    private int lvl;
    private int lvlEvoluzione;
    private Pokemon[] evolutions;
    private boolean inUse;

    public Pokemon(String name, ArrayList<Ability> abilities, String startIn, Stats stats, String[] types, int baseExperience, Sprites sprite, int lvl, int lvlEvoluzione, Pokemon[] evolutions, boolean inUse) {
        this.name = name;
        this.start = startIn;
        this.abilities = abilities;
        this.stats = stats;
        this.types = types;
        this.baseExperience = baseExperience;
        this.sprite = sprite;
        this.lvl = lvl;
        this.lvlEvoluzione = lvlEvoluzione;
        this.evolutions = evolutions;
        this.inUse = inUse;
    }

    public String getName() {
        return name;
    }

    public String getStart() {
        return start;
    }

    public ArrayList<Ability> getAbilities() {
        return abilities;
    }

    public int getLvlEvoluzione() {
        return lvlEvoluzione;
    }
    
    public void setLvlEvoluzione(int lvl) {
        this.lvlEvoluzione = lvl;
    }
    
    public void addAbility(Ability pokemon) {
        abilities.add(pokemon);
    }

    public void removeAbility(int index) {
        if (index >= 0 && index < abilities.size()) {
            abilities.remove(index);
        } else {
            System.out.println("Invalid index.");
        }
    }

    public Ability getAbility(int index) {
        if (index >= 0 && index < abilities.size()) {
            return abilities.get(index);
        } else {
            System.out.println("Invalid index.");
            return null;
        }
    }

    public void addAbilityAtIndex(Ability ability, int index) {
        if (index >= 0 && index < abilities.size()) {
            abilities.add(index, ability);
        } else {
            abilities.add(ability);
        }
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

    public Pokemon[] getEvolutions() {
        return evolutions;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

}
