/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.entities.statics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import myprojectgame.Handler;
import myprojectgame.entities.Entity;
import myprojectgame.entities.creature.Monster;
import myprojectgame.entities.creature.Player;
import myprojectgame.gfx.Animation;
import myprojectgame.gfx.Assets;
import myprojectgame.items.Items;
import myprojectgame.tiles.Tile;

/**
 *
 * @author my
 */
public class Rock extends StaticEntity{
    // Animations
    private Animation rockHurtTwo;
    private Animation rockTwo;
    private Timer timer;

    public Rock(Handler handler, int x, int y,int uID) {
        super(handler,x,y,Tile.TILE_WIDTH * 2,Tile.TILE_HEIGHT * 2,uID);
       bounds.x = 0;
       bounds.y = (int) (height / 2.3f);
       bounds.width = width + 5;
       bounds.height = (int) (height - height / 2.3f);  
       rockTwo = new Animation(500,Assets.rockTwo);
       rockHurtTwo = new Animation(500,Assets.hurtRockTwo);
       timer = new Timer();
    }

    
    public void checkEntityHurt() {
               if (isHurt) {
            getCurrentAnimationFrame();
           
           timer.schedule(new TimerTask(){
               @Override
               public void run() {
                   isHurt = false;
               }
           },150);
       }
    }
    
    @Override
    public void tick() {
        checkEntityHurt();
    }
    
    // Drops a small rock on entity death
    public void die() {
        handler.getWorld().getItemManager().addItem(Items.rockItemDrop.createNew((int) x + 40, (int) y + 60));
    }
    

    @Override
    public void render(Graphics g) { 
            g.drawImage(getCurrentAnimationFrame(),(int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()) ,width,height,null); 
        }
    
    private BufferedImage getCurrentAnimationFrame() { 
        // Returns entity animation based on whether entity is hurt or not 
       if (isHurt) {  
          return rockHurtTwo.getCurrentFrame();
       } else {
           return rockTwo.getCurrentFrame();
       }
       
    }
    
     
}
