package item.potions.duration;

import player.Inventory;
import database.ObjectList;
import engine.Timer;
import item.Item;
import item.potions.Potion;

public class DurationPotion extends Potion {
    
    int duration;
    boolean isActive = false;
    public Timer durationTimer;

    public void update() {
        
        if (durationTimer == null) {
             durationTimer = new Timer(duration, false, false);
        }

        hitbox.setBounds((int) X, (int) Y, W, H);
        topHitbox.setBounds((int) X, (int) Y, W, H / 3);
        middleHitbox.setBounds((int) X, (int) Y + (H / 3), W, H / 2);
        bottomHitbox.setBounds((int) X, (int) Y + H - bottomHitbox.height, W, H / 5);

        //move the object
        gravity();
        velocity();

        checkForEquip();

        if (Inventory.contains(this)) {
            alignToPlayer();
        }
        
        //if is actively adding effects, hide it and check to see if it has expired
        if (isActive && duration > 0) {
            Inventory.remove(this);
            X = 9999;
            Y = 9999;
            affect();
            durationTimer.update();
            if (durationTimer.getTime() > duration) {
                unaffect();
                delete();
            }
        }

    }
    
    public void rightClickAction() {
        if (gui.GameScreen.rightMouseDown == false) {
            isActive = true;
            affect();
        }
    }
    
    public void affect() {
        
    }
    
    public void unaffect() {
        
    }
}