/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.p3_estru;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author marin
 */
public class cjGato extends Casilla{
    JLabel matriz[][];
    int matrizNum[][];
    
    Jugador actual;
    Jugador player;
    Jugador contrincante;
    JF_Juego pantalla;
    
 

    public cjGato(int ID) {
        super(ID, "Gato", TipoCasilla.JUEGO, "src/main/java/tiles/gato2.png");
        matriz = new JLabel[3][3];
        matrizNum = new int[3][3];
        actual = null;
        player = null;
        contrincante = null;
        pantalla = null;
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
        
        contrincante = miJuego.escogerContrincante(jugador);
        actual = player = jugador;
        pantalla.stats2(jugador, contrincante);
        init(pantalla, jugador, contrincante);  
    }
    
    @Override
    public void resetear(){
        matriz = new JLabel[3][3];
        matrizNum = new int[3][3];
        actual = null;
        player = null;
        contrincante = null;
        pantalla = null;
    }
    
    public boolean win(int numero){
        for (int i = 0; i < 3; i++) {
            if(matrizNum[i][0]==numero && matrizNum[i][1]==numero && matrizNum[i][2]==numero)
                return true;
            else if (matrizNum[0][i]==numero && matrizNum[1][i]==numero && matrizNum[2][i]==numero)
                return true;
        }
        if(matrizNum[0][0]==numero && matrizNum[1][1]==numero && matrizNum[2][2]==numero)
            return true;
        if(matrizNum[0][2]==numero && matrizNum[1][1]==numero && matrizNum[2][0]==numero)
            return true;
        return false;
    }
    
    public boolean isfull(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrizNum[i][j]==0){
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public boolean done(){
        if(win(1)){
            player.setGano(true);
            pantalla.dispose();
            JOptionPane.showMessageDialog(pantalla, "Jugador " + player.getID() + " ha ganado :) ", "Gato", 2);
            return true; 
        }
        else if(win(2)){
            pantalla.dispose();
            JOptionPane.showMessageDialog(pantalla, "Jugador " + player.getID() + " ha perdido :( ", "Gato", 2);
            return true; 
        }
        else if(isfull()){
            pantalla.dispose();
            JOptionPane.showMessageDialog(pantalla, "Ha sido un empate", "Gato", 2);
            return true;
        }
        else{
            return false;  
        }
    }
    
    public void agregarPunto(JLabel btn){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (btn.equals(matriz[i][j])){
                    if (actual.equals(player)){
                        matrizNum[i][j] = 1;
                    }
                    else if (actual.equals(contrincante)){
                        matrizNum[i][j] = 2;
                    }     
                }
            }
        }
    }
    
    public void cambiarActual(){
        if(actual.equals(player)){
            actual = contrincante;
        }
        else{
            actual = player;
        }
    }
    
    public void init(JF_Juego panta, Jugador jugador, Jugador contrincante){
        this.pantalla = panta;
        pantalla.lblImage.revalidate();
        pantalla.lblImage.repaint();
        
        MouseListener m1 = new MouseListener(){
                    @Override
                    public void mouseClicked(MouseEvent e) {
                       JLabel comp = (JLabel) e.getComponent();
                       comp.removeMouseListener(this);
                    
                        String fileName1 = actual.getImagenPNG();
                        ImageIcon icon1 = new ImageIcon(fileName1);
                        Image image1 = icon1.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH ); //TODO
                        icon1 = new ImageIcon(image1,icon1.getDescription());
                        comp.setIcon(icon1);
                       
                       agregarPunto(comp);
                       cambiarActual();
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
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JLabel label = new JLabel();
                label.setText(" X ");
                label.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 100));
                label.setSize(150,150);
                label.setLocation(200+ (i*200), 100+(j*160));
                label.addMouseListener(m1);
                matriz[i][j] = label;
                pantalla.lblImage.add(matriz[i][j]);
            }
        }
        pantalla.lblImage.revalidate();
        pantalla.lblImage.repaint();
        
        
    }
    
}
