/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import myprojectgame.Handler;
import myprojectgame.gfx.Assets;

/**
 *
 * @author my
 */
public class Items {
        // Up to 256 unique items in the game for now - support for more when the time comes
        // Different items already instantiated - rockItemDrop from Rock entity, woodItemDrop from Tree and TreeLarge entities, etc.
        public static Items[] items = new Items[256];
        public static Items rockItemDrop = new Items(Assets.rock_drop,"Rock",0,false);
        public static Items woodItemDrop = new Items(Assets.tree_drop,"Wood",1,false);
        public static Items vaseItemDrop = new Items(Assets.vase_bags_drop,"Sand",2,false);
        public static Items sandBagsItemDrop = new Items(Assets.vase_bags_drop,"Sand",3,false);
        public static Items zombieDrop = new Items(Assets.zombie_drop,"Zombie Flesh",4,true);
    // Class
    
        public static final int ITEM_WIDTH = 32,ITEM_HEIGHT = 32;
        
        protected Handler handler;
        protected BufferedImage texture;
        protected String name;
        protected final int id;
        protected boolean usable;
        protected boolean pickedUp = false;
        
        protected Rectangle bounds;
        
        
        protected int x,y,count;
        
        public Items(BufferedImage texture,String name,int id,boolean usable) {
            this.texture = texture;
            this.name = name;
            this.id = id;
            this.usable = usable;
            count = 1; // Used in player inventory, shows how many instances of the item we are picking up
            bounds = new Rectangle(x,y,ITEM_WIDTH,ITEM_HEIGHT);
            items[id] = this;
        }
        
        public void tick() {
            if (handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f,0f).intersects(bounds)) {
                pickedUp = true;
                handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
            }
        }

    public boolean isPickedUp() {
        return pickedUp;
    }
    
    public boolean isUsable() {
            return usable;
        }    
    
        public void render(Graphics g) {
            
            if (handler == null) {
                return;
            }
            
            render(g,(int) (x - handler.getCamera().getxOffset()),(int) (y - handler.getCamera().getyOffset()));
        }
        
        public void render(Graphics g,int x,int y) {
            g.drawImage(texture,x,y,ITEM_WIDTH,ITEM_HEIGHT,null);
        }

        public Items createNew(int count) {
            Items i = new Items(texture,name,id,usable);
            i.setPickedUp(true);
            i.setCount(count);
            return i;
        }
        
        public Items createNew(int x,int y) {
            Items i = new Items(texture,name,id,usable);
            i.setPosition(x,y);
            return i;
        }
        
        public void setPosition(int x,int y) {
            this.x = x;
            this.y = y;
            bounds.x = x;
            bounds.y = y;
        }
        
        // Getters and Setters
        
        public int getID() {
            return id;
        }
        public void setPickedUp(boolean pickedUp) {
            this.pickedUp = pickedUp;
        }
        
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
        
}
