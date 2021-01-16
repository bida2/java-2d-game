/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame;

import myprojectgame.gfx.GameCamera;
import myprojectgame.input.KeyManager;
import myprojectgame.input.MouseManager;
import myprojectgame.world.World;

/**
 *
 * @author my
 */
public class Handler {
    
    // Utility class to provide us with various important 
    // classes in our game like the Game itself,the camera,the key and mouse managers(responsible for input)
    // height and width of the game window and setting the game world
    
    private Game game;
    private World world;
  
    
    public Handler(Game game) {
        this.game = game;
    }
    
    public GameCamera getCamera() {
        return game.getCamera();
    }
    
    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }
    
    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }
    
    public int getWidth() {
        return game.getWidthWindow();
    }
    
    public int getHeight() {
        return game.getHeightWindow();
    }
    
      public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
