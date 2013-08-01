package item.weapons;

import item.projectiles.Bullet;
import item.projectiles.Missile;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import engine.ObjectList;

public class NukeLauncher extends Weapon {
	
	public NukeLauncher(int x, int y) {

		this.X = x;
		this.Y = y;
		this.W = 60;
		this.H = 36;
		this.offsetX = -32;
		this.offsetY = 40;

		this.numberOfHands = 1;
		this.category = "ranged";

		try {
			defaultTexture = new Image("nuke_launcher.png", false, Image.FILTER_NEAREST);
			rightFacingTexture = new Image("nuke_launcher.png", false, Image.FILTER_NEAREST);
			leftFacingTexture = rightFacingTexture.getFlippedCopy(true, false);
			inventoryTexture = new Image("nuke_launcher_icon.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		ObjectList.items.add(this);

	}
	
	public void shoot() {
		
		if (ObjectList.player.facingDir == "right") {
			new Missile((int)X + W, (int)Y, 0.75, this);
		} else {
			new Missile((int)X, (int)Y, -0.75, this);
		}
		
	}
	
}
