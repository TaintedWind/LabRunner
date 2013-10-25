package item.explosives;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import particle.ParticleFactory;
import player.Inventory;

import database.ObjectList;
import engine.Timer;
import item.Item;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Explosive extends Item {

    Timer detonationTimer;
    
    public Explosive(String n, int ID, double x, double y) {
        this.X = x;
        this.Y = y;
        
        
        if (n.equals("GRENADE") || (n == "RANDOM" && ID == 1)) {
            this.offsetX = 20;
            this.offsetY = 45;
            this.category = "explosives";
            this.name = "GRENADE";
            try {
                defaultTexture = new Image("./resources/bomb.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/bomb.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = new Image("./resources/bomb.png", false, Image.FILTER_NEAREST);
                inventoryTexture = new Image("./resources/bomb.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println(n+" ("+ID+") is not a valid item!");
        }
        
        ObjectList.items.add(this);
        
    }

    public void update() {
        
        W = defaultTexture.getWidth();
        H = defaultTexture.getHeight();

        if (detonationTimer != null) {
            if (detonationTimer.getTime() >= 750) {
                System.out.println("BOOM");
                ParticleFactory.createExplosion(X, Y);
                delete();
            }
        }

        hitbox.setBounds((int) X, (int) Y, W, H);
        topHitbox.setBounds((int) X, (int) Y, W, H / 3);
        middleHitbox.setBounds((int) X, (int) Y + (H / 3), W, H / 2);
        bottomHitbox.setBounds((int) X, (int) Y + H - bottomHitbox.height, W, H / 5);

        //move the explosive
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

    public void leftClickAction() {
    }

    public void rightClickAction() {
        Throw();
    }

    public void Throw() {

        detonationTimer = new Timer(750, true, true);

        if (Inventory.contains(this)) {
            dx = 0.75;
            dy = getAngleOfElevation();
            Inventory.dropSelectedItem();
        }
    }

    public void Detonate() {
        ParticleFactory.createExplosion(X, Y);
    }

    public double getAngleOfElevation() {
        double targetX = Mouse.getX();
        double targetY = 600 - Mouse.getY();

        double heightOfOpposite, heightOfAdjacent;

        if (ObjectList.player.facingDir == "right") {
            heightOfOpposite = targetY -= ObjectList.player.Y;
            heightOfAdjacent = targetX -= ObjectList.player.X;
        } else {
            heightOfOpposite = targetY -= ObjectList.player.Y;
            heightOfAdjacent = ObjectList.player.X - targetX;
        }

        double tan = heightOfOpposite / heightOfAdjacent;

        double angle = Math.atan(tan);

        return angle;

    }
}
