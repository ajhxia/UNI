import javax.swing.*;

import Game.Coach;
import Game.Team;
import Pokemon.Pokemon;
import Shared.PixelFont;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Player extends JFrame {
    private JTextField nameField;
    private JTextField ageField;
    private JComboBox<String> genderComboBox;
    public static Coach player;
    private static ArrayList<Pokemon> playerTeam = new ArrayList<Pokemon>();

    public Player() {
        setTitle("Create a character");
        setSize(300, 200);
        setLayout(new BorderLayout()); // Utilizza BorderLayout come layout manager

        JPanel fieldsPanel = new JPanel(new GridLayout(3, 2, 5, 5)); // JPanel per contenere i campi di testo e le etichette
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Aggiungi un bordo vuoto per spaziatura

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(PixelFont.myCustomFont);
        fieldsPanel.add(nameLabel);

        nameField = new JTextField(10);
        nameField.setFont(PixelFont.myCustomFont);
        nameField.setPreferredSize(new Dimension(150, 30));
        fieldsPanel.add(nameField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(PixelFont.myCustomFont);   
        fieldsPanel.add(ageLabel);

        ageField = new JTextField(10);
        ageField.setFont(PixelFont.myCustomFont);
        ageField.setPreferredSize(new Dimension(150, 30));
        fieldsPanel.add(ageField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(PixelFont.myCustomFont);
        fieldsPanel.add(genderLabel);

        String[] genders = {"Male", "Female", "Other"};
        genderComboBox = new JComboBox<>(genders);
        genderComboBox.setBackground(Color.WHITE);
        genderComboBox.setFont(PixelFont.myCustomFont);
        fieldsPanel.add(genderComboBox);

        add(fieldsPanel, BorderLayout.CENTER); // Aggiungi il pannello dei campi al centro

        JButton createButton = new JButton("Create");
        createButton.setFont(PixelFont.myCustomFont);
        createButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createButton.setBackground(Color.WHITE);
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String ageText = ageField.getText();
                String gender = (String) genderComboBox.getSelectedItem();
        
                // Verifica se i campi sono vuoti o non validi
                if (name.isEmpty() || ageText.isEmpty()) {
                    JOptionPane.showMessageDialog(Player.this, "Please complete all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Esce dal metodo per evitare la creazione del personaggio
                }
        
                int age;
                try {
                    age = Integer.parseInt(ageText);
                    if (age <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Player.this, "You must enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Esce dal metodo per evitare la creazione del personaggio
                }
        
                // Se tutti i campi sono validi, crea il personaggio
                player = new Coach(name, age, new Team(playerTeam), gender);
                // Fai qualcosa con l'oggetto Coach creato
                Pokedex pokedex = new Pokedex(player);
                pokedex.setVisible(true);
                setVisible(false);

            }
        });
        createButton.setMargin(new Insets(10, 20, 10, 20));
        createButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        createButton.setContentAreaFilled(false);
        add(createButton, BorderLayout.SOUTH); // Aggiungi il pulsante alla parte inferiore

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
