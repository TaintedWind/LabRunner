package item.utilities;

import database.ObjectList;
import player.Inventory;
import item.Item;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tool extends Item {

    int handleLength;
    
    //things that repair or manipulate other objects, also comes in two types: affecting and interacting
    
    public Tool(String n, double x, double y, boolean processImmediately) {
        
        this.X = x;
        this.Y = y;
        this.name = n;
        
        if (n.equals("PLUNGER")) {
            this.offsetX = 0;
            this.offsetY = 30;
            this.swings = true;
            try {
                defaultTexture = new Image("./resources/plunger.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/plunger_inverted.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = new Image("./resources/plunger_inverted.png", false, Image.FILTER_NEAREST);
                inventoryTexture = new Image("./resources/plunger_icon.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            
        } else if (n.equals("null")) {
            this.name = "NULL";
        }
        
        if (processImmediately) {
            ObjectList.items.add(this);
        }
        
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

        checkForEquip();

        if (Inventory.contains(this)) {
            if (Inventory.getSelectedItem() == this) {
                alignToPlayer();
            } else {
                X = -50;
                Y = -50;
            }
        }
    }

    public void alignToPlayer() {

        if (swings) {
            leftFacingTexture.setRotation(-45);
            rightFacingTexture.setRotation(45);
            defaultTexture.setRotation(0);
        }

        if (ObjectList.player.facingDir == "left") {
            X = ObjectList.player.X - W - offsetX;
            Y = ObjectList.player.Y + offsetY;
        } else if (ObjectList.player.facingDir == "right") {
            X = ObjectList.player.X + ObjectList.player.W + offsetX;
            Y = ObjectList.player.Y + offsetY;
        }

    }
    
    public void createNew(double x, double y) {
        System.out.println(name);
        new Tool(name, x, y, true);
    }
    
}
