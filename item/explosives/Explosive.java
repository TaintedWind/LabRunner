package item.explosives;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import player.Inventory;

import engine.ObjectList;
import engine.Timer;
import item.Item;

public class Explosive extends Item {
	
	Timer detonationTimer;
	
	public void update() {
		
		if (detonationTimer != null) {
			detonationTimer.updateTimer();
			if (detonationTimer.getTime() == 1000) {
				System.out.println("BOOM");
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
			if (Inventory.selectedItem == this) {
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
		
		detonationTimer = new Timer();
		
		if (Inventory.contains(this)) {
			dx = 0.75;
			dy = getAngleOfElevation();
			Inventory.remove(this);
		}
	}
	
	public void Detonate() {
		
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
