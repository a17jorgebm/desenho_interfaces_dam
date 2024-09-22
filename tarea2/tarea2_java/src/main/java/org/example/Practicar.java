package org.example;

import javax.swing.*;
import java.awt.*;

public class Practicar {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ejemplo de GridBagLayout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800, 600));

        // Establecemos el layout de la ventana
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Insets insets = new Insets(5, 5, 5, 5);

        JPanel panel = new JPanel();


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = insets;
        gbc.fill = GridBagConstraints.BOTH;

        frame.add(panel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        insets.top=50;

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.GREEN);
        frame.add(panel2, gbc);


        // Hacemos visible la ventana
        frame.setVisible(true);
    }
}
