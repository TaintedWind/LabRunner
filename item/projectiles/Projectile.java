package item.projectiles;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;

import enemy.AI;
import engine.ObjectList;
import engine.Physics;

import item.Item;

public class Projectile extends Item {
	
	boolean isSolid, explodesOnTouch;
	
	public void Update() {
		
		hitbox.setBounds((int) X, (int) Y, W, H);
		topHitbox.setBounds(hitbox.getBounds());
		middleHitbox.setBounds(hitbox.getBounds());
		bottomHitbox.setBounds(hitbox.getBounds());
		
		if (isColliding() == false) {
			Gravity();
			Velocity();
		} else {
			if (isSolid == false) {
				delete();
			}
		}
		
		if (getCollidingEnemy(hitbox) != null) {
			((AI) getCollidingEnemy(hitbox)).Health(-damage);
			delete();
		}
		
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
	
	public void rotateImageToTarget() {
		
		System.out.println(getAngleOfElevation() * 80);
		
		if (ObjectList.player.facingDir == "right") {
			if (getAngleOfElevation() * 80 < 45 || getAngleOfElevation() > -45) {
				defaultTexture.setRotation((float) getAngleOfElevation() * 80);
			}
		} else {
			defaultTexture.setRotation((float) -getAngleOfElevation() * 80 + 180);			
		}
	}
	
}
