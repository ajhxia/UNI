package Battle;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.*;
import Game.Coach;
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
        
        ImageIcon imagePokePlayer = ImageUtility.loadImage(new URI(player.getTeam().getPokemon(0).getSprite().getBack()));
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

        /*for(int i = 0; i<player.getTeam().getPokemon(0).getAbilities().length; i++){
            JButton abilityButton = new JButton(player.getTeam().getPokemon(0).getAbilities()[i].getName());
            abilityButton.setBounds(100, 400 + i*50, 200, 50);
            abilityButton.setFont(PixelFont.myCustomFont.deriveFont(18f));
            abilityButton.setCursor(new Cursor(Cursor.HAND_CURSOR));   
            frame.add(abilityButton);
        }*/
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
