package engine;

import item.Item;

import java.awt.Rectangle;

import enemy.AI;

import object.Platform;
import player.Inventory;


public class Physics {

	public int fallHeight, jumpHeight;
	public int fallSpeed;
	
	public Rectangle hitbox = new Rectangle();
	public Rectangle bottomHitbox = new Rectangle();
	public Rectangle middleHitbox = new Rectangle();
	public Rectangle topHitbox = new Rectangle();
	public Rectangle range = new Rectangle();

	public double X, Y, dx, dy; //x, y, velocity
	public int W, H; //size
	
	public void Gravity() {
		
		if (isCollidingWithGround() == false) {
			this.fallHeight++;
			this.fallSpeed = (this.fallHeight * settings.GlobalVariables.Delta) / 40;
			this.Y += this.fallSpeed;
		} else {
			fallHeight = 0;
			fallSpeed = 0;
		}
		
		isCollidingWithBottom();

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
	
	public void getCollidingObject() {
		
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
	
	//prevent player from jumping through bottom of platform
	public boolean isCollidingWithBottom() {
		
		for (int i = 0; i < ObjectList.platforms.size(); i++) {
			
			if (hitbox.intersects(((Platform)ObjectList.platforms.get(i)).bottom)) {
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
