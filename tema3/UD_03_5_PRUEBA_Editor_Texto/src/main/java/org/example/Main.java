package org.example;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Custom Look and Feel");
        frame.setLocationRelativeTo(null);
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar barra=new JMenuBar();
        JMenu accion=new JMenu("Archivo");
        barra.add(accion);
            JMenuItem abrir = new JMenuItem("Abrir");
            JMenuItem guardar = new JMenuItem("Guardar");
            JMenuItem cerrar = new JMenuItem("Cerrar");
            accion.add(abrir);
            accion.add(guardar);
            accion.add(cerrar);

        frame.setJMenuBar(barra);

        JTextArea textArea = new JTextArea();
        frame.add(new JScrollPane(textArea));
        frame.setVisible(true);
    }
}