package item.potions.instant;

import player.Inventory;
import database.ObjectList;
import engine.Timer;
import item.Item;
import item.potions.Potion;

public class InstantPotion extends Potion {
    
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

    }
    
    public void rightClickAction() {
        affect();
        delete();
    }
    
    public void affect() {
        
    }
    
    public void unaffect() {
        
    }
}