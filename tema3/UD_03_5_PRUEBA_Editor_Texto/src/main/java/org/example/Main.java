package org.example;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Main {
    private static final String ICON_URL_LIGHT_DARK="/icons/night-light.png";
    private static final int OPTION_CANCEL_OPERATION=0;
    private static final int OPTION_SAVE_FILE=1;
    private static final int OPTION_DONT_SAVE_FILE=2;

    private boolean isDarkMode=false;
    private JFrame ventana;
    private JLayeredPane layeredPane;
    private JTextArea textAreaEditor;
    private GridBagLayout gridBagLayout;
    private GridBagConstraints gbc;

    private Path openedFile;
    public boolean fileContentHasBeenEdited;

    public Main(){
        this.ventana=new JFrame("Editor de texto");
        this.layeredPane=ventana.getLayeredPane();
        this.gbc=new GridBagConstraints();
        this.textAreaEditor=new JTextArea();

        this.openedFile=null;
        this.fileContentHasBeenEdited=false;
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
            ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            ventana.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    if (!this.fileContentHasBeenEdited){
                        int actionToDo = askIfSaveBeforeDoAction();
                        if (actionToDo==OPTION_CANCEL_OPERATION) return;
                        if (actionToDo==OPTION_SAVE_FILE) {
                            if(!saveTextFileContents()){
                                //mostrar mensaje de error
                                return; //cancelar operacion
                            }
                        }
                    }
                    super.windowClosing(e);
                }
            });

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
        if (!this.fileContentHasBeenEdited){
            int actionToDo = askIfSaveBeforeDoAction();
            if (actionToDo==OPTION_CANCEL_OPERATION) return;
            if (actionToDo==OPTION_SAVE_FILE) {
                if(!saveTextFileContents()){
                    //mostrar mensaje de error
                    return; //cancelar operacion
                }
            }
        }

        JFileChooser chooser=new JFileChooser();

        javax.swing.filechooser.FileFilter fileFilter=new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.isDirectory()) return true;

                String[] textFileFormats = {".txt", ".html", ".htm", ".md", ".json", ".xml", ".csv", ".log", ".css", ".js", ".java", ".sql", ".yaml", ".yml", ".php", ".rb", ".py", ".pl", ".cpp", ".h", ".c", ".swift", ".go", ".scala", ".tex", ".rtf", ".ini", ".conf", ".properties", ".bat", ".sh", ".bash", ".ts", ".twig", ".mdx", ".asciidoc", ".rst", ".htaccess"};
                String fileName=pathname.getName();
                int lastDotIntex=fileName.lastIndexOf(".");
                if (lastDotIntex==-1 || lastDotIntex==fileName.length()-1) return false;
                String extension=pathname.getName().substring(lastDotIntex);
                return pathname.isFile() && Arrays.asList(textFileFormats).contains(extension);
            }

            @Override
            public String getDescription() {
                return "Only text files(*.txt, *.html, *.java...)";
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
    }

    private int askIfSaveBeforeDoAction(){
        // Opciones a mostrar
        String[] opciones = {"Guardar", "Abrir sin guardar", "Cancelar"};

        // Mostrar el cuadro de diálogo
        int saveBeforeOpenPanel = JOptionPane.showOptionDialog(
                null, // Componente padre (null para centrar en la pantalla)
                "Tiene cambios sin guardar, que deseas hacer antes de abrir el nuevo archivo?", // Mensaje
                "Atención", // Título
                JOptionPane.DEFAULT_OPTION, // Tipo de opciones
                JOptionPane.QUESTION_MESSAGE, // Tipo de mensaje
                null, // Ícono personalizado (null para el predeterminado)
                opciones, // Opciones posibles
                opciones[2] // Opción predeterminada
        );
        if (saveBeforeOpenPanel==0) return OPTION_SAVE_FILE; //we save it before opening the new one
        if (saveBeforeOpenPanel==1) return OPTION_DONT_SAVE_FILE; //dont save and go ahead to the next actions
        return OPTION_CANCEL_OPERATION; //do nothing and cancel the next action
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

    private boolean saveTextFileContents(){
        return false;
    }
}