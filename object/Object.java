package object;

import java.awt.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import region.Functions;

import database.ObjectList;
import engine.Physics;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.Point;

public class Object extends Physics {

    Image defaultTexture;
    String category, action;

    public void update() {

        hitbox.setBounds((int) X, (int) Y, W, H);
        topHitbox.setBounds((int) X, (int) Y, W, H / 3);
        middleHitbox.setBounds((int) X, (int) Y + (H / 3), W, H / 2);
        bottomHitbox.setBounds((int) X, (int) Y + H - bottomHitbox.height, W, H / 5);
        
        gravity();

        if (gui.GameScreen.rightMouseDown == true && hitbox.intersects(Mouse.getX(), 600 - Mouse.getY(), 1, 1) && ObjectList.player.range.intersects(hitbox)) {
            activate();
        }

    }

    public void activate() {

    }

    public void delete() {
        ObjectList.objects.remove(this);
    }

    public void draw(Graphics g) {
        g.drawImage(defaultTexture, (int) X, (int) Y);
    }
}
