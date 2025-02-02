/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.p3_estru;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import static java.awt.Font.BOLD;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author marin
 */
public class JF_Grafo extends javax.swing.JFrame{
    JLabel lblImage;
    Juego juego;
    int x = -1;
    int y = -1;
    Casilla casilla = null;
    int value = 1;
    JLabel dados = new JLabel();
    JLabel nombre;
    
    public JF_Grafo(Juego juego) {
        initComponents();
        this.lblImage = new JLabel();
        this.juego = juego;
        this.nombre = new JLabel();
        nombre.setSize(500,50);
        nombre.setFont(new Font("Bahnscrift", Font.BOLD, 25));
        nombre.setLocation(0, 0);
        background("src/main/java/imagenes/background1.jpg");
    }

    public void setTurno(String id){
        nombre.setText(id + " turn's");
        lblImage.add(nombre);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pantalla = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cantCasillas = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnCrear = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1550, 850));

        jLabel1.setFont(new java.awt.Font("Bahnschrift", 1, 36)); // NOI18N
        jLabel1.setText("¿Cuantas casillas desea?");

        cantCasillas.setFont(new java.awt.Font("Bahnschrift", 1, 48)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("MINIMO 19");

        btnCrear.setFont(new java.awt.Font("Bahnschrift", 1, 24)); // NOI18N
        btnCrear.setText("crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pantallaLayout = new javax.swing.GroupLayout(pantalla);
        pantalla.setLayout(pantallaLayout);
        pantallaLayout.setHorizontalGroup(
            pantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pantallaLayout.createSequentialGroup()
                .addGroup(pantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pantallaLayout.createSequentialGroup()
                        .addGap(489, 489, 489)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pantallaLayout.createSequentialGroup()
                        .addGap(634, 634, 634)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pantallaLayout.createSequentialGroup()
                        .addGap(609, 609, 609)
                        .addComponent(cantCasillas, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pantallaLayout.createSequentialGroup()
                        .addGap(665, 665, 665)
                        .addComponent(btnCrear)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 271, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(213, 213, 213))
        );
        pantallaLayout.setVerticalGroup(
            pantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pantallaLayout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(54, 54, 54)
                .addComponent(cantCasillas, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(btnCrear)
                .addContainerGap(126, Short.MAX_VALUE))
            .addGroup(pantallaLayout.createSequentialGroup()
                .addComponent(jSeparator1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pantalla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pantalla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        String cantidad = cantCasillas.getText();
      
        int cant = 0;
        try{
            cant = Integer.parseInt(cantidad); 
        }
        catch(Exception excep){}
        if(cant>18)
            crearCuadros(cant);
        else{
           JOptionPane.showMessageDialog(this, "MINIMO 19 vertices", "Error", 2);}
       
        
    }//GEN-LAST:event_btnCrearActionPerformed
    
    private void crearCuadros(int cant){
        pantalla.removeAll();
        pantalla.revalidate();
        pantalla.repaint();
        pantalla.add(lblImage);

        juego.crearCasillas(cant);

        for (int i = 0; i < juego.getGrafo().size(); i++) {
            juego.getGrafo().get(i).setImagenLabel("src/main/java/tiles/ladrillo.png");
            JLabel label = juego.getGrafo().get(i).getLabel();
            
            label.setLocation(0,0);
            new Move(label);
            lblImage.add(label);
        }
 
        JButton btnLineas = new JButton();
        btnLineas.setText("Unir grafo");
        btnLineas.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 25));
        btnLineas.setSize(170, 50);
        btnLineas.setLocation(1300,10);
        lblImage.add(btnLineas);
        
        MouseListener m2 = new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel comp = (JLabel) e.getComponent();
                if (x!=-1){
                    linea(x, y , comp.getX(), comp.getY());
                    casilla = juego.buscarCasilla(x,y);
                    Random rand =  new Random();
                    int random =  rand.nextInt(1, 11);
                    casilla.agregarArista(juego.buscarCasilla(comp.getX(), comp.getY()),random);
                    casilla = null;
                    x=-1;
                    y=-1;
                }
                else{
                    x = comp.getX();
                    y = comp.getY(); 
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
        
        btnLineas.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            for(Casilla casilla : juego.getGrafo()){
                Move m1 = (Move) casilla.getLabel().getMouseListeners()[0];
                casilla.setXY(casilla.getLabel().getX(), casilla.getLabel().getY());
                casilla.getLabel().removeMouseListener(m1);
                casilla.getLabel().removeMouseMotionListener(m1);
                casilla.getLabel().addMouseListener(m2);
            }
            lblImage.remove(btnLineas);
            pantalla.revalidate();
            pantalla.repaint();
        }
        
    });
       crearLineas();
    }
    
    private void ponerlbllinea(int x, int y, int x1, int y1, int valor){
        int x2 = menor(x, x1) + ((mayor(x,x1)-menor(x,x1))/2);
        int y2 = menor(y, y1) + ((mayor(y,y1)-menor(y,y1))/2);
        JLabel btnLineas = new JLabel();
        btnLineas.setText(valor+"");
        btnLineas.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 20));
        btnLineas.setSize(25, 25);
        btnLineas.setLocation(x2,y2);
        lblImage.add(btnLineas); 
    }
    
    private int mayor(int a, int b){
        if (a>b){
            return a;
        }
        else{
            return b;
        }
    }
    
    private int menor(int a, int b){
        if (a<b){
            return a;
        }
        else{
            return b;
        }
    }
    
    private void crearLineas(){
        JButton btnLineas = new JButton();
        btnLineas.setText("Verificar grafo");
        btnLineas.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 15));
        btnLineas.setSize(170, 50);
        btnLineas.setLocation(1300,10);
        btnLineas.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!juego.warshall()){
                JOptionPane.showMessageDialog(lblImage, "GRAFO no es conexo, conecte x favo", "Error", 2);
            }
            else{
                lblImage.removeAll();
                lblImage.revalidate();
                lblImage.repaint();
                grafoLimpio();
                juego.cambiarCasillas();
                iniciarJuego();
            }
        }  
    });
        
        lblImage.add(btnLineas);        
    }
    
    public void grafoLimpio(){
        for(Casilla casilla : juego.getGrafo()){
            lblImage.add(casilla.getLabel());
            casilla.getLabel().removeMouseListener(casilla.getLabel().getMouseListeners()[0]);
            for (Arista arista : casilla.getAristas()){
                linea(casilla.getX() , casilla.getY(), arista.getCasilla().getX(), arista.getCasilla().getY());
                ponerlbllinea(casilla.getX() , casilla.getY(), arista.getCasilla().getX(), arista.getCasilla().getY(), arista.getPeso()); 
            }
        }
    }
    
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
   
    public void linea( int x, int y, int x1, int y1){
        Graphics2D g = (Graphics2D) lblImage.getGraphics();
        g.setStroke(new BasicStroke(10));
        g.draw(new Line2D.Float(x, y, x1, y1)); 
        int exe[] = {x1,x1+5,x1+10};
        int eye[] = {y1,y1-5,y1};
        g.setColor(Color.red);
        g.drawPolygon(exe, eye, 3);
    }

    public Juego getJuego() {
        return juego;
    }
    
    public void iniciarJuego(){
        Casilla inicio = juego.getGrafo().get(0);
        inicio.setVisitado(true);
        inicio.revelarImagen();
        for(Jugador jugador: juego.getJugadores()){
            personajeEnCasilla(jugador, inicio);
            jugador.setPantalla(this);
        }
        juego.setPantalla(this);
        juego.start();
    }
    
    public void gano(){
        lblImage.removeAll();
        lblImage.revalidate();
        lblImage.repaint();
        background("src/main/java/imagenes/win.jpg");
    }
    
    public void ganador(Jugador jugador){
        lblImage.removeAll();
        lblImage.revalidate();
        lblImage.repaint();
        
        String fileName1 = jugador.getImagenDado();
        ImageIcon icon1 = new ImageIcon(fileName1);
        Image image1 = icon1.getImage().getScaledInstance(1200, 600, Image.SCALE_SMOOTH ); //TODO
        icon1 = new ImageIcon(image1,icon1.getDescription());
        dados.setIcon(icon1);
        dados.setSize(1200,600);
        dados.setLocation(0, 0);
        lblImage.add(dados);
        
        JLabel titulo2 = new JLabel();
        titulo2.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 120));
        titulo2.setSize(200,130);
        titulo2.setText("GANADOR");
        titulo2.setForeground(Color.white);
        titulo2.setLocation(0, 0);
        dados.add(titulo2);
    }
    
    public void personajeEnCasilla(Jugador jugador, Casilla casilla){
        //se anaden uno a otro en las casillas
        jugador.setCasilla(casilla);
        casilla.addJugador(jugador);
        jugador.addCasilla(casilla);
        
        // graficamente se coloca al bichito en la casilla
        iconEnCasilla(casilla);
        
        //actualizacion de lo grafico
        lblImage.revalidate();
        lblImage.repaint();
    }
    
    public void iconEnCasilla(Casilla casilla){
        casilla.getLabel().removeAll();
        casilla.getLabel().revalidate();
        casilla.getLabel().repaint();
        for (int i = 0; i < casilla.getJugadoresEnCasilla().size(); i++) {
            Jugador jugador = casilla.getJugadoresEnCasilla().get(i);
            JLabel imagenJugador =  new JLabel();
            ImageIcon icon = new ImageIcon(jugador.getImagenPNG());
            Image image = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH ); //TODO
            icon = new ImageIcon(image,icon.getDescription());
            imagenJugador.setIcon(icon);
            imagenJugador.setSize(25,25);
            int j = 0;
            int k = 0;
            if(i%2!=0) j++;
            if(i>1) k++;
            if(i>3) k++;
            imagenJugador.setLocation(10+(j*30), k*25);
            casilla.getLabel().add(imagenJugador);
        }
        casilla.getLabel().revalidate();
        casilla.getLabel().repaint();
    }
    
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrear;
    private javax.swing.JTextField cantCasillas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel pantalla;
    // End of variables declaration//GEN-END:variables
}
