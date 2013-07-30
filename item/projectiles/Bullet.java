package item.projectiles;

import item.Item;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import engine.ObjectList;

public class Bullet extends Projectile {
	
	public Bullet(int x, int y, double xdir) {

		this.X = x;
		this.Y = y;
		this.W = 32;
		this.H = 16;
		
		this.damage = 10;
		
		this.isSolid = false;
		
		this.dx = xdir;
		this.dy = getAngleOfElevation() * 2;

		try {
			defaultTexture = new Image("bullet.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		try {
			leftFacingTexture = new Image("bullet.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		try {
			rightFacingTexture = new Image("bullet.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			// TODO Auto-gen
			e.printStackTrace();
		}
		
		rotateImageToTarget();

		ObjectList.items.add(this);

	}
}
