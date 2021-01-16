/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.entities.statics;

import java.util.logging.Level;
import java.util.logging.Logger;
import myprojectgame.Handler;
import myprojectgame.entities.Entity;

// Used for creating non-moving entities in the game 
// Example --> Rock class,Tree class,TreeLarge class,Vase class, BunchOfBags class
public abstract class StaticEntity extends Entity{
    public StaticEntity(Handler handler, float x, float y, int width, int height,int uID) {
        super(handler,x,y,width,height,uID);
    }
}
