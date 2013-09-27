package powerup;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import database.ObjectList;
import engine.Physics;

public class PowerUp extends Physics {

    public Image defaultTexture;
    public String category;
    public int strength; // the amount it adds or subtracts from the value (adding ammo or health)

    public void update() {

        hitbox.setBounds((int) X, (int) Y, W, H);
        topHitbox.setBounds((int) X, (int) Y, W, H / 3);
        middleHitbox.setBounds((int) X, (int) Y + (H / 3), W, H / 2);
        bottomHitbox.setBounds((int) X, (int) Y + H - bottomHitbox.height, W, H / 5);
        
        gravity();
        velocity();

        if (hitbox.intersects(ObjectList.player.hitbox)) {
            affect(ObjectList.player.hitbox);
        }

    }

    public void delete() {
        ObjectList.powerups.remove(this);
    }

    public void affect(Object target) {
        if (category == "healing") {
            ObjectList.player.health(strength, this);
            delete();
        }
    }

    public void draw(Graphics g) {
        g.drawImage(defaultTexture, (int) X, (int) Y, null);
    }
}
