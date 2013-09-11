package item.projectiles.grapple;

import item.Item;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import database.ObjectList;
import item.projectiles.Projectile;
import main.Screen;

public class Plunger extends Grapple {

    public Plunger(int x, int y, double xdir, Object p) {

        this.X = x;
        this.Y = y;
        this.W = 48;
        this.H = 16;

        this.parentWeapon = p;

        this.damage = 1;

        this.dx = xdir;
        this.dy = getAngleOfElevation();
        
        this.initialDX = dx;
        this.initialDY = dy;

        this.isAffectedByGravity = true;

        this.parentWeapon = p;

        try {
            defaultTexture = new Image("plunger_projectile.png", false, Image.FILTER_NEAREST);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        rotateImageToTarget();

        ObjectList.items.add(this);

    }
}
