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
	
	public void Update() {
		
		if (detonationTimer != null) {
			detonationTimer.updateTimer();
			if (detonationTimer.getTime() == 1000) {
				System.out.println("BOOM");
				delete();
			}
		}
		
		hitbox.setBounds((int) X, (int) Y, W, H);
		topHitbox.setBounds(hitbox.getBounds());
		middleHitbox.setBounds(hitbox.getBounds());
		bottomHitbox.setBounds(hitbox.getBounds());
		
		//move the explosive
		Gravity();
		Velocity();
		
		CheckForEquip();
		
		if (Inventory.contains(this)) {
			AlignToPlayer();
		}
		
		
	}
	
	public void Use() {
		
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
