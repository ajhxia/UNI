package Battle;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.*;
import Game.*;
import Pokemon.Pokemon;
import Shared.*;

public class BattleFrame extends JFrame {
    private static JProgressBar playerHealthBar;
    private static JProgressBar npcHealthBar;
    private static JProgressBar playerExpBar;

    public static JPanel abilityPanel;
    private static boolean startBattle = false;

    private JLabel pokePlayer;
    public static JLabel lvlPlayer;
    private JLabel imageLabelPlayer;
    private static JLabel playerCurrentHpLabel;
    private JLabel playerMaxHpLabel;

    private static JLabel pokeNpc;
    private static JLabel lvlNpc;
    private static JLabel imageLabelNpc;
    private JLabel[] playerPokeballs;
    private static JLabel[] npcPokeballs;

    public static JFrame frame;

    public BattleFrame() throws IOException, URISyntaxException {

        Coach player = Battle.getPlayer();
        Coach npc = Battle.getNpc();

        frame = new JFrame("Battle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(865, 538);
        frame.setLayout(null);

        // Pannello per i pulsanti delle abilità
        abilityPanel = new JPanel(new GridLayout(2, 4, 10, 10)); // 2 righe, 4 colonne, gap di 10 pixel
        abilityPanel.setBounds(50, 375, 750, 100);
        abilityPanel.setOpaque(false); // Imposta lo sfondo trasparente

        initialize(player, npc);

        // Carica l'immagine di sfondo
        ImageIcon backgroundImage = new ImageIcon(RelativePath.getAbsolutePath("Image/PENUP_20240516_162632.png"));

        // Crea un JLabel con l'immagine di sfondo
        JLabel backgroundLabel = new JLabel(ImageUtility.resizeIcon(backgroundImage, 850, 500));
        backgroundLabel.setBounds(0, 0, 850, 500);

        pokePlayer = new JLabel(player.getPokemonInUse().getName());
        pokePlayer.setBounds(530, 210, 200, 100);
        pokePlayer.setFont(PixelFont.myCustomFont.deriveFont(18f));
        backgroundLabel.add(pokePlayer);

        pokeNpc = new JLabel(npc.getPokemonInUse().getName());
        pokeNpc.setBounds(95, 30, 200, 100);
        pokeNpc.setFont(PixelFont.myCustomFont.deriveFont(18f));
        backgroundLabel.add(pokeNpc);

        lvlPlayer = new JLabel(String.valueOf(player.getPokemonInUse().getLvl()));
        lvlPlayer.setBounds(750, 210, 100, 100);
        lvlPlayer.setFont(PixelFont.myCustomFont.deriveFont(18f));
        backgroundLabel.add(lvlPlayer);

        lvlNpc = new JLabel(String.valueOf(npc.getPokemonInUse().getLvl()));
        lvlNpc.setBounds(317, 30, 100, 100);
        lvlNpc.setFont(PixelFont.myCustomFont.deriveFont(18f));
        backgroundLabel.add(lvlNpc);

        ImageIcon imagePokePlayer = ImageUtility
                .loadImage(new URI(player.getPokemonInUse().getSprite().getBack()));
        imageLabelPlayer = new JLabel(ImageUtility.resizeIcon(imagePokePlayer, 200, 200));
        imageLabelPlayer.setBounds(100, 175, 200, 200);
        backgroundLabel.add(imageLabelPlayer);

        ImageIcon imagePokeNpc = ImageUtility.loadImage(new URI(npc.getPokemonInUse().getSprite().getFront()));
        imageLabelNpc = new JLabel(ImageUtility.resizeIcon(imagePokeNpc, 200, 200));
        imageLabelNpc.setBounds(550, 40, 200, 200);
        backgroundLabel.add(imageLabelNpc);

        // Aggiungi le barre della salute al backgroundLabel
        backgroundLabel.add(playerHealthBar);
        backgroundLabel.add(npcHealthBar);
        backgroundLabel.add(playerExpBar);

        // Aggiungi JLabel per HP correnti e massimi del giocatore
        playerCurrentHpLabel = new JLabel(String.valueOf(player.getPokemonInUse().getStats().getHp()));
        playerCurrentHpLabel.setBounds(680, 300, 80, 20);
        playerCurrentHpLabel.setFont(PixelFont.myCustomFont.deriveFont(18f));
        backgroundLabel.add(playerCurrentHpLabel);

        playerMaxHpLabel = new JLabel(String.valueOf(player.getPokemonInUse().getStats().getMaxHp()));
        playerMaxHpLabel.setBounds(740, 300, 80, 20);
        playerMaxHpLabel.setFont(PixelFont.myCustomFont.deriveFont(18f));
        backgroundLabel.add(playerMaxHpLabel);

        abilityPanel();

        // Inizializza le Poké Ball
        initializePokeballs(player, npc, backgroundLabel);

        // Aggiungi il pannello dei pulsanti delle abilità al frame
        frame.add(abilityPanel);
        // Aggiungi il JLabel al JFrame
        frame.add(backgroundLabel);

        // Altre componenti del frame...
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initializePokeballs(Coach player, Coach npc, JLabel backgroundLabel) throws IOException {
        int playerPokeballCount = player.getTeam().getTeamSize();
        int npcPokeballCount = npc.getTeam().getTeamSize();

        playerPokeballs = new JLabel[playerPokeballCount];
        npcPokeballs = new JLabel[npcPokeballCount];

        ImageIcon activePokeball = new ImageIcon(RelativePath.getAbsolutePath("Image/active_pokeball.png"));

        for (int i = 0; i < playerPokeballCount; i++) {
            playerPokeballs[i] = new JLabel(ImageUtility.resizeIcon(activePokeball, 30, 30));
            playerPokeballs[i].setBounds(568 + (i * 40), 200, 30, 30);
            frame.add(playerPokeballs[i]);
        }

        for (int i = 0; i < npcPokeballCount; i++) {
            npcPokeballs[i] = new JLabel(ImageUtility.resizeIcon(activePokeball, 30, 30));
            npcPokeballs[i].setBounds(150 - (i * 40), 20, 30, 30);
            frame.add(npcPokeballs[i]);
        }
    }

    public void updatePokeballStatus(Coach player) {
        for (int i = 0; i < playerPokeballs.length; i++) {
            if (player.getTeam().getListPokemon().get(i).getStats().getHp() <= 0) {
                ImageIcon inactivePokeball = new ImageIcon(RelativePath.getAbsolutePath("Image/inactive_pokeball.png"));
                playerPokeballs[i].setIcon(ImageUtility.resizeIcon(inactivePokeball, 30, 30));
            }
        }
    }

    public static void updatePokeballStatusNpc(Coach npc) {
        for (int i = 0; i < npcPokeballs.length; i++) {
            if (npc.getTeam().getListPokemon().get(i).getStats().getHp() <= 0) {
                ImageIcon inactivePokeball = new ImageIcon(RelativePath.getAbsolutePath("Image/inactive_pokeball.png"));
                npcPokeballs[i].setIcon(ImageUtility.resizeIcon(inactivePokeball, 30, 30));
            }
        }
    }

    public void abilityPanel() {
        abilityPanel.removeAll();
        for (int i = 0; i < Battle.getPlayer().getPokemonInUse().getAbilities().size(); i++) {
            JButton abilityButton = createAbilityButton(Battle.getPlayer(), i, Battle.getNpc());
            abilityButton.setEnabled(Battle.isTurn());
            abilityPanel.add(abilityButton);
        }

        JButton changePoke = createChangePokemonButton(Battle.getPlayer(), Battle.getNpc());
        changePoke.setEnabled(Battle.isTurn());
        abilityPanel.add(changePoke);
        abilityPanel.revalidate();
        abilityPanel.repaint();
    }

    private void showMessageAbilityPlayer(int index) {
        abilityPanel.removeAll();
        JLabel message;
        message = new JLabel(
                "You used " + Battle.getPlayer().getPokemonInUse().getAbilities().get(index).getName());

        message.setFont(PixelFont.myCustomFont.deriveFont(18f));
        message.setForeground(Color.WHITE);
        abilityPanel.add(message);
        abilityPanel.revalidate();
        abilityPanel.repaint();

        Timer timer = new Timer(1500, e -> {
            abilityPanel.removeAll();
            abilityPanel.revalidate();
            abilityPanel.repaint();
            abilityPanel();
        });

        timer.setRepeats(false);
        timer.start();
    }

    public static void showMessageAbilityNpc(int index) {
        JPanel overlayPanel = new JPanel();
        abilityPanel.setLayout(null);
        overlayPanel.setLayout(new GridBagLayout());
        overlayPanel.setBackground(new Color(87, 144, 151, 255)); // Semi-transparent background

        // Set overlayPanel size to match abilityPanel
        overlayPanel.setBounds(0, 0, abilityPanel.getWidth(), abilityPanel.getHeight());

        JLabel message = new JLabel(
                "Enemy used " + Battle.getNpc().getPokemonInUse().getAbilities().get(index).getName());
        message.setFont(PixelFont.myCustomFont.deriveFont(18f));
        message.setForeground(Color.WHITE); // Set text color for better visibility
        overlayPanel.add(message);

        abilityPanel.add(overlayPanel, 0); // Add overlayPanel on top of abilityPanel
        abilityPanel.revalidate();
        abilityPanel.repaint();

        // Add a component listener to handle resizing of abilityPanel
        abilityPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                overlayPanel.setBounds(0, 0, abilityPanel.getWidth(), abilityPanel.getHeight());
            }
        });

        Timer timer = new Timer(1500, e -> {
            abilityPanel.remove(overlayPanel);
            abilityPanel.setLayout(new GridLayout(2, 4, 10, 10));
            abilityPanel.revalidate();
            abilityPanel.repaint();
        });

        timer.setRepeats(false);
        timer.start();
    }

    // permette di vedere di quanto aumentano le stats del pokemon dopo un livello
    public static void showIncrementStats() {
        Pokemon pokemon = Battle.getPlayer().getPokemonInUse();

        JPanel overlayPanel = new JPanel();
        abilityPanel.setLayout(null);
        overlayPanel.setLayout(new GridBagLayout());
        overlayPanel.setBackground(new Color(87, 144, 151, 255)); // Semi-transparent background

        // Set overlayPanel size to match abilityPanel
        overlayPanel.setBounds(0, 0, abilityPanel.getWidth(), abilityPanel.getHeight());

        JLabel message = new JLabel("Your " + pokemon.getName() + " has increased its stats!");
        message.setFont(PixelFont.myCustomFont.deriveFont(18f));
        message.setForeground(Color.WHITE); // Set text color for better visibility
        overlayPanel.add(message);

        // label per gli hp del pokemon
        pokemon.getStats().setAttack(pokemon.getStats().getAttack() + 30);
        pokemon.getStats().setDefense(pokemon.getStats().getDefense() + 25);
        pokemon.getStats().setSpeed(pokemon.getStats().getSpeed() + 50);
        pokemon.getStats().setHp(pokemon.getStats().getHp() + 23);

        JLabel hp = new JLabel("HP: " + pokemon.getStats().getHp() + " -> " + pokemon.getStats().getHp());
        hp.setFont(PixelFont.myCustomFont.deriveFont(12f));
        hp.setBounds(315, 193, 200, 50);
        overlayPanel.add(hp);

        JLabel atk = new JLabel("Attack: " + pokemon.getStats().getAttack() + " -> " + pokemon.getStats().getAttack());
        atk.setFont(PixelFont.myCustomFont.deriveFont(12f));
        atk.setBounds(315, 270, 200, 50);
        overlayPanel.add(atk);

        JLabel def = new JLabel(
                "Defense: " + pokemon.getStats().getDefense() + " -> " + pokemon.getStats().getDefense());
        def.setFont(PixelFont.myCustomFont.deriveFont(12f));
        def.setBounds(315, 290, 200, 50);
        overlayPanel.add(def);

        JLabel spAtk = new JLabel("Sp. Atk: " + pokemon.getStats().getSpeed() + " -> " + pokemon.getStats().getSpeed());
        spAtk.setFont(PixelFont.myCustomFont.deriveFont(12f));
        spAtk.setBounds(315, 310, 200, 50);
        overlayPanel.add(spAtk);

        abilityPanel.add(overlayPanel, 0); // Add overlayPanel on top of abilityPanel
        abilityPanel.revalidate();
        abilityPanel.repaint();

        // Add a component listener to handle resizing of abilityPanel
        abilityPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                overlayPanel.setBounds(0, 0, abilityPanel.getWidth(), abilityPanel.getHeight());
            }
        });

        Timer timer = new Timer(1500, e -> {
            abilityPanel.remove(overlayPanel);
            abilityPanel.setLayout(new GridLayout(2, 4, 10, 10));
            abilityPanel.revalidate();
            abilityPanel.repaint();
        });

        timer.setRepeats(false);
        timer.start();
    }

    private static void initialize(Coach player, Coach npc) {

        Battle.setPokeInUseAtStart(0);

        int playerMaxHp = player.getTeam().getPokemon(0).getStats().getMaxHp();
        int playerCurrentHp = player.getTeam().getPokemon(0).getStats().getHp();
        int npcMaxHp = npc.getTeam().getPokemon(0).getStats().getMaxHp();
        int npcCurrentHp = npc.getTeam().getPokemon(0).getStats().getHp();

        playerHealthBar = new JProgressBar(0, playerMaxHp);
        playerHealthBar.setValue(playerCurrentHp);
        playerHealthBar.setStringPainted(true);
        playerHealthBar.setForeground(new Color(75, 242, 136));
        playerHealthBar.setBounds(618, 282, 168, 10);
        playerHealthBar.setStringPainted(false);

        npcHealthBar = new JProgressBar(0, npcMaxHp);
        npcHealthBar.setValue(npcCurrentHp);
        npcHealthBar.setStringPainted(true);
        npcHealthBar.setForeground(new Color(75, 242, 136));
        npcHealthBar.setBounds(185, 102, 168, 10);
        npcHealthBar.setStringPainted(false);

        playerExpBar = new JProgressBar(0, player.getPokemonInUse().getMaxExperience());
        playerExpBar.setValue(player.getPokemonInUse().getBaseExperience());
        playerExpBar.setStringPainted(true);
        playerExpBar.setForeground(new Color(52, 76, 235));
        playerExpBar.setBounds(559, 330, 230, 10);
        playerExpBar.setStringPainted(false);

        if (startBattle == false) {
            System.out.println("Start Battle");
            player.getTeam().getPokemon(0).setInUse(true);
            npc.getTeam().getPokemon(0).setInUse(true);
            Battle.whoStart();
            startBattle = true; // Update startBattle to true
        }
    }

    public static JFrame getInstance() {
        return frame;
    }

    private JButton createAbilityButton(Coach player, int index, Coach npc) {
        JButton abilityButton = Style.createButton(Color.WHITE,
                player.getPokemonInUse().getAbilities().get(index).getName(), 12, 70, 65, 350, 40);
        abilityButton.addActionListener(e -> {
            Battle.decreaseHpNpc(player.getPokemonInUse().getAbilities().get(index).getStrength(),
                    player.getPokemonInUse().getAbilities().get(index).getTypo());
            Battle.setTurn(false);
            showMessageAbilityPlayer(index);
        });
        return abilityButton;
    }

    private JButton createChangePokemonButton(Coach player, Coach npc) {
        JButton changePoke = Style.createButton(Color.WHITE, "Change Pokémon", 12, 90, 65, 350, 40);
        changePoke.addActionListener(e -> {
            new ChangePokemonFrame(this);
        });
        return changePoke;
    }

    // Aggiorna la barra della salute del giocatore
    public static void updatePlayerHealthBar(int currentHp) {
        playerHealthBar.setValue(currentHp);
        if (currentHp <= 0) {
            playerHealthBar.setValue(0);
        } else {
            playerCurrentHpLabel.setText(String.valueOf(currentHp));
        }
    }

    // Aggiorna la barra della salute del NPC
    public static void updateNPCHealthBar(int currentHp) {
        npcHealthBar.setValue(currentHp);
    }

    public static void updatePlayerExpBar(int exp) {
        playerExpBar.setValue(exp);
    }

    public void updatePokemonDisplayPlayer(Coach player, Coach npc) throws IOException {
        // Update Pokémon name and level
        pokePlayer.setText(player.getPokemonInUse().getName());
        lvlPlayer.setText(String.valueOf(player.getPokemonInUse().getLvl()));

        playerCurrentHpLabel.setText(String.valueOf(player.getPokemonInUse().getStats().getHp()));
        updatePokeballStatus(player);

        // Update Pokémon image
        try {
            ImageIcon imagePokePlayer = ImageUtility.loadImage(new URI(player.getPokemonInUse().getSprite().getBack()));
            imageLabelPlayer.setIcon(ImageUtility.resizeIcon(imagePokePlayer, 200, 200));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        // Update health bar
        playerHealthBar.setMaximum(player.getPokemonInUse().getStats().getMaxHp());
        playerHealthBar.setValue(player.getPokemonInUse().getStats().getHp());

        // Update experience bar
        playerExpBar.setMaximum(player.getPokemonInUse().getMaxExperience());
        playerExpBar.setValue(player.getPokemonInUse().getBaseExperience());

        // Refresh ability buttons
        abilityPanel.removeAll();

        for (int i = 0; i < player.getPokemonInUse().getAbilities().size(); i++) {
            JButton abilityButton = createAbilityButton(player, i, npc);
            abilityPanel.add(abilityButton);
        }

        JButton changePoke = createChangePokemonButton(player, npc);
        abilityPanel.add(changePoke);

        abilityPanel.revalidate();
        abilityPanel.repaint();
    }

    public static void updatePokemonDisplayNpc() throws IOException {
        // Update Pokémon name and level
        Coach npc = Battle.getNpc();

        pokeNpc.setText(npc.getPokemonInUse().getName());
        lvlNpc.setText(String.valueOf(npc.getPokemonInUse().getLvl()));

        // Update Pokémon image
        try {
            ImageIcon imagePokeNpc = ImageUtility.loadImage(new URI(npc.getPokemonInUse().getSprite().getFront()));
            imageLabelNpc.setIcon(ImageUtility.resizeIcon(imagePokeNpc, 200, 200));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        // Update health bar
        npcHealthBar.setMaximum(npc.getPokemonInUse().getStats().getMaxHp());
        npcHealthBar.setValue(npc.getPokemonInUse().getStats().getHp());

    }
}
