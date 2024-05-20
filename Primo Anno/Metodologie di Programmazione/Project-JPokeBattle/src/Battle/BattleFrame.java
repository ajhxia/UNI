package Battle;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.*;
import Game.*;
import Shared.*;

public class BattleFrame extends JFrame {
    private static JProgressBar playerHealthBar;
    private static JProgressBar npcHealthBar;

    static JPanel abilityPanel;
    private boolean startBattle = false;

    private JLabel pokePlayer;
    private JLabel lvlPlayer;
    private JLabel imageLabelPlayer;

    private JLabel pokeNpc;
    private JLabel lvlNpc;
    private JLabel imageLabelNpc;

    private JFrame frame;

    public BattleFrame(Coach player, Coach npc) throws IOException, URISyntaxException {

        frame = new JFrame("Battle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(865, 538);
        frame.setLayout(null);

        // Pannello per i pulsanti delle abilità
        abilityPanel = new JPanel(new GridLayout(2, 4, 10, 10)); // 2 righe, 4 colonne, gap di 10 pixel
        abilityPanel.setBounds(50, 375, 750, 100);
        abilityPanel.setOpaque(false); // Imposta lo sfondo trasparente
        // Initialize health bars before adding them to the frame
        initialize(player, npc);

        // Carica l'immagine di sfondo
        ImageIcon backgroundImage = new ImageIcon(RelativePath.getAbsolutePath("Image/PENUP_20240516_162632.png"));

        // Crea un JLabel con l'immagine di sfondo
        JLabel backgroundLabel = new JLabel(ImageUtility.resizeIcon(backgroundImage, 850, 500));
        backgroundLabel.setBounds(0, 0, 850, 500);

        pokePlayer = new JLabel(BattleLogic.currentPokemonPlayer.getName());
        pokePlayer.setBounds(530, 210, 200, 100);
        pokePlayer.setFont(PixelFont.myCustomFont.deriveFont(18f));
        backgroundLabel.add(pokePlayer);

        pokeNpc = new JLabel(BattleLogic.currentPokemonNpc.getName());
        pokeNpc.setBounds(95, 30, 200, 100);
        pokeNpc.setFont(PixelFont.myCustomFont.deriveFont(18f));
        backgroundLabel.add(pokeNpc);

        lvlPlayer = new JLabel(String.valueOf(BattleLogic.currentPokemonPlayer.getLvl()));
        lvlPlayer.setBounds(750, 210, 100, 100);
        lvlPlayer.setFont(PixelFont.myCustomFont.deriveFont(18f));
        backgroundLabel.add(lvlPlayer);

        lvlNpc = new JLabel(String.valueOf(BattleLogic.currentPokemonNpc.getLvl()));
        lvlNpc.setBounds(317, 30, 100, 100);
        lvlNpc.setFont(PixelFont.myCustomFont.deriveFont(18f));
        backgroundLabel.add(lvlNpc);

        ImageIcon imagePokePlayer = ImageUtility
                .loadImage(new URI(BattleLogic.currentPokemonPlayer.getSprite().getBack()));
        imageLabelPlayer = new JLabel(ImageUtility.resizeIcon(imagePokePlayer, 200, 200));
        imageLabelPlayer.setBounds(100, 175, 200, 200);
        backgroundLabel.add(imageLabelPlayer);

        ImageIcon imagePokeNpc = ImageUtility.loadImage(new URI(BattleLogic.currentPokemonNpc.getSprite().getFront()));
        imageLabelNpc = new JLabel(ImageUtility.resizeIcon(imagePokeNpc, 200, 200));
        imageLabelNpc.setBounds(550, 50, 200, 200);
        backgroundLabel.add(imageLabelNpc);

        // Aggiungi le barre della salute al backgroundLabel
        backgroundLabel.add(playerHealthBar);
        backgroundLabel.add(npcHealthBar);

        for (int i = 0; i < BattleLogic.currentPokemonPlayer.getAbilities().size(); i++) {
            JButton abilityButton = createAbilityButton(BattleLogic.currentPokemonPlayer.getAbilities().get(i).getName(), i, npc);
            abilityPanel.add(abilityButton);
        }

        JButton changePoke = createChangePokemonButton(player, npc);
        abilityPanel.add(changePoke);

        // Aggiungi il pannello dei pulsanti delle abilità al frame
        frame.add(abilityPanel);
        // Aggiungi il JLabel al JFrame
        frame.add(backgroundLabel);

        // Altre componenti del frame...
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initialize(Coach player, Coach npc) {
        playerHealthBar = new JProgressBar(0, player.getTeam().getPokemon(0).getStats().getHp());
        playerHealthBar.setValue( player.getTeam().getPokemon(0).getStats().getHp());
        playerHealthBar.setStringPainted(true);
        playerHealthBar.setForeground(new Color(75, 242, 136));
        playerHealthBar.setBounds(618, 282, 168, 10);
        playerHealthBar.setStringPainted(false);

        npcHealthBar = new JProgressBar(0,  npc.getTeam().getPokemon(0).getStats().getHp());
        npcHealthBar.setValue( npc.getTeam().getPokemon(0).getStats().getHp());
        npcHealthBar.setStringPainted(true);
        npcHealthBar.setForeground(new Color(75, 242, 136));
        npcHealthBar.setBounds(185, 102, 168, 10);
        npcHealthBar.setStringPainted(false);

        if (this.startBattle == false) {
            System.out.println("Start Battle");
            BattleLogic.currentPokemonPlayer = player.getTeam().getPokemon(0);
            BattleLogic.currentPokemonNpc = npc.getTeam().getPokemon(0);
            BattleLogic.whoStart(player, npc);
            this.startBattle = true; // Update startBattle to true
        }
    }

    private JButton createAbilityButton(String name, int index, Coach npc) {
        JButton abilityButton = new JButton(name);
        abilityButton.setFont(PixelFont.myCustomFont);
        abilityButton.setForeground(Color.WHITE);
        abilityButton.setOpaque(false);
        abilityButton.setContentAreaFilled(false);
        abilityButton.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        abilityButton.setMargin(new Insets(10, 20, 10, 20));
        abilityButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        abilityButton.setFocusPainted(false);
        abilityButton.setBounds(70, 65, 350, 40);
        abilityButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        abilityButton.addActionListener(e -> {
            BattleLogic.decreaseHpNpc(npc, BattleLogic.currentPokemonPlayer.getAbilities().get(index).getStrength());
        });
        return abilityButton;
    }

    private JButton createChangePokemonButton(Coach player, Coach npc) {
        JButton changePoke = new JButton("Change Pokemon");
        changePoke.setFont(PixelFont.myCustomFont);
        changePoke.setForeground(Color.red);
        changePoke.setOpaque(false);
        changePoke.setContentAreaFilled(false);
        changePoke.setBorder(BorderFactory.createLineBorder(Color.red, 2));
        changePoke.setMargin(new Insets(10, 20, 10, 20));
        changePoke.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.red, 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        changePoke.setCursor(new Cursor(Cursor.HAND_CURSOR));
        changePoke.setFocusPainted(false);
        changePoke.addActionListener(e -> {
            new ChangePokemonFrame(player, this, npc);
        });
        changePoke.setBounds(90, 65, 350, 40);
        return changePoke;
    }

    // Aggiorna la barra della salute del giocatore
    public static void updatePlayerHealthBar(int currentHp) {
        playerHealthBar.setValue(currentHp);
    }

    // Aggiorna la barra della salute del NPC
    public static void updateNPCHealthBar(int currentHp) {
        npcHealthBar.setValue(currentHp);
    }

    public void updatePokemonDisplayPlayer(Coach player, Coach npc) throws IOException {
        // Update Pokémon name and level
        pokePlayer.setText(BattleLogic.currentPokemonPlayer.getName());
        lvlPlayer.setText(String.valueOf(BattleLogic.currentPokemonPlayer.getLvl()));

        // Update Pokémon image
        try {
            ImageIcon imagePokePlayer = ImageUtility.loadImage(new URI(BattleLogic.currentPokemonPlayer.getSprite().getBack()));
            imageLabelPlayer.setIcon(ImageUtility.resizeIcon(imagePokePlayer, 200, 200));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        // Update health bar
        playerHealthBar.setMaximum(BattleLogic.currentPokemonPlayer.getStats().getHp());
        playerHealthBar.setValue(BattleLogic.currentPokemonPlayer.getStats().getHp());

        // Refresh ability buttons
        abilityPanel.removeAll();

        for (int i = 0; i < BattleLogic.currentPokemonPlayer.getAbilities().size(); i++) {
            JButton abilityButton = createAbilityButton(BattleLogic.currentPokemonPlayer.getAbilities().get(i).getName(), i, npc);
            abilityPanel.add(abilityButton);
        }

        JButton changePoke = createChangePokemonButton(player, npc);
        abilityPanel.add(changePoke);

        abilityPanel.revalidate();
        abilityPanel.repaint();
    }

    public void updatePokemonDisplayNpc(Coach npc, Coach player) throws IOException {
        // Update Pokémon name and level
        pokeNpc.setText(BattleLogic.currentPokemonNpc.getName());
        lvlNpc.setText(String.valueOf(BattleLogic.currentPokemonNpc.getLvl()));

        // Update Pokémon image
        try {
            ImageIcon imagePokeNpc = ImageUtility.loadImage(new URI(BattleLogic.currentPokemonNpc.getSprite().getBack()));
            imageLabelPlayer.setIcon(ImageUtility.resizeIcon(imagePokeNpc, 200, 200));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        // Update health bar
        npcHealthBar.setMaximum(BattleLogic.currentPokemonNpc.getStats().getHp());
        npcHealthBar.setValue(BattleLogic.currentPokemonNpc.getStats().getHp());

        // Refresh ability buttons
        abilityPanel.removeAll();

        for (int i = 0; i < BattleLogic.currentPokemonNpc.getAbilities().size(); i++) {
            JButton abilityButton = createAbilityButton(BattleLogic.currentPokemonNpc.getAbilities().get(i).getName(), i, npc);
            abilityPanel.add(abilityButton);
        }

        JButton changePoke = createChangePokemonButton(player, npc);
        abilityPanel.add(changePoke);

        abilityPanel.revalidate();
        abilityPanel.repaint();
    }
}
