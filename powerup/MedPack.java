package powerup;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import player.Player;
import engine.ObjectList;

public class MedPack extends PowerUp {

	public MedPack() {
		this.X = 0;
		this.Y = 0;
		this.W = 48;
		this.H = 48;
		this.type = "tool";
		this.inScene = true;
		ObjectList.powerups.add(this);

	}

	public void draw(Graphics g) {

		try {
			this.icon = new Image("medpack.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}

		if (this.inScene) {
			g.drawImage(this.icon, (int) this.X, (int) this.Y, null);
		}
	}

	@Override
	public void UseOn(Object target) {

		//perform the task on the target then remove from scene

		((Player) target).Health(+50);

		//this.delete();

	}
}
