/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.p3_estru;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author marin
 */
public class cjMemoryPath extends Casilla{
    private boolean matrizBool[][];
    private JLabel matrizLbl[][];
    Jugador jugador;
    JF_Juego pantalla;
    int linea;
    int intentos;
    boolean done;
    

    public cjMemoryPath(int ID) {
        super(ID, "Memory Path", TipoCasilla.JUEGO, "src/main/java/tiles/memoria2.png" );
        this.matrizBool = new boolean [6][3];
        this.matrizLbl = new JLabel [6][3];
        this.linea = 0;
        this.intentos = 3;
        this.done = false; 
        this.jugador = null;
        this.pantalla= null;
    }
   
    @Override
    public void jugar(Jugador player){
        resetear();
        this.setVisitado(true);
        this.revelarImagen();        
        this.jugador = player;
        jugador.setGano(false);
        Juego miJuego = jugador.getPantalla().getJuego();
        JF_Juego pantalla = new JF_Juego(miJuego, jugador.getCasilla(), jugador);
        pantalla.setVisible(true);
        this.pantalla = pantalla;
        
        init();        
    }
    
    @Override
    public void resetear(){
        this.jugador = null;
        this.pantalla= null;
        this.matrizBool = new boolean [6][3];
        this.matrizLbl = new JLabel [6][3];
        this.linea = 0;
        this.intentos = 3;
        this.done = false; 
    }
    
    @Override
    public boolean done(){
        return done;
    }
    
    public void init(){
        
        //construyo matriz de respuestas
        for (int i = 0; i < 6; i++) {
            Random rand = new Random();
            matrizBool[i][rand.nextInt(3)] = true;
        }
        
        //Mouse Lis
        MouseListener m1 = new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel comp = (JLabel) e.getComponent();
                removeMouseListener(this);
                if (buscarComp(comp)){ // si el label que tocó, es la casilla correcta
                    String fileName1 = jugador.getImagenPNG();
                    ImageIcon icon1 = new ImageIcon(fileName1);
                    Image image1 = icon1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH ); //TODO
                    icon1 = new ImageIcon(image1,icon1.getDescription());
                    comp.setIcon(icon1);
                    linea+=1;
                    addMouseListener(this,linea);}
                else{
                    JOptionPane.showMessageDialog(pantalla, "Has elegido incorrecta", "Memory path", 2);
                    if (intentos>0){
                        intentos--;
                        inicio(this);
                    }
                    else{
                        JOptionPane.showMessageDialog(pantalla, "Se han acabado los intentos :(", "Memory path", 2);
                        done = true;
                        pantalla.dispose();
                    }
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
        
        imprimir();
        inicio(m1);
       
    }
    
    public void imprimir(){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(matrizBool[i][j]+ "\t");
            }
            System.out.println("");
        }
    }
    
    public void inicio(MouseListener m){
        this.linea = 0;
        pantalla.clear();
        pantalla.stats(jugador);
        //construyo matriz para juego
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                JLabel label = new JLabel();
                String fileName1 = "";
                if (matrizBool[i][j]){
                     fileName1 = "src/main/java/tiles/star.png";}
                else{
                     fileName1 = "src/main/java/tiles/pregunta.png";
                }
                ImageIcon icon1 = new ImageIcon(fileName1);
                Image image1 = icon1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH ); //TODO
                icon1 = new ImageIcon(image1,icon1.getDescription());
                label.setIcon(icon1);
                
                label.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 100));
                label.setSize(100,100);
                label.setLocation(500+(j*100),(i*100) );
                
                matrizLbl[i][j] = label;
                pantalla.lblImage.add(matrizLbl[i][j]);
            } 
        }
        pantalla.lblImage.revalidate(); 
        pantalla.lblImage.repaint();
        
        addMouseListener(m, 0);
        
    }
    
    public boolean buscarComp(JLabel label){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                if(matrizLbl[i][j].equals(label)){
                    return matrizBool[i][j];
                }
            }   
        }
    return false;
    }
    
    
    public void addMouseListener(MouseListener m, int linea){
        if (linea>5){
            done = true;
            jugador.setGano(true);
            JOptionPane.showMessageDialog(pantalla, "Has completado memory path! ", "Memory path", 2);
            pantalla.dispose();
        }
        else{
            for (int i = 0; i < 3; i++) {
                matrizLbl[linea][i].addMouseListener(m);
        }}
    }
    
    public void removeMouseListener(MouseListener m){
        for (int j = 0; j < 6; j++) {
           for (int i = 0; i < 3; i++) {
                matrizLbl[j][i].removeMouseListener(m);
            } 
        }
        
    }
    
    
}
