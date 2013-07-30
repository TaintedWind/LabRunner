package item.weapons;

import item.projectiles.Bullet;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import engine.ObjectList;

public class NukeLauncher extends Weapon {
	
	public NukeLauncher(int x, int y) {

		this.X = x;
		this.Y = y;
		this.W = 12;
		this.H = 48;
		this.offsetX = -5;
		this.offsetY = 40;

		this.numberOfHands = 1;
		this.category = "ranged";

		try {
			defaultTexture = new Image("nuke_launcher.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		try {
			leftFacingTexture = new Image("nuke_launcher_left.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		try {
			rightFacingTexture = new Image("nuke_launcher.png", false, Image.FILTER_NEAREST);
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
