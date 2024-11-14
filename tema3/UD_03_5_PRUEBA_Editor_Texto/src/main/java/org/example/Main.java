package org.example;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Main {
    private static final String ICON_URL_LIGHT_DARK="/icons/night-light.png";

    private boolean isDarkMode=false;
    private JFrame ventana;
    private JLayeredPane layeredPane;
    private JTextArea textAreaEditor;
    private GridBagLayout gridBagLayout;
    private GridBagConstraints gbc;

    private Path openedFile;

    public Main(){
        this.ventana=new JFrame("Editor de texto");
        this.layeredPane=ventana.getLayeredPane();
        this.gbc=new GridBagConstraints();
        this.textAreaEditor=new JTextArea();

        this.openedFile=null;
    }

    public static void main(String[] args) {
        Main main=new Main();
        main.startInterface();
    }

    private void startInterface(){
        SwingUtilities.invokeLater(()->{
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
            } catch (Exception e) {
                e.printStackTrace();
            }

            ventana.setLocationRelativeTo(null);
            ventana.setSize(800, 600);
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JMenuBar barra=new JMenuBar();
            JMenu accion=new JMenu("Archivo");
            barra.add(accion);
                JMenuItem abrir = new JMenuItem("Abrir");
                abrir.addActionListener(e -> {
                    SwingUtilities.invokeLater(()->{
                        openTextFile();
                    });
                });
                JMenuItem guardar = new JMenuItem("Guardar");
                JMenuItem cerrar = new JMenuItem("Cerrar");
            accion.add(abrir);
            accion.add(guardar);
            accion.add(cerrar);
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

            ventana.add(new JScrollPane(this.textAreaEditor));
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
        URL url=Main.class.getResource(path);
        if (url!=null){
            ImageIcon imageIcon=new ImageIcon(url);
            Image imageScalated=imageIcon.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
            return new ImageIcon(imageScalated);
        }else {
            System.out.println("Imposible recuperar o icono: "+path);
            return null;
        }
    }

    private void openTextFile(){
        JFileChooser chooser=new JFileChooser();

        javax.swing.filechooser.FileFilter fileFilter=new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                String[] textFileFormats = {".txt", ".html", ".htm", ".md", ".json", ".xml", ".csv", ".log", ".css", ".js", ".java", ".sql", ".yaml", ".yml", ".php", ".rb", ".py", ".pl", ".cpp", ".h", ".c", ".swift", ".go", ".scala", ".tex", ".rtf", ".ini", ".conf", ".properties", ".bat", ".sh", ".bash", ".ts", ".twig", ".mdx", ".asciidoc", ".rst", ".htaccess"};
                String extension=pathname.getName().substring(pathname.getName().lastIndexOf("."));
                System.out.println(extension);
                return pathname.isFile() && Arrays.asList(textFileFormats).contains(extension);
            }

            @Override
            public String getDescription() {
                return "Only text files(*.txt, *.html...)";
            }
        };

        chooser.setFileFilter(fileFilter);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result=chooser.showOpenDialog(ventana);
        File selectedFile=null;
        if (result!=JFileChooser.APPROVE_OPTION){ return; }

        selectedFile=chooser.getSelectedFile();
        String fileText=getFileText(selectedFile.toPath());
        if (fileText==null){
            //mensaje de error
            return;
        }

        this.openedFile=selectedFile.toPath();
        this.textAreaEditor.setText(fileText);
        //pfff fallo criminal
    }

    //Data ---------------------------------------------------------------------------------------------------------------------------------
    private String getFileText(Path openedFile){
        if (!Files.exists(openedFile)){
            System.out.println("O ficheiro non existe");
            return null;
        }
        StringBuilder text=new StringBuilder();
        try(BufferedReader reader=new BufferedReader(new FileReader(openedFile.toFile()))){
            String line;
            while ((line=reader.readLine())!=null){
                text.append(line);
            }
            return text.toString();
        }catch (IOException e){
            return null;
        }
    }
}