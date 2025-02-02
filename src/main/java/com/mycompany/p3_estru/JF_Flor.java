/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.p3_estru;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author marin
 */
public class JF_Flor extends javax.swing.JFrame {
    JLabel lblImage;
    Juego juego;
    Casilla casilla;
    Jugador jugador;
    Jugador elegido;
   
    
    public JF_Flor(Juego juego, Casilla casilla, Jugador jugador) {
        initComponents();
        this.lblImage =  new JLabel();
        this.juego = juego;
        this.casilla = casilla;
        this.jugador = jugador;
        this.elegido = null;
        background("src/main/java/imagenes/back2.jpg");
        elegirPersonajes();
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
    
    public void elegirPersonajes(){
        MouseListener m1 = new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel comp = (JLabel) e.getComponent();
                for (int i = 0; i < juego.getJugadores().size(); i++) {
                    if (juego.getJugadores().get(i).getImagenPNG().equals(comp.getIcon().toString())){
                        elegido = juego.getJugadores().get(i);
                    }
                }
                accion();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}

        };
        
        
        
        for (int i = 0; i < juego.getJugadores().size(); i++) {
            JLabel icon = new JLabel();
            icon.setSize(100, 100);
            icon.setLocation(100+120*i,400);
            ImageIcon icone = new ImageIcon(juego.getJugadores().get(i).getImagenPNG());
            Image image = icone.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH ); 
            icone = new ImageIcon(image,icone.getDescription());
            icon.setIcon(icone);
            icon.addMouseListener(m1);
            JLabel nombre = new JLabel();
            nombre.setSize(100, 50);
            nombre.setLocation(110+120*i,320);
            nombre.setFont(new Font("Bahnscrift", Font.BOLD, 30));
            nombre.setForeground(Color.white);
            nombre.setText(juego.getJugadores().get(i).getID());
            
            lblImage.add(icon);
            lblImage.add(nombre); 
        }
        
        JLabel nombre = new JLabel();
        nombre.setSize(600, 100);
        nombre.setLocation(500,100);
        nombre.setFont(new Font("Bahnscrift", Font.BOLD, 40));
        nombre.setText("Elija a que jugador perjudicar");
        nombre.setForeground(Color.white);
        lblImage.add(nombre);
        
    }
    
    public void accion(){
        if(casilla.getNombre().equals("Fuego")){
            elegido.getCasilla().removeJugador(elegido);
            elegido.setCasilla(juego.buscarInicio());
            juego.getPantalla().personajeEnCasilla(elegido, elegido.getCasilla());
            casilla.setDone(true);
            this.dispose();
        }
        else{
            elegido.setTurnosPerdidos(2);
            casilla.setDone(true);
            this.dispose();
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pantalla = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout pantallaLayout = new javax.swing.GroupLayout(pantalla);
        pantalla.setLayout(pantallaLayout);
        pantallaLayout.setHorizontalGroup(
            pantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1289, Short.MAX_VALUE)
        );
        pantallaLayout.setVerticalGroup(
            pantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 611, Short.MAX_VALUE)
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
