package Battle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;
import Game.*;

import Pokemon.Pokemon;
import Shared.*;
import java.util.List;

public class BattleFrame extends JFrame implements BattleEventListener {
    private static JProgressBar playerHealthBar;
    private static JProgressBar npcHealthBar;
    private static JProgressBar playerExpBar;
    static ImageIcon gifSmoke = new ImageIcon(RelativePath.getAbsolutePath("Image/smoke.gif"));
    static Image originalImage = gifSmoke.getImage();
    static Image resizedImage = originalImage.getScaledInstance(200, 150, Image.SCALE_DEFAULT);
    static ImageIcon smoke = new ImageIcon(resizedImage);

    Battle battle = new Battle(this);

    public static JPanel abilityPanel;
    private static boolean startBattle = false;

    private JLabel pokePlayer;
    public static JLabel lvlPlayer;
    private JLabel imageLabelPlayer;
    private static JLabel playerCurrentHpLabel;
    private static JLabel playerMaxHpLabel;

    private static JLabel pokeNpc;
    private static JLabel lvlNpc;
    private static JLabel imageLabelNpc;
    private JLabel[] playerPokeballs;
    private static JLabel[] npcPokeballs;

    public JFrame frame;

    /**
     * Metodo per aggiornare la barra della salute del giocatore
     */
    @Override
    public void onPlayerHealthUpdated(int dmg, int health) {
        updatePlayerHealthBar(dmg, health);
    }

    /**
     * Metodo per mostrare il frame per cambiare Pokémon
     */
    @Override
    public void onPokemonFainted() {
        // Mostra il frame per cambiare Pokémon
        new ChangePokemonFrame(this);
    }

    /** 
     * Metodo per aggiornare la barra della salute del NPC
     */
    @Override
    public void onNpcHealthUpdated(int dmg) {
        updateNPCHealthBar(dmg);
    }

    /**
     * Metodo per aggiornare il display del Pokémon del giocatore
     */
    @Override
    public void onBattleEnd() {
        frame.setVisible(false);
        frame.dispose();
    }

    /**
     * Metodo per aggiornare la barra dell'esperienza del giocatore
     */
    @Override
    public void updateExpBar(int exp) {
        updatePlayerExpBar(exp);
    }

    /**
     * Metodo per aggiornare le stats del giocatore
     */
    @Override
    public void incrementStats() {
        showIncrementStats();
    }

    /**
     * Metodo per aggiornare il display del Pokémon del giocatore
     */
    @Override
    public void AbilityNpc(int i) {
        showMessageAbilityNpc(i);
    }

    /**
     * Metodo per aggiornare il display del Pokémon del giocatore
     */
    @Override
    public void AbilityPlayer(int i) {
        showMessageAbilityPlayer(i);
    }

    /**
     * Costruttore della classe BattleFrame
     * @throws IOException
     * @throws URISyntaxException
     */
    public BattleFrame() throws IOException, URISyntaxException {

        Coach player = Battle.getPlayer();
        Coach npc = Battle.getNpc();

        frame = new JFrame("Battle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(865, 538);
        frame.setLayout(null);
        frame.setIconImage(new ImageIcon(RelativePath.getAbsolutePath("/Image/active_pokeball.png")).getImage());
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

        ImageIcon imagePokePlayer = ImageUtility.loadImage(new URI(player.getPokemonInUse().getSprite().getBack()));
        ImageIcon imagePokeNpc = ImageUtility.loadImage(new URI(npc.getPokemonInUse().getSprite().getFront()));

        imageLabelPlayer = new JLabel(smoke);

        imageLabelNpc = new JLabel(smoke);
        imageLabelPlayer = new JLabel(smoke);

        imageLabelPlayer.setBounds(100, 175, 200, 200);
        backgroundLabel.add(imageLabelPlayer);

        imageLabelNpc.setBounds(550, 40, 200, 200);
        backgroundLabel.add(imageLabelNpc);

        Timer timerN = new Timer(980, e -> {
            imageLabelNpc.setIcon(ImageUtility.resizeIcon(imagePokeNpc, 200, 200));
            imageLabelPlayer.setIcon(ImageUtility.resizeIcon(imagePokePlayer, 200, 200));
        });
        timerN.setRepeats(false);
        timerN.start();

        // Aggiungi le barre della salute al backgroundLabel
        backgroundLabel.add(playerHealthBar);
        backgroundLabel.add(npcHealthBar);
        backgroundLabel.add(playerExpBar);

        // Aggiungi JLabel per HP correnti e massimi del giocatore
        playerCurrentHpLabel = new JLabel(String.valueOf(player.getPokemonInUse().getStats().getHp()));
        playerCurrentHpLabel.setBounds(670, 300, 80, 20);
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

    /**
     * Inizializza le Poké Ball
     * @param player
     * @param npc
     * @param backgroundLabel
     * @throws IOException
     */
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
            npcPokeballs[i].setBounds(335 - (i * 40), 20, 30, 30);
            frame.add(npcPokeballs[i]);
        }
    }

    /**
     * Aggiorna lo stato delle Poké Ball del giocatore
     * @param player
     */
    public void updatePokeballStatus(Coach player) {
        for (int i = 0; i < playerPokeballs.length; i++) {
            if (player.getTeam(). getTeam().get(i).getStats().getHp() <= 0) {
                ImageIcon inactivePokeball = new ImageIcon(RelativePath.getAbsolutePath("Image/inactive_pokeball.png"));
                playerPokeballs[i].setIcon(ImageUtility.resizeIcon(inactivePokeball, 30, 30));
            }
        }
    }

    /**
     * Aggiorna lo stato delle Poké Ball del NPC
     * @param npc
     */
    public static void updatePokeballStatusNpc(Coach npc) {
        for (int i = 0; i < npcPokeballs.length; i++) {
            if (npc.getTeam(). getTeam().get(i).getStats().getHp() <= 0) {
                ImageIcon inactivePokeball = new ImageIcon(RelativePath.getAbsolutePath("Image/inactive_pokeball.png"));
                npcPokeballs[i].setIcon(ImageUtility.resizeIcon(inactivePokeball, 30, 30));
            }
        }
    }

    /**
     * Metodo per aggiornare il pannello delle abilità
     */
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

    /**
     * Mostra un messaggio quando il giocatore usa un'abilità
     * @param index
     */
    private void showMessageAbilityPlayer(int index) {
        abilityPanel.removeAll();
        JLabel message;
        message = new JLabel(
                "You used " + Battle.getPlayer().getPokemonInUse().getAbilities().get(index).getName());

        message.setBounds(250, 50, 750, 100);
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

    /**
     * Mostra un messaggio quando il NPC usa un'abilità
     * @param index
     */
    public static void showMessageAbilityNpc(int index) {
        JPanel overlayPanel = new JPanel();
        abilityPanel.setLayout(null);
        overlayPanel.setLayout(null);
        overlayPanel.setBackground(new Color(87, 144, 151, 255)); // Semi-transparent background

        // Set overlayPanel size to match abilityPanel
        overlayPanel.setBounds(0, 0, abilityPanel.getWidth(), abilityPanel.getHeight());

        JLabel message = new JLabel(
                "Enemy used " + Battle.getNpc().getPokemonInUse().getAbilities().get(index).getName());
        message.setBounds(10, 0, 750, 100); // Position the label at the top-left corner
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

    /**
     * Mostra le statistiche incrementate del giocatore
     */
    public static void showIncrementStats() {
        Pokemon pokemon = Battle.getPlayer().getPokemonInUse();

        lvlPlayer.setText(String.valueOf(pokemon.getLvl()));
        playerCurrentHpLabel.setText(String.valueOf(pokemon.getStats().getHp()));
        playerMaxHpLabel.setText(String.valueOf(pokemon.getStats().getMaxHp()));

        JPanel overlayPanel = new JPanel();
        abilityPanel.setLayout(null);
        overlayPanel.setLayout(new GridBagLayout());
        overlayPanel.setBackground(new Color(87, 144, 151, 255)); // Sfondo semi-trasparente

        // Imposta la dimensione di overlayPanel per adattarsi a abilityPanel
        overlayPanel.setBounds(0, 0, abilityPanel.getWidth(), abilityPanel.getHeight());

        JLabel message = new JLabel("Your " + pokemon.getName() + " has increased its stats!");
        message.setFont(PixelFont.myCustomFont.deriveFont(18f));
        message.setForeground(Color.WHITE); // Imposta il colore del testo per una migliore visibilità

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        overlayPanel.add(message, gbc);

        gbc.gridy++;
        JLabel hp = new JLabel("HP: " + pokemon.getStats().getHp() + " -> " + pokemon.getStats().getHp());
        hp.setFont(PixelFont.myCustomFont.deriveFont(12f));
        hp.setForeground(Color.WHITE);
        overlayPanel.add(hp, gbc);

        gbc.gridy++;
        JLabel atk = new JLabel("Attack: " + pokemon.getStats().getAttack() + " -> " + pokemon.getStats().getAttack());
        atk.setFont(PixelFont.myCustomFont.deriveFont(12f));
        atk.setForeground(Color.WHITE);
        overlayPanel.add(atk, gbc);

        gbc.gridy++;
        JLabel def = new JLabel(
                "Defense: " + pokemon.getStats().getDefense() + " -> " + pokemon.getStats().getDefense());
        def.setFont(PixelFont.myCustomFont.deriveFont(12f));
        def.setForeground(Color.WHITE);
        overlayPanel.add(def, gbc);

        gbc.gridy++;
        JLabel spd = new JLabel("Speed: " + pokemon.getStats().getSpeed() + " -> " + pokemon.getStats().getSpeed());
        spd.setFont(PixelFont.myCustomFont.deriveFont(12f));
        spd.setForeground(Color.WHITE);
        overlayPanel.add(spd, gbc);

        playerHealthBar.setMaximum(pokemon.getStats().getMaxHp());
        playerHealthBar.setValue(pokemon.getStats().getHp());

        abilityPanel.add(overlayPanel, 0); // Aggiungi overlayPanel sopra abilityPanel
        abilityPanel.revalidate();
        abilityPanel.repaint();

        // Aggiungi un listener per gestire il ridimensionamento di abilityPanel
        abilityPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                overlayPanel.setBounds(0, 0, abilityPanel.getWidth(), abilityPanel.getHeight());
            }
        });

        Timer timer = new Timer(2000, e -> {
            abilityPanel.remove(overlayPanel);
            abilityPanel.setLayout(new GridLayout(2, 4, 10, 10));
            abilityPanel.revalidate();
            abilityPanel.repaint();
        });

        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Inizializza la battaglia
     * @param player
     * @param npc
     */
    private void initialize(Coach player, Coach npc) {

        List<Pokemon> pokemonList = player.getTeam(). getTeam();
        for (int i = 0; i < pokemonList.size(); i++) {
            if (pokemonList.get(i).getStats().getHp() > 0) {
                Battle.setPokeInUseAtStart(i);
                break; // Exit the method once the first available Pokémon is found
            }
        }

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
            battle.whoStart();
            startBattle = true; // Update startBattle to true
        }
    }

    /**
     *  Metodo per creare un pulsante per le abilità
     * @param player
     * @param index
     * @param npc
      
     */
    private JButton createAbilityButton(Coach player, int index, Coach npc) {
        JButton abilityButton = Style.createButton(Color.YELLOW,
                player.getPokemonInUse().getAbilities().get(index).getName(), 12, 70, 65, 350, 40);
        abilityButton.addActionListener(e -> {
            battle.decreaseHpNpc(player.getPokemonInUse().getAbilities().get(index).getStrength(),
                    player.getPokemonInUse().getAbilities().get(index).getTypo());
            battle.setTurn(false);
            showMessageAbilityPlayer(index);
        });
        return abilityButton;
    }

    /**
     * Metodo per creare un pulsante per cambiare Pokémon
     * @param player
     * @param npc
      
     */
    private JButton createChangePokemonButton(Coach player, Coach npc) {
        JButton changePoke = Style.createButton(Color.WHITE, "Change Pokémon", 12, 90, 65, 350, 40);
        changePoke.addActionListener(e -> {
            new ChangePokemonFrame(this);
        });
        return changePoke;
    }

    /**
     * Aggiorna la barra della salute del giocatore
     * @param dmg
     * @param health
     */
    public static void updatePlayerHealthBar(int dmg, int health) {
        if (dmg == 0)
            return;
        Timer timer = new Timer(16, new ActionListener() {
                int count = dmg;
    
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (count == 0) {
                        ((Timer) e.getSource()).stop();
                        return;
                    }
    
                    count--;
                    playerHealthBar.setValue(playerHealthBar.getValue() - 1);
                }
            });
            timer.start();
        System.out.println("Player health: " + health);
        if (health <= 0) {
            playerCurrentHpLabel.setText(String.valueOf(0));
        } else {
            playerCurrentHpLabel.setText(String.valueOf(health));
        }

    }

    /**
     * Aggiorna la barra della salute del NPC
     * @param dmg
     */
    public static void updateNPCHealthBar(int dmg) {
        if (dmg == 0)
            return;

        Timer timer = new Timer(16, new ActionListener() {
            int count = dmg;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (count == 0) {
                    ((Timer) e.getSource()).stop();
                    return;
                }

                count--;
                npcHealthBar.setValue(npcHealthBar.getValue() - 1);
            }
        });
        timer.start();
    }

    /**
     * Aggiorna la barra dell'esperienza del giocatore
     * @param exp
     */
    public static void updatePlayerExpBar(int exp) {
        playerExpBar.setValue(exp);
    }

    /**
     * Aggiorna il display del Pokémon del giocatore durante la battaglia
     * @param player
     * @param npc
     * @throws IOException
     * @throws URISyntaxException
     */
    public void updatePokemonDisplayPlayer(Coach player, Coach npc) throws IOException, URISyntaxException {
        // Update Pokémon name and level
        pokePlayer.setText(player.getPokemonInUse().getName());
        lvlPlayer.setText(String.valueOf(player.getPokemonInUse().getLvl()));

        playerCurrentHpLabel.setText(String.valueOf(player.getPokemonInUse().getStats().getHp()));
        playerMaxHpLabel.setText(String.valueOf(player.getPokemonInUse().getStats().getMaxHp()));

        updatePokeballStatus(player);

        imageLabelPlayer.setIcon(smoke);

        ImageIcon imagePokePlayer = ImageUtility.loadImage(new URI(player.getPokemonInUse().getSprite().getBack()));

        Timer timerN = new Timer(970, e -> {
            imageLabelPlayer.setIcon(ImageUtility.resizeIcon(imagePokePlayer, 200, 200));
        });
        timerN.setRepeats(false);
        timerN.start();

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

    /**
     * Aggiorna il display del Pokémon del NPC durante la battaglia
     * @throws IOException
     * @throws URISyntaxException
     */
    public static void updatePokemonDisplayNpc() throws IOException, URISyntaxException {
        // Update Pokémon name and level
        Coach npc = Battle.getNpc();

        pokeNpc.setText(npc.getPokemonInUse().getName());
        lvlNpc.setText(String.valueOf(npc.getPokemonInUse().getLvl()));

        imageLabelNpc.setIcon(smoke);
        ImageIcon imagePokeNpc = ImageUtility.loadImage(new URI(npc.getPokemonInUse().getSprite().getFront()));
        Timer timerN = new Timer(980, e -> {
            // Update Pokémon image
            imageLabelNpc.setIcon(ImageUtility.resizeIcon(imagePokeNpc, 200, 200));
        });
        timerN.setRepeats(false);
        timerN.start();

        // Update health bar
        npcHealthBar.setMaximum(npc.getPokemonInUse().getStats().getMaxHp());
        npcHealthBar.setValue(npc.getPokemonInUse().getStats().getHp());

    }
}
