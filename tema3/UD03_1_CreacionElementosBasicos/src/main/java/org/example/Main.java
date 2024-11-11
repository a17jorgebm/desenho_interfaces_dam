package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import org.example.Funcions.*;

public class Main {
    private static final int CANTIDAD_EJERCICIOS=10;

    private JCheckBox[] checkBoxes;
    private LinkedList<JFrame> ventanasAbiertas;

    public Main(){
        this.ventanasAbiertas=new LinkedList<>();
        this.checkBoxes=new JCheckBox[CANTIDAD_EJERCICIOS];
    }

    public static void main(String[] args) {
        new Main().iniciarApp();
    }

    public void iniciarApp(){
        JFrame ventanaBase=Funcions.getGentanaBasica();
        ventanaBase.setTitle("Creacion de elementos basicos de UI");
        ventanaBase.setVisible(true);
        GridBagConstraints gbc=new GridBagConstraints();
        Funcions.cambiarValoresGBC(gbc,0,0,1,1,1,1,GridBagConstraints.BOTH);

        //selectores ventanas a abrir
        JPanel panelSelectores=new JPanel();
        panelSelectores.setLayout(new GridBagLayout());
        for (int i=1;i<=CANTIDAD_EJERCICIOS;i++){
            panelSelectores.add(new JCheckBox("Ejer "+i));
        }
        Funcions.cambiarValoresGBC(gbc,0,0,1,5,1,1,GridBagConstraints.BOTH);
        ventanaBase.add(panelSelectores,gbc);

        JPanel panelBotons = new JPanel();
        panelBotons.setLayout(new GridBagLayout());
        panelBotons.add(new Button("Abrir seleccionadas"));
        panelBotons.add(new Button("Cerrar todas"));
        Funcions.cambiarValoresGBC(gbc,0,1,1,1,1,1,GridBagConstraints.BOTH);
        ventanaBase.add(panelBotons,gbc);




        ventanaBase.revalidate();
        ventanaBase.repaint();
    }

    public void openSelectedExercices(){
        for (int i=0;i<checkBoxes.length;i++){
            if (checkBoxes[i].isSelected()){

            }
        }
    }

}