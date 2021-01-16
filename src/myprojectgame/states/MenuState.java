/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.states;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import static javax.swing.Spring.height;
import static javax.swing.Spring.width;
import myprojectgame.Game;
import myprojectgame.Handler;
import myprojectgame.afx.Audio;
import myprojectgame.display.Display;
import myprojectgame.gfx.Assets;
import myprojectgame.ui.ClickListener;
import myprojectgame.ui.UIImageButton;
import myprojectgame.ui.UIManager;

/**
 *
 * @author my
 */
public class MenuState extends State {
    // The default state of the game. The user is always greeted by this state when they start the game.
    // Useful for changing various options( in this release only the game resolution can be changed)
    public static UIManager ui;
    public static UIManager options;
    
    // Creates the gradient background from two programmer specified colors
    public static Graphics2D grRender;
     public static Color uiBG = new Color(0,0,0); // Gradient color 1
    public static Color uiBGT = new Color(15,155,15); // Gradient color 2
    
    public static int height = Display.getFrame().getSize().height;
    public static int width = Display.getFrame().getSize().width;
    public static GradientPaint paint = new GradientPaint(0,0,uiBG,width,height,uiBGT);
    
    public MenuState(Handler handler) {
        super(handler);
        ui = new UIManager(handler);
        handler.getMouseManager().setUIManager(ui);
        
        // Creates the buttons in the MenuState
        ui.addObject(new UIImageButton(200,50,185,145,Assets.btn_start,new ClickListener(){
            @Override
            public void onClick() {
                // we set this so when we switch to the game the buttons in the UI get no input anymore
                handler.getMouseManager().setUIManager(null); 
                try {
                    handler.getGame().getAudioPlayer().stop();
                    handler.getGame().getAudioPlayer().filePath = "res/sound/game_theme.wav";
                    handler.getGame().getAudioPlayer().resetAudioStream();
                    handler.getGame().getAudioPlayer().play();
                } catch (Exception ex) {
                    Logger.getLogger(MenuState.class.getName()).log(Level.SEVERE, null, ex);
                } 
                handler.getGame().gameState = new GameState(handler);
                State.setState(handler.getGame().gameState);
            }
        }));
        ui.addObject(new UIImageButton(200,200,185,145,Assets.btn_options,new ClickListener(){
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(OptionsState.options);
                State.setState(handler.getGame().optionsState);
            }
        } ));
            ui.addObject(new UIImageButton(200,350,185,145,Assets.btn_exit,new ClickListener(){
            @Override
            public void onClick() {
                System.exit(0);
            }
        } ));
    }
    
    @Override
    public void tick() {
       ui.tick();
    }

    @Override
    public void render(Graphics g) {
        height = Display.getFrame().getSize().height;
        width = Display.getFrame().getSize().width;
        grRender = (Graphics2D) g;
        grRender.setPaint(paint);
        grRender.fillRect(0,0,width,height);
        ui.render(g);
    }
    
}
