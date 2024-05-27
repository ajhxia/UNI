import javax.swing.*;

import Battle.BattleLogic;
import Game.Coach;
import Game.Team;
import Pokemon.Pokemon;
import Shared.PixelFont;
import Shared.Style;

import java.awt.Color;
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
        setSize(300, 300);
        setLayout(null); // Utilizza un layout nullo

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(null);
        fieldsPanel.setBounds(10, 10, 280, 180); // Imposta posizione e dimensioni del pannello

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(PixelFont.myCustomFont.deriveFont(12f));
        nameLabel.setBounds(10, 10, 60, 30); // Posizione e dimensioni dell'etichetta
        fieldsPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setFont(PixelFont.myCustomFont.deriveFont(12f));
        nameField.setBounds(100, 10, 150, 30); // Posizione e dimensioni del campo di testo
        fieldsPanel.add(nameField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(PixelFont.myCustomFont.deriveFont(12f));
        ageLabel.setBounds(10, 50, 60, 30); // Posizione e dimensioni dell'etichetta
        fieldsPanel.add(ageLabel);

        ageField = new JTextField();
        ageField.setFont(PixelFont.myCustomFont.deriveFont(12f));
        ageField.setBounds(100, 50, 150, 30); // Posizione e dimensioni del campo di testo
        fieldsPanel.add(ageField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(PixelFont.myCustomFont.deriveFont(12f));
        genderLabel.setBounds(10, 90, 100, 30); // Posizione e dimensioni dell'etichetta
        fieldsPanel.add(genderLabel);

        String[] genders = {" Male", " Female", " Other"};
        genderComboBox = new JComboBox<>(genders);
        genderComboBox.setFont(PixelFont.myCustomFont.deriveFont(12f));
        genderComboBox.setBounds(100, 90, 150, 30); // Posizione e dimensioni del combobox
        fieldsPanel.add(genderComboBox);

        add(fieldsPanel);

        JButton createButton = Style.createButton(Color.BLACK, "Create", 12, 50,200, 200, 30);
       
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
                BattleLogic.setPlayer(player);
                Pokedex pokedex = new Pokedex();
                pokedex.setVisible(true);
                setVisible(false);
            }
        });
        add(createButton);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
