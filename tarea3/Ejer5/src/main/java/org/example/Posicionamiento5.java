package org.example;

import javax.swing.*;
import java.awt.*;

public class Posicionamiento5 {
    private static final String VERDE_CLARO="#4ceba9";
    private static final String ROJO="#fe0000";
    private static final String NARANJA="#ff6d00";
    private static final String ROSA="#f000ff";
    private static final String VERDE_OSCURO="#387800";
    private static final String AZUL="#01509f";
    private static final String MARRON="#ca9e61";

    public static void main(String[] args) {
        JFrame ventana=new JFrame();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(new Dimension(1100,900));
        ventana.setMinimumSize(new Dimension(800, 600));
        ventana.setLocationRelativeTo(null);
        ventana.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;

        Insets insets=new Insets(0,0,0,0);

        JPanel main=new JPanel();
        main.setLayout(new GridBagLayout());
        ventana.add(main, c);

            JPanel esquerda=new JPanel();
            esquerda.setLayout(new GridBagLayout());
            insets.set(0,0,0,20);
            cambiarValoresGridBagConstraints(c,2.5,1,0,0,1,1,insets);
            main.add(esquerda, c);

                //verde claro
                JPanel verde=new JPanel();
                insets.set(0,0,20,0);
                cambiarValoresGridBagConstraints(c,1,1,0,0,1,1,insets);
                verde.setBackground(Color.decode(VERDE_CLARO));
                esquerda.add(verde, c);

                JPanel esqAbaixo=new JPanel();
                esqAbaixo.setLayout(new GridBagLayout());
                insets.set(0,0,0,0);
                cambiarValoresGridBagConstraints(c,1,1,0,1,1,1,insets);
                esquerda.add(esqAbaixo, c);

                    JPanel rojo=new JPanel();
                    insets.set(0,0,15,0);
                    cambiarValoresGridBagConstraints(c,0.4,0.3,0,0,1,1,insets);
                    rojo.setBackground(Color.decode(ROJO));
                    esqAbaixo.add(rojo, c);

                    JPanel naranja=new JPanel();
                    insets.set(0,0,0,0);
                    cambiarValoresGridBagConstraints(c,0.4,0.7,0,1,1,1,insets);
                    naranja.setBackground(Color.decode(NARANJA));
                    esqAbaixo.add(naranja, c);

                    JPanel rosa=new JPanel();
                    insets.set(0,15,0,0);
                    cambiarValoresGridBagConstraints(c,0.6,1,1,0,1,2,insets);
                    rosa.setBackground(Color.decode(ROSA));
                    esqAbaixo.add(rosa, c);

            JPanel dereita=new JPanel();
            dereita.setLayout(new GridBagLayout());
            insets.set(0,0,0,0);
            cambiarValoresGridBagConstraints(c,1,1,1,0,1,1,insets);
            main.add(dereita, c);

                JPanel verdeOscuro=new JPanel();
                verdeOscuro.setBackground(Color.decode(VERDE_OSCURO));
                insets.set(0,0,0,0);
                cambiarValoresGridBagConstraints(c,1,0.2,0,0,1,1,insets);
                dereita.add(verdeOscuro, c);

                JPanel azul=new JPanel();
                azul.setBackground(Color.decode(AZUL));
                insets.set(20,0,20,0);
                cambiarValoresGridBagConstraints(c,1,0.7,0,1,1,1,insets);
                dereita.add(azul, c);

                JPanel marron=new JPanel();
                marron.setBackground(Color.decode(MARRON));
                insets.set(0,0,0,0);
                cambiarValoresGridBagConstraints(c,1,0.1,0,2,1,1,insets);
                dereita.add(marron, c);

        ventana.revalidate();
        ventana.repaint();
        ventana.setVisible(true);
    }

    public static void cambiarValoresGridBagConstraints(
            GridBagConstraints c,
            double weightx,
            double weighty,
            int gridx,
            int gridy,
            int gridwidth,
            int gridheight
    ) {
        c.weightx = weightx;
        c.weighty = weighty;
        c.gridx =gridx;
        c.gridy = gridy;
        c.gridwidth = gridwidth;
        c.gridheight = gridheight;
    }

    public static void cambiarValoresGridBagConstraints(
            GridBagConstraints c,
            double weightx,
            double weighty,
            int gridx,
            int gridy,
            int gridwidth,
            int gridheight,
            Insets insets
    ) {
        c.weightx = weightx;
        c.weighty = weighty;
        c.gridx =gridx;
        c.gridy = gridy;
        c.gridwidth = gridwidth;
        c.gridheight = gridheight;
        c.insets = insets;
    }
}