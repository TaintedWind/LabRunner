package main;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.Date;
import org.lwjgl.opengl.Display;
import java.awt.DisplayMode;

import org.newdawn.slick.SlickException;

public class Screen {
    
    static DisplayMode[] modes;
    
    public static void toggleFullScreen() throws SlickException {
        if (main.Main.window.isFullscreen() == false) { 
            main.Main.window.setFullscreen(true);
        } else {
            main.Main.window.setFullscreen(false);           
        }
    }
    
    public static void changeWindowSize(int width, int height) throws SlickException {
        main.Main.window.setDisplayMode(width, height, false);
    }
    
    public static int getFPS() {
        return main.Main.window.getFPS();
    }
    
    public static float getWindowY() {
        return (float)Display.getY();
    }
    
    public static float getWindowX() {
        return (float)Display.getX();       
    }
    
    public static float getScreenWidth() {
        return Display.getWidth();
    }
    
    public static float getScreenHeight() {
        return Display.getHeight();
    }
    
    public static float getWindowWidth() {
        return main.Main.window.getWidth();
    }
    
    public static float getWindowHeight() {
        return main.Main.window.getHeight();        
    }
    
    public static int getRefreshRate() {
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        

        for (int i = 0; i < gs.length; i++) {
            DisplayMode dm = gs[i].getDisplayMode();

            int refreshRate = dm.getRefreshRate();
            
            if (refreshRate == DisplayMode.REFRESH_RATE_UNKNOWN) {
              System.out.println("Unknown rate"); 
            } else {
              return refreshRate;
            }

            int bitDepth = dm.getBitDepth();
            int numColors = (int) Math.pow(2, bitDepth);
        }
        
        return 60;
        
    }
    
}