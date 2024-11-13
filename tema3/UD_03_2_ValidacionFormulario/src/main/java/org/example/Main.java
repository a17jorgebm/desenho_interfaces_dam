package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        GridBagLayout layoutGridBag=new GridBagLayout();
        JFrame ventana=new JFrame("Formulario simple");
        ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ventana.setSize(new Dimension(800,600));
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setLayout(layoutGridBag);

        GridBagConstraints gbc=new GridBagConstraints();
        cambiarValoresGBC(gbc,0,0,1,1);

        JPanel panelPrincipal=new JPanel();
        panelPrincipal.setLayout(layoutGridBag);
        panelPrincipal.setBorder(new EmptyBorder(145,45,145,45));
        gbc.fill=GridBagConstraints.BOTH;
        ventana.add(panelPrincipal,gbc);

            JPanel panelCampos=new JPanel();
            panelCampos.setLayout(layoutGridBag);
            cambiarValoresGBC(gbc,0,0,1,0.7);
            panelPrincipal.add(panelCampos,gbc);
                //name
                JLabel labelNome=new JLabel("Nome");
                cambiarValoresGBC(gbc,0,0,0.3,1,new Insets(0,0,0,40));
                gbc.fill=GridBagConstraints.VERTICAL;
                gbc.anchor=GridBagConstraints.LINE_END;
                panelCampos.add(labelNome,gbc);

                JTextField txtNome=new JTextField();
                cambiarValoresGBC(gbc,1,0,0.7,1);
                gbc.fill=GridBagConstraints.HORIZONTAL;
                gbc.anchor=GridBagConstraints.CENTER;
                panelCampos.add(txtNome,gbc);

                JLabel errorNome=new JLabel("");
                errorNome.setForeground(Color.RED);
                cambiarValoresGBC(gbc,1,1,0.7,1);
                gbc.fill=GridBagConstraints.HORIZONTAL;
                panelCampos.add(errorNome,gbc);

                //email
                JLabel labelEmail=new JLabel("Email");
                cambiarValoresGBC(gbc,0,2,0.3,1,new Insets(0,0,0,40));
                gbc.fill=GridBagConstraints.VERTICAL;
                gbc.anchor=GridBagConstraints.LINE_END;
                panelCampos.add(labelEmail,gbc);

                JTextField txtEmail=new JTextField();
                cambiarValoresGBC(gbc,1,2,0.7,1);
                gbc.fill=GridBagConstraints.HORIZONTAL;
                gbc.anchor=GridBagConstraints.CENTER;
                panelCampos.add(txtEmail,gbc);

                JLabel errorEmail=new JLabel("");
                errorEmail.setForeground(Color.RED);
                cambiarValoresGBC(gbc,1,3,0.7,1);
                gbc.fill=GridBagConstraints.HORIZONTAL;
                panelCampos.add(errorEmail,gbc);

                //contrasinal
                JLabel labelPassword=new JLabel("Contrasinal");
                cambiarValoresGBC(gbc,0,4,0.3,1,new Insets(0,0,0,40));
                gbc.fill=GridBagConstraints.VERTICAL;
                gbc.anchor=GridBagConstraints.LINE_END;
                panelCampos.add(labelPassword,gbc);

                JTextField txtPassword=new JTextField();
                cambiarValoresGBC(gbc,1,4,0.7,1);
                gbc.fill=GridBagConstraints.HORIZONTAL;
                gbc.anchor=GridBagConstraints.CENTER;
                panelCampos.add(txtPassword,gbc);

                JLabel errorPassword=new JLabel("");
                errorPassword.setForeground(Color.RED);
                cambiarValoresGBC(gbc,1,5,0.7,1);
                gbc.fill=GridBagConstraints.HORIZONTAL;
                panelCampos.add(errorPassword,gbc);

            JPanel panelBotons=new JPanel();
            panelBotons.setLayout(layoutGridBag);
            cambiarValoresGBC(gbc,0,1,1,0.3);
            gbc.fill=GridBagConstraints.BOTH;
            panelPrincipal.add(panelBotons,gbc);
                JButton botonEnviar=new JButton("Enviar");
                botonEnviar.addActionListener(e -> {
                    String nameText=txtNome.getText().trim();
                    String emailText=txtEmail.getText().trim();
                    String passwordText=txtPassword.getText().trim();

                    boolean todoCorrecto=true;
                    if (!correctName(nameText)){
                        todoCorrecto=false;
                        errorNome.setText("O nome non pode estar baleiro");
                    }else{ errorNome.setText(""); }

                    if (!correctEmail(emailText)){
                        todoCorrecto=false;
                        errorEmail.setText("Formato do email incorrecto");
                    }else{ errorEmail.setText(""); }

                    if (!correctPassword(passwordText)){
                        todoCorrecto=false;
                        errorPassword.setText("A contrasinal debe ter 8 caracteres como mínimo");
                    }else{ errorPassword.setText(""); }

                    if (todoCorrecto) {
                        txtNome.setText("");
                        txtEmail.setText("");
                        txtPassword.setText("");
                        JOptionPane.showMessageDialog(ventana,"Todo bien! Formulario enviado");
                    }
                });
                cambiarValoresGBC(gbc,0,0,1,1);
                gbc.fill=GridBagConstraints.NONE;
                gbc.anchor=GridBagConstraints.SOUTHEAST;
                panelBotons.add(botonEnviar,gbc);

        ventana.revalidate();
        ventana.repaint();
        ventana.setVisible(true);
    }

    private static boolean correctName(String name){
        return !name.isBlank();
    }

    private static boolean correctEmail(String email){
        if (email.isBlank()) return false;
        return Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$").matcher(email).matches();
    }

    private static boolean correctPassword(String password){
        return password.length()>7;
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