package item.weapons;

import item.Item;

import item.projectiles.grapple.Plunger;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import database.ObjectList;

public class GrapplingHook extends Weapon {

    public GrapplingHook(int x, int y) {

        this.X = x;
        this.Y = y;
        this.W = 28;
        this.H = 48;
        this.offsetX = -5;
        this.offsetY = 40;
        
        this.ID = 3;

        this.numberOfHands = 1;
        this.category = "ranged";
        
        this.name = "GRAPPLING HOOK";
        
        this.ammoAmount = -1;

        try {
            defaultTexture = new Image("./resources/grapple.png", false, Image.FILTER_NEAREST);
            rightFacingTexture = new Image("./resources/grapple.png", false, Image.FILTER_NEAREST);
            leftFacingTexture = rightFacingTexture.getFlippedCopy(true, false);
            inventoryTexture = new Image("./resources/bow_icon.png", false, Image.FILTER_NEAREST);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        ObjectList.items.add(this);

    }

    public void shoot() {

        if (currentProjectile == null) {

            if (ObjectList.player.facingDir == "right") {
                currentProjectile = new Plunger((int) X + W, (int) Y, 0.85, this);
            } else {
                currentProjectile = new Plunger((int) X, (int) Y, -0.85, this);
            }

        }

    }
    
}
