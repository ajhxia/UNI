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
    private Types[] types;
    private int baseExperience;
    private Sprites sprite;
    private int lvl;
    private int lvlEvoluzione;
    private int[] evolutions;
    private boolean inUse;
    private int indexInPokedex;


    /**
     * Costruttore della classe Pokemon
     * @param name
     * @param indexInPokedex
     * @param abilities
     * @param startIn
     * @param stats
     * @param types
     * @param baseExperience
     * @param sprite
     * @param lvl
     * @param lvlEvoluzione
     * @param evolutions
     * @param inUse
     */
    public Pokemon(String name, int indexInPokedex, ArrayList<Ability> abilities, String startIn, Stats stats, Types[] types, int baseExperience, Sprites sprite, int lvl, int lvlEvoluzione, int[] evolutions, boolean inUse) {
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

    /**
     * Restituisce il nome del pokemon
       @return nome del pokemon
     */

    public String getName() {
        return name;
    }

    /**
     * Restituisce la stringa di inizio del pokemon
       @return stringa di inizio del pokemon
    */

    public String getStart() {
        return start;
    }

    /**
     * Restituisce l'indice del pokemon nel pokedex
       @return indice del pokemon nel pokedex
     */

    public int getIndexInPokedex() {
        return indexInPokedex;
    }

    /**
     * Restituisce l'esperienza massima necessaria per salire di livello
       @return esperienza massima necessaria per salire di livello
     */

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

    /**
     * Restituisce l'esperienza necessaria per salire di livello
     * @param baseExperience esperienza base del pokemon
       esperienza necessaria per salire di livello
     */

    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }

    /**
     * Restituisce le abilità del pokemon
       @return abilità del pokemon
     */

    public ArrayList<Ability> getAbilities() {
        return abilities;
    }

    /**
     * Restituisce il livello di evoluzione del pokemon
       @return livello di evoluzione del pokemon
     */

    public int getLvlEvoluzione() {
        return lvlEvoluzione;
    }
    
    /**
     * Restituisce l'abilità del pokemon
     * @param index indice dell'abilità
       abilità del pokemon
     */

    public void setLvlEvoluzione(int lvl) {
        this.lvlEvoluzione = lvl;
    }

    /**
     * Imposta l'abilità del pokemon
     * @param index indice dell'abilità
     * @param lvl livello dell'abilità
     */

    public void setAbility(int index, int lvl) {
        this.abilities = Ability.getAbility(index, lvl);
    }

    /**
     * Restituisce l'abilità del pokemon
     * @param index indice dell'abilità
       abilità del pokemon
     */
    
    public void addAbility(Ability pokemon) {
        abilities.add(pokemon);
    }

    /**
     * Aggiunge un'abilità al pokemon
     * @param pokemon abilità da aggiungere
     */

    public void removeAbility(int index) {
        if (index >= 0 && index < abilities.size()) {
            abilities.remove(index);
        } else {
            System.out.println("Invalid index.");
        }
    }

    /**
     * Rimuove un'abilità dal pokemon
     * @param index indice dell'abilità
     * @return true se l'abilità è stata rimossa, false altrimenti
     */

    public Ability getAbility(int index) {
        if (index >= 0 && index < abilities.size()) {
            return abilities.get(index);
        } else {
            System.out.println("Invalid index.");
            return null;
        }
    }

    /**
     * Restituisce l'abilità del pokemon
     * @param index indice dell'abilità
       @param abilità del pokemon
     */

    public void addAbilityAtIndex(Ability ability, int index) {
        if (index >= 0 && index < abilities.size()) {
            abilities.add(index, ability);
        } else {
            abilities.add(ability);
        }
    }   
    
    /**
     * Aggiunge un'abilità al pokemon
     * @param ability abilità da aggiungere
     * @param index indice dell'abilità
     * @return true se l'abilità è stata aggiunta, false altrimenti
     */

    public Stats getStats() {
        return stats;
    }

    /**
     * Restituisce le statistiche del pokemon
       @return statistiche del pokemon
     */

    public Types[] getTypes() {
        return types;
    }

    /**
     * Restituisce i tipi del pokemon
       @return tipi del pokemon
     */
    
    public int getLvl() {
        return lvl;
    }

    /**
     * Restituisce il livello del pokemon
       livello del pokemon
     */

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    /**
     * Imposta il livello del pokemon
     * @param lvl livello del pokemon
     */

    public int getBaseExperience() {
        return baseExperience;
    }

    /**
     * Restituisce l'esperienza base del pokemon
       @return esperienza base del pokemon
     */

    public Sprites getSprite() {
        return sprite;
    }

    /**
     * Restituisce lo sprite del pokemon
       @return sprite del pokemon
     */

    public int[] getEvolutions() {
        return evolutions;
    }

    /**
     * Restituisce le evoluzioni del pokemon
       @return evoluzioni del pokemon
     */

    public boolean isInUse() {
        return inUse;
    }

    /**
     * Restituisce se il pokemon è in uso
       true se il pokemon è in uso, false altrimenti
     */

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

}
