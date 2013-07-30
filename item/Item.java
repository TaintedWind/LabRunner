package item;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import player.Inventory;
import engine.ObjectList;
import engine.Physics;
import engine.Timer;
import item.projectiles.Arrow;
import enemy.AI;

//All items extend this class.
public class Item extends Physics {

	public Image defaultTexture;
	public Image leftFacingTexture;
	public Image rightFacingTexture;
	
	protected boolean isEquippable;
	
	protected Timer explosionTimer;

	int handleLength; //positioning
	protected int offsetX;
	protected int offsetY;
	public int damage;
	protected int strength;

	protected String category; //the category and action that the item falls under (used for reference below)

	//this method is overriden by the different types of items (such as item.weapon or item.food)
	public void Update() {

	}
	
	public void CheckForEquip() {
		if (settings.GlobalVariables.E == true && ObjectList.player.hitbox.intersects(hitbox) && Inventory.contains(this) == false) {
			Equip();
		}
	}
	
	public void AlignToPlayer() {
		if (ObjectList.player.facingDir == "left") {
			X = ObjectList.player.X - W + offsetX;
			Y = ObjectList.player.Y + offsetY;
		} else if (ObjectList.player.facingDir == "right") {
			X = ObjectList.player.X + ObjectList.player.W - offsetX;
			Y = ObjectList.player.Y + offsetY;
		}
	}

	public void Equip() {
		Inventory.add(this);
	}


	public void UnEquip() {
		
		System.out.println("Unequipping "+this);
		
		if (ObjectList.player.facingDir == "right") {
			X = ObjectList.player.X + 40;
		} else {
			X = ObjectList.player.X - 20;
		}
		
		Y = ObjectList.player.Y;
		fallHeight = 0;
		fallSpeed = 0;
		
	}

	public void Use() {

	}

	public void Swing() {

	}
	
	public void Attack() {
		((AI)getCollidingEnemy(ObjectList.player.range)).Health(-damage);
	}

	public void delete() {
		
		if (Inventory.contains(this) == true) {
			UnEquip();
			Inventory.remove(this);
		}

		ObjectList.items.remove(this);

	}

	//render the item
	public void draw(Graphics g) {

		if (Inventory.contains(this)) {
			if (Inventory.selectedItem == this) {
				if (ObjectList.player.facingDir == "right") {
					g.drawImage(rightFacingTexture, (int)X, (int)Y, null);
				} else if (ObjectList.player.facingDir == "left") {
					g.drawImage(leftFacingTexture, (int)X, (int)Y, null);
				} else {
					g.drawImage(defaultTexture, (int)X, (int)Y, null);
				}
			}
			
		} else {
			g.drawImage(defaultTexture, (int)X, (int)Y, null);
		}

		//g.setColor(Color.red);
		//g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		//g.setColor(Color.white);

	}

}
