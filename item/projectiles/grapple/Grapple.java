package item.projectiles.grapple;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;

import particles.ParticleFactory;

import enemy.AI;
import database.ObjectList;
import engine.Physics;
import engine.Timer;

import item.Item;
import item.projectiles.Projectile;
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
    
    Timer despawnTimer = new Timer();
    
    Line rope = new Line(0, 0, 0, 0);

    public void update() {

        hitbox.setBounds((int) X, (int) Y, W, H);
        topHitbox.setBounds((int) X, (int) Y, W, H / 3);
        middleHitbox.setBounds((int) X, (int) Y + (H / 3), W, H / 2);
        bottomHitbox.setBounds((int) X, (int) Y + H - bottomHitbox.height, W, H / 5);
        
        rope.set((int)((Item)parentWeapon).X + (int)((Item)parentWeapon).W / 2, (int)((Item)parentWeapon).Y + ((Item)parentWeapon).H / 2, (int)X + (W / 2), (int)Y + H);

        despawnTimer.updateTimer();
        
        if (despawnTimer.getTime() > 1500) {
            delete();
        }
        
        
        if (isCollidingWithGround()) {
            System.out.println(this+" is touching ground");
        }
        
        
        //if projectile is not colliding, move based on X and Y velocity
        if (isColliding() == false) {
            //if rope reaches a certain length, shut. down. everything.
            if (rope.length() < 400) {
                velocity();
            } else {
                delete();
                ObjectList.player.dx = 0;
                ObjectList.player.dy = 0;
                ObjectList.player.fallHeight = 0;
                ObjectList.player.fallSpeed = 0;
            }
        } else {
            if (ObjectList.player.hitbox.intersects(hitbox) == false && Inventory.getSelectedItem() == parentWeapon && rope.length() > 50) {
                ObjectList.player.Y -= 0.1;
                ObjectList.player.dx = initialDX / 2;
                ObjectList.player.dy = initialDY - 0.6;
                
                System.out.println(initialDY);
                
            } else {
                
                ObjectList.player.dx = 0;
                ObjectList.player.dy = 0;
                ObjectList.player.fallHeight = 0;
                ObjectList.player.fallSpeed = 0;
                
                delete();
                
            }
        }

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

    public void rotateImageToTarget() {

        //System.out.println(getAngleOfElevation() * 80 + 180);

        if (ObjectList.player.facingDir == "right") {
            if (getAngleOfElevation() * 80 < 60 && getAngleOfElevation() * 80 > -60) {
                defaultTexture.setRotation((float) getAngleOfElevation() * 80);
            } else {
                if (getAngleOfElevation() * 80 > 0) {
                    defaultTexture.setRotation((float) 60);
                } else {
                    defaultTexture.setRotation((float) -60);
                }
            }
        } else if (ObjectList.player.facingDir == "left") {
            if (getAngleOfElevation() * 80 < 60 && getAngleOfElevation() * 80 > -60) {
                defaultTexture.setRotation((float) -getAngleOfElevation() * 80 + 180);
            } else {
                if (getAngleOfElevation() * 80 < 0) {
                    defaultTexture.setRotation((float) 60 + 180);
                } else {
                    defaultTexture.setRotation((float) -60 + 180);
                }
            }
        }
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.drawImage(defaultTexture, (int)X, (int)Y, null);
        g.setColor(Color.black);
        g.drawLine((float)rope.getX1(), (float)rope.getY1(), (float)rope.getX2(), (float)rope.getY2());
    }
}
