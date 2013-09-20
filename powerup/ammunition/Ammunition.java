package powerup.ammunition;

import database.ObjectList;
import item.Item;
import item.weapons.Weapon;
import org.newdawn.slick.Graphics;
import player.Inventory;
import powerup.PowerUp;

public class Ammunition extends PowerUp {
    public void update() {
        
        System.out.println("Updating "+this);
        
        hitbox.setBounds((int) X, (int) Y, W, H);
        gravity();
        velocity();

        if (hitbox.intersects(ObjectList.player.hitbox)) {
            affect(Inventory.getSelectedItem());
        }
    }
    
    public void affect(Object target) {
        System.out.println("Running affect method for "+this);
        ((Weapon)target).ammoAmount += strength;
        this.delete();
    }
    
    public void draw(Graphics g) {
        System.out.println("Drawing "+this);
        g.drawImage(defaultTexture, (int) X, (int) Y, null);
    }
    
}