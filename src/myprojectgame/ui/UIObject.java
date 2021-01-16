/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import myprojectgame.Handler;

/**
 *
 * @author my
 */
public abstract class UIObject {
    // Draws menu state buttons to the screen using given X and Y coordinates,width and height of the button
    
    protected float x,y;
    protected int width,height;
    protected Rectangle bounds;
    protected boolean hovering = false; // if user is hovering over the button,display a different image
    protected Handler handler;
    
    public UIObject(float x,float y, int width,int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle( (int) x , (int) y, width, height );
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics g);
    
    public abstract void onClick();
    
    // if user is hovering over the button,display a different image
    public void onMouseMove(MouseEvent e) {
        if (bounds.contains(e.getX(), e.getY())) {
            hovering = true;
        } else {
            hovering = false;
        }
    }
    
    public void onMouseRelease(MouseEvent e) {
         if (hovering) {
             onClick();
         }
    }

    // Getters and Setters
    
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isHovering() {
        return hovering;
    }

    public void setHovering(boolean hovering) {
        this.hovering = hovering;
    }
    
    
}
