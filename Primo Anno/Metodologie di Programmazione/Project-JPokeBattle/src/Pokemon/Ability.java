package Pokemon;

/*
    * Classe che rappresenta le abilità dei pokemon
    * Ogni abilità ha un nome, un tipo e una forza
*/

public class Ability {
    private String name;
    private String[] typo;
    private int strength;

    public Ability(String name, String[] string, int strength) {
        this.name = name;
        this.typo = string;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public String[] getTypo() {
        return typo;
    }

    public int getStrength() {
        return strength;
    }

    public static Ability[] getAbility(int index, int level) {
        switch (index) {
            case 1: // Bulbasaur
                if (level >= 1 && level < 7) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 40)
                    };
                } else if (level >= 7 && level < 13) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 40),
                            new Ability("Leech Seed", new String[] { "Grass" }, 10)
                    };
                } else if (level >= 13 && level < 20) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 40),
                            new Ability("Leech Seed", new String[] { "Grass" }, 10),
                            new Ability("Vine Whip", new String[] { "Grass" }, 45)
                    };
                } else if (level >= 20) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 40),
                            new Ability("Leech Seed", new String[] { "Grass" }, 10),
                            new Ability("Vine Whip", new String[] { "Grass" }, 45),
                            new Ability("Poison Powder", new String[] { "Poison" }, 0)
                    };
                }
            case 2: // Ivysaur
                if (level >= 1 && level < 7) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 40),
                            new Ability("Leech Seed", new String[] { "Grass" }, 10),
                            new Ability("Vine Whip", new String[] { "Grass" }, 45),
                            new Ability("Poison Powder", new String[] { "Poison" }, 0)
                    };
                } else if (level >= 7 && level < 13) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 40),
                            new Ability("Leech Seed", new String[] { "Grass" }, 10),
                            new Ability("Vine Whip", new String[] { "Grass" }, 45),
                            new Ability("Poison Powder", new String[] { "Poison" }, 0),
                            new Ability("Razor Leaf", new String[] { "Grass" }, 55)
                    };
                } else if (level >= 13 ) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 40),
                            new Ability("Leech Seed", new String[] { "Grass" }, 10),
                            new Ability("Vine Whip", new String[] { "Grass" }, 45),
                            new Ability("Poison Powder", new String[] { "Poison" }, 0),
                            new Ability("Razor Leaf", new String[] { "Grass" }, 55),
                            new Ability("Sweet Scent", new String[] { "Normal" }, 0)
                    };
                }
            case 3: // Venusaur
                if (level >= 1 && level < 7) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 40),
                            new Ability("Leech Seed", new String[] { "Grass" }, 10),
                            new Ability("Vine Whip", new String[] { "Grass" }, 45),
                            new Ability("Poison Powder", new String[] { "Poison" }, 0),
                            new Ability("Razor Leaf", new String[] { "Grass" }, 55),
                            new Ability("Sweet Scent", new String[] { "Normal" }, 0)
                    };
                } else if (level >= 7 ) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 40),
                            new Ability("Leech Seed", new String[] { "Grass" }, 10),
                            new Ability("Vine Whip", new String[] { "Grass" }, 45),
                            new Ability("Poison Powder", new String[] { "Poison" }, 0),
                            new Ability("Razor Leaf", new String[] { "Grass" }, 55),
                            new Ability("Sweet Scent", new String[] { "Normal" }, 0),
                            new Ability("Growth", new String[] { "Normal" }, 0)
                    };
                }
            case 4: // Charmander
                if (level >= 1 && level < 9) {
                    return new Ability[] {
                            new Ability("Scratch", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 40)
                    };
                } else if (level >= 9 && level < 13) {
                    return new Ability[] {
                            new Ability("Scratch", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 40),
                            new Ability("Ember", new String[] { "Fire" }, 40)
                    };
                } else if (level >= 13 && level < 20) {
                    return new Ability[] {
                            new Ability("Scratch", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 40),
                            new Ability("Ember", new String[] { "Fire" }, 40),
                            new Ability("Smokescreen", new String[] { "Normal" }, 0)
                    };
                } else if (level >= 18) {
                    return new Ability[] {
                            new Ability("Scratch", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 40),
                            new Ability("Ember", new String[] { "Fire" }, 40),
                            new Ability("Smokescreen", new String[] { "Normal" }, 0),
                            new Ability("Dragon Rage", new String[] { "Dragon" }, 40)
                    };
                }
            case 5: // Charmeleon
                if (level >= 1 && level < 9) {
                    return new Ability[] {
                            new Ability("Scratch", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 40),
                            new Ability("Ember", new String[] { "Fire" }, 40),
                            new Ability("Smokescreen", new String[] { "Normal" }, 0),
                            new Ability("Dragon Rage", new String[] { "Dragon" }, 40)
                    };
                } else if (level >= 9 && level < 13) {
                    return new Ability[] {
                            new Ability("Scratch", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 40),
                            new Ability("Ember", new String[] { "Fire" }, 40),
                            new Ability("Smokescreen", new String[] { "Normal" }, 0),
                            new Ability("Dragon Rage", new String[] { "Dragon" }, 40),
                            new Ability("Scary Face", new String[] { "Normal" }, 0)
                    };
                } else if (level >= 13 ) {
                    return new Ability[] {
                            new Ability("Scratch", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 40),
                            new Ability("Ember", new String[] { "Fire" }, 40),
                            new Ability("Smokescreen", new String[] { "Normal" }, 0),
                            new Ability("Dragon Rage", new String[] { "Dragon" }, 40),
                            new Ability("Scary Face", new String[] { "Normal" }, 0),
                            new Ability("Fire Fang", new String[] { "Fire" }, 65)
                    };
                }
            case 6: // Charizard
                if (level >= 1 && level < 9) {
                    return new Ability[] {
                            new Ability("Scratch", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 40),
                            new Ability("Ember", new String[] { "Fire" }, 40),
                            new Ability("Smokescreen", new String[] { "Normal" }, 0),
                            new Ability("Dragon Rage", new String[] { "Dragon" }, 40),
                            new Ability("Scary Face", new String[] { "Normal" }, 0),
                            new Ability("Fire Fang", new String[] { "Fire" }, 65)
                    };
                } else if (level >= 9 && level < 13) {
                    return new Ability[] {
                            new Ability("Scratch", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 40),
                            new Ability("Ember", new String[] { "Fire" }, 40),
                            new Ability("Smokescreen", new String[] { "Normal" }, 0),
                            new Ability("Dragon Rage", new String[] { "Dragon" }, 40),
                            new Ability("Scary Face", new String[] { "Normal" }, 0),
                            new Ability("Fire Fang", new String[] { "Fire" }, 65),
                            new Ability("Air Slash", new String[] { "Flying" }, 75)
                    };
                } else if (level >= 13 ) {
                    return new Ability[] {
                            new Ability("Scratch", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 40),
                            new Ability("Ember", new String[] { "Fire" }, 40),
                            new Ability("Smokescreen", new String[] { "Normal" }, 0),
                            new Ability("Dragon Rage", new String[] { "Dragon" }, 40),
                            new Ability("Scary Face", new String[] { "Normal" }, 0),
                            new Ability("Fire Fang", new String[] { "Fire" }, 65),
                            new Ability("Air Slash", new String[] { "Flying" }, 75),
                            new Ability("Inferno", new String[] { "Fire" },
                                    100) };
                }
            case 7: // Squirtle
                if (level >= 1 && level < 8) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Tail Whip", new String[] { "Normal" }, 0)
                    };
                } else if (level >= 8 && level < 15) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Tail Whip", new String[] { "Normal" }, 0),
                            new Ability("Water Gun", new String[] { "Water" }, 40)
                    };
                } else if (level >= 15 && level < 22) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Tail Whip", new String[] { "Normal" }, 0),
                            new Ability("Water Gun", new String[] { "Water" }, 40),
                            new Ability("Bite", new String[] { "Dark" }, 60)
                    };
                } else if (level >= 23) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Tail Whip", new String[] { "Normal" }, 0),
                            new Ability("Water Gun", new String[] { "Water" }, 40),
                            new Ability("Bite", new String[] { "Dark" }, 60),
                            new Ability("Aqua Tail", new String[] { "Water" }, 90)
                    };
                }
            case 8: // Wartortle
                if (level >= 1 && level < 8) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Tail Whip", new String[] { "Normal" }, 0),
                            new Ability("Water Gun", new String[] { "Water" }, 40),
                            new Ability("Bite", new String[] { "Dark" }, 60),
                            new Ability("Aqua Tail", new String[] { "Water" }, 90)
                    };
                } else if (level >= 8 && level < 15) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Tail Whip", new String[] { "Normal" }, 0),
                            new Ability("Water Gun", new String[] { "Water" }, 40),
                            new Ability("Bite", new String[] { "Dark" }, 60),
                            new Ability("Aqua Tail", new String[] { "Water" }, 90),
                            new Ability("Withdraw", new String[] { "Water" }, 0)
                    };
                } else if (level >= 15) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Tail Whip", new String[] { "Normal" }, 0),
                            new Ability("Water Gun", new String[] { "Water" }, 40),
                            new Ability("Bite", new String[] { "Dark" }, 60),
                            new Ability("Aqua Tail", new String[] { "Water" }, 90),
                            new Ability("Withdraw", new String[] { "Water" }, 0),
                            new Ability("Skull Bash", new String[] { "Normal" }, 130)
                    };
                }
            case 9: // Blastoise
                if (level >= 1 && level < 8) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Tail Whip", new String[] { "Normal" }, 0),
                            new Ability("Water Gun", new String[] { "Water" }, 40),
                            new Ability("Bite", new String[] { "Dark" }, 60),
                            new Ability("Aqua Tail", new String[] { "Water" }, 90),
                            new Ability("Withdraw", new String[] { "Water" }, 0),
                            new Ability("Skull Bash", new String[] { "Normal" }, 130)
                    };
                } else if (level >= 8 && level < 15) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Tail Whip", new String[] { "Normal" }, 0),
                            new Ability("Water Gun", new String[] { "Water" }, 40),
                            new Ability("Bite", new String[] { "Dark" }, 60),
                            new Ability("Aqua Tail", new String[] { "Water" }, 90),
                            new Ability("Withdraw", new String[] { "Water" }, 0),
                            new Ability("Skull Bash", new String[] { "Normal" }, 130),
                            new Ability("Rain Dance", new String[] { "Water" }, 0)
                    };
                } else if (level >= 15) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Tail Whip", new String[] { "Normal" }, 0),
                            new Ability("Water Gun", new String[] { "Water" }, 40),
                            new Ability("Bite", new String[] { "Dark" }, 60),
                            new Ability("Aqua Tail", new String[] { "Water" }, 90),
                            new Ability("Withdraw", new String[] { "Water" }, 0),
                            new Ability("Skull Bash", new String[] { "Normal" }, 130),
                            new Ability("Rain Dance", new String[] { "Water" }, 0),
                            new Ability("Hydro Pump", new String[] { "Water" }, 110)
                    };
                }
            case 10: // Caterpie
                if (level >= 1 && level < 15) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("String Shot", new String[] { "Bug" }, 0)
                    };
                } else if (level >= 15) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("String Shot", new String[] { "Bug" }, 0),
                            new Ability("Bug Bite", new String[] { "Bug" }, 60)
                    };
                }
            case 11: // Metapod
                if (level >= 1) {
                    return new Ability[] {
                            new Ability("Harden", new String[] { "Normal" }, 0)
                    };
                }
            case 12: // Butterfree
                if (level == 1) {
                    return new Ability[] {
                            new Ability("Confusion", new String[] { "Psychic" }, 50),
                            new Ability("Gust", new String[] { "Flying" }, 40),
                            new Ability("Tackle", new String[] { "Normal" }, 40)
                    };
                } else if (level >= 15 && level < 17) {
                    return new Ability[] {
                            new Ability("Confusion", new String[] { "Psychic" }, 50),
                            new Ability("Gust", new String[] { "Flying" }, 40),
                            new Ability("Poison Powder", new String[] { "Poison" }, 0)
                    };
                } else if (level >= 17) {
                    return new Ability[] {
                            new Ability("Confusion", new String[] { "Psychic" }, 50),
                            new Ability("Gust", new String[] { "Flying" }, 40),
                            new Ability("Poison Powder", new String[] { "Poison" }, 0),
                            new Ability("Sleep Powder", new String[] { "Grass" }, 0)
                    };
                }
            case 13: // Weedle
                if (level >= 1 && level < 15) {
                    return new Ability[] {
                            new Ability("Poison Sting", new String[] { "Poison" }, 15),
                            new Ability("String Shot", new String[] { "Bug" }, 0)
                    };
                } else if (level >= 15) {
                    return new Ability[] {
                            new Ability("Poison Sting", new String[] { "Poison" }, 15),
                            new Ability("String Shot", new String[] { "Bug" }, 0),
                            new Ability("Bug Bite", new String[] { "Bug" }, 60)
                    };
                }
            case 14: // Kakuna
                if (level >= 1) {
                    return new Ability[] {
                            new Ability("Harden", new String[] { "Normal" }, 0),
                    };
                }

            case 15: // Beedrill
                if (level >= 1 && level < 7) {
                    return new Ability[] {
                            new Ability("Poison Sting", new String[] { "Poison" }, 15),
                            new Ability("String Shot", new String[] { "Bug" }, 0),
                            new Ability("Fury Attack", new String[] { "Normal" }, 15)
                    };
                } else if (level >= 7 && level < 13) {
                    return new Ability[] {
                            new Ability("Poison Sting", new String[] { "Poison" }, 15),
                            new Ability("String Shot", new String[] { "Bug" }, 0),
                            new Ability("Fury Attack", new String[] { "Normal" }, 15),
                            new Ability("Focus Energy", new String[] { "Normal" }, 0)
                    };
                } else if (level >= 13) {
                    return new Ability[] {
                            new Ability("Poison Sting", new String[] { "Poison" }, 15),
                            new Ability("String Shot", new String[] { "Bug" }, 0),
                            new Ability("Fury Attack", new String[] { "Normal" }, 15),
                            new Ability("Focus Energy", new String[] { "Normal" }, 0),
                            new Ability("Twineedle", new String[] { "Bug" }, 25)
                    };
                }
            case 16: // Pidgey
                if (level >= 1 && level < 7) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Gust", new String[] { "Flying" }, 40)
                    };
                } else if (level >= 7 && level < 13) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Gust", new String[] { "Flying" }, 40),
                            new Ability("Quick Attack", new String[] { "Normal" }, 40)
                    };
                } else if (level >= 13) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Gust", new String[] { "Flying" }, 40),
                            new Ability("Quick Attack", new String[] { "Normal" }, 40),
                            new Ability("Whirlwind", new String[] { "Normal" }, 0)
                    };
                }
            case 17: // Pidgeotto
                if (level >= 1 && level < 7) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Gust", new String[] { "Flying" }, 40),
                            new Ability("Quick Attack", new String[] { "Normal" }, 40),
                            new Ability("Whirlwind", new String[] { "Normal" }, 0)
                    };
                } else if (level >= 7 && level < 13) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Gust", new String[] { "Flying" }, 40),
                            new Ability("Quick Attack", new String[] { "Normal" }, 40),
                            new Ability("Whirlwind", new String[] { "Normal" }, 0),
                            new Ability("Twister", new String[] { "Dragon" }, 40)
                    };
                } else if (level >= 13) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Gust", new String[] { "Flying" }, 40),
                            new Ability("Quick Attack", new String[] { "Normal" }, 40),
                            new Ability("Whirlwind", new String[] { "Normal" }, 0),
                            new Ability("Twister", new String[] { "Dragon" }, 40),
                            new Ability("Feather Dance", new String[] { "Flying" }, 0)
                    };
                }
            case 18: // Pidgeot
                if (level >= 1 && level < 7) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Gust", new String[] { "Flying" }, 40),
                            new Ability("Quick Attack", new String[] { "Normal" }, 40),
                            new Ability("Whirlwind", new String[] { "Normal" }, 0),
                            new Ability("Twister", new String[] { "Dragon" }, 40),
                            new Ability("Feather Dance", new String[] { "Flying" }, 0)
                    };
                } else if (level >= 7 && level < 13) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Gust", new String[] { "Flying" }, 40),
                            new Ability("Quick Attack", new String[] { "Normal" }, 40),
                            new Ability("Whirlwind", new String[] { "Normal" }, 0),
                            new Ability("Twister", new String[] { "Dragon" }, 40),
                            new Ability("Feather Dance", new String[] { "Flying" }, 0),
                            new Ability("Agility", new String[] { "Psychic" }, 0)
                    };
                } else if (level >= 13) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Gust", new String[] { "Flying" }, 40),
                            new Ability("Quick Attack", new String[] { "Normal" }, 40),
                            new Ability("Whirlwind", new String[] { "Normal" }, 0),
                            new Ability("Twister", new String[] { "Dragon" }, 40),
                            new Ability("Feather Dance", new String[] { "Flying" }, 0),
                            new Ability("Agility", new String[] { "Psychic" }, 0),
                            new Ability("Air Slash", new String[] { "Flying" }, 75)
                    };
                }
            case 19: // Rattata
                if (level >= 1 && level < 7) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Tail Whip", new String[] { "Normal" }, 0)
                    };
                } else if (level >= 7 && level < 13) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Tail Whip", new String[] { "Normal" }, 0),
                            new Ability("Quick Attack", new String[] { "Normal" }, 40)
                    };
                } else if (level >= 13) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Tail Whip", new String[] { "Normal" }, 0),
                            new Ability("Quick Attack", new String[] { "Normal" }, 40),
                            new Ability("Hyper Fang", new String[] { "Normal" }, 80)
                    };
                }
            case 20: // Raticate
                if (level >= 1 && level < 7) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Tail Whip", new String[] { "Normal" }, 0),
                            new Ability("Quick Attack", new String[] { "Normal" }, 40),
                            new Ability("Hyper Fang", new String[] { "Normal" }, 80)
                    };
                } else if (level >= 7 && level < 13) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Tail Whip", new String[] { "Normal" }, 0),
                            new Ability("Quick Attack", new String[] { "Normal" }, 40),
                            new Ability("Hyper Fang", new String[] { "Normal" }, 80),
                            new Ability("Focus Energy", new String[] { "Normal" }, 0)
                    };
                } else if (level >= 13) {
                    return new Ability[] {
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Tail Whip", new String[] { "Normal" }, 0),
                            new Ability("Quick Attack", new String[] { "Normal" }, 40),
                            new Ability("Hyper Fang", new String[] { "Normal" }, 80),
                            new Ability("Focus Energy", new String[] { "Normal" }, 0),
                            new Ability("Sucker Punch", new String[] { "Dark" }, 70)
                    };
                }
            case 21: // Spearow
                if (level >= 1 && level < 7) {
                    return new Ability[] {
                            new Ability("Peck", new String[] { "Flying" }, 35),
                            new Ability("Growl", new String[] { "Normal" }, 0)
                    };
                } else if (level >= 7 && level < 13) {
                    return new Ability[] {
                            new Ability("Peck", new String[] { "Flying" }, 35),
                            new Ability("Growl", new String[] { "Normal" }, 0),
                            new Ability("Leer", new String[] { "Normal" }, 0)
                    };
                } else if (level >= 13) {
                    return new Ability[] {
                            new Ability("Peck", new String[] { "Flying" }, 35),
                            new Ability("Growl", new String[] { "Normal" }, 0),
                            new Ability("Leer", new String[] { "Normal" }, 0),
                            new Ability("Fury Attack", new String[] { "Normal" }, 15)
                    };
                }
            case 22: // Fearow
                if (level >= 1 && level < 7) {
                    return new Ability[] {
                            new Ability("Peck", new String[] { "Flying" }, 35),
                            new Ability("Growl", new String[] { "Normal" }, 0),
                            new Ability("Leer", new String[] { "Normal" }, 0),
                            new Ability("Fury Attack", new String[] { "Normal" }, 15)
                    };
                } else if (level >= 7 && level < 13) {
                    return new Ability[] {
                            new Ability("Peck", new String[] { "Flying" }, 35),
                            new Ability("Growl", new String[] { "Normal" }, 0),
                            new Ability("Leer", new String[] { "Normal" }, 0),
                            new Ability("Fury Attack", new String[] { "Normal" }, 15),
                            new Ability("Pursuit", new String[] { "Dark" }, 40)
                    };
                } else if (level >= 13) {
                    return new Ability[] {
                            new Ability("Peck", new String[] { "Flying" }, 35),
                            new Ability("Growl", new String[] { "Normal" }, 0),
                            new Ability("Leer", new String[] { "Normal" }, 0),
                            new Ability("Fury Attack", new String[] { "Normal" }, 15),
                            new Ability("Pursuit", new String[] { "Dark" }, 40),
                            new Ability("Mirror Move", new String[] { "Flying" }, 0)
                    };
                }
            case 23: // Ekans
                if (level >= 1 && level < 7) {
                    return new Ability[] {
                            new Ability("Wrap", new String[] { "Normal" }, 15),
                            new Ability("Leer", new String[] { "Normal" }, 0)
                    };
                } else if (level >= 7 && level < 13) {
                    return new Ability[] {
                            new Ability("Wrap", new String[] { "Normal" }, 15),
                            new Ability("Leer", new String[] { "Normal" }, 0),
                            new Ability("Poison Sting", new String[] { "Poison" }, 15)
                    };
                } else if (level >= 13) {
                    return new Ability[] {
                            new Ability("Wrap", new String[] { "Normal" }, 15),
                            new Ability("Leer", new String[] { "Normal" }, 0),
                            new Ability("Poison Sting", new String[] { "Poison" }, 15),
                            new Ability("Bite", new String[] { "Dark" }, 60)
                    };
                }
            case 24: // Arbok
                if (level >= 1 && level < 7) {
                    return new Ability[] {
                            new Ability("Wrap", new String[] { "Normal" }, 15),
                            new Ability("Leer", new String[] { "Normal" }, 0),
                            new Ability("Poison Sting", new String[] { "Poison" }, 15),
                            new Ability("Bite", new String[] { "Dark" }, 60)
                    };
                } else if (level >= 7 && level < 13) {
                    return new Ability[] {
                            new Ability("Wrap", new String[] { "Normal" }, 15),
                            new Ability("Leer", new String[] { "Normal" }, 0),
                            new Ability("Poison Sting", new String[] { "Poison" }, 15),
                            new Ability("Bite", new String[] { "Dark" }, 60),
                            new Ability("Glare", new String[] { "Normal" }, 0)
                    };
                } else if (level >= 13) {
                    return new Ability[] {
                            new Ability("Wrap", new String[] { "Normal" }, 15),
                            new Ability("Leer", new String[] { "Normal" }, 0),
                            new Ability("Poison Sting", new String[] { "Poison" }, 15),
                            new Ability("Bite", new String[] { "Dark" }, 60),
                            new Ability("Glare", new String[] { "Normal" }, 0),
                            new Ability("Screech", new String[] { "Normal" }, 0)
                    };
                }
            case 25: // Pikachu
                if (level >= 1 && level < 7) {
                    return new Ability[] {
                            new Ability("Thundershock", new String[] { "Electric" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 0)
                    };
                } else if (level >= 7 && level < 13) {
                    return new Ability[] {
                            new Ability("Thundershock", new String[] { "Electric" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 0),
                            new Ability("Quick Attack", new String[] { "Normal" }, 40)
                    };
                } else if (level >= 13) {
                    return new Ability[] {
                            new Ability("Thundershock", new String[] { "Electric" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 0),
                            new Ability("Quick Attack", new String[] { "Normal" }, 40),
                            new Ability("Electro Ball", new String[] { "Electric" }, 60)
                    };
                }
            case 26: // Raichu
                if (level >= 1) {
                    return new Ability[] {
                            new Ability("Thundershock", new String[] { "Electric" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 0),
                            new Ability("Quick Attack", new String[] { "Normal" }, 40),
                            new Ability("Electro Ball", new String[] { "Electric" }, 60),
                            new Ability("Double Team", new String[] { "Normal" }, 0),
                            new Ability("Thunderbolt", new String[] { "Electric" }, 90)
                    };
                }

            case 27: // Sandshrew
                if (level >= 1 && level < 7) {
                    return new Ability[] {
                            new Ability("Scratch", new String[] { "Normal" }, 40),
                    };
                } else if (level >= 6 && level < 10) {
                    return new Ability[] {
                            new Ability("Scratch", new String[] { "Normal" }, 40),
                            new Ability("Defense Curl", new String[] { "Normal" }, 0),
                    };
                } else if (level >= 10) {
                    return new Ability[] {
                            new Ability("Scratch", new String[] { "Normal" }, 40),
                            new Ability("Defense Curl", new String[] { "Normal" }, 0),
                            new Ability("Sand Attack", new String[] { "Ground" }, 0),
                    };
                }
            case 28: // Sandslash
                if (level >= 1 && level < 7) {
                    return new Ability[] {
                            new Ability("Scratch", new String[] { "Normal" }, 40),
                            new Ability("Defense Curl", new String[] { "Normal" }, 0),
                            new Ability("Sand Attack", new String[] { "Ground" }, 0),
                            new Ability("Poison Sting", new String[] { "Poison" }, 15)
                    };
                } else if (level >= 7 && level < 13) {
                    return new Ability[] {
                            new Ability("Scratch", new String[] { "Normal" }, 40),
                            new Ability("Defense Curl", new String[] { "Normal" }, 0),
                            new Ability("Sand Attack", new String[] { "Ground" }, 0),
                            new Ability("Poison Sting", new String[] { "Poison" }, 15),
                            new Ability("Rollout", new String[] { "Rock" }, 30)
                    };
                } else if (level >= 13) {
                    return new Ability[] {
                            new Ability("Scratch", new String[] { "Normal" }, 40),
                            new Ability("Defense Curl", new String[] { "Normal" }, 0),
                            new Ability("Sand Attack", new String[] { "Ground" }, 0),
                            new Ability("Poison Sting", new String[] { "Poison" }, 15),
                            new Ability("Rollout", new String[] { "Rock" }, 30),
                            new Ability("Swift", new String[] { "Normal" }, 60)
                    };
                }
            case 29: // Nidoran♀
                if (level >= 1 && level < 7) {
                    return new Ability[] {
                            new Ability("Growl", new String[] { "Normal" }, 0),
                            new Ability("Tackle", new String[] { "Normal" }, 40)
                    };
                } else if (level >= 7 && level < 13) {
                    return new Ability[] {
                            new Ability("Scratch", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 0),
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                    };
                } else if (level >= 13) {
                    return new Ability[] {
                            new Ability("Scratch", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 0),
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Poison Sting", new String[] { "Poison" }, 15)
                    };
                }
            case 30: // Nidorina
                if (level >= 1 && level < 7) {
                    return new Ability[] {
                            new Ability("Scratch", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 0),
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Poison Sting", new String[] { "Poison" }, 15)
                    };
                } else if (level >= 7 && level < 13) {
                    return new Ability[] {
                            new Ability("Scratch", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 0),
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Poison Sting", new String[] { "Poison" }, 15),
                            new Ability("Bite", new String[] { "Dark" }, 60)
                    };
                } else if (level >= 13) {
                    return new Ability[] {
                            new Ability("Scratch", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 0),
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Poison Sting", new String[] { "Poison" }, 15),
                            new Ability("Bite", new String[] { "Dark" }, 60),
                            new Ability("Fury Swipes", new String[] { "Normal" }, 18)
                    };
                }
            case 31: // Nidoqueen
                if (level >= 1 && level < 7) {
                    return new Ability[] {
                            new Ability("Scratch", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 0),
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Poison Sting", new String[] { "Poison" }, 15),
                            new Ability("Bite", new String[] { "Dark" }, 60),
                            new Ability("Fury Swipes", new String[] { "Normal" }, 18)
                    };
                } else if (level >= 7 && level < 13) {
                    return new Ability[] {
                            new Ability("Scratch", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 0),
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Poison Sting", new String[] { "Poison" }, 15),
                            new Ability("Bite", new String[] { "Dark" }, 60),
                            new Ability("Fury Swipes", new String[] { "Normal" }, 18),
                            new Ability("Body Slam", new String[] { "Normal" }, 85)
                    };
                } else if (level >= 13) {
                    return new Ability[] {
                            new Ability("Scratch", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 0),
                            new Ability("Tackle", new String[] { "Normal" }, 40),
                            new Ability("Poison Sting", new String[] { "Poison" }, 15),
                            new Ability("Bite", new String[] { "Dark" }, 60),
                            new Ability("Fury Swipes", new String[] { "Normal" }, 18),
                            new Ability("Body Slam", new String[] { "Normal" }, 85),
                            new Ability("Superpower", new String[] { "Fighting" }, 120)
                    };
                }
            case 32: // Nidoran♂
                if (level >= 1 && level < 7) {
                    return new Ability[] {
                            new Ability("Peck", new String[] { "Flying" }, 35),
                            new Ability("Leer", new String[] { "Normal" }, 0)
                    };
                } else if (level >= 7 && level < 13) {
                    return new Ability[] {
                            new Ability("Peck", new String[] { "Flying" }, 35),
                            new Ability("Leer", new String[] { "Normal" }, 0),
                            new Ability("Focus Energy", new String[] { "Normal" }, 0)
                    };
                } else if (level >= 13) {
                    return new Ability[] {
                            new Ability("Peck", new String[] { "Flying" }, 35),
                            new Ability("Leer", new String[] { "Normal" }, 0),
                            new Ability("Focus Energy", new String[] { "Normal" }, 0),
                            new Ability("Horn Attack", new String[] { "Normal" }, 65)
                    };
                }
            case 33: // Nidorino
                if (level >= 1 && level < 7) {
                    return new Ability[] {
                            new Ability("Peck", new String[] { "Flying" }, 35),
                            new Ability("Leer", new String[] { "Normal" }, 0),
                            new Ability("Focus Energy", new String[] { "Normal" }, 0),
                            new Ability("Horn Attack", new String[] { "Normal" }, 65)
                    };
                } else if (level >= 7 && level < 13) {
                    return new Ability[] {
                            new Ability("Peck", new String[] { "Flying" }, 35),
                            new Ability("Leer", new String[] { "Normal" }, 0),
                            new Ability("Focus Energy", new String[] { "Normal" }, 0),
                            new Ability("Horn Attack", new String[] { "Normal" }, 65),
                            new Ability("Double Kick", new String[] { "Fighting" }, 30)
                    };
                } else if (level >= 13) {
                    return new Ability[] {
                            new Ability("Peck", new String[] { "Flying" }, 35),
                            new Ability("Leer", new String[] { "Normal" }, 0),
                            new Ability("Focus Energy", new String[] { "Normal" }, 0),
                            new Ability("Horn Attack", new String[] { "Normal" }, 65),
                            new Ability("Double Kick", new String[] { "Fighting" }, 30),
                            new Ability("Poison Sting", new String[] { "Poison" }, 15)
                    };
                }
            case 34: // Nidoking
                if (level >= 1 && level < 7) {
                    return new Ability[] {
                            new Ability("Peck", new String[] { "Flying" }, 35),
                            new Ability("Leer", new String[] { "Normal" }, 0),
                            new Ability("Focus Energy", new String[] { "Normal" }, 0),
                            new Ability("Horn Attack", new String[] { "Normal" }, 65),
                            new Ability("Double Kick", new String[] { "Fighting" }, 30),
                            new Ability("Poison Sting", new String[] { "Poison" }, 15)
                    };
                } else if (level >= 7 && level < 13) {
                    return new Ability[] {
                            new Ability("Peck", new String[] { "Flying" }, 35),
                            new Ability("Leer", new String[] { "Normal" }, 0),
                            new Ability("Focus Energy", new String[] { "Normal" }, 0),
                            new Ability("Horn Attack", new String[] { "Normal" }, 65),
                            new Ability("Double Kick", new String[] { "Fighting" }, 30),
                            new Ability("Poison Sting", new String[] { "Poison" }, 15),
                            new Ability("Thrash", new String[] { "Normal" }, 120)
                    };
                } else if (level >= 13) {
                    return new Ability[] {
                            new Ability("Peck", new String[] { "Flying" }, 35),
                            new Ability("Leer", new String[] { "Normal" }, 0),
                            new Ability("Focus Energy", new String[] { "Normal" }, 0),
                            new Ability("Horn Attack", new String[] { "Normal" }, 65),
                            new Ability("Double Kick", new String[] { "Fighting" }, 30),
                            new Ability("Poison Sting", new String[] { "Poison" }, 15),
                            new Ability("Thrash", new String[] { "Normal" }, 120),
                            new Ability("Earth Power", new String[] { "Ground" }, 90)
                    };
                }
            case 35: // Clefairy
                if (level >= 1 && level < 7) {
                    return new Ability[] {
                            new Ability("Pound", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 0)
                    };
                } else if (level >= 7 && level < 13) {
                    return new Ability[] {
                            new Ability("Pound", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 0),
                            new Ability("Sing", new String[] { "Normal" }, 0)
                    };
                } else if (level >= 13) {
                    return new Ability[] {
                            new Ability("Pound", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 0),
                            new Ability("Sing", new String[] { "Normal" }, 0),
                            new Ability("Double Slap", new String[] { "Normal" }, 15)
                    };
                }
            case 36: // Clefable
                if (level >= 1 && level < 7) {
                    return new Ability[] {
                            new Ability("Pound", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 0),
                            new Ability("Sing", new String[] { "Normal" }, 0),
                            new Ability("Double Slap", new String[] { "Normal" }, 15)
                    };
                } else if (level >= 7 && level < 13) {
                    return new Ability[] {
                            new Ability("Pound", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 0),
                            new Ability("Sing", new String[] { "Normal" }, 0),
                            new Ability("Double Slap", new String[] { "Normal" }, 15),
                            new Ability("Minimize", new String[] { "Normal" }, 0)
                    };
                } else if (level >= 13) {
                    return new Ability[] {
                            new Ability("Pound", new String[] { "Normal" }, 40),
                            new Ability("Growl", new String[] { "Normal" }, 0),
                            new Ability("Sing", new String[] { "Normal" }, 0),
                            new Ability("Double Slap", new String[] { "Normal" }, 15),
                            new Ability("Minimize", new String[] { "Normal" }, 0),
                            new Ability("Metronome", new String[] { "Normal" }, 0)
                    };
                }
            case 37: // Vulpix
                if (level >= 1 && level < 7) {
                    return new Ability[] {
                            new Ability("Ember", new String[] { "Fire" }, 40),
                            new Ability("Tail Whip", new String[] { "Normal" }, 0)
                    };
                } else if (level >= 7 && level < 13) {
                    return new Ability[] {
                            new Ability("Ember", new String[] { "Fire" }, 40),
                            new Ability("Tail Whip", new String[] { "Normal" }, 0),
                            new Ability("Quick Attack", new String[] { "Normal" }, 40)
                    };
                } else if (level >= 13) {
                    return new Ability[] {
                            new Ability("Ember", new String[] { "Fire" }, 40),
                            new Ability("Tail Whip", new String[] { "Normal" }, 0),
                            new Ability("Quick Attack", new String[] { "Normal" }, 40),
                            new Ability("Will-O-Wisp", new String[] { "Fire" }, 0)
                    };
                }
            case 38: // Ninetales
                if (level >= 1 && level < 7) {
                    return new Ability[] {
                            new Ability("Ember", new String[] { "Fire" }, 40),
                            new Ability("Tail Whip", new String[] { "Normal" }, 0),
                            new Ability("Quick Attack", new String[] { "Normal" }, 40),
                            new Ability("Will-O-Wisp", new String[] { "Fire" }, 0)
                    };
                } else if (level >= 7 && level < 13) {
                    return new Ability[] {
                            new Ability("Ember", new String[] { "Fire" }, 40),
                            new Ability("Tail Whip", new String[] { "Normal" }, 0),
                            new Ability("Quick Attack", new String[] { "Normal" }, 40),
                            new Ability("Will-O-Wisp", new String[] { "Fire" }, 0),
                            new Ability("Confuse Ray", new String[] { "Ghost" }, 0)
                    };
                } else if (level >= 13) {
                    return new Ability[] {
                            new Ability("Ember", new String[] { "Fire" }, 40),
                            new Ability("Tail Whip", new String[] { "Normal" }, 0),
                            new Ability("Quick Attack", new String[] { "Normal" }, 40),
                            new Ability("Will-O-Wisp", new String[] { "Fire" }, 0),
                            new Ability("Confuse Ray", new String[] { "Ghost" }, 0),
                            new Ability("Flamethrower", new String[] { "Fire" }, 90)
                    };
                }
            case 39: // Jigglypuff
                if (level >= 1 && level < 7) {
                    return new Ability[] {
                            new Ability("Pound", new String[] { "Normal" }, 40),
                            new Ability("Sing", new String[] { "Normal" }, 0)
                    };
                } else if (level >= 7 && level < 13) {
                    return new Ability[] {
                            new Ability("Pound", new String[] { "Normal" }, 40),
                            new Ability("Sing", new String[] { "Normal" }, 0),
                            new Ability("Disable", new String[] { "Normal" }, 0)
                    };
                } else if (level >= 13) {
                    return new Ability[] {
                            new Ability("Pound", new String[] { "Normal" }, 40),
                            new Ability("Sing", new String[] { "Normal" }, 0),
                            new Ability("Disable", new String[] { "Normal" }, 0),
                            new Ability("Defense Curl", new String[] { "Normal" }, 0)
                    };
                }
            case 40: // Wigglytuff
                if (level >= 1 && level < 7) {
                    return new Ability[] {
                            new Ability("Pound", new String[] { "Normal" }, 40),
                            new Ability("Sing", new String[] { "Normal" }, 0),
                            new Ability("Disable", new String[] { "Normal" }, 0),
                            new Ability("Defense Curl", new String[] { "Normal" }, 0)
                    };
                } else if (level >= 7 && level < 13) {
                    return new Ability[] {
                            new Ability("Pound", new String[] { "Normal" }, 40),
                            new Ability("Sing", new String[] { "Normal" }, 0),
                            new Ability("Disable", new String[] { "Normal" }, 0),
                            new Ability("Defense Curl", new String[] { "Normal" }, 0),
                            new Ability("Double Slap", new String[] { "Normal" }, 15)
                    };
                } else if (level >= 13) {
                    return new Ability[] {
                            new Ability("Pound", new String[] { "Normal" }, 40),
                            new Ability("Sing", new String[] { "Normal" }, 0),
                            new Ability("Disable", new String[] { "Normal" }, 0),
                            new Ability("Defense Curl", new String[] { "Normal" }, 0),
                            new Ability("Double Slap", new String[] { "Normal" }, 15),
                            new Ability("Body Slam", new String[] { "Normal" }, 85)
                    };
                }
            case 41: // Zubat
                if (level >= 1 && level < 7) {
                    return new Ability[] {
                            new Ability("Leech Life", new String[] { "Bug" }, 20),
                            new Ability("Supersonic", new String[] { "Normal" }, 0)
                    };
                } else if (level >= 7 && level < 13) {
                    return new Ability[] {
                            new Ability("Leech Life", new String[] { "Bug" }, 20),
                            new Ability("Supersonic", new String[] { "Normal" }, 0),
                            new Ability("Astonish", new String[] { "Ghost" }, 30)
                    };
                } else if (level >= 13) {
                    return new Ability[] {
                            new Ability("Leech Life", new String[] { "Bug" }, 20),
                            new Ability("Supersonic", new String[] { "Normal" }, 0),
                            new Ability("Astonish", new String[] { "Ghost" }, 30),
                            new Ability("Bite", new String[] { "Dark" }, 60)
                    };
                }
            case 42: // Golbat
                if (level >= 1 && level < 7) {
                    {
                        return new Ability[] {
                                new Ability("Leech Life", new String[] { "Bug" }, 20),
                                new Ability("Supersonic", new String[] { "Normal" }, 0),
                                new Ability("Astonish", new String[] { "Ghost" }, 30),
                                new Ability("Bite", new String[] { "Dark" }, 60)
                        };
                    }
                } else if (level >= 7 && level < 13) {
                    return new Ability[] {
                            new Ability("Leech Life", new String[] { "Bug" }, 20),
                            new Ability("Supersonic", new String[] { "Normal" }, 0),
                            new Ability("Astonish", new String[] { "Ghost" }, 30),
                            new Ability("Bite", new String[] { "Dark" }, 60),
                            new Ability("Wing Attack", new String[] { "Flying" }, 60)
                    };
                } else if (level >= 13) {
                    return new Ability[] {
                            new Ability("Leech Life", new String[] { "Bug" }, 20),
                            new Ability("Supersonic", new String[] { "Normal" }, 0),
                            new Ability("Astonish", new String[] { "Ghost" }, 30),
                            new Ability("Bite", new String[] { "Dark" }, 60),
                            new Ability("Wing Attack", new String[] { "Flying" }, 60),
                            new Ability("Air Cutter", new String[] { "Flying" }, 60)
                    };
                }
            default:
                return null;
        }
    }
}
