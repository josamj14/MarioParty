/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.p3_estru;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


enum TipoBomba{
    SINGLE("src/main/java/imagenes/single.png"),
    DOUBLE("src/main/java/imagenes/doble.png"),
    CROSS("src/main/java/imagenes/cruz.png"),
    LINE("src/main/java/imagenes/line.png");
    
    String imagen;
    
    private TipoBomba(String imagen){
    this.imagen = imagen;
}
}

public class cjBombermario extends Casilla {
    JLabel matriz[][];
    Jugador player;
    JF_Juego pantalla;
    int espacios;
    int xTesoro;
    int yTesoro;
    TipoBomba bombaActual;
    int cantBombas;
    MouseListener m1;
    int cantTesoro;
    
    public cjBombermario(int ID) {
        super(ID, "Bombermario", TipoCasilla.JUEGO, "src/main/java/tiles/bomb.png");
        resetear();
    }
    
    @Override
    public void resetear(){
        this.espacios = 0;
        this.xTesoro  = 0;
        this.yTesoro =0;
        this.cantTesoro = 4;
        this.cantBombas = 7;
        Random rand = new Random();
        int aleatorio = rand.nextInt(3);
        if(aleatorio==0){
            this.espacios = 10;
        }
        else if (aleatorio==1){
            this.espacios= 15;
        }
        else{
            this.espacios = 20;
        }
        
        matriz = new JLabel[espacios][espacios];
        player = null;
        pantalla = null;
        
    }
    
    @Override
    public boolean done(){
        if(cantTesoro<1){
            pantalla.dispose();
            player.setGano(true);
            JOptionPane.showMessageDialog(pantalla, "Has ganado :) ", "BOMBERMARIO", 2);
            return true;
        }
        else if (cantBombas==0){
            pantalla.dispose();
            player.setGano(false);
            JOptionPane.showMessageDialog(pantalla, "Has perdido :( ", "BOMBERMARIO", 2);
            return true;
        }
        else{
            return false;
        }
    }
    
    @Override
    public void jugar(Jugador jugador){
        resetear();
        this.revelarImagen();
        this.setVisitado(true);
        jugador.setGano(false);
        
        Juego miJuego = jugador.getPantalla().getJuego();
        JF_Juego pantalla = new JF_Juego(miJuego, jugador.getCasilla(), jugador);
        
        this.player = jugador;
        pantalla.stats(jugador);
        init(pantalla); 
        crearBombas();
        agregarTesoro();
    }
    
    public void init(JF_Juego pantalla){
        this.pantalla = pantalla;
        pantalla.lblImage.revalidate();
        pantalla.lblImage.repaint();
        this.m1 = new MouseListener(){
                    @Override
                    public void mouseClicked(MouseEvent e) {
                       JLabel comp = (JLabel) e.getComponent();
                       if (!done() && cantBombas>0){
                           atacar(comp);
                           cantBombas--;
                       }
                       else{
                            player.setGano(false);
                            JOptionPane.showMessageDialog(pantalla, "Has perdido bombermario :( ", "BOMBERMARIO", 2);
                            pantalla.dispose();
                        
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
        
        for (int i = 0; i < espacios; i++) {
            for (int j = 0; j < espacios; j++) {
                JLabel label = new JLabel();
                label.setSize(30,30);
                label.setLocation(100+ (i*32), 100+(j*32 ));
                label.addMouseListener(m1);
                
                String fileName = "src/main/java/tiles/ladrillo.png";
                ImageIcon icon = new ImageIcon(fileName);
                Image image = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH ); 
                icon = new ImageIcon(image,icon.getDescription());
                label.setIcon(icon);
                
                label.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.white));
                matriz[i][j] = label;
                pantalla.lblImage.add(matriz[i][j]);
            }
        }
        
        pantalla.lblImage.revalidate();
        pantalla.lblImage.repaint();
        pantalla.setVisible(true);
    }
    
    public void agregarTesoro(){
        Random rand = new Random();
        xTesoro = rand.nextInt(espacios-1);
        yTesoro = rand.nextInt(espacios-1);
        System.out.println("El tesoro esta en : " + xTesoro + " " + yTesoro);
    }
    
    public boolean atacar(JLabel label){
        for (int i = 0; i < espacios; i++) {
            for (int j = 0; j < espacios; j++) {
                if (matriz[i][j].equals(label)){
                    if(bombaActual!=null){
                        switch(bombaActual){
                            case SINGLE:
                                revealTesoro(label, i, j);
                                break;
                            case DOUBLE:
                                revealTesoro(label, i, j);
                                if(i>0 && j>0){
                                    revealTesoro(matriz[i-1][j],i-1,j);
                                    revealTesoro(matriz[i][j-1],i,j-1);
                                    revealTesoro(matriz[i-1][j-1],i-1,j-1);
                                }
                                else if (i<espacios-1 && j<espacios-1){
                                    revealTesoro(matriz[i+1][j],i+1,j);
                                    revealTesoro(matriz[i][j+1],i,j+1);
                                    revealTesoro(matriz[i+1][j+1],i+1,j+1);
                                }
                                else if (j>0 && i<espacios-1){
                                    revealTesoro(matriz[i][j-1],i,j-1);
                                    revealTesoro(matriz[i+1][j-1],i+1,j-1);
                                    revealTesoro(matriz[i+1][j],i+1,j);
                                }
                                else if (j<espacios-1 && i>0){
                                    revealTesoro(matriz[i][j+1],i,j+1);
                                    revealTesoro(matriz[i-1][j+1],i-1,j+1);
                                    revealTesoro(matriz[i-1][j],i-1,j);
                                }
                                break;
                            case CROSS:
                                revealTesoro(label, i, j);
                                if(i>0) revealTesoro(matriz[i-1][j],i-1,j);
                                if(j>0) revealTesoro(matriz[i][j-1],i,j-1);
                                if(i<espacios-1) revealTesoro(matriz[i+1][j], i+1,j);
                                if(j<espacios-1) revealTesoro(matriz[i][j+1],i,j+1);
                                break;
                            case LINE:
                                revealTesoro(label, i, j);
                                if(i>3){
                                    revealTesoro(matriz[i-1][j],i-1,j);
                                    revealTesoro(matriz[i-2][j],i-2,j);
                                    revealTesoro(matriz[i-3][j],i-3,j);
                                }
                                else if (i<espacios-3){
                                    revealTesoro(matriz[i+1][j],i+1,j);
                                    revealTesoro(matriz[i+2][j],i+2,j);
                                    revealTesoro(matriz[i+3][j],i+3,j);
                                }
                                else if (j<espacios-3){
                                    revealTesoro(matriz[i][j+1],i,j+1);
                                    revealTesoro(matriz[i][j+2],i,j+2);
                                    revealTesoro(matriz[i][j+3],i,j+3);
                                }
                                else if (j>3){
                                    revealTesoro(matriz[i][j-1],i,j-1);
                                    revealTesoro(matriz[i][j-2],i,j-2);
                                    revealTesoro(matriz[i][j-3],i,j-3);
                                }
                                break;
                        }
             
                    }
                }
            }
        }
        return false;
    }
    
    public boolean revealTesoro(JLabel label, int i, int j){
        label.removeMouseListener(m1);
        String fileName = "";
        
        if(i==xTesoro && j==yTesoro)  fileName = "src/main/java/imagenes/tesoro1.png";
        else if(i==xTesoro+1 && j==yTesoro) fileName = "src/main/java/imagenes/tesoro2.png";
        else if(i==xTesoro && j==yTesoro+1) fileName = "src/main/java/imagenes/tesoro3.png";
        else if(i==xTesoro+1 && j==yTesoro+1) fileName = "src/main/java/imagenes/tesoro4.png";
        else{
           label.setIcon(null);
           return false;
        }
        ImageIcon icon = new ImageIcon(fileName);
        Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH ); 
        icon = new ImageIcon(image,icon.getDescription());
        label.setIcon(icon);
        cantTesoro--;
        return true;
    }
    
    public void crearBombas(){
        for (int i = 0; i < 4; i++) {
            JButton btnBomba = new JButton();
            btnBomba.setSize(100,100);
            btnBomba.setLocation(1100, 100+150*i);
            btnBomba.setBackground(Color.cyan);
            String fileName = "";
            if(i==0){
                fileName= TipoBomba.SINGLE.imagen;
                btnBomba.setName(TipoBomba.SINGLE+"");
            }
            else if(i==1){
                fileName= TipoBomba.DOUBLE.imagen;
                btnBomba.setName(TipoBomba.DOUBLE+"");
            }
            else if(i==2){
                fileName= TipoBomba.CROSS.imagen;
                btnBomba.setName(TipoBomba.CROSS+"");
            }
            else{
                fileName= TipoBomba.LINE.imagen;
                btnBomba.setName(TipoBomba.LINE+"");
            }
            ImageIcon icon = new ImageIcon(fileName);
            Image image = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH ); 
            icon = new ImageIcon(image,icon.getDescription());
            btnBomba.setIcon(icon);
            btnBomba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cantBombas>0){
                JButton boton = (JButton) e.getSource();
                bombaActual = TipoBomba.valueOf(boton.getName());
                cantBombas--;
                }
               
            }
            });
            pantalla.add(btnBomba);
            }
    }
}
