/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.p3_estru;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author marin
 */
public class JF_Pantalla extends javax.swing.JFrame {
    JLabel lblImage = new JLabel();
    Juego juego;
    int cantJugadores = 1;
    int cantElegida = 0;
    
    
    public JF_Pantalla() {
        initComponents();
        this.juego = new Juego();
        background("src/main/java/imagenes/2187756.jpg");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pantalla = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setPreferredSize(new java.awt.Dimension(1550, 1080));

        pantalla.setPreferredSize(new java.awt.Dimension(1550, 1080));

        jLabel2.setFont(new java.awt.Font("Schadow BT", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("press to start game");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pantallaLayout = new javax.swing.GroupLayout(pantalla);
        pantalla.setLayout(pantallaLayout);
        pantallaLayout.setHorizontalGroup(
            pantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pantallaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1536, Short.MAX_VALUE)
                .addContainerGap())
        );
        pantallaLayout.setVerticalGroup(
            pantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pantallaLayout.createSequentialGroup()
                .addContainerGap(957, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(73, 73, 73))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pantalla, javax.swing.GroupLayout.PREFERRED_SIZE, 1548, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pantalla, javax.swing.GroupLayout.DEFAULT_SIZE, 1074, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void background(String path){
        String fileName = path;
        ImageIcon icon = new ImageIcon(fileName);
        Image image = icon.getImage().getScaledInstance(1550, 880, Image.SCALE_SMOOTH ); //TODO
        icon = new ImageIcon(image,icon.getDescription());
        lblImage.setIcon(icon);
        lblImage.setSize(1550,880);
        lblImage.setLocation(0, 0);
        pantalla.add(lblImage);
    }
    
    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        pantalla.remove(jLabel2);
        background("src/main/java/imagenes/background1.jpg");
        JLabel titulo = new JLabel();
        titulo.setText("¿Cuantos personajes jugarán?");
        titulo.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 30));
        titulo.setSize(700,50);
        titulo.setLocation(550, 100);
        lblImage.add(titulo);
        
        MouseListener m1 = new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
               JLabel comp = (JLabel) e.getComponent();
               int jugadores = 0;
               
               try{
                    jugadores = Integer.parseInt(comp.getText()); 
                }
                catch (NumberFormatException ex){}
               cantElegida = jugadores;
               elegirJugadores(jugadores);
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
        
        for (int i = 1; i < 6; i++) {
            JLabel cuadro = new JLabel();
            cuadro.setText((i+1) +"");
            cuadro.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 200));
            cuadro.setSize(150,250);
            cuadro.setLocation(245*i, 400);
            cuadro.setBackground(Color.CYAN);
            cuadro.addMouseListener(m1);
            lblImage.add(cuadro);
            
        }
        
        
    }//GEN-LAST:event_jLabel2MouseClicked

    private void actualizarPersonajes(){
        if(juego.getJugadores().size() > 0){
            JLabel player = new JLabel();
            player.setText("Jugadores en partida: ");
            player.setSize(300, 50);
            player.setFont(new Font ("BAHNSCRIFT", Font.BOLD, 20));
            player.setLocation(1150, 50);
            lblImage.add(player);
        for (int i = 0; i < juego.getJugadores().size(); i++) {
            JLabel jugador = new JLabel();
            jugador.setText((i+1) + ". " + juego.getJugadores().get(i).getID());
            jugador.setSize(200, 50);
            jugador.setFont(new Font ("BAHNSCRIFT", Font.BOLD, 16));
            jugador.setLocation(1150, (50)+50*(i+1));
            lblImage.add(jugador); 
            
        }
        }
        if (juego.getJugadores().size() == cantElegida){
            lblImage.removeAll();
            lblImage.revalidate();
            lblImage.repaint();
            background("src/main/java/imagenes/background4.png");
            //incluir animacion vacilona de personajes if itme permits
            //creacion del grafo
            orden();
        }
        lblImage.revalidate();
        lblImage.repaint();
        
    }
    
    private void elegirJugadores(int cantidad){
        lblImage.removeAll();
        lblImage.revalidate();
        lblImage.repaint();
        int alto = 200;
        int ancho = 100;
        int columnas = 1;
        int filas = 10;
        
        
        JTextField id = new JTextField();
        id.setSize(200, 35);
        id.setFont(new Font ("BAHNSCRIFT", Font.PLAIN, 25));
        id.setLocation(550, 550);
        lblImage.add(id);
        
        JLabel idstr = new JLabel();
        idstr.setText("ID: ");
        idstr.setSize(200, 25);
        idstr.setFont(new Font ("BAHNSCRIFT", Font.BOLD, 30));
        idstr.setLocation(500, 550);
        lblImage.add(idstr);
        
        JLabel player = new JLabel();
        player.setText("PLAYER " + cantJugadores);
        player.setSize(200, 50);
        player.setFont(new Font ("BAHNSCRIFT", Font.BOLD, 35));
        player.setLocation(550, 500);
        lblImage.add(player);
        
        
        MouseListener m1 = new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
               if(cantidad>=cantJugadores){
               JLabel comp = (JLabel) e.getComponent();
               int jugadores = 0;
               try{
                    jugadores = Integer.parseInt(comp.getText()); 
                }
                catch (NumberFormatException ex){}
                juego.agregarJugador(id.getText(), getPersonajePNG(jugadores), getPersonajeIcon(jugadores), getPersonajeDados(jugadores));
                
                ImageIcon icon = new ImageIcon(getPersonajeDark(jugadores));
                Image image = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH ); 
                icon = new ImageIcon(image,icon.getDescription());
                comp.setIcon(icon);
                id.setText("");
                comp.removeMouseListener(this);
                
                cantJugadores++;
                player.setText("PLAYER " + cantJugadores);
               
                actualizarPersonajes();
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
        
        for (int i = 1; i < 9; i++) {
            if(i == 5) { filas=200; columnas = 1; }
            JLabel cuadro = new JLabel();
            cuadro.setText(i+"");
            String fileName = getPersonaje(i);
            ImageIcon icon = new ImageIcon(fileName);
            Image image = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH ); 
            icon = new ImageIcon(image,icon.getDescription());
            cuadro.setIcon(icon);
            cuadro.setSize(200,200);
            cuadro.setLocation(200*columnas, filas);
            cuadro.addMouseListener(m1);
            lblImage.add(cuadro);
            columnas++; 
        }
        
        
        
        
    }
    
    private void orden(){
        cantJugadores = 0;
        JLabel titulo = new JLabel();
        titulo.setText("¿De que forma desea escoger el orden de jugadores?");
        titulo.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 26));
        titulo.setSize(1100,50);
        titulo.setLocation(465, 690);
        lblImage.add(titulo);
        
        MouseListener m1 = new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
               JLabel comp = (JLabel) e.getComponent();
               if (comp.getText().equals("NUMEROS")){
                   JButton siguiente =  new JButton();
                   siguiente.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 45));
                   siguiente.setText("Siguiente");
                   siguiente.setSize(300,50);
                   siguiente.setHorizontalAlignment(JLabel.CENTER);
                   siguiente.setLocation(1100,250);
                   numeros(cantJugadores++, siguiente);
                   siguiente.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(juego.getJugadores().size()>cantJugadores)
                                numeros(cantJugadores++, siguiente);
                            else{
                                mostrarOrdenNumeros();
                            }
                        }
                    });
               }
               else if (comp.getText().equals("DADOS")){
                   JButton siguiente =  new JButton();
                   siguiente.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 45));
                   siguiente.setText("Siguiente");
                   siguiente.setSize(300,50);
                   siguiente.setHorizontalAlignment(JLabel.CENTER);
                   siguiente.setLocation(1100,250);
                   dados(cantJugadores++, siguiente);
                   siguiente.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(juego.getJugadores().size()>cantJugadores)
                                dados(cantJugadores++, siguiente);
                            else{
                                mostrarOrdenDados();
                            }
                        }
                    });

                    
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
        
        JLabel titulo1 = new JLabel();
        titulo1.setText("DADOS");
        titulo1.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 26));
        titulo1.setSize(100,50);
        titulo1.setLocation(600, 750);
        titulo1.addMouseListener(m1);
        lblImage.add(titulo1);
        
        JLabel titulo2 = new JLabel();
        titulo2.setText("NUMEROS");
        titulo2.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 26));
        titulo2.setSize(200,50);
        titulo2.setLocation(850, 750);
        titulo2.addMouseListener(m1);
        lblImage.add(titulo2);
        
    }
    
    private int tirarDado(){
        Random rand =  new Random();
        return rand.nextInt(1, 7);
    }
   
    private void dados(int personaje, JButton siguiente){
        lblImage.removeAll();
        lblImage.revalidate();
        lblImage.repaint();
        lblImage.add(siguiente);
        background("src/main/java/imagenes/back2.jpg");
        Jugador jugador = juego.getJugadores().get(personaje);
        JLabel titulo2 = new JLabel();
        titulo2.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 120));
        titulo2.setSize(200,130);
        titulo2.setForeground(Color.white);
        titulo2.setLocation(920, 550);
        lblImage.add(titulo2);

        MouseListener m1 = new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel comp = (JLabel) e.getComponent();
                int valor = tirarDado();
                jugador.setInicio(jugador.getInicio()+valor);
                jugador.setNegInicio(jugador.getNegInicio()-valor);
                ImageIcon icon = new ImageIcon(getImagenDado(valor));
                Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH ); //TODO
                icon = new ImageIcon(image,icon.getDescription());
                comp.setIcon(icon);
                comp.setSize(100,100);
                comp.removeMouseListener(this);
                titulo2.setText(jugador.getInicio() + "");
                lblImage.revalidate();
                lblImage.repaint();

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
            JLabel dado =  new JLabel();
            String fileName = "src/main/java/imagenes/dice3.gif";
            ImageIcon icon = new ImageIcon(fileName);
            dado.setIcon(icon);
            dado.setSize(100,100);
            dado.setLocation(300*(i+1), 100);
            dado.addMouseListener(m1);
            lblImage.add(dado);
        }
        
        
        
        JLabel jugador1 =  new JLabel();
        String fileName = jugador.getImagen();
        ImageIcon icon = new ImageIcon(fileName);
        Image image = icon.getImage().getScaledInstance(350, 450, Image.SCALE_SMOOTH ); //TODO
        icon = new ImageIcon(image,icon.getDescription());
        jugador1.setIcon(icon);
        jugador1.setSize(350,450);
        jugador1.setLocation(250, 300);
        lblImage.add(jugador1);
        
        JLabel nombreJugador =  new JLabel();
        nombreJugador.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 45));
        nombreJugador.setText(jugador.getID());
        nombreJugador.setSize(300,50);
        nombreJugador.setForeground(Color.white);
        nombreJugador.setHorizontalAlignment(JLabel.CENTER);
        nombreJugador.setLocation(300,250);
        lblImage.add(nombreJugador);
        
        JLabel titulo1 = new JLabel();
        titulo1.setText("El jugador ha acumulado:");
        titulo1.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 26));
        titulo1.setSize(500,50);
        titulo1.setForeground(Color.white);
        titulo1.setLocation(800, 450);
        lblImage.add(titulo1);
    }
    
    private void numeros(int personaje, JButton siguiente){
        lblImage.removeAll();
        lblImage.revalidate();
        lblImage.repaint();
        lblImage.add(siguiente);
        background("src/main/java/imagenes/back2.jpg");
        Jugador jugador = juego.getJugadores().get(personaje);
        
        JLabel jugador1 =  new JLabel();
        String fileName = jugador.getImagen();
        ImageIcon icon = new ImageIcon(fileName);
        Image image = icon.getImage().getScaledInstance(350, 450, Image.SCALE_SMOOTH ); //TODO
        icon = new ImageIcon(image,icon.getDescription());
        jugador1.setIcon(icon);
        jugador1.setSize(350,450);
        jugador1.setLocation(150, 200);
        lblImage.add(jugador1);
        
        JLabel nombreJugador =  new JLabel();
        nombreJugador.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 45));
        nombreJugador.setText(jugador.getID());
        nombreJugador.setSize(300,50);
        nombreJugador.setForeground(Color.white);
        nombreJugador.setHorizontalAlignment(JLabel.CENTER);
        nombreJugador.setLocation(150,150);
        lblImage.add(nombreJugador);
        
        JLabel titulo1 = new JLabel();
        titulo1.setText("Ingrese un numero del 1 al 100");
        titulo1.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 26));
        titulo1.setSize(500,50);
        titulo1.setForeground(Color.white);
        titulo1.setLocation(600, 280);
        lblImage.add(titulo1);
        
        JTextField numero =  new JTextField();
        numero.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 65));
        numero.setSize(300,100);
        numero.setLocation(650,350);
        KeyListener m1;
        m1 = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                
                int valor = 0;
               
                try{
                    valor = Integer.parseInt(numero.getText()); 
                }
                catch(NumberFormatException ex){}
                jugador.setInicio(valor);
            }
        };
        numero.addKeyListener(m1);
        lblImage.add(numero);
    }
    
    private void mostrarOrdenDados(){
        lblImage.removeAll();
        lblImage.revalidate();
        lblImage.repaint();
        juego.sort();
        crearGrafo();
        JLabel titulo2 = new JLabel();
        titulo2.setText("ORDEN DE TURNOS");
        titulo2.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 45));
        titulo2.setSize(600,100);
        titulo2.setForeground(Color.white);
        titulo2.setLocation(500, 10);
        lblImage.add(titulo2);
        
        for (int i = 0; i < juego.getJugadores().size(); i++) {
            JLabel cuadro = new JLabel();
            String fileName = juego.getJugadores().get(i).getImagen();
            ImageIcon icon = new ImageIcon(fileName);
            Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH ); 
            icon = new ImageIcon(image,icon.getDescription());
            cuadro.setIcon(icon);
            cuadro.setSize(100,100);
            cuadro.setLocation(550, 100+120*i);
            
            lblImage.add(cuadro);
            
            JLabel titulo = new JLabel();
            titulo.setText(juego.getJugadores().get(i).getInicio()+ "  ->  " +juego.getJugadores().get(i).getID());
            titulo.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 25));
            titulo.setSize(500,100);
            titulo.setForeground(Color.white);
            titulo.setLocation(700, 100+120*i);
            lblImage.add(titulo);
        }
    }

    private void mostrarOrdenNumeros(){
        lblImage.removeAll();
        lblImage.revalidate();
        lblImage.repaint();
        crearGrafo();
        int random = new Random().nextInt(1, 101);
        for(Jugador jugador : juego.getJugadores()){
            jugador.setNegInicio(Math.abs(jugador.getInicio() - random));
        }

        juego.sort();
        
        JLabel titulo2 = new JLabel();
        titulo2.setText("ORDEN DE TURNOS");
        titulo2.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 45));
        titulo2.setSize(600,100);
        titulo2.setForeground(Color.white);
        titulo2.setLocation(500, 10);
        lblImage.add(titulo2);
        
        JLabel titulo3 = new JLabel();
        titulo3.setText("NUMERO ELEGIDO");
        titulo3.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 30));
        titulo3.setSize(300,100);
        titulo3.setForeground(Color.white);
        titulo3.setLocation(50, 200);
        lblImage.add(titulo3);
        
        JLabel titulo4 = new JLabel();
        titulo4.setText(random+" ");
        titulo4.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 100));
        titulo4.setSize(200,200);
        titulo4.setForeground(Color.white);
        titulo4.setLocation(120, 300);
        lblImage.add(titulo4);
        
        for (int i = 0; i < juego.getJugadores().size(); i++) {
            JLabel cuadro = new JLabel();
            String fileName = juego.getJugadores().get(i).getImagen();
            ImageIcon icon = new ImageIcon(fileName);
            Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH ); 
            icon = new ImageIcon(image,icon.getDescription());
            cuadro.setIcon(icon);
            cuadro.setSize(100,100);
            cuadro.setLocation(550, 100+120*i);
            
            lblImage.add(cuadro);
            
            JLabel titulo = new JLabel();
            titulo.setText(juego.getJugadores().get(i).getInicio()+ "  ->  " +juego.getJugadores().get(i).getID());
            titulo.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 25));
            titulo.setSize(500,100);
            titulo.setForeground(Color.white);
            titulo.setLocation(700, 100+120*i);
            lblImage.add(titulo);
        }
    }
    
    private void crearGrafo(){
        JButton siguiente =  new JButton();
        siguiente.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 45));
        siguiente.setText("Crear grafo");
        siguiente.setSize(300,50);
        siguiente.setHorizontalAlignment(JLabel.CENTER);
        siguiente.setLocation(1100,350);
        siguiente.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 new JF_Grafo(juego).setVisible(true);
                 close();             
             }
         });
        lblImage.add(siguiente);
    }
    
    private void close(){
        this.dispose();
    }
    
    
    //GETTER DE IMAGENES 
    private String getPersonajeDados(int index){
        String str = "";
        switch (index) {
          case 1:
            str = "src/main/java/dosd/mario.png";
            break;
          case 2:
            str = "src/main/java/dosd/luigi.png";
            break;
          case 3:
            str = "src/main/java/dosd/peach.png";
            break;
          case 4:
            str = "src/main/java/dosd/daisy.png";
            break;
          case 5:
            str = "src/main/java/dosd/yoshi.png";
            break;
          case 6:
            str = "src/main/java/dosd/toad.png";
            break;
          case 7:
            str = "src/main/java/dosd/toadette.png";
            break;
          case 8:
            str = "src/main/java/dosd/bowser.png";
            break;
        }
        return str;
    }
    private String getPersonajeIcon(int index){
        String str = "";
        switch (index) {
          case 1:
            str = "src/main/java/icons/mario.png";
            break;
          case 2:
            str = "src/main/java/icons/luigi.png";
            break;
          case 3:
            str = "src/main/java/icons/peach.png";
            break;
          case 4:
            str = "src/main/java/icons/daisy.png";
            break;
          case 5:
            str = "src/main/java/icons/yoshi.png";
            break;
          case 6:
            str = "src/main/java/icons/toad.png";
            break;
          case 7:
            str = "src/main/java/icons/toadette.png";
            break;
          case 8:
            str = "src/main/java/icons/bowser.png";
            break;
        }
        return str;
    }
    private String getPersonaje(int index){
        String str = "";
        switch (index) {
          case 1:
            str = "src/main/java/personajes/mario.png";
            break;
          case 2:
            str = "src/main/java/personajes/luigi.png";
            break;
          case 3:
            str = "src/main/java/personajes/peach.png";
            break;
          case 4:
            str = "src/main/java/personajes/daisy.png";
            break;
          case 5:
            str = "src/main/java/personajes/yoshi.png";
            break;
          case 6:
            str = "src/main/java/personajes/toad.png";
            break;
          case 7:
            str = "src/main/java/personajes/toadette.png";
            break;
          case 8:
            str = "src/main/java/personajes/bowser.png";
            break;
        }
        return str;
    }
    private String getPersonajePNG(int index){
        String str = "";
        switch (index) {
          case 1:
            str = "src/main/java/personajesPNg/mario.png";
            break;
          case 2:
            str = "src/main/java/personajesPNg/luigi.png";
            break;
          case 3:
            str = "src/main/java/personajesPNg/peach.png";
            break;
          case 4:
            str = "src/main/java/personajesPNg/daisy.png";
            break;
          case 5:
            str = "src/main/java/personajesPNg/yoshi.png";
            break;
          case 6:
            str = "src/main/java/personajesPNg/toad.png";
            break;
          case 7:
            str = "src/main/java/personajesPNg/toadette.png";
            break;
          case 8:
            str = "src/main/java/personajesPNg/bowser.png";
            break;
        }
        return str;
    }
    private String getPersonajeDark(int index){
        String str = "";
        switch (index) {
          case 1:
            str = "src/main/java/personajesDark/mario.png";
            break;
          case 2:
            str = "src/main/java/personajesDark/luigi.png";
            break;
          case 3:
            str = "src/main/java/personajesDark/peach.png";
            break;
          case 4:
            str = "src/main/java/personajesDark/daisy.png";
            break;
          case 5:
            str = "src/main/java/personajesDark/yoshi.png";
            break;
          case 6:
            str = "src/main/java/personajesDark/toad.png";
            break;
          case 7:
            str = "src/main/java/personajesDark/toadette.png";
            break;
          case 8:
            str = "src/main/java/personajesDark/bowser.png";
            break;
        }
        return str;
    }
    private String getImagenDado(int index){
        String str = "";
        switch (index) {
          case 1:
            str = "src/main/java/dice/1.jpg";
            break;
          case 2:
            str = "src/main/java/dice/2.jpg";
            break;
          case 3:
            str = "src/main/java/dice/3.jpg";
            break;
          case 4:
            str = "src/main/java/dice/4.jpg";
            break;
          case 5:
            str = "src/main/java/dice/5.jpg";
            break;
          case 6:
            str = "src/main/java/dice/6.jpg";
            break;
        }
        return str;
    }
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel pantalla;
    // End of variables declaration//GEN-END:variables
}
