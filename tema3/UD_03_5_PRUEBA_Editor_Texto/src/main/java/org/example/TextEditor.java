package org.example;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;

public class TextEditor {
    private JFrame ventana;
    private GridBagLayout gridBagLayout;
    private GridBagConstraints gbc;

    public static void main(String[] args){
        TextEditor textEditor=new TextEditor();
        textEditor.startInterface();
    }

    public void startInterface(){

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
