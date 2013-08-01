package item.tools;

import engine.ObjectList;
import player.Inventory;
import item.Item;

public class Tool extends Item {
	
	int handleLength;
	
	boolean swings;

	public void update() {
		
		hitbox.setBounds((int) X, (int) Y, W, H);
		topHitbox.setBounds((int) X, (int) Y, W, H / 3);
		middleHitbox.setBounds((int) X, (int) Y + (H / 3), W, H / 2);
		bottomHitbox.setBounds((int) X, (int) Y + H - bottomHitbox.height, W, H / 5);
		
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
		swing();
	}
	
	public void swing() {
		if (swings) {
			if (ObjectList.player.facingDir == "right") {
				((Item)Inventory.selectedItem).rightFacingTexture.setRotation(90);
				Y += W;
				X += handleLength;
			} else if (ObjectList.player.facingDir == "left") {
				((Item)Inventory.selectedItem).leftFacingTexture.setRotation(-90);
				Y += W;
				X -= handleLength;
			}
		}
	}

	
	public void alignToPlayer() {
		
		if (swings) {
			leftFacingTexture.setRotation(-45);
			rightFacingTexture.setRotation(45);
			defaultTexture.setRotation(0);
		}
		
		if (ObjectList.player.facingDir == "left") {
			X = ObjectList.player.X - W - offsetX;
			Y = ObjectList.player.Y + offsetY;
		} else if (ObjectList.player.facingDir == "right") {
			X = ObjectList.player.X + ObjectList.player.W + offsetX;
			Y = ObjectList.player.Y + offsetY;
		}
		
	}
}
