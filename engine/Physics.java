package engine;

import database.ObjectList;
import java.awt.Rectangle;

import liquid.Liquid;

import platform.Platform;
import ai.enemy.Enemy;
import item.Item;
import item.projectiles.Projectile;
import levelobject.LevelObject;
import player.Inventory;
import player.Player;
import powerup.PowerUp;

public class Physics {

    public int fallHeight, jumpHeight, fallSpeed;
    public boolean isCollidingWithGround, isCollidingWithLeftSide, isCollidingWithRightSide, isCollidingWithBottom, isColliding;
    public Rectangle hitbox = new Rectangle();
    public Rectangle bottomHitbox = new Rectangle();
    public Rectangle middleHitbox = new Rectangle();
    public Rectangle topHitbox = new Rectangle();    
    public Rectangle lineofsight = new Rectangle();
    public Rectangle range = new Rectangle();
    public double X, Y, dx, dy; //x, y, velocity
    public int W, H; //size
    
    public String facingDir = "left";
    
    public void gravity() {

        if (isCollidingWithGround() == false && isCollidingWithLiquid() == false) {
            this.fallHeight++;
            this.fallSpeed = (this.fallHeight * database.GlobalVariables.deltaTime) / 50;
            this.Y += this.fallSpeed;
        } else if (isCollidingWithGround() == true) {

            if (fallHeight > 50) {
                this.health(-fallHeight / 5, null);
            }

            fallHeight = 0;
            fallSpeed = 0;
        }

        if (isCollidingWithLiquid()) {
            fallSpeed = 0;
            fallHeight = 0;
            Y += ((Liquid) getCollidingLiquid(hitbox)).sinkSpeed * database.GlobalVariables.deltaTime;
        }
        
        if (Y > 1000 && this.getClass() != Player.class) {
            //delete();
        }

        isCollidingWithBottom();

    }

    //moves the object according to dx and dy
    public void velocity() {

        if (isCollidingWithLeftSide() == false && isCollidingWithRightSide() == false) {
            X += dx * database.GlobalVariables.deltaTime;
        } else {
            dx = 0;
        }
        
        if (isCollidingWithGround() == false && isCollidingWithBottom() == false && isCollidingWithLiquid() == false) {
            Y += dy * database.GlobalVariables.deltaTime;
        } else {
            dy = 0;
        }
        
    }

    public void knockback(double xvel, double yvel, Object attacker) {
        
        if (this.isCollidingWithBottom() == false && this.isCollidingWithLeftSide() == false && this.isCollidingWithRightSide() == false) {
        
            if (this == ObjectList.player) {
                if (((Physics) attacker).X + ((Physics) attacker).W / 2 < X + (W / 2)) {
                    Y -= 1;
                    dx = xvel * 1.5 * database.GlobalVariables.deltaTime;
                    System.out.println(attacker+" did knockback "+xvel+" on "+this);
                } else {
                    Y -= 1;
                    dx = -xvel * database.GlobalVariables.deltaTime;
                    System.out.println(attacker+" did knockback "+xvel * -1+" on "+this);
                }            
            } else {
                if (attacker == Inventory.getSelectedItem() || ((Projectile)attacker).parentEntity == ObjectList.player) {
                    if (ObjectList.player.X + ObjectList.player.W / 2 < X + (W / 2)) {
                        Y -= 1;
                        dx = +xvel * 1.5 * database.GlobalVariables.deltaTime;
                        System.out.println(attacker+" did knockback "+xvel+" on "+this);
                    } else {
                        Y -= 1;
                        dx = -xvel * database.GlobalVariables.deltaTime;
                        System.out.println(attacker+" did knockback "+xvel * -1+" on "+this);

                    }  
                } else {
                    if (((Physics) attacker).X + ((Physics) attacker).W / 2 < X) {
                        Y -= 1;
                        dx = xvel * 1.5 * database.GlobalVariables.deltaTime;
                        System.out.println(attacker+" did knockback "+xvel+" on "+this);
                    } else {
                        Y -= 1;
                        dx = -xvel * database.GlobalVariables.deltaTime;
                        System.out.println(attacker+" did knockback "+xvel * -1+" on "+this);
                    }            
                }
            }
        
        
            if (((Physics)attacker).Y + ((Physics)attacker).H / 2 > Y) {
               dy += yvel * database.GlobalVariables.deltaTime;
            } else {
                dy -= yvel * database.GlobalVariables.deltaTime;            
            }
        }
        

    }

    public Object getCollidingEnemy(Rectangle r) {

        try {
            for (int t = 0; t <= ObjectList.enemies.size(); t++) {
                if (r.intersects(((Enemy) ObjectList.enemies.get(t)).hitbox)) {
                    if (ObjectList.enemies.get(t) != this) {
                        return ObjectList.enemies.get(t);
                    }
                }

            }

        } catch (Exception e) {
        }

        return null;
    }
    
    public Object getCollidingPlatform(Rectangle r) {

        try {
            for (int t = 0; t <= ObjectList.platforms.size(); t++) {
                if (r.intersects(((Platform) ObjectList.platforms.get(t)).body)) {
                    return ObjectList.platforms.get(t);
                }

            }

        } catch (Exception e) {
        }

        return null;
    }

    public Object getCollidingLiquid(Rectangle r) {

        try {
            for (int t = 0; t <= ObjectList.liquids.size(); t++) {
                if (r.intersects(((Liquid) ObjectList.liquids.get(t)).hitbox)) {
                    return ObjectList.liquids.get(t);
                }

            }

        } catch (Exception e) {
        }

        return null;
    }

    public Object getCollidingItem(Rectangle r) {

        try {
            for (int t = 0; t <= ObjectList.items.size(); t++) {
                if (r.contains(((Item) ObjectList.items.get(t)).hitbox)) {
                    return ObjectList.items.get(t);
                }

            }

        } catch (Exception e) {
        }

        return null;
    }
    
    public Object getCollidingLevelObject(Rectangle r) {

        try {
            for (int t = 0; t <= ObjectList.objects.size(); t++) {
                if (r.intersects(((LevelObject) ObjectList.objects.get(t)).hitbox)) {
                    return ObjectList.objects.get(t);
                }

            }

        } catch (Exception e) {
        }

        return null;
    }
    
        public Object getCollidingPowerup(Rectangle r) {

        try {
            for (int t = 0; t <= ObjectList.powerups.size(); t++) {
                if (r.intersects(((PowerUp) ObjectList.powerups.get(t)).hitbox)) {
                    return ObjectList.powerups.get(t);
                }

            }

        } catch (Exception e) {
        }

        return null;
    }

    public boolean isColliding() {
        if (isCollidingWithGround() || isCollidingWithLeftSide() || isCollidingWithRightSide() || isCollidingWithBottom()) {
            isColliding = true;
            return true;
        } else {
            isColliding = false;
            return false;
        }
    }

    public boolean isCollidingWithGround() {

        for (int i = 0; i < ObjectList.platforms.size(); i++) {

            if (bottomHitbox.intersects(((Platform) ObjectList.platforms.get(i)).top)) {
                
                if (isCollidingWithLeftSide == false || isCollidingWithRightSide == false) {
                    Y = ((Platform) ObjectList.platforms.get(i)).Y - H + 1;
                    dy = 0;
                    dx = 0;
                    
                    isCollidingWithGround = true;
                    return true;
                    
                }
                
            }
        }

        isCollidingWithGround = false;
        return false;

    }

    public boolean isCollidingWithLiquid() {
        for (int i = 0; i < ObjectList.liquids.size(); i++) {

            if (hitbox.intersects(((Liquid) ObjectList.liquids.get(i)).hitbox)) {
                return true;
            }

        }

        return false;
    }

    //prevent player from jumping through bottom of platform
    public boolean isCollidingWithBottom() {

        for (int i = 0; i < ObjectList.platforms.size(); i++) {
            if (topHitbox.intersects(((Platform) ObjectList.platforms.get(i)).bottom)) {
                    Y = ((Platform) ObjectList.platforms.get(i)).Y + ((Platform) ObjectList.platforms.get(i)).H - 1;
                    fallSpeed = 1;
                    fallHeight = 1;
                    isCollidingWithBottom = true;
                    return true;
            }
        }
        
        isCollidingWithBottom = false;
        return false;

    }

    public boolean isCollidingWithLeftSide() {

        for (int i = 0; i < ObjectList.platforms.size(); i++) {
            if (middleHitbox.intersects(((Platform) ObjectList.platforms.get(i)).left)) {
                if (isCollidingWithBottom == false) {
                    X = ((Platform) ObjectList.platforms.get(i)).left.x - W + 1;
                    isCollidingWithLeftSide = true;
                    return true;
                }
            }
        }
        
        isCollidingWithLeftSide = false;
        return false;

    }

    public boolean isCollidingWithRightSide() {

        for (int i = 0; i < ObjectList.platforms.size(); i++) {
            if (middleHitbox.intersects(((Platform) ObjectList.platforms.get(i)).right) || topHitbox.intersects(((Platform) ObjectList.platforms.get(i)).right)) {
                if (isCollidingWithBottom == false) {
                    X = ((Platform) ObjectList.platforms.get(i)).right.x + ((Platform) ObjectList.platforms.get(i)).right.width - 1;
                    isCollidingWithRightSide = true;
                    return true;
                }
            }
        }
        
        isCollidingWithRightSide = false;
        return false;

    }

    public void health(double amount, Object attacker) {
       //only serves to allow the gravity method to do damage
    }
    
    public void delete() {
        //only here so gravity can automatically call delete when the object/enemy falls into the void
    }
    
}
