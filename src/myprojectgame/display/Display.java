/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.display;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author my
 */
public class Display {
    
    // The Display class creates our JFrame component
    // and Canvas component
    
    // Those two components together allow us render whatever we wish to the screen
    private static JFrame frame;
    private Canvas canvas;
    private String title;
    private int width,height;
    
    // Constructor class 
    public Display(String title,int width,int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        createDisplay();
    }
    
    private void createDisplay() {
        frame = new JFrame(title); // Creates the JFrame and sets the title we want
        frame.setSize(width,height); // Sets the size of the JFrame window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Makes sure program closes down properly,doesn't keep running in background
        frame.setResizable(false); // Cannot resize the window --> breaks current iteration of the game
        frame.setLocationRelativeTo(null); // When window first appears on screen,it centers it on the screen with this method
        frame.setVisible(true); // Window is made visible through this method
        
        // Create the Canvas - the component we actually draw EVERYTHING in our game in
        // Think of it as a window within a window
        canvas = new Canvas(); 
        canvas.setPreferredSize(new Dimension(width,height));
        canvas.setMaximumSize(new Dimension(width,height));
        canvas.setMinimumSize(new Dimension(width,height));
        canvas.setFocusable(false); // Set it to not focusable - if focusable, visual bugs can occur
        
        frame.add(canvas); // add this Canvas to the frame
        frame.pack(); // Resizes the window so we can see the canvas FULLY
    }
    
    
    // Getters for the Canvas and JFrame
    
    public Canvas getCanvas() {
        return canvas;
    }
    
    public static JFrame getFrame() {
        return frame;
    }
}
