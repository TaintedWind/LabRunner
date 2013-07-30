package engine;

import java.awt.Rectangle;

import liquid.Liquid;

import platform.Platform;
import enemy.AI;
import item.Item;


public class Physics {

	public int fallHeight, jumpHeight, fallSpeed;

	public Rectangle hitbox = new Rectangle();
	public Rectangle bottomHitbox = new Rectangle();
	public Rectangle middleHitbox = new Rectangle();
	public Rectangle topHitbox = new Rectangle();
	public Rectangle range = new Rectangle();

	public double X, Y, dx, dy; //x, y, velocity
	public int W, H; //size

	public void Gravity() {

		if (isCollidingWithGround() == false && isCollidingWithLiquid() == false) {
			this.fallHeight++;
			this.fallSpeed = (this.fallHeight * settings.GlobalVariables.Delta) / 40;
			this.Y += this.fallSpeed;
		} else if (isCollidingWithGround() == true) {
			
			this.Health(-fallHeight / 2);
			
			fallHeight = 0;
			fallSpeed = 0;
		}
		
		if (isCollidingWithLiquid()) {
			fallSpeed = 0;
			Y += ((Liquid)getCollidingLiquid(hitbox)).sinkSpeed * settings.GlobalVariables.Delta;
		}
			
		isCollidingWithBottom();

	}
	
	//moves the object according to dx and dy, great for knockback effects
	public void Velocity() {
		if (isColliding() == false) {
			X += dx * settings.GlobalVariables.Delta;
			Y += dy * settings.GlobalVariables.Delta;
		}
		
		if (isCollidingWithGround()) {
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

	public Object getCollidingEnemy(Rectangle r) {
		
		try {
			for (int t = 0; t <= ObjectList.enemies.size(); t++) {
				if (r.intersects(((AI)ObjectList.enemies.get(t)).hitbox)) {
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
				if (r.intersects(((Liquid)ObjectList.liquids.get(t)).hitbox)) {
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
				if (r.intersects(((Item)ObjectList.items.get(t)).hitbox)) {
					return ObjectList.items.get(t);
				}

			}
			
		} catch (Exception e) {

		}

		return null;
	}
	
	public boolean isColliding() {
		if (isCollidingWithGround() || isCollidingWithLeftSide() || isCollidingWithRightSide()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isCollidingWithGround() {

		for (int i = 0; i < ObjectList.platforms.size(); i++) {

			if (hitbox.intersects(((Platform)ObjectList.platforms.get(i)).top)) {
				Y = ((Platform)ObjectList.platforms.get(i)).Y - H + 1;
				return true;
			}
		}

		return false;

	}
	
	public boolean isCollidingWithLiquid() {
		for (int i = 0; i < ObjectList.liquids.size(); i++) {

			if (hitbox.intersects(((Liquid)ObjectList.liquids.get(i)).hitbox)) {
				return true;
			}
			
		}

		return false;
	}

	//prevent player from jumping through bottom of platform
	public boolean isCollidingWithBottom() {

		for (int i = 0; i < ObjectList.platforms.size(); i++) {

			if (hitbox.intersects(((Platform)ObjectList.platforms.get(i)).bottom)) {
				Y = ((Platform)ObjectList.platforms.get(i)).bottom.y + ((Platform)ObjectList.platforms.get(i)).bottom.height;
				return true;
			}
		}

		return false;

	}

	public boolean isCollidingWithLeftSide() {

		for (int i = 0; i < ObjectList.platforms.size(); i++) {
			if (middleHitbox.intersects(((Platform)ObjectList.platforms.get(i)).left) || topHitbox.intersects(((Platform)ObjectList.platforms.get(i)).left)) {
				X = ((Platform)ObjectList.platforms.get(i)).left.x - W + 1;
				return true;
			}
		}

		return false;

	}

	public boolean isCollidingWithRightSide() {

		for (int i = 0; i < ObjectList.platforms.size(); i++) {
			if (middleHitbox.intersects(((Platform)ObjectList.platforms.get(i)).right) || topHitbox.intersects(((Platform)ObjectList.platforms.get(i)).right)) {
				X = ((Platform)ObjectList.platforms.get(i)).right.x + ((Platform)ObjectList.platforms.get(i)).right.width - 1;
				return true;
			}
		}

		return false;

	}

	public void Health(double amount) {

	}

}
