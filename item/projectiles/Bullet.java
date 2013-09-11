package item.projectiles;

import item.Item;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import database.ObjectList;

public class Bullet extends Projectile {

    public Bullet(int x, int y, double xdir, Object p) {

        this.X = x;
        this.Y = y;
        this.W = 32;
        this.H = 16;

        this.parentWeapon = p;

        this.damage = 10;

        this.deleteOnTouch = true;
        this.isAffectedByGravity = false;
        this.knockbackStrength = 0.01;

        this.dx = xdir;
        this.dy = getAngleOfElevation() * 1.5;

        try {
            defaultTexture = new Image("bullet.png", false, Image.FILTER_NEAREST);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        rotateImageToTarget();

        ObjectList.items.add(this);

    }
}
