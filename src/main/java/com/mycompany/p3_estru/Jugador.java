/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.p3_estru;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author marin
 */
public class Jugador extends Thread{
    private String ID;
    private String imagen;
    private String imagenPNG;
    private String imagenDado;
    private int inicio;
    private int negInicio;
    private int espacios;
    private boolean gano;
    private int turnosPerdidos = 0;
    private Casilla casilla;
    private ArrayList<Casilla> visitadas;
    private MouseListener m1;
    private MouseListener m2;
    private JF_Grafo pantalla;
    private boolean movido;
    private boolean tiraDados;
    
    
    public Jugador(String ID, String imagen, String imagenPNG, String imagenDado){
        this.ID = ID;
        this.imagen = imagen;
        this.inicio = 0;
        this.negInicio = 0;
        this.espacios = 0;
        this.gano = false;
        this.casilla = null;
        this.visitadas = new ArrayList<>();
        this.imagenPNG = imagenPNG;
        this.imagenDado = imagenDado;
        this.tiraDados = false;
        this.pantalla = null;
        this.movido = false;
        
    }
    
    // STUFF
    
    private void createMl(){
        m1 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel label = (JLabel) e.getComponent();
                Casilla nueva = pantalla.juego.buscarCasilla(label);
                Arista camino = casilla.encontrarArista(nueva);
                if(camino.getPeso() <= espacios){
                    espacios -= camino.getPeso();
                    for(Arista ari : casilla.getAristas()){
                        ari.getCasilla().getLabel().removeMouseListener(this);
                        ari.getCasilla().getLabel().setBorder(null);
                    }
                    pantalla.iconEnCasilla(casilla);
                    casilla = nueva;
                    movido = true;
                }
                else{
                    JOptionPane.showMessageDialog(pantalla, "No tiene suficientes espacios", "Error", 2);
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
    }
    
    private void createM2(){
        m2 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel label = (JLabel) e.getComponent();
                Casilla nueva = pantalla.juego.buscarCasilla(label);
                for (Casilla casilla : pantalla.juego.getGrafo()){
                    casilla.getLabel().removeMouseListener(this);
                    casilla.getLabel().setBorder(null);
                }
                if (!casilla.visitado){
                    casilla.setVisitado(true);
                    casilla.revelarImagen();
                }
                pantalla.iconEnCasilla(casilla);
                
                casilla = nueva;
                movido = true;
                
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
    }
    
    public void moverse(JF_Grafo pantalla){
        this.movido = false;
        this.pantalla = pantalla;
        createMl();
        init();
    }
    
    public void moverACualquier(){
        espacios = 10;
        createM2();
        casilla.removeJugador(this);
        casilla.getLabel().revalidate();
        casilla.getLabel().repaint();
        boolean movible = false;
        try {
            for (Casilla casilla : pantalla.juego.getGrafo()){
                    movible = true;
                    if(!casilla.getNombre().equals("Final")){
                    casilla.getLabel().addMouseListener(m2);
                    casilla.getLabel().setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.green));
            }}
            if(movible == false){
                movido = true;
            }
            while(!movido){
                Juego.sleep(1000);
            }
            
        } catch (InterruptedException ex){}
        
        
        
    }
    
    public boolean movible(){
        for (Arista camino : casilla.getAristas()){
                if (camino.getPeso()<= espacios){
                    return true;
                }
            }
        return false;
    }
    
    public void init(){
        casilla.removeJugador(this);
        boolean movible = false;
        try {
            for (Arista camino : casilla.getAristas()){
                if (camino.getPeso()<= espacios){
                    movible = true;
                    camino.getCasilla().getLabel().addMouseListener(m1);
                    camino.getCasilla().getLabel().setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.green));
                }
            }
            if(movible == false){
                movido = true;
            }
            while(!movido){
                Juego.sleep(1000);
            }
            
        } catch (InterruptedException ex){}
    }
    
    //GETTER Y SETTER

    public boolean getGano() {
        return gano;
    }

    public void setGano(boolean gano) {
        this.gano = gano;
    }

    public void setPantalla(JF_Grafo pantalla) {
        this.pantalla = pantalla;
    }
    
    

    public JF_Grafo getPantalla() {
        return pantalla;
    }

    public void setTiraDados(boolean tiraDados) {
        this.tiraDados = tiraDados;
    }

    public boolean isTiraDados() {
        return tiraDados;
    }
    
    
    
    public void addCasilla(Casilla casilla){
        this.visitadas.add(casilla);
    }
    
    public Casilla getCasilla() {
        return casilla;
    }

    public void setCasilla(Casilla casilla) {
        this.casilla = casilla;
    }

    public String getImagenDado() {
        return imagenDado;
    }

    public String getImagenPNG() {
        return imagenPNG;
    }

    public int getNegInicio() {
        return negInicio;
    }

    public void setNegInicio(int negInicio) {
        this.negInicio = negInicio;
    }
    
    
    public String getImagen() {
        return imagen;
    }

    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getEspacios() {
        return espacios;
    }

    public void setEspacios(int espacios) {
        this.espacios = espacios;
    }

    public int getTurnosPerdidos() {
        return turnosPerdidos;
    }

    public void setTurnosPerdidos(int turnosPerdidos) {
        this.turnosPerdidos = turnosPerdidos;
    }

    public String getID() {
        return ID;
    }
    
   
    
}
