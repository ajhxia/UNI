package Shared;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Style {
    public static JButton createButton(Color colorIn, String title, int dimensionFont, int x, int y, int width, int height) {
        JButton button = new JButton(title) {
            @Override
            protected void paintComponent(Graphics g) {
                // Rende il bottone trasparente
                if (isOpaque()) {
                    g.setColor(getBackground());
                    g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                }
                super.paintComponent(g);
            }

            @Override
            public void updateUI() {
                super.updateUI();
                setOpaque(false);
            }
        };

        button.setFont(PixelFont.myCustomFont.deriveFont((float) dimensionFont));
        button.setForeground(colorIn); // Imposta il colore del testo
        button.setContentAreaFilled(false); // Rende trasparente l'area di contenuto del bottone
        button.setBorder(new RoundedBorder(colorIn, 2, 20)); // Imposta il bordo arrotondato
        button.setMargin(new Insets(10, 20, 10, 20)); // Imposta il padding (top, left, bottom, right)
        button.setFocusPainted(false); // Rimuove l'effetto focus per migliorare l'aspetto
        button.setBounds(x, y, width, height); // Posiziona il bottone

        // Aggiungo un ascoltatore per l'effetto pointer
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Colore originale del testo e del bordo
        Color originalTextColor = button.getForeground();
        Color hoverTextColor = Color.RED; // Colore del testo durante l'hover

        // Aggiungo un MouseListener per gestire l'hover
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(hoverTextColor); // Cambia il colore del testo quando il mouse entra
                button.setBorder(new RoundedBorder(hoverTextColor, 2, 20)); // Cambia il colore del bordo
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(originalTextColor); // Ripristina il colore del testo originale quando il mouse esce
                button.setBorder(new RoundedBorder(originalTextColor, 2, 20)); // Ripristina il colore del bordo originale
            }
        });

        return button;
    }

    // Border personalizzato per avere bordi arrotondati
    static class RoundedBorder extends AbstractBorder {
        private Color color;
        private int thickness;
        private int radius;

        public RoundedBorder(Color color, int thickness, int radius) {
            this.color = color;
            this.thickness = thickness;
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(color);
            g2d.setStroke(new BasicStroke(thickness));
            g2d.drawRoundRect(x, y, width - thickness, height - thickness, radius, radius);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(thickness, thickness, thickness, thickness);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.right = insets.top = insets.bottom = thickness;
            return insets;
        }
    }

    
}
