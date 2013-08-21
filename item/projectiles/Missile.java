package item.projectiles;

import item.Item;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import database.ObjectList;

public class Missile extends Projectile {

    public Missile(double x, double y, double xdir, Object p) {

        this.X = x;
        this.Y = y;
        this.W = 32;
        this.H = 16;

        this.damage = 10;

        this.parentWeapon = p;

        this.dx = xdir;
        this.dy = getAngleOfElevation();

        this.deleteOnTouch = true;
        this.explodesOnTouch = true;
        this.isAffectedByGravity = false;

        try {
            defaultTexture = new Image("rocket.png", false, Image.FILTER_NEAREST);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        rotateImageToTarget();

        ObjectList.items.add(this);

    }
}
