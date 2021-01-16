/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import myprojectgame.Handler;

/**
 *
 * @author my
 */
public class UIManager {
    // Stores all the buttons in an ArrayList and allows us
    // to manipulate said buttons through methods like addObject(),removeObject()
    private Handler handler;
    private ArrayList<UIObject> objects;

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<UIObject> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<UIObject> objects) {
        this.objects = objects;
    }
    
    
    public UIManager(Handler handler) {
        this.handler = handler;
        objects = new ArrayList<UIObject>();
    }
    
    public void tick () {
        for (UIObject o : objects) {
            o.tick();
        }
    }
    
    public void render(Graphics g) {
         for (UIObject o : objects) {
            o.render(g);
        }
    }
    
    public void onMouseMove(MouseEvent e) {
         for (UIObject o : objects) {
            o.onMouseMove(e);
        }
    }
    
    public void onMouseRelease(MouseEvent e) {
         for (UIObject o : objects) {
            o.onMouseRelease(e);
        }
    }
    
    public void addObject(UIObject o) {
        objects.add(o);
    }
    
    public void removeObject(UIObject o) {
        objects.remove(o);
    }
}
