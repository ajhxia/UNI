package Pokemon;

import java.util.ArrayList;
import java.util.Arrays;

/*
    * Classe che rappresenta le abilità dei pokemon
    * Ogni abilità ha un nome, un tipo e una forza
*/

public class Ability {
    private String name;
    private Types typo;
    private int strength;

    /**
     * Costruttore della classe Ability
     * @param name
     * @param typo
     * @param strength
     */
    public Ability(String name, Types typo, int strength) {
        this.name = name;
        this.typo = typo;
        this.strength = strength;
    }   

    /**
     * Metodo per ottenere il nome dell'abilità
      @return nome dell'abilità
     */
    public String getName() {
        return name;
    }

    /**
     * Metodo per ottenere il tipo dell'abilità
      @return tipo dell'abilità
     */
    public Types getTypo() {
        return typo;
    }

    /**
     * Metodo per ottenere la forza dell'abilità
      @return forza dell'abilità
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Metodo per impostare il nome dell'abilità
     * @param name
     */
    public static ArrayList<Ability> getAbility(int index, int level) {
        switch (index) {
            case 1: // Bulbasaur
                if (level >= 1 && level < 7) {
                    return new ArrayList<Ability>(Arrays.asList(
                        new Ability("Tackle", Types.NORMAL, 40),
                        new Ability("Growl", Types.NORMAL, 40)
                    ));
                } else if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL, 40),
                            new Ability("Leech Seed", Types.GRASS, 10)
                    ));
                } else if (level >= 13 && level < 20) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL, 40),
                            new Ability("Leech Seed", Types.GRASS, 10),
                            new Ability("Vine Whip", Types.GRASS, 45)
                    ));
                } else if (level >= 20) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL, 40),
                            new Ability("Leech Seed", Types.GRASS, 10),
                            new Ability("Vine Whip", Types.GRASS, 45),
                            new Ability("Poison Powder", Types.POISON,20)
                    ));
                }
            case 2: // Ivysaur
                if (level >= 13 ) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL, 40),
                            new Ability("Leech Seed", Types.GRASS, 10),
                            new Ability("Vine Whip", Types.GRASS, 45),
                            new Ability("Poison Powder", Types.POISON,20),
                            new Ability("Razor Leaf", Types.GRASS, 55),
                            new Ability("Sweet Scent", Types.NORMAL,20)
                    ));
                }
            case 3: // Venusaur
                if (level >= 7 ) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL, 40),
                            new Ability("Leech Seed", Types.GRASS, 10),
                            new Ability("Vine Whip", Types.GRASS, 45),
                            new Ability("Poison Powder", Types.POISON,20),
                            new Ability("Razor Leaf", Types.GRASS, 55),
                            new Ability("Sweet Scent", Types.NORMAL,20),
                            new Ability("Growth", Types.NORMAL,20)
                    ));
                }
            case 4: // Charmander
                if (level >= 1 && level < 9) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL, 40)
                    ));
                } else if (level >= 9 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL, 40),
                            new Ability("Ember", Types.FIRE, 40)
                    ));
                } else if (level >= 13 && level < 20) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL, 40),
                            new Ability("Ember", Types.FIRE, 40),
                            new Ability("Smokescreen", Types.NORMAL,20)
                    ));
                } else if (level >= 18) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL, 40),
                            new Ability("Ember", Types.FIRE, 40),
                            new Ability("Smokescreen", Types.NORMAL,20),
                            new Ability("Dragon Rage", Types.DRAGON, 40)
                    ));
                }
            case 5: // Charmeleon
                 if (level >= 9 && level < 15) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL, 40),
                            new Ability("Ember", Types.FIRE, 40),
                            new Ability("Smokescreen", Types.NORMAL,20),
                            new Ability("Dragon Rage", Types.DRAGON, 40),
                            new Ability("Scary Face", Types.NORMAL,20)
                    ));
                } else if (level >= 15 ) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL, 40),
                            new Ability("Ember", Types.FIRE, 40),
                            new Ability("Smokescreen", Types.NORMAL,20),
                            new Ability("Dragon Rage", Types.DRAGON, 40),
                            new Ability("Scary Face", Types.NORMAL,20),
                            new Ability("Fire Fang", Types.FIRE, 65)
                    ));
                }
            case 6: // Charizard
                if (level >= 17 ) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL, 40),
                            new Ability("Ember", Types.FIRE, 40),
                            new Ability("Smokescreen", Types.NORMAL,20),
                            new Ability("Dragon Rage", Types.DRAGON, 40),
                            new Ability("Scary Face", Types.NORMAL,20),
                            new Ability("Fire Fang", Types.FIRE, 65),
                            new Ability("Air Slash", Types.FLYING, 75),
                            new Ability("Inferno", Types.FIRE,
                                    100) ));
                }
            case 7: // Squirtle
                if (level >= 1 && level < 8) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Tail Whip", Types.NORMAL,20)
                    ));
                } else if (level >= 8 && level < 15) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Tail Whip", Types.NORMAL,20),
                            new Ability("Water Gun", Types.WATER, 40)
                    ));
                } else if (level >= 15 && level < 22) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Tail Whip", Types.NORMAL,20),
                            new Ability("Water Gun", Types.WATER, 40),
                            new Ability("Bite", Types.DARK, 60)
                    ));
                } else if (level >= 23) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Tail Whip", Types.NORMAL,20),
                            new Ability("Water Gun", Types.WATER, 40),
                            new Ability("Bite", Types.DARK, 60),
                            new Ability("Aqua Tail", Types.WATER, 90)
                    ));
                }
            case 8: // Wartortle
                 if (level >= 8 && level < 15) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Tail Whip", Types.NORMAL,20),
                            new Ability("Water Gun", Types.WATER, 40),
                            new Ability("Bite", Types.DARK, 60),
                            new Ability("Aqua Tail", Types.WATER, 90),
                            new Ability("Withdraw", Types.WATER,20)
                    ));
                } else if (level >= 15) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Tail Whip", Types.NORMAL,20),
                            new Ability("Water Gun", Types.WATER, 40),
                            new Ability("Bite", Types.DARK, 60),
                            new Ability("Aqua Tail", Types.WATER, 90),
                            new Ability("Withdraw", Types.WATER,20),
                            new Ability("Skull Bash", Types.NORMAL, 130)
                    ));
                }
            case 9: // Blastoise
                if (level >= 8 && level < 16) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Tail Whip", Types.NORMAL,20),
                            new Ability("Water Gun", Types.WATER, 40),
                            new Ability("Bite", Types.DARK, 60),
                            new Ability("Aqua Tail", Types.WATER, 90),
                            new Ability("Withdraw", Types.WATER,20),
                            new Ability("Skull Bash", Types.NORMAL, 130),
                            new Ability("Rain Dance", Types.WATER,20)
                    ));
                } else if (level >= 15) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Tail Whip", Types.NORMAL,20),
                            new Ability("Water Gun", Types.WATER, 40),
                            new Ability("Bite", Types.DARK, 60),
                            new Ability("Aqua Tail", Types.WATER, 90),
                            new Ability("Withdraw", Types.WATER,20),
                            new Ability("Skull Bash", Types.NORMAL, 130),
                            new Ability("Rain Dance", Types.WATER,20),
                            new Ability("Hydro Pump", Types.WATER, 110)
                    ));
                }
            case 10: // Caterpie
                if (level >= 1 && level < 15) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("String Shot", Types.BUG,20)
                    ));
                } else if (level >= 15) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("String Shot", Types.BUG,20),
                            new Ability("Bug Bite", Types.BUG, 60)
                    ));
                }
            case 11: // Metapod
                if (level >= 1) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Harden", Types.NORMAL,20)
                    ));
                }
            case 12: // Butterfree
                if (level >= 15 && level < 17) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Confusion", Types.PSYCHIC, 50),
                            new Ability("Gust", Types.FLYING, 40),
                            new Ability("Poison Powder", Types.POISON,20)
                    ));
                } else if (level >= 17) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Confusion", Types.PSYCHIC, 50),
                            new Ability("Gust", Types.FLYING, 40),
                            new Ability("Poison Powder", Types.POISON,20),
                            new Ability("Sleep Powder", Types.GRASS,20)
                    ));
                }
            case 13: // Weedle
                if (level >= 1 && level < 15) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Poison Sting", Types.POISON, 15),
                            new Ability("String Shot", Types.BUG,20)
                    ));
                } else if (level >= 15) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Poison Sting", Types.POISON, 15),
                            new Ability("String Shot", Types.BUG,20),
                            new Ability("Bug Bite", Types.BUG, 60)
                    ));
                }
            case 14: // Kakuna
                if (level >= 1) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Harden", Types.NORMAL,20)
                    ));
                }

            case 15: // Beedrill
                if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Poison Sting", Types.POISON, 15),
                            new Ability("String Shot", Types.BUG,20),
                            new Ability("Fury Attack", Types.NORMAL, 15),
                            new Ability("Focus Energy", Types.NORMAL,20)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Poison Sting", Types.POISON, 15),
                            new Ability("String Shot", Types.BUG,20),
                            new Ability("Fury Attack", Types.NORMAL, 15),
                            new Ability("Focus Energy", Types.NORMAL,20),
                            new Ability("Twineedle", Types.BUG, 25)
                    ));
                }
            case 16: // Pidgey
                if (level >= 1 && level < 7) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Gust", Types.FLYING, 40)
                    ));
                } else if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Gust", Types.FLYING, 40),
                            new Ability("Quick Attack", Types.NORMAL, 40)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Gust", Types.FLYING, 40),
                            new Ability("Quick Attack", Types.NORMAL, 40),
                            new Ability("Whirlwind", Types.NORMAL,20)
                    ));
                }
            case 17: // Pidgeotto
                if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Gust", Types.FLYING, 40),
                            new Ability("Quick Attack", Types.NORMAL, 40),
                            new Ability("Whirlwind", Types.NORMAL,20),
                            new Ability("Twister", Types.DRAGON, 40)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Gust", Types.FLYING, 40),
                            new Ability("Quick Attack", Types.NORMAL, 40),
                            new Ability("Whirlwind", Types.NORMAL,20),
                            new Ability("Twister", Types.DRAGON, 40),
                            new Ability("Feather Dance", Types.FLYING,20)
                    ));
                }
            case 18: // Pidgeot
                if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Gust", Types.FLYING, 40),
                            new Ability("Quick Attack", Types.NORMAL, 40),
                            new Ability("Whirlwind", Types.NORMAL,20),
                            new Ability("Twister", Types.DRAGON, 40),
                            new Ability("Feather Dance", Types.FLYING,20),
                            new Ability("Agility", Types.PSYCHIC,20),
                            new Ability("Air Slash", Types.FLYING, 75)
                    ));
                }
            case 19: // Rattata
                if (level >= 1 && level < 7) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Tail Whip", Types.NORMAL,20)
                    ));
                } else if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Tail Whip", Types.NORMAL,20),
                            new Ability("Quick Attack", Types.NORMAL, 40)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Tail Whip", Types.NORMAL,20),
                            new Ability("Quick Attack", Types.NORMAL, 40),
                            new Ability("Hyper Fang", Types.NORMAL, 80)
                    ));
                }
            case 20: // Raticate
                if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Tail Whip", Types.NORMAL,20),
                            new Ability("Quick Attack", Types.NORMAL, 40),
                            new Ability("Hyper Fang", Types.NORMAL, 80),
                            new Ability("Focus Energy", Types.NORMAL,20)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Tail Whip", Types.NORMAL,20),
                            new Ability("Quick Attack", Types.NORMAL, 40),
                            new Ability("Hyper Fang", Types.NORMAL, 80),
                            new Ability("Focus Energy", Types.NORMAL,20),
                            new Ability("Sucker Punch", Types.DARK, 70)
                    ));
                }
            case 21: // Spearow
                if (level >= 1 && level < 7) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Peck", Types.FLYING, 35),
                            new Ability("Growl", Types.NORMAL,20)
                    ));
                } else if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Peck", Types.FLYING, 35),
                            new Ability("Growl", Types.NORMAL,20),
                            new Ability("Leer", Types.NORMAL,20)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Peck", Types.FLYING, 35),
                            new Ability("Growl", Types.NORMAL,20),
                            new Ability("Leer", Types.NORMAL,20),
                            new Ability("Fury Attack", Types.NORMAL, 15)
                    ));
                }
            case 22: // Fearow
                if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Peck", Types.FLYING, 35),
                            new Ability("Growl", Types.NORMAL,20),
                            new Ability("Leer", Types.NORMAL,20),
                            new Ability("Fury Attack", Types.NORMAL, 15),
                            new Ability("Pursuit", Types.DARK, 40)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Peck", Types.FLYING, 35),
                            new Ability("Growl", Types.NORMAL,20),
                            new Ability("Leer", Types.NORMAL,20),
                            new Ability("Fury Attack", Types.NORMAL, 15),
                            new Ability("Pursuit", Types.DARK, 40),
                            new Ability("Mirror Move", Types.FLYING,20)
                    ));
                }
            case 23: // Ekans
                if (level >= 1 && level < 7) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Wrap", Types.NORMAL, 15),
                            new Ability("Leer", Types.NORMAL,20)
                    ));
                } else if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Wrap", Types.NORMAL, 15),
                            new Ability("Leer", Types.NORMAL,20),
                            new Ability("Poison Sting", Types.POISON, 15)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Wrap", Types.NORMAL, 15),
                            new Ability("Leer", Types.NORMAL,20),
                            new Ability("Poison Sting", Types.POISON, 15),
                            new Ability("Bite", Types.DARK, 60)
                    ));
                }
            case 24: // Arbok
                if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Wrap", Types.NORMAL, 15),
                            new Ability("Leer", Types.NORMAL,20),
                            new Ability("Poison Sting", Types.POISON, 15),
                            new Ability("Bite", Types.DARK, 60),
                            new Ability("Glare", Types.NORMAL,20)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Wrap", Types.NORMAL, 15),
                            new Ability("Leer", Types.NORMAL,20),
                            new Ability("Poison Sting", Types.POISON, 15),
                            new Ability("Bite", Types.DARK, 60),
                            new Ability("Glare", Types.NORMAL,20),
                            new Ability("Screech", Types.NORMAL,20)
                    ));
                }
            case 25: // Pikachu
                if (level >= 1 && level < 7) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Thundershock", Types.ELECTRIC, 40),
                            new Ability("Growl", Types.NORMAL,20)
                    ));
                } else if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Thundershock", Types.ELECTRIC, 40),
                            new Ability("Growl", Types.NORMAL,20),
                            new Ability("Quick Attack", Types.NORMAL, 40)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Thundershock", Types.ELECTRIC, 40),
                            new Ability("Growl", Types.NORMAL,20),
                            new Ability("Quick Attack", Types.NORMAL, 40),
                            new Ability("Electro Ball", Types.ELECTRIC, 60)
                    ));
                }
            case 26: // Raichu
                if (level >= 1) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Thundershock", Types.ELECTRIC, 40),
                            new Ability("Growl", Types.NORMAL,20),
                            new Ability("Quick Attack", Types.NORMAL, 40),
                            new Ability("Electro Ball", Types.ELECTRIC, 60),
                            new Ability("Double Team", Types.NORMAL,20),
                            new Ability("Thunderbolt", Types.ELECTRIC, 90)
                    ));
                }

            case 27: // Sandshrew
                if (level >= 1 && level < 7) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40)
                    ));
                } else if (level >= 6 && level < 10) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Defense Curl", Types.NORMAL,20)
                    ));
                } else if (level >= 10) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Defense Curl", Types.NORMAL,20),
                            new Ability("Sand Attack", Types.GROUND,20)
                    ));
                }
            case 28: // Sandslash
                if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Defense Curl", Types.NORMAL,20),
                            new Ability("Sand Attack", Types.GROUND,20),
                            new Ability("Poison Sting", Types.POISON, 15),
                            new Ability("Rollout", Types.ROCK, 30)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Defense Curl", Types.NORMAL,20),
                            new Ability("Sand Attack", Types.GROUND,20),
                            new Ability("Poison Sting", Types.POISON, 15),
                            new Ability("Rollout", Types.ROCK, 30),
                            new Ability("Swift", Types.NORMAL, 60)
                    ));
                }
            case 29: // Nidoran♀
                if (level >= 1 && level < 7) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Growl", Types.NORMAL,20),
                            new Ability("Tackle", Types.NORMAL, 40)
                    ));
                } else if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL,20),
                            new Ability("Tackle", Types.NORMAL, 40)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL,20),
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Poison Sting", Types.POISON, 15)
                    ));
                }
            case 30: // Nidorina
                if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL,20),
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Poison Sting", Types.POISON, 15),
                            new Ability("Bite", Types.DARK, 60)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL,20),
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Poison Sting", Types.POISON, 15),
                            new Ability("Bite", Types.DARK, 60),
                            new Ability("Fury Swipes", Types.NORMAL, 18)
                    ));
                }
            case 31: // Nidoqueen
                if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL,20),
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Poison Sting", Types.POISON, 15),
                            new Ability("Bite", Types.DARK, 60),
                            new Ability("Fury Swipes", Types.NORMAL, 18),
                            new Ability("Body Slam", Types.NORMAL, 85),
                            new Ability("Superpower", Types.FIGHTING, 120)
                    ));
                }
            case 32: // Nidoran♂
                if (level >= 1 && level < 7) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Peck", Types.FLYING, 35),
                            new Ability("Leer", Types.NORMAL,20)
                    ));
                } else if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Peck", Types.FLYING, 35),
                            new Ability("Leer", Types.NORMAL,20),
                            new Ability("Focus Energy", Types.NORMAL,20)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Peck", Types.FLYING, 35),
                            new Ability("Leer", Types.NORMAL,20),
                            new Ability("Focus Energy", Types.NORMAL,20),
                            new Ability("Horn Attack", Types.NORMAL, 65)
                    ));
                }
            case 33: // Nidorino
                if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Peck", Types.FLYING, 35),
                            new Ability("Leer", Types.NORMAL,20),
                            new Ability("Focus Energy", Types.NORMAL,20),
                            new Ability("Horn Attack", Types.NORMAL, 65),
                            new Ability("Double Kick", Types.FIGHTING, 30)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Peck", Types.FLYING, 35),
                            new Ability("Leer", Types.NORMAL,20),
                            new Ability("Focus Energy", Types.NORMAL,20),
                            new Ability("Horn Attack", Types.NORMAL, 65),
                            new Ability("Double Kick", Types.FIGHTING, 30),
                            new Ability("Poison Sting", Types.POISON, 15)
                    ));
                }
            case 34: // Nidoking
                if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Peck", Types.FLYING, 35),
                            new Ability("Leer", Types.NORMAL,20),
                            new Ability("Focus Energy", Types.NORMAL,20),
                            new Ability("Horn Attack", Types.NORMAL, 65),
                            new Ability("Double Kick", Types.FIGHTING, 30),
                            new Ability("Poison Sting", Types.POISON, 15),
                            new Ability("Thrash", Types.NORMAL, 120),
                            new Ability("Earth Power", Types.GROUND, 90)
                    ));
                }
            case 35: // Clefairy
                if (level >= 1 && level < 7) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Pound", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL,20)
                    ));
                } else if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Pound", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL,20),
                            new Ability("Sing", Types.NORMAL,20)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Pound", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL,20),
                            new Ability("Sing", Types.NORMAL,20),
                            new Ability("Double Slap", Types.NORMAL, 15)
                    ));
                }
            case 36: // Clefable
                if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Pound", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL,20),
                            new Ability("Sing", Types.NORMAL,20),
                            new Ability("Double Slap", Types.NORMAL, 15),
                            new Ability("Minimize", Types.NORMAL,20)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Pound", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL,20),
                            new Ability("Sing", Types.NORMAL,20),
                            new Ability("Double Slap", Types.NORMAL, 15),
                            new Ability("Minimize", Types.NORMAL,20),
                            new Ability("Metronome", Types.NORMAL,20)
                    ));
                }
            case 37: // Vulpix
                if (level >= 1 && level < 7) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Ember", Types.FIRE, 40),
                            new Ability("Tail Whip", Types.NORMAL,20)
                    ));
                } else if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Ember", Types.FIRE, 40),
                            new Ability("Tail Whip", Types.NORMAL,20),
                            new Ability("Quick Attack", Types.NORMAL, 40)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Ember", Types.FIRE, 40),
                            new Ability("Tail Whip", Types.NORMAL,20),
                            new Ability("Quick Attack", Types.NORMAL, 40),
                            new Ability("Will-O-Wisp", Types.FIRE,20)
                    ));
                }
            case 38: // Ninetales
                if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Ember", Types.FIRE, 40),
                            new Ability("Tail Whip", Types.NORMAL,20),
                            new Ability("Quick Attack", Types.NORMAL, 40),
                            new Ability("Will-O-Wisp", Types.FIRE,20),
                            new Ability("Confuse Ray", Types.GHOST,20)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Ember", Types.FIRE, 40),
                            new Ability("Tail Whip", Types.NORMAL,20),
                            new Ability("Quick Attack", Types.NORMAL, 40),
                            new Ability("Will-O-Wisp", Types.FIRE,20),
                            new Ability("Confuse Ray", Types.GHOST,20),
                            new Ability("Flamethrower", Types.FIRE, 90)
                    ));
                }
            case 39: // Jigglypuff
                if (level >= 1 && level < 7) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Pound", Types.NORMAL, 40),
                            new Ability("Sing", Types.NORMAL,20)
                    ));
                } else if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Pound", Types.NORMAL, 40),
                            new Ability("Sing", Types.NORMAL,20),
                            new Ability("Disable", Types.NORMAL,20)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Pound", Types.NORMAL, 40),
                            new Ability("Sing", Types.NORMAL,20),
                            new Ability("Disable", Types.NORMAL,20),
                            new Ability("Defense Curl", Types.NORMAL,20)
                    ));
                }
            case 40: // Wigglytuff
                if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Pound", Types.NORMAL, 40),
                            new Ability("Sing", Types.NORMAL,20),
                            new Ability("Disable", Types.NORMAL,20),
                            new Ability("Defense Curl", Types.NORMAL,20),
                            new Ability("Double Slap", Types.NORMAL, 15)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Pound", Types.NORMAL, 40),
                            new Ability("Sing", Types.NORMAL,20),
                            new Ability("Disable", Types.NORMAL,20),
                            new Ability("Defense Curl", Types.NORMAL,20),
                            new Ability("Double Slap", Types.NORMAL, 15),
                            new Ability("Body Slam", Types.NORMAL, 85)
                    ));
                }
            case 41: // Zubat
                if (level >= 1 && level < 7) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Leech Life", Types.BUG, 20),
                            new Ability("Supersonic", Types.NORMAL,20)
                    ));
                } else if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Leech Life", Types.BUG, 20),
                            new Ability("Supersonic", Types.NORMAL,20),
                            new Ability("Astonish", Types.GHOST, 30)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Leech Life", Types.BUG, 20),
                            new Ability("Supersonic", Types.NORMAL,20),
                            new Ability("Astonish", Types.GHOST, 30),
                            new Ability("Bite", Types.DARK, 60)
                    ));
                }
            case 42: // Golbat
                if (level >= 7 && level <= 14) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Leech Life", Types.BUG, 20),
                            new Ability("Supersonic", Types.NORMAL,20),
                            new Ability("Astonish", Types.GHOST, 30),
                            new Ability("Bite", Types.DARK, 60),
                            new Ability("Wing Attack", Types.FLYING, 60)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Leech Life", Types.BUG, 20),
                            new Ability("Supersonic", Types.NORMAL,20),
                            new Ability("Astonish", Types.GHOST, 30),
                            new Ability("Bite", Types.DARK, 60),
                            new Ability("Wing Attack", Types.FLYING, 60),
                            new Ability("Air Cutter", Types.FLYING, 60)
                    ));
                } 
            case 43: // Oddish
                if (level >= 1 && level < 7) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Absorb", Types.GRASS, 20),
                            new Ability("Sweet Scent", Types.NORMAL,20)
                    ));
                } else if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Absorb", Types.GRASS, 20),
                            new Ability("Sweet Scent", Types.NORMAL,20),
                            new Ability("Acid", Types.POISON, 40)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Absorb", Types.GRASS, 20),
                            new Ability("Sweet Scent", Types.NORMAL,20),
                            new Ability("Acid", Types.POISON, 40),
                            new Ability("Poison Powder", Types.POISON,20)
                    ));
                }
            case 44: // Gloom
                if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Absorb", Types.GRASS, 20),
                            new Ability("Sweet Scent", Types.NORMAL,20),
                            new Ability("Acid", Types.POISON, 40),
                            new Ability("Poison Powder", Types.POISON,20),
                            new Ability("Sleep Powder", Types.GRASS,20)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Absorb", Types.GRASS, 20),
                            new Ability("Sweet Scent", Types.NORMAL,20),
                            new Ability("Acid", Types.POISON, 40),
                            new Ability("Poison Powder", Types.POISON,20),
                            new Ability("Sleep Powder", Types.GRASS,20),
                            new Ability("Mega Drain", Types.GRASS, 40)
                    ));
                }
            case 45: // Vileplume
                if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Absorb", Types.GRASS, 20),
                            new Ability("Sweet Scent", Types.NORMAL,20),
                            new Ability("Acid", Types.POISON, 40),
                            new Ability("Poison Powder", Types.POISON,20),
                            new Ability("Sleep Powder", Types.GRASS,20),
                            new Ability("Mega Drain", Types.GRASS, 40),
                            new Ability("Petal Dance", Types.GRASS, 120)
                    ));
                }
            case 46: // Paras
                if (level >= 1 && level < 7) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Stun Spore", Types.GRASS,20)
                    ));
                } else if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Stun Spore", Types.GRASS,20),
                            new Ability("Poison Powder", Types.POISON,20)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Stun Spore", Types.GRASS,20),
                            new Ability("Poison Powder", Types.POISON,20),
                            new Ability("Leech Life", Types.BUG, 20)
                    ));
                }
            case 47: // Parasect
                if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Stun Spore", Types.GRASS,20),
                            new Ability("Poison Powder", Types.POISON,20),
                            new Ability("Leech Life", Types.BUG, 20),
                            new Ability("Fury Cutter", Types.BUG, 40)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Stun Spore", Types.GRASS,20),
                            new Ability("Poison Powder", Types.POISON,20),
                            new Ability("Leech Life", Types.BUG, 20),
                            new Ability("Fury Cutter", Types.BUG, 40),
                            new Ability("Spore", Types.GRASS,20)
                    ));
                }
            case 48: // Venonat
                if (level >= 1 && level < 7) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Disable", Types.NORMAL,20)
                    ));
                } else if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Disable", Types.NORMAL,20),
                            new Ability("Confusion", Types.PSYCHIC, 50)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Disable", Types.NORMAL,20),
                            new Ability("Confusion", Types.PSYCHIC, 50),
                            new Ability("Poison Powder", Types.POISON,20)
                    ));
                }
            case 49: // Venomoth
                if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Disable", Types.NORMAL,20),
                            new Ability("Confusion", Types.PSYCHIC, 50),
                            new Ability("Poison Powder", Types.POISON,20),
                            new Ability("Leech Life", Types.BUG, 20)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Tackle", Types.NORMAL, 40),
                            new Ability("Disable", Types.NORMAL,20),
                            new Ability("Confusion", Types.PSYCHIC, 50),
                            new Ability("Poison Powder", Types.POISON,20),
                            new Ability("Leech Life", Types.BUG, 20),
                            new Ability("Psybeam", Types.PSYCHIC, 65)
                    ));
                }
            case 50: // Diglett
                if (level >= 1 && level < 7) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL,20)
                    ));
                } else if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL,20),
                            new Ability("Astonish", Types.GHOST, 30)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL,20),
                            new Ability("Astonish", Types.GHOST, 30),
                            new Ability("Mud-Slap", Types.GROUND, 20)
                    ));
                }
            case 51: // Dugtrio
                if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL,20),
                            new Ability("Astonish", Types.GHOST, 30),
                            new Ability("Mud-Slap", Types.GROUND, 20),
                            new Ability("Magnitude", Types.GROUND, 70)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL,20),
                            new Ability("Astonish", Types.GHOST, 30),
                            new Ability("Mud-Slap", Types.GROUND, 20),
                            new Ability("Magnitude", Types.GROUND, 70),
                            new Ability("Bulldoze", Types.GROUND, 60)
                    ));
                }
            case 52: // Meowth
                if (level >= 1 && level < 7) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL,20)
                    ));
                } else if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL,20),
                            new Ability("Bite", Types.DARK, 60)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL,20),
                            new Ability("Bite", Types.DARK, 60),
                            new Ability("Fake Out", Types.NORMAL, 40)
                    ));
                }
            case 53: // Persian
                if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL,20),
                            new Ability("Bite", Types.DARK, 60),
                            new Ability("Fake Out", Types.NORMAL, 40),
                            new Ability("Fury Swipes", Types.NORMAL, 18)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Growl", Types.NORMAL,20),
                            new Ability("Bite", Types.DARK, 60),
                            new Ability("Fake Out", Types.NORMAL, 40),
                            new Ability("Fury Swipes", Types.NORMAL, 18),
                            new Ability("Screech", Types.NORMAL,20)
                    ));
                }
            case 54: // Psyduck
                if (level >= 1 && level < 7) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Scratch", Types.NORMAL, 40),
                            new Ability("Tail Whip", Types.NORMAL,20)
                    ));
                } else if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Water Gun", Types.WATER, 40),
                            new Ability("Tail Whip", Types.NORMAL,20),
                            new Ability("Disable", Types.NORMAL,20)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Water Gun", Types.WATER, 40),
                            new Ability("Tail Whip", Types.NORMAL,20),
                            new Ability("Disable", Types.NORMAL,20),
                            new Ability("Confusion", Types.PSYCHIC, 50)
                    ));
                }
            case 55: // Golduck
                if (level >= 7 && level < 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Water Gun", Types.WATER, 40),
                            new Ability("Tail Whip", Types.NORMAL,20),
                            new Ability("Disable", Types.NORMAL,20),
                            new Ability("Confusion", Types.PSYCHIC, 50),
                            new Ability("Screech", Types.NORMAL,20)
                    ));
                } else if (level >= 13) {
                    return new ArrayList<Ability> (Arrays.asList(
                            new Ability("Water Gun", Types.WATER, 40),
                            new Ability("Tail Whip", Types.NORMAL,20),
                            new Ability("Disable", Types.NORMAL,20),
                            new Ability("Confusion", Types.PSYCHIC, 50),
                            new Ability("Screech", Types.NORMAL,20),
                            new Ability("Psychic", Types.PSYCHIC, 90)
                    ));
                }
            default:
                return null;
        }
    }
}
