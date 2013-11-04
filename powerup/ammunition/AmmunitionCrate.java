package powerup.ammunition;

import database.ObjectList;
import gui.overlay.Overlay;
import item.Item;
import item.projectiles.Projectile;
import item.weapons.Weapon;
import java.awt.Point;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import player.Inventory;
import powerup.PowerUp;

public class AmmunitionCrate extends PowerUp {
    
    String targetWeapon; //the weapon that the ammo pack will refill
    
    public AmmunitionCrate(double x, double y) {
        
        this.X = x;
        this.Y = y;
        
        try {
            this.defaultTexture = new Image("./resources/ammo_crate.png", false, Image.FILTER_NEAREST);
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
            affect(getWeaponWithLeastAmmo());
        }
    }
    
    public void affect(Object target) {
        try {
            
            System.out.println(((Item)target).name);
            
            if (Inventory.ammoAmount < 100 && target != null) {

                String a = ((Weapon)target).ammunition;
                Object aa = new Projectile(a, 9999, 9999, 1, this, new Point(0, 0));

                ((Weapon)target).ammoAmount += 40 / ((Projectile)aa).value; //grant up to 40 ammo points
                Inventory.ammoAmount += ((Projectile)aa).value * (40 / ((Projectile)aa).value);
                ((Item)aa).delete();
                Overlay.newFloatingText("+"+Integer.toString(40 / ((Projectile)aa).value)+" "+((Item)aa).name, ObjectList.player.X, ObjectList.player.Y, Color.green);
                this.delete();
            } else {
            }
        } catch (Exception e) {
        }
    }
    
    public Object getWeaponWithLeastAmmo() {
        
        Object o = null;
        int lowestAmmoAmount = 9999;
        int a;
        
        try {
            for (int i = 0; i <= Inventory.hotbar.length; i++) {
                a = ((Item)Inventory.getItem(i)).ammoAmount;
                if (((Item)Inventory.getItem(i)).getClass() == Weapon.class) {
                    if (a < lowestAmmoAmount) {
                        o = Inventory.getItem(i);
                        lowestAmmoAmount = a;
                    }
                }
            }
        } catch(Exception e) {
        }
        
        return o;
        
    }
    
    public void draw(Graphics g) {
        g.drawImage(defaultTexture, (int) X, (int) Y, null);
    }
    
}