/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.states;

import java.awt.Graphics;
import myprojectgame.Game;
import myprojectgame.Handler;
import myprojectgame.entities.creature.Player;
import myprojectgame.entities.statics.Tree;
import myprojectgame.gfx.Assets;
import static myprojectgame.gfx.Assets.player_down;
import myprojectgame.tiles.Tile;
import myprojectgame.world.World;

/**
 *
 * @author my
 */
public class GameState extends State{
    // The state our game world is in.
    // Helps us load the game world from a text file,as that is the current world loading system our game uses
    private World newWorld;
    
    public GameState(Handler handler) {
        super(handler);
         newWorld = new World(handler,"res/worlds/world2.txt");
         handler.setWorld(newWorld); 
    }
    
    @Override
    public void tick() {
        newWorld.tick();
    }

    @Override
    public void render(Graphics g) {
       newWorld.render(g);
    }
    
}
