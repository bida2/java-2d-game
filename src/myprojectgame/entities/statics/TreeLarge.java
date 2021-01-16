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
public class TreeLarge extends StaticEntity{
    
   

   // Animations
    private Animation treeLargeHurtA;
    private Animation treeLargeA;
    private Timer timer;
    
    
   public TreeLarge(Handler handler,float x,float y,int uID) {
       super(handler,x,y,Tile.TILE_WIDTH * 2,Tile.TILE_HEIGHT * 4,uID);
       bounds.x = 22;
       bounds.y = (int) (height / 1.1f);
       bounds.width = width - 34;
       bounds.height = (int) (height - height / 1.1f);
       treeLargeA = new Animation(500,Assets.treeLarge);
       treeLargeHurtA = new Animation(500,Assets.hurtTreeLarge);
       timer = new Timer();
   }

 
    @Override
    public void tick() {
    
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
    // Drops a wooden log on death
    public void die() {
        handler.getWorld().getItemManager().addItem(Items.woodItemDrop.createNew((int) x + 30, (int) (y + (y/2) - 45) ));
    }
    

    @Override
    public void render(Graphics g) { 
            g.drawImage(getCurrentAnimationFrame(),(int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()) ,width,height,null); 
        }
    
    private BufferedImage getCurrentAnimationFrame() {   
         // Returns entity animation based on whether entity is hurt or not 
           if (isHurt) {  
          return treeLargeHurtA.getCurrentFrame();
       } else {
           return treeLargeA.getCurrentFrame();
       }
    }
    
     
}
