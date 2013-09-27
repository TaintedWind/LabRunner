package item.projectiles;

import item.Item;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import database.ObjectList;
import main.Screen;

public class Arrow extends Projectile {

    public Arrow(int x, int y, double xdir, Object p) {

        this.X = x;
        this.Y = y;
        this.W = 32;
        this.H = 16;

        this.parentWeapon = p;

        this.damage = 1;

        this.dx = xdir;
        this.dy = getAngleOfElevation() * 1.5;

        this.deleteOnTouch = false;
        this.explodesOnTouch = false;
        this.isAffectedByGravity = false;
        this.knockbackStrength = 0.02;

        this.parentWeapon = p;

        try {
            defaultTexture = new Image("./resources/arrow.png", false, Image.FILTER_NEAREST);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        rotateImageToTarget();

        ObjectList.items.add(this);

    }
}
