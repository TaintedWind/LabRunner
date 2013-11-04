package particle.floating;

import ai.enemy.Enemy;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import database.ObjectList;
import engine.Physics;
import engine.Timer;
import item.Item;
import java.awt.Rectangle;
import particle.Particle;
import powerup.PowerUp;

public class FloatingParticles extends Particle {

    //work in progress
    public Image defaultTexture;
    Rectangle range;
    
    Timer deletionTimer = new Timer(1000, true, true);

    public void update() {
        velocity();
        
        deletionTimer.update();
        
        if (deletionTimer.getTime() > 1000) {
            delete();
        }
        
    }

    public void animate() {
        
    }

    public void draw(Graphics g) {
        g.drawImage(defaultTexture, (int)X, (int)Y, null);
    }
}
