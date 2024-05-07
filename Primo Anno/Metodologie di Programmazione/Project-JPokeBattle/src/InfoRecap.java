import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Game.Coach;

public class InfoRecap extends JFrame {

    public InfoRecap(Coach player) {

        setTitle("Informazioni Giocatore");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JLabel nameLabel = new JLabel("Nome: " + player.getName());
        JLabel ageLabel = new JLabel("Età: " + player.getAge());
        JLabel teamLabel = new JLabel("Squadra: ");

        for (int i = 0; i < player.getTeam().getPlayerTeam().size(); i++) {
            JLabel poke = new JLabel(player.getTeam().getPokemon(i).getName());
            panel.add(poke);
        }

        panel.add(nameLabel);
        panel.add(ageLabel);
        panel.add(teamLabel);

        add(panel);

        setVisible(true);
        setLocationRelativeTo(null);
    }
}
