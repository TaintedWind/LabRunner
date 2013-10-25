package particle.exploding;

import enemy.AI;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import database.ObjectList;
import engine.Physics;
import engine.Timer;
import item.Item;
import java.awt.Rectangle;
import particle.Particle;
import powerup.PowerUp;

public class ExplosiveParticles extends Particle {

    //work in progress
    public Image defaultTexture;
    public boolean isAnimated, hasKnockback;
    public Image anim1, anim2, anim3, anim4, anim5, anim6, anim7;
    Timer animationTimer, knockbackTimer;
    
    Rectangle range;

    @Override
    public void update() {
         
        range.setBounds((int)X - 50, (int)Y - 50, W + 50, H + 50);

        if (animationTimer == null) {
            animationTimer = new Timer(400, true, true);
        }
        
        if (knockbackTimer == null) {
            knockbackTimer = new Timer(100, true, true);
        }

        if (isAnimated) {
            animate();
        }
        
        doKnockBack();
        
    }
    
    public void doKnockBack() {
        
        if (getCollidingEnemy(range) != null) {
            ((AI)getCollidingEnemy(range)).knockback(0.02, -0.01, this);
            ((AI)getCollidingEnemy(range)).health(-5, range);
        }

        if (ObjectList.player.hitbox.intersects(range)) {
            System.out.println("Player intersects range of "+this);
            ObjectList.player.knockback(0.04, -0.01, this);
            ObjectList.player.health(-5, this);
        }
        
        
    }

    public void animate() {
        if (animationTimer.getTime() > 400) {
            delete();
        }
    }

    public void draw(Graphics g) {
        
        System.out.println(animationTimer.getTime());

        if (animationTimer != null) {

            if (animationTimer.getTime() > -1 && animationTimer.getTime() < 100) {
                g.drawImage(anim1, (int) X, (int) Y, null);
            } else if (animationTimer.getTime() >= 100 && animationTimer.getTime() < 150) {
                g.drawImage(anim2, (int) X, (int) Y, null);
            } else if (animationTimer.getTime() >= 150 && animationTimer.getTime() < 200) {
                g.drawImage(anim3, (int) X, (int) Y, null);
            } else if (animationTimer.getTime() >= 200 && animationTimer.getTime() < 250) {
                g.drawImage(anim4, (int) X, (int) Y, null);
            } else if (animationTimer.getTime() >= 250 && animationTimer.getTime() < 300) {
                g.drawImage(anim5, (int) X, (int) Y, null);
            } else if (animationTimer.getTime() >= 300 && animationTimer.getTime() < 350) {
                g.drawImage(anim6, (int) X, (int) Y, null);
            } else if (animationTimer.getTime() >= 350 && animationTimer.getTime() <= 400) {
                g.drawImage(anim7, (int) X, (int) Y, null);
            }
        }

    }
}
