package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        //1. Create the frame.
        JFrame frame = new JFrame("Ventana básica");
        

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);

        //2. Show it.
        frame.setVisible(true);
    }
}