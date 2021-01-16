/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.items;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import myprojectgame.Handler;

/**
 *
 * @author my
 */
public class ItemManager {
    
    // Provides methods for working with the items in the game
    // Allows us to add or remove items from the game and the game world
    private Handler handler;
    private ArrayList<Items> items;

  
    
    public ItemManager(Handler handler) {
        this.handler = handler;
        items = new ArrayList<Items>();
    }
    
    public void tick() {
        Iterator<Items> it = items.iterator();
        while (it.hasNext()) {
            Items i = it.next();
            i.tick();
            if (i.isPickedUp()) {
                // If item is picked up by player,remove it from the game world
                it.remove();
            }
        }
    }
    
    public void render(Graphics g) {
        for (Items i : items) {
            i.render(g);
        }
    }
    
    public void addItem(Items i) {
        i.setHandler(handler);
        items.add(i);
    }
    
    // Getters and Setters
    
    

    public Handler getHandler() {
        return handler;
    }
    
      public ArrayList<Items> getItems() {
        return items;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
    
}
