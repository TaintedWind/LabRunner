package liquid;

import item.Item;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import enemy.AI;
import database.ObjectList;
import engine.Physics;
import engine.Timer;
import particles.ParticleFactory;
import player.Inventory;

public class Liquid extends Physics {

    Image defaultTexture;
    String category;
    int damage;
    public double sinkSpeed;
    Timer damageTimer, smokeTimer;
    org.newdawn.slick.geom.Rectangle textureBox = new org.newdawn.slick.geom.Rectangle(0, 0, 0, 0);

    public void update() {
        
        hitbox.setBounds((int) X, (int) Y, W, H);
        textureBox.setBounds((int) X, (int) Y, W, H);

        if (damageTimer == null) {
            damageTimer = new Timer();
        }
        
        if (smokeTimer == null) {
            smokeTimer = new Timer();
        }

        damageTimer.updateTimer();
        smokeTimer.updateTimer();
        
        if (smokeTimer.getTime() > Math.random() * 100000) {
            ParticleFactory.createSmoke(X, Y - 32, 0, -0.2, W);
            smokeTimer.reset();
        }

        if (damageTimer.getTime() > 500) {
            if (getCollidingEnemy(hitbox) != null && category == "danger") {
                ((AI) getCollidingEnemy(hitbox)).health(-damage, this);
            }
            if (ObjectList.player.hitbox.intersects(hitbox)) {
                ObjectList.player.health(-damage, this);
                
            }

            damageTimer.reset();
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
