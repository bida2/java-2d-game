/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;
import myprojectgame.Game;
import myprojectgame.Handler;
import myprojectgame.entities.creature.Player;

/**
 *
 * @author my
 */
public abstract class Entity {
    // Every entity in the game extends this class - it contains the basic components
    // and activies an entity in the game must perform
    // An entity must be able to move (getInput() method), have health points(health and DEFAULT_HEALTH variables) and 
    // it must be active in the game's world to be able to move or take damage
     protected Handler handler;
     public int health;
     public static final int DEFAULT_HEALTH = 10;
     protected boolean active = true; // if it set to false,it removes the entity it is checked on from the game
     public int uID; // Unique ID of an entity
     public boolean isHurt = false; // Checks if the entity has taken damage recently. If true - returns a hurt animation on the entity
     
    
    

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
     public float x;
     public float y;
     protected int width,height;
     // Hit detection rectangle
     protected Rectangle bounds;
     
     public Entity(Handler handler,float x , float y, int width, int height,int uID) {
         this.handler = handler;
         this.uID = uID;
         this.x = x;
         this.y = y;
         this.width = width;
         this.height = height;
         health = DEFAULT_HEALTH;
         
         bounds = new Rectangle(0,0, width,height);
         
     }
    
     
     public abstract void tick(); // every entity must update its variables' values (implement the concept of the game loop)
     public abstract void render(Graphics g);
     public abstract void die();  // it is abstract so that every entity must implement its own die method
     public void hurt(int amount) {
         // Method called when an entity in our game is hurt
         // Removes entity health points,equal to the amount parameter
         // and also checks if it should remove entity from the game world
         // if the entity has ran out of health points 
         health -= amount;
         if (health <= 0) {
             active = false;
             die();
         }    
     }
     
     // Checks collision of one entity with another entity. Collision is not being able to walk
     // into one entity,based on its bounds.
     // Think of it as not being able to walk into the inside of a tree or another person
     public boolean checkEntityCollisions(float xOffset,float yOffset) {
         for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
             if (e.equals(this)) {
                 continue; // makes it so entity doesnt check against collisions from itself
             }
             if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset,yOffset))) {
                 return true;
             } 
         }
         return false;
     }
     
     public Rectangle getCollisionBounds(float xOffset,float yOffset) {
         return new Rectangle( (int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height );
     }

     // Getters and setters for various elements like unique ID of entity 
     // X and Y coordinates in the world of the entity 
     // and entity width and height
    public int getuID() {
        return uID;
    }
     
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
}
