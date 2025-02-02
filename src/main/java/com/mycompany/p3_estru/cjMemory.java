/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.p3_estru;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author marin
 */
public class cjMemory extends Casilla{
    JLabel matriz[][];
    JLabel hidden[][];
    Jugador jugador;
    Jugador contrincante;
    Jugador actual;
    int paresJugador;
    int paresContrincante;
    JLabel fliped;
    JLabel jugadorCont;
    JLabel contrincanteCont;
   
    JF_Juego pantalla;

    public cjMemory(int ID) {
        super(ID, "Memory", TipoCasilla.JUEGO, "src/main/java/tiles/memoria.png");
        matriz = new JLabel [3][6];
        hidden = new JLabel [3][6];
        jugador = null;
        contrincante =null;
        paresJugador = 0;
        paresContrincante = 0;
        fliped = null;
        pantalla = null;
        jugadorCont = new JLabel();
        contrincanteCont = new JLabel();
        jugadorCont.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 80));
        jugadorCont.setSize(100,100);
        jugadorCont.setLocation(40,75);
        contrincanteCont.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 80));
        contrincanteCont.setSize(100,100);
        contrincanteCont.setLocation(850,75);  
    }
    
    public void llenar(){
        for (int i = 0; i < 9; i++) {
            ImageIcon icono = getImagen(i);
            Random rand = new Random();
            JLabel label = new JLabel();
            label.setIcon(icono);
            label.setSize(150,150);
            
            int uno = rand.nextInt(3);
            int dos = rand.nextInt(6);
            //para la primera carta
            while(hidden[uno][dos]!=null){
                uno = rand.nextInt(3);
                dos = rand.nextInt(6);
            }
            
            hidden[uno][dos] = label;
            //para la segunda carta
            while(hidden[uno][dos]!=null){
                uno = rand.nextInt(3);
                dos = rand.nextInt(6);
            }
            hidden[uno][dos] = label;
        }
        
    }
    
    public ImageIcon getImagen(int valor){
        String nueva = "";
        switch (valor) {
            case 0 -> nueva = "src/main/java/tiles/coins.png";
            case 1 -> nueva = "src/main/java/tiles/cards.png";
            case 2 -> nueva = "src/main/java/tiles/bomb.png";
            case 3 -> nueva = "src/main/java/tiles/memoria.png";
            case 4 -> nueva = "src/main/java/tiles/memoria2.png";
            case 5 -> nueva = "src/main/java/tiles/who.png";
            case 6 -> nueva = "src/main/java/tiles/star.png";
            case 7 -> nueva = "src/main/java/tiles/gato.png";
            case 8 -> nueva = "src/main/java/tiles/fuego.png";
            case 9 -> nueva = "src/main/java/tiles/cola.png";
          }
        ImageIcon icon1 = new ImageIcon(nueva);
        Image image1 = icon1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH ); 
        return new ImageIcon(image1,icon1.getDescription());
    }
    
    @Override
    public void resetear(){
        matriz = new JLabel [3][6];
        hidden = new JLabel [3][6];
        jugador = null;
        contrincante =null;
        paresJugador = 0;
        paresContrincante = 0;
        fliped = null;
        pantalla = null;
        jugadorCont = new JLabel();
        contrincanteCont = new JLabel();
        jugadorCont.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 80));
        jugadorCont.setSize(100,100);
        jugadorCont.setLocation(40,75);
        contrincanteCont.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 80));
        contrincanteCont.setSize(100,100);
        contrincanteCont.setLocation(850,75); 
        
    }
    
    public void init(){
        this.revelarImagen();
        this.setVisitado(true);
        jugador.setGano(false);
        pantalla = new JF_Juego(jugador.getPantalla().getJuego(), jugador.getCasilla(), jugador);
        
        contrincante = jugador.getPantalla().getJuego().escogerContrincante(jugador);
        pantalla.stats2(jugador, contrincante);
        jugadorCont.setText(paresJugador+"");
        pantalla.lblImage.add(jugadorCont);
        contrincanteCont.setText(paresContrincante+"");
        pantalla.lblImage.add(contrincanteCont);
        
        llenar();
        MouseListener m1 = new MouseListener(){
                    @Override
                    public void mouseClicked(MouseEvent e) {
                       JLabel comp = (JLabel) e.getComponent();
                       comp.removeMouseListener(this);
                       revealImage(comp);
                        
                       if (fliped == null){
                           fliped = comp;
                       }
                        else{
                            if (fliped.getIcon().equals(comp.getIcon())){
                                parObtenido(fliped, comp);
                            }
                            else{
                                resetIcon(fliped, comp, this);
                                fliped = null;
                                cambiarActual();
                            }
                        }
                       pantalla.lblImage.revalidate();
                       pantalla.lblImage.repaint();
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
            for (int j = 0; j < 6; j++) {
                JLabel label = new JLabel();
                String fileName1 = "src/main/java/tiles/pregunta.png";
                ImageIcon icon1 = new ImageIcon(fileName1);
                Image image1 = icon1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH ); //TODO
                icon1 = new ImageIcon(image1,icon1.getDescription());
                label.setIcon(icon1);
                label.setSize(100,100);
                label.setLocation(100+(j*120), 200+ (i*120));
                label.addMouseListener(m1);
                matriz[i][j] = label;
                pantalla.lblImage.add(matriz[i][j]);
            }
        }
        pantalla.setVisible(true);
        pantalla.lblImage.revalidate();
        pantalla.lblImage.repaint();
    }
    
    @Override
    public void jugar(Jugador jugador){
        resetear();
        this.jugador = this.actual = jugador;
        init();
        
    }
    
    public void revealImage(JLabel label){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                if (matriz[i][j].equals(label)){
                    matriz[i][j].setIcon(hidden[i][j].getIcon());
                }
            }
            
        }
        
    }
    

    
    
    private void parObtenido(JLabel label, JLabel label2){
        String fileName1 = actual.getImagenPNG();
        ImageIcon icon1 = new ImageIcon(fileName1);
        Image image1 = icon1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH ); //TODO
        icon1 = new ImageIcon(image1,icon1.getDescription());
        label.setIcon(icon1);
        label2.setIcon(icon1);
        if (actual.equals(jugador)){
            paresJugador++;
            jugadorCont.setText(paresJugador+"");
        }
        else{
            paresContrincante++;
            contrincanteCont.setText(paresContrincante+"");
        }
        fliped = null;
    }
    
    private void resetIcon(JLabel label, JLabel label2, MouseListener m){
        String fileName1 = "src/main/java/tiles/pregunta.png";
        ImageIcon icon1 = new ImageIcon(fileName1);
        Image image1 = icon1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH ); //TODO
        icon1 = new ImageIcon(image1,icon1.getDescription());
        label.setIcon(icon1);
        label2.setIcon(icon1);
        label.addMouseListener(m);
        label2.addMouseListener(m);
    }
    
    @Override
    public boolean done(){
        if (paresJugador>4){
            jugador.setGano(true);
            pantalla.dispose();
            return true;
        }
        else if(paresContrincante>4){
            pantalla.dispose();
            return true;
        }
        else{
            return false;
        }
    }
    
     public void cambiarActual(){
        if(actual.equals(jugador)){
            actual = contrincante;
        }
        else{
            actual = jugador;
        }
    }
}
