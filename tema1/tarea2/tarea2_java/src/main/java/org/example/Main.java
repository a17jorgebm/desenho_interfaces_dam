package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class Main{
    public static final String COLOR_TEXTO="#FFFFFF";
    public static final String ROJO="#cf5659";
    public static final String AMARILLO_RARO="#bdb76b";

    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.setMinimumSize(new Dimension(800,600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        Insets insets=new Insets(0,0,0,0);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx=1;
        gbc.weighty=1;
        gbc.gridheight = 1;
        gbc.gridwidth=1;
        gbc.fill = GridBagConstraints.BOTH;

        ///////////////////////// header /////////////////////////
        JPanel header = new JPanel();
        header.setLayout(new GridBagLayout());
        header.setBackground(Color.decode(ROJO));
        header.add(crearTexto("<header>",COLOR_TEXTO));
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(header, gbc);

        ///////////////////////// centro ////////////////////////////////////////////////////
        JPanel centro = new JPanel();
        centro.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty=8;
        insets.set(10,0,10,0);
        gbc.insets = insets;
        frame.add(centro, gbc);

        /////// izquierda
        JPanel izquierda=new JPanel();
        izquierda.setLayout(new GridBagLayout());
        izquierda.setBackground(Color.decode(ROJO));
        izquierda.add(crearTexto("<nav>",COLOR_TEXTO));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx=1;
        gbc.weighty=1;
        insets.set(0,0,0,0);
        gbc.insets = insets;
        centro.add(izquierda, gbc);

        //////// medio
        JPanel medio = new JPanel();
        medio.setLayout(new GridBagLayout());
        medio.setBackground(Color.decode(ROJO));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx=3;
        gbc.weighty=1;
        insets.set(0,10,0,10);
        gbc.insets = insets;
        centro.add(medio, gbc);

        /////////////////////////////// label medio
        JPanel medioParaLabel=new JPanel();
        medioParaLabel.setLayout(new GridBagLayout());
        medioParaLabel.setBackground(Color.decode(ROJO));
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx=1;
        gbc.weighty=1;
        gbc.anchor=GridBagConstraints.SOUTH;
        insets.set(0,10,0,10);
        gbc.insets = insets;
        medioParaLabel.add(crearTexto("<section>",COLOR_TEXTO),gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx=1;
        gbc.weighty=1;
        insets.set(0,0,0,0);
        gbc.insets = insets;
        medio.add(medioParaLabel, gbc);

        /////////////////////////////// medio interno
        JPanel medioInterno = new JPanel();
        medioInterno.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx=1;
        gbc.weighty=15;
        insets.set(0,10,10,10);
        gbc.insets = insets;
        medio.add(medioInterno, gbc);

        //////////////////////////////////////////////// header medio
        JPanel headerMedio = new JPanel();
        headerMedio.setLayout(new GridBagLayout());
        headerMedio.setBackground(Color.decode(AMARILLO_RARO));
        headerMedio.add(crearTexto("<header>",COLOR_TEXTO));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx=1;
        gbc.weighty=1;
        insets.set(0,0,0,0);
        gbc.insets = insets;
        medioInterno.add(headerMedio, gbc);

        //////////////////////////////////////////////// article medio
        JPanel articleMedio = new JPanel();
        articleMedio.setLayout(new GridBagLayout());
        articleMedio.setBackground(Color.decode(AMARILLO_RARO));
        articleMedio.setBorder(new MatteBorder(10,0,10,0,Color.decode(ROJO)));
        articleMedio.add(crearTexto("<article>",COLOR_TEXTO));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx=1;
        gbc.weighty=6;
        medioInterno.add(articleMedio, gbc);

        //////////////////////////////////////////////// footer medio
        JPanel footerMedio = new JPanel();
        footerMedio.setLayout(new GridBagLayout());
        footerMedio.setBackground(Color.decode(AMARILLO_RARO));
        footerMedio.add(crearTexto("<footer>",COLOR_TEXTO));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx=1;
        gbc.weighty=1;
        medioInterno.add(footerMedio, gbc);

        /////// derecha
        JPanel derecha=new JPanel();
        derecha.setLayout(new GridBagLayout());
        derecha.setBackground(Color.decode(ROJO));
        derecha.add(crearTexto("<aside>",COLOR_TEXTO));
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx=1;
        gbc.weighty=1;
        insets.set(0,0,0,0);
        gbc.insets = insets;
        centro.add(derecha, gbc);
        ///////////////////////// FIN centro ////////////////////////////////////////////////////

        ///////////////////////// footer /////////////////////////
        JPanel footer = new JPanel();
        footer.setLayout(new GridBagLayout());
        footer.setBackground(Color.decode(ROJO));
        footer.add(crearTexto("<footer>",COLOR_TEXTO));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty=1;
        insets.set(0,0,0,0);
        frame.add(footer, gbc);


        frame.setVisible(true);
    }

    public static JLabel crearTexto(String texto,String color){
        JLabel label=new JLabel(texto);
        label.setForeground(Color.decode(color));
        label.setFont(new Font("Arial",Font.BOLD,20));
        return label;
    }
}