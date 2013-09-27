package item.projectiles.grapple;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;

import particle.ParticleFactory;

import enemy.AI;
import database.ObjectList;
import engine.Physics;
import engine.Timer;

import item.Item;
import item.projectiles.Projectile;
import item.weapons.Weapon;
import main.Screen;
import org.lwjgl.util.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Line;
import player.Inventory;

public class Grapple extends Projectile {

    boolean isAffectedByGravity;
    Object parentWeapon;
    
    double initialDX, initialDY;
    float tether, lastLength;
    
    Timer despawnTimer;
    
    Line rope = new Line(0, 0, 0, 0);

    public void update() {

        hitbox.setBounds((int) X, (int) Y, W, H);
        topHitbox.setBounds((int) X, (int) Y, W, H / 3);
        middleHitbox.setBounds((int) X, (int) Y + (H / 3), W, H / 2);
        bottomHitbox.setBounds((int) X, (int) Y + H - bottomHitbox.height, W, H / 5);
        
        rope.set((int)((Item)parentWeapon).X + (int)((Item)parentWeapon).W / 2, (int)((Item)parentWeapon).Y + ((Item)parentWeapon).H / 2, (int)X + (W / 2), (int)Y + H);
     

        if (isColliding() == true) {
            
            if (despawnTimer == null) {
                despawnTimer = new Timer(1000, true, true);
            }

            ObjectList.player.fallHeight = 0;
            ObjectList.player.fallSpeed = 0; 
            ObjectList.player.Y -= 0.01;
            ObjectList.player.dx = initialDX / 2;
            ObjectList.player.dy = initialDY;
            
            //if player reaches target, reset velocity and delete the projectile
            if (ObjectList.player.hitbox.intersects(hitbox)) {
                ObjectList.player.dx = 0;
                ObjectList.player.dy = 0;
                delete();
            //if the player does not reach the target in the set amount of time, delete the projectile
            } else if (despawnTimer.getTime() >= 500 && ObjectList.player.hitbox.intersects(hitbox) == false) {
                delete();
            }
            
            if (ObjectList.player.isCollidingWithLeftSide()) {
                ObjectList.player.X -= 0.01;
            } else if (ObjectList.player.isCollidingWithRightSide()) {
                ObjectList.player.X += 0.01;
            }
            
        } else {
            velocity();
        }
        
        if (getCollidingLiquid(hitbox) != null || rope.length() > 500) {
            delete();
        }

    }
    
    public void delete() {
        ObjectList.items.remove(this);
        ((Weapon)parentWeapon).currentProjectile = null;
    }

    public void explode() {
        ParticleFactory.createExplosion(X, Y);
        delete();
    }
    
    public double getAngleOfElevation() {

        //takes the parent of the projectile (aka the gun that shot it) as a parameter

        double targetX = Mouse.getX();
        double targetY = Screen.getWindowHeight() - Mouse.getY();

        double heightOfOpposite, lengthOfAdjacent;

        if (ObjectList.player.facingDir == "right") {
            heightOfOpposite = targetY - ((Physics) parentWeapon).Y;
            lengthOfAdjacent = targetX - ((Physics) parentWeapon).X;
        } else {
            heightOfOpposite = targetY - ((Physics) parentWeapon).Y;
            lengthOfAdjacent = ((Physics) parentWeapon).X - targetX;
        }

        double tan = heightOfOpposite / lengthOfAdjacent;

        double angle = Math.atan(tan);

        return angle;

    }
    
    public void draw(Graphics g) {
        
        g.setColor(Color.white);
        g.drawImage(defaultTexture, (int)X, (int)Y, null);
        g.setColor(Color.black);
        g.drawLine((float)rope.getX1(), (float)rope.getY1(), (float)rope.getX2(), (float)rope.getY2());
    }
}
