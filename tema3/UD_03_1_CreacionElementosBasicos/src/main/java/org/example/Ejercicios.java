package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

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
                System.out.println(textField.getText().trim().isBlank()
                        ? "No se ha introducido nada"
                        : textField.getText().trim());
            }
        });

        Funcions.cambiarValoresGBC(gbc,0,0,1,1,new Insets(0,45,0,45));
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
        Funcions.cambiarValoresGBC(gbc,0,0,0.5,1,new Insets(0,0,0,15));
        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.LINE_END;
        panelCompleto.add(labelNombre,gbc);
        Funcions.cambiarValoresGBC(gbc,1,0,1,1);
        JTextField textFieldNombre = new JTextField();
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.anchor=GridBagConstraints.CENTER;
        panelCompleto.add(textFieldNombre,gbc);

        JLabel labelApellidos=new JLabel("Apellidos:");
        Funcions.cambiarValoresGBC(gbc,0,1,0.5,1,new Insets(0,0,0,15));
        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.LINE_END;
        panelCompleto.add(labelApellidos,gbc);
        Funcions.cambiarValoresGBC(gbc,1,1,1,1);
        JTextField textFieldApellidos = new JTextField();
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.anchor=GridBagConstraints.CENTER;
        panelCompleto.add(textFieldApellidos,gbc);

        JButton btnEnviar=new JButton("Enviar");
        Funcions.cambiarValoresGBC(gbc,0,2,1,1);
        panelCompleto.add(btnEnviar,gbc);

        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoNombre=textFieldNombre.getText().trim();
                String textoApellidos=textFieldApellidos.getText().trim();
                if (textoNombre.isBlank() || textoApellidos.isBlank()){
                    System.out.println("Completa el nombre y apellidos para enviar");
                }else {
                    System.out.printf("Listo! Tu nombre es %s %s", textoNombre, textoApellidos);
                }
            }
        });

        return ventana;
    }
    public static JFrame ejer5(){
        JFrame ventana=Funcions.getGentanaBasica();
        ventana.setTitle("Ejer 5");

        JButton botonMostrarDialogo=new JButton("Mostrar dialogo");
        botonMostrarDialogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String edad=JOptionPane.showInputDialog(ventana,"Introduce tu edad").trim();
                if (!edad.isBlank()){
                    JOptionPane.showMessageDialog(ventana,edad);
                }
                else {
                    JOptionPane.showMessageDialog(ventana,"No has introducido nada");
                }
            }
        });

        ventana.add(botonMostrarDialogo);

        return ventana;
    }
    public static JFrame ejer6(){
        JFrame ventana=Funcions.getGentanaBasica();
        ventana.setTitle("Ejer 6");

        String[] colores= {"Rojo", "Verde", "Azul"};
        JComboBox<String> comboBox=new JComboBox<>(colores);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(comboBox.getSelectedItem());
            }
        });

        ventana.add(comboBox);
        return ventana;
    }
    public static JFrame ejer7(){
        JFrame ventana=Funcions.getGentanaBasica();
        ventana.setTitle("Ejer 7");

        GridBagConstraints gbc=new GridBagConstraints();

        JPanel panelCompleto=new JPanel();
        panelCompleto.setLayout(new GridBagLayout());
        panelCompleto.setBorder(new EmptyBorder(45,45,45,45));
        Funcions.cambiarValoresGBC(gbc,0,0,0.5,1);
        gbc.fill=GridBagConstraints.BOTH;
        ventana.add(panelCompleto,gbc);


        JLabel labelNombre=new JLabel("Nombre:");
        Funcions.cambiarValoresGBC(gbc,0,0,0.5,1,new Insets(0,0,0,15));
        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.LINE_END;
        panelCompleto.add(labelNombre,gbc);
        Funcions.cambiarValoresGBC(gbc,1,0,1,1);
        JTextField textFieldNombre = new JTextField();
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.anchor=GridBagConstraints.CENTER;
        panelCompleto.add(textFieldNombre,gbc);

        JLabel labelContrasinal=new JLabel("Contraseña:");
        Funcions.cambiarValoresGBC(gbc,0,1,0.5,1,new Insets(0,0,0,15));
        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.LINE_END;
        panelCompleto.add(labelContrasinal,gbc);
        Funcions.cambiarValoresGBC(gbc,1,1,1,1);
        JPasswordField textFieldContrasena = new JPasswordField();
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.anchor=GridBagConstraints.CENTER;
        panelCompleto.add(textFieldContrasena,gbc);

        JButton btnEnviar=new JButton("Iniciar sesión");
        Funcions.cambiarValoresGBC(gbc,0,2,1,1);
        panelCompleto.add(btnEnviar,gbc);

        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoNombre=textFieldNombre.getText().trim();
                String textoContrasena=textFieldContrasena.getText().trim();
                if (textoNombre.isBlank() || textoContrasena.isBlank()){
                    System.out.println("Completa el nombre y contraseña para iniciar sesión");
                }else {
                    System.out.printf("Listo! Tu nombre es %s con contraseña %s", textoNombre, textoContrasena);
                }
            }
        });

        return ventana;
    }
    public static JFrame ejer8(){
        JFrame ventana=Funcions.getGentanaBasica();
        ventana.setTitle("Ejer 8");

        GridBagConstraints gbc=new GridBagConstraints();

        JPanel panelCompleto=new JPanel();
        panelCompleto.setLayout(new GridBagLayout());
        panelCompleto.setBorder(new EmptyBorder(45,45,45,45));
        Funcions.cambiarValoresGBC(gbc,0,0,0.5,1);
        gbc.fill=GridBagConstraints.BOTH;
        ventana.add(panelCompleto,gbc);

        Funcions.cambiarValoresGBC(gbc,0,0,1,1);
        JRadioButton radioTarjeta=new JRadioButton("Tarjeta de crédito");
        radioTarjeta.setActionCommand("Tarjeta de crédito");
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.anchor=GridBagConstraints.CENTER;
        panelCompleto.add(radioTarjeta,gbc);

        Funcions.cambiarValoresGBC(gbc,0,1,1,1);
        JRadioButton radioPaypal=new JRadioButton("PayPal");
        radioPaypal.setActionCommand("PayPal");
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.anchor=GridBagConstraints.CENTER;
        panelCompleto.add(radioPaypal,gbc);

        Funcions.cambiarValoresGBC(gbc,0,2,1,1);
        JRadioButton radioTransferencia=new JRadioButton("Transferencia bancaria");
        radioTransferencia.setActionCommand("Transferencia bancaria");
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.anchor=GridBagConstraints.CENTER;
        panelCompleto.add(radioTransferencia,gbc);

        ButtonGroup grupo=new ButtonGroup();
        grupo.add(radioTarjeta);
        grupo.add(radioPaypal);
        grupo.add(radioTransferencia);

        JButton button=new JButton("Confirmar");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonModel btnModelSelected=grupo.getSelection();
                System.out.println(btnModelSelected!=null
                        ? btnModelSelected.getActionCommand()
                        : "No se ha seleccionado nada!");
            }
        });
        Funcions.cambiarValoresGBC(gbc,0,3,1,1);
        panelCompleto.add(button,gbc);

        return ventana;
    }
    public static JFrame ejer9(){
        JFrame ventana=Funcions.getGentanaBasica();
        ventana.setTitle("Ejer 9");

        GridBagConstraints gbc=new GridBagConstraints();

        JPanel panelCompleto=new JPanel();
        panelCompleto.setLayout(new GridBagLayout());
        panelCompleto.setBorder(new EmptyBorder(45,45,45,45));
        Funcions.cambiarValoresGBC(gbc,0,0,0.5,1);
        gbc.fill=GridBagConstraints.BOTH;
        ventana.add(panelCompleto,gbc);

        String[] toppings={"Queso","Extra","Pepperoni","Aceitunas"};
        List<JCheckBox> checkboxes=new ArrayList<>();

        int posicionTopping=0;
        while (posicionTopping<toppings.length){
            Funcions.cambiarValoresGBC(gbc,0,posicionTopping,1,1);
            JCheckBox checkBox=new JCheckBox(toppings[posicionTopping]);
            gbc.fill=GridBagConstraints.HORIZONTAL;
            gbc.anchor=GridBagConstraints.CENTER;
            panelCompleto.add(checkBox,gbc);
            checkboxes.add(checkBox);
            ++posicionTopping;
        }

        JButton button=new JButton("Confirmar");
        button.addActionListener(e -> {
            List<JCheckBox> seleccionados=checkboxes.stream().filter(JCheckBox::isSelected).toList();
            if (!seleccionados.isEmpty()){
                StringBuilder stringBuilder=new StringBuilder();
                stringBuilder.append("Toppings seleccionados:");
                seleccionados.forEach(check -> stringBuilder.append(String.format("\n\t%s",check.getText())));
                System.out.println(stringBuilder);
            }else {
                System.out.println("No se ha seleccionado ningún topping!");
            }
        });
        Funcions.cambiarValoresGBC(gbc,0,posicionTopping,1,1);
        panelCompleto.add(button,gbc);

        return ventana;
    }
    public static JFrame ejer10(){
        JFrame ventana=Funcions.getGentanaBasica();
        ventana.setTitle("Ejer 10");

        JMenuBar barraMenu= new JMenuBar();
            JMenu menuArchivo=new JMenu("Archivo");
                JMenuItem itemAbrir=new JMenuItem("Abrir");
                itemAbrir.addActionListener(e -> System.out.println(itemAbrir.getText()));
                menuArchivo.add(itemAbrir);
                JMenuItem itemGuardar=new JMenuItem("Guardar");
                itemGuardar.addActionListener(e -> System.out.println(itemGuardar.getText()));
                menuArchivo.add(itemGuardar);
            JMenu menuEdicion=new JMenu("Edición");
            //nos jmenu usase mouseListener, mentras que nos jmenuitem actionlistener
                //en concreto mousePressed porque con mouseClicked hai que dar 2 veces para que ejecute a acción
            menuEdicion.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    System.out.println(menuEdicion.getText());
                }
            });
        barraMenu.add(menuArchivo);
        barraMenu.add(menuEdicion);

        ventana.setJMenuBar(barraMenu);
        return ventana;
    }
}
