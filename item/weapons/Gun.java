package item.weapons;

import item.projectiles.Arrow;
import item.projectiles.Bullet;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import engine.ObjectList;

public class Gun extends Weapon {
	public Gun(int x, int y) {

		this.X = x;
		this.Y = y;
		this.W = 24;
		this.H = 24;
		this.handleLength = 0;
		this.offsetX = -10;
		this.offsetY = 40;

		this.numberOfHands = 1;
		this.category = "ranged";
		this.damage = 10;

		try {
			defaultTexture = new Image("pistol.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		try {
			leftFacingTexture = new Image("pistol.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		try {
			rightFacingTexture = new Image("pistol.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		ObjectList.items.add(this);

	}
	
	public void Shoot() {
		
		if (ObjectList.player.facingDir == "right") {
			new Bullet((int)X, (int)Y, 2);
		} else {
			new Bullet((int)X, (int)Y, -2);
		}
		
	}

}
