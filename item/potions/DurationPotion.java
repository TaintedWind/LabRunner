package item.potions;

import player.Inventory;
import database.ObjectList;
import engine.Mouse;
import engine.Timer;
import item.Item;
import item.resources.Resource;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class DurationPotion extends Item {
    
    int duration;
    boolean isActive = false, affectOnce;
    public Timer durationTimer;
    
    
    public DurationPotion(String n, int ID, double x, double y) {
        this.X = x;
        this.Y = y;
        this.name = n;
        
        if (n.equals("INVINCIBILITY POTION") || (n.equals("RANDOM") && ID == 1)) {
            this.offsetX = 20;
            this.offsetY = 50;
            this.strength = (int)ObjectList.player.maxHealth;
            this.duration = 60000;
            this.category = "healing";
            this.affectOnce = false;

            try {
                defaultTexture = new Image("./resources/invincibility_potion.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/invincibility_potion.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = new Image("./resources/invincibility_potion.png", false, Image.FILTER_NEAREST);
                inventoryTexture = defaultTexture;
            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else if (n.equals("DEFENSE POTION") || (n.equals("RANDOM") && ID == 2)) {
            this.offsetX = 20;
            this.offsetY = 50;    
            this.strength = 2;
            this.duration = 60000;
            this.category = "defense";
            this.affectOnce = false;
            try {
                defaultTexture = new Image("./resources/defense_potion.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/defense_potion.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = new Image("./resources/defense_potion.png", false, Image.FILTER_NEAREST);
                inventoryTexture = defaultTexture;
            } catch (SlickException e) {
                e.printStackTrace();
            }
            
        } else if (n.equals("STRENGTH POTION") || (n.equals("RANDOM") && ID == 3)) {
            this.offsetX = 20;
            this.offsetY = 50;    
            this.strength = 2;
            this.duration = 60000;
            this.category = "strength";
            this.affectOnce = false;
            try {
                defaultTexture = new Image("./resources/defense_potion.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/defense_potion.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = new Image("./resources/defense_potion.png", false, Image.FILTER_NEAREST);
                inventoryTexture = defaultTexture;
            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else if (n.equals("JUMP BOOST POTION") || (n.equals("RANDOM") && ID == 4)) {
            this.offsetX = 20;
            this.offsetY = 50;    
            this.strength = 2;
            this.duration = 60000;
            this.category = "jumping";
            this.affectOnce = false;
            try {
                defaultTexture = new Image("./resources/strength_potion.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/strength_potion.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = new Image("./resources/strength_potion.png", false, Image.FILTER_NEAREST);
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
        
        //if is actively adding effects, hide it and check to see if it has expired
        if (isActive && duration > 0) {
            Inventory.remove(this);
            X = 9999;
            Y = 9999;
            if (affectOnce == false) {
                affect();
            }
            durationTimer.update();
            if (durationTimer.getTime() > duration) {
                unaffect();
                delete();
            }
        }

    }
    
    public void rightClickAction() {
        if (gui.GameScreen.rightMouseDown == false && getCollidingLevelObject(Mouse.getRectangle()) == null) {
            isActive = true;
            affect();
            new Resource("BOTTLE", 0, (int)ObjectList.player.X, (int)ObjectList.player.Y);
        }
    }
    
    public void affect() {
        if (category == "healing") {
            ObjectList.player.health(strength, this);
        } else if (category == "defense") {
            if (ObjectList.player.defenseMultiplier < strength) {
                ObjectList.player.defenseMultiplier += ObjectList.player.defenseMultiplier;
            }
        } else if (category == "strength") {
            if (ObjectList.player.attackMultiplier < strength) {
                ObjectList.player.attackMultiplier += strength;
            }
        } else if (category == "jumping") {
            if (ObjectList.player.jumpHeight < strength) {
                ObjectList.player.jumpHeight += strength;
            }
        }
    }
    
    public void unaffect() {
        
        System.out.println("Removed effects of: "+this.name);
        
        if (category == "healing") {
            //no need to remove any affects
        } else if (category == "defense") {
            ObjectList.player.defenseMultiplier = 1;
        } else if (category == "strength") {
            ObjectList.player.attackMultiplier = 1;
        } else if (category == "jumping") {
            ObjectList.player.jumpHeight = 1;
        }
    }
}