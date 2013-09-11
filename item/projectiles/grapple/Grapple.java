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
    int tether;
    
    Timer despawnTimer = new Timer(1500);
    
    Line rope = new Line(0, 0, 0, 0);

    public void update() {

        hitbox.setBounds((int) X, (int) Y, W, H);
        topHitbox.setBounds((int) X, (int) Y, W, H / 3);
        middleHitbox.setBounds((int) X, (int) Y + (H / 3), W, H / 2);
        bottomHitbox.setBounds((int) X, (int) Y + H - bottomHitbox.height, W, H / 5);
        
        rope.set((int)((Item)parentWeapon).X + (int)((Item)parentWeapon).W / 2, (int)((Item)parentWeapon).Y + ((Item)parentWeapon).H / 2, (int)X + (W / 2), (int)Y + H);
        
        if (despawnTimer.getTime() > 1500 || getCollidingLiquid(hitbox) != null) {
            delete();
        }
        
        //if projectile is not colliding, move based on X and Y velocity
        if (isColliding() == false) {
            //if rope reaches a certain length, shut. down. everything.
            if (rope.length() < 500) {
                velocity();
            } else {
                delete();
            }
        } else {
            if (ObjectList.player.hitbox.intersects(hitbox) == false && Inventory.getSelectedItem() == parentWeapon && rope.length() > 50) {
                ObjectList.player.dx = initialDX / 2;
                ObjectList.player.dy = initialDY;
                ObjectList.player.X += dx / 5;
                ObjectList.player.Y -= 0.1;
                ObjectList.player.fallHeight = 0;
                ObjectList.player.fallSpeed = 0;                
                
                
                
            } else {
                
                delete();
                
            }
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
