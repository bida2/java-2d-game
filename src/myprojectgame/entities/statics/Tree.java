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
public class Tree extends StaticEntity{
    
   

   //Animations
    private Animation treeHurtOne;
    private Animation treeHurtTwo;
    private Animation treeOne;
    private Animation treeTwo;
    private Timer timer;
    
    
   public Tree(Handler handler,float x,float y,int uID) {
       super(handler,x,y,Tile.TILE_WIDTH,Tile.TILE_HEIGHT * 2,uID);
       bounds.x = 10;
       bounds.y = (int) (height / 2.3f);
       bounds.width = width - 20;
       bounds.height = (int) (height - height / 2.3f);
       treeOne = new Animation(500,Assets.treeOne);
       treeTwo = new Animation(500,Assets.treeTwo);
       treeHurtOne = new Animation(500,Assets.hurtTreeOne);
       treeHurtTwo = new Animation(500,Assets.hurtTreeTwo);
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
    
    // Drops a wooden log on entity death
    public void die() {
         handler.getWorld().getItemManager().addItem(Items.woodItemDrop.createNew((int) x, (int) y + 60));
    }
    

    @Override
    public void render(Graphics g) { 
            g.drawImage(getCurrentAnimationFrame(),(int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()) ,width,height,null); 
        }
    
    private BufferedImage getCurrentAnimationFrame() {  
         // Returns entity animation based on whether entity is hurt or not 
           if (isHurt) {  
          return treeHurtTwo.getCurrentFrame();
       } else {
           return treeTwo.getCurrentFrame();
       }
    }
    
     
}
