/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.tiles;

import myprojectgame.gfx.Assets;



// A grass tile for the game. Unique ID is 0.
public class GrassTile extends Tile{
    
    public GrassTile(int id) {
        super(Assets.grass, id);
    }
    
}
