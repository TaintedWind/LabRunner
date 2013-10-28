package item.projectiles;

import engine.Mouse;
import org.newdawn.slick.Graphics;

import particle.ParticleFactory;

import enemy.AI;
import database.ObjectList;
import engine.Physics;
import engine.Timer;

import item.Item;
import item.weapons.Weapon;
import main.Screen;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;

public class Projectile extends Item {

    boolean deleteOnTouch, explodesOnTouch, isAffectedByGravity, collidedOnce = false;
    double knockbackStrength;
    public Object parentWeapon;
    Timer despawnTimer = new Timer(10000, true, true);
    double initialDX, initialDY;
    Line rope = new Line(0, 0, 0, 0);
    public int value;
    
    public Projectile(String n, double x, double y, double xdir, Object p) {
        this.parentWeapon = p;
        this.X = x;
        this.Y = y;
        this.name = n;
        
        this.dx = xdir;
        this.dy = getAngleOfElevation();
        
        if (n.equals("ARROW")) {
            this.value = 2;
            this.damage = 1;
            this.dx = xdir;
            this.dy = getAngleOfElevation();
            this.deleteOnTouch = false;
            this.explodesOnTouch = false;
            this.isAffectedByGravity = false;
            this.knockbackStrength = 0.02;
            try {
                defaultTexture = new Image("./resources/arrow.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            
        } else if (n.equals("BULLET")) {
            this.value = 1;
            this.damage = 10;
            this.deleteOnTouch = true;
            this.isAffectedByGravity = false;
            this.knockbackStrength = 0.01;
            this.dx = xdir;
            this.dy = getAngleOfElevation();
            try {
                defaultTexture = new Image("./resources/bullet.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            
        } else if (n.equals("MISSILE")) {
            this.value = 10;
            this.damage = 10;
            this.parentWeapon = p;
            this.dx = xdir;
            this.dy = getAngleOfElevation();
            this.deleteOnTouch = true;
            this.explodesOnTouch = true;
            this.isAffectedByGravity = false;
            this.knockbackStrength = 0.02;
            try {
                defaultTexture = new Image("./resources/rocket.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else if (n.equals("SEED")) {
            this.value = 1;
            this.damage = 10;
            this.deleteOnTouch = true;
            this.isAffectedByGravity = false;
            this.knockbackStrength = 0.01;
            this.dx = xdir;
            this.dy = getAngleOfElevation();
            try {
                defaultTexture = new Image("./resources/seed.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else if (n.equals("GRAPPLE") && ((Weapon)parentWeapon).ammoLimitReached == false) {
            ((Weapon)parentWeapon).ammoLimitReached = true;
            this.dy = getAngleOfElevation();
            this.initialDX = dx;
            this.initialDY = dy;
            this.isAffectedByGravity = false;
            try {
                defaultTexture = new Image("./resources/plunger_projectile.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        
        } else if (n.equals("ANTIMATTER")) {
            this.value = 40;
            this.damage = 0;
            this.parentWeapon = p;
            this.dx = xdir;
            this.dy = getAngleOfElevation();
            this.deleteOnTouch = true;
            this.explodesOnTouch = false;
            this.isAffectedByGravity = false;
            this.knockbackStrength = 0;
            try {
                defaultTexture = new Image("./resources/antimatter.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            
        } else {
            System.err.println(n+" is not a valid item name!");
        }
        
        rotateImageToTarget();
        ObjectList.items.add(this);
        
    }

    public void update() {
        
        W = defaultTexture.getWidth();
        H = defaultTexture.getHeight();

        if (despawnTimer.getTime() >= 10000) {
            delete();
        }
        
        hitbox.setBounds((int) X, (int) Y, W, H);
        topHitbox.setBounds((int) X, (int) Y, W, H / 3);
        middleHitbox.setBounds((int) X, (int) Y + (H / 3), W, H / 2);
        bottomHitbox.setBounds((int) X, (int) Y + H - bottomHitbox.height, W, H / 5);
        
        if (this.name.equals("GRAPPLE")) {

            rope.set((int)((Item)parentWeapon).X + (int)((Item)parentWeapon).W / 2, (int)((Item)parentWeapon).Y + ((Item)parentWeapon).H / 2, (int)X + (W / 2), (int)Y + H);
            
            if (isColliding() == true) {
                
                collidedOnce = true;
                
                //this.dx = 0;
                this.dy = 0;

                if (despawnTimer == null) {
                    despawnTimer = new Timer(1000, true, true);
                }

                ObjectList.player.fallHeight = 0;
                ObjectList.player.fallSpeed = 0; 
                ObjectList.player.Y -= 0.01;
                if (gui.GameScreen.pansLeftRight) {
                    ObjectList.player.dx = initialDX / 1.5;
                    ObjectList.player.dy = initialDY / 1.5;
                } else {
                    ObjectList.player.dx = initialDX / 2;
                    ObjectList.player.dy = initialDY;
                }

                //if player reaches target, reset velocity and delete the projectile
                if (ObjectList.player.hitbox.intersects(hitbox)) {
                    ObjectList.player.dx = 0;
                    ObjectList.player.dy = 0;
                    delete();
                //if the player does not reach the target in the set amount of time or overshoots, delete the projectile
                } else if (despawnTimer.getTime() >= 1200 && ObjectList.player.hitbox.intersects(hitbox) == false) {
                    ObjectList.player.dx = 0;
                    ObjectList.player.dy = 0;
                    delete();
                }

                if (ObjectList.player.isCollidingWithLeftSide()) {
                    ObjectList.player.X -= 0.01;
                } else if (ObjectList.player.isCollidingWithRightSide()) {
                    ObjectList.player.X += 0.01;
                }

            } else if (isColliding() == false && collidedOnce == false) {
                velocity();
            } else {

                if (ObjectList.player.hitbox.intersects(hitbox) == false) {
                    ObjectList.player.fallHeight = 0;
                    ObjectList.player.fallSpeed = 0; 
                    ObjectList.player.Y -= 0.01;
                    if (gui.GameScreen.pansLeftRight) {
                        ObjectList.player.dx = initialDX / 1.5;
                        ObjectList.player.dy = initialDY / 1.5;
                    } else {
                        ObjectList.player.dx = initialDX / 2;
                        ObjectList.player.dy = initialDY / 1.5;
                    }
                } else {
                    ObjectList.player.dx = 0;
                    ObjectList.player.dy = 0;
                    delete();
                }
            }

            if (getCollidingLiquid(hitbox) != null || rope.length() > 500) {
                ObjectList.player.dx = 0;
                ObjectList.player.dy = 0;
                delete();
            }
            
        } else {
            
            if (this.name == "ANTIMATTER") {
                    if (ObjectList.player.facingDir == "right") {
                        if (X > Mouse.getX()) {
                            ParticleFactory.createBlackHole(X, Y);
                            delete();
                        }
                    } else {
                        if (X < Mouse.getX()) {
                            ParticleFactory.createBlackHole(X, Y);
                            delete();
                        }
                    }
            }

            //if projectile is not colliding, move based on X and Y velocity
            if (isColliding() == false) {
                if (isAffectedByGravity) {
                    gravity();
                }
                velocity();
            }

            //if marked as explosive, explode :P
            if (isColliding() && explodesOnTouch) {
                explode();
            }

            if (isColliding() && deleteOnTouch) {
                delete();
            }

            //gets the colliding enemy and does damage (then deletes itself)
            if (getCollidingEnemy(hitbox) != null) {
                ((Physics) getCollidingEnemy(hitbox)).knockback(knockbackStrength, -0.01, this);
                ((AI) getCollidingEnemy(hitbox)).health(-damage * ObjectList.player.attackMultiplier, ObjectList.player); //when ranged enemies are added, change from player's damage to the shooter's damage
                if (explodesOnTouch) {
                    explode();
                }
                delete();
            }
        }

    }

    public void explode() {
        ParticleFactory.createExplosion(X, Y);
        delete();
    }
    
    public void delete() {
        ObjectList.items.remove(this);
    }

    public double getAngleOfElevation() {

        //takes the parent of the projectile (aka the gun that shot it) as a parameter

        double targetX = Mouse.getX();
        double targetY = Mouse.getY();
        double heightOfOpposite, lengthOfAdjacent;

        if (ObjectList.player.facingDir == "right") {
            heightOfOpposite = targetY - ((Physics) parentWeapon).Y + ((Physics) parentWeapon).H / 2;
            lengthOfAdjacent = targetX - ((Physics) parentWeapon).X;
        } else {
            heightOfOpposite = targetY - ((Physics) parentWeapon).Y + ((Physics) parentWeapon).H / 2;
            lengthOfAdjacent = ((Physics) parentWeapon).X - targetX;
        }

        double angleInRadians = heightOfOpposite / lengthOfAdjacent;
        System.out.println(angleInRadians - 0.15);
        
        if (angleInRadians - 0.15 > 0) {
            if (angleInRadians > 1.5) {
                return 1.5;
            } else {
                return angleInRadians - 0.2;
            }
        } else {
            if (angleInRadians < -1.5) {
                return -1.5;
            } else {
                return angleInRadians - 0.15;
            }
        }
        

    }

    public void rotateImageToTarget() {
        
        //finish this!!! :\ if angle is > 1.2 then set angle to 60 or something

        //System.out.println(getAngleOfElevation() * 80 + 180);

        if (defaultTexture != null) {
            if (ObjectList.player.facingDir == "right") {
                if (getAngleOfElevation() * 80 < 60 && getAngleOfElevation() * 80 > -60) {
                    defaultTexture.setRotation((float) getAngleOfElevation() * 80);
                } else {
                    if (getAngleOfElevation() * 80 > 0) {
                        defaultTexture.setRotation((float) 60);
                    } else {
                        defaultTexture.setRotation((float) -60);
                    }
                }
            } else if (ObjectList.player.facingDir == "left") {
                if (getAngleOfElevation() * 80 < 60 && getAngleOfElevation() * 80 > -60) {
                    defaultTexture.setRotation((float) -getAngleOfElevation() * 80 + 180);
                } else {
                    if (getAngleOfElevation() * 80 < 0) {
                        defaultTexture.setRotation((float) 60 + 180);
                    } else {
                        defaultTexture.setRotation((float) -60 + 180);
                    }
                }
            }
        } else {
            System.err.println(this.name+" defaultTexture is null!");
        }
    }
    
    public void createNew(double x, double y) {
        new Projectile(name, x, y, dx, ObjectList.player);
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.drawImage(defaultTexture, (int)X, (int)Y, null);
        if (this.name.equals("GRAPPLE")) {
            g.setColor(Color.black);
            g.drawLine((int)((Item)parentWeapon).X + (int)((Item)parentWeapon).W / 2, (int)((Item)parentWeapon).Y + ((Item)parentWeapon).H / 2, (int)X + (W / 2), (int)Y + H);
        }
    }
}
