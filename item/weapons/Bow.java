package item.weapons;

import item.Item;
import item.projectiles.Arrow;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import engine.ObjectList;

public class Bow extends Weapon {
	
	public Bow(int x, int y) {

		this.X = x;
		this.Y = y;
		this.W = 12;
		this.H = 48;
		this.offsetX = -5;
		this.offsetY = 40;

		this.numberOfHands = 1;
		this.category = "ranged";

		try {
			defaultTexture = new Image("bow.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		try {
			leftFacingTexture = new Image("bow.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		try {
			rightFacingTexture = new Image("bow.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		ObjectList.items.add(this);

	}
	
	public void Shoot() {
		
		if (ObjectList.player.facingDir == "right") {
			new Arrow((int)X, (int)Y, 1);
		} else {
			new Arrow((int)X, (int)Y, -1);
		}
		
	}

}