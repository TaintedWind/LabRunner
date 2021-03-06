package player;

import item.Item;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ai.enemy.Enemy;
import database.ObjectList;
import engine.Physics;
import engine.Timer;
import gui.overlay.Overlay;
import liquid.Liquid;

public class Player extends Physics {

    public Image defaultTexture, leftFacingTexture, rightFacingTexture;
    Color skinColor = Color.white;
    Timer animationTimer = new Timer(300, true, true);
    public String walkingDir;
    public double health = 1000000;
    public double jumpHeight = -0.6;
    public boolean isMoving;
    public double attackMultiplier = 1, defenseMultiplier = 1, maxHealth = 1000000;

    public Player(int x, int y) {

        this.X = x;
        this.Y = y;
        this.W = 48;
        this.H = 96;
        this.walkingDir = null;
        this.isMoving = false;

        try {
            defaultTexture = new Image("./resources/player.png", false, Image.FILTER_NEAREST);
            leftFacingTexture = new Image("./resources/walking_left.png", false, Image.FILTER_NEAREST);
            rightFacingTexture = leftFacingTexture.getFlippedCopy(true, false);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        System.out.println("Creating new player instance");

    }

    public void update() {

        hitbox.setBounds((int) X, (int) Y, W, H);
        topHitbox.setBounds((int) X, (int) Y, W, H / 3);
        middleHitbox.setBounds((int) X, (int) Y + (H / 3), W, H / 2);
        bottomHitbox.setBounds((int) X, (int) Y + H - bottomHitbox.height, W, H / 5);
        
        range.setBounds((int)X - 50, (int)Y, W + 100, H);

        if (facingDir == "left") {
            lineofsight.setBounds((int) X - 50, (int) Y, 75, H);
        } else {
            lineofsight.setBounds((int) X + 25, (int) Y, 75, H);
        }

        gravity();
        velocity();
        checkForMovement();

    }
    
    public void health(double amount, Object attacker) {
        
        System.out.println("Adding: "+amount / defenseMultiplier+" to player health");

        if (amount < 0) {
            skinColor = Color.red;
            Overlay.newFloatingText(Double.toString(amount), ObjectList.player.X, ObjectList.player.Y - 5, Color.red);
        } else {
            Overlay.newFloatingText(Double.toString(amount), ObjectList.player.X, ObjectList.player.Y - 5, Color.red);
        }
        
        this.health += amount / defenseMultiplier;
        System.out.println(defenseMultiplier);

        if (this.health < 0) {
            this.health = 0;
        } else if (this.health > maxHealth) {
            this.health = maxHealth;
        }

    }

    public void checkForMovement() {

        if (dx == 0) {
            if (database.GlobalVariables.A && isCollidingWithRightSide() == false) {
                this.X -= 0.3 * database.GlobalVariables.deltaTime;
                this.walkingDir = "left";
                this.facingDir = "left";
                this.isMoving = true;
            } else if (database.GlobalVariables.D && isCollidingWithLeftSide() == false) {
                this.X += 0.3 * database.GlobalVariables.deltaTime;
                this.walkingDir = "right";
                this.facingDir = "right";
                this.isMoving = true;
            } else {
                this.walkingDir = null;
                this.isMoving = false;
            }
        }

        this.X += this.dx * database.GlobalVariables.deltaTime;
        this.Y += fallSpeed;

    }
    
    public void jump() {
        if (getCollidingLiquid(hitbox) == null && isCollidingWithGround() == true) {
            Y -= 0.01;
            dy = jumpHeight;
        }
    }

    public void reset() {
        health = maxHealth;
        fallHeight = 0;
        fallSpeed = 0;
        dy = 0;
        dx = 0;
        
        Inventory.reset();

    }

    public void draw(Graphics g) {

        g.setColor(skinColor);

        if (walkingDir == null) {
            g.drawImage(defaultTexture, (int) X, (int) Y, null);
        } else if (walkingDir == "left") {
            if (animationTimer.getTime() > -1 && animationTimer.getTime() < 150) {
                g.drawImage(leftFacingTexture, (int)X, (int)Y, null);
            } else if (animationTimer.getTime() >= 150 && animationTimer.getTime() < 300) {
                g.drawImage(defaultTexture, (int) X, (int) Y, null);
            } else {
                g.drawImage(defaultTexture, (int) X, (int) Y, null);
                animationTimer.reset();
            }
        } else if (walkingDir == "right") {
            g.drawImage(rightFacingTexture, (int) X, (int) Y, null);
        }

        skinColor = Color.white;
        g.setColor(Color.white);

        /*g.setColor(Color.red);
         g.drawRect(this.topHitbox.x, this.topHitbox.y, this.topHitbox.width, this.topHitbox.height);
         g.setColor(Color.green);
         g.drawRect(this.middleHitbox.x, this.middleHitbox.y, this.middleHitbox.width, this.middleHitbox.height);
         g.setColor(Color.blue);
         g.drawRect(this.bottomHitbox.x, this.bottomHitbox.y, this.bottomHitbox.width, this.bottomHitbox.height);
         g.drawRect(range.x, range.y, range.width, range.height);
         g.setColor(Color.white);*/

    }
}