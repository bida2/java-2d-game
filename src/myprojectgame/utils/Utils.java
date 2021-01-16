/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojectgame.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author my
 */
public class Utils {
    // Class that provides us with utility methods 
    // Example below is used to read a text file for creating our game's world
    public static String loadFileAsString (String path) {
        StringBuilder builder = new StringBuilder(); // StringBuiler class helps us add characters to a string easily
        
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line + "\n");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
    
    public static int parseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            e.printStackTrace(); // Prints error to screen
            return 0;
        }
    }
    
}
