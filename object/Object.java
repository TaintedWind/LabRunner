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
	
	//finish writing the object class to match how the Item class is setup

	public void Update() {

		hitbox.setBounds((int) X, (int) Y, W, H);
		
		if (settings.GlobalVariables.E == true && hitbox.intersects(ObjectList.player.hitbox)) {
			Activate();
		}
		
	}

	//equivalent to the Use() method that items have
	public void Activate() {
		
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
