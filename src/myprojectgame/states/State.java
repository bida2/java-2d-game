/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.states;

import java.awt.Graphics;
import myprojectgame.Game;
import myprojectgame.Handler;

/**
 *
 * @author my
 */
public abstract class State {
    // Base abstract class that all game states are based on(extend)
    // Contains methods to get and set the game state 
    private static State currentState = null;
    public static void setState(State state){
        currentState = state;
    }
    public static State getState() {
        return currentState;
    }
    
    protected Handler handler;
    
    public State(Handler handler) {
        this.handler = handler;
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);
}
