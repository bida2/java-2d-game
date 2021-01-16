/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.entities.creature;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import myprojectgame.Handler;
import myprojectgame.entities.Entity;
import myprojectgame.entities.statics.BunchOfBags;
import myprojectgame.entities.statics.Rock;
import myprojectgame.entities.statics.Tree;
import myprojectgame.entities.statics.TreeLarge;
import myprojectgame.entities.statics.Vase;
import myprojectgame.gfx.Animation;
import myprojectgame.gfx.Assets;
import myprojectgame.items.Items;

/**
 *
 * @author my
 */



public class Monster extends Creature{
    // Creates our game's zombie
    
    private Timer timer;
    // Used to create a cooldown on the zombie's attack so it doesn't attack several times per second
    private long lastAttackTimer; 
    private long attackCooldown = 1000;
    private long attackTimer = attackCooldown;
    // starts the zombie in a certain animation state(left)
    private int animState = 2;
    
    // Animations for the zombie
    // up,down,left,right --> walking animations for the zombie
    // zombie_hurt_up,zombie_hurt_down,zombie_hurt_left,zombie_hurt_right --> hurt state animations
    private Animation up;
    private Animation down;
    private Animation left;
    private Animation right;
    private Animation zombie_hurt_up;
    private Animation zombie_hurt_down;
    private Animation zombie_hurt_left;
    private Animation zombie_hurt_right;
   
   // Creates our target and its coordinates ---> target is the player
    private Point target;
    private int targetX;
    private int targetY;
    // Gives the monster's coordinates
    private int currX = (int) (this.x);
    private int currY = (int) (this.y);
    private Point currCoor;
    // Sets monster health
    private final int DEFAULT_MONSTER_HEALTH = 5;
    
    
    public Monster(Handler handler, float x, float y, int width, int height, int uID) {
        super(handler,x, y,Creature.DEFAULT_CREATURE_WIDTH + 10,Creature.DEFAULT_CREATURE_HEIGHT + 10 ,uID);
        health = DEFAULT_MONSTER_HEALTH;
        speed = (float) (DEFAULT_SPEED - 1.5);  
        bounds.x = 33;
        bounds.y = 19;
        bounds.width = 35;
        bounds.height = 33;
      //  System.out.println("Monster Width: " + width);
      //  System.out.println("Monster Height: " + height);
        
      // Instantiate the animations and coordinates 
      // defined earlier
        up = new Animation(200,Assets.zombie_up);
        down = new Animation(200,Assets.zombie_down);
        left = new Animation(200,Assets.zombie_left);
        right = new Animation(200,Assets.zombie_right);
        
        zombie_hurt_up = new Animation(200,Assets.zombie_up_hurt);
        zombie_hurt_down = new Animation(200,Assets.zombie_down_hurt);
        zombie_hurt_left = new Animation(200,Assets.zombie_left_hurt);
        zombie_hurt_right = new Animation(200,Assets.zombie_right_hurt);
        
      
        currCoor = new Point(currX,currY);
        target = new Point(targetX,targetY);
        
        timer = new Timer();
    }
    
     public void checkAttacks() {
        // Method,implementing attackCooldown
        // Cannot attack if the attackTimer < attackCooldown
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown) {
            return;
        } 
        
        // If the Player's inventory is active, do not attack
        if (handler.getWorld().getEntityManager().getPlayer().getInventory().isActive()) {
            return;
        }
       
        // Creates a Rectangle object that acts as our zombie's attack range
        Rectangle colBounds = getCollisionBounds(0,0); // Gets Collision Box for our Monster Entity
        Rectangle aRectangle = new Rectangle();
        int aRectSize = 20;
        aRectangle.width = aRectSize + 5;
        aRectangle.height = aRectSize;
        
        /* ********************************************* */
     
        // Attack in the direction the zombie is facing in.
        if (animState == 1) {
            aRectangle.x = colBounds.x + colBounds.width; // attack right
            aRectangle.y = colBounds.y + colBounds.height/2 - aRectSize / 2; 
        }
        else if (animState == 2) {
            aRectangle.x = colBounds.x - aRectSize; // attacks left
            aRectangle.y = colBounds.y + colBounds.height / 2 - aRectSize / 2;
        } else if (animState == 3) {
            aRectangle.x = colBounds.x + colBounds.width / 2 - aRectSize / 2; // attack down
            aRectangle.y = colBounds.y + colBounds.height;
        } else if (animState == 4) {
            aRectangle.x = colBounds.x + colBounds.width / 2 - aRectSize / 2; // attack up
            aRectangle.y = colBounds.y - aRectSize;
        } else {
            return;
        }
        
        /* ******************************************** */ 
        
        
        attackTimer = 0;  
        
        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this) || e instanceof Monster) {
               // Checks if the entity is this entity 
               // or if the entity is another Monster instance
                continue; 
            }
            if (e.getCollisionBounds(0,0).intersects(aRectangle)) {
                // Hurt only intersecting with attack rectangle entity
                e.isHurt = true;
                e.hurt(2);
                return;
            }
        }
       
       
    }

    
     
     
    
    public void getInput() {
        xMove = 0;
        yMove = 0;
        
        // Pauses movement of mobs (pauses game) when player inventory is active on screen
        if (handler.getWorld().getEntityManager().getPlayer().getInventory().isActive()) {
            return;
        }

        // Four animation states
        // State 1 : go right
        // State 2 : go left
        // State 3 : go down
        // State 4 : go up

        if (currCoor.x < target.x && currCoor.x < target.x - 1) {
            animState = 1;
            xMove = speed; // go right
        } else if (currCoor.x > target.x && currCoor.x > target.x + 1) {
            animState = 2;
            xMove = -speed; // go left
        }
        
        if (currCoor.y < target.y && currCoor.y < target.y - 1) {
            animState = 3;
            yMove = speed; // go up
        } else if (currCoor.y  > target.y && currCoor.y > target.y + 1) {
            animState = 4;
            yMove = -speed; // go down
        }
        System.out.println("Monster X: " + currCoor.x);
        System.out.println("Monster Y: " + currCoor.y);
        System.out.println("Player X: " + target.x);
        System.out.println("Player Y: " + target.y); 
        
        
    }
    
    // Updates monster coordinates in the world
    public void updateMonsterXY() {
        currX = (int) (this.x);
        currY = (int) (this.y);
        currCoor.x = currX;
        currCoor.y = currY;
    }
    
    // Updates target(Player's) coordinates in the world so our zombie
    // never loses track of the player
    public void updateTargetXY() {
        targetX = (int) (handler.getWorld().getEntityManager().getPlayer().x);
        targetY = (int) (handler.getWorld().getEntityManager().getPlayer().y);
        target.x = targetX;
        target.y = targetY;
       // System.out.println("X: " + targetX);
      //  System.out.println("Y: " + targetY);
    }
   
    // Check if the zombie is being hurt and return the proper animation
    public void checkHurtState() {
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
    
   
    // Implement the concept of the game loop for the zombie
    // so it can return proper animations and attack properly
    @Override
    public void tick() {
        updateMonsterXY();
        updateTargetXY();
        checkHurtState();
        up.tick();
        down.tick();
        left.tick();
        right.tick();
        zombie_hurt_up.tick();
        zombie_hurt_down.tick();
        zombie_hurt_left.tick();
        zombie_hurt_right.tick();
        getInput();
        move();
        checkAttacks();
    }
    
    private BufferedImage determineAnimation() {
        switch (animState) {
            case 1: 
                if (isHurt) {
                    return zombie_hurt_right.getCurrentFrame();
                } else {
                    return right.getCurrentFrame(); 
                }
            case 2: 
                if (isHurt) {
                    return zombie_hurt_left.getCurrentFrame(); 
                } else {
                    return left.getCurrentFrame();
                }
            case 3: 
                if (isHurt) {
                    return zombie_hurt_down.getCurrentFrame();
                } else {
                    return down.getCurrentFrame();
                }
            case 4:
                if (isHurt) {
                    return zombie_hurt_up.getCurrentFrame();
                } else {
                    return up.getCurrentFrame();
                }
        }
        
        return null;
    }
    
    // Draw the entity on the screen 

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(),(int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()),(width),(height), null);
    }
    
    private BufferedImage getCurrentAnimationFrame() {
        return determineAnimation();
    }
    
    // Drop an item on entity death --> in this case,Zombie Flesh which is usable
    // Press Enter with Zombie Flesh selected in Player Inventory
    // When Zombie Flesh is consumed,the Player loses 5 health points
    @Override
    public void die() {
         handler.getWorld().getItemManager().addItem(Items.zombieDrop.createNew((int) x, (int) y));
    }
    
}
