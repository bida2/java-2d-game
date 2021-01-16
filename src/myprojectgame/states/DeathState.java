/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import myprojectgame.Game;
import myprojectgame.Handler;
import myprojectgame.display.Display;
import myprojectgame.entities.creature.Monster;
import myprojectgame.entities.creature.Player;
import myprojectgame.gfx.Assets;
import myprojectgame.gfx.FontLoader;
import myprojectgame.gfx.Text;
import static myprojectgame.gfx.Text.drawString;
import myprojectgame.ui.ClickListener;
import myprojectgame.ui.UIImageButton;
import myprojectgame.ui.UIManager;

/**
 *
 * @author my
 */
public class DeathState extends State {
     // A death state. When the Player character is killed,the game switches from the GameState to 
    // this here DeathState
     public static UIManager death;
     public static UIManager ui;
     public static Font diedFont;
     
    
  public DeathState (Handler handler) throws FontFormatException, IOException {
      super(handler);
      death = new UIManager(handler);
      ui = new UIManager(handler);
      Color grayTr = new Color(60,60,60,105);
      Display.getFrame().getContentPane().setBackground(grayTr);
      diedFont = FontLoader.loadFont("res/fonts/pkmnem.ttf",60);
        death.addObject(new UIImageButton(200,240,200,180,Assets.main_menu_death,new ClickListener(){
            @Override
            public void onClick() {
                
                try {
                    handler.getGame().getAudioPlayer().stop();
                    handler.getGame().getAudioPlayer().filePath = "res/sound/main_theme.wav";
                    handler.getGame().getAudioPlayer().resetAudioStream();
                    handler.getGame().getAudioPlayer().play();
                } catch (Exception ex) {
                    Logger.getLogger(DeathState.class.getName()).log(Level.SEVERE, null, ex);
                } 
            
       
                 handler.getMouseManager().setUIManager(MenuState.ui);
                 State.setState(handler.getGame().menuState);
            }
        }));
        
       
    }
  
    
    @Override
    public void tick() {
       death.tick();
    }

    @Override
    public void render(Graphics g) {
        drawString(g,"You died!",300,170,true,Color.white,diedFont);
        death.render(g);

    }
   
}
