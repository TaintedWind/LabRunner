package enemy;

import java.awt.Rectangle;

import org.lwjgl.util.Point;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import player.Player;
import database.ObjectList;
import engine.Physics;
import engine.Timer;

/*this class defines all the movements that are possible for enemies (including those for land and air)
 * Enemies will override the update method to use only the appropriate methods
 */

public class AI extends Physics {

    int damage;
    int idleMovementDelay;
    double health, maxHealth;
    Image defaultTexture;
    String state;
    Point target, t1, t2;
    Color skinColor = Color.white;
    Timer attackTimer = new Timer(1000, true, true), idleTimer = new Timer(0, false, false);

    public void update() {
        
        System.out.println(attackTimer.getTime());
        
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
        if (isCollidingWithGround()) {
            if (ObjectList.player.X > X) {
                X += 0.2 * database.GlobalVariables.deltaTime;
            } else {
                X -= 0.2 * database.GlobalVariables.deltaTime;
            }
        }
    }

    public void attack(Object target) {
        ObjectList.player.health(-damage, this);
        ObjectList.player.knockback(0.01, -0.01, this);

    }

    @Override
    public void health(double amount, Object attacker) {

        //System.out.println("Attacker: "+attacker);

        if (attacker == ObjectList.player) {
            state = "hostile";
        }

        if (amount < 0) {
            skinColor = Color.red;
        }

        this.health += amount;

        if (this.health > maxHealth) {
            this.health = maxHealth;
        } else if (this.health < 0 || this.health == 0) {
            this.health = 0;
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

        /*g.setColor(Color.red);
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        g.setColor(Color.blue);
        g.drawRect(range.x, range.y, range.width, range.height);*/
    }
}
