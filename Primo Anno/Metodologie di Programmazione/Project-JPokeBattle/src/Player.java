import javax.swing.*;

import Game.Coach;
import java.util.ArrayList;
import Game.Team;
import Shared.PixelFont;

import java.awt.*;
import java.awt.event.*;


public class Player extends JFrame {
    private JTextField nameField;
    private JTextField ageField;
    private JComboBox<String> genderComboBox;
    public static Coach player;

    public Player() {
        setTitle("Crea un personaggio");
        setSize(300, 200);
        setLayout(new GridBagLayout()); // Imposta il layout a GridBagLayout per il posizionamento flessibile dei componenti

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margine per separare i componenti

        JLabel nameLabel = new JLabel("Nome:");
        nameLabel.setFont(PixelFont.myCustomFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nameLabel, gbc);

        nameField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(nameField, gbc);

        JLabel ageLabel = new JLabel("Età:");
        ageLabel.setFont(PixelFont.myCustomFont);   
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(ageLabel, gbc);

        ageField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(ageField, gbc);

        JLabel genderLabel = new JLabel("Sesso:");
        genderLabel.setFont(PixelFont.myCustomFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(genderLabel, gbc);

        String[] genders = {"Maschio", "Femmina", "Altro"};
        genderComboBox = new JComboBox<>(genders);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(genderComboBox, gbc);

        JButton createButton = new JButton("Crea");
        createButton.setFont(PixelFont.myCustomFont);
        createButton.setBackground(Color.WHITE);
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String gender = (String) genderComboBox.getSelectedItem();

                player = new Coach(name, age, new Team(new ArrayList<>()), gender);
                // Fai qualcosa con l'oggetto Coach creato
                Pokedex pokedex = new Pokedex();
                pokedex.setVisible(true);
                setVisible(false);
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(createButton, gbc);

        setLocationRelativeTo(null); // Centra la finestra nello schermo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}