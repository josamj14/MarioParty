/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.p3_estru;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author marin
 */
public class JF_Dados extends javax.swing.JFrame {
    JLabel dados = new JLabel();
    Jugador jugador;

    /**b
     * Creates new form JF_Dados
     */
    public JF_Dados(Jugador jugador) {
        initComponents();
        pantalla.add(dados);
        this.jugador = jugador;
        jugador.setTiraDados(false);
        dados();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pantalla = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("OK !");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pantallaLayout = new javax.swing.GroupLayout(pantalla);
        pantalla.setLayout(pantallaLayout);
        pantallaLayout.setHorizontalGroup(
            pantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pantallaLayout.createSequentialGroup()
                .addContainerGap(1071, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(53, 53, 53))
        );
        pantallaLayout.setVerticalGroup(
            pantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pantallaLayout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(382, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pantalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pantalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jugador.setTiraDados(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void dados(){
        String fileName1 = jugador.getImagenDado();
        ImageIcon icon1 = new ImageIcon(fileName1);
        Image image1 = icon1.getImage().getScaledInstance(1200, 600, Image.SCALE_SMOOTH ); //TODO
        icon1 = new ImageIcon(image1,icon1.getDescription());
        dados.setIcon(icon1);
        dados.setSize(1200,600);
        dados.setLocation(0, 0);
        
        JLabel titulo2 = new JLabel();
        titulo2.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 120));
        titulo2.setSize(200,130);
        titulo2.setForeground(Color.white);
        titulo2.setLocation(120, 400);
        dados.add(titulo2);

        MouseListener m1 = new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel comp = (JLabel) e.getComponent();
                int valor = tirarDado();
                if(valor!=6){
                    jugador.setEspacios(jugador.getEspacios()+valor);}
                else{
                    jugador.setTurnosPerdidos(jugador.getTurnosPerdidos()+1);
                }
                ImageIcon icon = new ImageIcon(getImagenDado(valor));
                Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH ); //TODO
                icon = new ImageIcon(image,icon.getDescription());
                comp.setIcon(icon);
                comp.setSize(100,100);
                comp.removeMouseListener(this);
                titulo2.setText(jugador.getEspacios() + "");

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
            dado.setLocation(250*(i+1), 100);
            dado.addMouseListener(m1);
            dados.add(dado);
        }
        
        JLabel nombreJugador =  new JLabel();
        nombreJugador.setFont(new Font("BAHNSCHRIFT", Font.BOLD, 45));
        nombreJugador.setText(jugador.getID());
        nombreJugador.setSize(300,50);
        nombreJugador.setForeground(Color.white);
        nombreJugador.setHorizontalAlignment(JLabel.CENTER);
        nombreJugador.setLocation(10,250);
        dados.add(nombreJugador);
        
    }
    
    private int tirarDado(){
        Random rand =  new Random();
        return rand.nextInt(1, 7);
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
            str = "src/main/java/dice/bowser.jpg";
            break;
        }
        return str;
    }
    
    
    
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pantalla;
    // End of variables declaration//GEN-END:variables
}
