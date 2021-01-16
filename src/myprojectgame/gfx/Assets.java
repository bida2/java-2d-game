/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.gfx;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import myprojectgame.Handler;
import myprojectgame.input.KeyManager;

/**
 *
 * @author my
 */
public class Assets {

    // Creates and initializes all of the game's assets in one class
    // This,however,is NOT a good practice
    // Learn from my mistake and split your game's assets into several different tiers
    // For example, keep the sound assets in a SoundAssets class, image assets in a Textures class
    private static final int width = 32,height = 32; // Asset default height and width
    
    public static Font font28;
    
    public static BufferedImage grass,rock,dirt,sand;
    public static BufferedImage rock_drop,tree_drop,vase_bags_drop,zombie_drop;
    public static BufferedImage[] player_down;
    public static BufferedImage[] player_up;
    public static BufferedImage[] player_left;
    public static BufferedImage[] player_right;
    public static BufferedImage[] player_down_idle;
    public static BufferedImage[] player_up_idle;
    public static BufferedImage[] player_left_idle;
    public static BufferedImage[] player_right_idle;
    
    
    public static BufferedImage[] player_down_hurt;
    public static BufferedImage[] player_left_hurt;
    public static BufferedImage[] player_right_hurt;
    public static BufferedImage[] player_up_hurt;
    public static BufferedImage[] player_down_idle_hurt;
    public static BufferedImage[] player_up_idle_hurt;
    public static BufferedImage[] player_left_idle_hurt;
    public static BufferedImage[] player_right_idle_hurt;
    
    // Player attacking (sword) animations
    public static BufferedImage[] player_attack_up;
    public static BufferedImage[] player_attack_down;
    public static BufferedImage[] player_attack_left;
    public static BufferedImage[] player_attack_right;
    
    // Menu State Assets ---> buttons mainly
    public static BufferedImage[] btn_start;
    public static BufferedImage[] btn_options;
    public static BufferedImage[] btn_exit;
    public static BufferedImage[] res1080;
    public static BufferedImage[] res800;
    public static BufferedImage[] resDefault;
    public static BufferedImage[] main_menu_death;
    
    public static BufferedImage inventoryScreen;
    
    // Static entity assets in the game
    public static BufferedImage[] treeOne,treeTwo;
    public static BufferedImage[] rockOne,rockTwo;
    public static BufferedImage[] treeLarge;
    public static BufferedImage[] bags;
    public static BufferedImage[] vase;
     
    // Hurt Animations ---> trees,rocks,enemies,etc.
    public static BufferedImage[] hurtTreeOne;
    public static BufferedImage[] hurtTreeTwo;
    public static BufferedImage[] hurtRockOne;
    public static BufferedImage[] hurtRockTwo;
    public static BufferedImage[] hurtTreeLarge;
    public static BufferedImage[] hurtBags;
    public static BufferedImage[] hurtVase;
    
    
    /* ***************************************** */
    // Zombie Animations --> walking animations
    public static BufferedImage[] zombie_left;
    public static BufferedImage[] zombie_right;
    public static BufferedImage[] zombie_up;
    public static BufferedImage[] zombie_down;
    
    // Zombie Hurt Animations ---> directional hurt states
    public static BufferedImage[] zombie_left_hurt;
    public static BufferedImage[] zombie_right_hurt;
    public static BufferedImage[] zombie_up_hurt;
    public static BufferedImage[] zombie_down_hurt;
     
    public static void init() throws IOException, FontFormatException {
        font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);
        
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/grass.png"));
        SpriteSheet sandSheet = new SpriteSheet(ImageLoader.loadImage("/textures/sand.jpg"));
        SpriteSheet sheetPlayer = new SpriteSheet(ImageLoader.loadImage("/textures/player.png"));
        SpriteSheet sheetPlayerHurt = new SpriteSheet(ImageLoader.loadImage("/textures/player_hurt.png"));
        SpriteSheet sheetRock = new SpriteSheet(ImageLoader.loadImage("/textures/rock.jpg"));
        SpriteSheet sheetDirt = new SpriteSheet(ImageLoader.loadImage("/textures/dirt.png"));
        SpriteSheet sheetTreeOne = new SpriteSheet(ImageLoader.loadImage("/textures/tree1.png"));
        SpriteSheet sheetTreeTwo = new SpriteSheet(ImageLoader.loadImage("/textures/tree2.png"));
        SpriteSheet sheetRockOne = new SpriteSheet(ImageLoader.loadImage("/textures/rock1.png"));
        SpriteSheet sheetRockTwo = new SpriteSheet(ImageLoader.loadImage("/textures/rock2.png"));
        SpriteSheet sheetDropRock = new SpriteSheet(ImageLoader.loadImage("/textures/rock_drop.png"));
        SpriteSheet sheetDropTree = new SpriteSheet(ImageLoader.loadImage("/textures/log_drop.png"));
        SpriteSheet sheetDropVaseBags = new SpriteSheet(ImageLoader.loadImage("/textures/bags_drop.png"));
        SpriteSheet sheetLargeTree = new SpriteSheet(ImageLoader.loadImage("/textures/largeTree.png"));
        SpriteSheet sheetBags = new SpriteSheet(ImageLoader.loadImage("/textures/bags.png"));
        SpriteSheet sheetVase = new SpriteSheet(ImageLoader.loadImage("/textures/vase.png"));
        
        // Menu State Assets --> buttons spritesheet 
        SpriteSheet start = new SpriteSheet(ImageLoader.loadImage("/textures/start.png"));
        SpriteSheet options = new SpriteSheet(ImageLoader.loadImage("/textures/options.png"));
        SpriteSheet exit = new SpriteSheet(ImageLoader.loadImage("/textures/exit.png"));
        SpriteSheet res_1920x1080 = new SpriteSheet(ImageLoader.loadImage("/textures/1920x1080_resolution.png"));
        SpriteSheet res_1024x800 = new SpriteSheet(ImageLoader.loadImage("/textures/1024x800_resolution.png"));
        SpriteSheet res_default = new SpriteSheet(ImageLoader.loadImage("/textures/default_resolution.png"));
        SpriteSheet menu_death = new SpriteSheet(ImageLoader.loadImage("/textures/death_menu_button.png"));
        // Hurt State Assets 
       SpriteSheet hurtTreeOneSheet = new SpriteSheet(ImageLoader.loadImage("/textures/tree1_hurt.png")); 
       SpriteSheet hurtTreeTwoSheet = new SpriteSheet(ImageLoader.loadImage("/textures/tree2_hurt.png")); 
       SpriteSheet hurtRockOneSheet = new SpriteSheet(ImageLoader.loadImage("/textures/rock1_hurt.png"));
       SpriteSheet hurtRockTwoSheet = new SpriteSheet(ImageLoader.loadImage("/textures/rock2_hurt.png"));
       SpriteSheet hurtLargeTreeSheet = new SpriteSheet(ImageLoader.loadImage("/textures/largeTree_hurt.png"));
       SpriteSheet hurtBagsSheet = new SpriteSheet(ImageLoader.loadImage("/textures/bags_hurt.png"));
       SpriteSheet hurtVaseSheet = new SpriteSheet(ImageLoader.loadImage("/textures/vase_hurt.png"));
       
       //Zombie Animation walking assets
       SpriteSheet zombie = new SpriteSheet(ImageLoader.loadImage("/textures/zombie.png"));
       SpriteSheet zombieHurt = new SpriteSheet(ImageLoader.loadImage("/textures/zombie_hurt.png"));
       SpriteSheet zombieDrop = new SpriteSheet(ImageLoader.loadImage("/textures/zombie_drop.png"));
       
       // Sword Sheet
       SpriteSheet sword = new SpriteSheet(ImageLoader.loadImage("/textures/sword.png"));
        
        // Menu State Animations/Buttons --> for the color of the buttons,used Midnight City
        // from uiGradients tool / library at https://uigradients.com/#MidnightCity
        
        inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");
        
        btn_start = new BufferedImage[2];
        btn_start[0] = start.crop(0, 0, 181,144);
        btn_start[1] = start.crop(219, 0, 181,144);
        
        btn_options = new BufferedImage[2];
        btn_options[0] = options.crop(0, 0, 181,144);
        btn_options[1] = options.crop(219, 0, 181,144);
        
        btn_exit = new BufferedImage[2];
        btn_exit[0] = exit.crop(0, 0, 181,144);
        btn_exit[1] = exit.crop(219, 0, 181,144);
        
        res1080 = new BufferedImage[2];
        res1080[0] = res_1920x1080.crop(0,0,230,200);
        res1080[1] = res_1920x1080.crop(265, 0, 230,200);
        
        res800 = new BufferedImage[2];
        res800[0] = res_1024x800.crop(0,0,230,200);
        res800[1] = res_1024x800.crop(265, 0, 230,200);
        
        
        resDefault = new BufferedImage[2];
        resDefault[0] = res_default.crop(0,0,230,200);
        resDefault[1] = res_default.crop(265, 0, 230,200);
        
        main_menu_death = new BufferedImage[2];
        main_menu_death[0] = menu_death.crop(0,0,230,200);
        main_menu_death[1] = menu_death.crop(265,0,230,200);
        
        
        
        player_down = new BufferedImage[6];
        player_up = new BufferedImage[6];
        player_left = new BufferedImage[6];
        player_right = new BufferedImage[6];
        player_down_idle = new BufferedImage[2];
        player_up_idle = new BufferedImage[2];
        player_left_idle = new BufferedImage[2];
        player_right_idle = new BufferedImage[2];
        
        player_down_hurt = new BufferedImage[6];
        player_up_hurt = new BufferedImage[6];
        player_left_hurt = new BufferedImage[6];
        player_right_hurt = new BufferedImage[6];
        player_down_idle_hurt = new BufferedImage[2];
        player_up_idle_hurt = new BufferedImage[2];
        player_left_idle_hurt = new BufferedImage[2];
        player_right_idle_hurt = new BufferedImage[2];
        
      
      
       // Currently done ---> 22/04/2018
        player_down[0] = sheetPlayer.crop(0, 44, 15,20);
        player_down[1] = sheetPlayer.crop(0, 22, 15,20);
        player_down[2] = sheetPlayer.crop(0, 44, 15,20);
        player_down[3] = sheetPlayer.crop(0, 22, 15,20);
        player_down[4] = sheetPlayer.crop(0, 44, 15,20);
        player_down[5] = sheetPlayer.crop(0, 22, 15,20);
        
        
        player_down_hurt[0] = sheetPlayerHurt.crop(0, 44, 15,20);
        player_down_hurt[1] = sheetPlayerHurt.crop(0, 22, 15,20);
        player_down_hurt[2] = sheetPlayerHurt.crop(0, 44, 15,20);
        player_down_hurt[3] = sheetPlayerHurt.crop(0, 22, 15,20);
        player_down_hurt[4] = sheetPlayerHurt.crop(0, 44, 15,20);
        player_down_hurt[5] = sheetPlayerHurt.crop(0, 22, 15,20);
        
       
        
        
        // Currently done --> 22/04/2018
        player_up[0] = sheetPlayer.crop(14, 44, 16,20);// ---> idle walk-up testing value ---> sheetPlayer.crop(14, 0, 16,20);
        player_up[1] = sheetPlayer.crop(14, 22, 16,20); 
        player_up[2] = sheetPlayer.crop(14, 44, 16,20);
        player_up[3] = sheetPlayer.crop(14, 22, 16,20); //----> walk-up running test value ---> sheetPlayer.crop(14, 64, 16,20);
        player_up[4] = sheetPlayer.crop(14, 44, 16,20);  //----> walk-up running test value --->  sheetPlayer.crop(14, 84, 16,20);
        player_up[5] = sheetPlayer.crop(14, 22, 16,20); // ----> walk-up running test value --->  sheetPlayer.crop(14, 104, 16,20);
        
        player_up_hurt[0] = sheetPlayerHurt.crop(14, 44, 16,20);// ---> idle walk-up testing value ---> sheetPlayer.crop(14, 0, 16,20);
        player_up_hurt[1] = sheetPlayerHurt.crop(14, 22, 16,20); 
        player_up_hurt[2] = sheetPlayerHurt.crop(14, 44, 16,20);
        player_up_hurt[3] = sheetPlayerHurt.crop(14, 22, 16,20); //----> walk-up running test value ---> sheetPlayer.crop(14, 64, 16,20);
        player_up_hurt[4] = sheetPlayerHurt.crop(14, 44, 16,20);  //----> walk-up running test value --->  sheetPlayer.crop(14, 84, 16,20);
        player_up_hurt[5] = sheetPlayerHurt.crop(14, 22, 16,20); // ----> walk-up running test value --->  sheetPlayer.crop(14, 104, 16,20);
        
        
        // Currently done ---> 22/04/2018
        player_left[0] = sheetPlayer.crop(29, 22, 15,20);
        player_left[1] = sheetPlayer.crop(29, 44, 15,20);
        player_left[2] = sheetPlayer.crop(29, 22, 15,20);
        player_left[3] = sheetPlayer.crop(29, 44, 15,20);
        player_left[4] = sheetPlayer.crop(29, 22, 15,20);
        player_left[5] = sheetPlayer.crop(29, 44, 15,20);
        
        
        player_left_hurt[0] = sheetPlayerHurt.crop(29, 22, 15,20);
        player_left_hurt[1] = sheetPlayerHurt.crop(29, 44, 15,20);
        player_left_hurt[2] = sheetPlayerHurt.crop(29, 22, 15,20);
        player_left_hurt[3] = sheetPlayerHurt.crop(29, 44, 15,20);
        player_left_hurt[4] = sheetPlayerHurt.crop(29, 22, 15,20);
        player_left_hurt[5] = sheetPlayerHurt.crop(29, 44, 15,20);
        // Currently done ---> 22/04/2018
        player_right[0] = sheetPlayer.crop(29, 22, 15,20); 
        player_right[1] = sheetPlayer.crop(29, 44, 15,20);
        player_right[2] = sheetPlayer.crop(29, 22, 15,20); 
        player_right[3] = sheetPlayer.crop(29, 44, 15,20); 
        player_right[4] = sheetPlayer.crop(29, 22, 15,20); 
        player_right[5] = sheetPlayer.crop(29, 44, 15,20); 
        // Through use of AffineTransform and AffineTransformOp classes,mirrors an image(mirror flip)
         AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-player_right[0].getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, 
                                AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        player_right[0] = op.filter(player_right[0], null);
        player_right[1] = op.filter(player_right[1],null);
        player_right[2] = op.filter(player_right[2], null);
        player_right[3] = op.filter(player_right[3],null);
        player_right[4] = op.filter(player_right[4], null);
        player_right[5] = op.filter(player_right[5],null);
        
        player_right_hurt[0] = sheetPlayerHurt.crop(29, 22, 15,20); 
        player_right_hurt[1] = sheetPlayerHurt.crop(29, 44, 15,20);
        player_right_hurt[2] = sheetPlayerHurt.crop(29, 22, 15,20); 
        player_right_hurt[3] = sheetPlayerHurt.crop(29, 44, 15,20); 
        player_right_hurt[4] = sheetPlayerHurt.crop(29, 22, 15,20); 
        player_right_hurt[5] = sheetPlayerHurt.crop(29, 44, 15,20); 
        // Through use of AffineTransform and AffineTransformOp classes,mirrors an image(mirror flip)
         AffineTransform txHurtRight = AffineTransform.getScaleInstance(-1, 1);
        txHurtRight.translate(-player_right_hurt[0].getWidth(null), 0);
        AffineTransformOp opHurtRight = new AffineTransformOp(txHurtRight, 
                                AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        player_right_hurt[0] = opHurtRight.filter(player_right_hurt[0], null);
        player_right_hurt[1] = opHurtRight.filter(player_right_hurt[1],null);
        player_right_hurt[2] = opHurtRight.filter(player_right_hurt[2], null);
        player_right_hurt[3] = opHurtRight.filter(player_right_hurt[3],null);
        player_right_hurt[4] = opHurtRight.filter(player_right_hurt[4], null);
        player_right_hurt[5] = opHurtRight.filter(player_right_hurt[5],null);
        
        
        /*************************************************************/
        // IDLE ANIMATIONS BEGIN HERE //
        /************************************************************/
        
        player_up_idle[0] = sheetPlayer.crop(15,0,15,20);
        player_up_idle[1] = sheetPlayer.crop(15,0,15,20);
        player_down_idle[0] = sheetPlayer.crop(0,0,15,20);
        player_down_idle[1] = sheetPlayer.crop(0,0,15,20);
        player_left_idle[0] = sheetPlayer.crop(30,0,15,20);
        player_left_idle[1] = sheetPlayer.crop(30,0,15,20);
        player_right_idle[0] = sheetPlayer.crop(30,0,15,20);
        player_right_idle[1] = sheetPlayer.crop(30,0,15,20);
        AffineTransform txTwo = AffineTransform.getScaleInstance(1, 1);
        txTwo.translate(-player_right_idle[0].getWidth(null),0);
        AffineTransformOp opTwo = new AffineTransformOp(tx,AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        player_right_idle[0] = opTwo.filter(player_right_idle[0], null);
        player_right_idle[1] = opTwo.filter(player_right_idle[1],null);
        
        
        
        player_up_idle_hurt[0] = sheetPlayerHurt.crop(15,0,15,20);
        player_up_idle_hurt[1] = sheetPlayerHurt.crop(15,0,15,20);
        player_down_idle_hurt[0] = sheetPlayerHurt.crop(0,0,15,20);
        player_down_idle_hurt[1] = sheetPlayerHurt.crop(0,0,15,20);
        player_left_idle_hurt[0] = sheetPlayerHurt.crop(30,0,15,20);
        player_left_idle_hurt[1] = sheetPlayerHurt.crop(30,0,15,20);
        player_right_idle_hurt[0] = sheetPlayerHurt.crop(30,0,15,20);
        player_right_idle_hurt[1] = sheetPlayerHurt.crop(30,0,15,20);
        AffineTransform txTwoHurt = AffineTransform.getScaleInstance(1, 1);
        txTwoHurt.translate(-player_right_idle_hurt[0].getWidth(null),0);
        AffineTransformOp opTwoHurt = new AffineTransformOp(tx,AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        player_right_idle_hurt[0] = opTwoHurt.filter(player_right_idle_hurt[0], null);
        player_right_idle_hurt[1] = opTwoHurt.filter(player_right_idle_hurt[1],null);
        
        
        
        
        
        
        
        player_attack_up = new BufferedImage[2];
        player_attack_up[0] = sword.crop(0, 0, width, height);
        player_attack_up[1] = sword.crop(0, 0, width, height);
        
        
        player_attack_down = new BufferedImage[2];
        player_attack_down[0] = sword.crop(0, 0, width, height);
        player_attack_down[1] = sword.crop(0, 0, width, height);
        double rotate180 = Math.toRadians(180);
        double rotate90 = Math.toRadians(90);
        double rotate270 = Math.toRadians(270);
        double locationX = player_attack_down[0].getWidth() / 2;
        double locationY = player_attack_down[1].getHeight() / 2;
        AffineTransform txAttackD = AffineTransform.getRotateInstance(rotate180, locationX, locationY);
        AffineTransformOp opAttackD = new AffineTransformOp(txAttackD, AffineTransformOp.TYPE_BILINEAR);
        player_attack_down[0] = opAttackD.filter(player_attack_down[0], null);
        player_attack_down[1] = opAttackD.filter(player_attack_down[1], null);
        
        player_attack_left = new BufferedImage[2];
        player_attack_left[0] = sword.crop(0, 0, width, height);
        player_attack_left[1] = sword.crop(0, 0, width, height);
        AffineTransform txAttackL = AffineTransform.getRotateInstance(rotate270, locationX, locationY);
        AffineTransformOp opAttackL = new AffineTransformOp(txAttackL, AffineTransformOp.TYPE_BILINEAR);
        player_attack_left[0] = opAttackL.filter(player_attack_left[0], null);
        player_attack_left[1] = opAttackL.filter(player_attack_left[0], null);
        
        player_attack_right = new BufferedImage[2];
        player_attack_right[0] = sword.crop(0, 0, width, height);
        player_attack_right[1] = sword.crop(0, 0, width, height);
        AffineTransform txAttackR = AffineTransform.getRotateInstance(rotate90, locationX, locationY);
        AffineTransformOp opAttackR = new AffineTransformOp(txAttackR, AffineTransformOp.TYPE_BILINEAR);
        player_attack_right[0] = opAttackR.filter(player_attack_right[0], null);
        player_attack_right[1] = opAttackR.filter(player_attack_right[1],null);
        //////////////////////////////////////////////////////////////
        
        /* ********************************************************* */
        // Zombie Animations
        zombie_up = new BufferedImage[3];
        zombie_up_hurt = new BufferedImage[3];
        zombie_down = new BufferedImage[3];
        zombie_down_hurt = new BufferedImage[3];
        zombie_left = new BufferedImage[3];
        zombie_right = new BufferedImage[3];
        zombie_left_hurt = new BufferedImage[3];
        zombie_right_hurt = new BufferedImage[3];
        
        zombie_up[0] = zombie.crop(0,196,width,height + 10);
        zombie_up[1] = zombie.crop(32,196,width,height + 10);
        zombie_up[2] = zombie.crop(64,196,width,height + 10);
        
        zombie_up_hurt[0] = zombieHurt.crop(0, 192, width, height + 10);
        zombie_up_hurt[1] = zombieHurt.crop(32, 192, width, height + 10);
        zombie_up_hurt[2] = zombieHurt.crop(64, 192, width, height + 10);
        
        zombie_down[0] = zombie.crop(0,5,width,height + 10);
        zombie_down[1] = zombie.crop(32,5,width,height + 10);
        zombie_down[2] = zombie.crop(64,5,width,height + 10);
        
        zombie_down_hurt[0] = zombieHurt.crop(0,5,width,height + 10);
        zombie_down_hurt[1] = zombieHurt.crop(32,5,width,height + 10);
        zombie_down_hurt[2] = zombieHurt.crop(64,5,width,height + 10);
        
        zombie_left[0] = zombie.crop(0, 70, width, height + 10);
        zombie_left[1] = zombie.crop(32,70,width,height + 10);
        zombie_left[2] = zombie.crop(64,70,width,height + 10);
        
        zombie_right[0] = zombie.crop(0,135,width,height + 10);
        zombie_right[1] = zombie.crop(32,133,width,height + 10);
        zombie_right[2] = zombie.crop(64,133,width,height + 10);
        
        zombie_left_hurt[0] = zombieHurt.crop(0,70,width,height + 10);
        zombie_left_hurt[1] = zombieHurt.crop(32,70,width,height + 10);
        zombie_left_hurt[2] = zombieHurt.crop(64,70,width,height + 10);
        
        zombie_right_hurt[0] = zombieHurt.crop(0,135,width,height + 10);
        zombie_right_hurt[1] = zombieHurt.crop(32,133,width,height + 10);
        zombie_right_hurt[2] = zombieHurt.crop(64,133,width,height + 10);
        
        zombie_drop = zombieDrop.crop(0,0,150,150);
        /* ********************************************************** */
        
        
        treeOne = new BufferedImage[2];
        treeTwo = new BufferedImage[2];
        treeLarge = new BufferedImage[2];
        bags = new BufferedImage[2];
        vase = new BufferedImage[2];
        rockOne = new BufferedImage[2];
        rockTwo = new BufferedImage[2];
        hurtTreeOne = new BufferedImage[2];
        hurtTreeTwo = new BufferedImage[2];
        hurtRockOne = new BufferedImage[2];
        hurtRockTwo = new BufferedImage[2];
        hurtTreeLarge = new BufferedImage[2];
        hurtVase = new BufferedImage[2];
        hurtBags = new BufferedImage[2];
        
        
        
        
        grass = sheet.crop(0, 0, 2*width, 2*height);
        rock = sheetRock.crop(0,0, 2*width, 2*height);
        dirt = sheetDirt.crop(0,0,2*width,2*height);
        sand = sandSheet.crop(0,0,2*width,2*height);
        rock_drop = sheetDropRock.crop(0,0,20,20);
        // Test the rock_drop and tree_drop values 
        tree_drop = sheetDropTree.crop(0,0,width + 8,height + 8);
        vase_bags_drop = sheetDropVaseBags.crop(0, 0, width, height);
        treeOne[0] = sheetTreeOne.crop(0,0,52,66);
        treeOne[1] = sheetTreeOne.crop(0,0,52,66);
        hurtTreeOne[0] = hurtTreeOneSheet.crop(0, 0, 52, 66);
        hurtTreeOne[1] = hurtTreeOneSheet.crop(0, 0, 52, 66);
        treeTwo[0] = sheetTreeTwo.crop(0,0,55,66);
        treeTwo[1] = sheetTreeTwo.crop(0,0,55,66);
        hurtTreeTwo[0] = hurtTreeTwoSheet.crop(0,0,55,66); 
        hurtTreeTwo[1] = hurtTreeTwoSheet.crop(0,0,55,66);
        rockOne[0] = sheetRockOne.crop(0,0,32,32);
        rockOne[1] = sheetRockOne.crop(0,0,32,32);
        hurtRockOne[0] = hurtRockOneSheet.crop(0,0,32,32);
        hurtRockOne[1] = hurtRockOneSheet.crop(0,0,32,32);
        rockTwo[0] = sheetRockTwo.crop(0,0,32,32);
        rockTwo[1] = sheetRockTwo.crop(0,0,32,32);
        hurtRockTwo[0] = hurtRockTwoSheet.crop(0,0,32,32);
        hurtRockTwo[1] = hurtRockTwoSheet.crop(0,0,32,32);
        treeLarge[0] = sheetLargeTree.crop(0,0,220,220);
        treeLarge[1] = sheetLargeTree.crop(0,0,220,220);
        hurtTreeLarge[0] = hurtLargeTreeSheet.crop(0,0,220,220);
        hurtTreeLarge[1] = hurtLargeTreeSheet.crop(0,0,220,220); 
        bags[0] = sheetBags.crop(0,0,64,62);
        bags[1] = sheetBags.crop(0,0,64,62);
        hurtBags[0] = hurtBagsSheet.crop(0,0,64,62);
        hurtBags[1] = hurtBagsSheet.crop(0,0,64,62);
        vase[0] = sheetVase.crop(0,0,24,38);
        vase[1] = sheetVase.crop(0,0,24,38);
        hurtVase[0] = hurtVaseSheet.crop(0,0,24,38);
        hurtVase[1] = hurtVaseSheet.crop(0,0,24,38);
    }
}
