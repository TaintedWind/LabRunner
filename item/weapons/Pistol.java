package item.weapons;

import item.projectiles.Bullet;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import database.ObjectList;

public class Pistol extends Weapon {

    public Pistol(int x, int y) {

        this.X = x;
        this.Y = y;
        this.W = 32;
        this.H = 24;
        this.handleLength = 0;
        this.offsetX = -5;
        this.offsetY = 40;

        this.category = "ranged";
        this.damage = 10;
        
        this.ammoAmount = 9999;
        
        this.name = "PISTOL";

        try {
            defaultTexture = new Image("pistol.png", false, Image.FILTER_NEAREST);
            rightFacingTexture = new Image("pistol.png", false, Image.FILTER_NEAREST);
            leftFacingTexture = rightFacingTexture.getFlippedCopy(true, false);
            inventoryTexture = new Image("pistol_icon.png", false, Image.FILTER_NEAREST);
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
