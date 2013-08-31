package particles.floating;

import enemy.AI;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import database.ObjectList;
import engine.Physics;
import engine.Timer;
import item.Item;
import java.awt.Rectangle;
import particles.Particle;
import powerup.PowerUp;

public class FloatingParticles extends Particle {

    //work in progress
    public Image defaultTexture;
    Rectangle range;
    
    Timer deletionTimer = new Timer();

    public void update() {
        velocity();
        
        deletionTimer.updateTimer();
        
        if (deletionTimer.getTime() > 1000) {
            delete();
        }
        
    }

    public void Animate() {
        
    }

    public void draw(Graphics g) {
        g.drawImage(defaultTexture, (int)X, (int)Y, null);
    }
}
