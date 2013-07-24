package powerup;

import org.newdawn.slick.Image;

import player.Player;
import engine.ObjectList;
import engine.Physics;

//All items extend this class.
public class PowerUp extends Physics {

	Image icon;
	public boolean inScene;

	String type;

	public void EntityCollisionCheck() {
		//look for collision with the player
	}

	public void Update() {
		if (this.inScene) {
			this.hitbox.setBounds((int) this.X, (int) this.Y, this.W, this.H);
			Gravity();
			EntityCollisionCheck();
		}

	}

	//overriden by the individual powerup's useOn method
	public void UseOn(Object target) {

	}

}
