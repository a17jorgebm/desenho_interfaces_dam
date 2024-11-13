package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import org.example.Funcions.*;

public class Main {
    private static final int CANTIDAD_EJERCICIOS=10;

    private JFrame ventanaBase;
    private JCheckBox[] checkBoxes;
    private LinkedList<JFrame> ventanasAbiertas;

    public Main(){
        this.ventanasAbiertas=new LinkedList<>();
        this.checkBoxes=new JCheckBox[CANTIDAD_EJERCICIOS];

        JFrame v=new JFrame("Elementos básicos");
        v.setSize(new Dimension(800,600));
        v.setLayout(new GridBagLayout());
        v.setLocationRelativeTo(null);
        v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.ventanaBase=v;
    }

    public static void main(String[] args) {
        new Main().iniciarApp();
    }

    public void iniciarApp(){
        ventanaBase.setTitle("Creacion de elementos basicos de UI");
        ventanaBase.setVisible(true);
        GridBagConstraints gbc=new GridBagConstraints();
        Funcions.cambiarValoresGBC(gbc,0,0,1,1,1,1,GridBagConstraints.BOTH);

        //selectores ventanas a abrir
        JPanel panelSelectores=new JPanel();
        panelSelectores.setLayout(new GridBagLayout());
        for (int i=1;i<=CANTIDAD_EJERCICIOS;i++){
            JCheckBox checkbox=new JCheckBox("Ejer "+i);
            panelSelectores.add(checkbox);
            checkBoxes[i-1]=checkbox;
        }
        Funcions.cambiarValoresGBC(gbc,0,0,1,5,1,1,GridBagConstraints.BOTH);
        ventanaBase.add(panelSelectores,gbc);

        JPanel panelBotons = new JPanel();
        panelBotons.setLayout(new GridBagLayout());
        Button botonAbrir = new Button("Abrir seleccionadas");
        botonAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSelectedExercices();
            }
        });
        Button botonAbrirTodas = new Button("Abrir todas");
        botonAbrirTodas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectAllAndOpen();
            }
        });
        Button botonCerrar= new Button("Cerrar todas");
        botonCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAllWindows();
            }
        });
        panelBotons.add(botonAbrir);
        panelBotons.add(botonAbrirTodas);
        panelBotons.add(botonCerrar);
        Funcions.cambiarValoresGBC(gbc,0,1,1,1,1,1,GridBagConstraints.BOTH);
        ventanaBase.add(panelBotons,gbc);

        ventanaBase.revalidate();
        ventanaBase.repaint();
    }

    private void selectAllAndOpen(){
        for (int i=0;i<checkBoxes.length;i++){
            checkBoxes[i].setSelected(true);
        }
        openSelectedExercices();
    }

    public void openSelectedExercices(){
        closeAllWindows();
        for (int i=0;i<checkBoxes.length;i++){
            if (checkBoxes[i].isSelected()){
                JFrame ventana = switch (i+1){
                    case 1 -> Ejercicios.ejer1();
                    case 2 -> Ejercicios.ejer2();
                    case 3 -> Ejercicios.ejer3();
                    case 4 -> Ejercicios.ejer4();
                    case 5 -> Ejercicios.ejer5();
                    case 6 -> Ejercicios.ejer6();
                    case 7 -> Ejercicios.ejer7();
                    case 8 -> Ejercicios.ejer8();
                    case 9 -> Ejercicios.ejer9();
                    case 10 -> Ejercicios.ejer10();
                    default -> null;
                };
                if (ventana!=null){
                    ventanasAbiertas.add(ventana);
                }
            }
        }
        ventanasAbiertas.forEach(ventana -> {
            ventana.setLocationRelativeTo(ventanaBase);
            //se se cerra que se borre da lista
            ventana.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    ventanasAbiertas.remove(ventana);
                }
            });
            ventana.setVisible(true);
        });
    }

    private void closeAllWindows(){
        ventanasAbiertas.forEach(JFrame::dispose);
        ventanasAbiertas.clear();
    }

}