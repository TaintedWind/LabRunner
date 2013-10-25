package engine;

import java.awt.Rectangle;
import main.Screen;

public class Mouse {
    
    public static int getX() {
        return org.lwjgl.input.Mouse.getX() ;
    }
    
    public static int getY() {
        return (int)Screen.getScreenHeight() - org.lwjgl.input.Mouse.getY();
    }
    
    public static Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), 1, 1);
    }
    
    public static int getScrollingDirection() {
        return org.lwjgl.input.Mouse.getDWheel();
    }
    
}