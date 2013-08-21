package main;

import java.util.Date;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import org.newdawn.slick.SlickException;

public class Screen {
    
    static DisplayMode fullscreen, original = new DisplayMode(800, 600);
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
    
}