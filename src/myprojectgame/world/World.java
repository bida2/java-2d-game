/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.world;

import java.awt.Graphics;
import java.io.File;
import myprojectgame.Game;
import myprojectgame.Handler;
import myprojectgame.entities.Entity;
import myprojectgame.entities.EntityManager;
import myprojectgame.entities.creature.Monster;
import myprojectgame.entities.creature.Player;
import myprojectgame.entities.statics.BunchOfBags;
import myprojectgame.entities.statics.Rock;
import myprojectgame.entities.statics.Tree;
import myprojectgame.entities.statics.TreeLarge;
import myprojectgame.entities.statics.Vase;
import myprojectgame.items.ItemManager;
import myprojectgame.tiles.Tile;
import myprojectgame.utils.Utils;


// Contains the current iteration of the game world
// The game world must be loaded from a text file first ( found in res/worlds)
// After it is loaded,we must add entities into it through the entityManager variable (of reference type EntityManager)
// we can add entities as showcased below
// we can remove them in the same way entityManager.remove(<get entity place in ArrayList>)

public class World {
    private Handler handler;
    private int width,height;
    private int spawnPlayerX,spawnPlayerY;

    
    private int[][] tiles;
    // Entities 
    private EntityManager entityManager;
    // Items 
    private ItemManager itemManager;
    
    public World(Handler handler,String path) {
        this.handler = handler;
        entityManager = new EntityManager(handler,new Player(handler,100,100,1) {});
        itemManager = new ItemManager(handler);
        // ************************************************* //
        // For small world (10x10) (world2)
        entityManager.addEntity(new Tree(handler,55,220,15));
        entityManager.addEntity(new Tree(handler,150,220,15));
        entityManager.addEntity(new Tree(handler,100,220,15));
        entityManager.addEntity(new Tree(handler,200,220,15));
        entityManager.addEntity(new BunchOfBags(handler,200,380,6));
        entityManager.addEntity(new BunchOfBags(handler,150,380,6));
         entityManager.addEntity(new BunchOfBags(handler,100,380,6));
         entityManager.addEntity(new BunchOfBags(handler,200,340,6));
        entityManager.addEntity(new Monster(handler,250,250,50,50,7));
         entityManager.addEntity(new Vase(handler,350,350,5));
         entityManager.addEntity(new Vase(handler,405,350,5));
         entityManager.addEntity(new BunchOfBags(handler,405,380,6));
         entityManager.addEntity(new TreeLarge(handler,400,0,34));
         entityManager.addEntity(new Rock(handler,320,60,377));
         entityManager.addEntity(new Rock(handler,200,60,377));
        // entityManager.addEntity(new Monster(handler,295,175,50,50,7));
        // ************************************************** //
        // For default world (14x14) (world1)
        
        
        // ************************************************* //
        
        // ************************************************* // 
        // For large world (20x20) (world1)
        
        // ************************************************ //
//        entityManager.addEntity(new Rock(handler,400,450,2));
//        entityManager.addEntity(new TreeLarge(handler,600,550,4));
//        entityManager.addEntity(new Vase(handler,350,350,5));
        //  entityManager.addEntity(new BunchOfBags(handler,55,380,6));
        //entityManager.addEntity(new Monster(handler,150,400,32,32,7));
//        entityManager.addEntity(new Monster(handler,600,450,32,32,44));
//        entityManager.addEntity(new Rock(handler,500,550,8));
       
        loadWorld(path); 
        
        entityManager.getPlayer().setX(spawnPlayerX);
        entityManager.getPlayer().setY(spawnPlayerY);
        
       }
    
    public void tick() {
        itemManager.tick();
        entityManager.tick();
    }
    
    public void render(Graphics g) {
       // Limit what tiles it renders(don't render tiles not on screen at the moment)
       int xStart = (int) Math.max(0, handler.getCamera().getxOffset() / Tile.TILE_WIDTH);
       int xEnd = (int) Math.min(width, (handler.getCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
       int yStart = (int) Math.max(0, handler.getCamera().getyOffset() / Tile.TILE_HEIGHT);
       int yEnd = (int) Math.min(height, (handler.getCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);
       
         for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                    getTile(x,y).render(g,(int) (x * Tile.TILE_WIDTH - handler.getCamera().getxOffset()),(int) (y * Tile.TILE_HEIGHT - handler.getCamera().getyOffset()));
             }
         }
         // Items 
         itemManager.render(g);
         // Entities 
         entityManager.render(g);
    }
    
    public Tile getTile(int x, int y) {
       if (x < 0 || y < 0 || x >= width || y >= height) {
           return Tile.grassTile;
       }
        
      Tile t = Tile.tiles[tiles[x][y]];  
        if (t == null) {
            return Tile.dirtTile;
        }
        return t;
    }
    
    // Load world size from text file , parse as int
    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnPlayerX = Utils.parseInt(tokens[2]);
        spawnPlayerY = Utils.parseInt(tokens[3]);
       
        
        // actual world data 
        tiles = new int[width][height];
        for (int y = 0; y < height; y++ ){
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]); // +4 because we have set the first four integers in our file 
            }
        }
        
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public EntityManager getEntityManager() {
        return entityManager;
    }

    
}
