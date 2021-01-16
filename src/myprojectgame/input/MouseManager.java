/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import myprojectgame.ui.UIManager;


// A custom MouseManager class that implements both the MouseListener(mouse events like clicking a button)
// and the MouseMotionListener interface(moving the mouse in the JFrame / Canvas)
// Allows the game to get mouse input from the user

public class MouseManager implements MouseListener,MouseMotionListener {
    
    public boolean leftPressed;
    private boolean rightPressed;
    private int mouseX,mouseY; 
    private UIManager ui;
    
    public MouseManager() {
        
    }
    
    public void setUIManager(UIManager ui) {
        this.ui = ui;
    }
    
    // Getters 
    
    public boolean isLeftPressed() {
        return leftPressed;
    }
    
    public boolean isRightPressed() {
        return rightPressed;
    }
    
    public int getMouseX() {
        return mouseX;
    }
    
    public int getMouseY() {
        return mouseY;
    }
    
    // Implemented methods
    
    @Override
    public void mouseClicked(MouseEvent e) {
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
       if (e.getButton() == MouseEvent.BUTTON1) {
           // BUTTON1 constant is the left mouse button
           leftPressed = true;
       } else if (e.getButton() == MouseEvent.BUTTON3) {
           // BUTTON2 is a constant for the mouse scroll/button
           // BUTTON3 is a constant for the right mouse button
           rightPressed = true;
           
       }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       if (e.getButton() == MouseEvent.BUTTON1) {
           // BUTTON1 constant is the left mouse button
           leftPressed = false;
       } else if (e.getButton() == MouseEvent.BUTTON3) {
           // BUTTON2 is a constant for the mouse scroll/button
           // BUTTON3 is a constant for the right mouse button
           rightPressed = false;
       }
       
       if (ui != null) {
           ui.onMouseRelease(e);
       }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
       
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        
        if (ui != null) {
           ui.onMouseMove(e);
       }
    }
    
}
