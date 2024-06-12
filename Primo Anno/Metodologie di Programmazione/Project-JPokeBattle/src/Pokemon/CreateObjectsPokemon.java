package Pokemon;

import java.util.ArrayList;

/*
* Classe che rappresenta uno switch di Pokémon.
* Questa classe permette di creare oggetti di tipo Pokemon.
* Grazie alla funzione getPokemon è possibile creare un oggetto di tipo Pokemon in base al parametro passato in input
*/

public class CreateObjectsPokemon extends Pokemon {
        /**
         * Costruttore della classe CreateObjectsPokemon
         * 
         * @param name
         * @param index
         * @param abilities
         * @param start
         * @param stats
         * @param types
         * @param baseExperience
         * @param sprite
         * @param lvl
         * @param lvlEvolutions
         * @param evolutions
         */
        public CreateObjectsPokemon(String name, int index, ArrayList<Ability> abilities, String start, Stats stats,
                        Types[] types, int baseExperience,
                        Sprites sprite, int lvl, int lvlEvolutions, int[] evolutions) {
                super(name, index, abilities, start, stats, types, baseExperience, sprite, lvl, lvlEvolutions,
                                evolutions,
                                false);
        }

        private static int randomLevel = 0;

        /**
         * Metodo per ottenere un livello random
         * @return numero intero
         */
        private static int getLevelRandom() {
                randomLevel = (int) (Math.random() * 5) + 1;
                return randomLevel;
        }

        /**
         * Metodo per ottenere un pokemon
         * 
         * @param num
         * @param level
         * @return Pokemon
         */
        public static Pokemon getPokemon(int num, int level) {
                if (level == 0)
                        randomLevel = getLevelRandom();
                else
                        randomLevel = level;

                switch (num) {
                        case 1:
                                return new Pokemon(
                                                "bulbasaur",
                                                1,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(49, 49, 45, randomLevel, 45),
                                                new Types[] { Types.GRASS, Types.POISON },
                                                64,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/1.png"),
                                                randomLevel,
                                                13,
                                                new int[] { 2, 3 },
                                                false);
                        case 2:
                                return new Pokemon(
                                                "ivysaur",
                                                2,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(62, 63, 60, randomLevel, 60),
                                                new Types[] { Types.GRASS, Types.POISON },
                                                142,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/2.png"),
                                                randomLevel,
                                                16,
                                                new int[] { 3 }, false);

                        case 3:
                                return new Pokemon(
                                                "venusaur",
                                                3,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(82, 83, 80, randomLevel, 80),
                                                new Types[] { Types.GRASS, Types.POISON },
                                                236,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/3.png"),
                                                randomLevel,
                                                0,
                                                new int[] { 0 }, false);
                        case 4:
                                return new Pokemon(
                                                "charmander",
                                                4,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(52, 43, 65, randomLevel, 39),
                                                new Types[] { Types.FIRE },
                                                62,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/4.png"),
                                                randomLevel,
                                                14,
                                                new int[] { 5, 6 }, false);

                        case 5:
                                return new Pokemon(
                                                "charmeleon",
                                                5,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(64, 58, 80, randomLevel, 58),
                                                new Types[] { Types.FIRE },
                                                142,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/5.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/5.png"),
                                                randomLevel,
                                                17,
                                                new int[] { 6 }, false);

                        case 6:
                                return new Pokemon(
                                                "charizard",
                                                6,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(84, 78, 100, randomLevel, 78),
                                                new Types[] { Types.FIRE, Types.FLYING },
                                                240,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/6.png"),
                                                randomLevel,
                                                0,
                                                new int[] { 0 }, false);

                        case 7:
                                return new Pokemon(
                                                "squirtle",
                                                7,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(48, 65, 43, randomLevel, 44),
                                                new Types[] { Types.WATER },
                                                63,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/7.png"),
                                                randomLevel,
                                                12,
                                                new int[] { 8, 9 }, false);
                        case 8:
                                return new Pokemon(
                                                "wartortle",
                                                8,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(63, 80, 58, randomLevel, 59),
                                                new Types[] { Types.WATER },
                                                142,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/8.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/8.png"),
                                                randomLevel,
                                                16,
                                                new int[] { 9 }, false);
                        case 9:
                                return new Pokemon(
                                                "blastoise",
                                                9,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(83, 100, 78, randomLevel, 79),
                                                new Types[] { Types.WATER },
                                                239,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/9.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/9.png"),
                                                randomLevel,
                                                0,
                                                new int[] { 0 }, false);

                        case 10:
                                return new Pokemon(
                                                "caterpie",
                                                10,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(30, 35, 45, randomLevel, 45),
                                                new Types[] { Types.BUG },
                                                39,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/10.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/10.png"),
                                                randomLevel,
                                                7,
                                                new int[] { 11 }, false);
                        case 11:
                                return new Pokemon(
                                                "metapod",
                                                11,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(20, 55, 30, randomLevel, 50),
                                                new Types[] { Types.BUG },
                                                72,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/11.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/11.png"),
                                                randomLevel,
                                                16,
                                                new int[] { 12 }, false);
                        case 12:
                                return new Pokemon(
                                                "butterfree",
                                                12,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(45, 50, 70, randomLevel, 60),
                                                new Types[] { Types.BUG, Types.FLYING },
                                                178,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/12.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/12.png"),
                                                randomLevel,
                                                0,
                                                new int[] { 0 }, false);
                        case 13:
                                return new Pokemon(
                                                "weedle",
                                                13,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(35, 30, 50, randomLevel, 40),
                                                new Types[] { Types.BUG, Types.POISON },
                                                39,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/13.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/13.png"),
                                                randomLevel,
                                                7,
                                                new int[] { 14 }, false);
                        case 14:
                                return new Pokemon(
                                                "kakuna",
                                                14,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(25, 50, 35, randomLevel, 45),
                                                new Types[] { Types.BUG, Types.POISON },
                                                72,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/14.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/14.png"),
                                                randomLevel,
                                                10,
                                                new int[] { 15 }, false);
                        case 15:
                                return new Pokemon(
                                                "beedrill",
                                                15,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(90, 40, 75, randomLevel, 65),
                                                new Types[] { Types.BUG, Types.POISON },
                                                178,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/15.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/15.png"),
                                                randomLevel,
                                                0,
                                                new int[] { 0 }, false);

                        case 16:
                                return new Pokemon(
                                                "pidgey",
                                                16,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(45, 40, 56, randomLevel, 40),
                                                new Types[] { Types.NORMAL, Types.FLYING },
                                                50,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/16.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/16.png"),
                                                randomLevel,
                                                11,
                                                new int[] { 17 }, false);

                        case 17:
                                return new Pokemon(
                                                "pidgeotto",
                                                17,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(60, 55, 71, randomLevel, 63),
                                                new Types[] { Types.NORMAL, Types.FLYING },
                                                122,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/17.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/17.png"),
                                                randomLevel,
                                                19,
                                                new int[] { 18 }, false);

                        case 18:
                                return new Pokemon(
                                                "pidgeot",
                                                18,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(80, 75, 101, randomLevel, 83),
                                                new Types[] { Types.NORMAL, Types.FLYING },
                                                216,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/18.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/18.png"),
                                                randomLevel,
                                                0,
                                                new int[] { 0 }, false);

                        case 19:
                                return new Pokemon(
                                                "rattata",
                                                19,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(56, 35, 72, randomLevel, 30),
                                                new Types[] { Types.NORMAL },
                                                51,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/19.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/19.png"),
                                                randomLevel,
                                                12,
                                                new int[] { 20 }, false);

                        case 20:
                                return new Pokemon(
                                                "raticate",
                                                20,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(81, 60, 97, randomLevel, 55),
                                                new Types[] { Types.NORMAL },
                                                145,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/20.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/20.png"),
                                                randomLevel,
                                                0,
                                                new int[] { 0 }, false);

                        case 21:
                                return new Pokemon(
                                                "spearow",
                                                21,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(60, 30, 70, randomLevel, 40),
                                                new Types[] { Types.NORMAL, Types.FLYING },
                                                52,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/21.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/21.png"),
                                                randomLevel,
                                                13,
                                                new int[] { 22 }, false);

                        case 22:
                                return new Pokemon(
                                                "fearow",
                                                22,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(90, 65, 100, randomLevel, 65),
                                                new Types[] { Types.NORMAL, Types.FLYING },
                                                155,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/22.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/22.png"),
                                                randomLevel,
                                                0,
                                                new int[] { 0 }, false);

                        case 23:
                                return new Pokemon(
                                                "ekans",
                                                23,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(60, 44, 55, randomLevel, 35),
                                                new Types[] { Types.POISON },
                                                58,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/23.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/23.png"),
                                                randomLevel,
                                                12,
                                                new int[] { 24 }, false);

                        case 24:
                                return new Pokemon(
                                                "arbok",
                                                24,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(85, 69, 80, randomLevel, 60),
                                                new Types[] { Types.POISON },
                                                153,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/24.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/24.png"),
                                                randomLevel,
                                                0,
                                                new int[] { 0 }, false);

                        case 25:
                                return new Pokemon(
                                                "pikachu",
                                                25,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(55, 40, 90, randomLevel, 35),
                                                new Types[] { Types.ELECTRIC },
                                                112,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/25.png"),
                                                randomLevel,
                                                14,
                                                new int[] { 26 }, false);

                        case 26:
                                return new Pokemon(
                                                "raichu",
                                                26,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(90, 55, 100, randomLevel, 60),
                                                new Types[] { Types.ELECTRIC },
                                                218,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/26.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/26.png"),
                                                randomLevel,
                                                0,
                                                new int[] { 0 }, false);

                        case 27:
                                return new Pokemon(
                                                "sandshrew",
                                                27,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(75, 85, 40, randomLevel, 50),
                                                new Types[] { Types.GROUND },
                                                60,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/27.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/27.png"),
                                                randomLevel,
                                                10,
                                                new int[] { 28 }, false);

                        case 28:
                                return new Pokemon(
                                                "sandslash",
                                                28,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(100, 110, 65, randomLevel, 75),
                                                new Types[] { Types.GROUND },
                                                158,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/28.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/28.png"),
                                                randomLevel,
                                                0,
                                                new int[] { 0 }, false);

                        case 29:
                                return new Pokemon(
                                                "nidoranF",
                                                29,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(47, 52, 41, randomLevel, 55),
                                                new Types[] { Types.POISON },
                                                55,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/29.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/29.png"),
                                                randomLevel,
                                                11,
                                                new int[] { 30 }, false);

                        case 30:
                                return new Pokemon(
                                                "nidorina",
                                                30,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(62, 67, 56, randomLevel, 70),
                                                new Types[] { Types.POISON },
                                                128,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/30.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/30.png"),
                                                randomLevel,
                                                16,
                                                new int[] { 31 }, false);
                        case 31:

                                return new Pokemon(
                                                "nidoqueen",
                                                31,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(82, 87, 76, randomLevel, 90),
                                                new Types[] { Types.POISON, Types.GROUND },
                                                227,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/31.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/31.png"),
                                                randomLevel,
                                                0,
                                                new int[] { 0 }, false);
                        case 32:
                                return new Pokemon(
                                                "nidoranM",
                                                32,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(57, 40, 50, randomLevel, 46),
                                                new Types[] { Types.POISON },
                                                55,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/32.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/32.png"),
                                                randomLevel,
                                                11,
                                                new int[] { 33 }, false);
                        case 33:
                                return new Pokemon(
                                                "nidorino",
                                                33,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(72, 57, 65, randomLevel, 61),
                                                new Types[] { Types.POISON },
                                                128,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/33.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/33.png"),
                                                randomLevel,
                                                16,
                                                new int[] { 34 }, false);
                        case 34:
                                return new Pokemon(
                                                "nidoking",
                                                34,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(102, 77, 85, randomLevel, 81),
                                                new Types[] { Types.POISON, Types.GROUND },
                                                227,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/34.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/34.png"),
                                                randomLevel,
                                                0,
                                                new int[] { 0 }, false);
                        case 35:
                                return new Pokemon(
                                                "clefairy",
                                                35,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(45, 48, 35, randomLevel, 70),
                                                new Types[] { Types.FAIRY },
                                                113,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/35.png"),
                                                randomLevel,
                                                13,
                                                new int[] { 36 }, false);
                        case 36:
                                return new Pokemon(
                                                "clefable",
                                                36,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(70, 73, 60, randomLevel, 95),
                                                new Types[] { Types.FAIRY },
                                                217,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/36.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/36.png"),
                                                randomLevel,
                                                0,
                                                new int[] { 0 }, false);
                        case 37:
                                return new Pokemon(
                                                "vulpix",
                                                37,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(41, 40, 65, randomLevel, 38),
                                                new Types[] { Types.FIRE },
                                                60,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/37.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/37.png"),
                                                randomLevel,
                                                13,
                                                new int[] { 38 }, false);
                        case 38:
                                return new Pokemon(
                                                "ninetales",
                                                38,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(76, 75, 100, randomLevel, 73),
                                                new Types[] { Types.FIRE },
                                                177,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/38.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/38.png"),
                                                randomLevel,
                                                0,
                                                new int[] { 0 }, false);
                        case 39:
                                return new Pokemon(
                                                "jigglypuff",
                                                39,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(45, 20, 20, randomLevel, 115),
                                                new Types[] { Types.NORMAL, Types.FAIRY },
                                                95,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/39.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/39.png"),
                                                randomLevel,
                                                13,
                                                new int[] { 40 }, false);
                        case 40:
                                return new Pokemon(
                                                "wigglytuff",
                                                40,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(70, 45, 45, randomLevel, 140),
                                                new Types[] { Types.NORMAL, Types.FAIRY },
                                                196,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/40.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/40.png"),
                                                randomLevel,
                                                0,
                                                new int[] { 0 }, false);
                        case 41:
                                return new Pokemon(
                                                "zubat",
                                                41,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(45, 35, 55, randomLevel, 40),
                                                new Types[] { Types.POISON, Types.FLYING },
                                                49,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/41.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/41.png"),
                                                randomLevel,
                                                14,
                                                new int[] { 42 }, false);
                        case 42:
                                return new Pokemon(
                                                "golbat",
                                                42,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(80, 70, 90, randomLevel, 75),
                                                new Types[] { Types.POISON, Types.FLYING },
                                                159,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/42.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/42.png"),
                                                randomLevel,
                                                0,
                                                new int[] { 0 }, false);
                        case 43:
                                return new Pokemon(
                                                "oddish",
                                                43,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(50, 55, 30, randomLevel, 45),
                                                new Types[] { Types.GRASS, Types.POISON },
                                                64,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/43.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/43.png"),
                                                randomLevel,
                                                21,
                                                new int[] { 44 }, false);
                        case 44:
                                return new Pokemon(
                                                "gloom",
                                                44,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(65, 70, 40, randomLevel, 60),
                                                new Types[] { Types.GRASS, Types.POISON },
                                                138,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/44.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/44.png"),
                                                randomLevel,
                                                21,
                                                new int[] { 45 }, false);
                        case 45:
                                return new Pokemon(
                                                "vileplume",
                                                45,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(80, 85, 50, randomLevel, 75),
                                                new Types[] { Types.GRASS, Types.POISON },
                                                221,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/45.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/45.png"),
                                                randomLevel,
                                                0,
                                                new int[] { 0 }, false);
                        case 46:
                                return new Pokemon(
                                                "paras",
                                                46,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(70, 55, 25, randomLevel, 35),
                                                new Types[] { Types.BUG, Types.GRASS },
                                                57,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/46.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/46.png"),
                                                randomLevel,
                                                24,
                                                new int[] { 47 }, false);
                        case 47:
                                return new Pokemon(
                                                "parasect",
                                                47,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(95, 80, 30, randomLevel, 60),
                                                new Types[] { Types.BUG, Types.GRASS },
                                                142,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/47.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/47.png"),
                                                randomLevel,
                                                0,
                                                new int[] { 0 }, false);
                        case 48:
                                return new Pokemon(
                                                "venonat",
                                                48,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(55, 50, 45, randomLevel, 60),
                                                new Types[] { Types.BUG, Types.POISON },
                                                61,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/48.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/48.png"),
                                                randomLevel,
                                                31,
                                                new int[] { 49 }, false);
                        case 49:
                                return new Pokemon(
                                                "venomoth",
                                                49,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(65, 60, 90, randomLevel, 70),
                                                new Types[] { Types.BUG, Types.POISON },
                                                158,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/49.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/49.png"),
                                                randomLevel,
                                                0,
                                                new int[] { 0 }, false);
                        case 50:
                                return new Pokemon(
                                                "diglett",
                                                50,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(55, 25, 95, randomLevel, 10),
                                                new Types[] { Types.GROUND },
                                                53,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/50.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/50.png"),
                                                randomLevel,
                                                26,
                                                new int[] { 51 }, false);
                        case 51:
                                return new Pokemon(
                                                "dugtrio",
                                                51,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(80, 50, 120, randomLevel, 35),
                                                new Types[] { Types.GROUND },
                                                149,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/51.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/51.png"),
                                                randomLevel,
                                                0,
                                                new int[] { 0 }, false);
                        case 52:
                                return new Pokemon(
                                                "meowth",
                                                52,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(45, 35, 90, randomLevel, 40),
                                                new Types[] { Types.NORMAL },
                                                58,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/52.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/52.png"),
                                                randomLevel,
                                                28,
                                                new int[] { 53 }, false);
                        case 53:
                                return new Pokemon(
                                                "persian",
                                                53,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(70, 60, 115, randomLevel, 65),
                                                new Types[] { Types.NORMAL },
                                                154,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/53.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/53.png"),
                                                randomLevel,
                                                0,
                                                new int[] { 0 }, false);
                        case 54:
                                return new Pokemon(
                                                "psyduck",
                                                54,
                                                Ability.getAbility(num, randomLevel),
                                                "start",
                                                new Stats(52, 48, 55, randomLevel, 50),
                                                new Types[] { Types.WATER },
                                                64,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/54.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/54.png"),
                                                randomLevel,
                                                33,
                                                new int[] { 55 }, false);
                        case 55:
                                return new Pokemon(
                                                "golduck",
                                                55,
                                                Ability.getAbility(num, randomLevel),
                                                null,
                                                new Stats(82, 78, 85, randomLevel, 80),
                                                new Types[] { Types.WATER },
                                                175,
                                                new Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/55.png",
                                                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/55.png"),
                                                randomLevel,
                                                0,
                                                new int[] { 0 }, false);
                        default:
                                return null;
                }

        }

}
