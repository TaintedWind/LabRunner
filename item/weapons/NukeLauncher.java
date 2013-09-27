package item.weapons;

import item.projectiles.Bullet;
import item.projectiles.Missile;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import database.ObjectList;

public class NukeLauncher extends Weapon {

    public NukeLauncher(int x, int y) {

        this.X = x;
        this.Y = y;
        this.W = 64;
        this.H = 32;
        this.offsetX = -32;
        this.offsetY = 40;

        this.ammoAmount = 20;
        
        this.ID = 5;

        this.numberOfHands = 1;
        this.category = "ranged";
        this.name = "ROCKET LAUNCHER";

        try {
            defaultTexture = new Image("./resources/nuke_launcher.png", false, Image.FILTER_NEAREST);
            rightFacingTexture = new Image("./resources/nuke_launcher.png", false, Image.FILTER_NEAREST);
            leftFacingTexture = rightFacingTexture.getFlippedCopy(true, false);
            inventoryTexture = new Image("./resources/nuke_launcher_icon.png", false, Image.FILTER_NEAREST);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        ObjectList.items.add(this);

    }

    public void shoot() {

        if (ObjectList.player.facingDir == "right") {
            new Missile((int) X + W, (int) Y, 0.75, this);
        } else {
            new Missile((int) X, (int) Y, -0.75, this);
        }

    }
}
