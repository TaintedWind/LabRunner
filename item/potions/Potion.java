package item.potions;

import player.Inventory;
import database.ObjectList;
import engine.Mouse;
import engine.Timer;
import item.Item;
import item.resources.Resource;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Potion extends Item {
    
    int duration;
    boolean isActive = false;
    public Timer durationTimer;
    boolean increaseMaxValue = false;
    
    public Potion(String n, int ID, double x, double y) {
        
        this.X = x;
        this.Y = y;
        this.name = n;
        
        if (n.equals("HEALTH POTION") || (n.equals("RANDOM") && ID == 1)) {
            this.offsetX = 20;
            this.offsetY = 50;
            this.strength = 50;
            this.category = "healing";
            this.increaseMaxValue = false;
            try {
                defaultTexture = new Image("./resources/health_potion.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/health_potion.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = new Image("./resources/health_potion.png", false, Image.FILTER_NEAREST);
                inventoryTexture = defaultTexture;
            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else if (n.equals("HEALTH BOOST POTION") || (n.equals("RANDOM") && ID == 2)) {
            this.offsetX = 20;
            this.offsetY = 50;
            this.strength = 50;
            this.category = "healing";
            this.increaseMaxValue = true;
            try {
                defaultTexture = new Image("./resources/health_potion.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/health_potion.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = new Image("./resources/health_potion.png", false, Image.FILTER_NEAREST);
                inventoryTexture = defaultTexture;
            } catch (SlickException e) {
                e.printStackTrace();
            }

        } else if (n.equals("CARRY WEIGHT POTION") || (n.equals("RANDOM") && ID == 3)) {
            this.offsetX = 20;
            this.offsetY = 50;
            this.strength = 9; //max 9 inventory slots
            this.category = "inventory";
            try {
                defaultTexture = new Image("./resources/defense_potion.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/defense_potion.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = new Image("./resources/defense_potion.png", false, Image.FILTER_NEAREST);
                inventoryTexture = defaultTexture;
            } catch (SlickException e) {
                e.printStackTrace();
            }
            
        } else {
            System.err.println(n+" is not a valid item name!");
        }
        
        ObjectList.items.add(this);
        
    }

    public void update() {
        
        W = defaultTexture.getWidth();
        H = defaultTexture.getHeight();
        
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
        if (getCollidingLevelObject(Mouse.getRectangle()) == null) {
            affect();
            new Resource("BOTTLE", 0, (int)ObjectList.player.X, (int)ObjectList.player.Y, true);
            delete();
        }
    }
    
    public void affect() {
        if (category == "healing") {
            if (increaseMaxValue) {
                ObjectList.player.maxHealth += strength;
            } else {
                ObjectList.player.health(strength, this); 
            }
        } else if (category == "inventory") {
            Inventory.setInventorySize((int)strength);
        }
    }
    
    public void unaffect() {
        
    }
}
