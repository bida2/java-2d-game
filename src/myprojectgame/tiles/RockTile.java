/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.tiles;

import myprojectgame.gfx.Assets;

// A solid rock tile in the game. Impassable, unique ID is 2.
public class RockTile extends Tile{
    
   
    
    public RockTile(int id) {
        super(Assets.rock,id);
    }
    
    @Override 
    public boolean isSolid() {
        return true;
    }
    
}
