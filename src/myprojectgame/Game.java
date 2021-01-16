/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame;

import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import myprojectgame.afx.Audio;
import myprojectgame.display.Display;
import myprojectgame.gfx.Assets;
import myprojectgame.gfx.GameCamera;
import myprojectgame.gfx.ImageLoader;
import myprojectgame.gfx.SpriteSheet;
import myprojectgame.input.KeyManager;
import myprojectgame.input.MouseManager;
import myprojectgame.states.DeathState;
import myprojectgame.states.GameState;
import myprojectgame.states.MenuState;
import myprojectgame.states.OptionsState;
import myprojectgame.states.State;

/**
 *
 * @author my
 */
public class Game implements Runnable{
    // Runnable interface is implemented
    // meaning that we must implement the run() method so it won't give us an error
    
    // Creates the Display class, used in displaying the JFrame and Canvas
    // to the window
    public static Display display;
    
    // Height,width and title of game window
    private int width,height;
    public String title;
    
    // BufferStrategy class - determines how complex memory is rendered to the
    // Canvas and Window elements in the JFrame
    // Graphics class - used to render ALL of our images to the Canvas
    private BufferStrategy bs;
    private Graphics g;
    
    // Game states --> menus,the actual game with levels,etc.
    public State gameState;
    public State menuState;
    public State optionsState;
    public State deathState;
    
    // Input - from both keyboard and mouse
    private KeyManager keys;
    private MouseManager mouse;
    
    // Camera - responsible for focusing on certain things
    // like focusing on the player
    private GameCamera camera;
    
    // Handler - a utility class that allows us
    // to access such things as our game world
    // the entities in the game, etc. from other classes
    private Handler handler;
    
    // Audio - custom Audio class for the project, allows us to
    // implement different songs for different states or events in the game
    private Audio audioPlayer;
    
    
    private Thread thread1; // A thread is created,so our game can run on it
    private boolean running = false; // Shows the program if the game is running or not
    public Game(String title,int width,int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keys = new KeyManager();
        mouse = new MouseManager();
        
    }
    
    private void init() throws IOException, FontFormatException {
        display = new Display(title,width,height); // Initialize our display, where the game takes place
        display.getFrame().addKeyListener(keys); // Add a listener for keyboard input by the player
        // We add a mouse listener and mouse motion listener to both our JFrame and JCanvas
        // This is done to prevent buggy behavior from JFrame
        // JFrame often has element focus problems
        display.getFrame().addMouseListener(mouse);
        display.getFrame().addMouseMotionListener(mouse);
        display.getCanvas().addMouseListener(mouse);
        display.getCanvas().addMouseMotionListener(mouse);
        // Initialize the assets for the game - music,textures(images) and game worlds
        Assets.init();
        
          // Initialize our handler ---> has to be before game camera always 
        handler = new Handler(this);
        // Start camera before game states
        camera = new GameCamera(handler,0,0);
        
      
        // Create all the game states in our game
        // Default state is the menuState --> user must start in the game menu
        // so he/she can choose desired resolution, for example
        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        optionsState = new OptionsState(handler);
        deathState = new DeathState(handler);
        State.setState(menuState);
        
    }
    
    
    // we use the tick() method on the keys so we can constantly
    // update the input of the user from the keyboard
    private void tick () {
        keys.tick();
        if (State.getState() != null) {
            State.getState().tick();
        }
    }
    
    private void render() {
        bs = display.getCanvas().getBufferStrategy(); // A BufferStrategy is a way for the computer to draw something to the screen(container)
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics(); // Akin to a paintbrush - used to draw something to the screen
        g.clearRect(0, 0, width, height);
        
        if (State.getState() != null) {
            State.getState().render(g);
        }
        
        bs.show(); // these two lines help us finish drawing(we tell Java we want to display what we have drawn)
        g.dispose(); // dispose of the "paintbrush"
    }
    
   
    
    public void run() {
        
        // Initialize states in the game and game containers
        try {
            init();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FontFormatException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Starts up the menu theme for the game by providing the file path to it
        // File Path changes depending on game state
        try
        {
             Audio.filePath = "res/sound/main_theme.wav";
             audioPlayer = new Audio();
             audioPlayer.play();
           
   
        } 
         
        catch (Exception ex) 
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
         
          }
        
        // Generally speaking, the rate at which scenes in a game change is called Frames Per Second(FPS)
        // In my game, I also utilize the term "tick" or "ticks" 
        // The two are synonyms for how quickly our game runs
        // The game is currently capped at 60 FPS, as higher frame rates may not be desireable at this point
        int fps = 60;
        double tPerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime(); // returns current time in nanoseconds
        long timer = 0;
        int ticks = 0;
        
        // Algorithm used in deciding whether to render next frame or not
        // We work in nanoseconds to ensure that the game renders scenes to the Canvas
        // In the most smooth way possible
        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / tPerTick;
            timer += now - lastTime;
            lastTime = now;
            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }
            if (timer >= 1000000000) {
               System.out.println("Ticks and frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        
        stop();
    }
    
    // Getters
    
    public KeyManager getKeyManager() {
        return keys;
    }
    
    public MouseManager getMouseManager() {
        return mouse;
    }
    
    public GameCamera getCamera() {
        return camera;
    }
    
    public int getWidthWindow() {
        return width;
    }
    
    public int getHeightWindow() {
        return height;
    }

    public Audio getAudioPlayer() {
        return audioPlayer;
    }
    
    
    
    
    public synchronized void start() {
        if (running) {
            return; // this code prevents us from reinitializing the start() method and all the threads and variables associated
        }
        running = true;
        thread1 = new Thread(this); // this keyword here means that we are running the Game class on thread1
        thread1.start(); // this thread1.start() call actually calls the run() method we have defined
    }
    
    public synchronized void stop() {
        if (!running) {
            return; // same as the one in start, we want to stop the program only once
        }
        running = false;
        try {
            thread1.join(); // this method closes down our thread PROPERLY
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
