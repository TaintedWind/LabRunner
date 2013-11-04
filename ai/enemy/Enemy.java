package ai.enemy;

import java.awt.Rectangle;

import org.lwjgl.util.Point;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import player.Player;
import database.ObjectList;
import engine.Physics;
import engine.Timer;
import gui.overlay.Overlay;
import item.projectiles.Projectile;
import particle.ParticleFactory;

/*this class defines all the movements that are possible for enemies (including those for land and air)
 * Enemies will override the update method to use only the appropriate methods
 */

public class Enemy extends Physics {

    int damage;
    int idleMovementDelay;
    double health, maxHealth;
    Image defaultTexture;
    public String state, name;
    Point target, t1, t2;
    Color skinColor = Color.white;
    Timer attackTimer, idleTimer;
    boolean explodesOnDeath;
    String ammo;
    Rectangle cliffDetection = new Rectangle(0, 0, 0, 0);

    public void update() {
        
        W = defaultTexture.getWidth();
        H = defaultTexture.getHeight();
        
        //update hitboxes
        hitbox.setBounds((int) X, (int) Y, W, H);
        topHitbox.setBounds((int) X, (int) Y, W, H / 3);
        middleHitbox.setBounds((int) X, (int) Y + (H / 3), W, H / 2);
        bottomHitbox.setBounds((int) X, (int) Y + H - bottomHitbox.height, W, H / 5);
        range.setBounds((int) X - 250, (int) Y - 50, 550, H + 50);

        //call gravity();
        gravity();
        velocity();

        if (range.intersects(ObjectList.player.hitbox) && hitbox.intersects(ObjectList.player.hitbox) == false || state == "hostile") {
            followPlayer();
        } else if (state == "idle") {
            doIdleMovements();
        }
        
        if (hitbox.intersects(ObjectList.player.hitbox) && attackTimer.getTime() > 1000) {
            attack(ObjectList.player);
        }

    }

    public void followPlayer() {
        
        if (ObjectList.player.X > X) {
            facingDir = "right";
        } else {
            facingDir = "left";
        }
        
        if (isCollidingWithGround() && isNearCliff() == false) {
            if (ObjectList.player.X > X) {
                moveRight();
            } else {
                moveLeft();
            }
        }
    }
    
    public void moveLeft() {
        if (getCollidingEnemy(hitbox) == null) {
            X -= 0.2 * database.GlobalVariables.deltaTime;
            facingDir = "left";
        } else {
            if (((Physics)getCollidingEnemy(hitbox)).X > X) {
                X--;
            } else {
                X++;
            }
        }
    }
    
    public void moveRight() {
        if (getCollidingEnemy(hitbox) == null) {
            X += 0.2 * database.GlobalVariables.deltaTime;
            facingDir = "right";
        } else {
            if (((Physics)getCollidingEnemy(hitbox)).X > X) {
                X--;
            } else {
                X++;
            }
        }
    }
    
    public void jump() {
        if (isCollidingWithGround()) {
            Y -= 0.1;
            dy = -0.3;
        }
    }
    
    public void shoot() {
        if (ObjectList.player.X > X) {
            new Projectile(ammo, X + W + 10, Y + (W / 2), 1, this, new java.awt.Point((int)ObjectList.player.X, (int)ObjectList.player.Y + 20));
        } else {
            new Projectile(ammo, X - 10, Y + (W / 2), -1, this, new java.awt.Point((int)ObjectList.player.X, (int)ObjectList.player.Y + 20));
        }
    }

    public void attack(Object target) {
        ((Physics)target).health(-damage, this);
        ((Physics)target).knockback(0.02, -0.01, this);

    }

    @Override
    public void health(double amount, Object attacker) {

        //System.out.println("Attacker: "+attacker);

        if (attacker == ObjectList.player) {
            state = "hostile";
        }

        if (amount < 0) {
            skinColor = Color.red;
            Overlay.newFloatingText(Double.toString(amount), X, Y - 5, Color.red);
        } else {
            Overlay.newFloatingText(Double.toString(amount), X, Y - 5, Color.green);
        }

        this.health += amount;

        if (this.health > maxHealth) {
            this.health = maxHealth;
        } else if (this.health < 0 || this.health == 0) {
            this.health = 0;
            if (explodesOnDeath) {
                ParticleFactory.createExplosion(X, Y);
            }
            this.delete();
        }
    }

    public void doIdleMovements() {

        if (idleTimer.getTime() > idleMovementDelay * 1000 && idleMovementDelay > 3) {
            if (target == t1) {
                target = t2;
            } else {
                target = t1;
            }

            //idleTimer.reset();
        }

        if (target == t2 && target.getX() > X) {
            X += 0.2 * database.GlobalVariables.deltaTime;
        } else if (target == t1 && target.getX() < X) {
            X -= 0.2 * database.GlobalVariables.deltaTime;
        } else {
        }

    }
    
    public boolean isNearCliff() {
        if (getCollidingPlatform(cliffDetection) != null) {
            return false;
        } else {
            return true;
        }
    }

    public void delete() {
        ObjectList.enemies.remove(this);
    }

    public void draw(Graphics g) {

        g.setColor(skinColor);
        g.drawImage(this.defaultTexture, (int) this.X, (int) this.Y, null);


        g.setColor(Color.gray);
        g.fillRect((int) X, (int) Y - 10, 50, 5);
        g.setColor(Color.green);
        g.fillRect((int) X, (int) Y - 10, (float) (health * (50 / maxHealth)), 5);

        skinColor = Color.white;
        g.setColor(Color.white);

        g.setColor(Color.red);
        g.drawRect(cliffDetection.x, cliffDetection.y, cliffDetection.width, cliffDetection.height);
        g.setColor(Color.blue);
        g.drawRect(range.x, range.y, range.width, range.height);
        g.setColor(Color.green);
        g.drawRect(lineofsight.x, lineofsight.y, lineofsight.width, lineofsight.height);
    }
}
