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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author marin
 */
public class cjCoins extends Casilla{
    JLabel matriz[][];
    int matrizM[][];
    int total;
    JLabel monTotales;
    JLabel monActual;
    JLabel timer;
    Jugador player;
    JF_Juego pantalla;
    MouseListener m1;
    int time;
    boolean done;
    
    public cjCoins(int ID) {
        super(ID, "Collect the coins", TipoCasilla.JUEGO, "src/main/java/tiles/coins.png");
        resetear();
    }
    
    @Override
    public void resetear(){
        done = false;
        matriz = new JLabel[25][25];
        matrizM = new int[25][25];
        total = 0;
        player = null;
        pantalla= null;
        m1 = null;
        
        monActual= new JLabel();
        monActual.setSize(300,50);
        monActual.setFont(new Font("Bahnscrift", Font.PLAIN, 30));
        monActual.setLocation(900,300);
        
        monTotales= new JLabel();
        monTotales.setSize(300,50);
        monTotales.setFont(new Font("Bahnscrift", Font.PLAIN, 30));
        monTotales.setLocation(900,200);
        monTotales.setText("Monedas: "+total);
        
        timer= new JLabel();
        timer.setSize(200,50);
        timer.setFont(new Font("Bahnscrift", Font.BOLD, 30));
        timer.setLocation(970,0);
        time= 0;
        time();
        timer.setText("Tiempo: "+time);
        crearMonedas();
    }
    
    @Override
    public boolean done(){
        return done;
    }
    
    public void time(){
        Random r =new Random();
        int valor = r.nextInt(2);
        switch(valor){
            case 1 -> time = 60;
            case 2 -> time = 30;
            case 0 -> time = 45;
        }
    }
    
    @Override
    public void jugar(Jugador jugador) {
        resetear();
        this.revelarImagen();
        this.setVisitado(true);
        jugador.setGano(false);
        
        Juego miJuego = jugador.getPantalla().getJuego();
        JF_Juego pantalla = new JF_Juego(miJuego, jugador.getCasilla(), jugador);
        
        
        this.pantalla = pantalla;
        this.player = jugador;
        pantalla.stats(jugador);
        init();
        this.empezar();
    }
   
    public void empezar(){
        pantalla.setVisible(true);
        
        pantalla.lblImage.revalidate();
        pantalla.lblImage.repaint();
        
        try {
            while(time>=0){
                timer.setText("Tiempo: "+time-- + " s");
                Juego.sleep(1000);
            }
        } catch (InterruptedException ex) {
        }
        removeML();
        done = true; 
        if (total>=0){
            JOptionPane.showMessageDialog(pantalla, "Has ganado Collect The Coins ", "COLLECT THE COINS", 2);
            player.setGano(true);
        }
        else{
            player.setGano(false);
            JOptionPane.showMessageDialog(pantalla, "Has perdido Collect The Coins :( ", "COLLECT THE COINS", 2);
        }
        pantalla.dispose();
    }
    
    public void crearMonedas(){
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                Random r = new Random();
                int valor = r.nextInt(4);
                int moneda = 0;
                switch (valor){
                    case 0 -> moneda = -10;
                    case 1 -> moneda = 10;
                    case 2 -> moneda = 1;
                    case 3 -> moneda = -1;
                }
                matrizM[i][j] = moneda;
            }
        }
        matrizM[0][0] = 10000;
    }
    
    public void init(){
        pantalla.lblImage.revalidate();
        pantalla.lblImage.repaint();
        pantalla.lblImage.add(monTotales);
        pantalla.lblImage.add(timer);
        pantalla.lblImage.add(monActual);
        this.m1 = new MouseListener(){
                    @Override
                    public void mouseClicked(MouseEvent e) {
                       JLabel comp = (JLabel) e.getComponent();
                       int coin = getCoin(comp);
                       total+= coin;
                       monTotales.setText("Monedas: "+total);
                       monActual.setText(coin+"");
                       comp.removeMouseListener(m1);
                       comp.setIcon(null);
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
        
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                JLabel label = new JLabel();
                label.setSize(26,26);
                label.setLocation(100+ (i*28), 80+(j*28));
                label.addMouseListener(m1);
                
                String fileName = "src/main/java/imagenes/coin.png";
                ImageIcon icon = new ImageIcon(fileName);
                Image image = icon.getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH ); 
                icon = new ImageIcon(image,icon.getDescription());
                label.setIcon(icon);

                matriz[i][j] = label;
                pantalla.lblImage.add(matriz[i][j]);
            }
        }
        
        pantalla.lblImage.revalidate();
        pantalla.lblImage.repaint();
    }
    
    public int getCoin(JLabel label){
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                if (matriz[i][j].equals(label)){
                    return matrizM[i][j];
                }
            }
        }
        return 0;
    }
    
    public void removeML(){
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                matriz[i][j].removeMouseListener(m1);
            }
        }
    }
    
}
