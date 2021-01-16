package myprojectgame.entities.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import myprojectgame.Game;
import myprojectgame.Handler;
import myprojectgame.entities.Entity;
import myprojectgame.entities.statics.BunchOfBags;
import myprojectgame.entities.statics.Rock;
import myprojectgame.entities.statics.Tree;
import myprojectgame.entities.statics.TreeLarge;
import myprojectgame.entities.statics.Vase;
import myprojectgame.gfx.Animation;
import myprojectgame.gfx.Assets;
import myprojectgame.input.KeyManager;
import static myprojectgame.input.KeyManager.keys;
import myprojectgame.inventory.Inventory;
import myprojectgame.states.DeathState;
import myprojectgame.states.State;


/**
 *
 * @author my
 */
public abstract class Player extends Creature {
    
    // Creates the player character that we can control
    // This class contains the player animations,complete with idle states
    // idle states are animations themselves that present the player as non-moving 
    private Timer timer;
    private Rectangle ar;
    boolean attackingUp = false,attackingDown = false,attackingLeft = false,attackingRight = false;
    
    private int animState;
    
    // Animations ---> while moving
    private Animation down;
    private Animation up;
    private Animation left;
    private Animation right;
    
    // Animations --> idle
    private Animation down_idle;
    private Animation up_idle;
    private Animation left_idle;
    private Animation right_idle;
    
     // Animations ---> while moving
    private Animation down_hurt;
    private Animation up_hurt;
    private Animation left_hurt;
    private Animation right_hurt;
    
    // Animations --> idle
    private Animation down_idle_hurt;
    private Animation up_idle_hurt;
    private Animation left_idle_hurt;
    private Animation right_idle_hurt;
    
    // Animations --> attacking
    private Animation attackUp;
    private Animation attackDown;
    private Animation attackLeft;
    private Animation attackRight;
    
    // Inventory
    private Inventory inventory;
    
    
    // Last pressed int variable --> start in down idle state
    public int lastPressed = KeyEvent.VK_S;
    
    // Attack timer
    public static long lastAttackTimer, attackCooldown = 800,attackTimer = attackCooldown; // 800 in miliseconds ---> 1000 miliseconds == 1 seconds
    public static long lastAttackMovementTimer, attackMovementCooldown = 400, attackTimerMovement = attackMovementCooldown;
    public static boolean attacking = false;
     public static boolean isZombie = false;
    public static int currID = 0;
    
    
    
    
    
    
    public Player(Handler handler,float x, float y,int uID) {
        super(handler,x, y,Creature.DEFAULT_CREATURE_WIDTH - 5,Creature.DEFAULT_CREATURE_HEIGHT - 5,uID);
        bounds.x = 16;
        bounds.y = 14;
        bounds.width = 25;
        bounds.height = 43;
        health = 10;
        // Animations --> while moving instantiation 
        down = new Animation(200,Assets.player_down);
        left = new Animation(200,Assets.player_left);
        right = new Animation(200,Assets.player_right);
        up = new Animation(200,Assets.player_up);
       // System.out.println("Player Width: " + width);
       // System.out.println("Player Height: " + height);
        
        // Animations --> while idle instantiation
        down_idle= new Animation(500,Assets.player_down_idle);
        right_idle= new Animation(500,Assets.player_right_idle);
        left_idle= new Animation(500,Assets.player_left_idle);
        up_idle= new Animation(500,Assets.player_up_idle);
        
        // Attacking
        attackUp = new Animation(1000,Assets.player_attack_up);
        attackDown = new Animation(1000,Assets.player_attack_down);
        attackRight = new Animation(1000,Assets.player_attack_right);
        attackLeft = new Animation(1000,Assets.player_attack_left);
        
        // Animations --> while moving instantiation 
        down_hurt = new Animation(300,Assets.player_down_hurt);
        left_hurt = new Animation(300,Assets.player_left_hurt);
        right_hurt = new Animation(300,Assets.player_right_hurt);
        up_hurt = new Animation(300,Assets.player_up_hurt);
        
        // Animations --> while idle instantiation
        down_idle_hurt = new Animation(500,Assets.player_down_idle_hurt);
        right_idle_hurt = new Animation(500,Assets.player_right_idle_hurt);
        left_idle_hurt = new Animation(500,Assets.player_left_idle_hurt);
        up_idle_hurt = new Animation(500,Assets.player_up_idle_hurt);
       
        
        // Inventory creation
        inventory = new Inventory(handler);
        
        this.timer = new Timer();
        this.ar = new Rectangle();
    }
    
    private void checkHurtState() {
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
        checkHurtState();
        down.tick();
        up.tick();
        right.tick();
        left.tick();
        down_idle.tick();
        up_idle.tick();
        right_idle.tick();
        left_idle.tick();
        getInput();
        move();
        handler.getCamera().centerOnEntity(this);
        //Attacks
        checkAttacks();
        // Inventory ticking
        inventory.tick();
    }
    
    public void getInstance() {
        
        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this)) {
               // Check if Entity is player 
               // So the player doesnt hurt himself when attacking
                continue; 
            }
            if (e.getCollisionBounds(0,0).intersects(ar)) {
                e.isHurt = true;
                e.hurt(1);
                return;
            }
            
        }
         
    }
    
    // Special method to render player inventory
    // on top of everything else in the game
    public void postRender(Graphics g) {
         inventory.render(g);
    }
    
    public void checkAttacks() {
        
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown) {
            return;
        } 
        
        
        // If inventory is active,player cannot attack (or move)
        if (inventory.isActive()) {
            return;
        }
        
         Rectangle colBounds = getCollisionBounds(0,0); // Gets Collision Box for our Player Entity
        Rectangle aRectangle = new Rectangle();
        int aRectSize = 20;
        aRectangle.width = aRectSize;
        aRectangle.height = aRectSize;
        
        ar.height = aRectangle.height;
        ar.width = aRectangle.width;
        
        if (handler.getKeyManager().aUp) {
            aRectangle.x = colBounds.x + colBounds.width / 2 - aRectSize / 2;
            aRectangle.y = colBounds.y - aRectSize;
            ar.x = colBounds.x + colBounds.width / 2 - aRectSize / 2;
            ar.y = colBounds.y - aRectSize;
           attackingUp = true;
            
        } else if (handler.getKeyManager().aDown) {
            aRectangle.x = colBounds.x + colBounds.width / 2 - aRectSize / 2;
            aRectangle.y = colBounds.y + colBounds.height;
            ar.x = colBounds.x + colBounds.width / 2 - aRectSize / 2;
            ar.y = colBounds.y + colBounds.height;
            attackingDown = true;
        } else if (handler.getKeyManager().aLeft) {
            aRectangle.x = colBounds.x - aRectSize;
            aRectangle.y = colBounds.y + colBounds.height / 2 - aRectSize / 2;
            ar.x = colBounds.x - aRectSize;
            ar.y = colBounds.y + colBounds.height / 2 - aRectSize / 2;
            attackingLeft = true;
        } else if (handler.getKeyManager().aRight) {
            aRectangle.x = colBounds.x + colBounds.width;
            aRectangle.y = colBounds.y + colBounds.height/2 - aRectSize / 2;
            ar.x = colBounds.x + colBounds.width;
            ar.y = colBounds.y + colBounds.height/2 - aRectSize / 2;
            attackingRight = true;
        } else {
            return;
        }
        
        attackTimer = 0;  
        attackTimerMovement = 0;
        
        getInstance();
       
       
    }
    
    // Play a different audio clip on player death
    // A sad piano theme is played on player death
    @Override
    public void die() {
        try {
            handler.getGame().getAudioPlayer().stop();
            handler.getGame().getAudioPlayer().filePath = "res/sound/death_theme.wav";
            handler.getGame().getAudioPlayer().resetAudioStream();
            handler.getGame().getAudioPlayer().play();
        } catch (Exception ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        isHurt = false;
        handler.getMouseManager().setUIManager(DeathState.death);
        State.setState(handler.getGame().deathState);
    }
    
    private void getInput() {
        xMove = 0;
        yMove = 0;
        
        if (inventory.isActive()) {
            return;
        }
        
        attackTimerMovement += System.currentTimeMillis() - lastAttackMovementTimer;
        lastAttackMovementTimer = System.currentTimeMillis();    
        if (attackTimerMovement < attackMovementCooldown) {
            attacking = true;
            return;
        }
        
        if (handler.getKeyManager().up) {
            yMove = -speed;
            lastPressed = KeyEvent.VK_W;
            
        }
        if (handler.getKeyManager().down) {
            yMove = speed;
            lastPressed = KeyEvent.VK_S;
        }
        if (handler.getKeyManager().left) {
            xMove = -speed;
            lastPressed = KeyEvent.VK_A;
        }
        if (handler.getKeyManager().right) {
            xMove = speed;
            lastPressed = KeyEvent.VK_D;
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void render(Graphics g) {
        // Renders the Player's sword (attack animation) to the screen based on where he's attacking 
        if (attackingUp && animState == 3) {
            g.drawImage(attackUp.getCurrentFrame(), (int) (ar.x - handler.getCamera().getxOffset() - 10),
  (int) (ar.y - handler.getCamera().getyOffset() - 15), null);
           timer.schedule(new TimerTask(){
                @Override
                public void run() {
                    attackingUp = false;
                }
            },300);
        } else if (attackingDown && animState == 4) {
            g.drawImage(attackDown.getCurrentFrame(), (int) (ar.x - handler.getCamera().getxOffset() - 10),
  (int) (ar.y - handler.getCamera().getyOffset() - 10 ), null);
           timer.schedule(new TimerTask(){
                @Override
                public void run() {
                    attackingDown = false;
                }
            },300);
        } else if (attackingLeft && animState == 1) {
            g.drawImage(attackLeft.getCurrentFrame(), (int) (ar.x - handler.getCamera().getxOffset() - 15),
  (int) (ar.y - handler.getCamera().getyOffset() - 10 ), null);
           timer.schedule(new TimerTask(){
                @Override
                public void run() {
                    attackingLeft = false;
                }
            },300);
        } else if (attackingRight && animState == 2) {
           g.drawImage(attackRight.getCurrentFrame(), (int) (ar.x - handler.getCamera().getxOffset() - 5),
  (int) (ar.y - handler.getCamera().getyOffset() - 10 ), null);
           timer.schedule(new TimerTask(){
                @Override
                public void run() {
                    attackingRight = false;
                }
            },300);
        }
        
        g.drawImage(getCurrentAnimationFrame(),(int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()),(width),(height), null);
       
    }

 
    
  
    
    
    private BufferedImage getCurrentAnimationFrame() {
        // Method to return either idle or hurt state
        if (lastPressed == KeyEvent.VK_A && attacking) {
           if (isHurt) {
               attacking = false;
                return left_hurt.getCurrentFrame();
            } else {
               attacking = false;
                return left_idle.getCurrentFrame();
            }
        } else if (lastPressed == KeyEvent.VK_S && attacking) {
            if (isHurt ) {
                attacking = false;
                return down_hurt.getCurrentFrame();
            } else {
                attacking = false;
                return down_idle.getCurrentFrame();
            }
        } else if (lastPressed == KeyEvent.VK_D && attacking) {
            if (isHurt) {
                attacking = false;
                return right_hurt.getCurrentFrame();
            } else {
                attacking = false;
                return right_idle.getCurrentFrame();
            }
        } else if (lastPressed == KeyEvent.VK_W && attacking) {
            if (isHurt) {
                attacking = false;
                return up_hurt.getCurrentFrame();
            } else {
                attacking = false;
                return up_idle.getCurrentFrame();
            }
        }
        
        
        // Movement and idle states 
        if (handler.getKeyManager().left && (lastPressed == KeyEvent.VK_A)) {
            animState = 1;
            if (isHurt) {
                return left_hurt.getCurrentFrame();
            } else {
                return left.getCurrentFrame();
            }
            
        } else if ( !(handler.getKeyManager().left) && (lastPressed == KeyEvent.VK_A)) {
            
            if (isHurt) {
                return left_idle_hurt.getCurrentFrame();
            } else {
            return left_idle.getCurrentFrame();
            }
        } 
        
        if (handler.getKeyManager().right && (lastPressed == KeyEvent.VK_D)) {
            animState = 2;
            if (isHurt) {
                return right_hurt.getCurrentFrame();
            } else {
                return right.getCurrentFrame();
            }
            
        } else if ( !(handler.getKeyManager().right) && (lastPressed == KeyEvent.VK_D)) {
            if (isHurt) {
                return right_idle_hurt.getCurrentFrame();
            } else {
                return right_idle.getCurrentFrame();
            }
        } 
        
         if (handler.getKeyManager().up && (lastPressed == KeyEvent.VK_W)) {
             animState = 3;
             if (isHurt) {
                 return up_hurt.getCurrentFrame();
             } else {
            return up.getCurrentFrame();
             }
        } else if ( !(handler.getKeyManager().up) && (lastPressed == KeyEvent.VK_W) ) {
            if (isHurt) {
              return up_idle_hurt.getCurrentFrame();   
            } else {
            return up_idle.getCurrentFrame();
            }
        } 
         
         if (handler.getKeyManager().down && (lastPressed ==  KeyEvent.VK_S)) {
             animState = 4;
             if (isHurt) {
                 return down_hurt.getCurrentFrame();
             } else {
            return down.getCurrentFrame();
             }
        } else if ( !(handler.getKeyManager().down)&& (lastPressed == KeyEvent.VK_S) ) {
            
                if (isHurt) {
                    return down_idle_hurt.getCurrentFrame();
                } else {
                    return down_idle.getCurrentFrame();
                }
            
        }  
         
        return null;
        
    }
}
