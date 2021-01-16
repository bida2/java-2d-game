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
public class SpriteSheet {
    // Creates a subimage from another image
    // This is how we create our animations in the game
    private BufferedImage sheet;
    
    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }
    
    public BufferedImage crop(int x, int y, int width, int height) {
        return sheet.getSubimage(x,y,width,height);
    }
}
