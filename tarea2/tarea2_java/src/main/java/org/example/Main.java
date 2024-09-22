package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class Main{
    public static final String COLOR_TEXTO="#FFFFFF";
    public static final String ROJO="#cf5659";

    public static void marcos(String[] args){
        JFrame frame=new JFrame();
        // Configuración de la ventana principal
        frame.setTitle("Estructura Web Básica");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        // Definir colores
        Color rojo = new Color(210, 73, 58);  // Color rojo del header y otras secciones
        Color amarillo = new Color(203, 190, 130); // Color del article y section

        //HEADER SUPERIOR
        JPanel header = new JPanel();
        header.setBorder(new MatteBorder(0,0,10,0, Color.WHITE)); // Borde blanco de 2 píxeles
        header.setBackground(rojo);
        // Crear JLabel y aplicar el cambio de fuente
        JLabel headerLabel = new JLabel("<header>");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Cambiar tamaño de la fuente
        headerLabel.setForeground(Color.white);
        header.add(headerLabel); // Agregar JLabel al panel
        // Configuración de GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        frame.add(header, gbc);

        // NAV
        JPanel nav = new JPanel();
        nav.setBorder(new MatteBorder(0,0,0,10, Color.WHITE)); // Borde blanco de 2 píxeles
        nav.setBackground(rojo);
        // Crear JLabel y aplicar el cambio de fuente
        JLabel navLabel = new JLabel("<nav>");
        navLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Cambiar tamaño de la fuente
        navLabel.setForeground(Color.white);
        nav.add(navLabel); // Agregar JLabel al panel
        // Configuración de GridBagConstraints
        gbc.gridx = 0;        // Coloca el componente en la columna 0 (la primera columna).
        gbc.gridy = 1;        // Coloca el componente en la fila 1 (segunda fila).
        gbc.gridwidth = 1;    // El componente ocupará 1 columna de ancho.
        gbc.gridheight = 2;   // El componente ocupará 2 filas de alto.
        gbc.weighty = 1;      // Permite que el componente crezca verticalmente cuando la ventana se redimensiona.
        frame.add(nav, gbc);        // Añade el componente 'nav' con estas restricciones al contenedor.

        // ASIDE
        JPanel aside = new JPanel(new FlowLayout(FlowLayout.CENTER));
        aside.setBorder(new MatteBorder(0,10,0,0, Color.WHITE)); // Borde blanco de 2 píxeles
        aside.setBackground(rojo);
        // Crear JLabel y aplicar el cambio de fuente
        JLabel asideLabel = new JLabel("<aside>");
        asideLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Cambiar tamaño de la fuente
        asideLabel.setForeground(Color.white);
        aside.add(asideLabel); // Agregar JLabel al panel
        // Configuración de GridBagConstraints
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        frame.add(aside, gbc);


        //SECTION
        JPanel section = new JPanel(new GridBagLayout());
        section.setBackground(rojo);
        GridBagConstraints gbcSection = new GridBagConstraints();
        section.setBorder(new MatteBorder(-130,0,0,0, Color.getColor(null, rojo))); // Borde blanco de 2 píxeles

        JLabel sectionLabel = new JLabel("<section>");
        sectionLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Cambiar tamaño de la fuente
        sectionLabel.setForeground(Color.white);
        section.add(sectionLabel); // Agregar JLabel al panel


        gbcSection.fill = GridBagConstraints.BOTH;
        gbcSection.weightx = 1;
        gbcSection.weighty = 1;

        // HEADER2
        JPanel header2 = new JPanel();
        header2.setBorder(new MatteBorder(50,10,10,10, Color.getColor(null, rojo))); // Borde blanco de 2 píxeles
        header2.setBackground(amarillo);
        // Crear JLabel y aplicar el cambio de fuente
        JLabel header2Label = new JLabel("<header>");
        header2Label.setFont(new Font("Arial", Font.BOLD, 30)); // Cambiar tamaño de la fuente
        header2Label.setForeground(Color.white);
        header2.add(header2Label); // Agregar JLabel al panel
        // Configuración de GridBagConstraints
        gbcSection.gridx = 0;
        gbcSection.gridy = 0;
        gbcSection.gridwidth = 1;
        gbcSection.gridheight = 1;
        gbcSection.weighty = 1;

        gbcSection.anchor = GridBagConstraints.
        section.add(header2, gbcSection);

        // ARTICLE
        JPanel article = new JPanel();
        article.setBorder(new MatteBorder(0,10,10,10, Color.getColor(null, rojo))); // Borde blanco de 2 píxeles
        article.setBackground(amarillo);
        // Crear JLabel y aplicar el cambio de fuente
        JLabel articleLabel = new JLabel("<article>");
        articleLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Cambiar tamaño de la fuente
        articleLabel.setForeground(Color.white);
        article.add(articleLabel); // Agregar JLabel al panel
        // Configuración de GridBagConstraints
        gbcSection.gridx = 0;
        gbcSection.gridy = 1;
        gbcSection.weighty = 0.5;
        section.add(article, gbcSection);


        // FOOTER2
        JPanel footer2 = new JPanel();
        footer2.setBorder(new MatteBorder(0,10,10,10, Color.getColor(null, rojo))); // Borde blanco de 2 píxeles
        footer2.setBackground(amarillo);
        // Crear JLabel y aplicar el cambio de fuente
        JLabel footer2Label = new JLabel("<footer>");
        footer2Label.setFont(new Font("Arial", Font.BOLD, 30)); // Cambiar tamaño de la fuente
        footer2Label.setForeground(Color.white);
        footer2.add(footer2Label); // Agregar JLabel al panel
        // Configuración de GridBagConstraints
        gbcSection.gridx = 0;
        gbcSection.gridy = 2;
        gbcSection.weighty = 0.1;
        section.add(footer2, gbcSection);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        frame.add(section, gbc);

        // FOOTER
        JPanel footer = new JPanel();
        footer.setBorder(new MatteBorder(10,0,0,0, Color.WHITE)); // Borde blanco de 2 píxeles
        footer.setBackground(rojo);
        // Crear JLabel y aplicar el cambio de fuente
        JLabel footerLabel = new JLabel("<footer>");
        footerLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Cambiar tamaño de la fuente
        footerLabel.setForeground(Color.white);
        footer.add(footerLabel); // Agregar JLabel al panel
        // Configuración de GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.weighty = 0.1;
        frame.add(footer, gbc);

        // Hacer visible la ventana
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        JFrame frame=new JFrame();
        //configuramos a ventana
        frame.setMinimumSize(new Dimension(800,600));
        frame.setTitle("Ventana básica");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout(1,3));

        GridBagConstraints gbc=new GridBagConstraints();
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1;
        gbc.weighty=1;

        //header
        JPanel header=new JPanel();
        header.setBorder(new MatteBorder(0,0,0,0,Color.WHITE));
        header.setBackground(Color.RED);
        JLabel headerLabel=new JLabel("<header>");
        //headerLabel.setForeground(Color.WHITE);
        header.add(headerLabel);

        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridheight=1;
        gbc.gridwidth=3;
        gbc.weighty=0;
        frame.add(header,gbc);

        //centro
        JPanel centro=new JPanel();
        centro.setLayout(new GridBagLayout());
        centro.setBorder(new MatteBorder(10,0,10,0,Color.WHITE));
        centro.setBackground(Color.BLUE);
        JLabel centroLabel=new JLabel("centro");
        //centro.add(centroLabel);
        GridBagConstraints gbcCentro=new GridBagConstraints();
        gbcCentro.fill=GridBagConstraints.BOTH;
        gbcCentro.gridx=0;
        gbcCentro.gridy=1;
        gbcCentro.gridheight=2;
        gbcCentro.gridwidth=5;
        gbcCentro.weightx=1;
        gbcCentro.weighty=1;

        //izquierda
        JPanel izquierda=new JPanel();
        izquierda.setBackground(Color.RED);
        JLabel izquierdaLabel=new JLabel("<izquierda>");
        izquierda.add(izquierdaLabel);
        GridBagConstraints izquierdaGbc=new GridBagConstraints();
        izquierdaGbc.gridx=0;
        izquierdaGbc.gridy=0;
        izquierdaGbc.gridheight=2;
        izquierdaGbc.gridwidth=1;
        izquierdaGbc.weighty=1;
        izquierdaGbc.weightx=1;
        izquierdaGbc.fill=GridBagConstraints.BOTH;
        centro.add(izquierda,izquierdaGbc);

        //medio
        JPanel medio=new JPanel();
        medio.setBackground(Color.RED);
        medio.setBorder(new MatteBorder(0,10,0,10, Color.WHITE));
        JLabel medioLabel=new JLabel("<medio>");
        medio.add(medioLabel);
        GridBagConstraints medioGbc=new GridBagConstraints();
        medioGbc.gridx=1;
        medioGbc.gridy=0;
        medioGbc.gridheight=2;
        medioGbc.gridwidth=3;
        medioGbc.weighty=1;
        medioGbc.weightx=1;
        medioGbc.fill=GridBagConstraints.BOTH;
        centro.add(medio,medioGbc);

        //derecha
        JPanel derecha=new JPanel();
        derecha.setBackground(Color.RED);
        JLabel derechaLabel=new JLabel("<derecha>");
        derechaLabel.setVerticalAlignment(SwingConstants.CENTER);
        derecha.add(derechaLabel);
        GridBagConstraints derechaGbc=new GridBagConstraints();
        derechaGbc.gridx=4;
        derechaGbc.gridy=0;
        derechaGbc.gridheight=2;
        derechaGbc.gridwidth=1;
        derechaGbc.weighty=1;
        derechaGbc.weightx=1;
        derechaGbc.fill=GridBagConstraints.BOTH;
        centro.add(derecha,derechaGbc);

        frame.add(centro,gbcCentro);

        //footer
        JPanel footer=new JPanel();
        footer.setLayout(new GridBagLayout());
        footer.setBackground(Color.RED);
        JLabel footerLabel=new JLabel("<footer>");
        footer.add(footerLabel);
        GridBagConstraints footerGbc=new GridBagConstraints();
        footerGbc.gridx=0;
        footerGbc.gridy=3;
        footerGbc.gridheight=1;
        footerGbc.gridwidth=3;
        footerGbc.weightx=1;
        footerGbc.fill=GridBagConstraints.HORIZONTAL;
        frame.add(footer,footerGbc);

        frame.setVisible(true);
    }

    public static void main2(String[] args) {
        JFrame frame=crearVentana();
        JPanel panelHeader=crearPanelHeader();
        JPanel panelMedio=crearPanelMedio();
        JPanel panelFooter=crearPanelFooter();

        // Añadir los paneles al marco
        frame.add(panelHeader, BorderLayout.NORTH);
        frame.add(panelMedio, BorderLayout.CENTER);
        frame.add(panelFooter, BorderLayout.SOUTH);

        // Hacer visible la ventana
        frame.setVisible(true);
    }

    public static JFrame crearVentana(){
        // Crear el marco de la ventana
        JFrame frame = new JFrame("Ventana Básica");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(400,300));
        frame.setLayout(new BorderLayout());
        return frame;
    }

    public static JPanel crearPanelHeader(){
        // Crear los paneles para el header, cuerpo y footer
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.decode("#cf5659"));
        //headerPanel.setLayout(new BorderLayout());
        //headerPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        headerPanel.setBorder(new EmptyBorder(10,10,10,10));
        headerPanel.add(crearTexto("<header>",Color.decode(COLOR_TEXTO)));
        return headerPanel;
    }

    public static JPanel crearPanelFooter(){
        //footer
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.decode("#cf5659"));
        footerPanel.setBorder(new EmptyBorder(10,10,10,10));
        footerPanel.add(crearTexto("<footer>",Color.decode(COLOR_TEXTO)));

        return footerPanel;
    }

    public static JPanel crearPanelMedio(){
        JPanel bodyPanel = new JPanel();
        bodyPanel.setBackground(Color.BLACK);
        bodyPanel.setBorder(new EmptyBorder(10,10,10,10));
        bodyPanel.setLayout(new BorderLayout());

        //nav
        JPanel izquierdaCuerpo = new JPanel();
        izquierdaCuerpo.setBackground(Color.decode("#cf5659"));
        izquierdaCuerpo.setBorder(new EmptyBorder(10,10,10,10));
        izquierdaCuerpo.add(crearTexto("<nav>",Color.decode(COLOR_TEXTO)));
        bodyPanel.add(izquierdaCuerpo,BorderLayout.EAST);

        System.out.println((int)(bodyPanel.getWidth()));
        //centro cuerpo
        JPanel centroCuerpo = new JPanel();
        centroCuerpo.setSize(150,150);
        centroCuerpo.setBackground(Color.decode("#cf5659"));
        centroCuerpo.setBorder(new EmptyBorder(10,10,10,10));
        centroCuerpo.add(crearTexto("<section>",Color.decode(COLOR_TEXTO)));
        bodyPanel.add(centroCuerpo,BorderLayout.CENTER);

        //izquierda derecha
        JPanel derechaCuerpo = new JPanel();
        derechaCuerpo.setBackground(Color.decode("#cf5659"));
        derechaCuerpo.setBorder(new EmptyBorder(10,10,10,10));
        derechaCuerpo.add(crearTexto("<aside>",Color.decode(COLOR_TEXTO)));
        bodyPanel.add(derechaCuerpo,BorderLayout.SOUTH);

        return bodyPanel;
    }

    public static JLabel crearTexto(String texto,Color color){
        JLabel label=new JLabel(texto);
        label.setForeground(color);
        return label;
    }
}