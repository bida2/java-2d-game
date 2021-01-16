/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.tiles;

import java.awt.image.BufferedImage;
import myprojectgame.gfx.Assets;

// A sand tile for the game. Unique ID is 3.
public class SandTile extends Tile {
    
    public SandTile(int id) {
        super(Assets.sand, id);
    }
    
}
