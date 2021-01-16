/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.tiles;

import myprojectgame.gfx.Assets;

// A dirt tile in the game. Unique ID for the dirt tile is 1.

public class DirtTile extends Tile {
    public DirtTile(int id) {
        super(Assets.dirt,id);
    }
}
