package item;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import player.Inventory;
import player.Player;
import enemy.AI;
import engine.ObjectList;
import engine.Physics;

//All items extend this class.
public class Item extends Physics {

	public Image defaultTexture;
	public Image leftFacingTexture;
	public Image rightFacingTexture;
	
	int handleLength; //the length of the handle, aka where the item meets the player's hand (used to properly align item)
	
	public int offsetX, offsetY;
	public int damage;
	int usageDelay = 0;
	
	String swingMotion; //determines how player holds the item
	String category; //the category that the item falls under (used for reference)
	
	//this method updates the position of the item and calls the gravity/collision/etc
	public void Update() {
		
		//set hitboxes
		hitbox.setBounds((int) X, (int) Y, W, H);	
		topHitbox.setBounds(hitbox.getBounds());
		middleHitbox.setBounds(hitbox.getBounds());
		bottomHitbox.setBounds(hitbox.getBounds());
		
		//call gravity method
		Gravity();
		
		//check for equip
		if (settings.GlobalVariables.E == true && ObjectList.player.hitbox.intersects(hitbox) && Inventory.contains(this) == false) {
			Equip();
		}
		
		//reset transformations (aka the default angles that are used when player is not swinging the item around)
		if (swingMotion == "jab") {
			defaultTexture.setRotation(0);
			leftFacingTexture.setRotation(0);
			rightFacingTexture.setRotation(0);
		} else if (swingMotion == "swing") {
			leftFacingTexture.setRotation(-45);
			rightFacingTexture.setRotation(45);
		}

		//if item is equipped, override gravity and attach to player (one-handed weapons switch side as player changes facingdir)
		if (Inventory.contains(this)) {
			if (this.equals(Inventory.selectedItem)) {
				//if the type is two handed (aka plunger), then align to X + offsetX and Y + offsetY
				if (swingMotion == "jab" || swingMotion == "none") {
					X = ObjectList.player.X + offsetX;
					Y = ObjectList.player.Y + offsetY;
				//if type is one-handed (aka sword), then align to different sides of player as player turns	
				} else if (swingMotion == "swing") {
					if (ObjectList.player.facingDir == "left") {
						X = ObjectList.player.X - W;
						Y = ObjectList.player.Y + offsetY;
					} else if (ObjectList.player.facingDir == "right") {
						X = ObjectList.player.X + ObjectList.player.W;
						Y = ObjectList.player.Y + offsetY;
					}
				}
			} else {
				this.X = -50;
				this.Y = -50;
			}
			
			this.fallHeight = 0;
			this.fallSpeed = 0;	
		}
		
	}

	public void EntityCollisionCheck() {

	}

	public void Equip() {
		Inventory.add(this);
	}


	public void UnEquip() {
		System.out.println("Unequipping "+this);
		X = ObjectList.player.X + 50;
		Y = ObjectList.player.Y;
		
		fallHeight = 0;
		fallSpeed = 0;

	}

	//overriden by the individual object's Use method
	public void Use() {

	}
	
	public void Swing() {
		if (swingMotion == "jab") {
			if (ObjectList.player.facingDir == "right") {
				((Item)Inventory.selectedItem).X = X + 25;
			} else {
				((Item)Inventory.selectedItem).X = X - 25;
			}
		} else if (swingMotion == "swing") {
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
	
	public void delete() {
		if (Inventory.contains(this) == false) {
			ObjectList.items.remove(this);
		}
	}
	
	//render the item
	public void draw(Graphics g) {
		
		if (Inventory.contains(this)) {
			if (ObjectList.player.facingDir == "right") {
				g.drawImage(rightFacingTexture, (int)X, (int)Y, null);
			} else if (ObjectList.player.facingDir == "left") {
				g.drawImage(leftFacingTexture, (int)X, (int)Y, null);
			} else {
				g.drawImage(defaultTexture, (int)X, (int)Y, null);
			}
		} else {
			g.drawImage(defaultTexture, (int)X, (int)Y, null);
		}
		
	//	g.setColor(Color.red);
	//	g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
	//	g.setColor(Color.white);

	}

}
