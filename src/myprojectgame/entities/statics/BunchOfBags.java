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
import static myprojectgame.gfx.Assets.hurtVase;
import myprojectgame.items.Items;
import myprojectgame.tiles.Tile;

/**
 *
 * @author my
 */
public class BunchOfBags extends StaticEntity{
    
   

    // Animations for the entity
    private Animation bagsHurt;
    private Animation bagsBunch;
    private Timer timer;
    
    
   public BunchOfBags(Handler handler,float x,float y,int uID) {
       super(handler,x,y,Tile.TILE_WIDTH ,Tile.TILE_HEIGHT,uID);
       bounds.x = 0;
       bounds.y = (int) (height / 2.3f);
       bounds.width = width;
       bounds.height = (int) (height - height / 2.3f);
       bagsBunch = new Animation(500,Assets.bags);
       bagsHurt = new Animation(500,Assets.hurtBags);
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
    
    // Drops sand on entity death
    public void die() {
       handler.getWorld().getItemManager().addItem(Items.sandBagsItemDrop.createNew((int) x, (int) y ));
    }
    

    @Override
    public void render(Graphics g) { 
        g.drawImage(getCurrentAnimationFrame(),(int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()) ,bounds.width,height,null); 
        }
    
    private BufferedImage getCurrentAnimationFrame() {   
        // Returns entity animation based on whether entity is hurt or not
           if (isHurt) {  
          return bagsHurt.getCurrentFrame();
       } else {
           return bagsBunch.getCurrentFrame();
       }
    }
    
     
}
