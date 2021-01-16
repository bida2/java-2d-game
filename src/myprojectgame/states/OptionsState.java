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
import myprojectgame.Game;
import myprojectgame.Handler;
import myprojectgame.display.Display;
import myprojectgame.gfx.Assets;
import static myprojectgame.states.MenuState.grRender;
import static myprojectgame.states.MenuState.paint;
import myprojectgame.ui.ClickListener;
import myprojectgame.ui.UIImageButton;
import myprojectgame.ui.UIManager;

/**
 *
 * @author my
 */
public class OptionsState extends State {
    // When the "Options" button in the MenuState is clicked, the game state is set to this state - the OptionsState 
    // Here,we can change various options,related to the game
    // in the current iteration,we can only change the resolution
    // for now that is NOT RECOMMENDED
    public static UIManager options;
    public static UIManager ui;
    public static Graphics2D grRender;
        public static Color uiBG = new Color(0,0,0); // Same color as menu state gradient 1
    public static Color uiBGT = new Color(15,155,15);  // Same color as menu state gradient 2
       public static int height = Display.getFrame().getSize().height;
    public static int width = Display.getFrame().getSize().width;
     GradientPaint paint = new GradientPaint(0,0,uiBG,width,height,uiBGT);
    
  public OptionsState (Handler handler) {
      super(handler);
      options = new UIManager(handler);
      ui = new UIManager(handler);
     
       
     

        options.addObject(new UIImageButton(200,50,200,180,Assets.resDefault,new ClickListener(){
            @Override
            public void onClick() {
                Game.display.getFrame().setSize(600, 600);
               handler.getMouseManager().setUIManager(MenuState.ui);
                State.setState(handler.getGame().menuState);
            }
        }));
        options.addObject(new UIImageButton(200,200,200,180,Assets.res800,new ClickListener(){
            @Override
            public void onClick() {
                Game.display.getFrame().setSize(1024, 800);
               handler.getMouseManager().setUIManager(MenuState.ui);
                 State.setState(handler.getGame().menuState);
            }
        }));
        options.addObject(new UIImageButton(200,350,200,180,Assets.res1080,new ClickListener(){
            @Override
            public void onClick() {
                Game.display.getFrame().setSize(1920, 1080);
               handler.getMouseManager().setUIManager(MenuState.ui);
                 State.setState(handler.getGame().menuState);
            }
        }));
       
    }
    
    @Override
    public void tick() {
       options.tick();
    }

    @Override
    public void render(Graphics g) {
        height = Display.getFrame().getSize().height;
        width = Display.getFrame().getSize().width;
    grRender = (Graphics2D) g;
    grRender.setPaint(paint);
    grRender.fillRect(0,0,width,height);
    options.render(g);

    }
    
}
