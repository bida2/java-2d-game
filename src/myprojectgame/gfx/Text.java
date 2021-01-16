/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 *
 * @author my
 */
public class Text {
    // A method that allows us to draw a string to a specified position on the screen
    // using specified X and Y coordinates
    public static void drawString(Graphics g,String text,int xPos,int yPos,boolean center,Color c,Font f) {
        g.setColor(c);
        g.setFont(f);
        int x = xPos;
        int y = yPos;
        if (center) {
            FontMetrics fm = g.getFontMetrics(f);
            x = xPos - fm.stringWidth(text) / 2;
            y = (yPos - fm.getHeight() / 2) + fm.getAscent();
        }
        g.drawString(text, x, y);
    }
    
}
