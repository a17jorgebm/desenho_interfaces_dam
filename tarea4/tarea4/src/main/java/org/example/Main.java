package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Main {
    public static final String COLOR_ROJO="#cb5b5e";
    public static final String COLOR_AZUL="#7abcde";
    public static final int MARGIN_ENTRE_ELEMENTOS=10;
    public static void main(String[] args) {
        JFrame ventana=new JFrame();
        ventana.setMinimumSize(new Dimension(600,400));
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new GridBagLayout());

        Insets insets=new Insets(0,0,0,0);

        GridBagConstraints gbc=new GridBagConstraints();
        cambiarValoresGBC(gbc,0,0,1,1,1,1,GridBagConstraints.BOTH);

        JPanel main=new JPanel();
        main.setLayout(new GridBagLayout());
        ventana.add(main,gbc);

            JPanel header=new JPanel();
            header.setLayout(new GridBagLayout());
            header.setBackground(Color.decode(COLOR_ROJO));
            header.add(new JLabel("cabeceira"));
            cambiarValoresGBC(gbc,0,0,1,0.1);
            main.add(header,gbc);

            JPanel centro=new JPanel();
            centro.setLayout(new GridBagLayout());
            insets.set(MARGIN_ENTRE_ELEMENTOS,0,MARGIN_ENTRE_ELEMENTOS,0);
            cambiarValoresGBC(gbc,0,1,1,0.85,insets);
            main.add(centro,gbc);

                JPanel esquerda=new JPanel();
                esquerda.setLayout(new GridBagLayout());
                esquerda.setBackground(Color.decode(COLOR_AZUL));
                esquerda.add(new JLabel("menu de navegación"));
                insets.set(0,0,0,MARGIN_ENTRE_ELEMENTOS);
                cambiarValoresGBC(gbc,0,0,0.2,1,1,1,GridBagConstraints.BOTH,insets);
                centro.add(esquerda,gbc);

                JPanel dereita=new JPanel();
                dereita.setLayout(new GridBagLayout());
                dereita.setBackground(Color.decode(COLOR_ROJO));
                dereita.add(new JLabel("menu de navegación"));

                cambiarValoresGBC(gbc,1,0,0.8,1,3,1,GridBagConstraints.BOTH);
                centro.add(dereita,gbc);

            JPanel footer=new JPanel();
            footer.setLayout(new GridBagLayout());
            footer.setBackground(Color.decode(COLOR_ROJO));
            footer.add(new JLabel("pé"));
            cambiarValoresGBC(gbc,0,2,1,0.05);
            main.add(footer,gbc);

        ventana.revalidate();
        ventana.repaint();
        ventana.setVisible(true);
    }
    public static void cambiarValoresGBC(
            GridBagConstraints objetoConstraints,
            int gridx,
            int gridy,
            double weightx,
            double weighty
    ){
        objetoConstraints.gridx=gridx;
        objetoConstraints.gridy=gridy;
        objetoConstraints.weightx=weightx;
        objetoConstraints.weighty=weighty;
        //reseteanse os insets
        objetoConstraints.insets=new Insets(0,0,0,0);
    }
    public static void cambiarValoresGBC(
            GridBagConstraints objetoConstraints,
            int gridx,
            int gridy,
            double weightx,
            double weighty,
            Insets insets
    ){
        objetoConstraints.gridx=gridx;
        objetoConstraints.gridy=gridy;
        objetoConstraints.weightx=weightx;
        objetoConstraints.weighty=weighty;
        objetoConstraints.insets=insets;
    }
    public static void cambiarValoresGBC(GridBagConstraints objetoConstraints,int gridx,int gridy,double weightx,double weighty,int gridwidth,int gridheight,int fill){
        objetoConstraints.gridx=gridx;
        objetoConstraints.gridy=gridy;
        objetoConstraints.weightx=weightx;
        objetoConstraints.weighty=weighty;
        objetoConstraints.gridwidth=gridwidth;
        objetoConstraints.gridheight=gridheight;
        objetoConstraints.fill=fill;
        //reseteanse os insets
        objetoConstraints.insets=new Insets(0,0,0,0);
    }
    public static void cambiarValoresGBC(GridBagConstraints objetoConstraints,int gridx,int gridy,double weightx,double weighty,int gridwidth,int gridheight,int fill, Insets insets){
        objetoConstraints.gridx=gridx;
        objetoConstraints.gridy=gridy;
        objetoConstraints.weightx=weightx;
        objetoConstraints.weighty=weighty;
        objetoConstraints.gridwidth=gridwidth;
        objetoConstraints.gridheight=gridheight;
        objetoConstraints.fill=fill;
        objetoConstraints.insets=insets;
    }
}