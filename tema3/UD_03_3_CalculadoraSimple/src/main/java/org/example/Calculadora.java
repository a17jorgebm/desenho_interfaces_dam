package org.example;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class Calculadora {
    private static final boolean START_IN_DARK_MODE=false;
    private static final String ICON_URL_CALCULATOR="/icons/calculator.png";
    private static final String ICON_URL_LIGHT_DARK="/icons/night-light.png";


    private boolean isDarkMode;
    private JFrame ventana;
    private JTextField txtEntradaNumeros;
    private GridBagLayout gridBagLayout;
    private GridBagConstraints gbc;

    public Calculadora(){
        this.isDarkMode = START_IN_DARK_MODE;
        this.ventana = new JFrame();
        this.txtEntradaNumeros = new JTextField();
        this.gridBagLayout = new GridBagLayout();
        this.gbc = new GridBagConstraints();
    }

    public static void main(String[] args) {
        Calculadora calculadora=new Calculadora();
        calculadora.startInterface();
    }

    private void startInterface(){
        SwingUtilities.invokeLater(()->{
            try {
                if (this.isDarkMode){
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                }else {
                    UIManager.setLookAndFeel(new FlatLightLaf());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            ventana.setSize(500, 600);
            ventana.setLayout(gridBagLayout);
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.validate(); //to apply the window size so when I do setLocation it works properly
            ventana.setLocationRelativeTo(null);
            ventana.setTitle("Calculadora");
            ventana.setIconImage(getScaledImageIcon(ICON_URL_CALCULATOR,16,16).getImage());

                JMenuBar barra=new JMenuBar();
                JMenu changeColorMode=new JMenu("");
                changeColorMode.setIcon(getScaledImageIcon(ICON_URL_LIGHT_DARK,16,16));
                changeColorMode.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        SwingUtilities.invokeLater(()->{
                            changeColorMode();
                        });
                    }
                });
                barra.add(changeColorMode);
                ventana.setJMenuBar(barra);

                //corpo
                JPanel panelVentanaCompleta=new JPanel();
                panelVentanaCompleta.setLayout(gridBagLayout);
                cambiarValoresGBC(gbc,0,0,1,1);
                ventana.add(panelVentanaCompleta,gbc);
                    //area de texto
                    txtEntradaNumeros.setHorizontalAlignment(SwingConstants.RIGHT);
                    txtEntradaNumeros.setFont(new Font("SansSerif", Font.BOLD, 24));
                    txtEntradaNumeros.setEditable(false);
                    txtEntradaNumeros.getCaret().setVisible(false);
                    txtEntradaNumeros.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    txtEntradaNumeros.setBackground(Color.LIGHT_GRAY);

                    cambiarValoresGBC(gbc,0,0,2,1,2,1,GridBagConstraints.BOTH,new Insets(2,2,2,2));
                    panelVentanaCompleta.add(txtEntradaNumeros,gbc);

                    //botones borrar
                    JPanel panelBotonesCompleto=new JPanel();
                    panelBotonesCompleto.setLayout(gridBagLayout);
                    cambiarValoresGBC(gbc,0,1,1,8,1,8,GridBagConstraints.BOTH);
                    panelVentanaCompleta.add(panelBotonesCompleto,gbc);
                        //botones borrado
                        JPanel panelBotonesBorrado=new JPanel();
                        panelBotonesBorrado.setLayout(gridBagLayout);
                        cambiarValoresGBC(gbc,0,0,1,1,1,1,GridBagConstraints.BOTH);
                        panelBotonesCompleto.add(panelBotonesBorrado,gbc);
                            JPanel botonC=getPanelWithButtonStyle("C");
                            cambiarValoresGBC(gbc,0,0,1,1,1,1,GridBagConstraints.BOTH,new Insets(2,2,2,2));
                            panelBotonesBorrado.add(botonC,gbc);
                            JPanel botonBorrarNumero=getPanelWithButtonStyle("<-");
                            cambiarValoresGBC(gbc,1,0,1,1,1,1,GridBagConstraints.BOTH,new Insets(2,2,2,2));
                            panelBotonesBorrado.add(botonBorrarNumero,gbc);
                        //botones numeros y operaciones
                        JPanel panelNumerosYOperaciones=new JPanel();
                        panelNumerosYOperaciones.setLayout(gridBagLayout);
                        cambiarValoresGBC(gbc,0,1,1,4,1,4,GridBagConstraints.BOTH);
                        panelNumerosYOperaciones.setBackground(Color.BLUE);
                        panelBotonesCompleto.add(panelNumerosYOperaciones,gbc);
                            //numeros
                            JPanel panelNumeros=new JPanel();
                            panelNumeros.setLayout(gridBagLayout);
                            cambiarValoresGBC(gbc,0,0,4,1,4,1,GridBagConstraints.BOTH);
                            panelNumeros.setBackground(Color.ORANGE);
                            panelNumerosYOperaciones.add(panelNumeros,gbc);
                                int gridy=0, gridx=0;
                                for (int i=1;i<10;i++){
                                    if (i==4 || i==7){
                                        gridy++;
                                        gridx=0; //algo falla nesto
                                    }
                                    JPanel numero=getPanelWithButtonStyle(String.valueOf(i));
                                    cambiarValoresGBC(gbc,gridx++,gridy,1,1,new Insets(2,2,2,2));
                                    panelNumeros.add(numero,gbc);
                                }
                            //operaciones
                            JPanel panelOperaciones=new JPanel();
                            panelOperaciones.setLayout(gridBagLayout);
                            cambiarValoresGBC(gbc,5,0,1,1,1,1,GridBagConstraints.BOTH);
                            panelOperaciones.setBackground(Color.YELLOW);
                            panelNumerosYOperaciones.add(panelOperaciones,gbc);
                                String[] operacionesDisponibles={"+","-","*","/"};
                                for (int i=0;i<operacionesDisponibles.length;i++){
                                    JPanel operacion=getPanelWithButtonStyle(operacionesDisponibles[i]);
                                    cambiarValoresGBC(gbc,0,i+1,1,1,new Insets(2,2,2,2));
                                    panelOperaciones.add(operacion,gbc);
                                }

                        //numero 0 y boton =
                        JPanel panel0andEquals=new JPanel();
                        panel0andEquals.setLayout(gridBagLayout);
                        cambiarValoresGBC(gbc,0,5,1,1,1,1,GridBagConstraints.BOTH);
                        panel0andEquals.setBackground(Color.CYAN);
                        panelBotonesCompleto.add(panel0andEquals,gbc);

            ventana.setVisible(true);
            SwingUtilities.updateComponentTreeUI(ventana);
            ventana.revalidate();
            ventana.repaint();
        });
    }

    //UI ---------------------------------------------------------------------------------------------------------------------------------
    private void changeColorMode(){
        try{
            if (isDarkMode){
                UIManager.setLookAndFeel(new FlatLightLaf());
                isDarkMode=false;
            }else {
                UIManager.setLookAndFeel(new FlatDarkLaf());
                isDarkMode=true;
            }
        }catch (UnsupportedLookAndFeelException e){
            System.out.println("Error ao cambiar o modo");
        }

        SwingUtilities.updateComponentTreeUI(ventana);
        ventana.revalidate();
        ventana.repaint();
    }

    private ImageIcon getScaledImageIcon(String path, int width, int height){
        URL url=Calculadora.class.getResource(path);
        if (url!=null){
            ImageIcon imageIcon=new ImageIcon(url);
            Image imageScalated=imageIcon.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
            return new ImageIcon(imageScalated);
        }else {
            System.out.println("Imposible recuperar o icono: "+path);
            return null;
        }
    }

    public static JLabel createText(String texto,int tamano){
        JLabel label=new JLabel(texto);
        label.setForeground(Color.decode("#FFFFFF"));
        label.setFont(new Font("Times New Roman",Font.BOLD,tamano));
        return label;
    }

    private static JPanel getPanelWithButtonStyle(String text){
        JPanel panel=new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gridBagConstraints=new GridBagConstraints();
        cambiarValoresGBC(gridBagConstraints,0,0,1,1);
        JLabel label=createText(text,20);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label,gridBagConstraints);
        return panel;
    }

    //funcions de interfaz
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
        objetoConstraints.fill=GridBagConstraints.BOTH;
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
        objetoConstraints.fill=GridBagConstraints.BOTH;
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