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
    private int[] evolutions;
    private boolean inUse;
    private int indexInPokedex;

    public Pokemon(String name, int indexInPokedex, ArrayList<Ability> abilities, String startIn, Stats stats, String[] types, int baseExperience, Sprites sprite, int lvl, int lvlEvoluzione, int[] evolutions, boolean inUse) {
        this.name = name;
        this.indexInPokedex = indexInPokedex;
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

    public int getIndexInPokedex() {
        return indexInPokedex;
    }

    public int getMaxExperience() {
        if (1<=lvl && lvl<=10) {
            return 1000;
        } else if (11<=lvl && lvl<=20) {
            return 1500;
        } else if (21<=lvl && lvl<=30) {
            return 2000;
        } else if (31<=lvl && lvl<=40) {
            return 2500;
        } else if (41<=lvl && lvl<=50) {
            return 3000;
        } else if (51<=lvl && lvl<=60) {
            return 3500;
        } else if (61<=lvl && lvl<=70) {
            return 4000;
        } else if (71<=lvl && lvl<=80) {
            return 4500;
        } else if (81<=lvl && lvl<=90) {
            return 5000;
        } else if (91<=lvl && lvl<=100) {
            return 5500;
        } else {
            return 0;
        }
    }

    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
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

    public void setAbility(int index, int lvl) {
        this.abilities = Ability.getAbility(index, lvl);
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

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public Sprites getSprite() {
        return sprite;
    }

    public int[] getEvolutions() {
        return evolutions;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

}
