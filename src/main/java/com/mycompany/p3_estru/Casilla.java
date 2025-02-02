/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.p3_estru;

import java.awt.Image;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author marin
 */

enum TipoCasilla{
    JUEGO,
    INICIO,
    FINAL,
    TUBO,
    CARCEL,
    ESTRELLA,
    FLOR_FUEGO,
    FLOR_HIELO,
    COLA,
    PRE
}

public class Casilla extends Thread {
    private int ID;
    private String nombre;
    private TipoCasilla tipo;
    private String imagen;
    public boolean visitado;
    private int peso;
    private ArrayList<Arista> aristas;
    private ArrayList<Jugador> jugadoresEnCasilla;
    private int x;
    private int y;
    private JLabel label;
    private boolean done;
    
    public Casilla(int ID, String nombre, TipoCasilla tipo, String imagen) {
        this.ID = ID;
        this.nombre = nombre;
        this.tipo = tipo;
        this.visitado = false;
        this.aristas = new ArrayList();
        this.jugadoresEnCasilla = new ArrayList();
        this.peso = -1;
        this.imagen = imagen;
        //graphics
        this.x =-1;
        this.y = -1;
        this.label = new JLabel();
        this.done = false;
    }
    
    public void jugar(Jugador jugador){
        jugador.setGano(true);
        
        revelarImagen();
        int day = 4;
        switch (tipo) {
          case INICIO:
                
                break;
          case FINAL:
            if (jugador.getEspacios() == 0){
                jugador.getPantalla().getJuego().ganar();
                jugador.getPantalla().ganador(jugador);
            }
            else{
                 JOptionPane.showMessageDialog(jugador.getPantalla(), "Jugador ha llegado al final pero tiene espacios acumulados :( ", "No gana", 2);
            }
            
            break;
          case TUBO:
            this.removeJugador(jugador);
            jugador.getPantalla().iconEnCasilla(this);
            Casilla sigTubo = jugador.getPantalla().juego.siguienteTubo(this);
            jugador.setCasilla(sigTubo);
            jugador.getPantalla().personajeEnCasilla(jugador, jugador.getCasilla());
            jugador.getCasilla().setVisitado(true);
            jugador.getCasilla().revelarImagen();
            jugador.getCasilla().done = true;
            
            break;
          case CARCEL:
            jugador.setTurnosPerdidos(2);
            
            break;
          case ESTRELLA:
            if(!visitado){
                done = true;
                visitado = true;  
                }
                done = true;
                break;
            
          case FLOR_FUEGO:
                if(!visitado){
                    visitado = true; 
                    done = false;
                    new JF_Flor(jugador.getPantalla().getJuego(),this,jugador).setVisible(true);
                    
                }
                else{
                    done = true;
                    JOptionPane.showMessageDialog(jugador.getPantalla(), "Flor de fuego ya fue usada ", "Nada", 2);
                }
                break;
          case FLOR_HIELO:
                if(!visitado){
                    visitado = true; 
                    done = false;
                    new JF_Flor(jugador.getPantalla().getJuego(),this,jugador).setVisible(true);
                   
                }
                else{
                    done = true;
                    JOptionPane.showMessageDialog(jugador.getPantalla(), "Flor de hielo ya fue usada ", "Nada", 2);
                }
                break;
          case COLA:
              if (!visitado){
              jugador.moverACualquier();}
              else{
                  done = true;
                  JOptionPane.showMessageDialog(jugador.getPantalla(), "Colita ya fue usada ", "Nada", 2);
              }
              
            break;
          case JUEGO:
              
              break;
        }
        visitado = true;
    }
    
    public boolean playerWon(){
        return true;
    }

    public boolean done(){
        if (tipo.equals(TipoCasilla.FLOR_FUEGO) || tipo.equals(TipoCasilla.FLOR_FUEGO) || tipo.equals(TipoCasilla.COLA)  ||  tipo.equals(TipoCasilla.JUEGO) )  {
            return done;
        }
        return true; 
    }

    public void setDone(boolean done) {
        this.done = done;
    }
    
    
    
    
    
    
    
    
    
    //GETTER AND SETTER
    
    
    public ArrayList<Jugador> getJugadoresEnCasilla() {
        return jugadoresEnCasilla;
    }
    
    public void addJugador(Jugador jugador){
        jugadoresEnCasilla.add(jugador);
    }
    
    public void removeJugador(Jugador jugador){
        jugadoresEnCasilla.remove(jugador);
    }
    
    public void start(Casilla anterior){
        setImagenLabel("src/main/java/imagenes/pregunta.png");
        this.nombre = anterior.getNombre();
        this.x = anterior.getX();
        this.y = anterior.getY();
        this.label.setLocation(x, y);
        this.aristas = anterior.getAristas();
        this.peso = anterior.getPeso();
    }

    
    public int getPeso() {
        return peso;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    
    
    public void setVisitado(boolean visitado){
        this.visitado = visitado;
    }
    
    public void setImagenLabel(String path){
        String fileName = path;
        ImageIcon icon = new ImageIcon(fileName);
        Image image = icon.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH ); //TODO
        icon = new ImageIcon(image,icon.getDescription());
        label.setIcon(icon);
        label.setSize(75,75);
    }

    public void revelarImagen(){
        setImagenLabel(imagen);
    }
    
    public void resetear(){}
    
    
    public ArrayList<Arista> getAristas() {
        return aristas;
    }
    
    public void agregarArista (Casilla arista, int peso){
        if (buscarArista(arista) == -1  && !arista.equals(this)){
            aristas.add(new Arista(arista, peso));}
    }
    
    public int buscarArista(Casilla v){
        for (int i = 0; i < aristas.size(); i++) {
            if (v.getNombre().equals(aristas.get(i).getCasilla().getNombre()))
                return i;
        }
        return -1;
    }
    
    public Arista encontrarArista(Casilla v){
        for (int i = 0; i < aristas.size(); i++) {
            if (v.getNombre().equals(aristas.get(i).getCasilla().getNombre()))
                return aristas.get(i);
        }
        return null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
    
    //IDK
    
    public void dados(Jugador jugador){
        int dado1 = tirarDado();
        int dado2 = tirarDado();
        int dado3 = tirarDado();
        if(dado1==6 || dado2==6 || dado3==6){
            if(dado1==6 && dado2==6 && dado3==6){
                jugador.setTurnosPerdidos(2);
            }
            else if(dado1==6 && dado2==6){
                jugador.setTurnosPerdidos(1);
            }
            else if (dado1==6 && dado3==6){
                jugador.setTurnosPerdidos(1);
            }
            else if (dado2==6 && dado3==6){
                jugador.setTurnosPerdidos(1);
            }
        }
        else{
            int espacios = 0;
            if(dado1<6)
                espacios+=dado1;
            if(dado2<6)
                espacios+=dado2;
            if(dado3<6)
                espacios+=dado3;
            jugador.setEspacios(espacios);
        }
    }
    
    private int tirarDado(){
        Random rand =  new Random();
        return rand.nextInt(1, 7);
    }
}
