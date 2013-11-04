package ai.enemy;

import database.ObjectList;
import engine.Timer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class RangedEnemy extends Enemy {
    
    public RangedEnemy(String n, double x, double y) {
        
        this.X = x;
        this.Y = y;
        
        this.name = n;
        
        if (n.equals("ROBOT")) {
            this.ammo = "BULLET";
            this.explodesOnDeath = true;
            this.damage = 10;
            this.maxHealth = 100;
            this.health = 100;
            this.state = "idle";
            try {
                this.defaultTexture = new Image("./resources/robot.png");
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
        
        this.attackTimer = new Timer(1500, true, true);
        this.idleTimer = new Timer(0, false, false);
        ObjectList.enemies.add(this);
        
    }
    
    public void update() {
        W = defaultTexture.getWidth();
        H = defaultTexture.getHeight();
        
        //update hitboxes
        hitbox.setBounds((int) X, (int) Y, W, H);
        topHitbox.setBounds((int) X, (int) Y, W, H / 3);
        middleHitbox.setBounds((int) X, (int) Y + (H / 3), W, H / 2);
        bottomHitbox.setBounds((int) X, (int) Y + H - bottomHitbox.height, W, H / 5);
        range.setBounds((int) X - 1000, (int) Y - 50, 2000, H + 50);
        
        if (facingDir == "left") {
            lineofsight.setBounds((int) X - 400, (int) Y, (int)X - (int) ObjectList.player.X + 10, H - 5);
            cliffDetection.setBounds((int) X - 32, (int) Y - 20, 1, H + 40);
        } else {
            lineofsight.setBounds((int) X + W, (int) Y, (int)ObjectList.player.X + 10 - (int)X, H - 5);
            cliffDetection.setBounds((int) X + W + 32, (int) Y - 20, 1, H + 40);
        }

        //call gravity();
        gravity();
        velocity();
        
        if ((ObjectList.player.hitbox.intersects(range) || state == "hostile") && getCollidingPlatform(lineofsight) == null) {
            followPlayer();
            if (attackTimer.getTime() > 1500) {
                shoot();
            }
        }
        
    }
     
}