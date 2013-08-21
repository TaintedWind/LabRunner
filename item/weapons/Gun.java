package item.weapons;

import item.projectiles.Bullet;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import database.ObjectList;

public class Gun extends Weapon {

    public Gun(int x, int y) {

        this.X = x;
        this.Y = y;
        this.W = 25;
        this.H = 25;
        this.handleLength = 0;
        this.offsetX = -10;
        this.offsetY = 40;

        this.category = "ranged";
        this.damage = 10;
        
        this.ID = 4;

        try {
            defaultTexture = new Image("pistol.png", false, Image.FILTER_NEAREST);
            rightFacingTexture = new Image("pistol.png", false, Image.FILTER_NEAREST);
            leftFacingTexture = rightFacingTexture.getFlippedCopy(true, false);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        ObjectList.items.add(this);

    }

    public void shoot() {

        if (ObjectList.player.facingDir == "right") {
            new Bullet((int) X + W, (int) Y, 2, this);
        } else {
            new Bullet((int) X, (int) Y, -2, this);
        }

    }
}
