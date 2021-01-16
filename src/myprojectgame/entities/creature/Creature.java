/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.entities.creature;

import java.awt.Graphics;
import myprojectgame.Game;
import myprojectgame.Handler;
import myprojectgame.entities.Entity;
import myprojectgame.tiles.Tile;

/**
 *
 * @author my
 */
public abstract class Creature extends Entity {
    // Class that extends our Entity class
    // and is mainly used for moving enemies in the game like our zombie
   // Specifies moving speed (movement along X and Y axis)
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 50,DEFAULT_CREATURE_HEIGHT = 50;
    
    
    protected float speed;
    
    // Variables used in moving the creature
    // depending on which direction that creature is moving in
    protected float xMove,yMove;
    
    public Creature(Handler handler,float x, float y , int width, int height,int uID) {
        super(handler,x, y, width, height,uID);
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }
    
    public void move() {
        if (!checkEntityCollisions(xMove,0f)) 
                    moveX();
        if (!checkEntityCollisions(0f,yMove))
                    moveY();
        
    }
    
    public void moveX() {
       if (xMove > 0) { // Moving right
           int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;
           if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
               // y + bounds.y ---> upper corner of bounding box
               // y + bounds.y + bounds.height ----> lower corner of bounding box
               x += xMove;    
           } else {
               x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
           }
       } else if (xMove < 0) {
           // Moving left
           int tx = (int) (x + bounds.x + xMove) / Tile.TILE_WIDTH;
           if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
               // y + bounds.y ---> upper corner of bounding box
               // y + bounds.y + bounds.height ----> lower corner of bounding box
               x += xMove; 
       } else {
               x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
           }
    }
  }  
    
    public void moveY() {
       if (yMove < 0) { // Going up
           int ty = (int) (y + bounds.y + yMove) / Tile.TILE_HEIGHT;
           
           if (!collisionWithTile( (int) (x + bounds.x) / Tile.TILE_WIDTH, ty) && !collisionWithTile( (int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty ) ) {
               y += yMove;
           } else {
               y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
           }
           
       } else if (yMove > 0) { // Down
           int ty = (int) (y + bounds.y + bounds.height + yMove) / Tile.TILE_HEIGHT;
           if (!collisionWithTile( (int) (x + bounds.x) / Tile.TILE_WIDTH, ty) && !collisionWithTile( (int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty ) ) {
               y += yMove;
           } else {
               y = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
           }
       }
    }
    
    public float getXMove() {
        return xMove;
    }
    
    protected boolean collisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x,y).isSolid();
    }
    
    public void setXMove(float xMove) {
        this.xMove = xMove;
    }
    
    public float getYMove() {
        return yMove;
    }
    
    public void setYMove(float yMove) {
        this.yMove = yMove;
    }
    
    
    public float getSpeed() {
        return speed;
    }
    
    public void setSpeed(float speed) {
        this.speed = speed;
    }
    
    @Override
    public void tick() {
       
    }

    @Override
    public void render(Graphics g) {
        
    }
    
}
