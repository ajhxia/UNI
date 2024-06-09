package Battle;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.swing.*;
import Pokemon.*;
import Shared.*;

public class AbilitySelection extends JFrame {

    private JFrame frame;
    private JPanel abilityPanel;
    private JPanel newAbilityPanel;
    private JComboBox<String> newAbilityComboBox;
    private JLabel instructionLabel;
    private JButton continueButton;
    Pokemon pokemon;

    public AbilitySelection(int indexPoke) throws IOException, URISyntaxException {

        frame = new JFrame("Ability Selection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(840, 355); // Dimensioni leggermente modificate per adattare meglio gli elementi
        frame.setLayout(null);

        pokemon = Battle.getPlayer().getTeam().getPokemon(indexPoke);

        // Aggiungi un JLabel per visualizzare il nome del Pokemon
        JLabel pokemonNameLabel = new JLabel("Pokémon's name: " + pokemon.getName());
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

        instructionLabel = new JLabel("Select a new move to learn");
        instructionLabel.setFont(PixelFont.myCustomFont.deriveFont(14f)); // Aumentato il font size per maggiore visibilità
        instructionLabel.setForeground(Color.black);
        instructionLabel.setBounds(240, 50, 550, 30); // Spostato verso destra e verso il basso
        frame.add(instructionLabel);

        newAbilityPanel = new JPanel(new GridLayout(1, 1, 10, 10));
        newAbilityPanel.setBounds(240, 80, 400, 30); // Ridimensionato e spostato verso il basso
        newAbilityPanel.setOpaque(false);
        initializeNewAbilityButtons(indexPoke);

        JLabel titleLabel = new JLabel("Number of ability available: " + pokemon.getAbilities().size());
        titleLabel.setFont(PixelFont.myCustomFont.deriveFont(14f)); // Aumentato il font size per maggiore visibilità
        titleLabel.setForeground(Color.black);
        titleLabel.setBounds(240, 150, 500, 30); // Spostato verso il basso
        frame.add(titleLabel);

        abilityPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        abilityPanel.setBounds(240, 180, 500, 50); // Spostato verso il basso
        abilityPanel.setOpaque(false);
        initializeAbilityButtons(indexPoke);

        continueButton = Style.createButton(Color.BLACK, "Continue", 12, 240, 260, 200, 40); // Spostato verso il basso
        continueButton.setEnabled(false); // Initially disabled
        continueButton.addActionListener(e -> {
            System.out.println("Selection completed.");
            // Assicurati che non ci siano più di 4 abilità dopo la rimozione
            while (pokemon.getAbilities().size() > 4) {
                pokemon.removeAbility(4);
            }
            frame.dispose();
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

        JButton selectNewAbilityButton = Style.createButton(Color.BLACK, "Select", 12, 240, 10, 100, 40);
        selectNewAbilityButton.addActionListener(e -> {
            String selectedAbilityName = (String) newAbilityComboBox.getSelectedItem();
            for (Ability newAbility : pokemon.getAbilities()) {
                if (newAbility.getName().equals(selectedAbilityName)) {
                    handleNewAbilitySelection(newAbility, indexPoke);
                    break;
                }
            }
            continueButton.setEnabled(true); // Enable the Continue button after selection
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
            JLabel messageLabel = new JLabel("Select an ability to remove:");
            messageLabel.setFont(PixelFont.myCustomFont.deriveFont(12f));
            String abilityToReplace = (String) JOptionPane.showInputDialog(
                    frame,
                    messageLabel,
                    "Substitute move",
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

    /*public static void main(String[] args) {
        PixelFont.loadCustomFont();
        ArrayList<Pokemon> npcTeam = new ArrayList<Pokemon>(Arrays.asList(CreateObjectsPokemon.getPokemon(3, 4), CreateObjectsPokemon.getPokemon(4, 55), CreateObjectsPokemon.getPokemon(6, 17), CreateObjectsPokemon.getPokemon(12, 0)));
        Team team = new Team(npcTeam);
        Coach player = new Coach("Player", 1, team, Gender.MALE );
        Battle.setPlayer(player);
        Battle.setNpc(player);

        try {
            new AbilitySelection(2);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }*/

}
