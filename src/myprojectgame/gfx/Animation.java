/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author my
 */
public class Animation {
    // Makes the animations in the game possible
    // Through the use of an array of buffered images(BufferedImage class) 
    // A frame is displayed for a specified by the programmer time and then it changes to the next one in the array
    // of buffered images
    
    public int speed;
    private int index;
    public long lastTime;
    public long timer;
    private BufferedImage[] frames;
    
    public Animation(int speed,BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    
    public void tick() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        
        if (timer > speed ){
            index++;
            timer = 0;
            if (index >= frames.length) {
                index = 0;
            }
        }
    }
    
    public BufferedImage getCurrentFrame() {
        return frames[index];
    }
    
}
