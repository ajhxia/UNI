package Battle;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.*;
import Game.*;
import Shared.*;

public class BattleFrame extends JFrame {
    private JFrame frame;
    private JProgressBar playerHealthBar;
    private JProgressBar npcHealthBar;

    public BattleFrame(Coach player, Coach npc) throws IOException, URISyntaxException {

        frame = new JFrame("Battle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(865, 538);

        frame.setLayout(null);

        // Carica l'immagine di sfondo
        ImageIcon backgroundImage = new ImageIcon(RelativePath.getAbsolutePath("Image/PENUP_20240516_162632.png"));

        // Crea un JLabel con l'immagine di sfondo
        JLabel backgroundLabel = new JLabel(ImageUtility.resizeIcon(backgroundImage, 850, 500));
        backgroundLabel.setBounds(0, 0, 850, 500);

        JLabel pokePlayer = new JLabel(player.getTeam().getPokemon(0).getName());
        pokePlayer.setBounds(530, 210, 200, 100);
        pokePlayer.setFont(PixelFont.myCustomFont.deriveFont(18f));
        backgroundLabel.add(pokePlayer);

        JLabel pokeNpc = new JLabel(npc.getTeam().getPokemon(0).getName());
        pokeNpc.setBounds(95, 30, 200, 100);
        pokeNpc.setFont(PixelFont.myCustomFont.deriveFont(18f));
        backgroundLabel.add(pokeNpc);

        JLabel lvlPlayer = new JLabel(String.valueOf(player.getTeam().getPokemon(0).getLvl()));
        lvlPlayer.setBounds(750, 210, 100, 100);
        lvlPlayer.setFont(PixelFont.myCustomFont.deriveFont(18f));
        backgroundLabel.add(lvlPlayer);

        JLabel lvlNpc = new JLabel(String.valueOf(npc.getTeam().getPokemon(0).getLvl()));
        lvlNpc.setBounds(317, 30, 100, 100);
        lvlNpc.setFont(PixelFont.myCustomFont.deriveFont(18f));
        backgroundLabel.add(lvlNpc);

        ImageIcon imagePokePlayer = ImageUtility
                .loadImage(new URI(player.getTeam().getPokemon(0).getSprite().getBack()));
        JLabel imageLabelPlayer = new JLabel(ImageUtility.resizeIcon(imagePokePlayer, 200, 200));
        imageLabelPlayer.setBounds(100, 175, 200, 200);
        backgroundLabel.add(imageLabelPlayer);

        ImageIcon imagePokeNpc = ImageUtility.loadImage(new URI(npc.getTeam().getPokemon(0).getSprite().getFront()));
        JLabel imageLabelNpc = new JLabel(ImageUtility.resizeIcon(imagePokeNpc, 200, 200));
        imageLabelNpc.setBounds(550, 50, 200, 200);
        backgroundLabel.add(imageLabelNpc);

        // Inizializza le barre della salute del giocatore e del NPC
        playerHealthBar = new JProgressBar(0, player.getTeam().getPokemon(1).getStats().getHp());
        playerHealthBar.setValue(player.getTeam().getPokemon(1).getStats().getHp());
        playerHealthBar.setStringPainted(true);
        playerHealthBar.setForeground(new Color(75, 242, 136));
        playerHealthBar.setBounds(618, 282, 168, 10);
        playerHealthBar.setStringPainted(false);
        backgroundLabel.add(playerHealthBar);

        npcHealthBar = new JProgressBar(0, npc.getTeam().getPokemon(1).getStats().getHp());
        npcHealthBar.setValue(npc.getTeam().getPokemon(1).getStats().getHp());
        npcHealthBar.setStringPainted(true);
        npcHealthBar.setForeground(new Color(75, 242, 136));
        npcHealthBar.setBounds(185, 102, 168, 10);
        npcHealthBar.setStringPainted(false);
        backgroundLabel.add(npcHealthBar);

        // Pannello per i pulsanti delle abilità
        JPanel abilityPanel = new JPanel(new GridLayout(2, 4, 10, 10)); // 2 righe, 2 colonne, gap di 10 pixel
        abilityPanel.setBounds(50, 375, 750, 100);
        abilityPanel.setOpaque(false); // Imposta lo sfondo trasparente

        for (int i = 0; i < player.getTeam().getPokemon(0).getAbilities().size(); i++) {
            JButton abilityButton = new JButton(player.getTeam().getPokemon(0).getAbilities().get(i).getName());
            abilityButton.setFont(PixelFont.myCustomFont);
            abilityButton.setForeground(Color.WHITE); // Imposta il colore del testo
            abilityButton.setOpaque(false); // Rende il bottone trasparente
            abilityButton.setContentAreaFilled(false); // Rende trasparente l'area di contenuto del bottone
            abilityButton.setBorder(BorderFactory.createLineBorder(Color.white, 2)); // Imposta il bordo
            abilityButton.setMargin(new Insets(10, 20, 10, 20)); // Imposta il padding (top, left, bottom, right)
            abilityButton.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.white, 2),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20) // Imposta il padding all'interno del bordo
            ));
            abilityButton.setFocusPainted(false); // Rimuove l'effetto focus per migliorare l'aspetto
            abilityButton.setBounds(70, 65, 350, 40); // Posiziona il bottone

            // Aggiungo un ascoltatore per l'effetto pointer
            abilityButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            abilityButton.addActionListener(e -> {
                System.out.println("Ability selected: " + abilityButton.getText());
            });

            abilityPanel.add(abilityButton);
        }

        JButton changePoke = new JButton("Change Pokemon");
        changePoke.setFont(PixelFont.myCustomFont);
        changePoke.setForeground(Color.red); // Imposta il colore del testo
        changePoke.setOpaque(false); // Rende il bottone trasparente
        changePoke.setContentAreaFilled(false); // Rende trasparente l'area di contenuto del bottone
        changePoke.setBorder(BorderFactory.createLineBorder(Color.red, 2)); // Imposta il bordo
        changePoke.setMargin(new Insets(10, 20, 10, 20)); // Imposta il padding (top, left, bottom, right)
        changePoke.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.red, 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20) // Imposta il padding all'interno del bordo
        ));
        changePoke.setFocusPainted(false); // Rimuove l'effetto focus per migliorare l'aspetto
        changePoke.setBounds(90, 65, 350, 40); // Posiziona il bottone

        abilityPanel.add(changePoke);

        // Aggiungi il pannello dei pulsanti delle abilità al frame
        frame.add(abilityPanel);
        // Aggiungi il JLabel al JFrame
        frame.add(backgroundLabel);

        // Altre componenti del frame...
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Aggiorna la barra della salute del giocatore
    public void updatePlayerHealthBar(int currentHp) {
        playerHealthBar.setValue(currentHp);
    }

    // Aggiorna la barra della salute del NPC
    public void updateNPCHealthBar(int currentHp) {
        npcHealthBar.setValue(currentHp);
    }
}
