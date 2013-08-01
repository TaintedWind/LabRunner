package item.projectiles;

import item.Item;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import engine.ObjectList;

public class Arrow extends Projectile {
	
	public Arrow(int x, int y, double xdir, Object p) {

		this.X = x;
		this.Y = y;
		this.W = 32;
		this.H = 16;
		
		this.parentWeapon = p;
		
		this.damage = 1;
		
		this.dx = xdir;
		this.dy = getAngleOfElevation() * 2;
		
		this.deleteOnTouch = false;
		this.isAffectedByGravity = true;
		
		this.parentWeapon = p;

		try {
			defaultTexture = new Image("arrow.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		rotateImageToTarget();

		ObjectList.items.add(this);

	}
}
