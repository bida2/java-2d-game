/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.gfx;

import myprojectgame.Game;
import myprojectgame.Handler;
import myprojectgame.entities.Entity;
import myprojectgame.tiles.Tile;

// Creates the game camera,responsible for following the player around(centering on the player)
// and not rendering past the borders of the game world --> through the checkBlankSpace() method

public class GameCamera {
    private Handler handler;
    private float xOffset,yOffset; // offsets from the end of the game world based on X and Y axis
    
    
    public GameCamera(Handler handler,float xOffset,float yOffset) {
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
    
    public void checkBlankSpace() {
        if (xOffset < 0) {
            xOffset = 0;
        } else if (xOffset > handler.getWorld().getHeight() * Tile.TILE_WIDTH - handler.getWidth()) {
            xOffset = (int) (handler.getWorld().getHeight() * Tile.TILE_WIDTH - handler.getWidth());
        }
        if (yOffset < 0) {
            yOffset = 0;
        } else if (yOffset > handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeight()) {
            yOffset = handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeight();
        } 
    }
    
    public void centerOnEntity(Entity e) {
        xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2; // dividing by two centers the character on the screen
        yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2; // as well as the camera on the character
        checkBlankSpace(); // check if blank space is going to show and reset to a state where it doesnt
    }
    
    public void move(float xAmount,float yAmount) {
        xOffset += xAmount;
        yOffset += yAmount;
        checkBlankSpace(); // check if blank space is going to show and reset to a state where it doesnt
    }
    
     public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }
    
    
}
