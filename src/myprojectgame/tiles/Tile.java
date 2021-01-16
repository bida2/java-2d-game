/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import myprojectgame.entities.statics.Tree;


// A class that creates and manages all of the tiles in the game 
// Can set whether a tile is a solid tile(impassable) or not
// Draws Tile at X and Y coordinates,specified by the programmer

public class Tile {
    
    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0); // we give every different tile a unique ID to that specific tile texture
    public static Tile dirtTile = new DirtTile(1);
    public static Tile rockTile = new RockTile(2);
    public static Tile sandTile = new SandTile(3);
    
    public static final int TILE_WIDTH = 64,TILE_HEIGHT = 64;
    
    protected BufferedImage texture;
    protected final int id;
    
    public Tile(BufferedImage texture,int id) {
        this.texture = texture;
        this.id = id;
        
        tiles[id] = this;
    }
    
    public void tick() {
        
    }
    
    public boolean isSolid() {
        return false;
    }
    
    public void render(Graphics g,int x, int y) {
        g.drawImage(texture, x, y, null);
    }
    
    public int getId() {
        return id;
    }
    
}
