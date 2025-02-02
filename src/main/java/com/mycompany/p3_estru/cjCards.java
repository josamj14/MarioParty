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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


enum Carta{
    JOKER(),
    CA(4,13),
    C2(4,1),
    C3(4,2),
    C4(4,3),
    C5(4,4),
    C6(4,5),
    C7(4,6),
    C8(4,7),
    C9(4,8),
    C10(4,9),
    CJ(4,10),
    CQ(4,11),
    CK(4,12),
    T2(3,1),
    T3(3,2),
    T4(3,3),
    T5(3,4),
    T6(3,5),
    T7(3,6),
    T8(3,7),
    T9(3,8),
    T10(3,9),
    TJ(3,10),
    TQ(3,11),
    TK(3,12),
    D2(2,1),
    D3(2,2),
    D4(2,3),
    D5(2,4),
    D6(2,5),
    D7(2,6),
    D8(2,7),
    D9(2,8),
    D10(2,9),
    DJ(2,10),
    DQ(2,11),
    DK(2,12),
    E2(1,1),
    E3(1,2),
    E4(1,3),
    E5(1,4),
    E6(1,5),
    E7(1,6),
    E8(1,7),
    E9(1,8),
    E10(1,9),
    EJ(1,10),
    EQ(1,11),
    EK(1,12);
    
    
    private Carta(int valorForma, int valorCarta){
        imagen = "src/main/java/"+forma(valorForma) +"/"+carta(valorCarta)+".png";
        this.valorCarta = valorCarta;
        this.valor = valorCarta+valorForma;
        String images = "src/main/java/imagenes/CARDBACK.png";
        ImageIcon icon = new ImageIcon(images);
        Image image = icon.getImage().getScaledInstance(60, 100, Image.SCALE_SMOOTH ); 
        icon = new ImageIcon(image,icon.getDescription());
        this.label = new JLabel();
        label.setIcon(icon);
        label.setSize(60, 100);
    }
    
    private Carta(){
        imagen = "src/main/java/imagenes/JOKER.png";
        String images = "src/main/java/imagenes/CARDBACK.png";
        ImageIcon icon = new ImageIcon(images);
        Image image = icon.getImage().getScaledInstance(60, 100, Image.SCALE_SMOOTH ); 
        icon = new ImageIcon(image,icon.getDescription());
        this.valor = this.valorCarta =100;
        this.label = new JLabel();
        label.setIcon(icon);
        label.setSize(60, 100);
    }
    
    String imagen;
    int valor;
    int valorCarta;
    JLabel label;
    
    public String forma(int valor){
        switch(valor){
            case 4 -> {return "corazon";}
            case 3 -> {return "trebol";}
            case 2 -> {return "diamante";}
            case 1 -> {return "espada";}
        }
        return "";
    }
    
    public String carta(int valor){
        switch(valor){
            case 1 -> {return "2";}
            case 2 -> {return "3";}
            case 3 -> {return "4";}
            case 4 -> {return "5";}
            case 5 -> {return "6";}
            case 6 -> {return "7";}
            case 7 -> {return "8";}
            case 8 -> {return "9";}
            case 9 -> {return "10";}
            case 10 -> {return "J";}
            case 11 -> {return "Q";}
            case 12 -> {return "K";}
            case 13 -> {return "A";}
        }
        return "";
    }
    
    public void revealCarta(){
        ImageIcon icon = new ImageIcon(imagen);
        Image image = icon.getImage().getScaledInstance(100, 160, Image.SCALE_SMOOTH ); 
        icon = new ImageIcon(image,icon.getDescription());
        label.setIcon(icon);
    }
}

public class cjCards extends Casilla{
    ArrayList<Carta> cartas;
    MouseListener m;
    ArrayList<Carta> cartasJugadores;
    ArrayList<Jugador> jugadores;
    Jugador jugador1;
    JF_Juego pantalla;
    int actual;
    boolean done;
    
    public cjCards(int ID) {
        super(ID, "Mario Cards", TipoCasilla.JUEGO, "src/main/java/tiles/cards.png");
        resetear();
    }
    
    @Override
    public void resetear(){
        cartas = new ArrayList();
        done = false;
        m = null;
        jugador1 = null;
        cartasJugadores  = new ArrayList();
        jugadores =  new ArrayList();
        pantalla = null;
        actual = 0;
        resetCards();
    }
    
    @Override
    public void jugar(Jugador jugador) {
        resetear();
        
        jugador1 = jugador;
        this.revelarImagen();
        this.setVisitado(true);
        jugador.setGano(false);
        
        Juego miJuego = jugador.getPantalla().getJuego();
        JF_Juego pantalla = new JF_Juego(miJuego, jugador.getCasilla(), jugador);
        this.pantalla = pantalla;
        
        
        jugadores = miJuego.getJugadores();
        this.pantalla.stats(jugadores.get(actual));
        
        crearCartas();
    }
    
    public void resetCards(){
        for (Carta card : Carta.values()) {  
            String images = "src/main/java/imagenes/CARDBACK.png";
            ImageIcon icon = new ImageIcon(images);
            Image image = icon.getImage().getScaledInstance(60, 100, Image.SCALE_SMOOTH ); 
            icon = new ImageIcon(image,icon.getDescription());

            card.label.setIcon(icon);
            card.label.setSize(60, 100);
        }
    }
    
    public void crearCartas(){
        m = new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel comp = (JLabel) e.getComponent();
                comp.removeMouseListener(m);
                cartasJugadores.add(buscarCarta(comp));
                actual++;
                pantalla.lblImage.remove(comp);
                if(actual<jugadores.size())
                    pantalla.stats(jugadores.get(actual));
                else{
                    removeML();
                    init();
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
        
        for (Carta carta: Carta.values()){
            cartas.add(carta);
        }
        
        int x = 0;
        int y = 0;
        
        for (Carta carta: cartas){
            if(x>12){ x=0;  y++; }
            carta.label.addMouseListener(m);
            carta.label.setLocation(100+70*x++,100+110*y);
            pantalla.lblImage.add(carta.label);
        }
        
        this.pantalla.setVisible(true);
        pantalla.lblImage.repaint();
        pantalla.lblImage.revalidate();
    }
    
    @Override
    public boolean done(){
        return done;
    }
            
            
    

    public void init(){
        pantalla.setVisible(false);
        pantalla.clear();
        for (int i = 0; i < jugadores.size(); i++) {
            JLabel icon = new JLabel();
            icon.setSize(50, 50);
            icon.setLocation(100+150*i,100);
            
            ImageIcon icone = new ImageIcon(jugadores.get(i).getImagenPNG());
            Image image = icone.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH ); 
            icone = new ImageIcon(image,icone.getDescription());
            icon.setIcon(icone);
            
            JLabel nombre = new JLabel();
            nombre.setSize(50, 50);
            nombre.setLocation(150+150*i,100);
            nombre.setFont(new Font("Bahnscrift", Font.PLAIN, 15));
            nombre.setText(jugadores.get(i).getID());
            
            JLabel carta = cartasJugadores.get(i).label;
            carta.setSize(100,160);
            carta.setLocation(100+150*i,200);
            cartasJugadores.get(i).revealCarta();
            pantalla.lblImage.add(icon);
            pantalla.lblImage.add(nombre);
            pantalla.lblImage.add(carta);
        }
        
        int valor = 0;
        int jugador = 0;
        for (int i = 0; i < jugadores.size(); i++) {
            if(cartasJugadores.get(i).valor>valor){
                valor = cartasJugadores.get(i).valor;
                jugador = i;
            }
        }
        
        cartasJugadores.get(jugador).label.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.green));
        
        
        if (jugadores.get(jugador).equals(jugador1)) {
            JOptionPane.showMessageDialog(pantalla, "Has ganado Mario Cards", "MARIO CARDS", 2);
            jugador1.setGano(true);
        }
        else{
            JOptionPane.showMessageDialog(pantalla, "Has perdido Mario Cards", "MARIO CARDS", 2);
        }
        pantalla.setVisible(true);
        
        
        JButton boton = new JButton();
        boton.setSize(150,25);
        boton.setText("Salir");
        boton.setFont(new Font("Bahnscrift", Font.PLAIN, 15));
        boton.setBackground(Color.red);
        boton.setLocation(900,500);
        boton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            done = true;
            pantalla.dispose();  
        }
        });
        pantalla.lblImage.add(boton);



              
    }
    
    public Carta buscarCarta(JLabel label){
        for (Carta carta: Carta.values()){
            if (carta.label.equals(label)){
                return carta;
            }
        }
        return null;
    }
    
    public void removeML(){
        for (Carta carta : cartas){
            carta.label.removeMouseListener(m);
        }
    }
    
    
    
    
    
}
