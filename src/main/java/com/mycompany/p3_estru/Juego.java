/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.p3_estru;

import java.awt.Font;
import static java.awt.Font.BOLD;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author marin
 */
public class Juego extends Thread{
    private ArrayList<Jugador> jugadores;
    private ArrayList<Casilla> grafo;
    private JF_Grafo pantalla;
    private boolean gano;
    

    public Juego() {
        this.jugadores = new ArrayList<>();
        this.grafo = new ArrayList<>();
        this.gano = false;
        this.pantalla = null;
        
    }
    
    public void setPantalla(JF_Grafo pantalla){
        this.pantalla = pantalla;
    }
    
    @Override
    public void run(){
        while(!gano){
            try{
                for(Jugador jugador: jugadores){
                    jugador.getPantalla().setTurno(jugador.getID());
                    if(jugador.getTurnosPerdidos()==0 ){
                        if(jugador.getGano()){ // si gano el juego anterior, muevase al siguiente
                        boolean done = false;
                        Juego.sleep(1000);
                        while(!done){
                            new JF_Dados(jugador).setVisible(true);
                            while(!jugador.isTiraDados()){
                                Juego.sleep(2000);
                            }
                            if(jugador.getTurnosPerdidos()==3){
                                jugador.setTurnosPerdidos(2);
                                JOptionPane.showMessageDialog(jugador.getPantalla(), "Jugador pierde dos turnos :( ", "Dado BOWSER", 2);
                                break;
                            }
                            else if (jugador.getTurnosPerdidos()==2){
                                jugador.setTurnosPerdidos(0);
                                JOptionPane.showMessageDialog(jugador.getPantalla(), "Jugador pierde el resto del turno, no se mueve", "Dado BOWSER", 2);
                                break;
                            }
                            jugador.setTurnosPerdidos(0);
                            if(jugador.movible()){ // para ver si tiene suficientes para moverse al siguiente
                                while(jugador.movible() && jugador.getTurnosPerdidos()==0){
                                    done = false;
                                    JOptionPane.showMessageDialog(jugador.getPantalla(), "Muevase ", "Buenos dados", 2);
                                    jugador.moverse(pantalla);
                                    pantalla.personajeEnCasilla(jugador, jugador.getCasilla());
                                    
                                    jugador.getCasilla().jugar(jugador);
                                    
                                    while(!done){
                                        Juego.sleep(1000);
                                        done = jugador.getCasilla().done();
                                    }
                                    
                                }
                            }
                            
                            else{
                                JOptionPane.showMessageDialog(jugador.getPantalla(), "No tiene suficientes espacios para moverse ", "Malos dados", 2);
                                done = true;
                                }
                            if (jugador.getCasilla().getNombre().equals("Estrella")){
                                done = false;
                            }
                                
                        }

                        if(gano) break;
                    }
                        else{
                            //si no ha ganado un juego no se puede mover
                            jugador.getCasilla().jugar(jugador);
                            boolean done = false;        
                            while(!done){
                                sleep(1000);
                                done = jugador.getCasilla().done();
                            }
                        }
                        
                    }
                    else{
                        jugador.setTurnosPerdidos(jugador.getTurnosPerdidos()-1);
                    }
                    
                } 
            }
            catch(Exception ex){} 
        }
        pantalla.gano();
    }
    
    
    public void ganar(){
        this.gano = true;
    }
    
    public boolean isPlaying(){
        return !gano;
    }
    
    public Casilla siguienteTubo(Casilla casillaJugador){
        Random r = new Random();
        int tubo = r.nextInt(2);
        for (Casilla casilla : grafo){
            if (casilla.getNombre().equals("Tubo")){
                if (!casilla.equals(casillaJugador) &&  tubo--==0)
                    return casilla;
            }
        }
        for (Casilla casilla : grafo){
            if (casilla.getNombre().equals("Tubo") && !casilla.equals(casillaJugador)){
                return casilla;
            }
        }
        return null;
    }
    
    public void agregarJugador(String id, String imagen, String imagenPNG, String imagenDado){
        jugadores.add(new Jugador(id, imagen, imagenPNG, imagenDado));
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public ArrayList<Casilla> getGrafo() {
        return grafo;
    }
    
    public Casilla buscarCasilla(JLabel label){
        for(Casilla casilla : grafo){
            if(casilla.getLabel().equals(label)){
                return casilla;
            }
        }
        return null;
    }
    
    public Casilla buscarCasilla(int x, int y){
        for(Casilla casilla : grafo){
            if(casilla.getX()==x && casilla.getY()==y){
                return casilla;
            }
        }
        return null;
    }
    
    public void sort(){
        jugadores.sort(Comparator.comparing(Jugador::getNegInicio));
    }
    
    public boolean warshall(){
        int numNodos = grafo.size();
        boolean [][]adyacencia = new boolean [numNodos][numNodos];
        boolean [][]warshall = new boolean [numNodos][numNodos];
        
        //construir adyacencia
        for (int i = 0; i < numNodos; i++) {
            Casilla ic = grafo.get(i);
            for (int j = 0; j < numNodos; j++) {
                int ij = ic.buscarArista(grafo.get(j));
                if(i==j || ij!=-1){
                    warshall[i][j]=true;
                }
            }
            
        }
        //aplico warshall
        for (int k = 0; k < numNodos; k++)
            for (int i = 0; i < numNodos; i++)
                for (int j = 0; j < numNodos; j++)
                    warshall[i][j] = (warshall[i][j] || (warshall[i][k] && warshall[k][j]));
        
        //reviso si es convexo
        for (int i = 0; i < numNodos; i++) {
            for (int j = 0; j < numNodos; j++) {
                if (!warshall[i][j])
                    return false;
            }
        }
        
        return true;
    }
    
    public Jugador escogerContrincante(Jugador jugador){
        Jugador contrincante = jugador;
        while (contrincante.equals(jugador)){
            Random rand = new Random();
            contrincante = jugadores.get(rand.nextInt(0, jugadores.size()));
        }
        return contrincante;
    }

    public JF_Grafo getPantalla() {
        return pantalla;
    }
    
    
    
    public void crearCasillas(int cant){
        for (int i = 0; i < cant; i++) {
            Casilla nueva = null;
            switch (i) {
              case 0:
                nueva = new Casilla(i, "Inicio", TipoCasilla.INICIO,"src/main/java/tiles/inicio.png");
                break;
              case 1:
                nueva = new Casilla(i, "Final", TipoCasilla.FINAL,"src/main/java/tiles/final.png");
                break;
              case 2:
                nueva = new Casilla(i, "Carcel", TipoCasilla.CARCEL,"src/main/java/tiles/jail.jpg");
                break;
              case 3:
                nueva = new Casilla(i, "Tubo", TipoCasilla.TUBO,"src/main/java/tiles/tubo.png");
                break;
              case 4:
                nueva = new Casilla(i, "Tubo", TipoCasilla.TUBO,"src/main/java/tiles/tubo.png");
                break;
              case 5:
                nueva = new Casilla(i, "Tubo", TipoCasilla.TUBO,"src/main/java/tiles/tubo.png");
                break;
              case 6:
                nueva = new Casilla(i, "Estrella", TipoCasilla.ESTRELLA,"src/main/java/tiles/star.png");
                break;
              case 7:
                nueva = new Casilla(i, "Hielo", TipoCasilla.FLOR_HIELO,"src/main/java/tiles/hielo.png");
                break;
              case 8:
                nueva = new Casilla(i, "Fuego", TipoCasilla.FLOR_FUEGO,"src/main/java/tiles/fuego.png");
                break;
              case 9:
                nueva = new Casilla(i, "Cola", TipoCasilla.COLA,"src/main/java/tiles/cola.png");
                break;
              default:{
                  int residuo = i%10;
                  switch (residuo) {
                    case 0 -> nueva = new cjGato(i);
                    case 1 -> nueva = new cjMemoryPath(i);
                    case 2 -> nueva = new cjMemory(i);
                    case 3 -> nueva = new cjCatchTheCat(i); 
                    case 4 -> nueva = new cjBombermario(i);
                    case 5 -> nueva = new cjGuessWho(i);
                    case 6 -> nueva = new cjCoins(i);
                    case 7 -> nueva = new cjCards(i);
                    case 8 -> nueva = new cjSopa(i);
                    case 9 -> nueva = new cjGato(i);
                  }
                }
                break;

            }
            grafo.add(nueva);
        }
    }
   
    public void cambiarCasillas(){
        for(Casilla casilla : grafo){
            casilla.setImagenLabel("src/main/java/tiles/pregunta.png");
        }
    }
    
    public Casilla buscarInicio(){
        for(Casilla  casilla : grafo){
            if (casilla.getNombre().equals("Inicio")){
                return casilla;
            }
        }
        return null;
    }
    
    
}
