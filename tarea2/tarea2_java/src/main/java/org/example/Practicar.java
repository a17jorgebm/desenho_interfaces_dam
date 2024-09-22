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

        // Creamos los JLabel
        JLabel label1 = new JLabel("Etiqueta 1");
        JLabel label2 = new JLabel("Etiqueta 2");
        JLabel label3 = new JLabel("Etiqueta 3");
        JTextArea label4 = new JTextArea("<html>Etiqueta 4 asdkjfbashfuioawbhfoqweufbqwuoefbqweuobfqwuoebfqwoeubfqwoeubfqweuobfqwoeubfqwouefbqwoefbqwoeubfqwoeuebfqowbfqwoufbqwobfqwoufbqweoufb</html>");

        // Añadimos el label1 en la posición (0,0)
        gbc.gridx = 0;  // Columna 0
        gbc.gridy = 0;  // Fila 0
        gbc.gridwidth = 1;  // Ocupar una columna
        gbc.gridheight = 1;  // Ocupar una fila
        gbc.weightx = 1.0;  // El espacio adicional horizontal lo usará este label
        gbc.weighty = 1.0;  // El espacio adicional vertical lo usará este label
        gbc.fill = GridBagConstraints.HORIZONTAL;  // El JLabel se estira horizontalmente
        frame.add(label1, gbc);

        // Añadimos el label2 en la posición (1,0)
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        frame.add(label2, gbc);

        // Añadimos el label3 en la posición (0,1)
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;  // Ocupar dos columnas
        gbc.fill = GridBagConstraints.HORIZONTAL;
        frame.add(label3, gbc);

        // Añadimos el label4 en la posición (0,2)
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;  // Ocupar dos columnas
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        label4.setLineWrap(true);
        label4.setEditable(false);
        label4.setOpaque(true);
        frame.add(label4, gbc);

        // Hacemos visible la ventana
        frame.setVisible(true);
    }
}
