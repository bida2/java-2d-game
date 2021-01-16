/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import myprojectgame.Handler;
import myprojectgame.entities.creature.Monster;
import myprojectgame.entities.creature.Player;
import myprojectgame.entities.statics.Tree;

/**
 *
 * @author my
 */
public class EntityManager {
    
    // Manages all of the entities in the game
    // Provides various methods for working with said entities
    // Examples: renderSort method() -> which of these two entities should be drawn first
    // If entity a is higher up in the game world than entity b 
    // entity a is going to be rendered before b
    // Otherwise, render entity b first
    
    private Handler handler;
    private Player player;
    public ArrayList<Entity> entities;
    private Comparator<Entity> renderSort = new Comparator<Entity>() {
        @Override
        public int compare(Entity a, Entity b) {
            if (a.getY() + a.getHeight() < b.getY() + b.getHeight()) {
                return -1; // a should be rendered before b
            } else {
            return 1; // b should be rendered before a
            }
        }  
        
    };
     
    public EntityManager(Handler handler,Player player) {
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<Entity>();
        addEntity(player);
      }
    
    public void tick() {
        Iterator<Entity> it = entities.iterator();
        while (it.hasNext()) {
            Entity e = it.next(); // gets entity at index of i
            e.tick();
            if (!e.isActive()) {
                it.remove();
            }
        }
        entities.sort(renderSort);
    }
    
    public void render(Graphics g) {
        for (Entity e : entities) {
            e.render(g);
        }
        player.postRender(g);
    }
    
    public void addEntity(Entity e) {
        entities.add(e);
    }
    
      public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
   

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
    
}
