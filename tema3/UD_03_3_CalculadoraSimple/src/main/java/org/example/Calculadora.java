package org.example;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.Locale;

public class Calculadora {
    // Fondo de la calculadora
    private static final Color COLOR_FONDO_CALCULADORA = new Color(240, 240, 240);

    // Pantalla de resultados
    private static final Color COLOR_CUADRO_RESULTADOS = new Color(255, 255, 255); // Fondo
    private static final Color COLOR_TEXTO_RESULTADOS = new Color(0, 0, 0);       // Texto

    // Botones numéricos
    private static final Color COLOR_BOTONES_NUMERICOS = new Color(200, 200, 200); // Fondo
    private static final Color COLOR_BOTONES_NUMERICOS_HOVER = new Color(180, 180, 180); // Hover
    private static final Color COLOR_TEXTO_NUMERICOS = new Color(50, 50, 50);      // Texto

    // Botones de operaciones (+, -, *, /)
    private static final Color COLOR_BOTONES_OPERACIONES = new Color(85, 134, 136);        // Fondo
    private static final Color COLOR_BOTONES_OPERACIONES_HOVER = new Color(65, 114, 116); // Hover
    private static final Color COLOR_TEXTO_OPERACIONES = new Color(255, 255, 255);        // Texto

    // Botón de igual (=)
    private static final Color COLOR_BOTON_IGUAL = new Color(228, 181, 51);        // Fondo
    private static final Color COLOR_BOTON_IGUAL_HOVER = new Color(198, 151, 31); // Hover
    private static final Color COLOR_TEXTO_IGUAL = new Color(255, 255, 255);       // Texto

    // Botón de borrar (C/AC)
    private static final Color COLOR_BOTON_BORRAR = new Color(231, 76, 60);        // Fondo
    private static final Color COLOR_BOTON_BORRAR_HOVER = new Color(192, 57, 43); // Hover
    private static final Color COLOR_TEXTO_BORRAR = new Color(255, 255, 255);


    private boolean isDarkMode;
    private JFrame ventana;
    private JTextField txtEntradaNumeros;
    private GridBagLayout gridBagLayout;
    private GridBagConstraints gbc;
    private Operacion operacion;

    public Calculadora(){
        this.ventana = new JFrame();
        this.txtEntradaNumeros = new JTextField();
        this.gridBagLayout = new GridBagLayout();
        this.gbc = new GridBagConstraints();
        this.operacion=new Operacion();
    }

    public static void main(String[] args) {
        Calculadora calculadora=new Calculadora();
        calculadora.startInterface();
    }

    private void startInterface(){
        SwingUtilities.invokeLater(()->{
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
            } catch (Exception e) {
                e.printStackTrace();
            }

            ventana.setSize(500, 600);
            ventana.setLayout(gridBagLayout);
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.validate(); //to apply the window size so when I do setLocation it works properly
            ventana.setLocationRelativeTo(null);
            ventana.setTitle("Calculadora");

                //corpo
                JPanel panelVentanaCompleta=new JPanel();
                panelVentanaCompleta.setLayout(gridBagLayout);
                panelVentanaCompleta.setBackground(COLOR_FONDO_CALCULADORA);
                cambiarValoresGBC(gbc,0,0,1,1);
                ventana.add(panelVentanaCompleta,gbc);
                    //area de texto
                    txtEntradaNumeros.setHorizontalAlignment(SwingConstants.RIGHT);
                    txtEntradaNumeros.setFont(new Font("SansSerif", Font.BOLD, 24));
                    txtEntradaNumeros.setEditable(false);
                    txtEntradaNumeros.getCaret().setVisible(false);
                    txtEntradaNumeros.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    txtEntradaNumeros.setBackground(COLOR_CUADRO_RESULTADOS);
                    txtEntradaNumeros.setForeground(COLOR_TEXTO_RESULTADOS);

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
                            JPanel botonC=getPanelWithButtonStyle("C",COLOR_BOTON_BORRAR,COLOR_BOTON_BORRAR_HOVER,COLOR_TEXTO_BORRAR);
                            botonC.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    deleteTxtContent();
                                    Calculadora.this.operacion.resetearValores();
                                }
                            });
                            cambiarValoresGBC(gbc,0,0,1,1,1,1,GridBagConstraints.BOTH,new Insets(2,2,2,2));
                            panelBotonesBorrado.add(botonC,gbc);
                            JPanel botonBorrarNumero=getPanelWithButtonStyle("<-",COLOR_BOTONES_OPERACIONES,COLOR_BOTONES_OPERACIONES_HOVER,COLOR_TEXTO_OPERACIONES);
                            botonBorrarNumero.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    borrarNumeroTxt();
                                }
                            });
                            cambiarValoresGBC(gbc,1,0,1,1,1,1,GridBagConstraints.BOTH,new Insets(2,2,2,2));
                            panelBotonesBorrado.add(botonBorrarNumero,gbc);
                        //botones numeros y operaciones
                        JPanel panelNumerosYOperaciones=new JPanel();
                        panelNumerosYOperaciones.setLayout(gridBagLayout);
                        cambiarValoresGBC(gbc,0,1,1,4,1,4,GridBagConstraints.BOTH,new Insets(0,0,0,0));
                        panelBotonesCompleto.add(panelNumerosYOperaciones,gbc);
                            //numeros
                            JPanel panelNumeros=new JPanel();
                            panelNumeros.setLayout(gridBagLayout);
                            cambiarValoresGBC(gbc,0,0,4,1,4,1,GridBagConstraints.BOTH);
                            panelNumerosYOperaciones.add(panelNumeros,gbc);
                                int gridy=0, gridx=0;
                                for (int i=1;i<10;i++){
                                    if (i==4 || i==7){
                                        gridy++;
                                        gridx=0;
                                    }
                                    JPanel numero=getPanelWithButtonStyle(String.valueOf(i),COLOR_BOTONES_NUMERICOS,COLOR_BOTONES_NUMERICOS_HOVER,COLOR_TEXTO_NUMERICOS);
                                    final int valorNumero=i; //para poder acceder dentro
                                    numero.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mouseClicked(MouseEvent e) {
                                            writeNumber(valorNumero);
                                        }
                                    });
                                    cambiarValoresGBC(gbc,gridx++,gridy,1,1,1,1,GridBagConstraints.BOTH,new Insets(2,2,2,2));
                                    panelNumeros.add(numero,gbc);
                                }
                            //operaciones
                            JPanel panelOperaciones=new JPanel();
                            panelOperaciones.setLayout(gridBagLayout);
                            cambiarValoresGBC(gbc,5,0,1,1,1,1,GridBagConstraints.BOTH,new Insets(0,0,0,0));
                            panelNumerosYOperaciones.add(panelOperaciones,gbc);
                                String[] operacionesDisponibles={"+","-","*","/"};
                                for (int i=0;i<operacionesDisponibles.length;i++){
                                    final String simbolo=operacionesDisponibles[i];
                                    JPanel operacion=getPanelWithButtonStyle(operacionesDisponibles[i],COLOR_BOTONES_OPERACIONES,COLOR_BOTONES_OPERACIONES_HOVER,COLOR_TEXTO_OPERACIONES);
                                    operacion.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mouseClicked(MouseEvent e) {
                                            addSimbolo(simbolo);
                                        }
                                    });
                                    cambiarValoresGBC(gbc,0,i+1,1,1,new Insets(2,2,2,2));
                                    panelOperaciones.add(operacion,gbc);
                                }

                        //numero 0 y boton =
                        JPanel panel0andEquals=new JPanel();
                        panel0andEquals.setLayout(gridBagLayout);
                        cambiarValoresGBC(gbc,0,5,1,1,1,1,GridBagConstraints.BOTH);
                        panelBotonesCompleto.add(panel0andEquals,gbc);
                            JPanel numero0=getPanelWithButtonStyle("0",COLOR_BOTONES_NUMERICOS,COLOR_BOTONES_NUMERICOS_HOVER,COLOR_TEXTO_NUMERICOS);
                            numero0.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    writeNumber(0);
                                }
                            });
                            cambiarValoresGBC(gbc,0,0,1,1,2,1,GridBagConstraints.BOTH,new Insets(2,2,2,2));
                            panel0andEquals.add(numero0,gbc);

                            JPanel operacionIgual=getPanelWithButtonStyle("=",COLOR_BOTON_IGUAL,COLOR_BOTON_IGUAL_HOVER,COLOR_TEXTO_IGUAL);
                            operacionIgual.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    calcularOperacion();
                                }
                            });
                            cambiarValoresGBC(gbc,2,0,1,1,2,1,GridBagConstraints.BOTH,new Insets(2,2,2,2));
                            panel0andEquals.add(operacionIgual,gbc);

            ventana.setVisible(true);
            SwingUtilities.updateComponentTreeUI(ventana);
            ventana.revalidate();
            ventana.repaint();
        });
    }

    public void addSimbolo(String simbolo){
        if (operacion.isCompletada()) return;
        if (operacion.getNumero1()!=null || operacion.getNumero2()!=null) return;
        String numeroCampoTexto=this.txtEntradaNumeros.getText();
        if (numeroCampoTexto.isBlank()) return;

        operacion.setNumero1(Long.valueOf(numeroCampoTexto));
        operacion.setOperacion(simbolo.charAt(0));

        deleteTxtContent();
    }

    public void writeNumber(int numero){
        if (txtFieldExceedsLongitude()) return;
        if (operacion.isCompletada()){
            operacion.setCompletada(false);
            deleteTxtContent();
        }
        String textoActual=this.txtEntradaNumeros.getText();
        this.txtEntradaNumeros.setText(textoActual+numero);
    }

    public void calcularOperacion(){
        if (operacion.isCompletada()) return;
        String numero2=this.txtEntradaNumeros.getText();
        if (numero2.isBlank()) return;
        if (operacion.getNumero1()==null || operacion.getOperacion()==null) return;

        operacion.setNumero2(Long.valueOf(numero2));

        Float resultado= operacion.ejecutarOperacion();
        operacion.resetearValores();
        if (resultado!=null){
            Locale.setDefault(Locale.FRANCE);
            this.txtEntradaNumeros.setText(new DecimalFormat("#,##0.####").format(resultado));
        }else {
            this.txtEntradaNumeros.setText("Err");
        }
    }
    //UI ---------------------------------------------------------------------------------------------------------------------------------
    public void deleteTxtContent(){
        this.txtEntradaNumeros.setText("");
    }

    public void borrarNumeroTxt(){
        String numerosTxt=this.txtEntradaNumeros.getText();
        if (numerosTxt.isBlank()) return;
        this.txtEntradaNumeros.setText(numerosTxt.substring(0,numerosTxt.length()-1));
    }

    public boolean txtFieldExceedsLongitude(){
        return this.txtEntradaNumeros.getText().trim().length()>=19;
    }

    public static JLabel createText(String texto,int tamano,Color colorTexto){
        JLabel label=new JLabel(texto);
        label.setForeground(colorTexto);
        label.setFont(new Font("Times New Roman",Font.BOLD,tamano));
        return label;
    }

    private static JPanel getPanelWithButtonStyle(String text, Color colorFondo, Color colorHover, Color colorTexto){
        JPanel panel=new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(colorFondo);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(colorHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(colorFondo);
            }
        });
        GridBagConstraints gridBagConstraints=new GridBagConstraints();
        cambiarValoresGBC(gridBagConstraints,0,0,1,1,1,1,GridBagConstraints.NONE);
        JLabel label=createText(text,20,colorTexto);
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