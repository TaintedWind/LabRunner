package item;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import player.Inventory;
import database.ObjectList;
import engine.Physics;
import engine.Timer;
import item.projectiles.Arrow;
import enemy.AI;

//All items extend this class.
public class Item extends Physics {

    public Image defaultTexture;
    public Image leftFacingTexture;
    public Image rightFacingTexture;
    public Image inventoryTexture;
    protected boolean isEquippable;
    protected Timer explosionTimer;
    int handleLength; //positioning
    protected int offsetX;
    protected int offsetY;
    public int ID; //item id for crafting
    public int damage;
    protected int strength;
    public int ammoAmount;
    protected String category; //the category and action that the item falls under (used for reference below)
    protected String material;
    public String name;

    //this method is overriden by the different types of items (such as item.weapon or item.food)
    public void update() {
    }

    public void checkForEquip() {
        if (database.GlobalVariables.E == true && ObjectList.player.hitbox.intersects(hitbox) && Inventory.contains(this) == false) {
            Inventory.add(this);
        }
    }

    public void alignToPlayer() {
        if (ObjectList.player.facingDir == "left") {
            X = ObjectList.player.X - W + offsetX;
            Y = ObjectList.player.Y + offsetY;
        } else if (ObjectList.player.facingDir == "right") {
            X = ObjectList.player.X + ObjectList.player.W - offsetX;
            Y = ObjectList.player.Y + offsetY;
        }
    }

    //does not follow lowerCamelCase because throw is a java keyword
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
            ((AI) getCollidingEnemy(ObjectList.player.range)).health(-damage, this);
        }

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

        /*g.setColor(Color.red);
         g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
         g.setColor(Color.white);*/

    }
}
