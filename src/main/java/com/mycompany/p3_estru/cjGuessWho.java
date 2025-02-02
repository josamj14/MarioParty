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
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

enum Guessed{
    BIRDO("src/main/java/who/birdo.png", "birdo"),
    BOWSER("src/main/java/who/bowser.png", "bowser"),
    DAISY("src/main/java/who/daisy.png", "daisy"),
    DONKING("src/main/java/who/donking.png", "donking"),
    KOOPA_TROOPA("src/main/java/who/koopa troopa.png", "koopa troopa"),
    LUIGI("src/main/java/who/luigi.png", "luigi"),
    MARIO("src/main/java/who/mario.png", "mario"),
    PEACH("src/main/java/who/peach.png", "peach"),
    SHY_GUY("src/main/java/who/shy guy.png", "shy guy"),
    TOAD("src/main/java/who/toad.png", "toad"),
    TOADETTE("src/main/java/who/toadette.png", "toadette"),
    WALUIGI("src/main/java/who/waluigi.png", "waluigi"),
    WARIO("src/main/java/who/wario.png", "wario"),
    YOSHI("src/main/java/who/yoshi.png", "yoshi");
    
    
    String imagen;
    String nombre;
    
    private Guessed(String imagen, String nombre){
        this.imagen = imagen;
        this.nombre = nombre;
    }
    
}

public class cjGuessWho extends Casilla{
    JLabel matriz[][];
    Jugador player;
    JF_Juego pantalla;
    boolean done;
    int bombas;
    JLabel fondo;
    Guessed elegido;
    JLabel num;

    public cjGuessWho(int ID) {
        super(ID, "Guess Who", TipoCasilla.JUEGO, "src/main/java/tiles/who.png");
        resetear();
    }
    
    @Override
    public boolean done(){
        return done;
    }
    
    @Override
    public void resetear(){
        matriz = new JLabel[10][10];
        player = null;
        pantalla = null;
        done = false;
        bombas = 0;
        Random rand = new Random();
        bombas = rand.nextInt(4,9);
        fondo = new JLabel();
        fondo.setSize(520,520);
        fondo.setLocation(100, 100);
        elegido = null;
        num = new JLabel();
        num.setSize(200,100);
        num.setLocation(700, 300);
        num.setText(bombas+"");
        num.setFont(new Font("Bahnscrift", Font.PLAIN, 15));
        
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
        this.pantalla.lblImage.add(fondo);
        this.pantalla.lblImage.add(num);
        this.player = jugador;
        pantalla.stats(jugador);
        init();  
    }
    
    public void init(){
        pantalla.lblImage.revalidate();
        pantalla.lblImage.repaint();
        
        MouseListener m1 = new MouseListener(){
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JLabel comp = (JLabel) e.getComponent();
                        if(bombas-- > 0){
                            num.setText(bombas+"");
                            comp.removeMouseListener(this);
                            comp.setIcon(null);
                        }
                        else{
                            num.setText("Quien es?");
                            removeAllML(this);
                            botones();
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
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JLabel label = new JLabel();
                label.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 100));
                label.setSize(50,50);
                label.setLocation((i*52), (j*52));
                label.addMouseListener(m1);
                
                String fileName = "src/main/java/tiles/pregunta.png";
                ImageIcon icon = new ImageIcon(fileName);
                Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH ); //TODO
                icon = new ImageIcon(image,icon.getDescription());
                label.setIcon(icon);
                
                matriz[i][j] = label;
                fondo.add(matriz[i][j]);
            }
        }
        imagen();
        pantalla.setVisible(true);
        pantalla.lblImage.revalidate();
        pantalla.lblImage.repaint();
        
        
    }
    
    public void imagen(){
        Random rand = new Random();
        int chosen = rand.nextInt(14);
        String fileName ="";
        for (Guessed personaje : Guessed.values()) { 
            if(chosen==0){
                fileName = personaje.imagen;
                elegido = personaje;
                break;
            }
            else{
                chosen--;
            }
        }
        ImageIcon icon = new ImageIcon(fileName);
        Image image = icon.getImage().getScaledInstance(520, 520, Image.SCALE_SMOOTH ); //TODO
        icon = new ImageIcon(image,icon.getDescription());
        fondo.setIcon(icon);
        fondo.revalidate();
        fondo.repaint();
    }
    
    public void removeAllML(MouseListener m){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                matriz[i][j].removeMouseListener(m);
            }
        }
    }
    
    public void botones(){
        for (int i = 0; i < 14; i++) {
            JButton boton = new JButton();
            boton.setSize(150,25);
            boton.setText(getEnum(i));
            boton.setFont(new Font("Bahnscrift", Font.PLAIN, 15));
            boton.setBackground(Color.cyan);
            boton.setLocation(900,100+35*i);
            boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton elBoton = (JButton) e.getSource();
                if (elBoton.getText().equals(elegido.nombre)){
                   player.setGano(true);
                   JOptionPane.showMessageDialog(pantalla, "Has ganado Guess Who :) ", "GUESS WHO", 2);
                   pantalla.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(pantalla, "Has perdido Guess Who :( ", "GUESS WHO", 2);
                    player.setGano(false);
                    pantalla.dispose();
                }
                done=true;
            }
            });
            pantalla.lblImage.add(boton);
            }
        pantalla.revalidate();
        pantalla.repaint();
        }
 
    public String getEnum(int i){
        int cont = 0;
        for (Guessed elem : Guessed.values()) {
            if (cont==i){
                return elem.nombre;
            }
            cont++;
        }
        return null;
    }
    
    
}
