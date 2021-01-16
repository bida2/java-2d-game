/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import myprojectgame.Handler;
import myprojectgame.gfx.Assets;
import myprojectgame.gfx.Text;
import myprojectgame.items.Items;

/**
 *
 * @author my
 */
public class Inventory {
    // Creates the Player's inventory and stores the items 
    // in a data structure - ArrayList
    // Items are traversed with the numpad buttons (8 for up,2 for down)
    private Handler handler;
    private boolean active = false;
    private ArrayList<Items> inventoryItems;

    
    // Hardcoded coordinates to draw the inventory to the screen
    private int invX = 64,invY = 48,
            invWidth = 512,invHeight = 384,
            invListCenterX = invX + 171,
            invListCenterY = invY + invHeight / 2 + 5,
            invListSpacing = 30;
    
    private int invImageX = 452, invImageY = 82,
            invImageWidth = 64, invImageHeight = 64;
    
    private int invCountX = 484, invCountY = 172;
    
    private int selectedItem = 0;
    
    public Inventory(Handler handler) {
        this.handler = handler;
        inventoryItems = new ArrayList<Items>();
        
        
    }
    
    
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public ArrayList<Items> getInventoryItems() {
        return inventoryItems;
    }
    
    // Gives the Player character the effects of the Zombie Flesh item
    // when said item is consumed by the Player
    public void giveZombieFleshEffects() {
         handler.getWorld().getEntityManager().getPlayer().hurt(5);
            System.out.println(handler.getWorld().getEntityManager().getPlayer().health);
            int currItemCount = handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems().get(selectedItem).getCount();
            handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems().get(selectedItem).setCount(currItemCount - 1);
            if (currItemCount == 1) {
                handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems().remove(selectedItem);
            }
    }
    
    
    public void tick() {
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_I)) {
            active = !active; // Invert value here so we can toggle inventory on and off
        }
        if (!active) {
            return;
        }
        
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems().get(selectedItem).isUsable()) {
          switch (handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems().get(selectedItem).getID()) {
              case 4: giveZombieFleshEffects(); break;
              // case X: giveHealthPackEffects(); break;
              // Add more item effects here in the future
          }
        }
        
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_NUMPAD8)) {
            selectedItem--;
        }
        
         if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_NUMPAD2)) {
            selectedItem++;
        }
         
         if (selectedItem < 0) {
             selectedItem = inventoryItems.size() - 1;
         } else if (selectedItem >= inventoryItems.size()) {
             selectedItem = 0;
         }
        
        
    }
    
    // Inventory methods
    public void addItem(Items item) {
        for (Items i : inventoryItems) {
            if (i.getID() == item.getID()) {
                i.setCount(i.getCount() + item.getCount());
                return;
            }
        }
       inventoryItems.add(item);
    }
    
    
    public void render(Graphics g) {
        if (!active) {
            return;
        }
        
        g.drawImage(Assets.inventoryScreen,invX,invY,invWidth,invHeight,null);
        
        
        int len = inventoryItems.size();
        
        if (len == 0) {
            return; // No items in inventory --> return
        }
        
        for (int i = -5;i < 6; i++) {
            if (selectedItem + i < 0 || selectedItem + i >= len) {
                continue;
            }
            if (i == 0) {
                Text.drawString(g,"> " + inventoryItems.get(selectedItem + i).getName() + " <", invListCenterX, invListCenterY + i * invListSpacing, true, Color.yellow, Assets.font28);
            } else {
                Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invListCenterX, invListCenterY + i * invListSpacing, true, Color.white, Assets.font28);
            }
        }
        Items item = inventoryItems.get(selectedItem);
        g.drawImage(item.getTexture(),invImageX,invImageY,invImageWidth,invImageHeight,null);
        Text.drawString(g, Integer.toString(item.getCount()), invCountX, invCountY, true, Color.white, Assets.font28);
    }
}
