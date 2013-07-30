package item.food;

import player.Inventory;
import engine.ObjectList;
import item.Item;

public class Food extends Item {
	
	public void Update() {
		
		hitbox.setBounds((int) X, (int) Y, W, H);
		
		//move the object
		Gravity();
		Velocity();
		
		CheckForEquip();
		
		if (Inventory.contains(this)) {
			AlignToPlayer();
		}
		
	}
	
	public void Use() {
		System.out.println("Using "+this);
		ObjectList.player.Health(strength);
		delete();
	}
	
}
