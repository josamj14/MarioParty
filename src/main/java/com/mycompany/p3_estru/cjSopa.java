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
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author marin
 */
public class cjSopa extends Casilla {
    ArrayList<String> texto;
    ArrayList<String> palabras;
    ArrayList<String> palabrasObtenidas;
    String matriz[][];
    JLabel matrizLbl[][];
    int tamano;
    String abecedario = "abcdeieofevaigaohiioejoikelmneovoiaipqiearastuvwxyz";
    JF_Juego pantalla;
    Jugador player;
    MouseListener m1;
    String palabraActual;
    boolean done;
    
    public cjSopa(int ID) {
        super(ID, "Sopa de Letras", TipoCasilla.JUEGO, "src/main/java/tiles/sopa.png");
        resetear();
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
        
        this.pantalla = pantalla;
        this.player = jugador;
        pantalla.stats(jugador);
        init();  
    }
    
    public void init(){
        this.m1 = new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
               JLabel comp = (JLabel) e.getComponent();
               comp.setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.cyan));
               comp.setBackground(Color.cyan);
               palabraActual = palabraActual.concat(comp.getText());
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
        
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                JLabel label = new JLabel();
                label.setSize(30,30);
                label.setLocation(100+ (i*31), 100+(j*31));
                label.addMouseListener(m1);
                label.setText(matriz[i][j]);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
                matrizLbl[i][j] = label;
                pantalla.lblImage.add(matrizLbl[i][j]);
            }
        }
        
        for (int j = 0; j < palabras.size(); j++) {
            JLabel label = new JLabel();
            label.setSize(150,30);
            label.setLocation(800, 100+(j*31));
            label.setText(palabras.get(j));
            
            label.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
            pantalla.lblImage.add(label);
            }
        
        
        
        JButton boton = new JButton();
        boton.setSize(150,25);
        boton.setText("Nueva Palabra");
        boton.setFont(new Font("Bahnscrift", Font.PLAIN, 15));
        boton.setBackground(Color.cyan);
        boton.setLocation(1000,100);
        boton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(gano()){
                player.setGano(true);
                done = true;
            }
            
            JButton elBoton = (JButton) e.getSource();
            palabrasObtenidas.add(palabraActual);
            System.out.println("Obtuvo " + palabraActual);
            palabraActual = ""; 
                
            
            
        }
        });
        pantalla.lblImage.add(boton);
        
        pantalla.lblImage.revalidate();
        pantalla.lblImage.repaint();
        pantalla.setVisible(true);
        run();
    }
    
    @Override
    public void run(){
        JLabel label = new JLabel();
        label.setSize(150,30);
        label.setLocation(1000,550);
        pantalla.lblImage.add(label);
        int time = 120;
        while (time>0 && !gano()){
            try {
                label.setText(time + " segundos");
                sleep(1000);
                time--;
            } catch (InterruptedException ex) {
                Logger.getLogger(cjSopa.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        if(gano()){
            JOptionPane.showMessageDialog(pantalla, "Has ganado Sopa de Letras :) ", "SOPA DE LETRAS", 2);
            player.setGano(true);
        }
        else{
            JOptionPane.showMessageDialog(pantalla, "Has perdido :( ", "SOPA DE LETRAS", 2);
            player.setGano(false);
        }
        done = true;
        pantalla.dispose();
    }
    
    public boolean gano(){
        int estan = 0;
        for(String palabra : palabrasObtenidas){
            boolean esta = false;
            for(String str : palabras){
                if (str.equalsIgnoreCase(palabra)){
                    esta=true;
                }
            }
            if (esta) estan++;
        }
        if(estan==4)return true;
        return false;
    }
    
    @Override
    public boolean done(){
       
        return done;
    }
    
    @Override
    public void resetear(){
        texto = new ArrayList();
        palabras = new ArrayList();
        palabrasObtenidas = new ArrayList();
        pantalla=null;
        player = null;
        palabraActual = "";
        done = false;
        crearTexto();
        
        Random r = new Random();
        int valor = r.nextInt(3);
        switch (valor){
            case 0 -> {tamano = 10;}
            case 1 -> {tamano = 20;}
            case 2 -> {tamano = 15;}
        }
        matriz = new String[tamano][tamano];
        matrizLbl = new JLabel[tamano][tamano];
        crearPalabras();
    }
    
    public void crearTexto(){
        String str = "MARIO LUIGI YOSHI PEACH PRINCE DAISY NINTENDO PROGRA BROS BOWSER TOAD WORLD TIME ROSAS DONKEY KONG BIRDO PAULINE DIDDY BOWSERJR WARIO WALUIGI KINGBOO BOMBAS GOOMBA KOOPA TROOPA SHYGUY COLITA SUPER PARTY KARTS FANTASMA DIJAKSTRA ENEMIGOS JUGAR GAMING REVISION ALGORITMO AZULADO DIAGRAMA YOYO ELEGIR EVERY GIVENUP WINNING STOPPING DEFENSES FALLING HEART ATTACK MEASURE TROUBLE CARING DOLLS KINGDOM BASKET VOLLEY SWIM SCORE ARCHIVO LISTA ALAS PERRO TURNO NUMERO ENTRAR JUGADOR GANAR JUEGO PERDER GASTAR PUNTOS CAMINO AMIGO REPETIR VERTICE ARISTA DADOS INICIO ACERA EMPATE ORDEN LANZAR COSTA RICA MUNDO NACION TIERRA FUEGO AMIGO ROSADO FICHA ABIERTO CERRADO";
        int espacio = 0;
        while (espacio<str.lastIndexOf(" ")){
            int finale = str.indexOf(" ", espacio);
            texto.add(str.substring(espacio, finale));
            espacio = finale+1;
        }
    }
    
    public void crearPalabras(){
       Random r = new Random();
       int x1 = r.nextInt(tamano);
       int y1 = r.nextInt(tamano);
       // PALABRA 1 HORIZONTAL
       String palabra = texto.get(r.nextInt(texto.size()));
       palabras.add(palabra);
       while (x1+palabra.length() > tamano){
           x1 = r.nextInt(tamano);
       }
       System.out.println(palabra + " esta en (" + x1 + " , " + y1 + ")");
       for (int i = 0; i < palabra.length(); i++) {
           matriz[x1+i][y1] = palabra.substring(i, i+1);
       }
       
       // PALABRA 2 VERTICAL
       x1 = r.nextInt(tamano);
       y1 = r.nextInt(tamano);
       palabra = texto.get(r.nextInt(texto.size()));
       palabras.add(palabra);
       while (y1+palabra.length() >= tamano || ocupadoV(x1,y1,palabra.length()) ){
           y1 = r.nextInt(tamano);
       }
       System.out.println(palabra + " esta en (" + x1 + " , " + y1+ ")");
       for (int i = 0; i < palabra.length(); i++) {
           matriz[x1][y1+i] = palabra.substring(i, i+1);
       }
       
       // PALABRA 3 y 4 HORIZONTAL
        for (int k = 0; k < 2; k++) {
            x1 = r.nextInt(tamano);
            y1 = r.nextInt(tamano);
            palabra = texto.get(r.nextInt(texto.size()));
            
            while (y1+palabra.length() >= tamano || x1+palabra.length()>=tamano || ocupadoH(x1,y1,palabra.length())){
                y1 = r.nextInt(tamano);
                x1 = r.nextInt(tamano);
                palabra = texto.get(r.nextInt(texto.size()));
            }
            palabras.add(palabra);
            System.out.println(palabra + " esta en (" + x1 + " , " + y1 + ")");
            for (int i = 0; i < palabra.length(); i++){
                matriz[x1+i][y1+i] = palabra.substring(i, i+1);
            }
        }
        //LLENAR DE LETRAS RANDOM
         for (int i = 0; i < tamano; i++) {
             for (int j = 0; j < tamano; j++) {
                 if (matriz[i][j]==null){
                     int random = r.nextInt(abecedario.length());
                     matriz[i][j] = abecedario.substring(random,random+1);
                 }
             }
        }
       
        
        
    }
    
    public boolean ocupadoV(int x, int y, int tamano){
        for (int i = 0; i < tamano; i++) {
            if(matriz[x][y+i]!=null){
                return true;
            }
        }
        return false;
    }
    
    public boolean ocupadoH(int x, int y, int tamano){
        for (int i = 0; i < tamano; i++) {
            if(matriz[x+i][y+i]!=null)
                return true;
        }
        return false;
    }
    
}
