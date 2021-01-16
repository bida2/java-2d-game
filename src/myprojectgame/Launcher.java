/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame;

import myprojectgame.display.Display;

/**
 *
 * @author my
 */
public class Launcher {
    // Simple class that starts our game with the specified title of the window
    // and height and width of that window
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Game game = new Game("Programming 2 Project",600,600);
       game.start();
       
    }
    
}
