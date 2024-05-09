import javax.swing.*;

import Game.Coach;
import Game.Team;

import java.util.ArrayList;

import Shared.PixelFont;

import java.awt.*;
import java.awt.event.*;


public class Player extends JFrame {
    private JTextField nameField;
    private JTextField ageField;
    private JComboBox<String> genderComboBox;
    public static Coach player;

    public Player() {
        setTitle("Create a character");
        setSize(300, 200);
        setLayout(new GridBagLayout()); // Imposta il layout a GridBagLayout per il posizionamento flessibile dei componenti

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margine per separare i componenti

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(PixelFont.myCustomFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nameLabel, gbc);

        nameField = new JTextField(10);
        nameField.setFont(PixelFont.myCustomFont);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(nameField, gbc);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(PixelFont.myCustomFont);   
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(ageLabel, gbc);

        ageField = new JTextField(10);
        ageField.setFont(PixelFont.myCustomFont);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(ageField, gbc);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(PixelFont.myCustomFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(genderLabel, gbc);

        String[] genders = {"Male", "Female", "Other"};
        genderComboBox = new JComboBox<>(genders);
        genderComboBox.setBackground(Color.WHITE);
        genderComboBox.setFont(PixelFont.myCustomFont);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(genderComboBox, gbc);

        JButton createButton = new JButton("Create");
        createButton.setFont(PixelFont.myCustomFont);
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
                player = new Coach(name, age, new Team(new ArrayList<>()), gender);
                // Fai qualcosa con l'oggetto Coach creato
                Pokedex pokedex = new Pokedex();
                pokedex.setVisible(true);
                setVisible(false);
            }
        });

        createButton.setMargin(new Insets(10, 20, 10, 20)); // Imposta il padding (top, left, bottom, right)
        createButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(10, 20, 10, 20) // Imposta il padding all'interno del bordo
        ));
        createButton.setContentAreaFilled(false); // Rimuove il colore di sfondo

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(createButton, gbc);

        setLocationRelativeTo(null); // Centra la finestra nello schermo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}