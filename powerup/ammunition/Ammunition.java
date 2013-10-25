package powerup.ammunition;

import database.ObjectList;
import enemy.Scientist;
import item.Item;
import item.projectiles.Projectile;
import item.weapons.Weapon;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import player.Inventory;
import powerup.PowerUp;

public class Ammunition extends PowerUp {
    
    String targetWeapon; //the weapon that the ammo pack will refill
    
    public Ammunition(double x, double y) {
        
        this.X = x;
        this.Y = y;
        
        try {
            this.defaultTexture = new Image("./resources/placeholder.png", false, Image.FILTER_NEAREST);
        } catch (SlickException ex) {
            ex.printStackTrace();
        }
        
        ObjectList.powerups.add(this);
    }
    
    public void update() {
        
        W = defaultTexture.getWidth();
        H = defaultTexture.getHeight();
        
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
        
        System.out.println(target);

        try {
            if (Inventory.ammoAmount < 100 && target != null) {

                String a = ((Weapon)target).ammunition;
                Object aa = new Projectile(a, 9999, 9999, 1, this);

                ((Weapon)target).ammoAmount += 40 / ((Projectile)aa).value; //grant up to 40 ammo points
                Inventory.ammoAmount += ((Projectile)aa).value * (40 / ((Projectile)aa).value);
                ((Item)aa).delete();
                this.delete();
            } else {
                System.err.println("Conditions not met; no ammo will be given!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void draw(Graphics g) {
        g.drawImage(defaultTexture, (int) X, (int) Y, null);
    }
    
}