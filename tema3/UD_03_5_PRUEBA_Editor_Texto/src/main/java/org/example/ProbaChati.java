package org.example;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ProbaChati {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Menu with Icons Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            // Create menu bar
            JMenuBar menuBar = new JMenuBar();

            // Create "File" menu with an icon
            JMenu fileMenu = new JMenu("");
            fileMenu.setIcon(loadAndResiceIcon("/icons/night-light.png",16,16));



            // Add "File" menu to the menu bar
            menuBar.add(fileMenu);

            // Set the menu bar on the frame
            frame.setJMenuBar(menuBar);

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }


    private static ImageIcon loadAndResiceIcon(String path, int width, int height){
        URL imgUrl=ProbaChati.class.getResource(path); //pillase a imagen
        if (imgUrl != null){
            ImageIcon imageIcon=new ImageIcon(imgUrl);
            Image scaledImage = imageIcon.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        }else {
            System.out.println("Erro ao obter o icono: "+ path);
            return null;
        }
    }
}