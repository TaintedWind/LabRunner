package object;

import java.awt.image.BufferedImage;

import org.newdawn.slick.Graphics;

import org.newdawn.slick.Image;

import engine.ObjectList;
import engine.Physics;

public class Object extends Physics {

	Image texture;
	int ID = 0;
	public boolean inScene;
	int repairDelay;
	int repairLevel;

	public void Update() {

		if (this.inScene) {
			this.hitbox.setBounds((int) this.X, (int) this.Y, this.W, this.H);
			EntityCollisionCheck();
		}
	}

	public void EntityCollisionCheck() {
		//to be filled in when I have a use for this method
		
	}

	public void Interact() {

	}
	
	public void draw(Graphics g) {
		
	}

	public void delete() {
		ObjectList.objects.remove(this);
	}

}
