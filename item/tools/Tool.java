package item.tools;

import engine.ObjectList;
import player.Inventory;
import item.Item;

public class Tool extends Item {
	
	int numberOfHands;
	int handleLength;

	public void Update() {
		
		hitbox.setBounds((int)X, (int)Y, W, H);
		
		Gravity();
		Velocity();
		
		CheckForEquip();
		
		if (Inventory.selectedItem == this) {
			AlignToPlayer();
		}
	}
	
	public void Swing() {
		if (numberOfHands == 2) {
			if (ObjectList.player.facingDir == "right") {
				((Item)Inventory.selectedItem).X = X + offsetX + 25;
			} else {
				((Item)Inventory.selectedItem).X = X - offsetX - 25;
			}
		} else if (numberOfHands == 1 && category == "melee") {
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

	
	public void AlignToPlayer() {
		
		//set rotation
		if (numberOfHands == 2) {
			if (ObjectList.player.facingDir == "left") {
				defaultTexture.setRotation(-90);	
			} else {
				defaultTexture.setRotation(90);
			}
		} else if (numberOfHands == 1 && category == "melee") {
			leftFacingTexture.setRotation(-45);
			rightFacingTexture.setRotation(45);
		}
		
		//align position with player
		if (numberOfHands == 1) {
			if (ObjectList.player.facingDir == "left") {
				X = ObjectList.player.X - W + offsetX;
				Y = ObjectList.player.Y + offsetY;
			} else if (ObjectList.player.facingDir == "right") {
				X = ObjectList.player.X + ObjectList.player.W - offsetX;
				Y = ObjectList.player.Y + offsetY;
			}
		}
	}
}
