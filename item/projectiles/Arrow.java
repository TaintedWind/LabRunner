package item.projectiles;

import item.Item;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import engine.ObjectList;

public class Arrow extends Projectile {
	
	public Arrow(int x, int y, double xdir) {

		this.X = x;
		this.Y = y;
		this.W = 32;
		this.H = 16;
		
		this.damage = 1;
		
		this.dx = xdir;
		this.dy = getAngleOfElevation() * 2;
		
		this.isSolid = true;

		try {
			defaultTexture = new Image("arrow.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		try {
			leftFacingTexture = new Image("arrow.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		try {
			rightFacingTexture = new Image("arrow.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			// TODO Auto-gen
			e.printStackTrace();
		}
		
		rotateImageToTarget();

		ObjectList.items.add(this);

	}
}
