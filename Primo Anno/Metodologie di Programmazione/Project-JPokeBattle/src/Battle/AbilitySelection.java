package Battle;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.swing.*;

import Game.Coach;
import Pokemon.Ability;
import Pokemon.Pokemon;
import Shared.ImageUtility;
import Shared.PixelFont;

public class AbilitySelection extends JFrame {

    private JFrame frame;
    private JPanel abilityPanel;
    private JPanel newAbilityPanel;
    private JComboBox<String> newAbilityComboBox;
    private JLabel instructionLabel;
    Pokemon pokemon;

    public AbilitySelection(Coach player, Coach npc, int indexPoke) throws IOException, URISyntaxException {

        frame = new JFrame("Ability Selection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(840, 355); // Dimensioni leggermente modificate per adattare meglio gli elementi
        frame.setLayout(null);

        pokemon = player.getTeam().getPokemon(indexPoke);

        // Aggiungi un JLabel per visualizzare il nome del Pokemon
        JLabel pokemonNameLabel = new JLabel("Nome del Pokémon: " + pokemon.getName());
        pokemonNameLabel.setFont(PixelFont.myCustomFont.deriveFont(16f)); // Aumentato il font size per maggiore visibilità
        pokemonNameLabel.setForeground(Color.black);
        pokemonNameLabel.setBounds(240, 10, 550, 30); // Leggermente spostato verso il basso per distanziarlo dall'inizio
        frame.add(pokemonNameLabel);

        // Carica l'immagine del Pokémon
        ImageIcon imagePoke = ImageUtility.loadImage(new URI(pokemon.getSprite().getFront()));
        int newWidth = 200;
        int newHeight = 200;
        JLabel imageLabel = new JLabel(ImageUtility.resizeIcon(imagePoke, newWidth, newHeight));
        imageLabel.setBounds(15, 40, 200, 200); // Ridimensionato e spostato verso il basso per una migliore visualizzazione
        frame.add(imageLabel);

        instructionLabel = new JLabel("Seleziona una nuova mossa da apprendere");
        instructionLabel.setFont(PixelFont.myCustomFont.deriveFont(14f)); // Aumentato il font size per maggiore visibilità
        instructionLabel.setForeground(Color.black);
        instructionLabel.setBounds(240, 50, 550, 30); // Spostato verso destra e verso il basso
        frame.add(instructionLabel);

        newAbilityPanel = new JPanel(new GridLayout(1, 1, 10, 10));
        newAbilityPanel.setBounds(240, 80, 400, 70); // Ridimensionato e spostato verso il basso
        newAbilityPanel.setOpaque(false);
        initializeNewAbilityButtons(indexPoke);

        JLabel titleLabel = new JLabel("Numero di mosse possibili: " + pokemon.getAbilities().size());
        titleLabel.setFont(PixelFont.myCustomFont.deriveFont(14f)); // Aumentato il font size per maggiore visibilità
        titleLabel.setForeground(Color.black);
        titleLabel.setBounds(240, 150, 500, 30); // Spostato verso il basso
        frame.add(titleLabel);

        abilityPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        abilityPanel.setBounds(240, 180, 500, 50); // Spostato verso il basso
        abilityPanel.setOpaque(false);
        initializeAbilityButtons(indexPoke);

        JButton continueButton = new JButton("Continua");
        continueButton.setFont(PixelFont.myCustomFont.deriveFont(14f)); // Aumentato il font size per maggiore visibilità
        continueButton.setForeground(Color.black);
        continueButton.setOpaque(false);
        continueButton.setContentAreaFilled(false);
        continueButton.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        continueButton.setBounds(240, 260, 200, 40); // Spostato verso il basso e centrato orizzontalmente
        continueButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        continueButton.addActionListener(e -> {
            System.out.println("Selection completed.");
            try {
                // Assicurati che non ci siano più di 4 abilità dopo la rimozione
                while (pokemon.getAbilities().size() > 4) {
                    pokemon.removeAbility(4);
                }
                BattleFrame battleFrame = new BattleFrame(player, npc);
                frame.dispose();
            } catch (IOException | URISyntaxException e1) {
                e1.printStackTrace();
            }
        });

        frame.add(abilityPanel);
        frame.add(newAbilityPanel);
        frame.add(continueButton);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initializeAbilityButtons(int indexPoke) {
        abilityPanel.removeAll();
        List<Ability> abilities = pokemon.getAbilities();
        // Aggiungi i pulsanti per le abilità esistenti (massimo 4)
        for (int i = 0; i < abilities.size() && i < 4; i++) {
            final int index = i;
            JButton abilityButton = createAbilityButton(abilities.get(index));
            abilityPanel.add(abilityButton);
        }
        abilityPanel.revalidate();
        abilityPanel.repaint();
    }

    private void initializeNewAbilityButtons(int indexPoke) {
        newAbilityPanel.removeAll();

        newAbilityComboBox = new JComboBox<>();
        List<Ability> abilities = pokemon.getAbilities();
        // Aggiungi solo le abilità con indice > 4
        for (int i = 4; i < abilities.size(); i++) {
            newAbilityComboBox.addItem(abilities.get(i).getName());
        }
        newAbilityComboBox.setFont(PixelFont.myCustomFont.deriveFont(12f));
        newAbilityComboBox.setForeground(Color.black);
        newAbilityComboBox.setOpaque(false);
        newAbilityPanel.add(newAbilityComboBox);

        JButton selectNewAbilityButton = new JButton("Seleziona");
        selectNewAbilityButton.setFont(PixelFont.myCustomFont.deriveFont(12f));
        selectNewAbilityButton.setForeground(Color.black);
        selectNewAbilityButton.setOpaque(false);
        selectNewAbilityButton.setContentAreaFilled(false);
        selectNewAbilityButton.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        selectNewAbilityButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectNewAbilityButton.addActionListener(e -> {
            String selectedAbilityName = (String) newAbilityComboBox.getSelectedItem();
            for (Ability newAbility : pokemon.getAbilities()) {
                if (newAbility.getName().equals(selectedAbilityName)) {
                    handleNewAbilitySelection(newAbility, indexPoke);
                    break;
                }
            }
        });

        newAbilityPanel.add(selectNewAbilityButton);
    }

    private JButton createAbilityButton(Ability ability) {
        JButton abilityButton = new JButton(ability.getName());
        abilityButton.setFont(PixelFont.myCustomFont.deriveFont(12f));
        abilityButton.setForeground(Color.black);
        abilityButton.setOpaque(false);
        abilityButton.setContentAreaFilled(false);
        abilityButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.black, 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        abilityButton.setFocusPainted(false);
        abilityButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return abilityButton;
    }

    private void handleNewAbilitySelection(Ability newAbility, int indexPoke) {
        List<Ability> abilities = pokemon.getAbilities();
    
        // Aggiungi la nuova abilità
        pokemon.addAbility(newAbility);
        System.out.println("New ability selected: " + newAbility.getName());
    
        // Assicurati che ci siano non più di 4 abilità
        if (abilities.size() > 4) {
            // Chiedi all'utente di selezionare una mossa da rimuovere e sostituirla con la nuova abilità
            String[] abilityNames = new String[abilities.size()];
            for (int i = 0; i < 4; i++) {
                abilityNames[i] = abilities.get(i).getName();
            }
            JLabel messageLabel = new JLabel("Seleziona una mossa da sostituire:");
            messageLabel.setFont(PixelFont.myCustomFont.deriveFont(12f));
            String abilityToReplace = (String) JOptionPane.showInputDialog(
                    frame,
                    messageLabel,
                    "Sostituisci mossa",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    abilityNames,
                    abilityNames[0]
            );
            if (abilityToReplace != null) {
                for (int i = 0; i < 4; i++) {
                    if (abilities.get(i).getName().equals(abilityToReplace)) {
                        // Sostituisci l'abilità selezionata con la nuova abilità
                        pokemon.removeAbility(i);
                        pokemon.addAbilityAtIndex(newAbility, i);
                        break;
                    }
                }
            }
    
            // Assicurati che non ci siano più di 4 abilità dopo la rimozione
            while (abilities.size() > 4) {
                pokemon.removeAbility(4);
            }
        }
        initializeAbilityButtons(indexPoke); // Aggiorna i pulsanti delle abilità dopo l'aggiunta della nuova abilità
    }
    
}
