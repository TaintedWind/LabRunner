package powerup;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import engine.ObjectList;
import engine.Physics;

public class PowerUp extends Physics {

	public Image defaultTexture;
	public String category;
	public int strength;

	public void Update() {

		hitbox.setBounds((int) X, (int) Y, W, H);
		Gravity();

		if (hitbox.intersects(ObjectList.player.hitbox)) {
			Affect(ObjectList.player.hitbox);
		}

	}

	public void delete() {
		ObjectList.powerups.remove(this);
	}

	public void Affect(Object target) {
		if (category == "healing") {
			ObjectList.player.Health(strength);
			delete();
		}
	}

	public void draw(Graphics g) {
		g.drawImage(defaultTexture, (int) X, (int) Y, null);
	}

}
