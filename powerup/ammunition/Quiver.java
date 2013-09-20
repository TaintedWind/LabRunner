package powerup.ammunition;

import database.ObjectList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import player.Inventory;
import powerup.PowerUp;

public class Quiver extends Ammunition {
    
    public Quiver(double x, double y, int s) {
        this.X = x;
        this.Y = y;
        this.strength = s;
        
        System.out.println("Creating new quiver");
        
        try {
            this.defaultTexture = new Image("placeholder.png", false, Image.FILTER_NEAREST);
        } catch (SlickException ex) {
            
        }
        
    }
    
    
}