package liquid;

import item.Item;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import enemy.AI;
import database.ObjectList;
import engine.Physics;
import engine.Timer;

public class Liquid extends Physics {

    Image defaultTexture;
    String category;
    int damage;
    public double sinkSpeed;
    Timer damageTimer;
    org.newdawn.slick.geom.Rectangle textureBox = new org.newdawn.slick.geom.Rectangle(0, 0, 0, 0);

    public void update() {

        if (damageTimer == null) {
            damageTimer = new Timer();
        }

        damageTimer.updateTimer();

        hitbox.setBounds((int) X, (int) Y, W, H);
        textureBox.setBounds((int) X, (int) Y, W, H);

        if (damageTimer.getTime() > 500) {
            if (getCollidingEnemy(hitbox) != null && category == "danger") {
                ((AI) getCollidingEnemy(hitbox)).health(-damage, this);
            }
            if (ObjectList.player.hitbox.intersects(hitbox)) {
                ObjectList.player.health(-damage, this);
            }

            damageTimer.reset();
        }


        if (getCollidingItem(hitbox) != null && category == "danger") {
            ((Item) getCollidingItem(hitbox)).delete();
        }


    }

    public void delete() {
        ObjectList.liquids.remove(this);
    }

    public void draw(Graphics g) {

        g.setColor(Color.white);
        g.texture(textureBox, defaultTexture, 0.03f, 0.03f, false);
        g.setColor(Color.white);
    }
}
