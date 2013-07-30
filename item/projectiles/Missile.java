package item.projectiles;

import item.Item;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import engine.ObjectList;

public class Missile extends Projectile {
	
	public Missile(int x, int y, double xdir) {

		this.X = x;
		this.Y = y;
		this.W = 32;
		this.H = 16;
		
		this.damage = 10;
		
		this.dx = xdir;
		this.dy = getAngleOfElevation() * 2;
		
		this.isSolid = true;

		try {
			defaultTexture = new Image("rocket.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		try {
			leftFacingTexture = new Image("rocket.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		try {
			rightFacingTexture = new Image("rocket.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			// TODO Auto-gen
			e.printStackTrace();
		}
		
		rotateImageToTarget();

		ObjectList.items.add(this);

	}
}
