package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Main {
    public static final String COLOR_TEXTO="#FFFFFF";

    public static void main(String[] args) {
        JFrame frame=crearVentana();
        JPanel panelHeader=crearPanelHeader();
        JPanel panelMedio=crearPanelMedio();
        JPanel panelFooter=crearPanelFooter();



        // Añadir los paneles al marco
        frame.add(panelHeader, BorderLayout.NORTH);
        frame.add(panelMedio, BorderLayout.CENTER);
        frame.add(panelFooter, BorderLayout.SOUTH);

        // Hacer visible la ventana
        frame.setVisible(true);
    }

    public static JFrame crearVentana(){
        // Crear el marco de la ventana
        JFrame frame = new JFrame("Ventana Básica");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(400,300));
        frame.setLayout(new BorderLayout());
        return frame;
    }

    public static JPanel crearPanelHeader(){
        // Crear los paneles para el header, cuerpo y footer
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.decode("#cf5659"));
        //headerPanel.setLayout(new BorderLayout());
        //headerPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        headerPanel.setBorder(new EmptyBorder(10,10,10,10));
        headerPanel.add(crearTexto("<header>",Color.decode(COLOR_TEXTO)));
        return headerPanel;
    }

    public static JPanel crearPanelFooter(){
        //footer
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.decode("#cf5659"));
        footerPanel.setBorder(new EmptyBorder(10,10,10,10));
        footerPanel.add(crearTexto("<footer>",Color.decode(COLOR_TEXTO)));

        return footerPanel;
    }

    public static JPanel crearPanelMedio(){
        JPanel bodyPanel = new JPanel();
        bodyPanel.setBackground(Color.BLACK);
        bodyPanel.setBorder(new EmptyBorder(10,10,10,10));

        //nav
        JPanel izquierdaCuerpo = new JPanel();
        izquierdaCuerpo.setBackground(Color.decode("#cf5659"));
        izquierdaCuerpo.setBorder(new EmptyBorder(10,10,10,10));
        izquierdaCuerpo.add(crearTexto("<nav>",Color.decode(COLOR_TEXTO)));
        bodyPanel.add(izquierdaCuerpo,BorderLayout.LINE_START);

        //centro cuerpo
        JPanel centroCuerpo = new JPanel();
        centroCuerpo.setBackground(Color.decode("#cf5659"));
        centroCuerpo.setBorder(new EmptyBorder(10,10,10,10));
        centroCuerpo.add(crearTexto("<section>",Color.decode(COLOR_TEXTO)));
        bodyPanel.add(centroCuerpo,BorderLayout.CENTER);

        //izquierda derecha
        JPanel derechaCuerpo = new JPanel();
        derechaCuerpo.setBackground(Color.decode("#cf5659"));
        derechaCuerpo.setBorder(new EmptyBorder(10,10,10,10));
        derechaCuerpo.add(crearTexto("<aside>",Color.decode(COLOR_TEXTO)));
        bodyPanel.add(derechaCuerpo,BorderLayout.CENTER);

        return bodyPanel;
    }

    public static JLabel crearTexto(String texto,Color color){
        JLabel label=new JLabel(texto);
        label.setForeground(color);
        return label;
    }
}