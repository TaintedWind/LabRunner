package item.projectiles;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;

import enemy.AI;
import engine.ObjectList;
import engine.Physics;
import engine.Timer;

import item.Item;

public class Projectile extends Item {
	
	boolean deleteOnTouch, explodesOnTouch, isAffectedByGravity;
	Object parentWeapon;
	Timer despawnTimer = new Timer();
	
	public void update() {
		
		despawnTimer.updateTimer();
		if (despawnTimer.getTime() > 10000) {
			delete();
		}
		
		hitbox.setBounds((int) X, (int) Y, W, H);
		topHitbox.setBounds((int) X, (int) Y, W, H / 3);
		middleHitbox.setBounds((int) X, (int) Y + (H / 3), W, H / 2);
		bottomHitbox.setBounds((int) X, (int) Y + H - bottomHitbox.height, W, H / 5);
		
		//if projectile is not colliding, move based on X and Y velocity
		if (isColliding() == false) {
			if (isAffectedByGravity) {
				gravity();
			}
			
			velocity();
		}
		
		//if the projectile is colliding and is set to delete on touch, then delete
		if (isColliding() == true && deleteOnTouch == true) {
			delete();
		}
		
		//gets the colliding enemy and does damage (then deletes itself)
		if (getCollidingEnemy(hitbox) != null) {
			((Physics) getCollidingEnemy(hitbox)).knockBack(this, 0.015, -0.01);
			((AI) getCollidingEnemy(hitbox)).health(-damage, ObjectList.player);
			delete();
		}
		
	}
	
	public void explode() {
		System.out.println("BOOM");
		delete();
	}
	
	public double getAngleOfElevation() {
		
		//takes the parent of the projectile (aka the gun that shot it) as a parameter
		
		double targetX = Mouse.getX();
		double targetY = 600 - Mouse.getY();
		
		double heightOfOpposite, lengthOfAdjacent;
		
		if (ObjectList.player.facingDir == "right") {
			heightOfOpposite = targetY -= ((Physics)parentWeapon).Y;
			lengthOfAdjacent = targetX -= ((Physics)parentWeapon).X;
		} else {
			heightOfOpposite = targetY -= ((Physics)parentWeapon).Y;
			lengthOfAdjacent = ((Physics)parentWeapon).X - targetX;			
		}
		
		double tan = heightOfOpposite / lengthOfAdjacent;
		
		double angle = Math.atan(tan);
		
		return angle;
		
	}
	
	public void rotateImageToTarget() {
		
		System.out.println(getAngleOfElevation() * 80 + 180);
		
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
	}
	
}
