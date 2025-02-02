/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.p3_estru;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author marin
 */
public class Move implements MouseListener, MouseMotionListener {
    private int x;
    private int y;
    private JLabel label;

    public Move(Component ... comps){
        for(Component label : comps){
        this.label = (JLabel) label;
        label.addMouseListener(this);
        label.addMouseMotionListener(this);}
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        e.getComponent().setLocation(e.getX()+e.getComponent().getX()-x, e.getY()+e.getComponent().getY()-y);
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
    
}
