package org.example;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Main {
    private static final String[] VALID_TEXT_FILE_EXTENSIONS = {".txt", ".html", ".htm", ".md", ".json", ".xml", ".csv", ".log", ".css", ".js", ".java", ".sql", ".yaml", ".yml", ".php", ".rb", ".py", ".pl", ".cpp", ".h", ".c", ".swift", ".go", ".scala", ".tex", ".rtf", ".ini", ".conf", ".properties", ".bat", ".sh", ".bash", ".ts", ".twig", ".mdx", ".asciidoc", ".rst", ".htaccess"};
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
        this.ventana=new JFrame();
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

            ventana.setSize(800, 600);
            ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            ventana.validate(); //to apply the window size so when I do setLocation it works properly
            ventana.setLocationRelativeTo(null);
            ventana.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    if (Main.this.fileContentHasBeenEdited){ //luis non me mates :)
                        int actionToDo = askIfSaveBeforeDoAction();
                        if (actionToDo==OPTION_CANCEL_OPERATION) return;
                        if (actionToDo==OPTION_SAVE_FILE) {
                            if(!saveContents(Main.this.textAreaEditor.getText())){
                                //mostrar mensaje de error
                                return; //cancelar operacion
                            }
                        }
                    }
                    Main.this.ventana.dispose();
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
                guardar.addActionListener(e -> {
                    saveContents(Main.this.textAreaEditor.getText());
                });
                JMenuItem cerrar = new JMenuItem("Cerrar");
                cerrar.addActionListener(e -> {
                    if (Main.this.fileContentHasBeenEdited){ //luis non me mates :)
                        int actionToDo = askIfSaveBeforeDoAction();
                        if (actionToDo==OPTION_CANCEL_OPERATION) return;
                        if (actionToDo==OPTION_SAVE_FILE) {
                            if(!saveContents(Main.this.textAreaEditor.getText())){
                                //mostrar mensaje de error
                                return; //cancelar operacion
                            }
                        }
                    }
                    Main.this.ventana.dispose();
                });
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

            this.textAreaEditor.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    textChanged();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    textChanged();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    textChanged();
                }

                private void textChanged(){
                    if (!Main.this.fileContentHasBeenEdited){ //para que non teña que cambiar o nome da ventana cada vez que se escribe algo
                        Main.this.fileContentHasBeenEdited=true;
                        Main.this.updateWindowName();
                    }
                }
            });
            ventana.add(new JScrollPane(this.textAreaEditor));

            updateWindowName();
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
        if (this.fileContentHasBeenEdited){
            int actionToDo = askIfSaveBeforeDoAction();
            if (actionToDo==OPTION_CANCEL_OPERATION) return;
            if (actionToDo==OPTION_SAVE_FILE) {
                if(!saveContents(this.textAreaEditor.getText())){
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

                String fileName=pathname.getName();
                int lastDotIntex=fileName.lastIndexOf(".");
                if (lastDotIntex==-1 || lastDotIntex==fileName.length()-1) return false;
                String extension=pathname.getName().substring(lastDotIntex);
                return pathname.isFile() && Arrays.asList(VALID_TEXT_FILE_EXTENSIONS).contains(extension);
            }

            @Override
            public String getDescription() {
                return "Only text files(*.txt, *.html, *.java...)";
            }
        };

        chooser.setFileFilter(fileFilter);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result=chooser.showOpenDialog(JOptionPane.getRootFrame());
        File selectedFile=null;
        if (result!=JFileChooser.APPROVE_OPTION){ return; }

        selectedFile=chooser.getSelectedFile();
        String fileText=getFileText(selectedFile.toPath());
        if (fileText==null){
            //mensaje de error
            System.out.println("Error ao abrir o ficheiro");
            return;
        }

        this.textAreaEditor.setText(fileText);
        this.openedFile=selectedFile.toPath();
        this.fileContentHasBeenEdited=false;
        updateWindowName();
    }

    private int askIfSaveBeforeDoAction(){
        // Opciones a mostrar
        String[] opciones = {"Guardar", "Continuar sin guardar", "Cancelar"};

        // Mostrar el cuadro de diálogo
        int saveBeforeOpenPanel = JOptionPane.showOptionDialog(
                JOptionPane.getRootFrame(), // Componente padre (null para centrar en la pantalla)
                "Tiene cambios sin guardar, que deseas hacer?", // Mensaje
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

    private Path createNewFile(){
        String newFileName=JOptionPane.showInputDialog("Nombre del nuevo archivo:");
        if (!isValidFileName(newFileName)){
            System.out.println("nombre non valido");
            return null;
        }

        JFileChooser jFileChooser=new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser.setName("Selecciona directorio de guardado");
        if (jFileChooser.showOpenDialog(null)!=JFileChooser.APPROVE_OPTION) return null;

        return jFileChooser.getSelectedFile().toPath().resolve(newFileName);
    }

    private boolean isValidFileName(String fileName){
        if (fileName == null || fileName.isBlank()) return false;
        if (!Pattern.matches("^[a-zA-Z0-9._-]+$", fileName)) return false;
        if (!fileName.contains(".")) return false;

        String extension = fileName.substring(fileName.lastIndexOf("."));
        if (!Arrays.asList(VALID_TEXT_FILE_EXTENSIONS).contains(extension)) return false;

        return true;
    }

    private void updateWindowName(){
        StringBuilder texto=new StringBuilder();
        if (fileContentHasBeenEdited) texto.append("*");
        texto.append(openedFile!=null ? openedFile.getFileName() : "Sin título");
        texto.append(" : Editor de texto");
        SwingUtilities.invokeLater(()->{
            this.ventana.setTitle(texto.toString());
        });
    }

    //Data ---------------------------------------------------------------------------------------------------------------------------------
    private String getFileText(Path fileToOpenPath){
        if (!Files.exists(fileToOpenPath)){
            System.out.println("O ficheiro non existe");
            return null;
        }
        StringBuilder text=new StringBuilder();
        try(BufferedReader reader=new BufferedReader(new FileReader(fileToOpenPath.toFile()))){
            String line;
            while ((line=reader.readLine())!=null){
                text.append(line);
            }
            return text.toString();
        }catch (IOException e){
            return null;
        }
    }

    private boolean saveContents(String textContent){
        Path fileToSave=this.openedFile;
        if (fileToSave==null || !Files.exists(fileToSave)){
            if ((fileToSave=createNewFile())==null) return false;
        }

        try(BufferedWriter output=new BufferedWriter(new FileWriter(fileToSave.toFile()));
            BufferedReader readerContent=new BufferedReader(new StringReader(textContent));
        ){
            String line;
            while ((line=readerContent.readLine())!=null){
                output.write(line);
                output.write(System.lineSeparator());
            }
        }catch (IOException e){
            System.out.println("Fallito o gardar: "+e.getMessage());
            return false;
        }
        this.openedFile=fileToSave;
        this.fileContentHasBeenEdited=false;
        updateWindowName();
        return true;
    }
}