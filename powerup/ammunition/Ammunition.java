package powerup.ammunition;

import database.ObjectList;
import enemy.Scientist;
import item.Item;
import item.weapons.Weapon;
import org.newdawn.slick.Graphics;
import player.Inventory;
import powerup.PowerUp;

public class Ammunition extends PowerUp {
    
    public void update() {
        
        hitbox.setBounds((int) X, (int) Y, W, H);
        topHitbox.setBounds((int) X, (int) Y, W, H / 3);
        middleHitbox.setBounds((int) X, (int) Y + (H / 3), W, H / 2);
        bottomHitbox.setBounds((int) X, (int) Y + H - bottomHitbox.height, W, H / 5);
        
        gravity();
        velocity();

        if (hitbox.intersects(ObjectList.player.hitbox)) {
            affect(Inventory.getSelectedItem());
        }
    }
    
    public void affect(Object target) {
        ((Weapon)target).ammoAmount += strength;
        this.delete();
    }
    
    public void draw(Graphics g) {
        g.drawImage(defaultTexture, (int) X, (int) Y, null);
    }
    
}