/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.p3_estru;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author marin
 */
public class cjCatchTheCat extends Casilla {
    JLabel matriz[][];
    Jugador player;
    JF_Juego pantalla;
    JLabel gato;
    boolean done;

    public cjCatchTheCat(int ID) {
        super(ID, "Catch the cat", TipoCasilla.JUEGO, "src/main/java/tiles/gato.png");
        resetear();
    }
    
    @Override
    public boolean done(){
        return done;
    }
    
    @Override
    public void jugar(Jugador jugador) {
        resetear();
        this.revelarImagen();
        this.setVisitado(true);
        jugador.setGano(false);
        
        Juego miJuego = jugador.getPantalla().getJuego();
        JF_Juego pantalla = new JF_Juego(miJuego, jugador.getCasilla(), jugador);
        pantalla.setVisible(true);
        this.player = jugador;
        pantalla.stats(jugador);
        init(pantalla);  
    }
    
    @Override
    public void resetear(){
        matriz = new JLabel[11][11];
        player = null;
        pantalla = null;
        gato = null;
        done= false;
    }
    
    public void init(JF_Juego pantalla){
        this.pantalla = pantalla;
        
        pantalla.setSize(pantalla.getWidth(), pantalla.getHeight()+200);
        pantalla.lblImage.revalidate();
        pantalla.lblImage.repaint();
        MouseListener m1 = new MouseListener(){
                    @Override
                    public void mouseClicked(MouseEvent e) {
                       JLabel comp = (JLabel) e.getComponent();
                       if (!comp.equals(gato)){
                            comp.removeMouseListener(this);
                            String fileName = "src/main/java/tiles/ladrillo.png";
                            ImageIcon icon = new ImageIcon(fileName);
                            Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH ); 
                            icon = new ImageIcon(image,icon.getDescription());
                            comp.setIcon(icon);
                            moverGato(); 
                       }
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
        
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                JLabel label = new JLabel();
                label.setSize(50,50);
                label.setLocation(100+ (i*60), 100+(j*60 ));
                label.addMouseListener(m1);
                
                String fileName = "src/main/java/tiles/purple.png";
                ImageIcon icon = new ImageIcon(fileName);
                Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH ); 
                icon = new ImageIcon(image,icon.getDescription());
                label.setIcon(icon);
                
                label.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.white));
                matriz[i][j] = label;
                pantalla.lblImage.add(matriz[i][j]);
            }
        }
        
        
        String fileName1 = "src/main/java/tiles/gato.png";
        ImageIcon icon1 = new ImageIcon(fileName1);
        Image image1 = icon1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH ); 
        icon1 = new ImageIcon(image1,icon1.getDescription());
        matriz[5][5].setIcon(icon1);
        
        gato = matriz[5][5];
        
        pantalla.lblImage.revalidate();
        pantalla.lblImage.repaint();
        pantalla.revalidate();
        pantalla.repaint();
    }
    
    public boolean moverGato(){
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (matriz[i][j].equals(gato)){
                    String fileName = "src/main/java/tiles/purple.png";
                    ImageIcon icon = new ImageIcon(fileName);
                    Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH ); 
                    icon = new ImageIcon(image,icon.getDescription());
                   
                    if(i<10 && movible(matriz[i+1][j])){ // abajo
                        matriz[i+1][j].setIcon(matriz[i][j].getIcon());
                        gato = matriz[i+1][j];
                    }
                    else if(i>0 && movible(matriz[i-1][j])){ // arriba
                        matriz[i-1][j].setIcon(matriz[i][j].getIcon());
                        gato = matriz[i-1][j];
                    }
                    else if(j>0 && movible(matriz[i][j-1])){ // izquierda
                        matriz[i][j-1].setIcon(matriz[i][j].getIcon());
                        gato = matriz[i][j-1];
                    }
                    else if(10>j && movible(matriz[i][j+1])){ // derecha
                        matriz[i][j+1].setIcon(matriz[i][j].getIcon());   
                        gato = matriz[i][j+1];
                    }
                    else{
                        player.setGano(true);
                        done = true;
                        JOptionPane.showMessageDialog(pantalla, "Has completado catch the cat! ", "CATCH THE CAT", 2);
                        pantalla.dispose();
                        return false;
                    }
                    matriz[i][j].setIcon(icon);
                    return false;
                }
            }
        }
        return false;
    }
    
    public boolean movible(JLabel label){
        if (label!=null && label.getIcon().toString().equals("src/main/java/tiles/purple.png")){
            return true;
        }
        return false;
    }
    
   
}
