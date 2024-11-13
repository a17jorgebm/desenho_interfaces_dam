package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejercicios {
    public static JFrame ejer1(){
        JFrame ventana=Funcions.getGentanaBasica();
        ventana.setTitle("Ejer 1");

        JButton boton=new JButton("Mostrar mensaje");
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ola desde el ejercicio 1");
            }
        });
        ventana.add(boton);
        return ventana;
    }
    public static JFrame ejer2(){
        JFrame ventana=Funcions.getGentanaBasica();
        ventana.setTitle("Ejer 2");

        JButton boton1=new JButton("Aceptar");
        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Aceptado");
            }
        });
        JButton boton2=new JButton("Cancelar");
        boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cancelado");
            }
        });
        ventana.add(boton1);
        ventana.add(boton2);
        return ventana;
    }
    public static JFrame ejer3(){
        JFrame ventana=Funcions.getGentanaBasica();
        ventana.setTitle("Ejer 3");

        GridBagConstraints gbc=new GridBagConstraints();

        JTextField textField=new JTextField();
        JButton botonMostrar = new JButton("Mostrar nombre introducido");
        botonMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(textField.getText().trim().equals("")
                        ? "No se ha introducido nada"
                        : textField.getText().trim());
            }
        });

        Funcions.cambiarValoresGBC(gbc,0,0,1,1,new Insets(45,45,45,45));
        gbc.fill=GridBagConstraints.HORIZONTAL;
        ventana.add(textField,gbc);
        Funcions.cambiarValoresGBC(gbc,0,1,1,1,new Insets(45,45,45,45));
        ventana.add(botonMostrar,gbc);
        return ventana;
    }
    public static JFrame ejer4(){
        JFrame ventana=Funcions.getGentanaBasica();
        ventana.setTitle("Ejer 4");

        GridBagConstraints gbc=new GridBagConstraints();

        JPanel panelCompleto=new JPanel();
        panelCompleto.setLayout(new GridBagLayout());
        panelCompleto.setBorder(new EmptyBorder(45,45,45,45));
        Funcions.cambiarValoresGBC(gbc,0,0,0.5,1);
        gbc.fill=GridBagConstraints.BOTH;
        ventana.add(panelCompleto,gbc);


        JLabel labelNombre=new JLabel("Nombre:");
        JTextField textFieldNombre = new JTextField();
        Funcions.cambiarValoresGBC(gbc,0,0,0.5,1,new Insets(0,0,0,15));
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.anchor=GridBagConstraints.LINE_END;
        panelCompleto.add(labelNombre,gbc);
        Funcions.cambiarValoresGBC(gbc,1,0,1,1);
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.anchor=GridBagConstraints.CENTER;
        panelCompleto.add(textFieldNombre,gbc);

        JLabel labelApellidos=new JLabel("Apellidos:");
        JTextField textFieldApellidos = new JTextField();

        return ventana;
    }
    public static JFrame ejer5(){
        JFrame ventana=Funcions.getGentanaBasica();
        ventana.setTitle("Ejer 5");

        return ventana;
    }
    public static JFrame ejer6(){
        JFrame ventana=Funcions.getGentanaBasica();
        ventana.setTitle("Ejer 6");

        return ventana;
    }
    public static JFrame ejer7(){
        JFrame ventana=Funcions.getGentanaBasica();
        ventana.setTitle("Ejer 7");

        return ventana;
    }
    public static JFrame ejer8(){
        JFrame ventana=Funcions.getGentanaBasica();
        ventana.setTitle("Ejer 8");

        return ventana;
    }
    public static JFrame ejer9(){
        JFrame ventana=Funcions.getGentanaBasica();
        ventana.setTitle("Ejer 9");

        return ventana;
    }
    public static JFrame ejer10(){
        JFrame ventana=Funcions.getGentanaBasica();
        ventana.setTitle("Ejer 10");

        return ventana;
    }
}
