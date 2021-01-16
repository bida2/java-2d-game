/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.gfx;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author my
 */
public class FontLoader {
    // Loads a font to the screen. Used in conjunction with the Text class.
    
    public static Font loadFont(String path,float size) throws FontFormatException, IOException {
        return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(Font.PLAIN,size);
    }
    
}
