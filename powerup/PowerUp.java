package powerup;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import engine.ObjectList;
import engine.Physics;

public class PowerUp extends Physics {

	public Image defaultTexture;
	public String category;
	public int strength;

	public void update() {

		hitbox.setBounds((int) X, (int) Y, W, H);
		gravity();

		if (hitbox.intersects(ObjectList.player.hitbox)) {
			affect(ObjectList.player.hitbox);
		}

	}

	public void delete() {
		ObjectList.powerups.remove(this);
	}

	public void affect(Object target) {
		if (category == "healing") {
			ObjectList.player.health(strength, this);
			delete();
		}
	}

	public void draw(Graphics g) {
		g.drawImage(defaultTexture, (int) X, (int) Y, null);
	}

}
