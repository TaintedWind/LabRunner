package item.food;

import player.Inventory;
import database.ObjectList;
import item.Item;

public class Food extends Item {

    public void update() {

        hitbox.setBounds((int) X, (int) Y, W, H);

        //move the object
        gravity();
        velocity();

        checkForEquip();

        if (Inventory.contains(this)) {
            alignToPlayer();
        }

    }

    public void use() {
        System.out.println("Using " + this);
        ObjectList.player.health(strength, this);
        delete();
    }
}
