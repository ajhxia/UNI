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

    public static JPanel abilityPanel;
    private boolean startBattle = false;

    private JLabel pokePlayer;
    private JLabel lvlPlayer;
    private JLabel imageLabelPlayer;

    private JLabel pokeNpc;
    private JLabel lvlNpc;
    private JLabel imageLabelNpc;

    private JFrame frame;

    public void setPokeInUse(int index) {
        BattleLogic.getPlayer().getTeam().getPokemon(index).setInUse(true);
        BattleLogic.getNpc().getTeam().getPokemon(index).setInUse(true);
    }

    public BattleFrame() throws IOException, URISyntaxException {

        Coach player = BattleLogic.getPlayer();
        Coach npc = BattleLogic.getNpc();

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
        imageLabelNpc.setBounds(550, 50, 200, 200);
        backgroundLabel.add(imageLabelNpc);

        // Aggiungi le barre della salute al backgroundLabel
        backgroundLabel.add(playerHealthBar);
        backgroundLabel.add(npcHealthBar);

        abilityPanel();

        // Aggiungi il pannello dei pulsanti delle abilità al frame
        frame.add(abilityPanel);
        // Aggiungi il JLabel al JFrame
        frame.add(backgroundLabel);

        // Altre componenti del frame...
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void abilityPanel() {
        abilityPanel.removeAll();
        for (int i = 0; i < BattleLogic.getPlayer().getPokemonInUse().getAbilities().size(); i++) {
            JButton abilityButton = createAbilityButton(BattleLogic.getPlayer(), i, BattleLogic.getNpc());
            abilityPanel.add(abilityButton);
        }

        JButton changePoke = createChangePokemonButton(BattleLogic.getPlayer(), BattleLogic.getNpc());
        abilityPanel.add(changePoke);
        abilityPanel.revalidate();
        abilityPanel.repaint();
    }

    private void showMessageAbility(int index) {
        abilityPanel.removeAll();
        JLabel message;
        if (BattleLogic.isTurn()) {
            message = new JLabel(
                    "You used " + BattleLogic.getPlayer().getPokemonInUse().getAbilities().get(index).getName());
        } else {
            message = new JLabel(
                    "The opponent used " + BattleLogic.getNpc().getPokemonInUse().getAbilities().get(index).getName());

        }
        message.setFont(PixelFont.myCustomFont.deriveFont(18f));
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

    private void initialize(Coach player, Coach npc) {

        setPokeInUse(0);

        playerHealthBar = new JProgressBar(0, player.getTeam().getPokemon(0).getStats().getHp());
        playerHealthBar.setValue(player.getTeam().getPokemon(0).getStats().getHp());
        playerHealthBar.setStringPainted(true);
        playerHealthBar.setForeground(new Color(75, 242, 136));
        playerHealthBar.setBounds(618, 282, 168, 10);
        playerHealthBar.setStringPainted(false);

        npcHealthBar = new JProgressBar(0, npc.getTeam().getPokemon(0).getStats().getHp());
        npcHealthBar.setValue(npc.getTeam().getPokemon(0).getStats().getHp());
        npcHealthBar.setStringPainted(true);
        npcHealthBar.setForeground(new Color(75, 242, 136));
        npcHealthBar.setBounds(185, 102, 168, 10);
        npcHealthBar.setStringPainted(false);

        if (this.startBattle == false) {
            System.out.println("Start Battle");
            player.getTeam().getPokemon(0).setInUse(true);
            npc.getTeam().getPokemon(0).setInUse(true);
            BattleLogic.whoStart();
            this.startBattle = true; // Update startBattle to true
        }
    }

    private JButton createAbilityButton(Coach player, int index, Coach npc) {
        JButton abilityButton = Style.createButton(Color.BLACK,
                player.getPokemonInUse().getAbilities().get(index).getName(), 12, 70, 65, 350, 40);
        abilityButton.addActionListener(e -> {
            BattleLogic.setTurn(true);
            BattleLogic.decreaseHpNpc(player.getPokemonInUse().getAbilities().get(index).getStrength(),
                    player.getPokemonInUse().getAbilities().get(index).getTypo());
            showMessageAbility(index);
            
        });
        return abilityButton;
    }

    private JButton createChangePokemonButton(Coach player, Coach npc) {
        JButton changePoke = Style.createButton(Color.BLACK, "Change Pokémon", 12, 90, 65, 350, 40);
        ;
        changePoke.addActionListener(e -> {
            new ChangePokemonFrame(this);
        });
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
        pokePlayer.setText(player.getPokemonInUse().getName());
        lvlPlayer.setText(String.valueOf(player.getPokemonInUse().getLvl()));

        // Update Pokémon image
        try {
            ImageIcon imagePokePlayer = ImageUtility.loadImage(new URI(player.getPokemonInUse().getSprite().getBack()));
            imageLabelPlayer.setIcon(ImageUtility.resizeIcon(imagePokePlayer, 200, 200));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        // Update health bar
        playerHealthBar.setMaximum(player.getPokemonInUse().getStats().getHp());
        playerHealthBar.setValue(player.getPokemonInUse().getStats().getHp());

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
}
