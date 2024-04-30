package Pokemon;

public class CreateObjectsPokemon extends Pokemon {
    public CreateObjectsPokemon(String name, Ability[] abilities, Stats stats, String[] types, int baseExperience,
            Sprites sprite, int lvl) {
        super(name, abilities, stats, types, baseExperience, sprite, lvl);
    }

    public static Pokemon getPokemon(int num) {
        switch (num) {
            case 1:
                return new Pokemon(
                        "charmander",
                        new Ability[] {
                                new Ability("scratch",
                                        "normal",
                                        40),
                                new Ability("ember",
                                        "fire",
                                        40)
                        },
                        new Stats("39",
                                "52",
                                "43",
                                "65"),
                        new String[] { "fire" },
                        62,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/4.png"),
                        5);
            case 2:
                return new Pokemon(
                        "charmeleon",
                        new Ability[] {
                                new Ability("scratch",
                                        "normal",
                                        40),
                                new Ability("ember",
                                        "fire",
                                        40)
                        },
                        new Stats("58",
                                "64",
                                "58",
                                "80"),
                        new String[] { "fire" },
                        142,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/5.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/5.png"),
                        5);
            case 3:
                return new Pokemon(
                        "charizard",
                        new Ability[] {
                                new Ability("scratch",
                                        "normal",
                                        40),
                                new Ability("ember",
                                        "fire",
                                        40)
                        },
                        new Stats("78",
                                "84",
                                "78",
                                "100"),
                        new String[] { "fire", "flying" },
                        240,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/6.png"),
                        5);
            case 4:
                return new Pokemon(
                        "bulbasaur",
                        new Ability[] {
                                new Ability("tackle",
                                        "normal",
                                        40),
                                new Ability("vine whip",
                                        "grass",
                                        45)
                        },
                        new Stats("45",
                                "49",
                                "49",
                                "45"),
                        new String[] { "grass", "poison" },
                        64,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/1.png"),
                        5);
            case 5:
                return new Pokemon(
                        "ivysaur",
                        new Ability[] {
                                new Ability("tackle",
                                        "normal",
                                        40),
                                new Ability("vine whip",
                                        "grass",
                                        45)
                        },
                        new Stats("60",
                                "62",
                                "63",
                                "60"),
                        new String[] { "grass", "poison" },
                        142,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/2.png"),
                        5);
            case 6:
                return new Pokemon(
                        "venusaur",
                        new Ability[] {
                                new Ability("tackle",
                                        "normal",
                                        40),
                                new Ability("vine whip",
                                        "grass",
                                        45)
                        },
                        new Stats("80",
                                "82",
                                "83",
                                "80"),
                        new String[] { "grass", "poison" },
                        236,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/3.png"),
                        5);
            case 7:
                return new Pokemon(
                        "squirtle",
                        new Ability[] {
                                new Ability("tackle",
                                        "normal",
                                        40),
                                new Ability("water gun",
                                        "water",
                                        40)
                        },
                        new Stats("44",
                                "48",
                                "65",
                                "43"),
                        new String[] { "water" },
                        63,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/7.png"),
                        5);
            case 8:
                return new Pokemon(
                        "wartortle",
                        new Ability[] {
                                new Ability("tackle",
                                        "normal",
                                        40),
                                new Ability("water gun",
                                        "water",
                                        40)
                        },
                        new Stats("59",
                                "63",
                                "80",
                                "58"),
                        new String[] { "water" },
                        142,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/8.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/8.png"),
                        5);
            case 9:
                return new Pokemon(
                        "blastoise",
                        new Ability[] {
                                new Ability("tackle",
                                        "normal",
                                        40),
                                new Ability("water gun",
                                        "water",
                                        40)
                        },
                        new Stats("79",
                                "83",
                                "100",
                                "78"),
                        new String[] { "water" },
                        239,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/9.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/9.png"),
                        5);

            case 10:
                return new Pokemon(
                        "caterpie",
                        new Ability[] {
                                new Ability("tackle", "normal", 40),
                                new Ability("string shot", "bug", 40)
                        },
                        new Stats("45", "30", "35", "45"),
                        new String[] { "bug" },
                        39,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/10.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/10.png"),
                        5);
            case 11:
                return new Pokemon(
                        "metapod",
                        new Ability[] {
                                new Ability("tackle", "normal", 40),
                                new Ability("harden", "normal", 40)
                        },
                        new Stats("50", "20", "55", "30"),
                        new String[] { "bug" },
                        72,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/11.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/11.png"),
                        5);
            case 12:
                return new Pokemon(
                        "butterfree",
                        new Ability[] {
                                new Ability("tackle", "normal", 40),
                                new Ability("confusion", "psychic", 50)
                        },
                        new Stats("60", "45", "50", "70"),
                        new String[] { "bug", "flying" },
                        178,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/12.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/12.png"),
                        5);
            case 13:
                return new Pokemon(
                        "weedle",
                        new Ability[] {
                                new Ability("poison sting", "poison", 40),
                                new Ability("s tring shot", "bug", 40)
                        },
                        new Stats("40", "35", "30", "50"),
                        new String[] { "bug", "poison" },
                        39,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/13.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/13.png"),
                        5);
            case 14:
                return new Pokemon(
                        "kakuna",
                        new Ability[] {
                                new Ability("poison sting", "poison", 40),
                                new Ability("harden", "normal", 40)
                        },
                        new Stats("45", "25", "50", "35"),
                        new String[] { "bug", "poison" },
                        72,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/14.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/14.png"),
                        5);
            case 15:
                return new Pokemon(
                        "beedrill",
                        new Ability[] {
                                new Ability("poison sting", "poison", 40),
                                new Ability("twineedle", "bug", 50)
                        },
                        new Stats("65", "90", "40", "75"),
                        new String[] { "bug", "poison" },
                        178,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/15.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/15.png"),
                        5);

            case 16:
                return new Pokemon(
                        "pidgey",
                        new Ability[] {
                                new Ability("tackle", "normal", 40),
                                new Ability("gust", "flying", 40)
                        },
                        new Stats("40", "45", "40", "56"),
                        new String[] { "normal", "flying" },
                        50,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/16.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/16.png"),
                        5);

            case 17:
                return new Pokemon(
                        "pidgeotto",
                        new Ability[] {
                                new Ability("tackle", "normal", 40),
                                new Ability("gust", "flying", 40)
                        },
                        new Stats("63", "60", "55", "71"),
                        new String[] { "normal", "flying" },
                        122,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/17.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/17.png"),
                        5);

            case 18:
                return new Pokemon(
                        "pidgeot",
                        new Ability[] {
                                new Ability("tackle", "normal", 40),
                                new Ability("gust", "flying", 40)
                        },
                        new Stats("83", "80", "75", "101"),
                        new String[] { "normal", "flying" },
                        216,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/18.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/18.png"),
                        5);

            case 19:
                return new Pokemon(
                        "rattata",
                        new Ability[] {
                                new Ability("tackle", "normal", 40),
                                new Ability("quick attack", "normal", 40)
                        },
                        new Stats("30", "56", "35", "72"),
                        new String[] { "normal" },
                        51,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/19.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/19.png"),
                        5);

            case 20:
                return new Pokemon(
                        "raticate",
                        new Ability[] {
                                new Ability("tackle", "normal", 40),
                                new Ability("quick attack", "normal", 40)
                        },
                        new Stats("55", "81", "60", "97"),
                        new String[] { "normal" },
                        145,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/20.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/20.png"),
                        5);

            case 21:
                return new Pokemon(
                        "spearow",
                        new Ability[] {
                                new Ability("peck", "flying", 35),
                                new Ability("drill peck", "flying", 80)
                        },
                        new Stats("40", "60", "30", "70"),
                        new String[] { "normal", "flying" },
                        52,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/21.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/21.png"),
                        5);

            case 22:
                return new Pokemon(
                        "fearow",
                        new Ability[] {
                                new Ability("peck", "flying", 35),
                                new Ability("drill peck", "flying", 80)
                        },
                        new Stats("65", "90", "65", "100"),
                        new String[] { "normal", "flying" },
                        155,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/22.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/22.png"),
                        5);

            case 23:
                return new Pokemon(
                        "ekans",
                        new Ability[] {
                                new Ability("poison sting", "poison", 40),
                                new Ability("bite", "dark", 60)
                        },
                        new Stats("35", "60", "44", "55"),
                        new String[] { "poison" },
                        58,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/23.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/23.png"),
                        5);

            case 24:
                return new Pokemon(
                        "arbok",
                        new Ability[] {
                                new Ability("poison sting", "poison", 40),
                                new Ability("bite", "dark", 60)
                        },
                        new Stats("60", "85", "69", "80"),
                        new String[] { "poison" },
                        153,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/24.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/24.png"),
                        5);

            case 25:
                return new Pokemon(
                        "pikachu",
                        new Ability[] {
                                new Ability("quick attack", "normal", 40),
                                new Ability("thunder shock", "electric", 40)
                        },
                        new Stats("35", "55", "40", "90"),
                        new String[] { "electric" },
                        112,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/25.png"),
                        5);

            case 26:
                return new Pokemon(
                        "raichu",
                        new Ability[] {
                                new Ability("quick attack", "normal", 40),
                                new Ability("thunder shock", "electric", 40)
                        },
                        new Stats("60", "90", "55", "100"),
                        new String[] { "electric" },
                        218,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/26.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/26.png"),
                        5);

            case 27:
                return new Pokemon(
                        "sandshrew",
                        new Ability[] {
                                new Ability("scratch", "normal", 40),
                                new Ability("sand attack", "ground", 40)
                        },
                        new Stats("50", "75", "85", "40"),
                        new String[] { "ground" },
                        60,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/27.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/27.png"),
                        5);

            case 28:
                return new Pokemon(
                        "sandslash",
                        new Ability[] {
                                new Ability("scratch", "normal", 40),
                                new Ability("sand attack", "ground", 40)
                        },
                        new Stats("75", "100", "110", "65"),
                        new String[] { "ground" },
                        158,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/28.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/28.png"),
                        5);

            case 29:
                return new Pokemon(
                        "nidoranF",
                        new Ability[] {
                                new Ability("scratch", "normal", 40),
                                new Ability("poison sting", "poison", 40)
                        },
                        new Stats("55", "47", "52", "41"),
                        new String[] { "poison" },
                        55,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/29.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/29.png"),
                        5);

            case 30:
                return new Pokemon(
                        "nidorina",
                        new Ability[] {
                                new Ability("scratch", "normal", 40),
                                new Ability("poison sting", "poison", 40)
                        },
                        new Stats("70", "62", "67", "56"),
                        new String[] { "poison" },
                        128,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/30.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/30.png"),
                        5);
            case 31:
                return new Pokemon(
                        "nidoqueen",
                        new Ability[] {
                                new Ability("scratch", "normal", 40),
                                new Ability("poison sting", "poison", 40)
                        },
                        new Stats("90", "82", "87", "76"),
                        new String[] { "poison", "ground" },
                        227,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/31.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/31.png"),
                        5);
            case 32:
                return new Pokemon(
                        "nidoranM",
                        new Ability[] {
                                new Ability("scratch", "normal", 40),
                                new Ability("poison sting", "poison", 40)
                        },
                        new Stats("46", "57", "40", "50"),
                        new String[] { "poison" },
                        55,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/32.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/32.png"),
                        5);
            case 33:
                return new Pokemon(
                        "nidorino",
                        new Ability[] {
                                new Ability("scratch", "normal", 40),
                                new Ability("poison sting", "poison", 40)
                        },
                        new Stats("61", "72", "57", "65"),
                        new String[] { "poison" },
                        128,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/33.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/33.png"),
                        5);
            case 34:
                return new Pokemon(
                        "nidoking",
                        new Ability[] {
                                new Ability("scratch", "normal", 40),
                                new Ability("poison sting", "poison", 40)
                        },
                        new Stats("81", "102", "77", "85"),
                        new String[] { "poison", "ground" },
                        227,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/34.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/34.png"),
                        5);
            case 35:
                return new Pokemon(
                        "clefairy",
                        new Ability[] {
                                new Ability("pound",
                                        "normal",
                                        40),
                                new Ability("double slap",
                                        "normal",
                                        45)
                        },
                        new Stats("70",
                                "45",
                                "48",
                                "35"),
                        new String[] { "fairy" },
                        113,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/35.png"),
                        5);
            case 36:
                return new Pokemon(
                        "clefable",
                        new Ability[] {
                                new Ability("pound",
                                        "normal",
                                        40),
                                new Ability("double slap",
                                        "normal",
                                        45)
                        },
                        new Stats("95",
                                "70",
                                "73",
                                "60"),
                        new String[] { "fairy" },
                        217,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/36.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/36.png"),
                        5);
            case 37:
                return new Pokemon(
                        "vulpix",
                        new Ability[] {
                                new Ability("ember",
                                        "fire",
                                        40),
                                new Ability("quick attack",
                                        "normal",
                                        40)
                        },
                        new Stats("38",
                                "41",
                                "40",
                                "65"),
                        new String[] { "fire" },
                        60,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/37.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/37.png"),
                        5);
            case 38:
                return new Pokemon(
                        "ninetales",
                        new Ability[] {
                                new Ability("ember",
                                        "fire",
                                        40),
                                new Ability("quick attack",
                                        "normal",
                                        40)
                        },
                        new Stats("73",
                                "76",
                                "75",
                                "100"),
                        new String[] { "fire" },
                        177,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/38.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/38.png"),
                        5);
            case 39:
                return new Pokemon(
                        "jigglypuff",
                        new Ability[] {
                                new Ability("pound",
                                        "normal",
                                        40),
                                new Ability("double slap",
                                        "normal",
                                        45)
                        },
                        new Stats("115",
                                "45",
                                "20",
                                "20"),
                        new String[] { "normal", "fairy" },
                        95,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/39.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/39.png"),
                        5);
            case 40:
                return new Pokemon(
                        "wigglytuff",
                        new Ability[] {
                                new Ability("pound",
                                        "normal",
                                        40),
                                new Ability("double slap",
                                        "normal",
                                        45)
                        },
                        new Stats("140",
                                "70",
                                "45",
                                "45"),
                        new String[] { "normal", "fairy" },
                        196,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/40.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/40.png"),
                        5);
            case 41:
                return new Pokemon(
                        "zubat",
                        new Ability[] {
                                new Ability("leech life",
                                        "bug",
                                        40),
                                new Ability("supersonic",
                                        "normal",
                                        40)
                        },
                        new Stats("40",
                                "45",
                                "35",
                                "55"),
                        new String[] { "poison", "flying" },
                        49,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/41.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/41.png"),
                        5);

            case 42:
                return new Pokemon(
                        "golbat",
                        new Ability[] {
                                new Ability("leech life",
                                        "bug",
                                        40),
                                new Ability("supersonic",
                                        "normal",
                                        40)
                        },
                        new Stats("75",
                                "80",
                                "70",
                                "90"),
                        new String[] { "poison", "flying" },
                        159,
                        new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/42.png",
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/42.png"),
                        5);
            default:
                return null;
        }

    }

}
