package org.example;

import javax.swing.*;
import java.awt.*;

public class proba {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("JLabel Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            // Create a JPanel with GridBagLayout
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            // Create a JLabel and set properties
            JLabel label = new JLabel("Centered Text");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);

            // Make the label fill the entire panel
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;  // Allow it to expand in both directions

            // Add the label to the panel
            panel.add(label, gbc);

            // Add the panel to the frame
            frame.add(panel);
            frame.setVisible(true);
        });
    }
}