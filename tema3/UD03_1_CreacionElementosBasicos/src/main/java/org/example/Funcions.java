package org.example;

import javax.swing.*;
import java.awt.*;

public class Funcions {
    public static JFrame getGentanaBasica(){
        JFrame ventana = new JFrame();
        ventana.setSize(new Dimension(500,300));
        ventana.setLayout(new GridBagLayout());
        ventana.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        return ventana;
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
