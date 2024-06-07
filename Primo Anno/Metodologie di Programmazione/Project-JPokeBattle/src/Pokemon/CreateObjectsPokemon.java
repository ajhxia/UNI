package Pokemon;

import java.util.ArrayList;

/*
* Classe che rappresenta uno switch di Pokémon.
* Questa classe permette di creare oggetti di tipo Pokemon.
* Grazie alla funzione getPokemon è possibile creare un oggetto di tipo Pokemon in base al parametro passato in input
*/

public class CreateObjectsPokemon extends Pokemon {
        public CreateObjectsPokemon(String name, int index, ArrayList<Ability> abilities, String start, Stats stats,
                        String[] types, int baseExperience,
                        Sprites sprite, int lvl, int lvlEvolutions, Pokemon[] evolutions) {
                super(name, index, abilities, start, stats, types, baseExperience, sprite, lvl, lvlEvolutions, evolutions,
                                false);
        }

        private static int randomLevel = 0;

        private static int getLevelRandom() {
                randomLevel = (int) (Math.random() * 5) + 1;
                return randomLevel;
        }

        public static Pokemon getPokemon(int num) {
                getLevelRandom();

                switch (num) {
                        case 1:
                                return new Pokemon(
                                                "bulbasaur",
                                                1,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(45, 49, 49, 45, randomLevel, 45),
                                                new String[] { "grass", "poison" },
                                                64,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/1.png"),
                                                randomLevel,
                                                13,
                                                new Pokemon[] { getPokemon(2), getPokemon(3) },
                                                false);
                        case 2:
                                return new Pokemon(
                                                "ivysaur",
                                                2,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(60, 62, 63, 60, randomLevel, 60),
                                                new String[] { "grass", "poison" },
                                                142,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/2.png"),
                                                randomLevel,
                                                16,
                                                new Pokemon[] { getPokemon(3) }, false);

                        case 3:
                                return new Pokemon(
                                                "venusaur",
                                                3,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(80, 82, 83, 80, randomLevel, 80),
                                                new String[] { "grass", "poison" },
                                                236,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/3.png"),
                                                randomLevel,
                                                0,
                                                new Pokemon[] { null }, false);
                        case 4:
                                return new Pokemon(
                                                "charmander",
                                                4,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(39, 52, 43, 65, randomLevel, 39),
                                                new String[] { "fire" },
                                                62,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/4.png"),
                                                randomLevel,
                                                14,
                                                new Pokemon[] { getPokemon(5), getPokemon(6) }, false);

                        case 5:
                                return new Pokemon(
                                                "charmeleon",
                                                5,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(58, 64, 58, 80, randomLevel, 58),
                                                new String[] { "fire" },
                                                142,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/5.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/5.png"),
                                                randomLevel,
                                                17,
                                                new Pokemon[] { getPokemon(6) }, false);

                        case 6:
                                return new Pokemon(
                                                "charizard",
                                                6,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(78, 84, 78, 100, randomLevel, 78),
                                                new String[] { "fire", "flying" },
                                                240,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/6.png"),
                                                randomLevel,
                                                0,
                                                new Pokemon[] { null }, false);

                        case 7:
                                return new Pokemon(
                                                "squirtle",
                                                7,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(44, 48, 65, 43, randomLevel, 44),
                                                new String[] { "water" },
                                                63,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/7.png"),
                                                randomLevel,
                                                12,
                                                new Pokemon[] { getPokemon(8), getPokemon(9) }, false);
                        case 8:
                                return new Pokemon(
                                                "wartortle",
                                                8,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(59, 63, 80, 58, randomLevel, 59),
                                                new String[] { "water" },
                                                142,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/8.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/8.png"),
                                                randomLevel,
                                                16,
                                                new Pokemon[] { getPokemon(9) }, false);
                        case 9:
                                return new Pokemon(
                                                "blastoise",
                                                9,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(79, 83, 100, 78, randomLevel, 79),
                                                new String[] { "water" },
                                                239,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/9.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/9.png"),
                                                randomLevel,
                                                0,
                                                new Pokemon[] { null }, false);

                        case 10:
                                return new Pokemon(
                                                "caterpie",
                                                10,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(45, 30, 35, 45, randomLevel, 45),
                                                new String[] { "bug" },
                                                39,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/10.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/10.png"),
                                                randomLevel,
                                                7,
                                                new Pokemon[] { getPokemon(11) }, false);
                        case 11:
                                return new Pokemon(
                                                "metapod",
                                                11,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(50, 20, 55, 30, randomLevel, 50),
                                                new String[] { "bug" },
                                                72,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/11.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/11.png"),
                                                randomLevel,
                                                16,
                                                new Pokemon[] { getPokemon(12) }, false);
                        case 12:
                                return new Pokemon(
                                                "butterfree",
                                                12,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(60, 45, 50, 70, randomLevel, 60),
                                                new String[] { "bug", "flying" },
                                                178,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/12.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/12.png"),
                                                randomLevel,
                                                0,
                                                new Pokemon[] { null }, false);
                        case 13:
                                return new Pokemon(
                                                "weedle",
                                                13,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(40, 35, 30, 50, randomLevel, 40),
                                                new String[] { "bug", "poison" },
                                                39,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/13.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/13.png"),
                                                randomLevel,
                                                7,
                                                new Pokemon[] { getPokemon(14) }, false);
                        case 14:
                                return new Pokemon(
                                                "kakuna",
                                                14,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(45, 25, 50, 35, randomLevel, 45),
                                                new String[] { "bug", "poison" },
                                                72,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/14.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/14.png"),
                                                randomLevel,
                                                10,
                                                new Pokemon[] { getPokemon(15) }, false);
                        case 15:
                                return new Pokemon(
                                                "beedrill",
                                                15,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(65, 90, 40, 75, randomLevel, 65),
                                                new String[] { "bug", "poison" },
                                                178,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/15.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/15.png"),
                                                randomLevel,
                                                0,
                                                new Pokemon[] { null }, false);

                        case 16:
                                return new Pokemon(
                                                "pidgey",
                                                16,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(40, 45, 40, 56, randomLevel, 40),
                                                new String[] { "normal", "flying" },
                                                50,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/16.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/16.png"),
                                                randomLevel,
                                                11,
                                                new Pokemon[] { getPokemon(17) }, false);

                        case 17:
                                return new Pokemon(
                                                "pidgeotto",
                                                17,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(63, 60, 55, 71, randomLevel, 63),
                                                new String[] { "normal", "flying" },
                                                122,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/17.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/17.png"),
                                                randomLevel,
                                                19,
                                                new Pokemon[] { getPokemon(18) }, false);

                        case 18:
                                return new Pokemon(
                                                "pidgeot",
                                                18,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(83, 80, 75, 101, randomLevel, 83),
                                                new String[] { "normal", "flying" },
                                                216,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/18.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/18.png"),
                                                randomLevel,
                                                0,
                                                new Pokemon[] { null }, false);

                        case 19:
                                return new Pokemon(
                                                "rattata",
                                                19,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(30, 56, 35, 72, randomLevel, 30),
                                                new String[] { "normal" },
                                                51,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/19.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/19.png"),
                                                randomLevel,
                                                12,
                                                new Pokemon[] { getPokemon(20) }, false);

                        case 20:
                                return new Pokemon(
                                                "raticate",
                                                20,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(55, 81, 60, 97, randomLevel, 55),
                                                new String[] { "normal" },
                                                145,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/20.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/20.png"),
                                                randomLevel,
                                                0,
                                                new Pokemon[] { null }, false);

                        case 21:
                                return new Pokemon(
                                                "spearow",
                                                21,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(40, 60, 30, 70, randomLevel, 40),
                                                new String[] { "normal", "flying" },
                                                52,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/21.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/21.png"),
                                                randomLevel,
                                                13,
                                                new Pokemon[] { getPokemon(22) }, false);

                        case 22:
                                return new Pokemon(
                                                "fearow",
                                                22,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(65, 90, 65, 100, randomLevel, 65),
                                                new String[] { "normal", "flying" },
                                                155,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/22.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/22.png"),
                                                randomLevel,
                                                0,
                                                new Pokemon[] { null }, false);

                        case 23:
                                return new Pokemon(
                                                "ekans",
                                                23,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(35, 60, 44, 55, randomLevel, 35),
                                                new String[] { "poison" },
                                                58,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/23.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/23.png"),
                                                randomLevel,
                                                12,
                                                new Pokemon[] { getPokemon(24) }, false);

                        case 24:
                                return new Pokemon(
                                                "arbok",
                                                24,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(60, 85, 69, 80, randomLevel, 60),
                                                new String[] { "poison" },
                                                153,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/24.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/24.png"),
                                                randomLevel,
                                                0,
                                                new Pokemon[] { null }, false);

                        case 25:
                                return new Pokemon(
                                                "pikachu",
                                                25,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(35, 55, 40, 90, randomLevel, 35),
                                                new String[] { "electric" },
                                                112,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/25.png"),
                                                randomLevel,
                                                14,
                                                new Pokemon[] { getPokemon(26) }, false);

                        case 26:
                                return new Pokemon(
                                                "raichu",
                                                26,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(60, 90, 55, 100, randomLevel, 60),
                                                new String[] { "electric" },
                                                218,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/26.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/26.png"),
                                                randomLevel,
                                                0,
                                                new Pokemon[] { null }, false);

                        case 27:
                                return new Pokemon(
                                                "sandshrew",
                                                27,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(50, 75, 85, 40, randomLevel, 50),
                                                new String[] { "ground" },
                                                60,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/27.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/27.png"),
                                                randomLevel,
                                                10,
                                                new Pokemon[] { getPokemon(28) }, false);

                        case 28:
                                return new Pokemon(
                                                "sandslash",
                                                28,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(75, 100, 110, 65, randomLevel, 75),
                                                new String[] { "ground" },
                                                158,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/28.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/28.png"),
                                                randomLevel,
                                                0,
                                                new Pokemon[] { null }, false);

                        case 29:
                                return new Pokemon(
                                                "nidoranF",
                                                29,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(55, 47, 52, 41, randomLevel, 55),
                                                new String[] { "poison" },
                                                55,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/29.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/29.png"),
                                                randomLevel,
                                                11,
                                                new Pokemon[] { getPokemon(30) }, false);

                        case 30:
                                return new Pokemon(
                                                "nidorina",
                                                30,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(70, 62, 67, 56, randomLevel, 70),
                                                new String[] { "poison" },
                                                128,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/30.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/30.png"),
                                                randomLevel,
                                                16,
                                                new Pokemon[] { getPokemon(31) }, false);
                        case 31:

                                return new Pokemon(
                                                "nidoqueen",
                                                31,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(90, 82, 87, 76, randomLevel, 90),
                                                new String[] { "poison", "ground" },
                                                227,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/31.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/31.png"),
                                                randomLevel,
                                                0,
                                                new Pokemon[] { null }, false);
                        case 32:
                                return new Pokemon(
                                                "nidoranM",
                                                32,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(46, 57, 40, 50, randomLevel, 46),
                                                new String[] { "poison" },
                                                55,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/32.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/32.png"),
                                                randomLevel,
                                                11,
                                                new Pokemon[] { getPokemon(33) }, false);
                        case 33:
                                return new Pokemon(
                                                "nidorino",
                                                33,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(61, 72, 57, 65, randomLevel, 61),
                                                new String[] { "poison" },
                                                128,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/33.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/33.png"),
                                                randomLevel,
                                                16,
                                                new Pokemon[] { getPokemon(34) }, false);
                        case 34:
                                return new Pokemon(
                                                "nidoking",
                                                34,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(81, 102, 77, 85, randomLevel, 81),
                                                new String[] { "poison", "ground" },
                                                227,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/34.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/34.png"),
                                                randomLevel,
                                                0,
                                                new Pokemon[] { null }, false);
                        case 35:
                                return new Pokemon(
                                                "clefairy",
                                                35,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(70, 45, 48, 35, randomLevel, 70),
                                                new String[] { "fairy" },
                                                113,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/35.png"),
                                                randomLevel,
                                                13,
                                                new Pokemon[] { getPokemon(36) }, false);
                        case 36:
                                return new Pokemon(
                                                "clefable",
                                                36,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(95, 70, 73, 60, randomLevel, 95),
                                                new String[] { "fairy" },
                                                217,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/36.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/36.png"),
                                                randomLevel,
                                                0,
                                                new Pokemon[] { null }, false);
                        case 37:
                                return new Pokemon(
                                                "vulpix",
                                                37,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(38, 41, 40, 65, randomLevel, 38),
                                                new String[] { "fire" },
                                                60,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/37.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/37.png"),
                                                randomLevel,
                                                13,
                                                new Pokemon[] { getPokemon(38) }, false);
                        case 38:
                                return new Pokemon(
                                                "ninetales",
                                                38,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(73, 76, 75, 100, randomLevel, 73),
                                                new String[] { "fire" },
                                                177,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/38.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/38.png"),
                                                randomLevel,
                                                0,
                                                new Pokemon[] { null }, false);
                        case 39:
                                return new Pokemon(
                                                "jigglypuff",
                                                39,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(115, 45, 20, 20, randomLevel, 115),
                                                new String[] { "normal", "fairy" },
                                                95,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/39.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/39.png"),
                                                randomLevel,
                                                13,
                                                new Pokemon[] { getPokemon(40) }, false);
                        case 40:
                                return new Pokemon(
                                                "wigglytuff",
                                                40,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(140, 70, 45, 45, randomLevel, 140),
                                                new String[] { "normal", "fairy" },
                                                196,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/40.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/40.png"),
                                                randomLevel,
                                                0,
                                                new Pokemon[] { null }, false);
                        case 41:
                                return new Pokemon(
                                                "zubat",
                                                41,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(40, 45, 35, 55, randomLevel, 40),
                                                new String[] { "poison", "flying" },
                                                49,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/41.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/41.png"),
                                                randomLevel,
                                                14,
                                                new Pokemon[] { getPokemon(42) }, false);
                        case 42:
                                return new Pokemon(
                                                "golbat",
                                                42,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(75, 80, 70, 90, randomLevel, 75),
                                                new String[] { "poison", "flying" },
                                                159,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/42.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/42.png"),
                                                randomLevel,
                                                0,
                                                new Pokemon[] { null }, false);
                        case 43:
                                return new Pokemon(
                                                "oddish",
                                                43,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(45, 50, 55, 30, randomLevel, 45),
                                                new String[] { "grass", "poison" },
                                                64,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/43.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/43.png"),
                                                randomLevel,
                                                21,
                                                new Pokemon[] { getPokemon(44) }, false);
                        case 44:
                                return new Pokemon(
                                                "gloom",
                                                44,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(60, 65, 70, 40, randomLevel, 60),
                                                new String[] { "grass", "poison" },
                                                138,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/44.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/44.png"),
                                                randomLevel,
                                                21,
                                                new Pokemon[] { getPokemon(45) }, false);
                        case 45:
                                return new Pokemon(
                                                "vileplume",
                                                45,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(75, 80, 85, 50, randomLevel, 75),
                                                new String[] { "grass", "poison" },
                                                221,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/45.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/45.png"),
                                                randomLevel,
                                                0,
                                                new Pokemon[] { null }, false);
                        case 46:
                                return new Pokemon(
                                                "paras",
                                                46,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(35, 70, 55, 25, randomLevel, 35),
                                                new String[] { "bug", "grass" },
                                                57,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/46.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/46.png"),
                                                randomLevel,
                                                24,
                                                new Pokemon[] { getPokemon(47) }, false);
                        case 47:
                                return new Pokemon(
                                                "parasect",
                                                47,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(60, 95, 80, 30, randomLevel, 60),
                                                new String[] { "bug", "grass" },
                                                142,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/47.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/47.png"),
                                                randomLevel,
                                                0,
                                                new Pokemon[] { null }, false);
                        case 48:
                                return new Pokemon(
                                                "venonat",
                                                48,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(60, 55, 50, 45, randomLevel, 60),
                                                new String[] { "bug", "poison" },
                                                61,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/48.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/48.png"),
                                                randomLevel,
                                                31,
                                                new Pokemon[] { getPokemon(49) }, false);
                        case 49:
                                return new Pokemon(
                                                "venomoth",
                                                49,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(70, 65, 60, 90, randomLevel, 70),
                                                new String[] { "bug", "poison" },
                                                158,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/49.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/49.png"),
                                                randomLevel,
                                                0,
                                                new Pokemon[] { null }, false);
                        case 50:
                                return new Pokemon(
                                                "diglett",
                                                50,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(10, 55, 25, 95, randomLevel, 10),
                                                new String[] { "ground" },
                                                53,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/50.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/50.png"),
                                                randomLevel,
                                                26,
                                                new Pokemon[] { getPokemon(51) }, false);
                        case 51:
                                return new Pokemon(
                                                "dugtrio",
                                                51,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(35, 80, 50, 120, randomLevel, 35),
                                                new String[] { "ground" },
                                                149,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/51.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/51.png"),
                                                randomLevel,
                                                0,
                                                new Pokemon[] { null }, false);
                        case 52:
                                return new Pokemon(
                                                "meowth",
                                                52,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(40, 45, 35, 90, randomLevel, 40),
                                                new String[] { "normal" },
                                                58,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/52.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/52.png"),
                                                randomLevel,
                                                28,
                                                new Pokemon[] { getPokemon(53) }, false);
                        case 53:
                                return new Pokemon(
                                                "persian",
                                                53,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(65, 70, 60, 115, randomLevel, 65),
                                                new String[] { "normal" },
                                                154,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/53.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/53.png"),
                                                randomLevel,
                                                0,
                                                new Pokemon[] { null }, false);
                        case 54:
                                return new Pokemon(
                                                "psyduck",
                                                54,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(50, 52, 48, 55, randomLevel, 50),
                                                new String[] { "water" },
                                                64,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/54.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/54.png"),
                                                randomLevel,
                                                33,
                                                new Pokemon[] { getPokemon(55) }, false);
                        case 55:
                                return new Pokemon(
                                                "golduck",
                                                55,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(80, 82, 78, 85, randomLevel, 80),
                                                new String[] { "water" },
                                                175,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/55.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/55.png"),
                                                randomLevel,
                                                0,
                                                new Pokemon[] { null }, false);
                        default:
                                return null;
                }

        }

}
