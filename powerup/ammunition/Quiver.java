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
        this.W = 32;
        this.H = 32;
        this.strength = s;
        
        try {
            this.defaultTexture = new Image("./resources/placeholder.png", false, Image.FILTER_NEAREST);
        } catch (SlickException ex) {
            ex.printStackTrace();
        }
        
        ObjectList.powerups.add(this);
        
    }
    
    
}