package particle.vacuum;

import particle.exploding.*;
import ai.enemy.Enemy;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import database.ObjectList;
import engine.Physics;
import engine.Timer;
import item.Item;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import particle.Particle;
import powerup.PowerUp;

public class VacuumParticles extends Particle {

    //work in progress
    public Image defaultTexture;
    public boolean isAnimated, hasKnockback;
    public Image anim1, anim2, anim3, anim4, anim5, anim6, anim7;
    Timer animationTimer, knockbackTimer;
    
    Rectangle range;
    
    public VacuumParticles(String n, double x, double y) {
        if (n.equals("BLACK HOLE")) {
            this.X = x - 50;
            this.Y = y - 50;
            this.W = 100;
            this.H = 100;

            this.range = new Rectangle((int)X, (int)Y, W, H);
            this.isAnimated = true;
            try {
                this.anim1 = new Image("./resources/blackhole_1.png");
                this.anim2 = new Image("./resources/blackhole_2.png");
            } catch (SlickException ex) {
                Logger.getLogger(VacuumParticles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        ObjectList.particles.add(this);
    }

    @Override
    public void update() {
         
        range.setBounds((int)X - 200, (int)Y - 200, W + 400, H + 400);
        hitbox.setBounds((int)X + 50, (int)Y, 1, 100);

        if (animationTimer == null) {
            animationTimer = new Timer(200, true, true);
        }

        if (isAnimated) {
            animate();
        }
        
        eatEverything();
        
    }
    
    public void eatEverything() {
        
        if (range.contains(ObjectList.player.hitbox)) {
            if (ObjectList.player.X > range.x && ObjectList.player.X < range.x + (range.width / 2)) {
                ObjectList.player.dx = 0.7;
            } else if (ObjectList.player.X < range.x + range.width && ObjectList.player.X > range.x + (range.width / 2)) {
                ObjectList.player.dx = -0.7;
            }
            
            if (ObjectList.player.Y > range.y + (range.height / 2.5)) {
                ObjectList.player.dy = 0.2;
                ObjectList.player.fallHeight = 0;
            }
            
            if (ObjectList.player.hitbox.intersects(hitbox)) {
                ObjectList.player.health(-10, this);
            }
            
        }
        
        if (getCollidingEnemy(range) != null) {
            if (((Physics)getCollidingEnemy(range)).X > range.x && ((Physics)getCollidingEnemy(range)).X < range.x + (range.width / 2)) {
                ((Physics)getCollidingEnemy(range)).X += 0.5 * database.GlobalVariables.deltaTime;
            } else if (((Physics)getCollidingEnemy(range)).X < range.x + range.width && ((Physics)getCollidingEnemy(range)).X > range.x + (range.width / 2)) {
                ((Physics)getCollidingEnemy(range)).X -= 0.5 * database.GlobalVariables.deltaTime;
            }
            
            if (((Physics)getCollidingEnemy(range)).Y > range.y + (range.height / 2)) {
                ((Physics)getCollidingEnemy(range)).Y -= 0.2 * database.GlobalVariables.deltaTime;
                ((Physics)getCollidingEnemy(range)).fallHeight = 0;
                ((Physics)getCollidingEnemy(range)).dy = 0;
            }
            
            if (((Physics)getCollidingEnemy(hitbox)) != null) {
                ((Physics)getCollidingEnemy(hitbox)).health(-5, this);
            }
            
        }
        
        if (getCollidingItem(range) != null) {
            if (((Physics)getCollidingItem(range)).X > range.x && ((Physics)getCollidingItem(range)).X < range.x + (range.width / 2)) {
                ((Physics)getCollidingItem(range)).X += 0.5 * database.GlobalVariables.deltaTime;
            } else if (((Physics)getCollidingItem(range)).X < range.x + range.width && ((Physics)getCollidingItem(range)).X > range.x + (range.width / 2)) {
                ((Physics)getCollidingItem(range)).X -= 0.5 * database.GlobalVariables.deltaTime;
            }
            
            if (((Physics)getCollidingItem(range)).Y > range.y + (range.height / 2)) {
                ((Physics)getCollidingItem(range)).Y -= 0.2 * database.GlobalVariables.deltaTime;
                ((Physics)getCollidingItem(range)).fallHeight = 0;
                ((Physics)getCollidingItem(range)).dy = 0;
            }
            
            if (((Item)getCollidingItem(range)).hitbox.intersects(hitbox)) {
                ((Item)getCollidingItem(range)).delete();
            }
            
        }
        
    }

    public void animate() {
        if (animationTimer.getTime() > 399) {
            delete();
        }
    }

    public void draw(Graphics g) {

        if (animationTimer != null) {
            if (animationTimer.getTime() > -1 && animationTimer.getTime() <= 100) {
                g.drawImage(anim1, (int) X, (int) Y, null);
            } else if (animationTimer.getTime() > 100 && animationTimer.getTime() <= 200) {
                g.drawImage(anim2, (int) X, (int) Y, null);   
            }
        }

    }
}
