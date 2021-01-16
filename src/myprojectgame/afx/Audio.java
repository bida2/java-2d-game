/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.afx;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author my
 */
public class Audio {
    // Audio class that allows us to play,pause and stop sound clips 
    
    // When changing music from one game state to the other, the resetAudioStream() method must be called
    // so that we can start an entirely different clip
    
    // to store current position
    Long currentFrame;
    public static Clip clip;
     
    // current status of clip
    String status;
     
    AudioInputStream audioInputStream;
   public static String filePath;
 
    // constructor to initialize streams and clip
    public Audio()
        throws UnsupportedAudioFileException,
        IOException, LineUnavailableException 
    {
        // create AudioInputStream object
        audioInputStream = 
                AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
         
        // create clip reference
        clip = AudioSystem.getClip();
         
        // open audioInputStream to the clip
        clip.open(audioInputStream);
         
        clip.loop(Clip.LOOP_CONTINUOUSLY);
}
    
     public void play() 
    {
        //start the clip
        clip.start();
    }
     
     public void stop() throws UnsupportedAudioFileException,
    IOException, LineUnavailableException 
    {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }
     
     public void pause() 
    { 
        this.currentFrame = this.clip.getMicrosecondPosition();
        clip.stop();
    }
     
     public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
                                            LineUnavailableException 
    {
        audioInputStream = AudioSystem.getAudioInputStream(
        new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
