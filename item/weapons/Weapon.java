package item.weapons;

import database.ObjectList;
import engine.Mouse;
import player.Inventory;
import item.Item;
import item.projectiles.Projectile;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Weapon extends Item {

    int handleLength;
    int numberOfHands;
    public boolean ammoLimitReached;
    public String ammunition;
    
    public Weapon(String n, int ID, double x, double y) {
        this.X = x;
        this.Y = y;
        this.name = n;
        
        if (n.equals("WAND") || (n.equals("RANDOM") && ID == 1)) {
            this.offsetX = 10;
            this.offsetY = 40;
            this.name = "WAND";
            this.swings = false;
            this.category = "ranged";
            this.ammoAmount = -1;
            this.ammunition = "ARROW";
            this.strength = 1;
            try {
                defaultTexture = new Image("./resources/wand.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/wand.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = leftFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/wand.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            
        } else if (n.equals("BLOWPIPE") || (n.equals("RANDOM") && ID == 2)) {
            this.offsetX = 25;
            this.offsetY = 30;
            this.ammoAmount = 20;
            this.numberOfHands = 1;
            this.category = "ranged";
            this.name = "BLOWPIPE";
            this.swings = false;
            this.strength = 1;
            this.ammunition = "SEED";
            try {
                defaultTexture = new Image("./resources/blow.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = new Image("./resources/blow.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = rightFacingTexture.getFlippedCopy(true, false);
                //inventoryTexture = new Image("./resources/n.png", false, Image.FILTER_NEAREST); get a new inventory texture
            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else if (n.equals("BOW") || (n.equals("RANDOM") && ID == 3)) {
            this.offsetX = 5;
            this.offsetY = 40;
            this.numberOfHands = 1;
            this.category = "ranged";
            this.ammoAmount = 20;
            this.ammunition = "ARROW";
            this.name = "BOW";
            this.strength = 1;

            try {
                defaultTexture = new Image("./resources/bow.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = new Image("./resources/bow.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = rightFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/bow_icon.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            
        } else if (n.equals("GRAPPLING HOOK") || (n.equals("RANDOM") && ID == 4)) {
            this.offsetX = 5;
            this.offsetY = 40;
            this.numberOfHands = 1;
            this.category = "ranged";
            this.name = "GRAPPLING HOOK";
            this.ammoAmount = -1;
            this.ammunition = "GRAPPLE";
            this.strength = 1;

            try {
                defaultTexture = new Image("./resources/grapple.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = new Image("./resources/grapple.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = rightFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/bow_icon.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            
        } else if (n.equals("KNIFE") || (n.equals("RANDOM") && ID == 5)) {
            this.offsetX = 0;
            this.offsetY = 40;
            this.damage = 1;
            this.handleLength = 6;
            this.swings = true;
            this.name = "KNIFE";
            this.ammoAmount = -1;

            try {
                defaultTexture = new Image("./resources/knife.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/knife.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = leftFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/knife.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            
        } else if (n.equals("ROCKET LAUNCHER") || (n.equals("RANDOM") && ID == 6)) {
            this.offsetX = 32;
            this.offsetY = 40;
            this.ammoAmount = 20;
            this.numberOfHands = 1;
            this.category = "ranged";
            this.name = "ROCKET LAUNCHER";
            this.ammunition = "MISSILE";
            this.strength = 1;
            try {
                defaultTexture = new Image("./resources/nuke_launcher.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = new Image("./resources/nuke_launcher.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = rightFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/nuke_launcher_icon.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            
        } else if (n.equals("PISTOL") || (n.equals("RANDOM") && ID == 7)) {
            this.offsetX = 5;
            this.offsetY = 40;
            this.category = "ranged";
            this.damage = 10;
            this.ammoAmount = 9999;
            this.name = "PISTOL";
            this.ammunition = "BULLET";

            try {
                defaultTexture = new Image("./resources/pistol.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = new Image("./resources/pistol.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = rightFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/pistol_icon.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else if (n.equals("IRON SWORD") || (n.equals("RANDOM") && ID == 8)) {
            this.offsetX = 0;
            this.offsetY = 10;
            this.damage = 6;
            this.swings = true;
            try {
                defaultTexture = new Image("./resources/sword_iron.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/sword_iron.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = new Image("./resources/sword_iron.png", false, Image.FILTER_NEAREST);
                inventoryTexture = new Image("./resources/sword_icon.png", false, Image.FILTER_NEAREST);

            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else if (n.equals("BLACK HOLE GUN") || (n.equals("RANDOM") && ID == 9)) {
            this.offsetX = 32;
            this.offsetY = 40;
            this.ammoAmount = 20;
            this.numberOfHands = 1;
            this.category = "ranged";
            this.name = "BLACK HOLE GUN";
            this.ammunition = "ANTIMATTER";
            this.strength = 1;
            try {
                defaultTexture = new Image("./resources/blackhole_gun.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = new Image("./resources/blackhole_gun.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = rightFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/blackhole_gun_icon.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(n+" is not a valid weapon name!");
        }
        
        ObjectList.items.add(this);
        
    }

    public void update() {
        
        //set width and height to the image size; makes it easier
        W = defaultTexture.getWidth();
        H = defaultTexture.getHeight() + 1;
        
        //System.out.println(this+": "+W+", "+H);

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
                X = -9999;
                Y = -9999;
            }
        }
    }

    public void leftClickAction() {
        swing();
        if (gui.GameScreen.leftMouseDown == false) {
            attack();
        }
    }

    public void rightClickAction() {
        if (gui.GameScreen.rightMouseDown == false && getCollidingLevelObject(Mouse.getRectangle()) == null) {
            if ((category == "ranged" && ammoAmount > 0) || ammoAmount == -1) {

                    if (Mouse.getX() > ObjectList.player.X) {
                        ObjectList.player.facingDir = "right";
                    } else {
                        ObjectList.player.facingDir = "left";  
                    }

                    shoot();

                    if (ammoAmount != -1) {
                        ammoAmount--;
                    }

            }
        }
    }

    public void swing() {
        if (swings) {
            if (ObjectList.player.facingDir == "right") {
                ((Item) Inventory.getSelectedItem()).rightFacingTexture.setRotation(90);
                Y += W;
                X += handleLength;
            } else if (ObjectList.player.facingDir == "left") {
                ((Item) Inventory.getSelectedItem()).leftFacingTexture.setRotation(-90);
                Y += W;
                X -= handleLength;
            }
        }
        
    }

    public void shoot() {
        
        //spawn a new projectile and subtract the value from the amount of ammo you are carrying
        
        
        System.out.println(Inventory.ammoAmount);
        
        if (ObjectList.player.facingDir == "right") {
            Inventory.ammoAmount -= ((Projectile)new Projectile(this.ammunition, (int) X + W, (int) Y + (H / 2), strength, this)).value;
        } else {
            Inventory.ammoAmount -= ((Projectile)new Projectile(this.ammunition, (int) X + W, (int) Y + (H / 2), -strength, this)).value;
        }
    }
}