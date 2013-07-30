package object;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import region.Functions;
import engine.ObjectList;

public class Door extends Object {

	public Door(int x, int y) {
		this.X = x;
		this.Y = y;
		this.W = 50;
		this.H = 100;
		
		try {
			this.defaultTexture = new Image("door.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		this.category = "door";
		
		ObjectList.objects.add(this);
	}

}
