package Pokemon;

import java.util.ArrayList;

/*
 * Questa classe rappresenta un pokemon con le sue caratteristiche
 * Ogni pokemon ha un nome, delle abilità, delle statistiche, dei tipi, un'esperienza base, uno sprite, un livello e delle evoluzioni
 */

public class Pokemon {
    private String name;
    private ArrayList<Ability> abilities;
    private Stats stats;
    private String[] types;
    private int baseExperience;
    private Sprites sprite;
    private int lvl;
    private Pokemon[] evolutions;

    public Pokemon(String name, ArrayList<Ability> abilities, Stats stats, String[] types, int baseExperience, Sprites sprite, int lvl, Pokemon[] evolutions) {
        this.name = name;
        this.abilities = abilities;
        this.stats = stats;
        this.types = types;
        this.baseExperience = baseExperience;
        this.sprite = sprite;
        this.lvl = lvl;
        this.evolutions = evolutions;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Ability> getAbilities() {
        return abilities;
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


}
