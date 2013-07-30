package item.weapons;

import engine.ObjectList;
import player.Inventory;
import item.Item;
import item.projectiles.Arrow;
import item.projectiles.Bullet;

public class Weapon extends Item {
	int handleLength;
	int numberOfHands;	
	
	public void Update() {
		
		hitbox.setBounds((int)X, (int)Y, W, H);
		
		Gravity();
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
	
	public void Use() {
		if (category == "ranged") {
			Shoot();
		}
	}
	
	public void Shoot() {
		//overriden by the ranged weapon
	}

	
	public void AlignToPlayer() {
		
		if (numberOfHands == 2) {
			defaultTexture.setRotation(0);
			leftFacingTexture.setRotation(0);
			rightFacingTexture.setRotation(0);
		} else if (numberOfHands == 1) {
			if (category == "melee") {
				leftFacingTexture.setRotation(-45);
				rightFacingTexture.setRotation(45);
			} else {
				leftFacingTexture.setRotation(-180);
				rightFacingTexture.setRotation(0);				
			}
		}
		
		if (numberOfHands == 1) {
			if (ObjectList.player.facingDir == "left") {
				X = ObjectList.player.X - W - offsetX;
				Y = ObjectList.player.Y + offsetY;
			} else if (ObjectList.player.facingDir == "right") {
				X = ObjectList.player.X + ObjectList.player.W + offsetX;
				Y = ObjectList.player.Y + offsetY;
			}
		}
	}
	
}
