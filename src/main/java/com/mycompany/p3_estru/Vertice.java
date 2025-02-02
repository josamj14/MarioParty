/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.p3_estru;

import java.util.ArrayList;

/**
 *
 * @author marin
 */
public class Vertice {
    public int ID;
    boolean visitado;
    ArrayList<Vertice> aristas;
    int peso;//peso
    ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
    Casilla casilla;
    
    public Vertice(int ID)
    {
        aristas = new ArrayList<Vertice>();
        this.ID = ID;
        this.visitado = false;
    }

    public Vertice(int ID, int peso)
    {
        aristas = new ArrayList<Vertice>();
        this.ID = ID;
        this.visitado = false;
        this.peso = peso;
    }

    public void agregarArista (Vertice arista)
    {
        // si no está la arista para no repetir
        if (buscarArista(arista) == -1)
            aristas.add(new Vertice(arista.ID));
    }
    
    public void agregarArista (Vertice arista, int peso)
    {
        // si no está la arista para no repetir
        if (buscarArista(arista) == -1)
            aristas.add(new Vertice(arista.ID, peso));
    }

    public int buscarArista(Vertice v)
    {
        for (int i = 0; i < aristas.size(); i++) {
            if (v.ID == aristas.get(i).ID)
                return i;
        }
        return -1;
    }
    
}
