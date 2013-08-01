package object;

import java.awt.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import region.Functions;

import engine.ObjectList;
import engine.Physics;

public class Object extends Physics {

	Image defaultTexture;
	String category, action;

	public void update() {

		hitbox.setBounds((int) X, (int) Y, W, H);
		
		if (database.GlobalVariables.E == true && hitbox.intersects(ObjectList.player.hitbox)) {
			activate();
		}
		
	}

	public void activate() {
		
		if (category == "door") {
			ObjectList.deleteAllObjects();
			Functions.loadRandomLevel();
		}
		
	}

	public void delete() {
		ObjectList.objects.remove(this);
	}
	
	public void draw(Graphics g) {
		g.drawImage(defaultTexture, (int) X, (int) Y);
	}

}
