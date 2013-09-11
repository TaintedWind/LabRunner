package item.weapons;

import database.ObjectList;
import player.Inventory;
import item.Item;
import item.projectiles.Arrow;
import item.projectiles.Bullet;
import org.lwjgl.input.Mouse;

public class Weapon extends Item {

    int handleLength;
    int numberOfHands;
    boolean swings;
    public Object currentProjectile;

    public void update() {

        hitbox.setBounds((int) X, (int) Y, W, H);
        topHitbox.setBounds((int) X, (int) Y, W, H / 3);
        middleHitbox.setBounds((int) X, (int) Y + (H / 3), W, H / 2);
        bottomHitbox.setBounds((int) X, (int) Y + H - bottomHitbox.height, W, H / 5);

        gravity();
        velocity();
        checkForEquip();

        if (Inventory.contains(this)) {
            if (Inventory.getSelectedItem() == this) {
                alignToPlayer();
            } else {
                X = -50;
                Y = -50;
            }
        }
    }

    public void leftClickAction() {
        swing();
        if (gui.GameScreen.leftMouseDown == false) {
            if (category == "ranged" && ammoAmount > 0 || category == "ranged" && ammoAmount == -1) {
                
                if (Mouse.getX() > ObjectList.player.X) {
                    ObjectList.player.facingDir = "right";
                } else {
                    ObjectList.player.facingDir = "left";  
                }
                
                shoot();
                if (ammoAmount != -1) {
                    ammoAmount--;
                }
                
            } else {
                attack();
            }
        }
    }

    public void rightClickAction() {

    }

    public void swing() {
        if (swings) {
            if (ObjectList.player.facingDir == "right") {
                ((Item) Inventory.getSelectedItem()).rightFacingTexture.setRotation(90);
                Y += W;
                X += handleLength;
            } else if (ObjectList.player.facingDir == "left") {
                ((Item) Inventory.getSelectedItem()).leftFacingTexture.setRotation(-90);
                Y += W;
                X -= handleLength;
            }
        }
    }

    public void shoot() {
        //overriden by the ranged weapon
    }

    public void alignToPlayer() {

        if (swings) {
            leftFacingTexture.setRotation(-45);
            rightFacingTexture.setRotation(45);
            defaultTexture.setRotation(0);
        }

        if (ObjectList.player.facingDir == "left") {
            X = ObjectList.player.X - W - offsetX;
            Y = ObjectList.player.Y + offsetY;
        } else if (ObjectList.player.facingDir == "right") {
            X = ObjectList.player.X + ObjectList.player.W + offsetX;
            Y = ObjectList.player.Y + offsetY;
        }

    }
}
