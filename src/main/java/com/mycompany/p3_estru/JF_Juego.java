/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.p3_estru;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author marin
 */
public class JF_Juego extends javax.swing.JFrame {
    JLabel lblImage;
    Juego juego;
    Casilla casilla;
    Jugador jugador;
    
    JLabel imagenIcon = new JLabel();
    JLabel titulo2 = new JLabel();
    
    
    public JF_Juego(Juego juego, Casilla casilla, Jugador jugador) {
        initComponents();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.lblImage =  new JLabel();
        this.juego = juego;
        this.casilla = casilla;
        this.jugador = jugador;
        
    }

    public void background(String path){
        String fileName = path;
        ImageIcon icon = new ImageIcon(fileName);
        Image image = icon.getImage().getScaledInstance(1550, 880, Image.SCALE_SMOOTH ); //TODO
        icon = new ImageIcon(image,icon.getDescription());
        lblImage.setIcon(icon);
        lblImage.setSize(1550,880);
        lblImage.setLocation(0, 0);
        pantalla.add(lblImage);
    }
    
    public void stats(Jugador jugador){
        background("src/main/java/imagenes/background1.jpg");
        
        
        String fileName1 = jugador.getImagenPNG();
        ImageIcon icon1 = new ImageIcon(fileName1);
        Image image1 = icon1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH ); //TODO
        icon1 = new ImageIcon(image1,icon1.getDescription());
        imagenIcon.setIcon(icon1);
        imagenIcon.setSize(50,50);
        imagenIcon.setLocation(0, 0);
        lblImage.add(imagenIcon);
        
       
        titulo2.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 80));
        titulo2.setSize(500,100);
        titulo2.setText(jugador.getID());
        
        titulo2.setLocation(60, 0);
        lblImage.add(titulo2);
        
        lblImage.revalidate();
        lblImage.repaint();
    }
    
    public void stats2(Jugador jugador, Jugador contrincante){
        background("src/main/java/imagenes/background1.jpg");
        
        JLabel imagenIcon = new JLabel();
        String fileName = jugador.getImagenPNG();
        ImageIcon icon = new ImageIcon(fileName);
        Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH ); //TODO
        icon = new ImageIcon(image,icon.getDescription());
        imagenIcon.setIcon(icon);
        imagenIcon.setSize(50,50);
        imagenIcon.setLocation(0, 0);
        lblImage.add(imagenIcon);
        
        JLabel titulo = new JLabel();
        titulo.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 45));
        titulo.setSize(500,50);
        titulo.setText(jugador.getID());
        
        titulo.setLocation(60, 0);
        lblImage.add(titulo);
        
        JLabel imagenIcon2 = new JLabel();
        String fileName1 = contrincante.getImagenPNG();
        ImageIcon icon1 = new ImageIcon(fileName1);
        Image image1 = icon1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH ); //TODO
        icon1 = new ImageIcon(image1,icon1.getDescription());
        imagenIcon2.setIcon(icon1);
        imagenIcon2.setSize(50,50);
        imagenIcon2.setLocation(800, 0);
        lblImage.add(imagenIcon2);
        
        JLabel titulo2 = new JLabel();
        titulo2.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 45));
        titulo2.setSize(500,50);
        titulo2.setText(contrincante.getID());
        
        titulo2.setLocation(850, 0);
        lblImage.add(titulo2);
        
        
        
        lblImage.revalidate();
        lblImage.repaint();
    }
    
    public void clear(){
        lblImage.removeAll();{
        lblImage.revalidate();
        lblImage.repaint();
    }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pantalla = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1439, 642));

        javax.swing.GroupLayout pantallaLayout = new javax.swing.GroupLayout(pantalla);
        pantalla.setLayout(pantallaLayout);
        pantallaLayout.setHorizontalGroup(
            pantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1460, Short.MAX_VALUE)
        );
        pantallaLayout.setVerticalGroup(
            pantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 643, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pantalla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pantalla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pantalla;
    // End of variables declaration//GEN-END:variables
}
