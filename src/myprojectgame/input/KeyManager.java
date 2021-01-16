/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import myprojectgame.entities.creature.Player;


// A KeyManager class implementing the KeyListener interface
// This class allows us to get keyboard input from the user 
// and based on that keyboard - return proper animations or attack 

public class KeyManager implements KeyListener {
    
    public static boolean[] keys,justPressed,cantPress;
    public boolean up,down,left,right;
    public boolean aUp,aDown,aLeft,aRight;
    
    public KeyManager() {
        keys = new boolean[256];
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];
    }
    
    public void tick() {
        
        for (int i = 0; i < keys.length; i++) {
            if(cantPress[i] && !keys[i]) {
                cantPress[i] = false;
            } else if (justPressed[i]) {
                cantPress[i] = true;
                justPressed[i] = false;
            }
            
            if (!cantPress[i] && keys[i]){
                justPressed[i] = true;
            }
        }
        
        
        
        // Move with WASD, attack with arrow keys
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        
        aUp = keys[KeyEvent.VK_UP];
        aDown = keys[KeyEvent.VK_DOWN];
        aLeft = keys[KeyEvent.VK_LEFT];
        aRight = keys[KeyEvent.VK_RIGHT];
    }
     
    
   
 public boolean keyJustPressed(int keyCode){
    if(keyCode < 0 || keyCode > keys.length) {
                return false;
    }
            return justPressed[keyCode];
}
    @Override
    public void keyTyped(KeyEvent e) {
      if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) {
          return;
      }
      keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
         if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) {
          return;
      }
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
         if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) {
          return;
      }
        keys[e.getKeyCode()] = false;
    }
    
}
