package item.potions;

import player.Inventory;
import database.ObjectList;
import item.Item;

public class Potion extends Item {

    public void update() {

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
        use();
    }

    public void use() {
        
    }
}
