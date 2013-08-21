package engine;

import database.ObjectList;
import java.awt.Rectangle;

import liquid.Liquid;

import platform.Platform;
import enemy.AI;
import item.Item;
import player.Inventory;
import player.Player;
import powerup.PowerUp;

public class Physics {

    public int fallHeight, jumpHeight, fallSpeed;
    public Rectangle hitbox = new Rectangle();
    public Rectangle bottomHitbox = new Rectangle();
    public Rectangle middleHitbox = new Rectangle();
    public Rectangle topHitbox = new Rectangle();
    public Rectangle range = new Rectangle();
    public double X, Y, dx, dy; //x, y, velocity
    public int W, H; //size

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
            Y += ((Liquid) getCollidingLiquid(hitbox)).sinkSpeed * database.GlobalVariables.deltaTime;
        }
        
        if (Y > 1000 && this.getClass() != Player.class) {
            delete();
        }

        isCollidingWithBottom();

    }

    //moves the object according to dx and dy, great for knockback effects
    public void velocity() {

        if (isColliding() == false) {
            X += dx * database.GlobalVariables.deltaTime;
            Y += dy * database.GlobalVariables.deltaTime;
        }

        if (isCollidingWithGround() || isCollidingWithLiquid()) {
            dy = 0;
            dx = 0;
        }

        if (isCollidingWithLiquid()) {
            dy = 0;
        }

        if (isCollidingWithLeftSide()) {
            dx = 0;
        }

        if (isCollidingWithRightSide()) {
            dx = 0;
        }

        if (isCollidingWithBottom()) {
            dy = 0;
        }

    }

    public void knockBack(Object attacker, double xvel, double yvel) {

        if (attacker == Inventory.getSelectedItem()) {
            if (ObjectList.player.X + ObjectList.player.W / 2 < X) {
                Y -= 1;
                dx = +xvel * 1.5 * database.GlobalVariables.deltaTime;
            } else {
                Y -= 1;
                dx = -xvel * database.GlobalVariables.deltaTime;
            }   
        } else {
            if (((Physics) attacker).X + ((Physics) attacker).W / 2 < X) {
                Y -= 1;
                dx = xvel * database.GlobalVariables.deltaTime;
            } else {
                Y -= 1;
                dx = -xvel * database.GlobalVariables.deltaTime;
            }            
        }

        if (((Physics)attacker).Y + ((Physics)attacker).H / 2 > Y) {
           dy += yvel * database.GlobalVariables.deltaTime;
        } else {
            dy -= yvel * database.GlobalVariables.deltaTime;            
        }
        
    }

    public Object getCollidingEnemy(Rectangle r) {

        try {
            for (int t = 0; t <= ObjectList.enemies.size(); t++) {
                if (r.intersects(((AI) ObjectList.enemies.get(t)).hitbox)) {
                    return ObjectList.enemies.get(t);
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

    public Object getCollidingPlatform(Rectangle r) {

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
                if (r.intersects(((Item) ObjectList.items.get(t)).hitbox)) {
                    return ObjectList.items.get(t);
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
            return true;
        } else {
            return false;
        }
    }

    public boolean isCollidingWithGround() {

        for (int i = 0; i < ObjectList.platforms.size(); i++) {

            if (bottomHitbox.intersects(((Platform) ObjectList.platforms.get(i)).top)) {
                Y = ((Platform) ObjectList.platforms.get(i)).Y - H + 1;
                dy = 0;
                return true;
            }
        }

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
                Y = ((Platform) ObjectList.platforms.get(i)).bottom.y + ((Platform) ObjectList.platforms.get(i)).bottom.height;
                return true;
            }
        }

        return false;

    }

    public boolean isCollidingWithLeftSide() {

        for (int i = 0; i < ObjectList.platforms.size(); i++) {
            if (middleHitbox.intersects(((Platform) ObjectList.platforms.get(i)).left) || topHitbox.intersects(((Platform) ObjectList.platforms.get(i)).left)) {
                X = ((Platform) ObjectList.platforms.get(i)).left.x - W + 1;
                return true;
            }
        }

        return false;

    }

    public boolean isCollidingWithRightSide() {

        for (int i = 0; i < ObjectList.platforms.size(); i++) {
            if (middleHitbox.intersects(((Platform) ObjectList.platforms.get(i)).right) || topHitbox.intersects(((Platform) ObjectList.platforms.get(i)).right)) {
                X = ((Platform) ObjectList.platforms.get(i)).right.x + ((Platform) ObjectList.platforms.get(i)).right.width - 1;
                return true;
            }
        }

        return false;

    }

    public void health(double amount, Object attacker) {
       //only serves to allow the gravity method to do damage
    }
    
    public void delete() {
        //only here so gravity can automatically call delete when the object/enemy falls into the void
    }
    
}
