package item;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import player.Inventory;
import database.ObjectList;
import engine.Physics;
import engine.Timer;
import enemy.AI;
import item.explosives.Explosive;
import item.potions.DurationPotion;
import item.potions.Potion;
import item.resources.Resource;
import item.utilities.Tool;
import item.weapons.Weapon;

//All items extend this class.
public class Item extends Physics {

    public Image defaultTexture;
    public Image leftFacingTexture;
    public Image rightFacingTexture;
    public Image inventoryTexture;
    protected boolean swings;
    protected Timer explosionTimer;
    int handleLength;
    protected int offsetX;
    protected int offsetY;
    public double damage, strength; //strength is the strength of the potion or the velocity of the projectile that is shot
    public int ammoAmount;
    protected String category; //the category and action that the item falls under
    protected String material;
    public String name;
    
    Timer animationTimer = new Timer(1000, true, false);

    //this method is overriden by the different types of items (such as item.weapon or item.food)
    public void update() {
    }

    public void checkForEquip() {
        if (database.GlobalVariables.E == true && ObjectList.player.hitbox.intersects(hitbox) && Inventory.contains(this) == false) {
            Inventory.add(this);
        }
    }

    public void alignToPlayer() {
        
        if (swings) {
            leftFacingTexture.setRotation(-45);
            rightFacingTexture.setRotation(45);
            defaultTexture.setRotation(0);
        }
        
        if (ObjectList.player.facingDir == "left") {
            X = ObjectList.player.X - W + offsetX;
            Y = ObjectList.player.Y + offsetY;
        } else if (ObjectList.player.facingDir == "right") {
            X = ObjectList.player.X + ObjectList.player.W - offsetX;
            Y = ObjectList.player.Y + offsetY;
        }
    }

    public void use() {
        
    }

    public void swing() {
    }

    public void leftClickAction() {

    }

    public void rightClickAction() {
    }

    public void attack() {
        
        if (ObjectList.player.getCollidingEnemy(ObjectList.player.range) != null) {
            ((Physics) getCollidingEnemy(ObjectList.player.range)).knockback(0.02, -0.01, this);
            ((AI) getCollidingEnemy(ObjectList.player.range)).health(-damage * ObjectList.player.attackMultiplier, this);
        }

    }
    
    //creates an item, can specify the class for simple item spawning throughout the code
    public static Object newItem(String c, String n, int ID, double X, double Y, boolean addToInventory) {

        try {
            Object o;

            if (c.equals("class item.explosives.Explosive")) {
                o = new Explosive(n, ID, X, Y);
            } else if (c.equals("class item.potions.DurationPotion")) {
                o = new DurationPotion(n, ID, X, Y);
            } else if (c.equals("class item.potions.Potion")) {
                o = new Potion(n, ID, X, Y);
            } else if (c.equals("class item.utilities.Tool")) {
                o = new Tool(n, X, Y);
            } else if (c.equals("class item.weapons.Weapon")) {
                o = new Weapon(n, ID, X, Y);
            } else if (c.equals("class item.resources.Resource")) {
                o = new Resource(n, ID, X, Y);
            } else {
                System.err.println("Item class "+c+" does not exist!");
                o = null;
            }
            if (o != null) {
                if (addToInventory == true) {
                    Inventory.add(o);
                }
            }
            
            return o;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
        
    }

    public void delete() {
        
        Inventory.remove(this);
        ObjectList.items.remove(this);

    } 

    //render the item
    public void draw(Graphics g) {

        if (Inventory.contains(this)) {      
            if (Inventory.getSelectedItem() == this) {
                if (ObjectList.player.facingDir == "right") {
                    g.drawImage(rightFacingTexture, (int) X, (int) Y, null);
                } else if (ObjectList.player.facingDir == "left") {
                    g.drawImage(leftFacingTexture, (int) X, (int) Y, null);
                } else {
                    g.drawImage(defaultTexture, (int) X, (int) Y, null);
                }
            }

        } else {
            g.drawImage(defaultTexture, (int) X, (int) Y, null);
        }

//        g.setColor(Color.red);
//        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
//        g.setColor(Color.white);

    }
    
}
