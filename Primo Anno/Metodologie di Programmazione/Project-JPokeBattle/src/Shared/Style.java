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
                // Render the button transparent
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

            @Override
            public void setEnabled(boolean enabled) {
                super.setEnabled(enabled);
                if (!enabled) {
                    setForeground(Color.GRAY); // Change text color when disabled
                    setBorder(new RoundedBorder(Color.GRAY, 2, 20)); // Change border color when disabled
                    setCursor(Cursor.getDefaultCursor()); // Change cursor when disabled
                } else {
                    setForeground(colorIn); // Restore original text color when enabled
                    setBorder(new RoundedBorder(colorIn, 2, 20)); // Restore original border color when enabled
                    setCursor(new Cursor(Cursor.HAND_CURSOR)); // Restore cursor when enabled
                }
            }
        };

        button.setFont(PixelFont.myCustomFont.deriveFont((float) dimensionFont));
        button.setForeground(colorIn); // Set text color
        button.setContentAreaFilled(false); // Make the content area transparent
        button.setBorder(new RoundedBorder(colorIn, 2, 20)); // Set rounded border
        button.setMargin(new Insets(10, 20, 10, 20)); // Set padding (top, left, bottom, right)
        button.setFocusPainted(false); // Remove focus effect to improve appearance
        button.setBounds(x, y, width, height); // Position the button

        // Add a cursor effect
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Original text and border color
        Color originalTextColor = button.getForeground();
        Color hoverTextColor = Color.RED; // Text color during hover

        // Add a MouseListener to handle hover
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (button.isEnabled()) {
                    button.setForeground(hoverTextColor); // Change text color when mouse enters
                    button.setBorder(new RoundedBorder(hoverTextColor, 2, 20)); // Change border color
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (button.isEnabled()) {
                    button.setForeground(originalTextColor); // Restore original text color when mouse exits
                    button.setBorder(new RoundedBorder(originalTextColor, 2, 20)); // Restore original border color
                }
            }
        });

        return button;
    }

    // Custom border for rounded edges
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
